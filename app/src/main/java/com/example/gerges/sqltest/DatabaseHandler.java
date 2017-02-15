package com.example.gerges.sqltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerges on 2/15/2017.
 *
 *
 */

public class DatabaseHandler extends SQLiteOpenHelper {

     // database Version
      private static final  int DATABASE_VERSION = 1;
     // database name
    private static final String DATABASE_NAME = "contactsManager";
    // table name
    private static  final String TABLE_NAME = "contacts";

    // columns Name
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_NUMBER = "number";

     // inherited from SQLiteOpenHelper   Constractor that require CONTEXT , DATABASE NAME , DATABASE VERSION
    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);

    }


    // creating table
    @Override
    public void onCreate(SQLiteDatabase db) {

        // sqlLite Query for creating the table columns

        String CREATE_CONTACTS_TABLE =
                "CREATE TABLE " +
                TABLE_NAME +
                "("+
                        KEY_ID + " INTEGER PRIMARY KEY," +
                        KEY_NAME + "TEXT, " +
                        KEY_NUMBER + "TEXT"
                  + ")";

        // execute the query
        db.execSQL(CREATE_CONTACTS_TABLE);

    }
   // upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older Table , if Exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );

        // Then Create new Table
        onCreate(db);

    }





    /*
    *
    *
    *
    * ************************************************************* *
    *                                                               *
    *    ALL CRUD OPERATIONS ( CREATE , READ , UPDATE , DELETE )    *
    *                                                               *
    * ************************************************************* *
    *
    *
    *
    */

    // Adding new contact
    public void addContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName()); // contact Name
        values.put(KEY_NUMBER, contact.getNumber()); // contact Number

        // inserting Row

        db.insert(TABLE_NAME, null, values );
        db.close();

    }

    // Getting single contact
    public Contact getContact(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                   KEY_ID, KEY_NAME, KEY_NUMBER
                },
                KEY_ID + "=?",
                new String[]{
                    String.valueOf(id)
                },
                null , null, null, null);

         if(cursor!= null){
             cursor.moveToFirst();
        }
        Contact contact =new Contact();
            contact.setId(Integer.parseInt(cursor.getString(0)));
            contact.setName(cursor.getString(1));
            contact.setNumber(cursor.getString(2));

        return contact;



    }

    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();

        // select All Query

        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        // looping throw all Rows  and Adding to list

        if(cursor.moveToFirst()){
            do{
                  Contact contact = new Contact();

                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setName(cursor.getString(1));
                    contact.setNumber(cursor.getString(2));

                contactList.add(contact);

            }while(cursor.moveToNext());
        }

         return contactList;

    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }



    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.getName()); // contact Name
        values.put(KEY_NUMBER, contact.getNumber()); // contact Number
        return db.update(
                TABLE_NAME,
                values,
                KEY_ID + "= ?",
                new String[]{
                   String.valueOf(contact.getId())
                }
        );
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(
                TABLE_NAME,
                KEY_ID + "= ?",
                new String[]{
                        String.valueOf(contact.getId())
                }
        );

        db.close();

    }
}
