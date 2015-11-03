package rommateapp.development.albie.therommateapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;
import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Albert on 10/21/2015.
 */
public class GroceryActivity extends AppCompatActivity {

    private Context mContext;
    private ArrayList<Grocery> allGroc= new ArrayList<>();
    private ArrayList<Grocery> currentGroc = new ArrayList<>();
    private ListView lv ;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_main);

        mContext = this;
        Date date = new Date(System.currentTimeMillis());

        allGroc.add(new Grocery("Milk", 1, date, "Greco", false));
        allGroc.add(new Grocery("Carton of Eggs", 1, date, "Albie", false));
        allGroc.add(new Grocery("Oranges", 6, date, "Greco", false));
        allGroc.add(new Grocery("Taco Kit", 1, date, "Matt", false));

        lv = (ListView) findViewById(R.id.list_grocery);

        currentAdapter(lv);
    }

    public void currentAdapter(View view){

        for(int i = 0; i < allGroc.size() ; i++){
            Grocery grocery = allGroc.get(i);
            if(!currentGroc.contains(grocery) && !grocery.isPurchased()) {
                currentGroc.add(grocery);
            }
        }

        GroceryRowAdapter adapter;
        adapter = new GroceryRowAdapter(mContext, currentGroc);
        lv.setAdapter(adapter);

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                //Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                //showModal(position);
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
        Utility.openNewActivity(id, this);
        return super.onOptionsItemSelected(item);
    }
}
