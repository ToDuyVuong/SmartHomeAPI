package vn.smarthome.smarthomeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.smarthome.smarthomeapi.entity.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    @Query("SELECT c FROM OrderItem c WHERE c.order.orderId = :orderId")
    List<OrderItem> listOrderItemsByOrderId(@Param("orderId") int orderId);

    @Query("SELECT c.product.productId FROM OrderItem c WHERE c.order.orderId = :orderId")
    List<Integer> listProductIdByOrderId(@Param("orderId") int orderId);
}