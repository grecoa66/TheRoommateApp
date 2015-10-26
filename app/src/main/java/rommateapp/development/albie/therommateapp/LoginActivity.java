package rommateapp.development.albie.therommateapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Matthew on 10/20/2015.
 */
public class LoginActivity extends AppCompatActivity {

    EditText ET_NAME,ET_PASS;
    String login_name,login_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ET_NAME = (EditText)findViewById(R.id.user_name);
        ET_PASS = (EditText)findViewById(R.id.user_pass);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings_icon) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
        public void userReg(View view)
        {
            startActivity(new Intent(this,Register.class));
        }
        public void userLogin(View view)
        {
            login_name = ET_NAME.getText().toString();
            login_pass = ET_PASS.getText().toString();
            String method = "login";
            HTTP_Connector httpcon = new HTTP_Connector(this);
            httpcon.execute(method,login_name,login_pass);
        }
    }
