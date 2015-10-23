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
    public String BillListId;
    public ArrayList<Bill> billList;
    public boolean isEmpty;

    public BillList(String billListId, boolean isEmpty, ArrayList<Bill> billList) {
        BillListId = billListId;
        this.isEmpty = isEmpty;
        this.billList = billList;
    }

    public String getBillListId() {
        return BillListId;
    }

    public void setBillListId(String billListId) {
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

    public boolean isEmpty() {
        return billList.isEmpty();
    }
}
