package rommateapp.development.albie.therommateapp;

/**
 * Created by alexgreco on 11/1/15.
 */
public class UserList {
    public int userListId;
    public User[] userList;
    public boolean isEmpty;

    public UserList(int userListId, User[] userList, boolean isEmpty) {
        this.userListId = userListId;
        this.userList = userList;
        this.isEmpty = isEmpty;
    }

    public int getUserListId() {
        return userListId;
    }

    public void setUserListId(int userListId) {
        this.userListId = userListId;
    }

    public User[] getUserList() {
        return userList;
    }

    public void setUserList(User[] userList) {
        this.userList = userList;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
}
