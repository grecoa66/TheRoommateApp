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
    public User userToPay;
    public User userToBill;
    public double totalPaid;

    public Bill(String desc, int billId, double totalAmount, User userToBill,
               User userToPay,
                double totalPaid, boolean isComplete)
    {
        this.desc = desc;
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.userToPay = userToPay;
        this.userToBill = userToBill;
        this.totalPaid = totalPaid;
        this.isComplete = isComplete;
    }

    public User getUserToPay() {
        return userToPay;
    }

    public void setUserToPay(User userToPay) {
        this.userToPay = userToPay;
    }
    public User getUserToBill() {
        return userToBill;
    }

    public void setUserToBill(User billableUser) {
        this.userToBill = billableUser;
    }


    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
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

    public boolean isComplete;


}
