package com.example.gerges.sqltest;

/**
 * Created by gerges on 2/15/2017.
 */

public class Contact {
    // private vars for items in table
    int id ;
    String name ;
    String number;



    // constractor

/*    public Contact(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }



}
