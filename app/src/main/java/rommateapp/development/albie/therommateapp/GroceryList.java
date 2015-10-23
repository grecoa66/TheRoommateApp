package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**
 * A GroceryList is a collection of grocery object.
 * a grocery can be added to this list.
 * Every GroeceryList has a uniqueId.
 * Every group can have one groceryList.
 *
 * Created by alexgreco on 10/21/15.
 */
public class GroceryList {

    public String groceryListId;
    public ArrayList<Grocery> groceryList;

    public GroceryList(String groceryListId, ArrayList<Grocery> groceryList) {
        this.groceryListId = groceryListId;
        this.groceryList = groceryList;
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
        return groceryList.isEmpty();
    }

}
