package rommateapp.development.albie.therommateapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        HTTP_Connector httpcon = new HTTP_Connector(this);

/**
 * example of getUser function
 */
       /* HTTP_Connector.getUser gu = httpcon.new getUser();
        gu.execute("cieslakm0@students.rowan.edu", "pass");
        if(gu.getStatus() == AsyncTask.Status.FINISHED){
            gu.createUserObject();
        }
        */
/**
 * example of getChoreList function
 */
        /*
        HTTP_Connector.getChoreList getchores = httpcon.new getChoreList();
        getchores.execute("1");
        if(getchores.getStatus() == AsyncTask.Status.FINISHED) {
            getchores.createChoreListObject();
        }
        */
        /**
         * example of addChore function
         */
        /*
        HTTP_Connector.addChore adder = httpcon.new addChore();
        Chore test_chore = new Chore("cleanbathroom", "Clean the bathroom", "Matt", "testuser", false, 1);
        adder.execute(test_chore);
        */
/**
 * example of getChoreList function
 */
        /*
        HTTP_Connector.editChore edit = httpcon.new editChore();
        Chore test_chore = new Chore("clean kitchen edit", "Clean the bathroom edit", "Matt", "testuser", false, 1);
        test_chore.setChoreId(1);
        edit.execute(test_chore);
        */
    }


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


    public void testVenmo(View view) {
        if (VenmoLibrary.isVenmoInstalled(this)) {
            Intent venmoIntent = VenmoLibrary.openVenmoPayment("2952", "Roommate App Test", "7327889740", ".01", "test", "charge");
            startActivityForResult(venmoIntent, 2952);

        }
    }

    public void testSqlite(View view){
        Intent dbIntent = new Intent(this, DbTestActivity.class);
        startActivity(dbIntent);
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
}
