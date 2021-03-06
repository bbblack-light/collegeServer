package com.example.collegeServer.model.buisness;

import com.example.collegeServer.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "master_class")
public class MasterClass extends BaseEntity {
    private String speakerName;
    private String title;
    private String cabinet;
    private Date date;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterClass")
    private List<JoinMasterClassUser> users;

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

    public List<JoinMasterClassUser> getUsers() {
        return users;
    }

    public void setUsers(List<JoinMasterClassUser> users) {
        this.users = users;
    }
}
