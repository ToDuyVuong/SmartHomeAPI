package vn.smarthome.smarthomeapi.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import vn.smarthome.smarthomeapi.entity.Order;
import vn.smarthome.smarthomeapi.repository.OrderRepository;
import vn.smarthome.smarthomeapi.service.IOrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrderService {


    @Autowired
    OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public void deleteById(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
    @Override
    public Order saveOrUpdate(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Query("SELECT c FROM Order c WHERE c.user.id = :id")
    public List<Order> listOrderByUserId(int id) {
        return orderRepository.listOrderByUserId(id);
    }

    @Override
    public <S extends Order> S save(S entity) {
        return orderRepository.save(entity);
    }
}

