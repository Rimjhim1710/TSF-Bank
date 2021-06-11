package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table"; 
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(7544567890,'Rashi',5472.00,'rashisingh.02@gmail.com','XXXXXXXXXXXX6432','NGB589394950')");
        db.execSQL("insert into user_table values(4658313789,'Mansi',8082.67,'mansi.03@gmail.com','XXXXXXXXXXXX8653','ASA96836722')");
        db.execSQL("insert into user_table values(7633589432,'Tina',1349.56,'tinasharma@gmail.com','XXXXXXXXXXXX9752','THB57393029')");
        db.execSQL("insert into user_table values(9472638486,'Rahul',1500.01,'Rajput.rahul05@gmail.com','XXXXXXXXXXXX1357','FIJ68747483')");
        db.execSQL("insert into user_table values(2948694293,'Aakash',2653.48,'theaakash@gmail.com','XXXXXXXXXXXX8654','NSM63749593')");
        db.execSQL("insert into user_table values(1928486900,'Priya',9456.00,'priyass@gmail.com','XXXXXXXXXXXX6424','FJW38598395')");
        db.execSQL("insert into user_table values(6938939495,'Abhishek',5936.76,'abhi.08@gmail.com','XXXXXXXXXXXX0394','KTL68490328')");
        db.execSQL("insert into user_table values(3049584839,'Anjali',8572.45,'anjaliii.09@gmail.com','XXXXXXXXXXXX1938','MDU78463738')");
        db.execSQL("insert into user_table values(8382394984,'Rimjhim',4396.87,'rimjhim17@gmail.com','XXXXXXXXXXXX9684','ALO68390624')");
        db.execSQL("insert into user_table values(5038372734,'Vidhi',6730.32,'vidhigupta@gmail.com','XXXXXXXXXXXX6392','ABC76493939')");
    
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
