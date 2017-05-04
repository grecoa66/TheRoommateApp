package rommateapp.development.albie.therommateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by alexgreco on 11/3/15.
 */
public class GroceryRowAdapterForMain extends BaseAdapter{

    private final Context context;
    private ArrayList<Grocery> allGroc;
    private CheckBox checkBox;

    public GroceryRowAdapterForMain(Context context, ArrayList<Grocery> allGroc) {
        this.context = context;
        this.allGroc = allGroc;
    }

    @Override
    public int getCount() {
        return allGroc.size();
    }

    @Override
    public Object getItem(int pos) {
        return allGroc.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0l;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.grocery_row_for_main, parent, false);

        TextView grocName       = (TextView) rowView.findViewById(R.id.grocName);
        TextView grocAssignedBy = (TextView) rowView.findViewById(R.id.assignedBy);
        TextView grocQuant      = (TextView) rowView.findViewById(R.id.grocQuant);


        // Change the icon for Windows and iPhone
        Grocery grocery = allGroc.get(position);
        grocName.setText(grocery.getItemName());
        grocAssignedBy.setText("From: " + grocery.getRequestUser());
        grocQuant.setText(Integer.toString(grocery.getQuantity()));
        final int pos = position;


        //we can create the function we want to attach to the  image here

        return rowView;
    }


}
