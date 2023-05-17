package vn.smarthome.smarthomeapi.controller.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.smarthome.smarthomeapi.service.*;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {
    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IProductImageService productImageService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IOrderItemService orderItemService;

    @GetMapping("/getAll")
    public ModelAndView showOrder(ModelMap modelMap) {
        modelMap.addAttribute("orders", orderService.findAll());
        return new ModelAndView("list-order");
    }
}
