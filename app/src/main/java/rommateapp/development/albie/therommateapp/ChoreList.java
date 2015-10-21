package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**
 * Created by alexgreco on 10/20/15.
 */
public class ChoreList {
    public String choreListId;
    public ArrayList<Chore> chores;
    public boolean isEmpty;

    public ChoreList(String choreListId, ArrayList<Chore> chores, boolean isEmpty) {
        this.choreListId = choreListId;
        this.chores = chores;
        this.isEmpty = isEmpty;
    }

    public String getChoreListId() {
        return choreListId;
    }

    public void setChoreListId(String choreListId) {
        this.choreListId = choreListId;
    }

    public ArrayList<Chore> getChores() {
        return chores;
    }

    public void setChores(ArrayList<Chore> chores) {
        this.chores = chores;
    }

    public void addChore(Chore chore){
        chores.add(chore);
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
