package com.nhi.english.Revise_PhuongDong;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
        int pos; // số câu
        int idChecked; // id radio button

    public Answer(int idChecked, int pos) {
        this.pos = pos;
        this.idChecked = idChecked;
    }

    public int getPos() {
        return pos;
    }

    public int getIdChecked() {
        return idChecked;
    }

    protected Answer(Parcel in) {
        pos = in.readInt();
        idChecked = in.readInt();
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pos);
        parcel.writeInt(idChecked);
    }
}
