package sg.edu.rp.c346.sosiandowhat;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.id;

/**
 * Created by 15017608 on 11/1/2017.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME_DRY = "dryAct.db";
    private static final String TABLE_DRY = "dryAct";
    private static final String COLUMN_ID_DRY = "id";
    private static final String DRY_ACT_TITLE = "dryActTitle";

    private static final String TABLE_DRY_GOOD = "dryActGood";
    private static final String COLUMN_ID_DRY_GOOD = "dry_id_good";
    private static final String DRY_ACT_TITLE_GOOD = "dryActTitleGood";

    private static final String TABLE_WET = "wetAct";
    private static final String COLUMN_ID_WET = "wet_id";
    private static final String WET_ACT_TITLE = "wetActTitle";

    private static final String TABLE_WET_GOOD = "wetActGood";
    private static final String COLUMN_ID_WET_GOOD = "wet_id_good";
    private static final String WET_ACT_TITLE_GOOD = "wetActTitleGood";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME_DRY, null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSqlDry = "CREATE TABLE " + TABLE_DRY +  "("
                + COLUMN_ID_DRY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DRY_ACT_TITLE + " TEXT)";

        db.execSQL(createTableSqlDry);
        Log.i("info" ,"created drryTable tables");

        String createTableSqlDryGood = "CREATE TABLE " + TABLE_DRY_GOOD +  "("
                + COLUMN_ID_DRY_GOOD + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DRY_ACT_TITLE_GOOD + " TEXT)";

        db.execSQL(createTableSqlDryGood);
        Log.i("info" ,"created drryGood tables");


        String createTableSqlWet = "CREATE TABLE " + TABLE_WET +  "("
                + COLUMN_ID_WET + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WET_ACT_TITLE + " TEXT)";

        db.execSQL(createTableSqlWet);
        Log.i("info" ,"created wet tables");

        String createTableSqlWetGood = "CREATE TABLE " + TABLE_WET_GOOD +  "("
                + COLUMN_ID_WET_GOOD + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WET_ACT_TITLE_GOOD + " TEXT)";

        db.execSQL(createTableSqlWetGood);
        Log.i("info" ,"created wetGood tables");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRY);
        db.execSQL("DROP TABLE IF EXIXTS " + TABLE_DRY_GOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WET_GOOD);
        // Create table(s) again
        onCreate(db);

    }
    public void insertDry(String title) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(DRY_ACT_TITLE, title);
        // Store the column name as key and the date as value
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_DRY, null, values);
        // Close the database connection
    }

    public void insertDryGood(String title) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(DRY_ACT_TITLE_GOOD, title);
        // Store the column name as key and the date as value
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_DRY_GOOD, null, values);
        // Close the database connection
        db.close();
    }

    public void insertWet(String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(WET_ACT_TITLE, title);
        // Store the column name as key and the date as value
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_WET, null, values);
        // Close the database connection
    }

    public void insertWetGood(String title) {
        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(WET_ACT_TITLE_GOOD, title);
        // Store the column name as key and the date as value
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_WET_GOOD, null, values);
        // Close the database connection
    }

    public ArrayList<DryItem> getDry() {
        ArrayList<DryItem> dryList = new ArrayList<DryItem>();
        String selectQuery = "SELECT " + COLUMN_ID_DRY + " ,"
                + DRY_ACT_TITLE
                + " FROM " + TABLE_DRY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String itemDry = cursor.getString(1);
                DryItem obj = new DryItem(id, itemDry);
                dryList.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dryList;
    }

    public ArrayList<DryItemGood> getDryGood() {
        ArrayList<DryItemGood> dryListGood = new ArrayList<DryItemGood>();
        String selectQuery = "SELECT " + COLUMN_ID_DRY_GOOD + " ,"
                + DRY_ACT_TITLE_GOOD
                + " FROM " + TABLE_DRY_GOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String itemDryGood = cursor.getString(1);
                DryItemGood obj = new DryItemGood(id, itemDryGood);
                dryListGood.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return dryListGood;
    }

    public ArrayList<WetItem> getWet() {
        ArrayList<WetItem> wetList = new ArrayList<WetItem>();
        String selectQuery = "SELECT " + COLUMN_ID_WET + " ,"
                + WET_ACT_TITLE
                + " FROM " + TABLE_WET;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String itemDry = cursor.getString(1);
                WetItem obj = new WetItem(id, itemDry);
                wetList.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wetList;
    }

    public ArrayList<WetItemGood> getWetGood() {
        ArrayList<WetItemGood> wetListGood = new ArrayList<WetItemGood>();
        String selectQuery = "SELECT " + COLUMN_ID_WET_GOOD + " ,"
                + WET_ACT_TITLE_GOOD
                + " FROM " + TABLE_WET_GOOD;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String itemWetGood = cursor.getString(1);
                WetItemGood obj = new WetItemGood(id, itemWetGood);
                wetListGood.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wetListGood;
    }


//    public int updateItem(DryItem dryItem){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(DRY_ACT_TITLE, dryItem.getDryTitle());
//        values.put(WET_ACT_TITLE, item.getWetTitle());
//        String condition = COLUMN_ID_DRY + "= ?";
//        String[] args = {String.valueOf(dryItem.getId())};
//        int result = db.update(TABLE_DRY, values, condition, args);
//        db.close();
//        return result;
//    }

    public int deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID_DRY + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_DRY, condition, args);
        db.close();
        return result;
    }

    public int deleteItemDryGood(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID_DRY_GOOD + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_DRY_GOOD, condition, args);
        db.close();
        return result;
    }

    public int deleteItemWet(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID_WET + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_WET, condition, args);
        db.close();
        return result;
    }

    public int deleteItemWetGood(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID_WET_GOOD + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_WET_GOOD, condition, args);
        db.close();
        return result;
    }
}
