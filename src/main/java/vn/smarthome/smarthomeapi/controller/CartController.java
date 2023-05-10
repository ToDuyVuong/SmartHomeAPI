package vn.smarthome.smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/addProductToCart")
    public ResponseEntity<List<Cart>> addToCart(@RequestBody Cart cart) {
        User user = userService.findById(cart.getUser().getId());
        Product product = cart.getProduct();
        Cart existingCart = cartService.findByUserAndProduct(user, product);


        if (existingCart != null) {
            if (product.getQuantity() < cart.getQuantity() + existingCart.getQuantity()) {
                cart.setQuantity(product.getQuantity());
                existingCart.setQuantity(0);
            }
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            cartService.save(existingCart);
            List<Cart> list = cartService.findByUser(user);
            return ResponseEntity.ok(list);
        } else {
            if (product.getQuantity() < cart.getQuantity()) {
                cart.setQuantity(product.getQuantity());
            }
            cartService.save(cart);
            List<Cart> list = cartService.findByUser(user);
            return ResponseEntity.ok(list);
        }
    }

    @PostMapping("/minusProductToCart")
    public ResponseEntity<List<Cart>> minusProductToCart(@RequestBody Cart cart) {
        System.out.println("cartcartcart");

        User user = userService.findById(cart.getUser().getId());
        Product product = cart.getProduct();
        Cart existingCart = cartService.findByUserAndProduct(user, product);

            if (product.getQuantity() < cart.getQuantity() + existingCart.getQuantity()) {
                cart.setQuantity(product.getQuantity());
                existingCart.setQuantity(0);
            }
            existingCart.setQuantity(existingCart.getQuantity() - 1);
            cartService.save(existingCart);
            List<Cart> list = cartService.findByUser(user);
            return ResponseEntity.ok(list);

    }

    @GetMapping("/view")
    public ResponseEntity<List<Cart>> getCartItems(@RequestParam String id) {
        User user = new User();
        user.setId(id);
        if (user != null) {
            List<Cart> list = cartService.findByUser(user);
            return new ResponseEntity<List<Cart>>(list, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remove/{cartId}")
    public void removeProductFromCart(@PathVariable("cartId") Integer cartId) {
        cartService.deleteById(cartId);
    }

}
