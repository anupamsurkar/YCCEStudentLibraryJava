package com.syrous.yccestudentlibraryjava.ui.other_features;

public class ModelOF {

    private Integer featureLogo;
    private String featureName;
    private String url;

    public ModelOF(Integer featureLogo, String featureName, String url){
        this.featureLogo = featureLogo;
        this.featureName = featureName;
        this.url = url;
    }

    public Integer getFeatureLogo() {
        return featureLogo;
    }

    public String getFeatureName() {
        return featureName;
    }

    public String getUrl(){ return url;}
}
