package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**
 * Created by alexgreco on 10/21/15.
 */
public class GroceryList {

    public String groceryListId;
    public ArrayList<Grocery> groceryList;
    public boolean isEmpty;

    public GroceryList(String groceryListId, ArrayList<Grocery> groceryList, boolean isEmpty) {
        this.groceryListId = groceryListId;
        this.groceryList = groceryList;
        this.isEmpty = isEmpty;
    }

    public String getGroceryListId() {
        return groceryListId;
    }

    public void setGroceryListId(String groceryListId) {
        this.groceryListId = groceryListId;
    }

    public ArrayList<Grocery> getGroceryList() {
        return groceryList;
    }

    public void setGroceryList(ArrayList<Grocery> groceryList) {
        this.groceryList = groceryList;
    }

    public void addGrocery(Grocery grocery){
        groceryList.add(grocery);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
