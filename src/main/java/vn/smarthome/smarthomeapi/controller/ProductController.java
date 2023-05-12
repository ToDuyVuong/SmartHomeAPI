package vn.smarthome.smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.smarthome.smarthomeapi.entity.Category;
import vn.smarthome.smarthomeapi.entity.Product;
import vn.smarthome.smarthomeapi.service.ICategoryService;
import vn.smarthome.smarthomeapi.service.IProductService;

import java.util.ArrayList;
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

    @GetMapping("/getLatestProduct")
    public ResponseEntity<List<Product>> getLatestProduct(){
        List<Product> list = productService.findAll();
        List<Product> lastFourProducts = new ArrayList<>();

        int a = list.size();
        int b = 0;
        while (a > 4) {
            list.remove(b);
            b++;
            a--;
        }

        int startIndex = Math.max(0, list.size() - 4); // Get the starting index for the loop

        for (int i = list.size() - 1; i > startIndex; i--) {
            Product product = list.get(i);
            lastFourProducts.add(product);
        }

        return new ResponseEntity<>(lastFourProducts, HttpStatus.OK);
    }

    @GetMapping("getProductByCategory")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String categoryId){
        List<Product> list = productService.findByCategoryCategoryId(Integer.parseInt(categoryId));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/productDetail")
    public ResponseEntity<Product> getProductDetail(@RequestParam String productId){
        Product product = productService.findById(Integer.parseInt(productId));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }




    @GetMapping("getAllProduct")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/searchProduct")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String search){
        List<Product> list = productService.findByNameContaining(search);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
