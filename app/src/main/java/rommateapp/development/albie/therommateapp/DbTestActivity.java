package rommateapp.development.albie.therommateapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by alexgreco on 10/24/15.
 */
public class DbTestActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_test);

        TextView dbData = (TextView) findViewById(R.id.db_text_view);
        //start sqlite database
        DBHelperUser userDB = new DBHelperUser(this);
        userDB.insertUser("001", "James", "Richmond", "abc123@gmail.com", "908-234-9566");
        userDB.insertUser("002", "Rick", "Shamrock", "rShamrock@gmail.com", "856-443-1234");
        userDB.insertUser("003", "Diane", "Sharp", "kanyeRox@gmail.com", "908-993-1215");

        Cursor dbCursor = userDB.getUserData("002");
        dbCursor.moveToFirst();
        String dbString  = dbCursor.getString(dbCursor.getColumnIndex(DBHelperUser.FIRST__NAME));
        String dbString2 = dbCursor.getString(dbCursor.getColumnIndex(DBHelperUser.LAST__NAME));
        String dbString3 = dbString + " " + dbString2;

        dbData.setText(dbString3);
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
        Utility.openNewActivity(id, this);
        return super.onOptionsItemSelected(item);
    }
}
