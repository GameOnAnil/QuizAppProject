package com.example.quizproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizproject.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizProject.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";


        final String SQL_CREATE_QUESTIONS_TABLE2 = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME2 + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE2);
        fillQuestionsTable();
        fillQuestionsTable2();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME2);
        onCreate(db);
    }


    //FOR TABLE 1
    private void fillQuestionsTable() {
        Question q1 = new Question("What is 2 * 2", "4", "2", "0", 1);
        addQuestion(q1);
        Question q2 = new Question("15/5+27", "Non of the below", "5", "30", 3);
        addQuestion(q2);
        Question q3 = new Question("Are you boored", "Yes", "I am having a blast", "No", 2);
        addQuestion(q3);
        Question q4 = new Question("Do you like anime", "Hell yeah", "Little bit", "Nah", 1);
        addQuestion(q4);
        Question q5 =  new Question("How cool is this app ?", "It sucks", "Bloody brilliant", "It's alright", 2);
        addQuestion(q5);

    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }


    //FOR TABLE 2
    private void fillQuestionsTable2() {
        Question m1 =  new Question("The average of first 50 natural numbers is ?", "25.30", "25.5", "25.00", 2);
        addQuestion2(m1);
        Question m2 = new Question("15/5+27", "Non of the below", "5", "30", 3);
        addQuestion2(m2);
        Question m3 = new Question("The number of 3-digit numbers divisible by 6, is?", "149", "166", "150", 3);
        addQuestion2(m3);
        Question m4 = new Question("106 × 106 – 94 × 94 = ?", "2004", "2400", "1904", 1);
        addQuestion2(m4);
        Question m5 = new Question("What is 1004 divided by 2?", " 52", "502", "520", 2);
        addQuestion2(m5);
        Question m6 = new Question("Which of the following numbers gives 240 when added to its own square?", "15", "16", "18", 1);
        addQuestion2(m6);
        Question m7 = new Question("83 × 82 × 8-5 is", "8", "1", "None of these", 2);
        addQuestion2(m7);
        Question m8 = new Question("The simplest form of 1.5 : 2.5 is", "6 : 10", "0.75 : 1.25", "3 : 5", 3);
        addQuestion2(m8);
        Question m9 = new Question("In 24,673 ; the place-value of 6 is ?", "600", "10", "100", 1);
        addQuestion2(m9);

    }

    private void addQuestion2(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME2, null, cv);
    }

    public List<Question> getAllQuestions2() {
        List<Question> questionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME2, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}