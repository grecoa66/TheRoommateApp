package rommateapp.development.albie.therommateapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Albert on 10/15/2015.
 */

// Instances of this class are fragments representing a single
// object in our collection.
public  class DemoObjectFragment extends android.support.v4.app.Fragment  {


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        Bundle args = getArguments();
        int tab = args.getInt("whichTab");

        View rootView = inflater.inflate(
                getTabById(tab), container, false);

        TextView tv = new TextView(getActivity().getApplicationContext());
        

        View rv = rootView.findViewById(R.id.text1);

       // ((TextView) rootView.findViewById(R.id.text1)).setText(
        //        Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }

    private int getTabById(int id){
        switch (id) {
            case 0://all current
                return R.layout.fragment_current;
            case 1: //Me
                return R.layout.fragment_me;
            case 2: //past
                return R.layout.fragment_all;
            default:
                return R.layout.fragment_me;
        }
    }
}