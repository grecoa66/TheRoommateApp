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
    public String billId;
    public String desc;
    public double totalAmount;
    public ArrayList<User> userToPay;
    public ArrayList<Payment> payments;

    public double totalPaid;

    //arraylist of the user that are going to split this bill
    public ArrayList<User> getUserToPay() {
        return userToPay;
    }

    public void setUserToPay(ArrayList<User> userToPay) {
        this.userToPay = userToPay;
    }

    public void addUser(User user){
        userToPay.add(user);
    }

    //array list of the payments that this bill is split into
    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    //add a new payment to the list
    public void addPayment(Payment payment){
        payments.add(payment);
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

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public boolean isComplete;


}
