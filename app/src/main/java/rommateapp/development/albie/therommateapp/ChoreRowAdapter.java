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


public class ChoreRowAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Chore> chores;
    public ChoreRowAdapter( Context context, ArrayList<Chore> chores) {
        this.context = context;
        this.chores = chores;
    }

    @Override
    public int getCount() {
        return chores.size();
    }

    @Override
    public Object getItem(int pos) {
        return chores.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0l;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
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

        final int pos = position;


        //we can create the function we want to attach to the  image here
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chore c = chores.get(position);
                chores.remove(c);

                HTTP_Connector httpcon = new HTTP_Connector(context);
                HTTP_Connector.deleteChore dbDeleteChore = httpcon.new deleteChore();
                dbDeleteChore.execute(c.getChoreId());

                notifyDataSetChanged();
                Toast.makeText(v.getContext(),
                        "chore: "+c.getTitle()+" complete!",
                        Toast.LENGTH_LONG).show();
            }
        });

        return rowView;
    }


} 