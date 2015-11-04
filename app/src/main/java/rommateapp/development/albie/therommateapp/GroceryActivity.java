package rommateapp.development.albie.therommateapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

    public void addGrocery(View view){
        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.grocery_add, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                mContext);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        final EditText grocName = (EditText) promptsView.findViewById(R.id.grocNameAdd);
        final EditText grocQuant= (EditText) promptsView.findViewById(R.id.grocQuantAdd);
        final Date currentDate = new Date(System.currentTimeMillis());


        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                allGroc.add(new Grocery(
                                        grocName.getText().toString(),
                                        Integer.parseInt(grocQuant.getText().toString()),
                                        currentDate,
                                        "Albie", false));
                                ListView lv = (ListView) findViewById(R.id.list_grocery);
                                GroceryRowAdapter adapter = new GroceryRowAdapter(mContext, allGroc);
                                lv.setAdapter(adapter);
                                //setListener(list);
                                currentAdapter(lv);
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void showModal(final int pos){

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
