package rommateapp.development.albie.therommateapp;

/**
 * Created by Matthew on 11/10/2015.
 */
public class Announcement {
    public String content;
    public String poster;
    public String date;
    public int id;
    public int groupid;
    public boolean isComplete = false;


    //constructor for creating bills when data is flowing from backend to front
    public Announcement(int id, String content, String poster, String date, int groupid)
    {
        this.id = id;
        this.content = content;
        this.poster = poster;
        this.date = date;
        this.groupid = groupid;
    }
    //constructor for adding bills, billId omitted because you don't know the bill id before its added to db
    public Announcement(String content, String poster, String date, int groupid)
    {
        this.content = content;
        this.poster = poster;
        this.date = date;
        this.groupid = groupid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDate() {
        return date;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getGroupid() {
        return groupid;
    }


}
