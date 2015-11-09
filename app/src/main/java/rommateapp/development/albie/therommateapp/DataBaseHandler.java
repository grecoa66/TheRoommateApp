package rommateapp.development.albie.therommateapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Albert on 11/4/2015.
 * This class would have the HTTPConnector object and would handle all the db calls
 * return choreList, addChore etc would all be done here. This should be the only class that implements
 * the response interfaces
 */
public class DataBaseHandler implements AsyncResponse{


    private  HTTP_Connector httpcon;
    private Context mContext;

    public DataBaseHandler(Context context){
        mContext = context;
        httpcon = new HTTP_Connector(mContext);
    }
    public void userListFinish(ArrayList<User> output){

    }
    public void groupFinish(Group output){

    }
    public void userFinish(User output){
        AsyncResponse resp = (AsyncResponse) mContext;
        resp.processFinish(output);

    }

    public void processFinish(ArrayList<Chore> result){

    }
    public void processFinish(MaintenanceList result){

    }
    public void processFinish(GroceryList result){

    }
    public void processFinish(User output){
        AsyncResponse resp = (AsyncResponse) mContext;
        resp.processFinish(output);


    }
    public void getUser(){
        //hardcoded for albie user
        HTTP_Connector.getUser getUser = httpcon.new getUser(this);
        getUser.execute("rynkie18@students.rowan.edu","pass");

    }
}
