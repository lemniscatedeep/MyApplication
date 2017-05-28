package com.example.daman.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Daman on 3/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";

    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";

    private static final String TABLE_APPLICANT = "applicant";
    private static final String COLUMN_LOANPURPOSE = "loan";
    private static final String COLUMN_LOANAMOUNT = "amount";
    private static final String COLUMN_TENURE = "tenure";
    private static final String COLUMN_RESIDENCE = "residencetype";
    private static final String COLUMN_ADDRESS = "address";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null , " +
            "name text not null , email text not null , uname text not null , pass text not null);";
    //
    private static final String Table_2 = "create table applicant (id integer primary key autoincrement, name text not null, " +
            "loan text not null," +
            "amount int not null, tenure int not null, residencetype text not null, address text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(Table_2);
        this.db = db;
    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getPass());

//
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void insertApplicant(Contact c) {
        db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();

        newValues.put(COLUMN_NAME,c.getName());
//        newValues.put(COLUMN_EMAIL,c.getEmail());
        newValues.put(COLUMN_LOANPURPOSE, c.getLoan());
        newValues.put(COLUMN_LOANAMOUNT, c.getAmount());
        newValues.put(COLUMN_TENURE, c.getTenure());
        newValues.put(COLUMN_RESIDENCE, c.getResidencetype());
        newValues.put(COLUMN_ADDRESS, c.getAddress());
        db.insert(TABLE_APPLICANT, null, newValues);
        db.close();
    }

    public String searchPass(String uname) {
        db = this.getReadableDatabase();
        String query = "select uname, pass from " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(uname)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_NAME;
        db.execSQL(query);
        db.execSQL("DROP TABLE IF EXISTS TABLE_APPLICANT");
        onCreate(db);

    }

}