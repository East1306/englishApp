package com.nhi.english.Revise_PhuongDong.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.nhi.english.Revise_PhuongDong.Models.RecordingItem;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public  static final  String DATABASE_NAME = "saved_recordings.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "saved_recording_table";

    public static final String COLMN_NAME = "name";
    public static final String COLMN_Path = "path";
    public static final String COLMN_LENGTH = "length";
    public static final String COLMN_TIME_ADDED = "time_added";

    public static final String COMA_SEP = ",";

    private static final String SQLITE_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                                                    + " (" + "id INTEGER PRIMARY KEY"
                                                    + " AUTOINCREMENT" + COMA_SEP
                                                    + COLMN_NAME + " TEXT" + COMA_SEP
                                                    + COLMN_Path + " TEXT" + COMA_SEP
                                                    + COLMN_LENGTH + " INTERGER" + COMA_SEP
                                                    + COLMN_TIME_ADDED + " INTERGER " + ") ";
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLITE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean addRecording (RecordingItem recordingItem){
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLMN_NAME, recordingItem.getName());
            contentValues.put(COLMN_Path, recordingItem.getPath());
            contentValues.put(COLMN_LENGTH, recordingItem.getLength());
            contentValues.put(COLMN_TIME_ADDED, recordingItem.getTime_added());

            db.insert(TABLE_NAME, null, contentValues);

            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }


    }
}
