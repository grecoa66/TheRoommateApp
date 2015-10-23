package rommateapp.development.albie.therommateapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Albert on 10/15/2015.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public class MaintenanceFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static MaintenanceFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        MaintenanceFragment fragment = new MaintenanceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= null;
        if(mPage==1){
            view = inflater.inflate(R.layout.maintenance_me, container, false);
        } else if(mPage==2){
            view = inflater.inflate(R.layout.maintenance_house, container, false);
        } else{
            view = inflater.inflate(R.layout.maintenance_all, container, false);
        }

        return view;
    }
}