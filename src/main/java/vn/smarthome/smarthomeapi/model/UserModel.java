package vn.smarthome.smarthomeapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private String id;

    private String password;

    private String username;

    private String avatar;

    private String email;

    private String phoneNumber;

    private String address;
}
