package com.example.collegeServer.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConferenceDto extends BaseDto {
    private String teacher;
    private String password;
    private String fullId;
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

    public String getFullId() {
        return fullId;
    }

    public void setFullId(String fullId) {
        this.fullId = fullId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
