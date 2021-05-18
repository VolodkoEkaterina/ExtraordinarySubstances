package com.example.extraordinarysubstances;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBQuestion {
    private static final String DATABASE_NAME = "simple.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "tableQuestions";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_QUESTIONTITLE = "QuestionTitle";
    private static final String COLUMN_QUESTIONTEXT = "QuestionText";
    private static final String COLUMN_ANSWER = "Answer";
    private static final String COLUMN_ANSWERRIGHT = "AnswerRight";
    private static final String COLUMN_TYPE = "Type";
    private static final String COLUMN_TESTNAME = "TestName";


    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_QUESTIONTITLE = 1;
    private static final int NUM_COLUMN_QUESTIONTEXT = 2;
    private static final int NUM_COLUMN_ANSWER = 3;
    private static final int NUM_COLUMN_ANSWERRIGHT = 4;
    private static final int NUM_COLUMN_TYPE = 5;
    private static final int NUM_COLUMN_TESTNAME = 6;

    private SQLiteDatabase mDataBase;

    public DBQuestion(Context context) {
        OpenHelper mOpenHelper = new OpenHelper(context);
        mDataBase = mOpenHelper.getWritableDatabase();
    }


    public long insert(String questionTitle, String questionText, String answer, String answerRight, int type, String testName) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_QUESTIONTITLE, questionTitle);
        cv.put(COLUMN_QUESTIONTEXT, questionText);
        cv.put(COLUMN_ANSWER, answer);
        cv.put(COLUMN_ANSWERRIGHT, answerRight);
        cv.put(COLUMN_TYPE,type);
        cv.put(COLUMN_TESTNAME,testName);
        return mDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Question md) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_QUESTIONTITLE, md.getQuestionTitle());
        cv.put(COLUMN_QUESTIONTEXT, md.getQuestionText());
        cv.put(COLUMN_ANSWER, md.getAnswer());
        cv.put(COLUMN_ANSWERRIGHT, md.getAnswerRight());
        cv.put(COLUMN_TYPE,md.getType());
        cv.put(COLUMN_TESTNAME, md.getTestName());

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
        String QuestionTitle = mCursor.getString(NUM_COLUMN_QUESTIONTITLE);
        String QuestionText = mCursor.getString(NUM_COLUMN_QUESTIONTEXT);
        String Answer = mCursor.getString(NUM_COLUMN_ANSWER);
        String AnswerRight = mCursor.getString(NUM_COLUMN_ANSWERRIGHT);
        int Type = mCursor.getInt(NUM_COLUMN_TYPE);
        String TestName = mCursor.getString(NUM_COLUMN_TESTNAME);

        return new Question(id, QuestionTitle, QuestionText, Answer, AnswerRight, Type, TestName);
    }
    public ArrayList<Question> selectTest(String testName) {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, COLUMN_TESTNAME + " = ?", new String[]{String.valueOf(testName)}, null, null, null);
        ArrayList<Question> questions = new ArrayList<>();
        mCursor.moveToFirst();
        do { String QuestionTitle = mCursor.getString(NUM_COLUMN_QUESTIONTITLE); String QuestionText = mCursor.getString(NUM_COLUMN_QUESTIONTEXT);
        String Answer = mCursor.getString(NUM_COLUMN_ANSWER);
        String AnswerRight = mCursor.getString(NUM_COLUMN_ANSWERRIGHT);
        int Type = mCursor.getInt(NUM_COLUMN_TYPE);
        String TestName = mCursor.getString(NUM_COLUMN_TESTNAME);
        questions.add(new Question(QuestionTitle, QuestionText, Answer, AnswerRight, Type, TestName));
        }while (mCursor.moveToNext());

        return questions;
    }

    public ArrayList<Question> selectAll() {
        Cursor mCursor = mDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Question> arr = new ArrayList<Question>();
        mCursor.moveToFirst();
        if (!mCursor.isAfterLast()) {
            do {
                long id = mCursor.getLong(NUM_COLUMN_ID);
                String QuestionTitle = mCursor.getString(NUM_COLUMN_QUESTIONTITLE);
                String QuestionText = mCursor.getString(NUM_COLUMN_QUESTIONTEXT);
                String Answer = mCursor.getString(NUM_COLUMN_ANSWER);
                String AnswerRight = mCursor.getString(NUM_COLUMN_ANSWERRIGHT);
                int Type = mCursor.getInt(NUM_COLUMN_TYPE);
                String TestName = mCursor.getString(NUM_COLUMN_TESTNAME);
                arr.add(new Question(id,QuestionTitle, QuestionText, Answer, AnswerRight, Type, TestName));
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
                    COLUMN_QUESTIONTITLE+ " TEXT, " +
                    COLUMN_QUESTIONTEXT+ " TEXT, " +
                    COLUMN_ANSWER + " TEXT, " +
                    COLUMN_ANSWERRIGHT + " TEXT, " +
                    COLUMN_TYPE + " INT, " +
                    COLUMN_TESTNAME + " TEXT); " ;
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
