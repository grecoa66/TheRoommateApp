package rommateapp.development.albie.therommateapp;

/**
 * The User class will hold all information about a single user
 * Created by alexgreco on 10/16/15.
 */
public class User {

    public String userId = null;
    public String fName = "";
    public String lName = "";
    public String emailAddress = "";
    public String phoneNumber= "";

    public User(String userId, String fName, String lName, String emailAddress, String phoneNumber){
        this.userId = userId;
        this.fName = fName;
        this.lName = lName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        return "Name: " + fName + " " + lName + ", Phone Number: " + phoneNumber + ", Email Address:" + emailAddress;
    }

}