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
    private ArrayList<Chore> allChores;
    private ArrayList<Chore> currentChores;
    private ChoreRowAdapter adapter;
    private ListView list;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chores_main);
        mContext = this;

        allChores = new ArrayList<>();
        User albie = new User(0, "albie","rynkie", "rynk@a.com","842523942");
        User greco = new User(0, "greco","alex", "rynk@a.com","842523942");
        User matt = new User(0, "Matt","cieslak", "rynk@a.com","842523942");

        allChores.add(new Chore("Sweep", "kitchen", "greco", "albie",true, 1));
        allChores.add(new Chore("Mop", "bathroom", "matt", "albie",true, 1));
        allChores.add(new Chore("walk dog", "sparky needs to go", "greco", "matt",true, 1));
        allChores.add(new Chore("garbage", "Its garbage day", "matt", "greco",true, 1));



        list = (ListView) findViewById(R.id.list_chores);


        currentChores = new ArrayList<>();
        for(int i=0;i<allChores.size();i++){
            Chore c = allChores.get(i);
            if(c.getAssignedUser().equals("albie")){
                currentChores.add(c);
            }

        }

        String[] values = new String[currentChores.size()];
        for(int i=0;i<currentChores.size();i++){values[i]="";}
        adapter = new ChoreRowAdapter(mContext, currentChores, values);

        list.setAdapter(adapter);
        setListener(list);




        ///////////////////////////////////////////////////
      /*  HTTP_Connector httpcon= new HTTP_Connector(this);
        HTTP_Connector.getChoreList getchores = httpcon.new getChoreList();
        getchores.execute("1");

        if(getchores.getStatus() == AsyncTask.Status.FINISHED){
            //chores = getchore..
            //setAdapters(chores);
            Toast.makeText(this,"callback",Toast.LENGTH_SHORT).show();
        }
*/
    }
    //}

    public void changeAdapter(View view){

        currentChores = new ArrayList<>();
        User albie = new User(0, "albie","rynkie", "rynk@a.com","842523942");
        User greco = new User(0, "greco","alex", "rynk@a.com","842523942");
        User matt = new User(0, "Matt","cieslak", "rynk@a.com","842523942");

        currentChores.add(new Chore("0", "Sweep", "kitchen", "albie",true, 1));
        currentChores.add(new Chore("0", "Sweep", "kitchen", "albie",true, 1));





        String[] values = new String[currentChores.size()];
        for(int i=0;i<currentChores.size();i++){values[i]="";}
        adapter = new ChoreRowAdapter(mContext, currentChores, values);

        list.setAdapter(adapter);
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
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                                currentChores.add(new Chore(name.getText().toString(), desc.getText().toString(), spinner.getSelectedItem().toString(), "albie",true, 1));//current user, not albie
                                ListView lv = (ListView) findViewById(R.id.list_chores);
                                String[] values = new String[currentChores.size()];
                                for(int i=0;i<currentChores.size();i++){values[i]="";}
                                adapter = new ChoreRowAdapter(mContext, currentChores, values);
                                list.setAdapter(adapter);
                                setListener(list);
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

    public void showModal(final int position){

        Chore c = currentChores.get(position);
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
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                                //result.setText(userInput.getText());
                                Chore c = currentChores.get(position);
                                c.setDesc(desc.getText().toString());
                                c.setTitle(name.getText().toString());
                                c.setAssignedUser(spinner.getSelectedItem().toString());
                                String[] values = new String[currentChores.size()];
                                for(int i=0;i<currentChores.size();i++){values[i]="";}
                                adapter = new ChoreRowAdapter(mContext, currentChores, values);

                                list.setAdapter(adapter);
                                Toast.makeText(mContext, "I should edit here "+ name.getText().toString()+", "+desc.getText().toString()+", "+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
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

    public void setListener(ListView lv){

        //This is where we can create the modal for edit  delete
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Start the CAB using the ActionMode.Callback defined above
                Toast.makeText(mContext, "you changed me " + position, Toast.LENGTH_SHORT).show();
                showModal(position);
                view.setSelected(true);
            }
        });

    }

}

