package com.example.collegeServer.model.buisness;

import com.example.collegeServer.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity(name = "conference")
@Getter
@Setter
public class Conference extends BaseEntity {
    private String teacher;
    private String password;
    private String fullId;
    private String url;
}
