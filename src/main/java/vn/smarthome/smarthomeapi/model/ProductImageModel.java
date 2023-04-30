package vn.smarthome.smarthomeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import vn.smarthome.smarthomeapi.entity.Product;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageModel {

    private Integer productImageId;
    private String image;
    private Product product;
}
