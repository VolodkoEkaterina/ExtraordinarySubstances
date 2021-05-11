package com.example.extraordinarysubstances;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBQuestion {
    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tableQuestions";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUESTIONTEXT = "QuestionText";
    private static final String COLUMN_ANSWER = "Answer";
    private static final String COLUMN_ANSW1 = "Answ1";
    private static final String COLUMN_ANSW2 = "Answ2";
    private static final String COLUMN_ANSW3 = "Answ3";
    private static final String COLUMN_ANSW4 = "Answ4";


    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_QUESTIONTEXT = 1;
    private static final int NUM_COLUMN_ANSWER = 2;
    private static final int NUM_COLUMN_ANSW1 = 3;
    private static final int NUM_COLUMN_ANSW2 = 4;
    private static final int NUM_COLUMN_ANSW3 = 5;
    private static final int NUM_COLUMN_ANSW4 = 6;

    private SQLiteDatabase mDataBase;

    public DBQuestion(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }

    public long insert(String questionText,String answer,String answ1,String answ2,String answ3,String answ4) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_QUESTIONTEXT, questionText);
        cv.put(COLUMN_ANSWER, answer);
        cv.put(COLUMN_ANSW1, answ1);
        cv.put(COLUMN_ANSW2,answ2);
        cv.put(COLUMN_ANSW3,answ3);
        cv.put(COLUMN_ANSW4,answ4);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Question md) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_QUESTIONTEXT, md.getQuestionText());
        cv.put(COLUMN_ANSWER, md.getAnswer());
        cv.put(COLUMN_ANSW1, md.getAnsw1());
        cv.put(COLUMN_ANSW2,md.getAnsw2());
        cv.put(COLUMN_ANSW3, md.getAnsw3());
        cv.put(COLUMN_ANSW4,md.getAnsw4());

        return mDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(md.getId())});
    }

    public void deleteAll() {
        mDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        mDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Question select(long id) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        mCursor.moveToFirst();
        String QuestionText = mCursor.getString(NUM_COLUMN_QUESTIONTEXT);
        String Answer = mCursor.getString(NUM_COLUMN_ANSWER);
        String Answ1 = mCursor.getString(NUM_COLUMN_ANSW1);
        String Answ2 = mCursor.getString(NUM_COLUMN_ANSW2);
        String Answ3 = mCursor.getString(NUM_COLUMN_ANSW3);
        String Answ4 = mCursor.getString(NUM_COLUMN_ANSW4);

        return new Question(id, QuestionText, Answer, Answ1, Answ2, Answ3, Answ4);
    }

    public ArrayList<Question> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Question> arr = new ArrayList<Question>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String QuestionText = mCursor.getString(NUM_COLUMN_QUESTIONTEXT);
                String Answer = mCursor.getString(NUM_COLUMN_ANSWER);
                String Answ1 = mCursor.getString(NUM_COLUMN_ANSW1);
                String Answ2 = mCursor.getString(NUM_COLUMN_ANSW2);
                String Answ3 = mCursor.getString(NUM_COLUMN_ANSW3);
                String Answ4 = mCursor.getString(NUM_COLUMN_ANSW4);
                arr.add(new Question(id, QuestionText, Answer, Answ1, Answ2, Answ3, Answ4));
            } while (mCursor.moveToNext());
        }
        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_QUESTIONTEXT+ " TEXT, " +
                    COLUMN_ANSWER + " TEXT, " +
                    COLUMN_ANSW1 + " TEXT, " +
                    COLUMN_ANSW2 + " TEXT, " +
                    COLUMN_ANSW3 + " TEXT, " +
                    COLUMN_ANSW4 + " TEXT); ";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
