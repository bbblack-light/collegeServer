package com.example.collegeServer.model.buisness;

import com.example.collegeServer.model.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "consulting")
public class Consulting extends BaseEntity {
    private String teacherName;
    private String discipline;
    private String cabinet;
    private Date date;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulting")
    private List<JoinConsultingUser> users;

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

    public List<JoinConsultingUser> getUsers() {
        return users;
    }

    public void setUsers(List<JoinConsultingUser> users) {
        this.users = users;
    }
}
