package com.syrous.yccestudentlibraryjava.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.syrous.yccestudentlibraryjava.data.ModelPaper;

import static com.syrous.yccestudentlibraryjava.database.RecentDbSchema.RecentPapers;

public class RecentCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public RecentCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ModelPaper getPaper(){

      String courseCode = getString(getColumnIndex(RecentPapers.Cols.COURSE_CODE));
      String paperTitle = getString(getColumnIndex(RecentPapers.Cols.PAPER_TITLE));
      String department = getString(getColumnIndex(RecentPapers.Cols.DEPARTMENT));
      String uploadedBy = getString(getColumnIndex(RecentPapers.Cols.UPLOADED_BY));
      String uploadDate = getString(getColumnIndex(RecentPapers.Cols.UPLOAD_DATE));
      String downloadUrl = getString(getColumnIndex(RecentPapers.Cols.DOWNLOAD_URL));
      int priority = getInt(getColumnIndex(RecentPapers.Cols.PAPER_PRIORITY));

        return new ModelPaper(paperTitle, department, courseCode, uploadedBy, uploadDate, downloadUrl, priority);
    }

}
