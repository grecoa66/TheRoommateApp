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
public class DBHelperBill extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "bills";
    public static final String BILL_ID = "billId";
    public static final String BILL_TOTAL = "total";
    public static final String BILL_DESC = "desc";
    public static final String REQUEST_USER_ID = "requestUser";
    public static final String ASSIGNED_USER_ID = "assignedUser";
    public static final String IS_COMPLETE = "isComplete";
    public static final String TOTAL_PAID = "totalPaid";

    public DBHelperBill(Context context)
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
                "create table bills " +
                        "(billId integer primary key," +
                        " total text," +
                        " desc text," +
                        " requestUser text, " +
                        " assignedUser text," +
                        " isComplete text, " +
                        " totalPaid text)"
        );
    }//end oncreate

    public boolean insertBill(String billId, String total, String desc, String requestUser, String assignedUser, String isComplete, String totalPaid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("billId", billId);
        cv.put("total", total);
        cv.put("desc", desc);
        cv.put("requestUser", requestUser);
        cv.put("assignedUser", assignedUser);
        cv.put("iscomplete", isComplete);
        cv.put("totalPaid", totalPaid);
        db.insert("bills", null, cv);
        return true;
    }

    public int removeChore(String billId){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("bills", "billId = ?", new String[] {billId});
    }

    public boolean updateBill(String billId, String title, String desc, String requestUser, String assignedUser, String isComplete, String totalPaid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("billId", billId);
        cv.put("title", title);
        cv.put("desc", desc);
        cv.put("requestUser", requestUser);
        cv.put("assignedUser", assignedUser);
        cv.put("iscomplete", isComplete);
        cv.put("totalPaid", totalPaid);
        db.update("bills", cv, "billId = ?", new String[]{billId});
        return true;
    }

    public Cursor getBillData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from bills where billId=" + id + "", null);
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean billIsPaid(String billId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("billId", billId);
        cv.put("iscomplete", "true");
        db.update("bills", cv, "billId = ?", new String[]{billId});
        return true;
    }

    public ArrayList<String> getAllUsers(){
        ArrayList<String> newArr = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from chores", null);
        res.moveToFirst();
        while(res.isAfterLast() == false){
            newArr.add(res.getString(res.getColumnIndex(BILL_ID)));
        }
        return newArr;
    }
}
