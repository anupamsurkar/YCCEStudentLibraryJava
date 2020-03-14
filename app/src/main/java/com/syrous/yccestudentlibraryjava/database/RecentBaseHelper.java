package com.syrous.yccestudentlibraryjava.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.syrous.yccestudentlibraryjava.database.RecentDbSchema.*;

public class RecentBaseHelper extends SQLiteOpenHelper {

    //Database Version
    private static final int VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "database.db";

    public RecentBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ RecentPapers.NAME + "(" +
                "_id integer primary key autoincrement," +
                RecentPapers.Cols.COURSE_CODE + ", " +
                RecentPapers.Cols.PAPER_TITLE + ", " +
                RecentPapers.Cols.DEPARTMENT + ", " +
                RecentPapers.Cols.DOWNLOAD_URL+ ", " +
                RecentPapers.Cols.UPLOAD_DATE + ", " +
                RecentPapers.Cols.UPLOADED_BY + ", " +
                RecentPapers.Cols.PAPER_PRIORITY +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
