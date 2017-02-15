package com.example.gerges.sqltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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




    }
}
