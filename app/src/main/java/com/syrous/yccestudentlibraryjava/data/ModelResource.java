package com.syrous.yccestudentlibraryjava.data;

public class ModelResource {

    private String title;
    private String unit;
    private String courseCode;

    public ModelResource(String title, String unit, String courseCode) {
        this.title = title;
        this.unit = unit;
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getUnit() {
        return unit;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
