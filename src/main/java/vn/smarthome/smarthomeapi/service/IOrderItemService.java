package vn.smarthome.smarthomeapi.service;


import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import vn.smarthome.smarthomeapi.entity.Order;
import vn.smarthome.smarthomeapi.entity.OrderItem;

import java.util.List;

public interface IOrderItemService {

    void deleteAll();

    void deleteById(Integer orderItemId);

    long count();

    List<OrderItem> findAllById(Iterable<Integer> orderItemIds);

    List<OrderItem> findAll(Sort sort);

    List<OrderItem> findAll();

    OrderItem saveOrUpdate(OrderItem orderItem);


    @Query("SELECT c FROM OrderItem c WHERE c.order.orderId = :orderId")
    List<OrderItem> listOrderItemsByOrderId(int orderId);
    @Query("SELECT c.product.productId FROM OrderItem c WHERE c.order.orderId = :orderId")
    List<Integer> listProductIdByOrderId(int orderId);

    <S extends OrderItem> S save(S entity);

    List<OrderItem> findByOrder(Order order);
}
