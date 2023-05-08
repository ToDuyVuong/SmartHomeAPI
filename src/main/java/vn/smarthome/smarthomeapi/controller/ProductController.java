package vn.smarthome.smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("getProductByCategory")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String categoryId){
        List<Product> list = productService.findByCategoryCategoryId(Integer.parseInt(categoryId));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }




    @GetMapping("getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
