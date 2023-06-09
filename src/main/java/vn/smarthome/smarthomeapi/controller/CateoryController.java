package vn.smarthome.smarthomeapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vn.smarthome.smarthomeapi.entity.Category;
import vn.smarthome.smarthomeapi.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CateoryController {

    @Autowired
    ICategoryService categoryService;




    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> listcaCategories = categoryService.findAll();
        return new ResponseEntity<>(listcaCategories, HttpStatus.OK);
    }



}
