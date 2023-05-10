package vn.smarthome.smarthomeapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.smarthome.smarthomeapi.entity.Order;
import vn.smarthome.smarthomeapi.entity.OrderItem;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private Order order;
    private List<OrderItem> orderItemList;



}
