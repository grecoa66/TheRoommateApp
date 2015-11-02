package rommateapp.development.albie.therommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Alex G on 10/21/2015.
 */
public class MaintenanceActivity extends AppCompatActivity {

    private Context mContext;

    private ArrayList<MaintenanceItem> mainteList = new ArrayList<MaintenanceItem>();
    private ArrayList<MaintenanceItem> justMe = new ArrayList<>();
    private ArrayList<MaintenanceItem> group = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_main);
        mContext = this;

        mainteList.add(new MaintenanceItem( "Broken Door", "albie", "albie", false));
        mainteList.add(new MaintenanceItem( "scuffed paint in living room", "greco", "matt", false));
        mainteList.add(new MaintenanceItem( "leak in sink", "matt", "greco", false));
        mainteList.add(new MaintenanceItem( "Burnt out bulb", "greco", "greco", false));
        mainteList.add(new MaintenanceItem( "Broken Window", "matt", "matt", false));
        mainteList.add(new MaintenanceItem( "Broken toilet seat", "ablie", "greco", false));
        mainteList.add(new MaintenanceItem( "New spare Key", "matt", "matt", false));


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

    public void addItem(View view) {

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.maintenance_add, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText editDesc = (EditText) promptsView.findViewById(R.id.mainteDesc);
        final Spinner spinner = (Spinner) promptsView.findViewById(R.id.PeopleSpinner);
        final Spinner spinner2 = (Spinner) promptsView.findViewById(R.id.PeopleSpinnerTwo);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            group.add(new MaintenanceItem(
                                editDesc.getText().toString(),
                                spinner.getSelectedItem().toString(),
                                spinner2.getSelectedItem().toString(),
                                false));
                            ListView lv = (ListView) findViewById(R.id.list_mainte);
                            String[] values = new String[group.size()];
                            for (int i = 0; i < group.size(); i++) {
                                values[i] = "";
                            }
                            MaintenaceRowAdapter adapter = new MaintenaceRowAdapter(mContext, group, values);
                            lv.setAdapter(adapter);
                        //setListener(list);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void removeMainteItem(View view){
        
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