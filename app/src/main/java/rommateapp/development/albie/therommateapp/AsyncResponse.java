package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**
 * Created by Albert on 11/6/2015.
 */
public interface AsyncResponse {

    void processFinish(ArrayList<Chore> result);
    void processFinish(GroceryList gl);
    void processFinish(MaintenanceList ml);
    void processFinish(User resp);
}
