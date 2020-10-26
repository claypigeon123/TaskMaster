package com.c.taskmaster.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TM_TODO_DB";

    // Table 1
    private static final String TODO_TABLE = "TODOS";
    // Columns
    private static final String TODO_COL_1 = "ID";
    private static final String TODO_COL_2 = "NAME";
    private static final String TODO_COL_3 = "DESCRIPTION";
    private static final String TODO_COL_4 = "EXP";
    private static final String TODO_COL_5 = "GOLD";
    private static final String TODO_COL_6 = "PROF_ID";
    private static final String TODO_COL_7 = "IMPORTANCE";

    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" +
            TODO_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TODO_COL_2 + " TEXT, " +
            TODO_COL_3 + " TEXT, " +
            TODO_COL_4 + " INTEGER, " +
            TODO_COL_5 + " INTEGER, " +
            TODO_COL_6 + " INTEGER, " +
            TODO_COL_7 + " INTEGER " +
            ")";

    // Table 2
    private static final String PROF_TABLE = "PROFILE";
    // Columns
    private static final String PROF_COL_1 = "ID";
    private static final String PROF_COL_2 = "NAME";
    private static final String PROF_COL_3 = "EXP";
    private static final String PROF_COL_4 = "GOLD";
    private static final String PROF_COL_5 = "IMG";
    private static final String PROF_COL_6 = "THEME";

    private static final String CREATE_PROF_TABLE = "CREATE TABLE " + PROF_TABLE + "(" +
            PROF_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PROF_COL_2 + " TEXT, " +
            PROF_COL_3 + " INTEGER, " +
            PROF_COL_4 + " INTEGER, " +
            PROF_COL_5 + " INTEGER, " +
            PROF_COL_6 + " INTEGER " +
            ")";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TODO_TABLE);
        db.execSQL(CREATE_PROF_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PROF_TABLE);

        onCreate(db);
    }

    // TODOS OPERATIONS
    public boolean insertTodo(String name, String description, int exp, int gold, String profId, int importance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(TODO_COL_2, name);
        content.put(TODO_COL_3, description);
        content.put(TODO_COL_4, exp);
        content.put(TODO_COL_5, gold);
        content.put(TODO_COL_6, profId);
        content.put(TODO_COL_7, importance);

        long result = db.insert(TODO_TABLE, null, content);
        return result != -1;
    }

    public boolean updateTodo(String id, String name, String description, int exp, int gold, String profId, int importance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(TODO_COL_1, id);
        content.put(TODO_COL_2, name);
        content.put(TODO_COL_3, description);
        content.put(TODO_COL_4, exp);
        content.put(TODO_COL_5, gold);
        content.put(TODO_COL_6, profId);
        content.put(TODO_COL_7, importance);

        long result = db.update(TODO_TABLE, content, "ID=?", new String[]{id});
        return result != -1;
    }

    public boolean deleteTodo(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TODO_TABLE, "ID=?", new String[]{id});
        return result != -1;
    }

    public Cursor viewAllTodos(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TODO_TABLE + " WHERE PROF_ID = " + id;

        return db.rawQuery(query, null);
    }

    // PROF OPERATIONS
    public boolean insertProf(String name, int exp, int gold, int pic, int theme) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(PROF_COL_2, name);
        content.put(PROF_COL_3, exp);
        content.put(PROF_COL_4, gold);
        content.put(PROF_COL_5, pic);
        content.put(PROF_COL_6, theme);

        long result = db.insert(PROF_TABLE, null, content);
        return result != -1;
    }

    public boolean updateProf(String id, String name, int exp, int gold, int pic, int theme) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put(PROF_COL_1, id);
        content.put(PROF_COL_2, name);
        content.put(PROF_COL_3, exp);
        content.put(PROF_COL_4, gold);
        content.put(PROF_COL_5, pic);
        content.put(PROF_COL_6, theme);

        long result = db.update(PROF_TABLE, content, "ID=?", new String[]{id});
        return result != -1;
    }

    public boolean deleteProf(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(PROF_TABLE, "ID=?", new String[]{id});
        return result != -1;
    }

    public Cursor viewAllProfs() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + PROF_TABLE;

        return db.rawQuery(query, null);
    }
}
