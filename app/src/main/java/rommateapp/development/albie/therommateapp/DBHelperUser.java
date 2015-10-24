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
public class DBHelperUser extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "users";
    public static final String USER_ID = "userId";
    public static final String FIRST__NAME = "firstName";
    public static final String LAST__NAME = "lastName";
    public static final String EMAIL_ADDRESS = "email";
    public static final String PHONE_NUMBER = "phone";


    public DBHelperUser(Context context)
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
                "create table users " +
                        "(userId integer primary key," +
                        " firstName text," +
                        " lastName text," +
                        " email text, " +
                        " phone text)"
        );
    }//end oncreate

    public boolean insertUser(String userId, String firstName, String lastName, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userId", userId);
        cv.put("firstName", firstName);
        cv.put("lastName", lastName);
        cv.put("email", email);
        cv.put("phone", phone);
        db.insert("users", null, cv);
        return true;
    }

    public int removeUser(String userId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("users", "userId = ?", new String[] { userId });
    }

    public boolean updateUser(String userId, String firstName, String lastName, String email, String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("userId", userId);
        cv.put("firstName", firstName);
        cv.put("lastName", lastName);
        cv.put("email", email);
        cv.put("phone", phone);
        db.update("users", cv, "userId = ? ", new String[] { userId } );
        return true;
    }

    public Cursor getUserData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users where userId="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> newArr = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from users", null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            newArr.add(res.getString(res.getColumnIndex(FIRST__NAME)));
        }
        return newArr;
    }
}
