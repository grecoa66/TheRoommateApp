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
import android.widget.ArrayAdapter;
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
    private ListView lv;
    private AlertDialog alertDialog;
    private MaintenanceItem itemToDelete;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_main);
        mContext = this;

        mainteList.add(new MaintenanceItem("Broken Door", "Albie", "Albie", false));
        mainteList.add(new MaintenanceItem("scuffed paint in living room", "Greco", "matt", false));
        mainteList.add(new MaintenanceItem("leak in sink", "matt", "greco", false));
        mainteList.add(new MaintenanceItem("Burnt out bulb", "greco", "greco", false));
        mainteList.add(new MaintenanceItem("Broken Window", "matt", "matt", false));
        mainteList.add(new MaintenanceItem("Broken toilet seat", "ablie", "greco", false));
        mainteList.add(new MaintenanceItem("New spare Key", "matt", "matt", false));


        lv = (ListView) findViewById(R.id.list_mainte);
        meAdapter(lv);


    }//end on create

    public void meAdapter(View view) {
        mainteList = cleanMaintList();

        MaintenaceRowAdapter adapter;
        //start with a fresh copy
        //solves bug of removing someone
        justMe = new ArrayList<MaintenanceItem>();

        for (int i = 0; i < mainteList.size(); i++) {
            MaintenanceItem mItem = mainteList.get(i);
            if (mItem.getCausingUser().equalsIgnoreCase("Albie") && !justMe.contains(mItem) && !mItem.isComplete()) {
                justMe.add(mItem);
            }
        }

        adapter = new MaintenaceRowAdapter(mContext, justMe);

        lv.setAdapter(adapter);

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                //Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                showModal(position);
                view.setSelected(true);
            }
        });
    }

    public void groupAdapter(View view) {
        mainteList = cleanMaintList();

        MaintenaceRowAdapter adapter;

        //start with a fresh copy
        group = new ArrayList<MaintenanceItem>();

        for (int i = 0; i < mainteList.size(); i++) {
            MaintenanceItem mItem = mainteList.get(i);
            if (!group.contains(mItem) && !mItem.isComplete()) {
                group.add(mItem);
            }
        }

        adapter = new MaintenaceRowAdapter(mContext, group);

        lv.setAdapter(adapter);

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                Toast.makeText(mContext, "you changed me " + position, Toast.LENGTH_SHORT).show();
                showModal(position);
                view.setSelected(true);
            }
        });

    }


    public void addItem(View view) {

        mainteList = cleanMaintList();

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
                                mainteList.add(new MaintenanceItem(
                                        editDesc.getText().toString(),
                                        spinner.getSelectedItem().toString(),
                                        spinner2.getSelectedItem().toString(),
                                        false));
                                ListView lv = (ListView) findViewById(R.id.list_mainte);
                                MaintenaceRowAdapter adapter = new MaintenaceRowAdapter(mContext, mainteList);
                                lv.setAdapter(adapter);
                                //setListener(list);
                                meAdapter(lv);
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

    public void showModal(final int pos) {

        mainteList = cleanMaintList();

        //get current Mainte item you want to edit
        MaintenanceItem mItem = mainteList.get(pos);
        LayoutInflater li = LayoutInflater.from(mContext);
        //inflate the mainte edit layout
        View promptsView = li.inflate(R.layout.maintenace_edit, null);
        //make the mainte edit a alert box
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText editDesc = (EditText) promptsView.findViewById(R.id.mainteDesc);
        final Spinner spinner = (Spinner) promptsView.findViewById(R.id.PeopleSpinner);
        final Spinner spinner2 = (Spinner) promptsView.findViewById(R.id.PeopleSpinnerTwo);

        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(mItem.getCausingUser());
        spinner.setSelection(spinnerPosition);
        ArrayAdapter myAdap2 = (ArrayAdapter) spinner2.getAdapter();
        int spinnerPosition2 = myAdap2.getPosition(mItem.getPurchaseUser());
        spinner2.setSelection(spinnerPosition2);
        editDesc.setText(mItem.getDesc());
        itemToDelete = mItem;

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //get the Mainte item and set all its attributes to new ones
                                MaintenanceItem thisItem = mainteList.get(pos);
                                thisItem.setDesc(editDesc.getText().toString());
                                thisItem.setCausingUser(spinner.getSelectedItem().toString());
                                thisItem.setPurchaseUser(spinner2.getSelectedItem().toString());
                                //get the mainte list
                                ListView lv = (ListView) findViewById(R.id.list_mainte);
                                //create new adapter
                                MaintenaceRowAdapter adapter = new MaintenaceRowAdapter(mContext, mainteList);
                                //set an adapter to the new listview
                                lv.setAdapter(adapter);
                                meAdapter(lv);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

    }

    public void removeMainteItem(View view) {
        //remove item
        mainteList.remove(itemToDelete);
        //update adapter
        MaintenaceRowAdapter adapter = new MaintenaceRowAdapter(mContext, mainteList);
        //set new adapter
        lv.setAdapter(adapter);
        Toast.makeText(mContext, "Maintenance Item Deleted", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);
        //close dialog box
        alertDialog.cancel();
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

    /*
    this is to solve a bug where when mItems were
    complete, they still appeared on the current lists.
    This just transfers finshed tasks to a new arraylist
     */
    public ArrayList<MaintenanceItem> cleanMaintList() {
        for (int i = 0; i < mainteList.size(); i++) {
            MaintenanceItem mItem = mainteList.get(i);
            if (mItem.isComplete()) {
                mainteList.remove(mItem);
            }
        }
        return mainteList;
    }
}