package vn.smarthome.smarthomeapi.service;


import org.springframework.data.jpa.repository.Query;
import vn.smarthome.smarthomeapi.entity.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    void deleteById(Integer orderId);

    long count();

    Optional<Order> findById(Integer orderId);

    List<Order> findAll();

    Order saveOrUpdate(Order order);

    @Query("SELECT c FROM Order c WHERE c.user.id = :id")
    List<Order> listOrderByUserId(int id);

}