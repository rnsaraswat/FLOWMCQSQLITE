package com.example.mcqsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mcqsqlite.QuizContract.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 3;
    private static QuizDbHelper instance;
    private SQLiteDatabase db;
    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            Log.d("Ravi", "QuizDbHelper QuizDbHelper Start 46");
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
//                Log.d("Ravi", "QuizDBHelper onCreate before drop 1");
//        final String SQL_DROP_QUESTIONS_TABLE = "DROP TABLE " + CategoriesTable.TABLE_NAME;
//        db.execSQL(SQL_DROP_QUESTIONS_TABLE);
//        Log.d("Ravi", "QuizDBHelper onCreate after drop 2 " + SQL_DROP_QUESTIONS_TABLE);
//        final String SQL_DROP_CATEGORIES_TABLE = "DROP TABLE " + CategoriesTable.TABLE_NAME ;
//        db.execSQL(SQL_DROP_CATEGORIES_TABLE);
//        Log.d("Ravi", "QuizDBHelper onCreate after drop  2 " + SQL_DROP_CATEGORIES_TABLE);
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
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        Log.d("Ravi", "QuizDbHelper onCreate Start 47");
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        Log.d("Ravi", "QuizDbHelper onCreate Start 48");
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        Log.d("Ravi", "QuizDbHelper onCreate Start 49");
        fillCategoriesTable();
        fillQuestionsTable();
        Log.d("Ravi", "QuizDbHelper onCreate Start 50");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Ravi", "QuizDbHelper onUpgrade Start 51");
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        Log.d("Ravi", "QuizDbHelper onUpgrade Start 52");
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        Log.d("Ravi", "QuizDbHelper onUpgrade Start 53");
        onCreate(db);
        Log.d("Ravi", "QuizDbHelper onUpgrade Start 54");
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        Log.d("Ravi", "QuizDbHelper onConfigure Start 55");
        db.setForeignKeyConstraintsEnabled(true);
    }
    private void fillCategoriesTable() {
        Log.d("Ravi", "QuizDbHelper fillCategoriesTable Start 56");
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Geography");
        insertCategory(c2);
        Category c3 = new Category("Math");
        insertCategory(c3);
        Log.d("Ravi", "QuizDbHelper fillCategoriesTable Start 57");
        Category c4 = new Category("RAILWAY");
        Log.d("Ravi", "QuizDbHelper fillCategoriesTable Start 58");
        insertCategory(c4);
        Log.d("Ravi", "QuizDbHelper fillCategoriesTable Start 59");
    }
    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }
    public void addCategories(List<Locale.Category> categories) {
        Log.d("Ravi", "QuizDbHelper addCategories Start 60");
        db = getWritableDatabase();
        for (Locale.Category category : categories) {
            Log.d("Ravi", "QuizDbHelper addCategories Start 61");
            insertCategory(category);
            Log.d("Ravi", "QuizDbHelper addCategories Start 62");
        }
    }

    private void insertCategory(Locale.Category category) {
        Log.d("Ravi", "QuizDbHelper insertCategory Start 63");
    }

    private void insertCategory(Category category) {
        Log.d("Ravi", "QuizDbHelper insertCategory Start 64");
        ContentValues cv = new ContentValues();
        Log.d("Ravi", "QuizDbHelper insertCategory Start 65");
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        Log.d("Ravi", "QuizDbHelper insertCategory Start 66");
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
        Log.d("Ravi", "QuizDbHelper insertCategory Start 67");
    }
    private void fillQuestionsTable() {
        Log.d("Ravi", "QuizDbHelper fillQuestionsTable Start 68");
        Question q1 = new Question("Presentation of the first ever railway budget in India held in ", "1853", "1888", "1925", "1905", 3, Question.DIFFICULTY_HARD, 4);
        insertQuestion(q1);
        Question q2 = new Question("Which is the shortest station name in Indian Railways? ", "Pune", "Bc", "Goa", "Ib", 4, Question.DIFFICULTY_EASY, Category.RAILWAY);
        insertQuestion(q2);
        Question q3 = new Question("Which is the longest station name in Indian Railways? ", "Kanyakumari", "Venkatanarasimharajuvariapeta", "Kharagpur", "Chhatrapati Shivaji Terminus", 2, Question.DIFFICULTY_EASY, Category.RAILWAY);
        insertQuestion(q3);
        Question q4 = new Question("Where is the wheel and axle plant of Indian Railways situated? ", "Chittranjan", "Kapurthala", "Bangalore", "Perambur", 3, Question.DIFFICULTY_MEDIUM, Category.RAILWAY);
        insertQuestion(q4);
        Question q5 = new Question("At which of the following places Diesel Component Works is established?","Jamshedpur", "Patiala", "Perambur", "Varanasi ", 2, Question.DIFFICULTY_EASY, Category.RAILWAY);
        insertQuestion(q5);
        Question q6 = new Question("Diesel Locomotive Works is situated at", "Perambur", "Varanasi", "Kapurthala", "Bangalore", 2, Question.DIFFICULTY_EASY, Category.RAILWAY);
        insertQuestion(q6);
        Question q7 = new Question("The passenger bogies of the Indian Railways are manufactured at which of the following places? ", "Kapurthala", "Chittranjan", "Perambur", "Bangalore", 3, Question.DIFFICULTY_EASY, Category.RAILWAY);
        insertQuestion(q7);
        Question q8 = new Question("In diesel engine, ignition is caused by", "Spark", "Automatic starter", "Compression ", "Friction", 3, Question.DIFFICULTY_MEDIUM, Category.RAILWAY);
        insertQuestion(q8);
        Question q9= new Question("Which train in India has the longest route length? ", "Vivek Express", "Indrani Express", "Kanyakumari Express", "Bangalore Guwahati Express", 1, Question.DIFFICULTY_EASY, Category.RAILWAY);
        insertQuestion(q9);
        Question q10 = new Question("When was the first underground railway (Metro Railway) started? ", "1982", "1989", "1984", "1992", 3, Question.DIFFICULTY_HARD, Category.RAILWAY);
        Log.d("Ravi", "QuizDbHelper fillQuestionsTable Start 69");
        insertQuestion(q10);
        Log.d("Ravi", "QuizDbHelper fillQuestionsTable Start 70");
        Question q11 = new Question("A station where the rail lines end, is called", "Junction station", "Way-side station", "Block station", "Terminal station", 4, Question.DIFFICULTY_MEDIUM, Category.RAILWAY);
        insertQuestion(q11);
        Question q12 = new Question("How much distance was travelled by first train of India? ", "33 km","36 km","34 km","46 km", 3, Question.DIFFICULTY_HARD, Category.RAILWAY);
        insertQuestion(q12);
        Question q13 = new Question("What is the position of the Indian Railway in the world according to the length of rail lines? ", "First", "Second", "Third", "Fourth", 4, Question.DIFFICULTY_HARD, Category.RAILWAY);
        insertQuestion(q13);
        Question q14 = new Question("General Manger is responsible for", "Railway Board", "Railway Ministry", "Both railway Board and Railway Ministry", "None of these", 1, Question.DIFFICULTY_MEDIUM, Category.RAILWAY);
        insertQuestion(q14);
        Question q15 = new Question("The headquarters of South-Central Railways is situated at", "Mumbai (CST) ", "Chennai", "Secundrabad",  "mumbai (Charch Gate", 3, Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q15);
        Question q16 = new Question("Is Python case sensitive when dealing with identifiers? ", "Yes", "No", "machine dependent", "none of the mentioned", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q16);
        Question q34 = new Question("What is the maximum possible length of an identifier? ", "31 characters", "63 characters", "79 characters", "none of the mentioned", 4,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q34);
        Question q35 = new Question("Which of the following is invalid? ", " _a = 1", "__a = 1", "__str__ = 1", "none of the mentioned", 4,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q35);
        Question q36 = new Question("Why are local variable names beginning with an underscore discouraged? ", "they are used to indicate a private variables of a class", "they confuse the interpreter", "they are used to indicate global variables", "they slow down execution", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q36);
        Question q17 = new Question("The Sankosh river forms boundary between which of the following two states? ",
                "Bihar and West Bengal", "Assam and Arunachal Pradesh", "Assam and West Bengal", "Bihar and Jharkhand", 2, Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        insertQuestion(q17);
        Question q37 = new Question("TWhich of the following is NOT a petrochemical centre of India? ",
                "Koyali", "Jamnagar", "Mangalore", "Rourkela", 4, Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        insertQuestion(q37);
        Question q18 = new Question("Math, Hard: C is correct",
                "A", "B", "C", "D",3, Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q18);
        Question q19 = new Question("Math, Easy: A is correct",
                "A", "B", "C", "D", 1, Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q19);
        Question q20 = new Question("Non existing, Easy: A is correct",
                "A", "B", "C", "D",1, Question.DIFFICULTY_EASY, 3);
        insertQuestion(q20);
        Question q21 = new Question("Non existing, Medium: B is correct",
                "A", "B", "C", "D", 2, Question.DIFFICULTY_MEDIUM, 1);
        insertQuestion(q21);
        Question q25 = new Question("Math, Easy: 3 + 4  A is correct",
                "A 7", "B 6 ", "C 8", "D 9",1, Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q25);
        Question q22 = new Question("Math, Easy:  5 + 6  A is correct",
                "A 10", "B 11", "C 12", "D 13", 2, Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q22);
        Question q23 = new Question("Math, Easy:  4 + 5  A is correct",
                "A 8", "B 7", "C 9", "D 10", 3, Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q23);
        Question q24 = new Question("Math, Easy:  6 + 7  A is correct",
                "A 13", "B 12 ", "C 14", "D 15", 1, Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q24);
        Question q26= new Question("Math, Medium,: 3 * 4  A is correct",
                "A 12", "B 13 ", "C 14", "D 11",1, Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q26);
        Question q27 = new Question("Math, Medium:  5 * 6  A is correct",
                "A 25", "B 30", "C 35", "D 40", 2, Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q27);
        Question q28 = new Question("Math, Medium:  4 * 5  A is correct",
                "A 15", "B 25", "C 20", "D 10", 3, Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q28);
        Question q29 = new Question("Math, Medium:  6 * 7  A is correct",
                "A 42", "B 43 ", "C 41", "D 44", 1, Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q29);
        Question q30 = new Question("Math, Hard:  14 * 2  A is correct",
                "A 7", "B 8 ", "C 9", "D 6", 1, Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q30);
        Question q31 = new Question("Math, Hard:  15 * 3  A is correct",
                "A 6", "B 3 ", "C 4", "D 5", 4, Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q31);
        Question q32 = new Question("Math, Hard:  18 * 6  A is correct",
                "A 2", "B 4 ", "C 3", "D 5", 3, Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q32);
        Question q33 = new Question("Math, Hard:  42 * 6  A is correct",
                "A 6", "B 7 ", "C 8", "D 5", 2, Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q33);
        Log.d("Ravi", "QuizDbHelper fillQuestionsTable Start 71" + q33);
    }
    public void addQuestion(Question question) {
        db = getWritableDatabase();
        Log.d("Ravi", "QuizDbHelper addQuestion Start 73");
        insertQuestion(question);
        Log.d("Ravi", "QuizDbHelper addQuestion Start 74" + question);
    }
    public void addQuestions(List<Question> questions) {
        Log.d("Ravi", "QuizDbHelper addQuestion Start 75");
        db = getWritableDatabase();
        for (Question question : questions) {
            insertQuestion(question);
            Log.d("Ravi", "QuizDbHelper addQuestion Start 76");
        }
    }

    private void insertQuestion(Question question) {
        Log.d("Ravi", "QuizDbHelper insertQuestion Start 77");
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
        Log.d("Ravi", "QuizDbHelper insertQuestion Start 78");
    }

    public List<Category> getAllCategories() {
        Log.d("Ravi", "QuizDbHelper List<Category> Start 79");
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        Log.d("Ravi", "QuizDbHelper List<Category> Start 80");
        if (c.moveToFirst()) {
            Log.d("Ravi", "QuizDbHelper List<Category> Start 81");
            do {
                Log.d("Ravi", "QuizDbHelper List<Category> Start 82");
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
                Log.d("Ravi", "QuizDbHelper List<Category> Start 83");
            } while (c.moveToNext());
        }
        Log.d("Ravi", "QuizDbHelper List<Category> Start 84");
        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 85");
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 86");
        if (c.moveToFirst()) {
            do {
                Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 87");
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 88");
        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 89");
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
        Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 90" + questionList);
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
            Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 91");
        if (c.moveToFirst()) {
            do {
                Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 92");
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
                Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 93" + question);
            } while (c.moveToNext());
        }
        Log.d("Ravi", "QuizDbHelper ArrayList<Question> Start 94" + questionList);
        c.close();
        return questionList;
    }
}
