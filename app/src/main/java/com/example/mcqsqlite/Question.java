package com.example.mcqsqlite;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Question implements Parcelable {

    public static final String DIFFICULTY_EASY = "Easy";
    public static final String DIFFICULTY_MEDIUM = "Medium";
    public static final String DIFFICULTY_HARD = "Hard";
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int answerNr;
    private String difficulty;
    private int categoryID;
    public Question() {
    }

    public Question(String question, String option1, String option2, String option3, String option4,
                    int answerNr, String difficulty, int categoryID) {
        Log.d("Ravi", "Question Question Start 46");
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.difficulty = difficulty;
        this.categoryID = categoryID;
        Log.d("Ravi", "Question Question  Start 106 " + question);
        Log.d("Ravi", "Question Question  Start 106 " + option3);
        Log.d("Ravi", "Question Question  Start 106 " + option4);
    }

    protected Question(Parcel in) {
        Log.d("Ravi", "Question Question parcel Start 47");
        id = in.readInt();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        answerNr = in.readInt();
        difficulty = in.readString();
        categoryID = in.readInt();
        Log.d("Ravi", "Question Question parcel Start 104 " + question);
        Log.d("Ravi", "Question Question parcel Start 104 " + option3);
        Log.d("Ravi", "Question Question parcel Start 104 " + option4);
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.d("Ravi", "Question writeToParcel Start 48");
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeString(option4);
        dest.writeInt(answerNr);
        dest.writeString(difficulty);
        dest.writeInt(categoryID);
        Log.d("Ravi", "Question Question parcel Start 105 " + question);
        Log.d("Ravi", "Question Question parcel Start 105 " + option3);
        Log.d("Ravi", "Question Question parcel Start 105 " + option4);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }
        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getQuestion() {
        Log.d("Ravi", "Question getQuestion Start 100 " + question);
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public String getOption1() {
        return option1;
    }
    public void setOption1(String option1) {
        this.option1 = option1;
    }
    public String getOption2() {
        return option2;
    }
    public void setOption2(String option2) {
        this.option2 = option2;
    }
    public String getOption3() {
        Log.d("Ravi", "Question getQuestion Start 101 " + option3);
        return option3;
    }
    public void setOption3(String option3) {
        Log.d("Ravi", "Question getQuestion Start 102 " + option3);
        this.option3 = option3;
    }
    public String getOption4() {
        Log.d("Ravi", "Question getQuestion Start 103 " + option4);
        return option4;}
    public void setOption4(String option4) {this.option4 = option4;}
    public int getAnswerNr() {
        return answerNr;
    }
    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public int getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
    public static String[] getAllDifficultyLevels() {
        return new String[]{
                DIFFICULTY_EASY,
                DIFFICULTY_MEDIUM,
                DIFFICULTY_HARD
        };
    }
}
