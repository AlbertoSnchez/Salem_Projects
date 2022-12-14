package com.example.lab5a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "lab5a";
    private static final String TABLE_LIST = "list";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_IS_URGENT = "isUrgent";

    public DataBaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LIST_TABLE = "CREATE TABLE " + TABLE_LIST +
                "( " + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " +
                KEY_IS_URGENT + " INTEGER "+")";
        sqLiteDatabase.execSQL(CREATE_LIST_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST);
        onCreate(sqLiteDatabase);
    }

    public void addAct (Task todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, todo.getName());
        values.put(KEY_IS_URGENT, todo.getUrgent());
        db.insert(TABLE_LIST,null,values);
        db.close();
    }

    public void deleteAct (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIST,KEY_ID + " =? ", new String[]{String.valueOf(id)});
        db.close();
        Cursor cur;
    }

    public ArrayList<Task> show (){
        ArrayList<Task> list = new ArrayList<>();
        String select = "SELECT * FROM " + TABLE_LIST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(select,null);
        if (c.moveToNext()){
            do {
                Task task = new Task(c.getInt(0),c.getString(1), c.getInt(2));
                list.add(task);
            } while (c.moveToNext());
        }
        return list;
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public static String getTableList() {
        return TABLE_LIST;
    }
}
