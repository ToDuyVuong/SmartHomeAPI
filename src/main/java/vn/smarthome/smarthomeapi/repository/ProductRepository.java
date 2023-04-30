package vn.smarthome.smarthomeapi.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.smarthome.smarthomeapi.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findById(int id);

    List<Product> findByCategory_CategoryId(int categoryId);


    List<Product> findByNameContaining(String name);
    List<Product> findByName(String name);

    List<Product> findByPrice(Long price);


    List<Product> findByQuantity(int quantity);

    List<Product> findByCategoryCategoryId(Integer categoryId);

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :id")
    List<Product> listProductByCategoryId(@Param("id") int id);

    @Modifying
    @Query("UPDATE Product p SET p.quantity = 0 WHERE p.productId = :id")
    void updateProductQuantityToZeroById(@Param("id") int id);


    ///////////


    @Query(value="Select * From product p ORDER BY p.quantity DESC LIMIT 12;",nativeQuery = true)
    List<Product> findTop12ProductBestSellers();

    @Query(value="Select * From product p ORDER BY p.created_at DESC LIMIT 12;",nativeQuery = true)
    List<Product> findTop12ProductNewArrivals();

    Page<Product> findAllByCategoryCategoryId(int id, Pageable pageable);

//    Product findById(int id);

//    @Query(value="select * from product where product.product_name like %?1% and product.category_id= ?2",nativeQuery = true)
//    Page<Product> findByProduct_NameAndCategory_idContaining(String name, int category_id, Pageable pageable);

    @Query(value="select * from product where product.name like %?1%",nativeQuery = true)
    Page<Product> findByNameContaining(String name, Pageable pageable);

//    @Query(value="select * from product p where p.category_id = ?1 ORDER BY p.sold DESC LIMIT 4;",nativeQuery = true)
//    List<Product> findTop4ProductByCategory_id(int id);
}

