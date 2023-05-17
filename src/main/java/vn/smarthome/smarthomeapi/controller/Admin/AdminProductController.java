package vn.smarthome.smarthomeapi.controller.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import vn.smarthome.smarthomeapi.entity.Category;
import vn.smarthome.smarthomeapi.entity.Product;
import vn.smarthome.smarthomeapi.entity.ProductImage;
import vn.smarthome.smarthomeapi.service.CloudinaryService;
import vn.smarthome.smarthomeapi.service.ICategoryService;
import vn.smarthome.smarthomeapi.service.IProductImageService;
import vn.smarthome.smarthomeapi.service.IProductService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/product")
public class AdminProductController {

    @Autowired
    IProductService productService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    HttpSession session;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    IProductImageService productImageService;


//    @GetMapping("/add")
//    public ModelAndView showAddCategoryForm(Model model) {
//        model.addAttribute("listCategories", categoryService.findAll());
//        model.addAttribute("product", new  Product());
//        return new ModelAndView("add-product") ;
//    }


    @GetMapping("/add")
    public ModelAndView DashboardAddProductView(Model model) {

        String addProduct = (String) session.getAttribute("addProduct");
        model.addAttribute("addProduct", addProduct);
        session.setAttribute("addProduct", null);
        List<Category> listCategories = categoryService.findAll();
        model.addAttribute("listCategories", listCategories);
        return new ModelAndView("add-product");

    }

    @PostMapping("/add")
    public ModelAndView DashboardAddProductHandel(Model model, @ModelAttribute("product_name") String product_name,
                                                  @ModelAttribute("price") String price, @ModelAttribute("availability") String availability,
                                                  @ModelAttribute("category") int category, @ModelAttribute("description") String description,
                                                  @ModelAttribute("listImage") MultipartFile[] listImage) throws Exception {

        if (listImage != null) {
            Category cate = categoryService.findById(category);
            System.out.println(cate);
            long millis = System.currentTimeMillis();
            Date create_at = new java.sql.Date(millis);
            Product newPro = new Product();
            newPro.setCreatedDate(create_at);
            newPro.setDescription(description);
            newPro.setActive(1);
            newPro.setPrice(Long.parseLong(price));
            newPro.setName(product_name);
            newPro.setQuantity(Integer.parseInt(availability));
            newPro.setSold(0);
            newPro.setCategory(cate);
            newPro = productService.save(newPro);
            for (MultipartFile y : listImage) {
                String urlImg = cloudinaryService.uploadFile(y);
                ProductImage img = new ProductImage();
                img.setProduct(newPro);
                img.setImage(urlImg);
                productImageService.save(img);
            }
            session.setAttribute("addProduct", "addProductSuccess");
            return new ModelAndView("redirect:/product/list");
        } else {
            return new ModelAndView("redirect:/product/add");
        }
    }

    @GetMapping("/list")
    public ModelAndView productView(Model model) {

            List<Category> listCategories = categoryService.findAll();
            Pageable pageable = PageRequest.of(0, 3);
            Page<Product> pageProduct = productService.findAll(pageable);
            model.addAttribute("pageProduct", pageProduct);
            model.addAttribute("listCategories", listCategories);
            return new  ModelAndView ("list-product");

    }

    @GetMapping("/edit/{id}")
    public ModelAndView DashboardMyProductEditView(@PathVariable int id, Model model) {

            List<Category> listCategories = categoryService.findAll();
            Product product = productService.findById(id);
            model.addAttribute("product", product);
            model.addAttribute("listCategories", listCategories);
            String editProduct = (String) session.getAttribute("editProduct");
            model.addAttribute("editProduct", editProduct);
            session.setAttribute("editProduct", null);
            return new  ModelAndView  ("edit-product");

    }

//    @PostMapping("/dashboard-myproducts/edit")
//    public String DashboardMyProductEditHandel(Model model, @ModelAttribute("product_id") int product_id,
//                                               @ModelAttribute("product_name") String product_name, @ModelAttribute("price") String price,
//                                               @ModelAttribute("availability") String availability, @ModelAttribute("category") int category,
//                                               @ModelAttribute("description") String description, @ModelAttribute("listImage") MultipartFile[] listImage)
//            throws Exception {
//        User admin = (User) session.getAttribute("admin");
//        if (admin == null) {
//            return "redirect:/signin-admin";
//        } else {
//            if (listImage != null) {
//                Category cate = categoryService.getCategoryById(category);
//                Product product = productService.getProductById(product_id);
////				System.out.println(cate);
////				long millis = System.currentTimeMillis();
////				Date create_at = new java.sql.Date(millis);
////				Product newPro = new Product();
//                product.setProduct_Name(product_name);
//                product.setPrice(Integer.parseInt(price));
//                product.setQuantity(Integer.parseInt(availability));
//                product.setCategory(cate);
//                product.setDescription(description);
//                productService.saveProduct(product);
//                for (MultipartFile y : listImage) {
//                    if (!y.isEmpty()) {
//                        String urlImg = cloudinaryService.uploadFile(y);
//                        ProductImage img = new ProductImage();
//                        img.setProduct(product);
//                        img.setUrl_Image(urlImg);
//                        productImageService.save(img);
//                    }
//                }
//                session.setAttribute("editProduct", "editProductSuccess");
//                return "redirect:/dashboard-myproducts/edit/" + product_id;
//            } else {
//                return "redirect:/dashboard-myproducts/edit/" + product_id;
//            }
//
//        }
//    }
//
//    @GetMapping("/dashboard-myproducts/delete-image/{id}")
//    public String DeleteImage(@PathVariable int id, HttpServletRequest request) {
//        String referer = request.getHeader("Referer");
//        productImageService.deleteById(id);
//        return "redirect:"+referer;
//    }

