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
public class MaintenanceActivity extends AppCompatActivity implements AsyncResponse {

    private Context mContext;

    private ArrayList<MaintenanceItem> mainteList = new ArrayList<MaintenanceItem>();
    private ArrayList<MaintenanceItem> justMe = new ArrayList<>();
    private ArrayList<MaintenanceItem> group = new ArrayList<>();
    private ListView lv;
    private AlertDialog alertDialog;
    private MaintenanceItem itemToDelete;
    private MaintenaceRowAdapter adapter;
    private Group myGroup;
    private User currUser;
    private HTTP_Connector httpcon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintenance_main);
        mContext = this;


        myGroup= (Group) getIntent().getSerializableExtra("group");
        currUser = (User) getIntent().getSerializableExtra("user");
        httpcon = new HTTP_Connector(this);

        lv = (ListView) findViewById(R.id.list_mainte);

        if (myGroup == null) {
            mainteList = new ArrayList<>();
            //pull from db here
        } else {

            mainteList = myGroup.getMaintenanceList().getMaintenanceList();
            adapter = new MaintenaceRowAdapter(mContext, mainteList);
            lv.setAdapter(adapter);
            setListener(lv);
        }

        meAdapter(lv);


    }//end on create

    //this gets the groc list from the http connector
    public void processFinish(GroceryList result) {

    }

    public void processFinish(MaintenanceList result) {
        mainteList = result.getMaintenanceList();
        adapter = new MaintenaceRowAdapter(mContext, mainteList);
        lv.setAdapter(adapter);
        setListener(lv);
    }

    public void processFinish(ChoreList result) {

    }

    public void processFinish(User resp) {

    }

    public void processFinish(BillList bl) {
    }

    public void processFinish(UserList ul) {

    }
    public void processFinish(Announcement an){

    }
    public void setListener(ListView lv) {

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                showModal(position, justMe);
                view.setSelected(true);
            }
        });

    }

    public void meAdapter(View view) {
        mainteList = cleanMaintList();

        MaintenaceRowAdapter adapter;
        //start with a fresh copy
        //solves bug of removing someone
        justMe = new ArrayList<MaintenanceItem>();

        for (int i = 0; i < mainteList.size(); i++) {
            MaintenanceItem mItem = mainteList.get(i);
            if (mItem.getCausingUser().equalsIgnoreCase(currUser.getfName()) && !justMe.contains(mItem) && !mItem.isComplete()) {
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
                showModal(position, justMe);
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
                showModal(position, group);
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

                                MaintenanceItem newItem = new MaintenanceItem(
                                        myGroup.getGroupId(),
                                        editDesc.getText().toString(),
                                        spinner.getSelectedItem().toString(),
                                        spinner2.getSelectedItem().toString(),
                                        false);
                                mainteList.add(newItem);
                                ListView lv = (ListView) findViewById(R.id.list_mainte);
                                //create new adapter to with updated local mainteList
                                MaintenaceRowAdapter adapter = new MaintenaceRowAdapter(mContext, mainteList);
                                lv.setAdapter(adapter);
                                //go back to the personal view
                                meAdapter(lv);

                                //add item to db
                                HTTP_Connector.addMaintenanceItem dbAddMaintenance = httpcon.new addMaintenanceItem();
                                dbAddMaintenance.execute(newItem);
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

    public void showModal(final int pos, ArrayList<MaintenanceItem> mList) {

        //mainteList = cleanMaintList();

        //get current Mainte item you want to edit
        MaintenanceItem mItem = mList.get(pos);
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

                                //add item to db
                                HTTP_Connector.editMaintenanceItem dbEditMaintenance = httpcon.new editMaintenanceItem();
                                dbEditMaintenance.execute(thisItem);

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

        HTTP_Connector.deleteMaintenanceItem dbDeleteMaintItem = httpcon.new deleteMaintenanceItem();
        dbDeleteMaintItem.execute(itemToDelete.getMaintenanceItemId());

        meAdapter(lv);
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
        Utility.openNewActivity(id, this, myGroup, currUser);
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

    public void finishItem(MaintenanceItem mItem){
        mItem.setIsComplete(true);
        HTTP_Connector.editMaintenanceItem dbEditMaintenance = httpcon.new editMaintenanceItem();
        dbEditMaintenance.execute(mItem);

    }
}