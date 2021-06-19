package com.example.collegeServer.model.buisness;

import com.example.collegeServer.model.BaseEntity;

import javax.persistence.Entity;

@Entity(name = "conference")
public class Conference extends BaseEntity {
    private String teacher;
    private String password;
    private String url;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
