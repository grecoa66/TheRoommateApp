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


public class noDataAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<String> chores;
    public noDataAdapter( Context context, ArrayList<String> emptyList) {
        this.context = context;
        this.chores = emptyList;
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
        View rowView = inflater.inflate(R.layout.nodata_row, parent, false);

        return rowView;
    }
} 