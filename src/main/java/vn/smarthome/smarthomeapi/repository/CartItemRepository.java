package vn.smarthome.smarthomeapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import vn.smarthome.smarthomeapi.entity.CartItem;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByUserId(int id);
//    CartItem findByCartCartIdAndProductsProductId(int cartId, int productId);

//    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.products.productId = :productId")
//    CartItem findByCartIdAndProductId(@Param("cartId") int cartId, @Param("productId") int productId);
}