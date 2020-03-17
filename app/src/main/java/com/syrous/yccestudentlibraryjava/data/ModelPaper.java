package com.syrous.yccestudentlibraryjava.data;

public class ModelPaper {

    private String courseCode;
    private String paperTitle;
    private String department;
    private String uploadedBy;
    private String uploadDate;
    private int examinationYear;
    private String uploadTime;
    private String downloadUrl;
    private String examType;
    private int priority;

    public ModelPaper(String paperTitle, String department, String courseCode, String uploadedBy,
                      String uploadDate, String downloadUrl, int priority) {
        this.paperTitle = paperTitle;
        this.department = department;
        this.courseCode = courseCode;
        this.uploadedBy = uploadedBy;
        this.uploadDate = uploadDate;
        this.downloadUrl = downloadUrl;
//        this.uploadTime = uploadTime;
        this.priority = priority;

    }

    public ModelPaper(String paperTitle, String department, String courseCode, String examType, String uploadedBy,
                      String uploadDate, String downloadUrl, String uploadTime, int examinationYear) {
        this.paperTitle = paperTitle;
        this.department = department;
        this.courseCode = courseCode;
        this.examType = examType;
        this.uploadedBy = uploadedBy;
        this.uploadDate = uploadDate;
        this.downloadUrl = downloadUrl;
        this.uploadTime = uploadTime;
        this.examinationYear = examinationYear;
        this.priority = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public String getDepartment() {
        return department;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public int getPriority() {
        return priority;
    }

    public int getExaminationYear() {
        return examinationYear;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getExamType() {
        return examType;
    }
}
