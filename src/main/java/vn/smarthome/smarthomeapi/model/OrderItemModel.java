package vn.smarthome.smarthomeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.smarthome.smarthomeapi.entity.Order;
import vn.smarthome.smarthomeapi.entity.Product;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemModel {

    private Integer orderItemId;
    private int quantity;
    private Long price;
    private Product product;
    private Order order;
}
