package rommateapp.development.albie.therommateapp;

import java.io.Serializable;

/** A damage will be made up of several maintenance items.
 * maintenance items will represent one item a user would have
 * to purchase to fix a damage.
 * Created by alexgreco on 10/19/15.
 */
public class MaintenanceItem implements Serializable{
    public int MaintenanceItemId;
    public String desc;
    public String causingUser;
    public String purchaseUser;
    public int groupid;
    public boolean isComplete = false;

    public MaintenanceItem( String desc,
                           String causingUser, String purchaseUser,  boolean isComplete){
        this.desc = desc;
        this.causingUser = causingUser;
        this.purchaseUser = purchaseUser;
        this.isComplete = isComplete;

    }


    public MaintenanceItem(int MaintenanceItemId, String desc,
                           String causingUser, String purchaseUser, int groupid, boolean isComplete){
        this.MaintenanceItemId = MaintenanceItemId;
        this.desc = desc;
        this.causingUser = causingUser;
        this.purchaseUser = purchaseUser;
        this.isComplete = isComplete;

    }

    public int getMaintenanceItemId() {
        return MaintenanceItemId;
    }

    public void setMaintenanceItemId(int maintenanceItemId) {
        MaintenanceItemId = maintenanceItemId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCausingUser() {
        return causingUser;
    }

    public void setCausingUser(String causingUser) {
        this.causingUser = causingUser;
    }

    public String getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(String purchaseUser) {
        this.purchaseUser = purchaseUser;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "MaintenanceItem{" +
                "MaintenanceItemId='" + MaintenanceItemId + '\'' +
                ", desc='" + desc + '\'' +
                ", causingUser=" + causingUser +
                ", purchaseUser=" + purchaseUser +
                ", isComplete=" + isComplete +
                '}';
    }
}