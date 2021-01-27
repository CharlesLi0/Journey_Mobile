package com.example.journeyMobile.service.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class PlanDBHelper extends SQLiteOpenHelper{

    private static final String DATEBASE_NAME = "plan_db";
    private static final int DATEBASE_VERSION = 1;

    /**
     * for the table of the database
     */
    private static final String CREATE_TABLE = "create table " + PlanContract.PlanEntry.TABLE_NAME
            + "("
            + PlanContract.PlanEntry.DATE_TIME + " text PRIMARY KEY,"
            + PlanContract.PlanEntry.PLAN_TITLE + " text" + ","
            + PlanContract.PlanEntry.PLAN_STARTDATE + " date"
            + ");";

    /**
     * for drop the table
     */
    public static final String DROP_TABLE = "drop table if exists " + PlanContract.PlanEntry.TABLE_NAME;

    public PlanDBHelper(Context context) {
        super(context, DATEBASE_NAME, null, DATEBASE_VERSION);
        Log.d("Database Operations", "Database created...." );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d("Database Operations", "Table created....." );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     *  add a row to the database
     * @param database database
     * @param id id
     * @param title title
     * @param startDate startDate
     * @return true for successful, false for unsuccessful
     */
    public boolean add(SQLiteDatabase database, Integer id,String title,String startDate) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(PlanContract.PlanEntry.DATE_TIME, id);
        contentValues.put(PlanContract.PlanEntry.PLAN_TITLE, title);
        contentValues.put(PlanContract.PlanEntry.PLAN_STARTDATE, startDate);

        long insert = database.insert(PlanContract.PlanEntry.TABLE_NAME,null,contentValues);
        Log.d("Database Operations", "One Row inserted...." );

        if (insert == -1){
            Log.d("Database Operations", "One Row event_title:" + title + " inserted false..." );
            return false;

        } else
            Log.d("Database Operations", "One Row event_title:" + title + " inserted succeed..." );
        return true;
    }

    /**
     * serach in the database
     * @param database database
     * @param id id
     * @return true for having id, false for no id
     */
    public boolean searchId(SQLiteDatabase database, Integer id){
        String selectionArgs = "where " + PlanContract.PlanEntry.DATE_TIME + " = " + id;
        Cursor cursor = database.rawQuery("select * from " + PlanContract.PlanEntry.TABLE_NAME
                + " where " + PlanContract.PlanEntry.DATE_TIME + " = ?", new String[] {id+""});

        while (cursor.moveToNext()){

            return  true;
        }

        return false;
    }

    /**
     * read form database
     * @param database database
     * @return Cursor with data
     */
    public Cursor read(SQLiteDatabase database) {

        String[] projections = {PlanContract.PlanEntry.DATE_TIME,
                PlanContract.PlanEntry.PLAN_TITLE,
                PlanContract.PlanEntry.PLAN_STARTDATE
        };

        Cursor cursor = database.query(PlanContract.PlanEntry.TABLE_NAME, projections, null,
                null, null, null, null);

        return cursor;
    }

    /**
     * undata  base on the id
     * @param database database
     * @param id id
     * @param title title
     * @param startDate start date
     * @param endDate endDate
     * @param venue venue
     * @param location location
     * @param attendees attendees
     * @param movieTitle movieTitle
     * @return true for successful, false for unsuccessful
     */
    public boolean update(SQLiteDatabase database, Integer id,String title,String startDate,String endDate
            ,String venue,String location,String attendees, String movieTitle, boolean isNotify) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(PlanContract.PlanEntry.PLAN_TITLE, title);
        contentValues.put(PlanContract.PlanEntry.PLAN_STARTDATE, startDate);


        String selection = PlanContract.PlanEntry.DATE_TIME + " = " + id;

        int update = database.update(PlanContract.PlanEntry.TABLE_NAME,contentValues,selection,null);

        if (update == -1){
            Log.d("Database Operations", "One Row event_ID:" + id + " update false..." );
            return false;

        } else
            Log.d("Database Operations", "One Row event_ID:" + id + " update succeed..." );
        return true;
    }

    /**
     * delete base on id
     * @param database database
     * @param id id
     * @return true for successful, false for unsuccessful
     */
    public boolean delete(SQLiteDatabase database, Integer id) {

        String selection = PlanContract.PlanEntry.DATE_TIME+ " = " + id;
        int delete = database.delete(PlanContract.PlanEntry.TABLE_NAME,selection,null);

        if (delete == -1){
            Log.d("Database Operations", "One Row event_ID:" + id + " update false..." );
            return false;

        } else
            Log.d("Database Operations", "One Row event_ID:" + id + " update succeed..." );
        return true;
    }
}
