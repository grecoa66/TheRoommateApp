package rommateapp.development.albie.therommateapp;

import java.io.Serializable;

/**
 * The Chore class will hold information for one chore.
 * Created by alexgreco on 10/16/15.
 */
public class Chore implements Serializable {


    public int choreId ;
    public String title = "";
    public String desc = "";
    public String requestUser;
    public String assignedUser;
    public int groupid;
    public boolean isComplete = false;

    public Chore( String title, String desc, String requestUser, String assignedUser, boolean isComplete, int groupid){

        this.title = title;
        this.desc = desc;
        this.requestUser = requestUser;
        this.assignedUser = assignedUser;
        this.isComplete = isComplete;
        this.groupid = groupid;
    }

    public int getChoreId() {
        return this.choreId;
    }

    public void setChoreId(int choreId) {
        this.choreId = choreId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setId(int id){ this.choreId = id; }

    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }

    public String getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(String assignedUser) {
        this.assignedUser = assignedUser;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }


    @Override
    public String toString() {
        return "Chore{" +
                "choreId='" + choreId + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", requestUser=" + requestUser +
                ", assignedUser=" + assignedUser +
                ", isComplete=" + isComplete +
                '}';
    }
}