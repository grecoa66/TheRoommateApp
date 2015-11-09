package rommateapp.development.albie.therommateapp;

import java.util.ArrayList;

/**
 * Created by Albert on 11/4/2015.
 */
public interface userGroupResponse {

    void userListFinish(ArrayList<User> output);
    void groupFinish(Group output);
    void userFinish(User output);
}
