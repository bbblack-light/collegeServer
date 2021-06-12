package com.example.collegeServer.services;

import com.example.collegeServer.controllers.utils.exception.NotFoundException;
import com.example.collegeServer.controllers.utils.response.OperationResponse;
import com.example.collegeServer.dto.user.UserDto;
import com.example.collegeServer.dto.user.UserLoginDataDto;
import com.example.collegeServer.model.user.Gender;
import com.example.collegeServer.model.user.Role;
import com.example.collegeServer.model.user.User;
import com.example.collegeServer.repo.UserRepo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;


    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDto getUserInformation(String userIdParam) {
        String loggedInUserId = getLoggedInUserId();
        User user;
        if (Strings.isNullOrEmpty(userIdParam)) {
            user = getLoggedInUser();
        } else if (loggedInUserId.equals(userIdParam)) {
            user = getLoggedInUser();
        } else {
            user = getUserInfoByUserId(userIdParam);
        }
        return UserDto.convertFromEntity(user);

    }

    public String getLoggedInUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "nosession";
        }
        return auth.getName();
    }


    public User getLoggedInUser() {
        String loggedInUserId = this.getLoggedInUserId();
        return this.getUserInfoByUserId(loggedInUserId);
    }

    public User getUserInfoByUserId(String userId) {
        return this.userRepo.findOneByUserId(userId).orElseGet(User::new);
    }


    public boolean insertOrSaveUser(User user) {
        this.userRepo.save(user);
        return true;
    }

    public boolean addNewUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User newUser = this.getUserInfoByUserId(user.getUserId());
        if (newUser.getUserId().equals("new")) {
            // This means the username is not found therefore its is returning a default value of "new"
            user.setRole(Role.STUDENT);
            return this.insertOrSaveUser(user);
        } else {
            return false;
        }
    }

    public boolean editLoginData(UserLoginDataDto user) {
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        User buffUser = this.getUserInfoByUserId(user.getUserId());
        if (user.getPassword() != null && !user.getPassword().equals("")) {
            buffUser.setPassword(user.getPassword());
        }
        buffUser.setEmail(user.getEmail());
        return this.insertOrSaveUser(buffUser);
    }

    public List<UserDto> findAll() {
        return UserDto.convertFromEntities(userRepo.findAll());
    }

    public OperationResponse changeRole(User user) {//change
        if (!userRepo.existsByUserId(user.getUserId())) {
            throw  new NotFoundException("user not found");
        }
        User buff = userRepo.findOneByUserId(user.getUserId()).get();
        buff.setRole(user.getRole());
        userRepo.save(buff);
        return new OperationResponse("Change status successful");
    }

    public OperationResponse editUserName(String userId, String firstName, String lastName) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(()->new NotFoundException("There are no such user"));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepo.save(user);
        return new OperationResponse("name is editing");
    }

    public OperationResponse editUserEmail(String userId, String email) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(()->new NotFoundException("There are no such user"));
        user.setEmail(email);
        userRepo.save(user);
        return new OperationResponse("email is editing");
    }

    public OperationResponse editUserPhone (String userId, String phone) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(()->new NotFoundException("There are no such user"));
        user.setPhone(phone);
        userRepo.save(user);
        return new OperationResponse("phone is editing");
    }

    public OperationResponse editUserGender(String userId, Gender gender) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(()->new NotFoundException("There are no such user"));
        user.setGender(gender);
        userRepo.save(user);
        return new OperationResponse("gender is editing");
    }

    public OperationResponse editPassword (String userId, String password) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(()->new NotFoundException("There are no such user"));
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userRepo.save(user);
        return new OperationResponse("password is editing");
    }

    public OperationResponse editUserPosition(String userId, String position) {
        User user = userRepo.findOneByUserId(userId).orElseThrow(()->new NotFoundException("There are no such user"));
        userRepo.save(user);
        return new OperationResponse("position is editing");
    }

    public User save(User user) {
        return userRepo.save(user);
    }
}

