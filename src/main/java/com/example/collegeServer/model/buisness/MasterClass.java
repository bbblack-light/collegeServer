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
@Getter
@Setter
public class MasterClass extends BaseEntity {
    private String speakerName;
    private String title;
    private String cabinet;
    private Date date;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "masterClass")
    private List<JoinMasterClassUser> users;
}
