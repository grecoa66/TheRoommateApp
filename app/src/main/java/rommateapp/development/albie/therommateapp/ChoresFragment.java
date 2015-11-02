package rommateapp.development.albie.therommateapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Albert on 10/15/2015.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public class ChoresFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private Activity mContext;
    private int mPage;

    public static ChoresFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        ChoresFragment fragment = new ChoresFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mPage = getArguments().getInt(ARG_PAGE);
        //list view things
        //test data
        ArrayList<Chore> chores = new ArrayList<Chore>();
        User albie = new User(0, "albie","rynkie", "rynk@a.com","842523942");
        User greco = new User(0, "greco","alex", "rynk@a.com","842523942");
        User matt = new User(0, "Matt","cieslak", "rynk@a.com","842523942");

        chores.add(new Chore(0, "Sweep", "kitchen", albie, greco, true ));
        chores.add(new Chore(0, "Garbage", "kitchen", matt, greco, false ));
        chores.add(new Chore(0, "Mop", "living room", matt, albie, false ));
        chores.add(new Chore(0, "Dishes", "sink is full", greco, matt, true ));
        chores.add(new Chore(0, "Walk Dog", "sparky needs to go", albie, greco, false ));
        chores.add(new Chore(0, "Laundry", "it stinks", greco, matt, false ));

        ListView lv;
        ListView lv2;
        ListView lv3;
        ChoreRowAdapter adapter;
        ChoreRowNoPicture adapter2;
        ChoreRowNoPicture adapter3;

        ArrayList<Chore> justMe = new ArrayList<>();
        for(int i=0;i<chores.size();i++){
            Chore c = chores.get(i);
            if(c.getAssignedUser().getlName().equals("rynkie") && c.isComplete()==false)
            {
                justMe.add(c);
            }
        }

        String[] values = new String[justMe.size()];
        for(int i=0;i<justMe.size();i++){values[i]="";}
        adapter = new ChoreRowAdapter(getActivity(), justMe, values);



        ArrayList<Chore> house = new ArrayList<Chore>();
        for(int i=0;i<chores.size();i++){
            Chore c = chores.get(i);
            if(c.isComplete()==false)
            {
                house.add(c);
            }
        }

        values = new String[house.size()];
        for(int i=0;i<house.size();i++){values[i]="";}
        adapter2 = new ChoreRowNoPicture(getActivity(), house, values);

        values = new String[chores.size()];
        for(int i=0;i<chores.size();i++){values[i]="";}
        adapter3 = new ChoreRowNoPicture(getActivity(), chores, values);



        lv = (ListView) getActivity().findViewById(R.id.list);
        lv2 = (ListView) getActivity().findViewById(R.id.listHouse);
        lv3 = (ListView) getActivity().findViewById(R.id.listAll);
        lv.setAdapter(adapter);
        lv2.setAdapter(adapter2);
        lv3.setAdapter(adapter3);

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                view.setSelected(true);
            }
        });
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentLayout;

        if(mPage==1){
            fragmentLayout = inflater.inflate(R.layout.chores_me, container, false);
        } else if(mPage==2){
            fragmentLayout = inflater.inflate(R.layout.chores_house, container, false);

        } else if(mPage==3){
            fragmentLayout = inflater.inflate(R.layout.chores_all, container, false);
        }else{
            fragmentLayout = inflater.inflate(R.layout.main, container, false);
        }


        return fragmentLayout;
    }
}