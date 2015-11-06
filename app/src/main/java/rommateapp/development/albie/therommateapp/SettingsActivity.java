package rommateapp.development.albie.therommateapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.List;

/**
 * Created by alexgreco on 11/5/15.
 */
public class SettingsActivity extends AppCompatActivity {

    public Context mContext;
    public ListView lv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_main);

        mContext = this;
        lv = (ListView) findViewById(R.id.setting_main);

    }
}
