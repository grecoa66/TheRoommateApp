package rommateapp.development.albie.therommateapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

/**
 * Created by Albert on 10/21/2015.
 */
public class ChoreActivity extends AppCompatActivity {

    private Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chores_main);
        mContext = this;


        ArrayList<Chore> chores = new ArrayList<Chore>();
        User albie = new User(0, "albie","rynkie", "rynk@a.com","842523942");
        User greco = new User(0, "greco","alex", "rynk@a.com","842523942");
        User matt = new User(0, "Matt","cieslak", "rynk@a.com","842523942");

        chores.add(new Chore("0", "Sweep", "kitchen", "albie",true, 1));
        chores.add(new Chore("0", "Sweep", "kitchen", "albie",true, 1));
        chores.add(new Chore("0", "Sweep", "kitchen", "albie",true, 1));
        chores.add(new Chore("0", "Sweep", "kitchen", "albie",true, 1));



        ListView lv = (ListView) findViewById(R.id.list_chores);

        ChoreRowAdapter adapter;

        ArrayList<Chore> justMe = new ArrayList<>();
        for(int i=0;i<chores.size();i++){
            Chore c = chores.get(i);
            justMe.add(c);

        }

        String[] values = new String[justMe.size()];
        for(int i=0;i<justMe.size();i++){values[i]="";}
        adapter = new ChoreRowAdapter(mContext, justMe, values);

        lv.setAdapter(adapter);

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                view.setSelected(true);
            }
        });





        HTTP_Connector httpcon= new HTTP_Connector(this);
        HTTP_Connector.getChoreList getchores = httpcon.new getChoreList();
        getchores.execute("1");

        if(getchores.getStatus() == AsyncTask.Status.FINISHED){
            //chores = getchore..
            //setAdapters(chores);
            Toast.makeText(this,"callback",Toast.LENGTH_SHORT).show();
        }

        }
    //}

    public void changeAdapater(View view){

    }


   /* public void setAdapters(ArrayList<Chore> chores){

        ListView lv;
        ChoreRowAdapter adapter;

        ArrayList<Chore> justMe = new ArrayList<>();
        for(int i=0;i<chores.size();i++){
            Chore c = chores.get(i);
            if(c.getRequestUser().equals("rynkie") && c.isComplete()==false)
            {
                justMe.add(c);
            }
        }

        String[] values = new String[justMe.size()];
        for(int i=0;i<justMe.size();i++){values[i]="";}
        adapter = new ChoreRowAdapter(mContext, justMe, values);



        ArrayList<Chore> house = new ArrayList<Chore>();
        for(int i=0;i<chores.size();i++){
            Chore c = chores.get(i);
            if(c.isComplete()==false)
            {
                house.add(c);
            }
        }

        values = new String[house.size()];
        for(int i=0;i<house.size();i++){values[i]="";}
       // adapter2 = new ChoreRowNoPicture(mContext, house, values);

        values = new String[chores.size()];
        for(int i=0;i<chores.size();i++){values[i]="";}
       //// adapter3 = new ChoreRowNoPicture(mContext, chores, values);


        lv.setAdapter(adapter);

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                view.setSelected(true);
            }
        });
    }
*/



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


    public void addChore(View view) {

        LayoutInflater li = LayoutInflater.from(mContext);
        View promptsView = li.inflate(R.layout.chore_add, null);

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

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                                Toast.makeText(mContext, name.getText().toString()+", "+desc.getText().toString()+", "+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
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
}
