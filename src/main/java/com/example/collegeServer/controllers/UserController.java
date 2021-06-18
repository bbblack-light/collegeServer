package com.example.collegeServer.controllers;

import com.example.collegeServer.controllers.utils.response.OperationResponse;
import com.example.collegeServer.dto.user.UserDto;
import com.example.collegeServer.model.user.User;
import com.example.collegeServer.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public OperationResponse editUser(@RequestBody UserDto user, HttpServletRequest req) {
        boolean userEditSuccess = userService.edit(user);
        return userEditSuccess ? new OperationResponse("User Edited") : new OperationResponse("Unable to edit user");
    }

    @ApiOperation(value = "Get user information")
    @RequestMapping(value = "/user/info/{userId}", method = RequestMethod.GET, produces = {"application/json"})
    public UserDto getUserInfo(@PathVariable("userId") String userId) {
        return userService.getUserInformation(userId);
    }

    @ApiOperation(value = "Get user information")
    @RequestMapping(value = "/user/info/{userId}", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") String userId) {
        return userService.delete(userId);
    }
}
