package rommateapp.development.albie.therommateapp;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.ListView;
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
    private UserList userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        String  o;
        HTTP_Connector httpcon= new HTTP_Connector(this);
        HTTP_Connector.getChoreList getchores = httpcon.new getChoreList(this);

        getchores.execute("1");



    }

    public void processFinish(ArrayList<Chore> response){

        chores = response;

        Toast.makeText(this,"we have chores: "+ chores.toString(),Toast.LENGTH_SHORT).show();
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

