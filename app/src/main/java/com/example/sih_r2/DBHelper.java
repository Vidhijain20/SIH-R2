package com.example.sih_r2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public class DBHelper extends SQLiteOpenHelper {

    static final String TABLE_NAME = "IssueTable";
    static final String KEY_ROWID = "_id";
    static final String KEY_TITLE = "Title";
    static final String KEY_DESC = "Description";

    static final String KEY_STATUS = "Status";

    static final String KEY_RESOLUTION = "Resolution";
    static final String KEY_URL = "URL";

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "IssuesDatabase";

    private static SQLiteDatabase DB;

    private String TAG = "DBHelper";

    private static String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+KEY_ROWID+" integer PRIMARY KEY,"+KEY_TITLE+", "+KEY_DESC+", "+KEY_STATUS+", "+KEY_RESOLUTION+", "+KEY_URL+" BLOB);";

    DBHelper (Context context)
    {
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "Table Created Successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    void insertValues(String title, String desc, String status, byte[] image_url)
    {
        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, title);
        values.put(KEY_DESC, desc);
        values.put(KEY_STATUS, status);
        values.put(KEY_URL, image_url);

        DB = this.getWritableDatabase();

        DB.insert(TABLE_NAME, null, values);

        Log.d(TAG, "Values Inserted");
    }

    public void deleteIssue(int deleteID)
    {
        String rowID = Integer.toString(deleteID);

        DB = this.getWritableDatabase();

        DB.delete(TABLE_NAME, KEY_ROWID+" = "+rowID, null);

        DB.execSQL("UPDATE " + TABLE_NAME + " set " + KEY_ROWID + "= (" + KEY_ROWID + "-1) where "+ KEY_ROWID+" > "+deleteID);

        Log.d(TAG, "Delete Successful.");
    }

    public ArrayList<IssueModel> readPendingQueries()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<IssueModel> issueModels = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                if (Objects.equals(cursor.getString(3), "0")) {
                    issueModels.add(new IssueModel(cursor.getString(1), cursor.getString(2), cursor.getBlob(5)));
                }
            }while(cursor.moveToNext());
        }

        cursor.close();
        return issueModels;
    }

    public ArrayList<IssueModel> readResolvedQueries()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<IssueModel> issueModels = new ArrayList<>();

        if (cursor.moveToFirst()){
            do {
                if (Objects.equals(cursor.getString(3), "1")) {
                    issueModels.add(new IssueModel(cursor.getString(1), cursor.getString(2), cursor.getBlob(5)));
                }
            }while(cursor.moveToNext());
        }

        cursor.close();
        return issueModels;
    }
}
