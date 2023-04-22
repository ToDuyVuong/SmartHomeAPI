package vn.smarthome.smarthomeapi.service;

import vn.smarthome.smarthomeapi.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUser();

    User saveUser(User user);

    User updateUser(User user);

    void deleteUserById(String id);

    User GetUserByEmail(String email);


    List<User> findAll();

    User findByEmail(String email);

    User findById(String id);

    void deleteById(String id);
}
