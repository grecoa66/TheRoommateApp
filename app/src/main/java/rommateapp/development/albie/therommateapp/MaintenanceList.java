package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**
 * The MaintenanceList is a collection of MaintenanceItems.
 * MaintenanceItem objects can be add to this object.
 * Every Group can have only one MaintenanceList.
 * Every MaintenanceList has a unique Id.
 *
 * Created by Alex Greco on 10/21/15.
 */
public class MaintenanceList {
    public int maintenanceListId;
    public ArrayList<MaintenanceItem> maintenanceList;

    public MaintenanceList(int maintenanceListId, ArrayList<MaintenanceItem> maintenanceList) {
        this.maintenanceListId = maintenanceListId;
        this.maintenanceList = maintenanceList;
    }

    //returns the array of maintenanceItem objects
    public ArrayList<MaintenanceItem> getMaintenanceList() {
        return maintenanceList;
    }
    //set the maintenace list
    public void setMaintenanceList(ArrayList<MaintenanceItem> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }

    //add a new maintenanceItem
    public void addMaintenaceItem(MaintenanceItem maintenanceItem){
        maintenanceList.add(maintenanceItem);
    }

    public Boolean removeMaintenacceItem(MaintenanceItem item){
        return maintenanceList.remove(item);
    }
    public int getMaintenanceListId() {
        return maintenanceListId;
    }

    public void setMaintenanceListId(int maintenanceListId) {
        this.maintenanceListId = maintenanceListId;
    }

    //are all maintenaceItems done?
    public boolean isComplete() {
        return maintenanceList.isEmpty();
    }
}
