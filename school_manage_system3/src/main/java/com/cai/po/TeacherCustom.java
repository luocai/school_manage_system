package com.cai.po;

public class TeacherCustom extends Teacher {
    //所属院系名
    private String collegeName;

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeName() {
        return collegeName;
    }
}
