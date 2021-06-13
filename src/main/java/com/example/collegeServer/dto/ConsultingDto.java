package com.example.collegeServer.dto;

import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class ConsultingDto extends BaseDto {
    private String teacherName;
    private String discipline;
    private String cabinet;
    private Date date;
    private String description;
    private List<JoinConsultingUserDto> users;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
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

    public List<JoinConsultingUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<JoinConsultingUserDto> users) {
        this.users = users;
    }
}
