package com.syrous.yccestudentlibraryjava.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.syrous.yccestudentlibraryjava.database.RecentBaseHelper;
import com.syrous.yccestudentlibraryjava.database.RecentCursorWrapper;
import com.syrous.yccestudentlibraryjava.database.RecentDbSchema.RecentPapers;

import java.util.ArrayList;
import java.util.List;

import static com.syrous.yccestudentlibraryjava.database.RecentDbSchema.RecentPapers.NAME;

public class RecentRepository {

    private static RecentRepository sRecent;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private RecentRepository (Context context){
        mContext = context.getApplicationContext();
        mDatabase = new RecentBaseHelper(mContext)
                        .getWritableDatabase();
    }

    public static RecentRepository get(Context context){
        if(sRecent == null){
            sRecent = new RecentRepository(context);
        }
        return sRecent;
    }


    public void deletePaper(ModelPaper paper){
        String courseCode = paper.getCourseCode();
        String paperTitle = paper.getPaperTitle();
        mDatabase.delete(NAME,
                RecentPapers.Cols.COURSE_CODE + " = ?" +
                        " AND " + RecentPapers.Cols.PAPER_TITLE + " = ?" ,
                new String[] {courseCode, paperTitle}
        );
    }

    public void addPaper(ModelPaper c) {
        ContentValues values = getContentValues(c);

        mDatabase.insert(NAME, null, values);
    }

    public List<ModelPaper> getPapers() {

        List<ModelPaper> papers = new ArrayList<>();

        RecentCursorWrapper cursor = queryPapers(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                papers.add(cursor.getPaper());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return papers;

    }

    public ModelPaper getCrime(String courseCode, String paperTitle){

        RecentCursorWrapper cursor = queryPapers(
                RecentPapers.Cols.COURSE_CODE + " = ?" +
                " AND "+ RecentPapers.Cols.PAPER_TITLE + " = ?",
                new String[] { courseCode, paperTitle}
        );

        try{
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getPaper();
        } finally {
            cursor.close();
        }

    }

    public void updatePaper(ModelPaper paper){
        String courseCode = paper.getCourseCode();
        String paperTitle = paper.getPaperTitle();
        ContentValues values = getContentValues(paper);

        mDatabase.update(NAME, values,
                RecentPapers.Cols.COURSE_CODE + " = ?" +
                " AND " + RecentPapers.Cols.PAPER_TITLE + " = ?",
                new String[] { courseCode, paperTitle});
    }

    private RecentCursorWrapper queryPapers(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new RecentCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(ModelPaper paper){
        ContentValues values = new ContentValues();
        values.put(RecentPapers.Cols.COURSE_CODE, paper.getCourseCode());
        values.put(RecentPapers.Cols.PAPER_TITLE, paper.getPaperTitle());
        values.put(RecentPapers.Cols.DEPARTMENT, paper.getDepartment());
        values.put(RecentPapers.Cols.DOWNLOAD_URL, paper.getDownloadUrl());
        values.put(RecentPapers.Cols.UPLOAD_DATE, paper.getUploadDate());
        values.put(RecentPapers.Cols.UPLOADED_BY, paper.getUploadedBy());
        values.put(RecentPapers.Cols.PAPER_PRIORITY, paper.getPriority());
        return values;
    }
}
