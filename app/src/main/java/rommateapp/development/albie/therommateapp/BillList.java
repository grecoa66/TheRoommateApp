package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**The BillList is a collection of bill objects.
 * Bill objects can be added to the BillList.
 * Every group can only have one BillList.
 * Every Billist has a unique ID;
 *
 * Created by alexgreco on 10/21/15.
 */
public class BillList {
    public int BillListId;
    public ArrayList<Bill> billList;

    public BillList(int billListId, ArrayList<Bill> billList) {
        BillListId = billListId;
        this.billList = billList;
    }
    public BillList() {}

    public int getBillListId() {
        return BillListId;
    }

    public void setBillListId(int billListId) {
        BillListId = billListId;
    }

    public ArrayList<Bill> getBillList() {
        return billList;
    }

    public void setBillList(ArrayList<Bill> billList) {
        this.billList = billList;
    }

    public void addBill(Bill bill){
        billList.add(bill);
    }

    public Boolean removeBill(Bill bill){
        return billList.remove(bill);
    }

    public boolean isEmpty() {
        return billList.isEmpty();
    }
}
