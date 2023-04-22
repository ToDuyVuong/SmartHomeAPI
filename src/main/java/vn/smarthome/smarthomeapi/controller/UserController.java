package vn.smarthome.smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.smarthome.smarthomeapi.entity.User;
import vn.smarthome.smarthomeapi.service.CloudinaryService;
import vn.smarthome.smarthomeapi.service.IMailService;
import vn.smarthome.smarthomeapi.service.IUserService;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

//    @Autowired
//    IMailService mailService;
//
//
//    @Autowired
//    CloudinaryService cloudinaryService;

    @PostMapping(path = "/signup", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<User> SignUp(String username, String fullname, String email, String password) {
        User user = userService.findById(username);
        if (user != null) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            String encodedValue = Base64.getEncoder().encodeToString(password.getBytes());
            String avatar = "https://haycafe.vn/wp-content/uploads/2022/02/Avatar-trang-den.png";

            User newUser = new User();
            newUser.setUsername(fullname);
            newUser.setId(username);
            newUser.setPassword(password);
            newUser.setEmail(email);
            newUser.setAvatar(avatar);
            newUser.setPhoneNumber("0");
            newUser.setAddress("DiaChi");

            userService.saveUser(newUser);

            System.out.println(newUser);

            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }
    }

}
