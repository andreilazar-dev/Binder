package com.binder.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    //TAG FOR LOG
    private static final String TAG = "DatabaseHelper";

    private static String DB_PATH ; //="/data/data/com.binder/databases/";
    private static String DB_NAME = "dbmonitoring.db";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase mDataBase = null;
    private final Context ctx;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.d("DatabaseFunction","Inizializzed DB");

        //Metodo piú ellegante per dire /data/data/com.binder/databases/
        DB_PATH="/data/data/" + context.getPackageName() + "/" + "databases/";

        this.ctx = context;
    }

    //Check if database exist before create it
    public void createDataBase() throws IOException{

        Log.d(TAG,"createDatabase - start");
        boolean dbExist = checkDataBase();


        if(dbExist)
        {
            Log.v("DB Exists", "db exists");
            // By calling this method here onUpgrade will be called on a
            // writeable database, but only if the version number has been
            // bumped
            // onUpgrade(myDataBase, DATABASE_VERSION_old, DATABASE_VERSION);
        }

        boolean dbExist1 = checkDataBase();

        if(!dbExist1)
        {
            this.getReadableDatabase();
            try
            {
                this.close();
                copyDataBase();
            }
            catch (IOException e)
            {
                throw new Error("Error copying database");
            }
        }

    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;

        Log.d(TAG,"CheckDataBase - start");
/*
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){
            //database does't exist yet.
        }

        if(checkDB != null){
            checkDB.close();
        }

        return checkDB != null;

 */
        File databaseFile = new File(DB_PATH + DB_NAME);
        return databaseFile.exists();
    }


    boolean openDataBase() throws SQLException {
        Log.d("DatabaseFunction","Open DB");
        //mDataBase = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //return mDataBase != null;
        String mPath = DB_PATH + DB_NAME;
        //Log.v("mPath", mPath);
        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return mDataBase != null;
    }

    public synchronized void close(){
        Log.d("DatabaseFunction","Close DB");
        if(mDataBase != null)
            mDataBase.close();
       // SQLiteDatabase.releaseMemory();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    //_______________________________________________________________________________________________

    //copy database file from assets folder to device archive
    private void copyDataBase() throws IOException {
        Log.v(TAG, "copyDataBase() - start");
        try{
        //Open your local db as the input stream
        InputStream mInput =  ctx.getAssets().open("database/"+DB_NAME);
        // Path to the just created empty db
        String outfileName = DB_PATH + DB_NAME;
        //Open the empty db as the output stream
        OutputStream mOutput = new FileOutputStream(outfileName);
        byte[] buffer = new byte[1024];
        //transfer bytes from the input file to the output file
        int mLength;
        while ((mLength = mInput.read(buffer))>0) {
            mOutput.write(buffer, 0, mLength);
        }
        //Close the streams
        mOutput.flush();
        mInput.close();
        mOutput.close();

        //<<<<<<<<<<<NEEEEEEEEEEDDD RE WATCH
        } catch (Exception e) {
            Log.e(TAG, "copyDataBase(): " + e);

            StackTraceElement trace[] = e.getStackTrace();
            for(StackTraceElement element : trace) {
                Log.e(TAG, element.toString());

            }
        }
        Log.v(TAG, "copyDataBase() - end");
    }



}