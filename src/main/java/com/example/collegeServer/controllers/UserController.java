package com.example.collegeServer.controllers;

import com.example.collegeServer.controllers.utils.response.OperationResponse;
import com.example.collegeServer.dto.user.UserDto;
import com.example.collegeServer.dto.user.UserLoginDataDto;
import com.example.collegeServer.model.user.User;
import com.example.collegeServer.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Api(tags = {"Authentication"})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Get current user information")
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = {"application/json"})
    public UserDto getUserInformation(@RequestParam(value = "name", required = false) String userIdParam, HttpServletRequest req) {
        return userService.getUserInformation(userIdParam);
    }

    @ApiOperation(value = "get all users")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @ApiOperation(value = "Add new user", response = OperationResponse.class)
    @RequestMapping(value = "/registration", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse addNewUser(@RequestBody User user, HttpServletRequest req) {
        boolean userAddSuccess = userService.addNewUser(user);
        return userAddSuccess ? new OperationResponse("user added") : new OperationResponse("user did not added");
    }

    @ApiOperation(value = "Edit user", response = OperationResponse.class)
    @RequestMapping(value = "/edit/user", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse editUser(@RequestBody UserLoginDataDto user, HttpServletRequest req) {
        boolean userEditSuccess = userService.editLoginData(user);
        return userEditSuccess ? new OperationResponse("User Edited") : new OperationResponse("Unable to edit user");
    }

    @ApiOperation(value = "Edit user", response = OperationResponse.class)
    @RequestMapping(value = "/editRole", method = RequestMethod.POST, produces = {"application/json"})
    public OperationResponse changeUserRole(@RequestBody User user) {
        return userService.changeRole(user);
    }

    @ApiOperation(value = "Edit FirstName or LAstName for User", response = OperationResponse.class)
    @RequestMapping(value = "/editName/{userId}", method = RequestMethod.PUT, produces = {"application/json"})
    public OperationResponse editUserName(@PathVariable("userId") String userId, @RequestBody UserDto user) {
        return userService.editUserName(userId, user.getFirstName(), user.getLastName());
    }

    @ApiOperation(value = "Edit email for User", response = OperationResponse.class)
    @RequestMapping(value = "/editEmail/{userId}", method = RequestMethod.PUT, produces = {"application/json"})
    public OperationResponse editUserEmail(@PathVariable("userId") String userId, @RequestBody UserDto user) {
        return userService.editUserEmail(userId, user.getEmail());
    }

    @ApiOperation(value = "Edit Gender for User", response = OperationResponse.class)
    @RequestMapping(value = "/editGender/{userId}", method = RequestMethod.PUT, produces = {"application/json"})
    public OperationResponse editUserGender(@PathVariable("userId") String userId, @RequestBody UserDto user) {
        return userService.editUserGender(userId, user.getGender());
    }

    @ApiOperation(value = "edit Password For User", response = OperationResponse.class)
    @RequestMapping(value = "/editPassword/{userId}", method = RequestMethod.PUT, produces = {"application/json"})
    public OperationResponse editPasswordForUser(@PathVariable("userId") String userId, @RequestBody UserDto user) {
        return userService.editPassword(userId, user.getPassword());
    }

    @ApiOperation(value = "Get user information")
    @RequestMapping(value = "/user/info/{userId}", method = RequestMethod.GET, produces = {"application/json"})
    public UserDto getUserInfo(@PathVariable("userId") String userId) {
        return userService.getUserInformation(userId);
    }
}
