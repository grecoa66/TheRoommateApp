package rommateapp.development.albie.therommateapp;

import java.util.Date;
/**
 * This wil hold the information for one grocery item.
 * Created by alexgreco on 10/16/15.
 */
public class Grocery {
    public String groceryId = null;
    public String itemName;
    public int quantity;
    public Date dateRequested;
    public Date datePurchased;
    public boolean isPurchased = false;
    public double costPerItem;
    public double totalCost;
    public User requestUser;
    public User purchaseUser;//who purchased the item(s)

    public Grocery(String groceryId, String itemName, int quantity,
                   Date dateRequested, Date datePurchased, boolean isPurchased,
                   Double costPerItem, User requestUser,User purchaseUser ){
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

    public String getGroceryId() {
        return groceryId;
    }

    public void setGroceryId(String groceryId) {
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

    public User getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(User requestUser) {
        this.requestUser = requestUser;
    }

    public User getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(User purchaseUser) {
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
