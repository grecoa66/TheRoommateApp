package rommateapp.development.albie.therommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
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

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private UserList userList;
    private User currUser;
    private ChoreList choreList;
    private GroceryList groceryList;
    private  BillList billList;
    private MaintenanceList maintList;
    private Group group;
    private  ListView groceryLv;
    private  ListView choreLv;
    private  ListView billsLv;
    private  ListView maintLv;

    private  HTTP_Connector httpcon;
    private Context mContext;
    private AsyncResponse async;
    private AlertDialog alert;


    //Chores Things
    private Chore choreToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        async = this;

        //executing each get call, ideally this should all be done in one call so we can fill a group object

        httpcon = new HTTP_Connector(this);

        HTTP_Connector.getUser getUser = httpcon.new getUser(this);
       // getUser.execute(Settings.Secure.getString(mContext.getContentResolver(),
       //         Settings.Secure.ANDROID_ID));



        getUser.execute("3f4e05043d09a8c3");


        groceryLv = (ListView) findViewById(R.id.groceriesSnapShot);
        choreLv= (ListView) findViewById(R.id.choresSnapShot);
        billsLv= (ListView) findViewById(R.id.billsSnapShot);
        maintLv= (ListView) findViewById(R.id.maintenanceSnapShot);


        ArrayList<String> noVals = new ArrayList<>();
        noVals.add("");
        noDataAdapter adapter = new noDataAdapter(mContext, noVals);

        billsLv.setAdapter(adapter);
        maintLv.setAdapter(adapter);
        groceryLv.setAdapter(adapter);
    }



    public void processFinish(ArrayList<Chore> result){
        choreList = new ChoreList(4, result);
        ChoreRowAdapter adapter = new ChoreRowAdapter(mContext, choreList.getChores());
        choreLv.setAdapter(adapter);
        setListenerChores(choreLv);
    }
    public void processFinish(MaintenanceList result){
        maintList = result;
        MaintenaceRowAdapter adapter = new MaintenaceRowAdapter(mContext, maintList.getMaintenanceList());
        maintLv.setAdapter(adapter);
    }
    public void processFinish(GroceryList result){
        groceryList = result;
        GroceryRowAdapter adapter = new GroceryRowAdapter(mContext, groceryList.getGroceryList());
        groceryLv.setAdapter(adapter);
    }
    public void processFinish(User resp){
        currUser = resp;
        if(currUser != null){
            Toast.makeText(mContext, "Hello, "+ currUser.getfName()+". Retrieving roomie data...",Toast.LENGTH_SHORT).show();
            HTTP_Connector.getChoreList getChores = httpcon.new getChoreList(this);
            getChores.execute(String.valueOf(currUser.groupId));

            HTTP_Connector.getGroceryList getGrocer = httpcon.new getGroceryList(this);
            getGrocer.execute(String.valueOf(currUser.groupId));

            HTTP_Connector.getMaintenanceList getMaint = httpcon.new getMaintenanceList(this);
            getMaint.execute(String.valueOf(currUser.groupId));

            HTTP_Connector.getUserList getUserList = httpcon.new getUserList(this);
            getUserList.execute(String.valueOf(currUser.groupId));

            //still need Bills...

            //


        }

    }

    public void processFinish(UserList ul){
        userList = ul;
    }


    public void setListenerChores(ListView lv){

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                showModalChores(position);
                view.setSelected(true);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        group = new Group(currUser.groupId, userList, new BillList(4, new ArrayList<Bill>()), choreList, groceryList, maintList);//BILLLIST SHOULD BE PULLED FROM DB
        Utility.openNewActivity(id, this, group, currUser);
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 2952: {
                if (resultCode == RESULT_OK) {
                    String signedrequest = data.getStringExtra("signedrequest");
                    if (signedrequest != null) {
                        VenmoLibrary.VenmoResponse response = (new VenmoLibrary()).validateVenmoPaymentResponse(signedrequest, "n7XawqshzyCMUfbhfugjWZW4DGDYjGpE");
                        if (response.getSuccess().equals("1")) {
                            //Payment successful.  Use data from response object to display a success message
                            String note = response.getNote();
                            String amount = response.getAmount();
                            Toast toast = Toast.makeText(this, "payment successful! " + note + ": " + amount, Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    } else {
                        String error_message = data.getStringExtra("error_message");
                        Toast toast = Toast.makeText(this, error_message, Toast.LENGTH_SHORT);
                        toast.show();
                    }
                } else if (resultCode == RESULT_CANCELED) {
                    Toast toast = Toast.makeText(this, "you cancelled", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            }
        }
    }

    public void showModalChores(final int position){

        Chore c = choreList.getChores().get(position);
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.chore_edit, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText name = (EditText) promptsView
                .findViewById(R.id.choreName);
        final EditText desc = (EditText) promptsView
                .findViewById(R.id.choreDesc);
        final Spinner spinner = (Spinner) promptsView
                .findViewById(R.id.PeopleSpinner);

        name.setText(c.getTitle());

        choreToDelete = choreList.getChores().get(position);
        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter(); //cast to an ArrayAdapter
        int spinnerPosition = myAdap.getPosition(c.getAssignedUser());
        spinner.setSelection(spinnerPosition);
        desc.setText(c.getDesc());
        //choreToDelete = c;
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                                Chore c = choreList.getChores().get(position);
                                c.setDesc(desc.getText().toString());
                                c.setTitle(name.getText().toString());
                                c.setAssignedUser(spinner.getSelectedItem().toString());
                                ChoreRowAdapter adapter = new ChoreRowAdapter(mContext, choreList.getChores());


                                HTTP_Connector.editChore editChore = httpcon.new editChore();
                                editChore.execute(c);

                                choreLv.setAdapter(adapter);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        alert = alertDialogBuilder.create();

        // show it
        alert.show();
    }
        public void deleteChore(View view) {

        choreList.getChores().remove(choreToDelete);
        ChoreRowAdapter adapter = new ChoreRowAdapter(mContext, choreList.getChores());
        choreLv.setAdapter(adapter);
        Toast.makeText(mContext, "Chore deleted", Toast.LENGTH_SHORT).show();
        alert.cancel();

        HTTP_Connector.deleteChore dbDeleteChore = httpcon.new deleteChore();
        dbDeleteChore.execute(choreToDelete.getChoreId());
    }
    public void addAnnouncement(View view) {

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.announcement_add, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);



        final TextView newMessage = (TextView) promptsView.findViewById(R.id.newAnnouncement);
        final TextView tv = (TextView) findViewById(R.id.AnnouncementsMessage);
        final TextView tvUser = (TextView) findViewById(R.id.AnnouncementsUser);


        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                //result.setText(userInput.getText());
                        //HTTP_Connector.editChore editChore = httpcon.new editChore();
                              //  editChore.execute(c);


                                tv.setText(newMessage.getText().toString());
                                tvUser.setText("-Albie");


                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        alert = alertDialogBuilder.create();

        // show it
        alert.show();
    }


}

