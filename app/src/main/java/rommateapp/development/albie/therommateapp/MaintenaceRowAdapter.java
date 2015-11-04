package rommateapp.development.albie.therommateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MaintenaceRowAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<MaintenanceItem> mainteList;

    public MaintenaceRowAdapter(Context context, ArrayList<MaintenanceItem> mainteList) {
        this.context = context;
        this.mainteList = mainteList;
    }

    @Override
    public int getCount() {
        return mainteList.size();
    }

    @Override
    public Object getItem(int pos) {
        return mainteList.get(pos);
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
        View rowView = inflater.inflate(R.layout.maintenance_row, parent, false);

        TextView mainteDescTv = (TextView) rowView.findViewById(R.id.mainteDesc);
        TextView mainteAssignedToTv = (TextView) rowView.findViewById(R.id.assignedTo);
        TextView mainteAssignedByTv = (TextView) rowView.findViewById(R.id.assignedBy);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        // Change the icon for Windows and iPhone
        MaintenanceItem mItem = mainteList.get(position);
        mainteAssignedToTv.setText("To: "+mItem.getCausingUser());
        mainteAssignedByTv.setText("From: "+ mItem.getPurchaseUser());
        mainteDescTv.setText(mItem.getDesc());

        final int pos = position;


        //we can create the function we want to attach to the  image here
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaintenanceItem mItem = mainteList.get(pos);
                mainteList.remove(mItem);
                mItem.setIsComplete(true);
                notifyDataSetChanged();
                Toast.makeText(v.getContext(),
                        "item "+mItem.getDesc() + " was completed.",
                        Toast.LENGTH_LONG).show();

            }
        });

        return rowView;
    }
} 