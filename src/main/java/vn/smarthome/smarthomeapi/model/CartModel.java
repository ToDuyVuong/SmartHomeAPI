package vn.smarthome.smarthomeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.smarthome.smarthomeapi.entity.Product;
import vn.smarthome.smarthomeapi.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartModel {

    private Integer cartId;
    private int quantity;
    private User user;
    private Product product;
}
