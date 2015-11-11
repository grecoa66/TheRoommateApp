package rommateapp.development.albie.therommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HouseActivity extends AppCompatActivity implements AsyncResponse{

    private Context mContext;
    private User currUser;
    private Group currGroup;

    private TextView firstName;
    private TextView lastName;
    private TextView userPhone;
    private TextView userEmail;
    private TextView groupAddr;
    private TextView groupName;
    private ArrayList<Points> currentPoints = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_main);
        mContext = this;


        currGroup= (Group) getIntent().getSerializableExtra("group");
        currUser = (User) getIntent().getSerializableExtra("user");

        currGroup.setAddr("201 Mullica Hill Road");
        currGroup.setName("The Roomie\'s House");

        groupAddr = (TextView) findViewById(R.id.groupAddress);
        groupName = (TextView) findViewById(R.id.groupName);
        groupAddr.setText("Group NickName: " + currGroup.getName());
        groupName.setText("Address: " + currGroup.getAddr());


        firstName = (TextView) findViewById(R.id.userFirstName);
        lastName = (TextView) findViewById(R.id.userLastName);
        userPhone = (TextView) findViewById(R.id.userPhone);
        userEmail = (TextView) findViewById(R.id.userEmail);

        firstName.setText("First Name: " + currUser.getfName());
        lastName.setText("Last Name: "+ currUser.getlName());
        userPhone.setText("Phone: "+ currUser.getPhoneNumber());
        userEmail.setText("email: " + currUser.getEmailAddress());

        currentPoints.add(new Points("Albie", 25));
        currentPoints.add(new Points("Greco", 15));
        currentPoints.add(new Points("Matt", 10));


        ListView list = (ListView) findViewById(R.id.list_points);


        PointsRowAdapter adapter = new PointsRowAdapter(mContext, currentPoints);
        list.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Utility.openNewActivity(id, this, currGroup, currUser);
        return super.onOptionsItemSelected(item);
    }


    public void addUser(View view){

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.user_add, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText fName = (EditText) promptsView
                .findViewById(R.id.firstName);
        final EditText lName = (EditText) promptsView
                .findViewById(R.id.lastName);
        final EditText email = (EditText) promptsView
                .findViewById(R.id.email);
        final EditText phoneNum= (EditText) promptsView
                .findViewById(R.id.phoneNum);

        //choreToDelete = c;
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setTitle("Add User to Group")
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // HTTP_Connector.editChore editChore = httpcon.new editChore();
                                // editChore.execute(c);
                                final User u = new User(fName.getText().toString(),lName.getText().toString(), email.getText().toString(), phoneNum.getText().toString(), currUser.groupId );

                                Toast.makeText(mContext, u.getfName() + u.getlName()+u.getEmailAddress()+u.getPhoneNumber()+u.groupId, Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alert = alertDialogBuilder.create();

        // show it
        alert.show();
    }

    public void removeUser(View view){
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.user_remove, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final Spinner spinner = (Spinner) promptsView
                .findViewById(R.id.PeopleSpinner);


        //choreToDelete = c;
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setTitle("Delete User from Group")
                .setPositiveButton("Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // HTTP_Connector.editChore editChore = httpcon.new editChore();
                                // editChore.execute(c);

                                Toast.makeText(mContext, spinner.getSelectedItem().toString()+ " deleted", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alert = alertDialogBuilder.create();

        // show it
        alert.show();
    }

    public void editUser(View view){
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.user_add, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText fName = (EditText) promptsView
                .findViewById(R.id.firstName);
        final EditText lName = (EditText) promptsView
                .findViewById(R.id.lastName);
        final EditText email = (EditText) promptsView
                .findViewById(R.id.email);
        final EditText phoneNum= (EditText) promptsView
                .findViewById(R.id.phoneNum);

        fName.setText(firstName.getText().toString().substring(firstName.getText().toString().lastIndexOf(" ")));
        lName.setText(lastName.getText().toString().substring(lastName.getText().toString().lastIndexOf(" ")));
        email.setText(userEmail.getText().toString().substring(userEmail.getText().toString().indexOf(" ")));
        phoneNum.setText(userPhone.getText().toString().substring(userPhone.getText().toString().indexOf(" ")));
        //choreToDelete = c;
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setTitle("Edit Profile")
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // HTTP_Connector.editChore editChore = httpcon.new editChore();
                                // editChore.execute(c);
                                final User u = new User(fName.getText().toString(),lName.getText().toString(), email.getText().toString(), phoneNum.getText().toString(), currUser.groupId );

                                Toast.makeText(mContext, u.getfName() + u.getlName()+u.getEmailAddress()+u.getPhoneNumber()+u.groupId, Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alert = alertDialogBuilder.create();

        // show it
        alert.show();
    }


    public void editGroup(View view){
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.group_edit, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText address = (EditText) promptsView
                .findViewById(R.id.Address);
        final EditText nickname = (EditText) promptsView
                .findViewById(R.id.NickName);

        address.setText(groupAddr.getText().toString().substring(groupAddr.getText().toString().lastIndexOf(":")+1));
        nickname.setText(groupName.getText().toString().substring(groupName.getText().toString().lastIndexOf(":") + 1));
        //choreToDelete = c;
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setTitle("Edit Profile")
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // HTTP_Connector.editChore editChore = httpcon.new editChore();
                                // editChore.execute(c);
                                final Group g = new Group(address.getText().toString(), nickname.getText().toString() );

                                Toast.makeText(mContext, g.getAddr() + g.getName(), Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alert = alertDialogBuilder.create();

        // show it
        alert.show();
    }

    public void processFinish(ChoreList result){
    }
    public void processFinish(User user){
    }

    public void processFinish(MaintenanceList result){

    }
    public void processFinish(GroceryList result){

    }
    public void processFinish(UserList ul){

    }
    public void processFinish(BillList bl){
    }
    public void processFinish(Announcement an){

    }
    public void userListFinish(ArrayList<User> output){

    }
    public void groupFinish(Group output){

    }

}

