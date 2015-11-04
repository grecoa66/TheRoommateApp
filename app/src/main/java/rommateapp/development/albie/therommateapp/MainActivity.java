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


public class MainActivity extends AppCompatActivity implements AsyncResponse {

    private ArrayList<Chore> chores;
    private ArrayList<User> userList;
    private ArrayList<Chore> choreList;
    private ArrayList<Grocery> groceryList;
    private ArrayList<Bill> billList;
    private ArrayList<MaintenanceItem> maintList;
    private  ListView groceryLv;
    private  ListView choreLv;
    private  ListView billsLv;
    private  ListView maintLv;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        HTTP_Connector httpcon= new HTTP_Connector(this);
        HTTP_Connector.getChoreList getchores = httpcon.new getChoreList(this);

        getchores.execute("1");

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

    public void processFinish(ArrayList<Chore> response){


        choreList = response;
        ChoreRowAdapter adapter = new ChoreRowAdapter(this, choreList);
        choreLv.setAdapter(adapter);
        setListener(choreLv);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Utility.openNewActivity(id, this, choreList);
        return super.onOptionsItemSelected(item);
    }


    public void testVenmo(View view) {
        if (VenmoLibrary.isVenmoInstalled(this)) {
            Intent venmoIntent = VenmoLibrary.openVenmoPayment("2952", "Roommate App Test", "7327889740", ".01", "test", "charge");
            startActivityForResult(venmoIntent, 2952);

        }
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

    public void showModal(final int position){

        Chore c = choreList.get(position);
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

        name.setText(c.getTitle());
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
                                Chore c = choreList.get(position);
                                c.setDesc(desc.getText().toString());
                                c.setTitle(name.getText().toString());
                                c.setAssignedUser(spinner.getSelectedItem().toString());
                                String[] values = new String[choreList.size()];
                                for(int i=0;i<choreList.size();i++){values[i]="";}
                                ChoreRowAdapter adapter = new ChoreRowAdapter(mContext, choreList);

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
        AlertDialog alertDialog= alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }



}

