package rommateapp.development.albie.therommateapp;

/**
 * The Chore class will hold information for one chore.
 * Created by alexgreco on 10/16/15.
 */
public class Chore {


    public String choreId = null;
    public String title = "";
    public String desc = "";
    public User requestUser;
    public User assignedUser;
    public boolean isComplete = false;

    public Chore(String choreId, String title, String desc, User requestUser, User assignedUser, boolean isComplete){
        this.choreId = choreId;
        this.title = title;
        this.desc = desc;
        this.requestUser = requestUser;
        this.assignedUser = assignedUser;
        this.isComplete = isComplete;
    }

    public String getChoreId() {
        return choreId;
    }

    public void setChoreId(String choreId) {
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

    public User getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(User requestUser) {
        this.requestUser = requestUser;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
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
