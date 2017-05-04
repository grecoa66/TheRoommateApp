package rommateapp.development.albie.therommateapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Matthew on 10/20/2015.
 */

public class Register extends Activity {
    EditText ET_NAME,ET_USER_NAME,ET_USER_PASS;
    String name,user_name,user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        ET_NAME = (EditText)findViewById(R.id.name);
        ET_USER_NAME= (EditText)findViewById(R.id.new_user_name);
        ET_USER_PASS = (EditText)findViewById(R.id.new_user_pass);


    }
    public void userReg(View view)
    {

        name = ET_NAME.getText().toString();
        user_name = ET_USER_NAME.getText().toString();
        user_pass =ET_USER_PASS.getText().toString();
        String method = "register";
        HTTP_Connector http_c = new HTTP_Connector(this);
       // http_c.execute(method, name, user_name, user_pass);
        finish();

    }

}
