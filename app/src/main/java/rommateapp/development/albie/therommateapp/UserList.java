package rommateapp.development.albie.therommateapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by alexgreco on 11/1/15.
 */
public class UserList implements Serializable {
    public int userListId;
    public ArrayList<User> userList;
    public boolean isEmpty;

    public UserList(int userListId, ArrayList<User> userList) {
        this.userListId = userListId;
        this.userList = userList;
    }
    public UserList() {userList = new ArrayList<>();}

    public int getUserListId() {
        return userListId;
    }

    public void setUserListId(int userListId) {
        this.userListId = userListId;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public void addUser(User newUser){
        userList.add(newUser);
    }

    public Boolean removeUser(User user){
       return userList.remove(user);
    }

    public boolean isEmpty() {
        return userList.isEmpty();
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
