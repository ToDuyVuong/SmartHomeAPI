package vn.smarthome.smarthomeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.smarthome.smarthomeapi.entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemModel {

    private Integer cartItemId;
    private int quantity;
    private User user;
}
