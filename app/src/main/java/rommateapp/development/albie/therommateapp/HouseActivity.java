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


public class HouseActivity extends AppCompatActivity implements userGroupResponse{

    private Context mContext;
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.house_main);
        mContext = this;

        DataBaseHandler db = new DataBaseHandler(this);
        db.getUser();

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


    public void addUser(View view){

    }

    public void removeUser(View view){

    }

    public void editUser(View view){

    }


    public void editGroup(View view){

    }


    public void userFinish(User user){
        currUser = user;
        TextView firstName = (TextView) findViewById(R.id.userFirstName);
        TextView lastName = (TextView) findViewById(R.id.userLastName);
        TextView phone = (TextView) findViewById(R.id.userPhone);
        TextView email = (TextView) findViewById(R.id.userEmail);

        firstName.setText("First Name: "+ user.getfName());
        lastName.setText("Last Name: "+ user.getlName());
        phone.setText("Phone: "+ user.getPhoneNumber());
        email.setText("email: "+ user.getEmailAddress());
    }

    public void userListFinish(ArrayList<User> output){

    }
    public void groupFinish(Group output){

    }

}

