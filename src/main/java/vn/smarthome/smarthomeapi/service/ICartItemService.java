package vn.smarthome.smarthomeapi.service;

import vn.smarthome.smarthomeapi.entity.CartItem;

import java.util.List;
import java.util.Optional;

public interface ICartItemService {

    void delete(CartItem entity);

    void deleteById(Integer cartItemId);

    long count();

    Optional<CartItem> findById(Integer cartItemId);

    void saveCartItem(CartItem cartItemEntity);

    void deleteCartItemById(Integer cartItemId);

//    CartItem getCartItemByCartIdAndProductId(Integer cartId, Integer productId);
//
//    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.products.productId = :productId")
//    CartItem findByCartIdAndProductId(int cartId, int productId);

    List<CartItem> getCartItemsByUserId(int id);

    List<CartItem> findAll();

}
