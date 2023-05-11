package vn.smarthome.smarthomeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.smarthome.smarthomeapi.entity.Cart;
import vn.smarthome.smarthomeapi.entity.Order;
import vn.smarthome.smarthomeapi.entity.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT c FROM Order c WHERE c.user.id = :id")
    List<Order> listOrderByUserId(@Param("id") int id);

    List<Order> findByUser(User user);
    List<Order> findByUserId(String id);
}
