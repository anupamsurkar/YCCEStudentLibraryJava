package com.syrous.yccestudentlibraryjava.ui.recents;

public class ModelRecents {

    private Integer featureLogo;
    private String featureName;

    public ModelRecents(Integer featureLogo, String featureNam){
        this.featureLogo = featureLogo;
        this.featureName = featureNam;
    }

    public Integer getFeatureLogo() {
        return featureLogo;
    }

    public String getFeatureName() {
        return featureName;
    }

}
