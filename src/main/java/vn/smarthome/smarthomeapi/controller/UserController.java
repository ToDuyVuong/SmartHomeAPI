package vn.smarthome.smarthomeapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.smarthome.smarthomeapi.entity.User;
import vn.smarthome.smarthomeapi.model.UserModel;
import vn.smarthome.smarthomeapi.service.IUserService;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

//    @Autowired
//    IMailService mailService;
//
//
//    @Autowired
//    CloudinaryService cloudinaryService;


    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<User> register(@RequestBody UserModel userModel) {

        User user = userService.findById(userModel.getId());
        if (user != null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
//            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
        String avatar = "https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png";

        User newUser = new User();
        newUser.setUsername(userModel.getUsername());
        newUser.setId("id" + userModel.getUsername());
        newUser.setPassword(userModel.getPassword());
        newUser.setEmail("email");
        newUser.setAvatar(avatar);
        newUser.setGender(true);
        newUser.setPhoneNumber("0");
        newUser.setAddress("DiaChi");

        userService.saveUser(newUser);

        System.out.println(newUser.getId());
        System.out.println(newUser.getUsername());
        System.out.println(newUser.getPhoneNumber());

        return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
    }




    //http://localhost:8085/get
    @GetMapping("/get")
    public List<User> get() {

        return userService.findAll();
    }

}