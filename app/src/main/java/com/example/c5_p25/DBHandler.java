package com.example.c5_p25;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "friendlistDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_FRIENDS = "friend";
    private static final String ID = "id";
    private static final String FIRSTNAME_COL = "firstname";
    private static final String LASTNAME_COL = "lastname";
    private static final String EMAIL_COL = "email";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreate = "create table " + TABLE_FRIENDS + "( " + ID
                + " integer primary key autoincrement, "
                + FIRSTNAME_COL + " TEXT,"
                + LASTNAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT)";

        sqLiteDatabase.execSQL( sqlCreate );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_FRIENDS );
        onCreate(sqLiteDatabase);
    }
    public void insert( Friend friend ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlInsert = "insert into " + TABLE_FRIENDS;
        sqlInsert += " values( null, '" + friend.getFirstName();
        sqlInsert += "', '" + friend.getLastName()+ "', '" + friend.getEmailAddress() + "' )";

        db.execSQL( sqlInsert );

        db.close( );
    }
    public String searchByEmail (String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String result = "";
        String sqlGet = "SELECT * FROM " + TABLE_FRIENDS + " WHERE "
                + EMAIL_COL + "='" + email +"'";
        Cursor cursor = db.rawQuery(sqlGet, null);
        if (cursor.moveToFirst()) {
            result += cursor.getString(1);
            result += " ";
            result += cursor.getString(2);
        }

        return result;

    }
    public void deleteById( int id ) {
        SQLiteDatabase db = this.getWritableDatabase( );
        String sqlDelete = "delete from " + TABLE_FRIENDS;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL( sqlDelete );
        db.close( );
    }
    public void updateById( int id, String firstname, String lastname, String emailaddress ) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_FRIENDS;
        sqlUpdate += " set " + FIRSTNAME_COL + " = '" + firstname + "', ";
        sqlUpdate += LASTNAME_COL+ " = '" + lastname + "', ";
        sqlUpdate += EMAIL_COL + " = '" + emailaddress + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL( sqlUpdate );
        db.close( );
    }
    public ArrayList<Friend> GetAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from " + TABLE_FRIENDS;
        Cursor cursor = db.rawQuery( sqlQuery, null );
        ArrayList<Friend> friends = new ArrayList<Friend>( );
        while( cursor.moveToNext( ) ) {
            Friend currentFriend
                    = new Friend( Integer.parseInt( cursor.getString( 0 ) ),
                    cursor.getString( 1 ), cursor.getString( 2 ), cursor.getString(3) );
            friends.add( currentFriend );
        }
        db.close( );
        return friends;
    }
    public String[] GetAllEmails(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from " + TABLE_FRIENDS;
        Cursor cursor = db.rawQuery( sqlQuery, null );
        String [] emails = new String[cursor.getCount()];
        int i = 0;
        while(cursor.moveToNext()){
            String current = cursor.getString(3);
            emails[i] = current;
        }
        return emails;
    }
    @SuppressLint("Range")
    public ArrayList<HashMap<String,String>> GetAllInHash(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> userList = new ArrayList<>();
        String query = "SELECT id, firstname, lastname, email FROM "+ TABLE_FRIENDS;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            String sid = String.valueOf(cursor.getInt(cursor.getColumnIndex(ID)));
            user.put("id", sid);
            user.put("firstname",cursor.getString(cursor.getColumnIndex(FIRSTNAME_COL)));
            user.put("lastname",cursor.getString(cursor.getColumnIndex(LASTNAME_COL)));
            user.put("email",cursor.getString(cursor.getColumnIndex(EMAIL_COL)));
            userList.add(user);
        }
        return  userList;
    }



}
