package com.pkp.todokeepingapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    Context context;

    private static final int VERSION = 1;
    private static final String DB_NAME = "todo";
    private static final String TABLE_NAME = "todo";

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String UPDATE_DATE = "update_date";
    private static final String FINISHED = "finished";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" ("
                 +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                 +TITLE+" TEXT,"
                 +DESCRIPTION+" TEXT,"
                 +STARTED+" TEXT,"
                 +UPDATE_DATE+" TEXT,"
                 +FINISHED+" TEXT"+
                 ");";
         db.execSQL(TABLE_CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old, int newV) {
        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+TABLE_NAME;
        // Drop order table if existed
        db.execSQL(DROP_TABLE_QUERY);
        // Create table again
        onCreate(db);
    }

    // save databases toDo
    public void addToDo(ToDo toDo){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(TITLE, toDo.getTitle());
        contentValues.put(DESCRIPTION, toDo.getDescription());
        contentValues.put(STARTED, toDo.getStarted());
        contentValues.put(UPDATE_DATE, toDo.getUpdate_date());
        contentValues.put(FINISHED, toDo.getFinished());

        // save to table

        long insert = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        Toast.makeText(context,"save : "+insert,Toast.LENGTH_LONG).show();
        // close database connection
        sqLiteDatabase.close();
    }

    //count Todo table record
    public int countToDo(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(sql, null);
        return cursor.getCount();
    }

    public List<ToDo> getAllToDos(){
        List<ToDo> toDos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do{
                toDos.add(new ToDo(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getLong(3),
                        cursor.getLong(4),
                        cursor.getLong(5)
                ));
            }while (cursor.moveToNext());
        }
        db.close();
        return toDos;
    }

    public void deleteToDo(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID+" =?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ToDo getSingleToDo(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE "+ID+"= ?", new String[]{String.valueOf(id)});
        ToDo toDo = null;
        if (cursor.moveToFirst()){
            toDo = new ToDo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4),
                    cursor.getLong(5)
            );
        }
        return toDo;
    }

    public boolean updateToDo(ToDo toDo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID, toDo.getId());
        contentValues.put(TITLE, toDo.getTitle());
        contentValues.put(DESCRIPTION, toDo.getDescription());
        contentValues.put(STARTED, toDo.getStarted());
        contentValues.put(UPDATE_DATE, toDo.getUpdate_date());
        contentValues.put(FINISHED, toDo.getFinished());

        return db.update(TABLE_NAME,
                contentValues,
                ID+" =?",
                new String[]{String.valueOf(toDo.getId())} )>0;
    }
}
