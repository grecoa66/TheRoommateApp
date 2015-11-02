package rommateapp.development.albie.therommateapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Alex G on 10/21/2015.
 */
public class MaintenanceActivity extends AppCompatActivity {

    private Context mContext;

    ArrayList<MaintenanceItem> mainteList = new ArrayList<MaintenanceItem>();
    ArrayList<MaintenanceItem> justMe = new ArrayList<>();
    ArrayList<MaintenanceItem> group = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_main);
        mContext = this;

        mainteList.add(new MaintenanceItem(1, "Broken Door", "albie", "albie", false));
        mainteList.add(new MaintenanceItem(2, "scuffed paint in living room", "greco", "matt", false));
        mainteList.add(new MaintenanceItem(3, "leak in sink", "matt", "greco", false));
        mainteList.add(new MaintenanceItem(4, "Burnt out bulb", "greco", "greco", false));
        mainteList.add(new MaintenanceItem(5, "Broken Window", "matt", "matt", false));
        mainteList.add(new MaintenanceItem(6, "Broken toilet seat", "ablie", "greco", false));
        mainteList.add(new MaintenanceItem(7, "New spare Key", "matt", "matt", false));


        ListView lv = (ListView) findViewById(R.id.list_mainte);
        meAdapter(lv);


    }//end on create

    public void meAdapter(View view){

        ListView lv = (ListView) findViewById(R.id.list_mainte);

        MaintenaceRowAdapter adapter;



        for(int i = 0; i < mainteList.size() ; i++){
            MaintenanceItem mItem = mainteList.get(i);
            if(mItem.getCausingUser() == "albie" && !justMe.contains(mItem)) {
                justMe.add(mItem);
            }
        }

        String[] values = new String[justMe.size()];
        for(int i = 0; i < justMe.size() ; i++){values[i]="";}
        adapter = new MaintenaceRowAdapter(mContext, justMe, values);

        lv.setAdapter(adapter);

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                view.setSelected(true);
            }
        });
    }

    public void groupAdapter(View view){

        ListView lv = (ListView) findViewById(R.id.list_mainte);

        MaintenaceRowAdapter adapter;

        for(int i = 0; i < mainteList.size() ; i++){
            MaintenanceItem mItem = mainteList.get(i);
            if(!group.contains(mItem)) {
                group.add(mItem);
            }
        }

        String[] values = new String[group.size()];
        for(int i = 0; i < group.size() ; i++){values[i]="";}
        adapter = new MaintenaceRowAdapter(mContext, group, values);

        lv.setAdapter(adapter);

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                Toast.makeText(mContext, "you changed me " + position, Toast.LENGTH_SHORT).show();
                view.setSelected(true);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Utility.openNewActivity(id, this);
        return super.onOptionsItemSelected(item);
    }
}