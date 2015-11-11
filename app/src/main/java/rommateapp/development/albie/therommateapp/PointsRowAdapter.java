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


public class PointsRowAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Points> pointsList;

    public PointsRowAdapter(Context context, ArrayList<Points> pointsList) {
        this.context = context;
        this.pointsList = pointsList;
    }

    @Override
    public int getCount() {
        return pointsList.size();
    }

    @Override
    public Object getItem(int pos) {
        return pointsList.get(pos);
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
        View rowView = inflater.inflate(R.layout.points_row, parent, false);

        TextView userTv = (TextView) rowView.findViewById(R.id.pointsUser);
        TextView pointsTV = (TextView) rowView.findViewById(R.id.pointsPoints);


        // Change the icon for Windows and iPhone
        Points mItem = pointsList.get(position);
        userTv.setText(mItem.user);
        pointsTV.setText(""+ mItem.points);


        return rowView;
    }
} 