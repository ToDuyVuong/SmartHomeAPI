package vn.smarthome.smarthomeapi.service.Impl;

import org.springframework.stereotype.Service;
import vn.smarthome.smarthomeapi.entity.User;
import vn.smarthome.smarthomeapi.repository.UserRepository;
import vn.smarthome.smarthomeapi.service.IUserService;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository=userRepository;
    }
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
    @Override
    public User GetUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }
}
