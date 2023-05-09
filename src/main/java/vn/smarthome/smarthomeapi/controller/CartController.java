package vn.smarthome.smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.smarthome.smarthomeapi.entity.Cart;
import vn.smarthome.smarthomeapi.entity.Product;
import vn.smarthome.smarthomeapi.entity.User;
import vn.smarthome.smarthomeapi.service.ICartService;
import vn.smarthome.smarthomeapi.service.IProductService;
import vn.smarthome.smarthomeapi.service.IUserService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ICartService cartService;
    @Autowired
    IUserService userService;
    @Autowired
    IProductService productService;

    @PostMapping("/add")
    public ResponseEntity<List<Cart>> addToCart(@RequestBody Cart cart) {
        User user = userService.findById(cart.getUser().getId());
        Product product = cart.getProduct();
        Cart existingCart = cartService.findByUserAndProduct(user, product);


        if (existingCart != null) {
            if(product.getQuantity() < cart.getQuantity()+existingCart.getQuantity()){
                cart.setQuantity(product.getQuantity());
                existingCart.setQuantity(0);
            }
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            cartService.save(existingCart);
            List<Cart> list = cartService.findByUser(user);
            return ResponseEntity.ok(list);
        } else {
            if(product.getQuantity() < cart.getQuantity()){
                cart.setQuantity(product.getQuantity());
            }
            cartService.save(cart);
            List<Cart> list = cartService.findByUser(user);
            return ResponseEntity.ok(list);
        }
    }

}
