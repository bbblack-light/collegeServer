package com.example.collegeServer.model.buisness;

import com.example.collegeServer.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "consulting")
@Getter
@Setter
public class Consulting extends BaseEntity {
    private String teacherName;
    private String discipline;
    private String cabinet;
    private Date date;
    private String description;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consulting")
//    private List<JoinConsultingUser> users;
}
