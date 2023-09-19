package com.example.sih_r2;

public class IssueModel {
    String title;
    String desc;
    byte[] image_url;

    public String getSavedTitle() {
        return title;
    }

    public String getSavedDesc() {
        return desc;
    }

    public byte[] getSavedURL(){return image_url;}

    public void setSavedTitle(String title) {
        this.title = title;
    }

    public void setSavedDesc(String desc) {
        this.desc = desc;
    }

    public void setSavedURL(byte[] image_url) {this.image_url = image_url;}

    public IssueModel(String title, String desc, byte[] image_url)
    {
        this.title = title;
        this.desc = desc;
        this.image_url = image_url;
    }
}
