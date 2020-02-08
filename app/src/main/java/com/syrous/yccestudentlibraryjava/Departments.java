package com.syrous.yccestudentlibraryjava;

public class Departments {

    private String dept;
    private String category;
    private int thumbnail;

    public Departments(String dept, int thumbnail) {
        this.dept = dept;
        this.thumbnail= thumbnail;

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






