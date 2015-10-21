package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**
 * Created by alexgreco on 10/21/15.
 */
public class MaintenanceList {
    public String maintenanceListId;
    public ArrayList<MaintenanceItem> maintenanceList;
    public boolean isComplete;

    public MaintenanceList(String maintenanceListId, ArrayList<MaintenanceItem> maintenanceList, boolean isComplete) {
        this.maintenanceListId = maintenanceListId;
        this.maintenanceList = maintenanceList;
        this.isComplete = isComplete;
    }

    public ArrayList<MaintenanceItem> getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(ArrayList<MaintenanceItem> maintenanceList) {
        this.maintenanceList = maintenanceList;
    }

    public void addMaintenaceItem(MaintenanceItem maintenanceItem){
        maintenanceList.add(maintenanceItem);
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public String getMaintenanceListId() {
        return maintenanceListId;
    }

    public void setMaintenanceListId(String maintenanceListId) {
        this.maintenanceListId = maintenanceListId;
    }
}
