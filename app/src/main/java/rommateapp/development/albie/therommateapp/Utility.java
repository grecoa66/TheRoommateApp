package rommateapp.development.albie.therommateapp;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

/**
 * Created by Albert on 10/21/2015.
 * This class does things that other classes in this project may need. It is used so that the other
 * classes have cleaner looking code
 */
public class Utility {

    public static void openNewActivity(int id, Activity callingActivity) {
        switch (id) {
            case R.id.grocery_icon:
                Intent groceryIntent = new Intent(callingActivity, GroceryActivity.class);
                groceryIntent.putExtra("key", 4); //Optional parameters
                groceryIntent.setFlags(groceryIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(groceryIntent);
                break;
            case R.id.chores_icon:
                Intent choresIntent = new Intent(callingActivity, ChoreActivity.class);
                choresIntent.putExtra("key", 4); //Optional parameters
                choresIntent.setFlags(choresIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(choresIntent);
                break;
            case R.id.maint_icon:
                Intent maintIntent = new Intent(callingActivity, MaintenanceActivity.class);
                maintIntent.putExtra("key", 4); //Optional parameters
                maintIntent.setFlags(maintIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(maintIntent);
                break;
            case R.id.bills_icon:
                Intent billsIntent = new Intent(callingActivity, BillsActivity.class);
                billsIntent.putExtra("key", 4); //Optional parameters
                // billsIntent.setFlags(billsIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(billsIntent);
                break;
           case R.id.settings_icon:
                Intent houseIntent = new Intent(callingActivity, HouseActivity.class);
                houseIntent.putExtra("key", 4); //Optional parameters
                houseIntent.setFlags(houseIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(houseIntent);
                break;

        }
    }

    public static void openNewActivity(int id, Activity callingActivity, ArrayList<Chore> choresList){
        switch (id){
            case R.id.grocery_icon:
                Intent groceryIntent = new Intent(callingActivity, GroceryActivity.class);
                groceryIntent.putExtra("key", 4); //Optional parameters
                groceryIntent.setFlags(groceryIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(groceryIntent);
                break;
            case R.id.chores_icon:
                Intent choresIntent = new Intent(callingActivity, ChoreActivity.class);
                choresIntent.putExtra("key", 4); //Optional parameters
                choresIntent.putExtra("chores", choresList);
                choresIntent.setFlags(choresIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(choresIntent);
                break;
            case R.id.maint_icon:
                Intent maintIntent = new Intent(callingActivity, MaintenanceActivity.class);
                maintIntent.putExtra("key", 4); //Optional parameters
                maintIntent.setFlags(maintIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(maintIntent);
                break;
            case R.id.bills_icon:
                Intent billsIntent = new Intent(callingActivity, BillsActivity.class);
                billsIntent.putExtra("key", 4); //Optional parameters
                // billsIntent.setFlags(billsIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(billsIntent);
                break;
           case R.id.settings_icon:
                Intent houseIntent = new Intent(callingActivity, HouseActivity.class);
               houseIntent.putExtra("key", 4); //Optional parameters
               houseIntent.setFlags(houseIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);//not followed on backstack
                callingActivity.startActivity(houseIntent);
                break;

        }

        }
    /*
        *Could implement so the above code is a little cleaner
     */
    private static Intent getIntent(Activity callingActivity){
        return new Intent();
    }

}
