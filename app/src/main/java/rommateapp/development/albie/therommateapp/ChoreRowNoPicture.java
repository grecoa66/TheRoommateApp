package rommateapp.development.albie.therommateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ChoreRowNoPicture extends ArrayAdapter<String> {
    private final Context context;
    private ArrayList<Chore> chores;
    public ChoreRowNoPicture(Context context, ArrayList<Chore> chores, String [] values) {
        super(context, R.layout.chore_row, values);
        this.context = context;
        this.chores = chores;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.chore_row, parent, false);

        TextView choreNameTv = (TextView) rowView.findViewById(R.id.choreName);
        TextView choreDescTv = (TextView) rowView.findViewById(R.id.choreDesc);
        TextView choreAssignedToTv = (TextView) rowView.findViewById(R.id.assignedTo);
        TextView choreAssignedByTv = (TextView) rowView.findViewById(R.id.assignedBy);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);


        // Change the icon for Windows and iPhone
        Chore c = chores.get(position);

        choreAssignedToTv.setText("To: "+c.getAssignedUser());
        choreAssignedByTv.setText("From: "+ c.getRequestUser());
        choreNameTv.setText(c.getTitle());
        choreDescTv.setText(c.getDesc());
        imageView.setVisibility(View.INVISIBLE);

        final int pos = position;

        imageView.setVisibility(View.INVISIBLE);

        return rowView;
    }
} 