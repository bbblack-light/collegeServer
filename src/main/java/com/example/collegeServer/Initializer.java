package com.example.collegeServer;

import com.example.collegeServer.model.user.Role;
import com.example.collegeServer.model.user.User;
import com.example.collegeServer.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    private static UserService service;

    public Initializer(UserService service) {
        this.service = service;
    }

    public static void createAdmin() {
        User user = new User();
        user.setActive(true);
        user.setPassword("$2a$10$U0fbYjlwLFraz1UHrjjX2.rErJoP/X.KIRthU9jtmMkRACYBeVOpG");
        user.setUserId("admin");
        user.setRole(Role.ADMIN);
        user.setFirstName("Админ");
        user.setLastName("");
        service.insertOrSaveUser(user);
    }

}
