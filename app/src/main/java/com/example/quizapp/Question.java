package com.example.quizapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private int question;
    private boolean answer;

    public Question(int question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    protected Question(Parcel in) {
        question = in.readInt();
        answer = in.readByte() != 0;
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

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(question);
        dest.writeByte((byte) (answer ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Question{" +
                "question=" + question +
                ", answer=" + answer +
                '}';
    }
}
