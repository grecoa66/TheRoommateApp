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

/**
 * Created by Albert on 10/15/2015.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public class ChoresFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private Activity mContext;
    private int mPage;
    private ListView lv;
    private ListView lv2;
    private ListView lv3;
    private ChoreRowAdapter adapter;

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
        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2" };
        adapter = new ChoreRowAdapter(getActivity(), values);

        lv = (ListView) getActivity().findViewById(R.id.list);
        lv2 = (ListView) getActivity().findViewById(R.id.listHouse);
        lv3 = (ListView) getActivity().findViewById(R.id.listAll);
        lv.setAdapter(adapter);
        lv2.setAdapter(adapter);
        lv3.setAdapter(adapter);
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