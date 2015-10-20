package rommateapp.development.albie.therommateapp;
/**
 * A bill will be made up of a collection of bills.
 * Each bill will have one user who must pay, and one user it
 * will be paid to.
 * Each bill will have an amount, that will be divided
 * into any number of payments, which are assigned to a user.
 * The user who must make the payment will have a balance.
 * Until the user has a balance of 0.00, they will still be assigned the payment
 * Created by alexgreco on 10/19/15.
 */
public class Payment {
    public String paymentId = null;
    public String desc;
    public double total;
    public double amountPaid;
    public double balance;
    public User makingPayment;
    public User recievingPayment;
    public boolean isComplete = false;

    public Payment(String paymentId, String desc,
                   double total, double amountPaid, User makingPayment,
                   User recievingPayment, boolean isComplete){

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getMakingPayment() {
        return makingPayment;
    }

    public void setMakingPayment(User makingPayment) {
        this.makingPayment = makingPayment;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public User getRecievingPayment() {
        return recievingPayment;
    }

    public void setRecievingPayment(User recievingPayment) {
        this.recievingPayment = recievingPayment;
    }

}
