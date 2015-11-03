package rommateapp.development.albie.therommateapp;

import java.util.Date;
/**
 * This wil hold the information for one grocery item.
 * Created by alexgreco on 10/16/15.
 */
public class Grocery {
    public int groceryId;
    public String itemName;
    public int quantity;
    public Date dateRequested;
    public Date datePurchased;
    public boolean isPurchased = false;
    public double costPerItem;
    public double totalCost;
    public String requestUser;
    public String purchaseUser;//who purchased the item(s)

    public Grocery(String itemName, int quantity,
                   Date dateRequested, String requestUser, boolean isPurchased ){
        this.itemName = itemName;
        this.quantity = quantity;
        this.dateRequested = dateRequested;
        this.isPurchased = isPurchased;
        this.requestUser = requestUser;
        this.purchaseUser = purchaseUser;
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

    public Date getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    public Date getDatePurchased() {
        return datePurchased;
    }

    public void setDatePurchased(Date datePurchased) {
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
