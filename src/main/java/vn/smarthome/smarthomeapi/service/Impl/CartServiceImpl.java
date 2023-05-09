package vn.smarthome.smarthomeapi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.smarthome.smarthomeapi.entity.Cart;
import vn.smarthome.smarthomeapi.entity.Product;
import vn.smarthome.smarthomeapi.entity.User;
import vn.smarthome.smarthomeapi.repository.CartRepository;
import vn.smarthome.smarthomeapi.service.ICartService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    CartRepository cartItemRepository;

    public CartServiceImpl(CartRepository cartItemRepository) {

        this.cartItemRepository = cartItemRepository;
    }

    @Override
    public void saveCartItem(Cart cartItemEntity) {
        cartItemRepository.save(cartItemEntity);
    }

    @Override
    public void deleteCartItemById(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

//	@Override
//	public CartItemEntity getCartItemByCartIdAndProductId(Integer cartId, Integer productId) {
//		return cartItemRepository.findByCartCartIdAndProducts(cartId, productId);
//	}

//    @Override
//    public CartItem getCartItemByCartIdAndProductId(Integer cartId, Integer productId) {
//        return cartItemRepository.findByCartCartIdAndProductsProductId(cartId, productId);
//    }

//    @Override
//    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.cartId = :cartId AND ci.products.productId = :productId")
//    public CartItem findByCartIdAndProductId(int cartId, int productId) {
//        return cartItemRepository.findByCartIdAndProductId(cartId, productId);
//    }

    @Override
    public List<Cart> getCartItemsByUserId(int id) {
        return cartItemRepository.findByUserId(id);
    }

    @Override
    public List<Cart> findAll() {
        return cartItemRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Integer cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }

    @Override
    public long count() {
        return cartItemRepository.count();
    }

    @Override
    public void deleteById(Integer cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public void delete(Cart entity) {
        cartItemRepository.delete(entity);
    }


    @Override
    public List<Cart> findByUser(User user) {
        return cartItemRepository.findByUser(user);
    }

    @Override
    public Cart findByUserAndProduct(User user, Product product) {
        return cartItemRepository.findByUserAndProduct(user, product);
    }

    @Override
    public <S extends Cart> S save(S entity) {
        return cartItemRepository.save(entity);
    }
}