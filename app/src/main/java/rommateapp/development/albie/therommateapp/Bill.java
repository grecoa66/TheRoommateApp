package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**This class will be a bill.
 * It will be made by a user or a grocery item or maintenanceItem.
 * It will be made up of a collection of payment objects.
 * When a bill is created, the user can chose who will
 * be assigned with a payment
 * Created by alexgreco on 10/19/15.
 */
public class Bill {
    public int billId;
    public String desc;
    public double totalAmount;
    public String userToPay;
    public String userToBill;
    public double totalPaid;
    public int groupid;
    public boolean isComplete = false;


    //constructor for creating bills when data is flowing from backend to front
    public Bill(int billId, String desc, double totalAmount, String userToBill, String userToPay, int groupid)
    {
        this.billId = billId;
        this.desc = desc;
        this.totalAmount = totalAmount;
        this.userToPay = userToPay;
        this.userToBill = userToBill;
        this.groupid = groupid;
    }
    //constructor for adding bills, billId omitted because you don't know the bill id before its added to db
    public Bill(String desc, double totalAmount, String userToBill, String userToPay, int groupid)
    {
        this.desc = desc;
        this.totalAmount = totalAmount;
        this.userToPay = userToPay;
        this.userToBill = userToBill;
        this.groupid = groupid;
    }

    public String getUserToPay() {
        return userToPay;
    }

    public void setUserToPay(String userToPay) {
        this.userToPay = userToPay;
    }
    public String getUserToBill() {
        return userToBill;
    }

    public void setUserToBill(String billableUser) {
        this.userToBill = billableUser;
    }


    public boolean isComplete() {
        if((totalAmount - totalPaid) == 0){
            isComplete = true;
        }else{
            isComplete = false;
        }
        return isComplete;
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }




}
