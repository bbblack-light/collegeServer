package com.example.collegeServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MasterClassDto extends BaseDto {
    private String speakerName;
    private String title;
    private String cabinet;
    private Date date;
    private String description;
    private List<JoinMasterClassUserDto> users;

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<JoinMasterClassUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<JoinMasterClassUserDto> users) {
        this.users = users;
    }
}
