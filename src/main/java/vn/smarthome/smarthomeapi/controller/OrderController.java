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
}


//    @PostMapping("/newOrder")
//    public ResponseEntity<Order> addToCart(@RequestBody OrderRequest orderRequest) {
//
//        try {
//            Order order = orderRequest.getOrder();
//            List<OrderItem> orderItemList = orderRequest.getOrderItemList();
//
//            // Save the order
//            orderService.save(order);
//
//            // Process each order item
//            for (OrderItem orderItem : orderItemList) {
//                Product product = orderItem.getProduct();
//
//                // Check if the requested quantity exceeds the available quantity
//                int availableQuantity = product.getQuantity();
//                int requestedQuantity = orderItem.getQuantity();
//                int quantityToProcess = Math.min(availableQuantity, requestedQuantity);
//
//                // Update the order item quantity
//                orderItem.setQuantity(quantityToProcess);
//                orderItemService.save(orderItem);
//
//                // Update the product quantity and sold values
//                product.setQuantity(availableQuantity - quantityToProcess);
//                product.setSold(product.getSold() + quantityToProcess);
//                productService.save(product);
//
//                // Delete the corresponding cart entry
//                Cart cart = cartService.findByUserAndProduct(order.getUser(), product);
//                if (cart != null) {
//                    cartService.delete(cart);
//                }
//            }
//
//            return ResponseEntity.ok(order);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }


//    @PostMapping("/newOrder")
//    public ResponseEntity<Order> addToCart(@RequestBody OrderRequest orderRequest) {
//
//        try {
//
//            Order order = orderRequest.getOrder();
//            List<OrderItem> orderItemList = orderRequest.getOrderItemList();
//
//            orderService.save(order);
//
//            User user = userService.findById(order.getUser().getId());
//            for (OrderItem orderItem : orderItemList) {
//                if (orderItem.getProduct().getQuantity() < orderItem.getQuantity()) {
//                    orderItem.setQuantity(orderItem.getProduct().getQuantity());
//                }
//                orderItemService.save(orderItem);
//
//                Product product = orderItem.getProduct();
//                product.setQuantity(product.getQuantity() - orderItem.getQuantity());
//                product.setSold(orderItem.getQuantity());
//                productService.save(product);
//
//                Cart cart = cartService.findByUserAndProduct(user, product);
//                cartService.delete(cart);
//            }
//
//            return ResponseEntity.ok(order);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//
//    }
//
//
//}
