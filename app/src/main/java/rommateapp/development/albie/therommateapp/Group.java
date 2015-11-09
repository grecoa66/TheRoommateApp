package rommateapp.development.albie.therommateapp;

import java.io.Serializable;

/**
 * Created by alexgreco on 11/1/15.
 */
public class Group implements Serializable{
    public int groupId;
    public UserList userList;
    public BillList billList;
    public ChoreList choreList;
    public GroceryList groceryList;
    public MaintenanceList maintenanceList;
    private String addr;
    private String name;

    public Group(int groupId, UserList userList, BillList billList, ChoreList choreList, GroceryList groceryList, MaintenanceList maintenanceList) {
        this.groupId = groupId;
        this.userList = userList;
        this.billList = billList;
        this.choreList = choreList;
        this.groceryList = groceryList;
        this.maintenanceList = maintenanceList;
    }

    public Group(String addr, String name) {
        this.addr = addr;
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public UserList getUserList() {
        return userList;
    }

    public void setUserList(UserList userList) {
        this.userList = userList;
    }

    public BillList getBillList() {
        return billList;
    }

    public void setBillList(BillList billList) {
        this.billList = billList;
    }

    public ChoreList getChoreList() {
        return choreList;
    }

    public void setChoreList(ChoreList choreList) {
        this.choreList = choreList;
    }

    public GroceryList getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(GroceryList groceryList) {
        this.groceryList = groceryList;
    }

    public MaintenanceList getMaintenanceList() {
        return maintenanceList;
    }

    public void setMaintenanceList(MaintenanceList maintenanceList) {
        this.maintenanceList = maintenanceList;
    }
}
