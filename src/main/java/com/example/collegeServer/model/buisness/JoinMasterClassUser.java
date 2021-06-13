package com.example.collegeServer.model.buisness;

import com.example.collegeServer.model.BaseEntity;
import com.example.collegeServer.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "join_master_class_user")
@Getter
@Setter
public class JoinMasterClassUser extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "master_class_id", nullable = false)
    private MasterClass masterClass;
}
