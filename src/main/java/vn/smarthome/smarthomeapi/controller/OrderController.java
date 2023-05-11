package vn.smarthome.smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.smarthome.smarthomeapi.entity.*;
import vn.smarthome.smarthomeapi.model.OrderItemModel;
import vn.smarthome.smarthomeapi.request.OrderRequest;
import vn.smarthome.smarthomeapi.service.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ICartService cartService;
    @Autowired
    IUserService userService;
    @Autowired
    IProductService productService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IOrderItemService orderItemService;


    @PostMapping("/newOrder")
    public ResponseEntity<Order> addToCart(@RequestBody OrderRequest orderRequest) {

        try {
            Order order = orderRequest.getOrder();
            List<OrderItem> orderItemList = orderRequest.getOrderItemList();

            // Save the order and establish the association
            orderService.save(order);

            User user = userService.findById(order.getUser().getId());
            for (OrderItem orderItem : orderItemList) {
                if (orderItem.getProduct().getQuantity() < orderItem.getQuantity()) {
                    orderItem.setQuantity(orderItem.getProduct().getQuantity());
                }

                // Establish the association between order and order item
                orderItem.setOrder(order);
                orderItemService.save(orderItem);

                Product product = productService.findById(orderItem.getProduct().getProductId()).get();
                product.setQuantity(product.getQuantity() - orderItem.getQuantity());
                product.setSold(orderItem.getQuantity() + product.getSold());
                productService.save(product);

                Cart cart = cartService.findByUserAndProduct(user, product);
                cartService.delete(cart);
            }

            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/listOrder")
    public ResponseEntity<List<Order>> listOrder(@RequestParam String id) {
        System.out.println("idUS: " + id);
        try {
            User user = userService.findById(id);
            System.out.println("userOK: " + user.getEmail());
            List<Order> orders = orderService.findByUser(userService.findById(id));
            List<Order>  orderList = orderService.findAll();
            System.out.println("orders: " + orderList);
            return ResponseEntity.ok(orderList);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<Map<String, String>> test(@RequestParam String id) {
        System.out.println("test" + id);

        User user = userService.findById(id);
        System.out.println("userOK: " + user.getEmail());


        return ResponseEntity.ok(Collections.singletonMap("message", "OK"));
    }

    @GetMapping("/orderDetail")
    public ResponseEntity<List<OrderItem>> getOrderDetail(@RequestParam String orderId) {
        System.out.println("orderDetail id: " + orderId);
        try {
            List<OrderItem> orderItems = orderItemService.findByOrder(orderService.findById(Integer.parseInt(orderId)).get());


            System.out.println("orderDetailorders: " + orderItems);
            return ResponseEntity.ok(orderItems);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}

