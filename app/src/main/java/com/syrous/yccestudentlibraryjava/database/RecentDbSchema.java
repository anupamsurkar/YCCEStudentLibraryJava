package com.syrous.yccestudentlibraryjava.database;

public class RecentDbSchema {

    public static final class RecentPapers{
        public static final String NAME = "recent_papers";

        public static final class Cols{
            public static final String COURSE_CODE = "course_code";
            public static final String PAPER_TITLE = "paper_title";
            public static final String DEPARTMENT = "department";
            public static final String UPLOADED_BY = "uploaded_by";
            public static final String UPLOAD_DATE = "upload_date";
            public static final String DOWNLOAD_URL = "download_url";
            public static final String PAPER_PRIORITY = "paper_priority";
        }
    }

}
