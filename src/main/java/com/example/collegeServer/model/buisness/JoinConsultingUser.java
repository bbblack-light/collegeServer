package com.example.collegeServer.model.buisness;

import com.example.collegeServer.model.BaseEntity;
import com.example.collegeServer.model.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "join_consulting_user")
public class JoinConsultingUser extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "consulting_id", nullable = false)
    private Consulting consulting;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Consulting getConsulting() {
        return consulting;
    }

    public void setConsulting(Consulting consulting) {
        this.consulting = consulting;
    }
}
