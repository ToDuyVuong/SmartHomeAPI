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
            System.out.println("User already exists" + user.getAvatar());
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
//            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
//        String avatar = "https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png";

            User newUser = new User();
            newUser.setId(userModel.getId());
            newUser.setUsername(userModel.getUsername());
            newUser.setPassword(userModel.getPassword());
            newUser.setAvatar(userModel.getAvatar());
            newUser.setGender(userModel.getGender());
            newUser.setEmail(userModel.getEmail());
            newUser.setPhoneNumber(userModel.getPhoneNumber());
            newUser.setAddress(userModel.getAddress());

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