package com.syrous.yccestudentlibraryjava.data;

public class ModelSubject {

    private String name;
    private String courseCode;
    private String noOfMsePapers;
    private String noOfEsePapers;

    public ModelSubject(String courseCode, String name){
        this.courseCode = courseCode;
        this.name = name;
    }

//    public ModelSubject(String courseCode, String name, String noOfMsePapers, String noOfEsePapers) {
//        this.name = name;
//        this.courseCode = courseCode;
//        this.noOfMsePapers = noOfMsePapers;
//        this.noOfEsePapers = noOfEsePapers;
//    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getSubjectName() {
        return name;
    }

    public String getNoOfMsePapers() {
        return noOfMsePapers;
    }

    public String getNoOfEsePapers() {
        return noOfEsePapers;
    }
}
