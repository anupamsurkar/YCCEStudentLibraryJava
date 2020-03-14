package com.syrous.yccestudentlibraryjava.ui.departments;

public class ModelDepartments {

    private String dept;
    private String category;
    private int thumbnail;
    private String deptShortName;

    public ModelDepartments(String dept, int thumbnail, String deptShortName) {
        this.dept = dept;
        this.thumbnail= thumbnail;
        this.deptShortName = deptShortName;
    }

    public String getDeptShortName() {
        return deptShortName;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}






