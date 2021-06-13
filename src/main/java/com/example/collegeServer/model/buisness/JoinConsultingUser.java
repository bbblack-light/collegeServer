//package com.example.collegeServer.model.buisness;
//
//import com.example.collegeServer.model.BaseEntity;
//import com.example.collegeServer.model.user.User;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//
//@Entity(name = "join_consulting_user")
//@Getter
//@Setter
//public class JoinConsultingUser extends BaseEntity {
//    @ManyToOne
//    @JoinColumn(name = "userId", nullable = false)
//    private User user;
//    @ManyToOne
//    @JoinColumn(name = "consulting_id", nullable = false)
//    private Consulting consulting;
//}
