package rommateapp.development.albie.therommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Albert on 10/21/2015.
 */
public class BillsActivity extends AppCompatActivity {

    private Context mContext;
    private ArrayList<Bill> allBills;
    private ArrayList<Bill> currentBills;
    private BillRowAdapter adapter;
    private ListView list;
    private Bill billToDelete;
    private AlertDialog alertDialog;
    private ArrayList<User> users;
    private Group currGroup;
    private User currUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bills_main);
        mContext = this;

        allBills = new ArrayList<>();
        User albie = new User(0, "Albie","rynkie", "rynk@a.com","842523942", 1);
        User greco = new User(0, "Greco","Alex", "rynk@a.com","842523942", 1);
        User matt = new User(0, "Matt","cieslak", "rynk@a.com","842523942", 1);
        users = new ArrayList<>();
        users.add(albie);
        users.add(greco);
        users.add(matt);
        allBills.add(new Bill("water", 40, albie, greco,0 ));
        allBills.add(new Bill("gas",  35, matt, greco,0 ));
        allBills.add(new Bill("rent", 500, matt, albie,0 ));
        allBills.add(new Bill("dinner", 20, greco, matt,0 ));

        currGroup= (Group) getIntent().getSerializableExtra("group");
        currUser = (User) getIntent().getSerializableExtra("user");

        list = (ListView) findViewById(R.id.list_bills);


     /*   currentChores = new ArrayList<>();
        for(int i=0;i<allChores.size();i++){
            Chore c = allChores.get(i);
            if(c.getAssignedUser().equals("albie")){
                currentChores.add(c);
            }

        }
*/
        currentBills = allBills;
        adapter = new BillRowAdapter(mContext, allBills);

        list.setAdapter(adapter);
        setListener(list);

        setMyList(list);


    }


    public void setMyList(View view){
        if(currentBills==null){
            return;
        }
        ArrayList<Bill> myList = new ArrayList<>();
        for(int i=0; i< currentBills.size();i++){
            if( currentBills.get(i).getUserToBill().getfName().equals("Albie")){
                myList.add(currentBills.get(i));
            }
        }
        adapter = new BillRowAdapter(mContext, myList);
        list.setAdapter(adapter);
        setListener(list);
    }
    public void setAllList(View view){
        if(currentBills==null){
            return;
        }
        adapter = new BillRowAdapter(mContext, currentBills);
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
        Utility.openNewActivity(id, this, currGroup, currUser);
        return super.onOptionsItemSelected(item);
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


    public void showModal(final int position){

        Bill b = currentBills.get(position);
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.bill_edit, null);

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

        name.setText(b.getDesc());
        desc.setText(b.getDesc());
        billToDelete = b;
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
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

    public void openPaymentDialog(final Bill b){

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.bill_pay, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        Button venmoButton = (Button) promptsView.findViewById(R.id.venmoButton);
        venmoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doVenmo(b);
            }
        });


        alertDialogBuilder.setView(promptsView);
        billToDelete = b;
        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
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

    public void doVenmo(Bill b){
        if (VenmoLibrary.isVenmoInstalled(mContext)) {
            Intent venmoIntent = VenmoLibrary.openVenmoPayment("2952", b.getDesc(), b.getUserToPay().getPhoneNumber(), ".01", b.getDesc()+" --Thanks Roomie!", "charge");
            startActivityForResult(venmoIntent, 2952);

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
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

    public void deleteBill(View view) {

        currentBills.remove(billToDelete);
        adapter = new BillRowAdapter(mContext, currentBills);
        list.setAdapter(adapter);
        Toast.makeText(mContext, "Bill Dismissed", Toast.LENGTH_SHORT).show();
        alertDialog.cancel();
    }


    public void addBill(View view) {

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.bill_add, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        String[] roomies = new String[users.size()];
        final ArrayList<Integer> seletedItems=new ArrayList();

        for(int i  =0; i<users.size();i++){
            roomies[i]=users.get(i).getfName();
        }


        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText name = (EditText) promptsView
                .findViewById(R.id.billName);
        final EditText amount = (EditText) promptsView
                .findViewById(R.id.totalAmount);


       // name.setText(b.getDesc());
        alertDialogBuilder.setMultiChoiceItems(roomies, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int indexSelected,
                                        boolean isChecked) {
      //  desc.setText(b.getDesc());
       // billToDelete = b;
        // set dialog message
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            seletedItems.add(indexSelected);
                        } else if (seletedItems.contains(indexSelected)) {
                            // Else, if the item is already in the array, remove it
                            seletedItems.remove(Integer.valueOf(indexSelected));
                        }
                    }
                })
                .setTitle("Roomies to Bill")
                .setCancelable(false)
                .setPositiveButton("Add",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       Toast.makeText(mContext, seletedItems.toString(), Toast.LENGTH_SHORT).show();
                        // for each roomie in selectedItems, we need to create a bill
                        for(int i=0;i<seletedItems.size();i++){
                            Bill b = new Bill(name.getText().toString(), Double.valueOf(amount.getText().toString())/seletedItems.size(),
                                    users.get(seletedItems.get(i)), users.get(0), 0);
                            allBills.add(b);
                        }
                        adapter = new BillRowAdapter(mContext, allBills);
                        list.setAdapter(adapter);
                        setListener(list);

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
}
