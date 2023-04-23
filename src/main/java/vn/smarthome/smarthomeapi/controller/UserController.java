package vn.smarthome.smarthomeapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
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

//    @PostMapping(path = "/register", consumes = "application/x-www-form-urlencoded")
@PostMapping(path = "/register", consumes = "application/json")
    public ResponseEntity<User> register(@RequestBody UserModel userModel) {
        User user = userService.findById(userModel.getId());
        if (user != null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
//            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
            String avatar = "https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png";

            User newUser = new User();
            newUser.setUsername(userModel.getUsername());
            newUser.setId("íad");
            newUser.setPassword(userModel.getPassword());
            newUser.setEmail("email");
            newUser.setAvatar(avatar);
            newUser.setPhoneNumber("0");
            newUser.setAddress("DiaChi");

            userService.saveUser(newUser);

            System.out.println(newUser);

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
    }

    @PostMapping(path = "/a", consumes = "application/json")
    public ResponseEntity<String> addUsera(@RequestBody UserModel userModel) {
        User user = new User();


        try {
            user.setId("username");
            user.setPassword(userModel.getPassword());
            user.setUsername(userModel.getUsername());
            user.setAvatar("https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png");
            user.setPhoneNumber("0");
            user.setAddress("DiaChi");
            user.setEmail("email");

            userService.saveUser(user);
            System.out.println(user.getId());
            System.out.println(user.getUsername());
            System.out.println(user.getPhoneNumber());
            System.out.println(user.getAddress());
            System.out.println(user.getAvatar());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    //http://localhost:8085/add
//    @PostMapping("/add")
//    @ResponseBody
//    public UserModel add(@ResponseBody UserModel userModel) {
//
//
//        return userModel;
//    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            System.out.println(user.getId());
            System.out.println(user.getUsername());
            System.out.println(user.getPhoneNumber());
            System.out.println(user.getAddress());
            System.out.println(user.getAvatar());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//
//    @PostMapping("/add")
//    @ResponseBody
//    public User add( String id, String password, String username, String avatar, String email, String phoneNumber, String address) {
//
//        User user = new User();
//        user.setId(id);
//        user.setUsername(username);
//        user.setPhoneNumber(phoneNumber);
//        user.setAddress(address);
//        user.setAvatar(avatar);
//        user.setPassword(password);
//        user.setEmail(email);
//        System.out.println(user.getId());
//        System.out.println(user.getUsername());
//        System.out.println(user.getPhoneNumber());
//        System.out.println(user.getAddress());
//        System.out.println(user.getAvatar());
//
//        userService.saveUser(user);
//        return user;
//    }

//    {
//        "id": "user1",
//            "password": "123",
//            "username": "ha",
//            "avatar": "https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png",
//            "email": "a@gmail.com",
//            "phoneNumber": "0",
//            "address": "DiaChi"
//    }


    //http://localhost:8085/get
    @GetMapping("/get")
    public List<User> get() {
        List<User> user = userService.findAll();

        return user;
    }

}


/*   @PostMapping("/add")
    public User add() {

        User user = new User();
        user.setId("user1");
        user.setUsername("ha");
        user.setPhoneNumber("0");
        user.setAddress("DiaChi");
        user.setAvatar("https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png");
        user.setPassword("123");
        user.setEmail("a@gmail.com");
        userService.saveUser(user);
        return user;
    }*/