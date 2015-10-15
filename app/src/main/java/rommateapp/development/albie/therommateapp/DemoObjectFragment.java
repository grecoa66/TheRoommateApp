package rommateapp.development.albie.therommateapp;

import android.app.Fragment;
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
    public static final String ARG_OBJECT = "object";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_chores, container, false);
        Bundle args = getArguments();

        View rv = rootView.findViewById(R.id.text1);

       // ((TextView) rootView.findViewById(R.id.text1)).setText(
        //        Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}