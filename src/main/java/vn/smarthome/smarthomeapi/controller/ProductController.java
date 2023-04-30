package vn.smarthome.smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.smarthome.smarthomeapi.entity.Category;
import vn.smarthome.smarthomeapi.entity.Product;
import vn.smarthome.smarthomeapi.service.ICategoryService;
import vn.smarthome.smarthomeapi.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ICategoryService categoryService;
    @Autowired
    IProductService productService;

    @GetMapping("/getProductPupularIndex")




    public ResponseEntity<List<Product>> getProductPupularIndex(){
        List<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
