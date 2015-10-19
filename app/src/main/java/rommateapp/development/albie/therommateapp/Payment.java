import rommateapp.development.albie.therommateapp.User;

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

}
