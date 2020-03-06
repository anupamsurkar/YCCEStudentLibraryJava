package com.syrous.yccestudentlibraryjava.utils;

public class ModelPapers {

    private String paperTitle;
    private String department;
    private String exam;
    private String uploadedBy;
    private String uploadDate;
    private String downloadUrl;

    public ModelPapers(String paperTitle, String department, String exam, String uploadedBy, String uploadDate, String downloadUrl) {
        this.paperTitle = paperTitle;
        this.department = department;
        this.exam = exam;
        this.uploadedBy = uploadedBy;
        this.uploadDate = uploadDate;
        this.downloadUrl = downloadUrl;
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public String getDepartment() {
        return department;
    }

    public String getExam() {
        return exam;
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
}
