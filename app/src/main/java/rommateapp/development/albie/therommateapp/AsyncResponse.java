package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**
 * Created by Albert on 11/6/2015.
 */
public interface AsyncResponse {

    void processFinish(ChoreList cl);
    void processFinish(GroceryList gl);
    void processFinish(MaintenanceList ml);
    void processFinish(User resp);
    void processFinish(UserList ul);
    void processFinish(BillList bl);
    //void processFinish(ArrayList<)
}
