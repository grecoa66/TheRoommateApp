package rommateapp.development.albie.therommateapp;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.DatabaseUtils;
import java.util.ArrayList;

/**
 * Created by alexgreco on 10/24/15.
 */
public class DBHelperChore extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "chores";
    public static final String CHORE_ID = "choreId";
    public static final String CHORE_TITLE = "title";
    public static final String CHORE_DESC = "desc";
    public static final String REQUEST_USER_ID = "requestUser";
    public static final String ASSIGNED_USER_ID = "assignedUser";
    public static final String IS_COMPLETE = "isComplete";

    public DBHelperChore(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }//end onUpgrade

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table chores " +
                        "(choreId integer primary key," +
                        " title text," +
                        " desc text," +
                        " requestUser text, " +
                        " assignedUser text," +
                        " isComplete text)"
        );
    }//end oncreate

    public boolean insertChore(String choreId, String title, String desc, String requestUser, String assignedUser, String isComplete){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("choreId", choreId);
        cv.put("title", title);
        cv.put("desc", desc);
        cv.put("requestUser", requestUser);
        cv.put("assignedUser", assignedUser);
        cv.put("iscomplete", isComplete);
        db.insert("chores", null, cv);
        return true;
    }

    public int removeUser(String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("chores", "choreId = ?", new String[] { userId });
    }

    public boolean updateChore(String choreId, String title, String desc, String requestUser, String assignedUser, String isComplete){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("choreId", choreId);
        cv.put("title", title);
        cv.put("desc", desc);
        cv.put("requestUser", requestUser);
        cv.put("assignedUser", assignedUser);
        cv.put("iscomplete", isComplete);
        db.update("chores", cv, "choreId = ?", new String[]{choreId});
        return true;
    }

    public Cursor getChoreData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from chores where choreId="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean choreIsComplete(String choreId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("choreId", choreId);
        cv.put("iscomplete", "true");
        db.update("chores", cv, "choreId = ?", new String[]{choreId});
        return true;
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> newArr = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from chores", null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            newArr.add(res.getString(res.getColumnIndex(CHORE_TITLE)));
        }
        return newArr;
    }
}
