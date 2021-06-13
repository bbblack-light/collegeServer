package com.example.collegeServer.model.buisness;

import com.example.collegeServer.model.BaseEntity;
import com.example.collegeServer.model.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "join_master_class_user")
public class JoinMasterClassUser extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "master_class_id", nullable = false)
    private MasterClass masterClass;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MasterClass getMasterClass() {
        return masterClass;
    }

    public void setMasterClass(MasterClass masterClass) {
        this.masterClass = masterClass;
    }
}
