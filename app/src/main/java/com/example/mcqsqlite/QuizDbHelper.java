package com.example.mcqsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mcqsqlite.QuizContract.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private static QuizDbHelper instance;
    private SQLiteDatabase db;
    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
//                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Geography");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
//        Category c4 = new Category("RAILWAY");
//        insertCategory(c4);
    }
    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }
    public void addCategories(List<Locale.Category> categories) {
        db = getWritableDatabase();
        for (Locale.Category category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(Locale.Category category) {
    }

    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }
    private void fillQuestionsTable() {
//        Question q1 = new Question("Presentation of the first ever railway budget in India held in", "1853", "1888", "1925", "1905", 3, Question.DIFFICULTY_EASY, 4);
//        insertQuestion(q1);
//        Question q2 = new Question("Which is the shortest station name in Indian Railways? ", "Pune", "Bc", "Goa", "Ib", 4, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q2);
//        Question q3 = new Question("Which is the longest station name in Indian Railways? ", "Kanyakumari", "Venkatanarasimharajuvariapeta", "Kharagpur", "Chhatrapati Shivaji Terminus", 2, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q3);
//        Question q4 = new Question("Where is the wheel and axle plant of Indian Railways situated? ", "Chittranjan", "Kapurthala", "Bangalore", "Perambur", 3, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q4);
//        Question q5 = new Question("At which of the following places Diesel Component Works is established?","Jamshedpur", "Patiala", "Perambur", "Varanasi ", 2, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q5);
//        Question q6 = new Question("Diesel Locomotive Works is situated at", "Perambur", "Varanasi", "Kapurthala", "Bangalore", 2, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q6);
//        Question q7 = new Question("The passenger bogies of the Indian Railways are manufactured at which of the following places? ", "Kapurthala", "Chittranjan", "Perambur", "Bangalore", 3, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q7);
//        Question q8 = new Question("In diesel engine, ignition is caused by", "Spark", "Automatic starter", "Compression ", "Friction", 3, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q8);
//        Question q9= new Question("Which train in India has the longest route length? ", "Vivek Express", "Indrani Express", "Kanyakumari Express", "Bangalore Guwahati Express", 1, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q9);
//        Question q10 = new Question("When was the first underground railway (Metro Railway) started? ", "1982", "1989", "1984", "1992", 3, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q10);
//        Question q11 = new Question("A station where the rail lines end, is called", "Junction station", "Way-side station", "Block station", "Terminal station", 4, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q11);
//        Question q12 = new Question("How much distance was travelled by first train of India? ", "33 km","36 km","34 km","46 km", 3, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q12);
//        Question q13 = new Question("What is the position of the Indian Railway in the world according to the length of rail lines? ", "First", "Second", "Third", "Fourth", 4, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q13);
//        Question q14 = new Question("General Manger is responsible for", "Railway Board", "Railway Ministry", "Both railway Board and Railway Ministry", "None of these", 1, Question.DIFFICULTY_EASY, Category.RAILWAY);
//        insertQuestion(q14);
//        Question q15 = new Question("The headquarters of South-Central Railways is situated at", "Mumbai (CST) ", "Chennai", "Secundrabad",  3, Question.DIFFICULTY_EASY, Category.PROGRAMMING);
//        insertQuestion(q15);
        Question q16 = new Question("Programming, Easy: A is correct", "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q16);
        Question q17 = new Question("Geography, Medium: B is correct",
//                "A", "B", "C", "d", 2,
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q17);
        Question q18 = new Question("Math, Hard: C is correct",
//                "A", "B", "C", "D",3,
                "A", "B", "C",3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q18);
        Question q19 = new Question("Math, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q19);
        Question q20 = new Question("Non existing, Easy: A is correct",
                "A", "B", "C", 1,
                Question.DIFFICULTY_EASY, 3);
        insertQuestion(q20);
        Question q21 = new Question("Non existing, Medium: B is correct",
                "A", "B", "C", 2,
                Question.DIFFICULTY_MEDIUM, 1);
        insertQuestion(q21);
        Question q1 = new Question("Math, Easy: 3 + 4  A is correct",
                "A 7", "B 6 ", "C 8", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q1);
        Question q2 = new Question("Math, Easy:  5 + 6  A is correct",
                "A 10", "B 11", "C 12", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q2);
        Question q3 = new Question("Math, Easy:  4 + 5  A is correct",
                "A 8", "B 7", "C 9", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q3);
        Question q4 = new Question("Math, Easy:  6 + 7  A is correct",
                "A 13", "B 12 ", "C 14", 1,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q4);
    }
    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }
    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();
        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
//        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
//                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
//                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
