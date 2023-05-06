package vn.smarthome.smarthomeapi.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.smarthome.smarthomeapi.entity.Category;
import vn.smarthome.smarthomeapi.service.ICategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryAdminController {


    @Autowired
    ICategoryService categoryService;


    @GetMapping("/add")
    public ModelAndView showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return new ModelAndView("add-category") ;
    }

    @PostMapping("/save")
    public ModelAndView saveCategory(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return new ModelAndView("redirect:/category/list");
    }

    @GetMapping("/list")
    public ModelAndView showCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return new ModelAndView ("list-category");
    }

    @GetMapping("/edit/{categoryId}")
    public ModelAndView editCategory(@PathVariable Integer categoryId) {
        ModelAndView mav = new ModelAndView("edit-category");
        Category category = categoryService.findById(categoryId).get();

        mav.addObject("category", category);
        return mav;
    }

    @PostMapping("/edit/{categoryId}")
    public ModelAndView saveEditedCategory(@ModelAttribute("category") Category category,
                                           @PathVariable("categoryId") Integer categoryId) {
        category.setCategoryId(categoryId);
        categoryService.save(category);
        return new ModelAndView("redirect:/category/list");
    }

    @GetMapping("/delete/{categoryId}")
    public ModelAndView deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryService.deleteById(categoryId);
        return new ModelAndView("redirect:/category/list");
    }


}






