package rommateapp.development.albie.therommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Albert on 10/21/2015.
 */

public class ChoreActivity extends AppCompatActivity implements AsyncResponse {

    private Context mContext;
    private ArrayList<Chore> currentChores;
    private ChoreRowAdapter adapter;
    private ListView list;
    private Chore choreToDelete;
    private AlertDialog alertDialog;
    private  HTTP_Connector httpcon;
    private Group myGroup;
    private User currUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chores_main);
        mContext = this;

        list = (ListView) findViewById(R.id.list_chores);


        myGroup= (Group) getIntent().getSerializableExtra("group");
        currUser = (User) getIntent().getSerializableExtra("user");
        httpcon = new HTTP_Connector(this);

        if(myGroup ==null) {
            currentChores = new ArrayList<>();
            //pull from db here
        }
      else{

            currentChores = myGroup.getChoreList().getChores();
            adapter = new ChoreRowAdapter(mContext, currentChores);
            list.setAdapter(adapter);
            setListener(list);
        }
        setMyList(list);
    }
    //}

    public void processFinish(ChoreList result){

        currentChores = result.getChores();
        adapter = new ChoreRowAdapter(mContext, currentChores);
        list.setAdapter(adapter);
        setListener(list);
    }
    public void processFinish(MaintenanceList result){

    }
    public void processFinish(GroceryList result){

    } public void processFinish(Announcement an){

    }
    public void processFinish(User resp){

    }

    public void processFinish(UserList ul){

    }public void processFinish(BillList bl){
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.chores_icon).setChecked(true);
        return true;
    }

    public void setMyList(View view){
        ArrayList<Chore> myList = new ArrayList<>();

        for(int i=0; i< currentChores.size();i++){
            if( currentChores.get(i).getAssignedUser().equals(currUser.getfName())){
                myList.add(currentChores.get(i));
            }
        }
        adapter = new ChoreRowAdapter(mContext, myList);
        list.setAdapter(adapter);
        setListener(list);
    }
    public void setAllList(View view){
        adapter = new ChoreRowAdapter(mContext, currentChores);
        list.setAdapter(adapter);
        setListener(list);
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


    public void addChore(View view) {

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.chore_add, null);

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

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                                Chore c = new Chore(name.getText().toString(), desc.getText().toString(), currUser.getfName(), spinner.getSelectedItem().toString(),true, currUser.getGroupId());
                                currentChores.add(c);

                                adapter = new ChoreRowAdapter(mContext, currentChores);
                                list.setAdapter(adapter);
                                setListener(list);
                                Toast.makeText(mContext, name.getText().toString()+", "+desc.getText().toString()+", "+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                                HTTP_Connector.addChore dbAddChore = httpcon.new addChore();
                                dbAddChore.execute(c);
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

    public void showModal(final int position){

        Chore c = currentChores.get(position);
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


        //set adapter --
        ArrayList<User> ul = myGroup.getUserList().getUserList();
        String[] names = new String[ul.size()];
        for(int i=0; i<ul.size();i++){
            names[i] = ul.get(i).getfName();
        }
        ArrayAdapter<String> namesAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, names);
        namesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(namesAdapter);
        name.setText(c.getTitle());

        int spinnerPosition = namesAdapter.getPosition(c.getAssignedUser());
        spinner.setSelection(spinnerPosition);
        desc.setText(c.getDesc());
        choreToDelete = c;
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                                Chore c = currentChores.get(position);
                                c.setDesc(desc.getText().toString());
                                c.setTitle(name.getText().toString());
                                c.setAssignedUser(spinner.getSelectedItem().toString());
                                adapter = new ChoreRowAdapter(mContext, currentChores);
                                list.setAdapter(adapter);
                                HTTP_Connector.editChore dbEditChore = httpcon.new editChore();
                                dbEditChore.execute(c);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        alertDialog= alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void setListener(ListView lv){

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                showModal(position);
                view.setSelected(true);
            }
        });

    }
    public void deleteChore(View view) {

        currentChores.remove(choreToDelete);
        adapter = new ChoreRowAdapter(mContext, currentChores);
        list.setAdapter(adapter);
        Toast.makeText(mContext, "Chore deleted", Toast.LENGTH_SHORT).show();
        alertDialog.cancel();

        HTTP_Connector.deleteChore dbDeleteChore = httpcon.new deleteChore();
        dbDeleteChore.execute(choreToDelete.getChoreId());
    }
}

