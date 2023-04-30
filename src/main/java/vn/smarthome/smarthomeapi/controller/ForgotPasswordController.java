package vn.smarthome.smarthomeapi.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.smarthome.smarthomeapi.entity.User;
import vn.smarthome.smarthomeapi.model.MailModel;
import vn.smarthome.smarthomeapi.model.UserModel;
import vn.smarthome.smarthomeapi.service.IMailService;
import vn.smarthome.smarthomeapi.service.IUserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/forgot")
public class ForgotPasswordController {

    @Autowired
    IMailService mailService;

    @Autowired
    IUserService userService;


    @GetMapping(path = "/password")
    public ResponseEntity<List<String>> forgotPassword(@RequestParam String id) {
        User user = userService.findById(id);
        if (user != null) {
            int code = (int) Math.floor(((Math.random() * 899999) + 100000));
            MailModel mail = new MailModel();
            mail.setMailFrom("conc5288@gmail.com");
            mail.setMailTo(user.getEmail());
            mail.setMailSubject("Forgot Password");
            mail.setMailContent("Your code is: " + code);
            mailService.sendEmail(mail);
            System.out.println(code);
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(code));
            list.add(id);
//            String response = "{ \"code\": \"" + code + "\" }";
            return new ResponseEntity<List<String>>(list, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }






    @PostMapping(path = "/newpass")
    public ResponseEntity<String> ForgotNewPass(@RequestParam String id, @RequestParam("password") String password) {
        System.out.println("id: " + id);
        System.out.println("password: " + password);

        User user = userService.findById(id);
        if (user != null) {
            user.setPassword(password);
            userService.saveUser(user);
            System.out.println("password: " + password);
            return new ResponseEntity<String>(password, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
