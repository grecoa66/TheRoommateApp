package rommateapp.development.albie.therommateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ChoreRowAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;//should be array of chores

    public ChoreRowAdapter(Context context, String[] values) {
        super(context, R.layout.chore_row, values);
        this.context = context;
        this.values = values;
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
        String s = values[position]; //change to chore

        choreAssignedToTv.setText("To: Greco");
        choreAssignedByTv.setText("From: Albie");
        choreNameTv.setText("Sweep");
        choreDescTv.setText("Why are you holding a broom?");


        final int pos = position;


        //we can create the function we want to attach to the  image here
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "item + "+pos,
                        Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }
} 