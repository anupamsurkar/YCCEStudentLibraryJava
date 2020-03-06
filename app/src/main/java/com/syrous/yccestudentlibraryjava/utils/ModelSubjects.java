package com.syrous.yccestudentlibraryjava.utils;

public class ModelSubjects {

    private String name;
    private String noOfMsePapers;
    private String noOfEsePapers;

    public ModelSubjects(String name, String noOfMsePapers, String noOfEsePapers) {
        this.name = name;
        this.noOfMsePapers = noOfMsePapers;
        this.noOfEsePapers = noOfEsePapers;
    }

    public String getName() {
        return name;
    }

    public String getNoOfMsePapers() {
        return noOfMsePapers;
    }

    public String getNoOfEsePapers() {
        return noOfEsePapers;
    }
}
