package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DBManager {

    private DatabaseHelper dbHelper;
    byte[] imageAsBytes;
    private Context context;

    private static SQLiteDatabase database;



    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper=new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String mob,String email,byte[] image) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.MOB, mob);
        contentValue.put(DatabaseHelper.EMAIL, email);
        contentValue.put(DatabaseHelper.IMAGE, image);


        ContentValues contentValues = new ContentValues();
        contentValues.put("email", "MyImage");
        contentValues.put("image", imageAsBytes);
//
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public static Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.NAME, DatabaseHelper.MOB, DatabaseHelper.EMAIL, DatabaseHelper.IMAGE };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;

    }



    public int update(int _id, String name, String mob, String email, byte[] image) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, name);
        contentValues.put(DatabaseHelper.MOB, mob);
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.IMAGE, image);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(int _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }


    public Boolean checkusernamepassword(String username,String password)
    {
        Cursor cursor=database.rawQuery("select email,mob from regi where email=? and mob=?", new String[]{username,password});

        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

