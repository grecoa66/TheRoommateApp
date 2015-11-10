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
import android.widget.Toast;

import java.util.Date;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Albert on 10/21/2015.
 */
public class GroceryActivity extends AppCompatActivity implements AsyncResponse{

    private Context mContext;
    private ArrayList<Grocery> allGroc = new ArrayList<>();
    private ArrayList<Grocery> currentGroc = new ArrayList<>();
    private ArrayList<Grocery> grocToPurchase = new ArrayList<>();
    private ListView lv;
    private GroceryRowAdapter adapter;
    private Grocery grocToDelete;
    private AlertDialog alertDialog;
    private Date date;
    private User currUser;
    private Group myGroup;
    private HTTP_Connector httpcon;


    private Group group;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_main);

        mContext = this;
        date = new Date(System.currentTimeMillis());

        myGroup= (Group) getIntent().getSerializableExtra("group");
        currUser = (User) getIntent().getSerializableExtra("user");
        httpcon = new HTTP_Connector(this);

        lv = (ListView) findViewById(R.id.list_grocery);

        if(myGroup ==null) {
            currentGroc = new ArrayList<>();
            //pull from db here
        }
        else{

            currentGroc = myGroup.getGroceryList().getGroceryList();
            adapter = new GroceryRowAdapter(mContext, currentGroc);
            lv.setAdapter(adapter);
            setListener(lv);
        }

        currentAdapter(lv);
    }

    //this gets the groc list from the http connector
    public void processFinish(GroceryList result){

        currentGroc = result.getGroceryList();
        adapter = new GroceryRowAdapter(mContext, currentGroc);
        lv.setAdapter(adapter);
        setListener(lv);
    }
    public void processFinish(MaintenanceList result){

    }
    public void processFinish(ArrayList<Chore> result){

    }
    public void processFinish(User resp){

    }
    public void processFinish(BillList bl){
    }
    public void processFinish(UserList ul){

    }

    //This is the adapter that gets shown
    //this activity will only have one adapter because
    //the whole house shares the entire grocery list
    public void currentAdapter(View view) {

        currentGroc = buildCurrentList(currentGroc);
        adapter = new GroceryRowAdapter(mContext, currentGroc);
        lv.setAdapter(adapter);
        //This is where we can create the modal for edit  delete
       setListener(lv);
    }

    public void addGrocery(View view) {
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.grocery_add, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText grocName = (EditText) promptsView.findViewById(R.id.grocNameAdd);
        final EditText grocQuant = (EditText) promptsView.findViewById(R.id.grocQuantAdd);
        final Date currentDate = new Date(System.currentTimeMillis());


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Grocery g = new Grocery(grocName.getText().toString(),
                                        Integer.parseInt(grocQuant.getText().toString()),
                                        currentDate.toString(),
                                        "Albie", false);
                                currentGroc.add(g);
                                ListView lv = (ListView) findViewById(R.id.list_grocery);
                                GroceryRowAdapter adapter = new GroceryRowAdapter(mContext, currentGroc);
                                lv.setAdapter(adapter);
                                //setListener(list);
                                currentAdapter(lv);

                                HTTP_Connector.addGrocery dbAddGrocery = httpcon.new addGrocery();
                                dbAddGrocery.execute(g);
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

    public void showModal(final int pos) {
        //get the grocery that was pressed
        final Grocery grocery = currentGroc.get(pos);

        LayoutInflater li = LayoutInflater.from(mContext);

        View promptsView = li.inflate(R.layout.grocery_edit, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

        alertDialogBuilder.setView(promptsView);

        final EditText editGrocName = (EditText) promptsView.findViewById(R.id.grocNameEdit);
        final EditText editGrocQuant = (EditText) promptsView.findViewById(R.id.grocQuantEdit);
        final Spinner spinner = (Spinner) promptsView.findViewById(R.id.PeopleSpinnerGrocery);

        editGrocName.setText(grocery.getItemName());
        editGrocQuant.setText("" + grocery.getQuantity());
        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter(); //cast to an ArrayAdapter
        final int spinnerPosition = myAdap.getPosition(grocery.getRequestUser());
        spinner.setSelection(spinnerPosition);
        grocToDelete = grocery;

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                grocery.getDateRequested();
                                grocery.setRequestUser(spinner.getSelectedItem().toString());
                                grocery.setItemName(editGrocName.getText().toString());
                                grocery.setQuantity(Integer.parseInt(editGrocQuant.getText().toString()));
                                ListView lv = (ListView) findViewById(R.id.list_grocery);
                                GroceryRowAdapter adapter = new GroceryRowAdapter(mContext, currentGroc);
                                lv.setAdapter(adapter);
                                currentAdapter(lv);
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

    public void removeGrocery(View view){
        currentGroc.remove(grocToDelete);
        currentGroc = buildCurrentList(currentGroc);
        adapter = new GroceryRowAdapter(mContext, currentGroc);
        lv.setAdapter(adapter);

        Toast.makeText(mContext, "Grocery Deleted", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);
        //close dialog box
        alertDialog.cancel();

        //delete in db
        HTTP_Connector.deleteGrocery dbDeleteGroc = httpcon.new deleteGrocery();
        dbDeleteGroc.execute(grocToDelete.getGroceryId());
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

    //fixes bug where  adapter was being sent bad data
    public ArrayList<Grocery> buildCurrentList(ArrayList<Grocery> allGroc){
        currentGroc = new ArrayList<Grocery>();

        for (int i = 0; i < allGroc.size(); i++) {
            Grocery grocery = allGroc.get(i);
            if (!currentGroc.contains(grocery) && !grocery.isPurchased()) {
                currentGroc.add(grocery);
            }
        }
        return currentGroc;
    }

    public void addGrocToPurchase(Grocery g){
        grocToPurchase.add(g);
        //String test = grocToPurchase.get(0).toString();
        //Toast.makeText(mContext, test, Toast.LENGTH_SHORT).show();
    }

    //create a modal to ask for price
    //after that go through the grocToPurchase array
    //and set all of their setPurchased to true
    public void checkOut(View view){

        //get the grocery that was pressed
        LayoutInflater li = LayoutInflater.from(mContext);

        View promptsView = li.inflate(R.layout.grocery_checkout, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

        alertDialogBuilder.setView(promptsView);

        final EditText enterAmount = (EditText) promptsView.findViewById(R.id.enter_amount);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (!grocToPurchase.isEmpty()) {
                                    double totalAmount = Double.parseDouble(enterAmount.getText().toString());

                                    UserList uList = group.getUserList();
                                    ArrayList arrL = uList.getUserList();
                                    int userCount = arrL.size();

                                    double amountPerPerson = (totalAmount / userCount);

                                    BillList bl = group.getBillList();
                                    ArrayList bArr = bl.getBillList();

                                    //bill all users in group evenly
                                    for (int i = 0; i < userCount; i++) {
                                        User u = (User) arrL.get(i);
                                        //TODO add the current user as the userToBill
                                        Bill newBill = new Bill("grocery check out", amountPerPerson, u.getfName(), u.getfName(), 0);
                                        bl.addBill(newBill);
                                    }

                                    //set is purchased to true for all things just purchased
                                    for (int j = 0; j < grocToPurchase.size(); j++) {
                                        Grocery g = grocToPurchase.get(j);
                                        g.setIsPurchased(true);
                                    }

                                    ListView lv = (ListView) findViewById(R.id.list_grocery);
                                    GroceryRowAdapter adapter = new GroceryRowAdapter(mContext, currentGroc);
                                    lv.setAdapter(adapter);

                                    String billTest = bArr.toString();

                                    Toast.makeText(mContext, billTest, Toast.LENGTH_SHORT).show();

                                    //load a new current view to reflect changes
                                    currentAdapter(lv);
                                } else {
                                    Toast.makeText(GroceryActivity.this, "You have purchased anything", Toast.LENGTH_SHORT).show();
                                }
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
    }//end checkout
}//end class
