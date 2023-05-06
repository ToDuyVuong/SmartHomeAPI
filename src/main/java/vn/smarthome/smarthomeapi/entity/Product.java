package vn.smarthome.smarthomeapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "product_id")

    private Integer productId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "active")
    private int active;
    @Column(name = "created_Date")
    private Date createdDate;

    @Column(name = "sold")
    private int sold;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImage> images;

    //    // relationship with Category
//    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false, foreignKey = @ForeignKey(name = "FK_category_product"))
    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Category category;

//    // relationship with OrderItem
//    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
//    private List<OrderItem> orderItems;

    //relationship with Cart
//    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
//    private Set<Cart> carts = new HashSet<>();
}
