package rommateapp.development.albie.therommateapp;

import java.io.Serializable;
import java.util.Date;
/**
 * This wil hold the information for one grocery item.
 * Created by alexgreco on 10/16/15.
 */
public class Grocery implements Serializable{
    public int groceryId;
    public int groupid;
    public String itemName;
    public int quantity;
    public String dateRequested;
    public String datePurchased;
    public boolean isPurchased = false;
    public double costPerItem;
    public double totalCost;
    public String requestUser;
    public String purchaseUser;//who purchased the item(s)


    public Grocery(int groupid,String itemName, int quantity,
                   String dateRequested, String requestUser, boolean isPurchased
                   ){
        this.itemName = itemName;
        this.quantity = quantity;
        this.dateRequested = dateRequested;
        this.isPurchased = isPurchased;
        this.requestUser = requestUser;
        this.groupid = groupid;
    }


    public Grocery(int groceryId, String itemName, int quantity,
                   String dateRequested, String datePurchased, boolean isPurchased,
                   Double costPerItem, String requestUser,String purchaseUser ){
        this.groceryId = groceryId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.dateRequested = dateRequested;
        this.datePurchased = datePurchased;
        this.isPurchased = isPurchased;
        this.costPerItem = costPerItem;
        this.requestUser = requestUser;
        this.purchaseUser = purchaseUser;
    }

    public int getGroupId() {
        return groupid;
    }

    public void setGroupId(int groupid) {
        this.groupid = groupid;
    }
    public int getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(int groceryId) {
        this.groceryId = groceryId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(String dateRequested) {
        this.dateRequested = dateRequested;
    }

    public String getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(String datePurchased) {
        this.datePurchased = datePurchased;
    }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setIsPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }

    public double getCostPerItem() {
        return costPerItem;
    }

    public void setCostPerItem(double costPerItem) {
        this.costPerItem = costPerItem;
    }

    public double getTotalCost(double costPer, int quantity){
        return costPer * quantity;
    }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(String purchaseUser) {
        this.purchaseUser = purchaseUser;
    }

    @Override
    public String toString() {
        return "Grocery{" +
                "groceryId='" + groceryId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", dateRequested=" + dateRequested +
                ", datePurchased=" + datePurchased +
                ", isPurchased=" + isPurchased +
                ", costPerItem=" + costPerItem +
                ", totalCost=" + totalCost +
                ", requestUser=" + requestUser +
                ", purchaseUser=" + purchaseUser +
                '}';
    }
}