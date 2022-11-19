package com.nhi.english.Revise_PhuongDong;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    public String style;
    public String id;
    public String content;
    public String answerA, answerB, answerC, answerD, Answer;

    public Question(String style, String id, String content, String answerA, String answerB, String answerC, String answerD, String answer) {
        this.style = style;
        this.id = id;
        this.content = content;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        Answer = answer;
    }

    public Question(Parcel in) {
        id = in.readString();
        content = in.readString();
        answerA = in.readString();
        answerB = in.readString();
        answerC = in.readString();
        answerD = in.readString();
        Answer = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(content);
        parcel.writeString(answerA);
        parcel.writeString(answerB);
        parcel.writeString(answerC);
        parcel.writeString(answerD);
        parcel.writeString(Answer);
    }
}