    @GetMapping("/{page}")
    public ModelAndView productPageView(@PathVariable int page, Model model) {

            List<Category> listCategories = categoryService.findAll();
            Pageable pageable = PageRequest.of(page, 3);
            Page<Product> pageProduct = productService.findAll(pageable);
            model.addAttribute("pageProduct", pageProduct);
            model.addAttribute("listCategories", listCategories);
            return new  ModelAndView("list-product");

    }
//
//    @PostMapping("/dashboard-myproduct/search")
//    public String DashboardMyproductSearch(@ModelAttribute("search-input") String search_input,
//                                           @ModelAttribute("category-selected") int category_selected, Model model) {
//        User admin = (User) session.getAttribute("admin");
//        if (admin == null) {
//            return "redirect:/signin-admin";
//        } else {
//            Page<Product> pageProduct = null;
//            Pageable pageable = PageRequest.of(0, 3);
//            if (category_selected > 0) {
//                pageProduct = productService.findByProduct_NameAndCategory_idContaining(search_input, category_selected,
//                        pageable);
//            } else {
//                pageProduct = productService.findByProduct_NameContaining(search_input, pageable);
//            }
//            List<Category> listCategories = categoryService.findAll();
//            String nameCategory = null;
//            if (category_selected == 0) {
//                nameCategory = null;
//            } else {
//                for (Category y : listCategories) {
//                    if (y.getId() == category_selected) {
//                        nameCategory = y.getCategory_Name();
//                    }
//                }
//            }
//            System.out.println(nameCategory);
//            model.addAttribute("pageProduct", pageProduct);
//            model.addAttribute("listCategories", listCategories);
//            model.addAttribute("search_dashboard", "search_dashboard");
//            model.addAttribute("search_input", search_input);
//            model.addAttribute("nameCategory", nameCategory);
//            session.setAttribute("search_input_dashboard", search_input);
//            session.setAttribute("category_selected", category_selected);
//            return "dashboard-myproducts";
//        }
//    }
//
//    @GetMapping("/dashboard-myproduct/search/{page}")
//    public String DashboardMyproductSearchPage(@PathVariable int page, Model model) {
//        User admin = (User) session.getAttribute("admin");
//        if (admin == null) {
//            return "redirect:/signin-admin";
//        } else {
//            String search_input = (String) session.getAttribute("search_input_dashboard");
//            int category_selected = (int) session.getAttribute("category_selected");
////			int category_selected = 0;
//            Page<Product> pageProduct = null;
//            Pageable pageable = PageRequest.of(page, 3);
//            if (category_selected > 0) {
//                pageProduct = productService.findByProduct_NameAndCategory_idContaining(search_input, category_selected,
//                        pageable);
//            } else {
//                pageProduct = productService.findByProduct_NameContaining(search_input, pageable);
//            }
//            List<Category> listCategories = categoryService.findAll();
//            model.addAttribute("pageProduct", pageProduct);
//            model.addAttribute("listCategories", listCategories);
//            model.addAttribute("search_dashboard", "search_dashboard");
//            model.addAttribute("search_input", search_input);
//            model.addAttribute("category_selected", category_selected);
//            session.setAttribute("search_input_dashboard", search_input);
//            return "dashboard-myproducts";
//        }
//    }



}
