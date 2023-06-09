package vn.smarthome.smarthomeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import vn.smarthome.smarthomeapi.entity.Category;
import vn.smarthome.smarthomeapi.entity.OrderItem;
import vn.smarthome.smarthomeapi.entity.ProductImage;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductModel {
//    private Integer productId;
//    private String name;
//    private String description;
//    private List<ProductImage> images;
//    private Long price;
//    private int quantity;
//    private Category category;
//    private int active;
//    private Date createdDate;
//    private int sold;
////    private List<OrderItem> orderItems;

    private Integer productId;
    private String name;
    private String description;
    private Long price;
    private int quantity;
    private int active;
    private Date createdDate;
    private int sold;
    private List<ProductImage> images;
    private Category category;
//    private List<OrderItem> orderItems;
}