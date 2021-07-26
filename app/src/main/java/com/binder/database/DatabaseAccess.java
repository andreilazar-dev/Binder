package com.binder.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.binder.database.datamodel.User;

import java.io.IOException;

public class DatabaseAccess {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;

    Cursor c =null;

    //TAG FOR LOG
    private static final String TAG = "DatabaseAccess";

    private DatabaseAccess(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    //to return the single instance of database

    public  static DatabaseAccess getInstance(Context context){
        if (instance== null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }
        public DatabaseAccess createDatabase() throws SQLException {
        try {
            dbHelper.createDataBase();
        } catch (IOException ignored) {
        }
        return this;
    }

    public DatabaseAccess open() throws SQLException {
        try {
            dbHelper.openDataBase();
            //dbHelper.close();
            db = dbHelper.getReadableDatabase();
        } catch (SQLException ignored) {
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }




    // E un mio test lasciamo stare per ora

    //now lets create a method to querry and return the result from database

    //we will querry for user by passing id and pass

    public User getLogin(String id , String pass){

        Log.v(TAG,"Querry getLogin --Start ");

        String queryString = "SELECT * FROM " + "utenti";
        c=db.rawQuery(queryString,null);
        User credenziali = null;
        if(c.moveToFirst()){
            //loop through the cursor (result set) and create new customer object.put them in to the return list.
            do{
                if ((id.equals(c.getString(0)) && pass.equals(c.getString(1))))
                {

                    Log.v(TAG ,  "dentro la querry "+"valore in---> " + id +"valore out ---> "+ c.getString(0));
                    String iddb = c.getString(0);
                    String passworddb = c.getString(1);
                    String nomedb = c.getString(2);
                    String flagdb = c.getString(3);
                    String datadb = c.getString(4);
                    credenziali = new User(iddb, passworddb, nomedb, flagdb, datadb);
                    break;
                }
            }while (c.moveToNext());
        }
        else{
            //failure, do not add anything  to the list.
        }
        //close both the cursor and the db when done.

        c.close();
        db.close();
        return credenziali;
    }
}

