package com.example.collegeServer.dto.user;

import com.example.collegeServer.model.user.Gender;
import com.example.collegeServer.model.user.Role;
import com.example.collegeServer.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Long groupId;
    private Gender gender;
    private String phone;
    private String password;
    private String position;

    public static UserDto convertFromEntity(User user) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto, "group", "password");
        return dto;
    }

    public static List<UserDto> convertFromEntities(List<User> users) {
        return users.stream().map(UserDto::convertFromEntity).collect(Collectors.toList());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
