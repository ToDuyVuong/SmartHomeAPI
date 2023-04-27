package vn.smarthome.smarthomeapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.smarthome.smarthomeapi.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
    User findById(String id);
    void deleteById(String id);

    User findByUsername(String username);

}
