package com.example.gerges.sqltest;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /*
    to read or write data to sqlliet DB  Steps

      - define Schema of your DB  (Contract Class implements BaseColumns)

      - Create DB  by SQL handler  ( sql query to create entries )



     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DatabaseHandler db = new DatabaseHandler(this);

        /// using CRUD OPERATIONS  in database Handler

       Log.d("insert: ", "Inserting.." );
        db.addContact(new Contact(0,"Ravi", "9100000000"));
        db.addContact(new Contact(1,"Srinivas", "9199999999"));
        db.addContact(new Contact(2,"Tommy", "9522222222"));
        db.addContact(new Contact(3,"Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");

        List<Contact> contacts = db.getAllContacts();

        for (Contact cn : contacts) {
            String log = "Id: "+ cn.getId()+" ,Name: " + cn.getName() + " ,Phone: " + cn.getNumber();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }







    }
}
