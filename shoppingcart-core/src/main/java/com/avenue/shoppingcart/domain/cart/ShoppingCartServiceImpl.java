package com.avenue.shoppingcart.domain.cart;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.avenue.shoppingcart.domain.product.Product;
import com.avenue.shoppingcart.domain.product.ProductService;
import com.avenue.shoppingcart.exception.InvalidProductOperationException;
import com.avenue.shoppingcart.exception.InvalidShoppingCartOperationException;

@Named
public class ShoppingCartServiceImpl implements ShoppingCartService{

	private ProductService productService;
	
	private Map<String, ShoppingCart> shoppingCartsCache = new HashMap<String, ShoppingCart>();
	
	@Inject public ShoppingCartServiceImpl(ProductService productService) {
		this.productService = productService;
	}
	
	public Collection<ShoppingCartItem> listCartItems(String cartId){
		return getShoppingCartBy(cartId).getShoppingCartItems();
	}
	
	public void addNewItem(String cartId, Long productId, Integer productQuantity) throws InvalidProductOperationException {
		productService.decreaseProductStock(productId, productQuantity);
		Product product = productService.loadProductDetailsBy(productId);
		ShoppingCartItem cartItem = new ShoppingCartItem(product, productQuantity);
		getShoppingCartBy(cartId).add(cartItem);
	}
	
	public void removeAllItems(String cartId) throws InvalidProductOperationException {
		ShoppingCart cart = getShoppingCartBy(cartId);
		Collection<ShoppingCartItem> cartItems= cart.getShoppingCartItems();
		Long productId;
		for (ShoppingCartItem cartItem : cartItems) {
			productId = cartItem.getProduct().getProductId();
			productService.increaseProductStock(productId, cartItem.getQuantity());
		}
		cart.clear();
	}
	
	public void updateItem(String cartId, Long productId, Integer productQuantity) throws InvalidShoppingCartOperationException, InvalidProductOperationException {
		ShoppingCart cart = getShoppingCartBy(cartId);
		ShoppingCartItem cartItem = findCartItemBy(cart,productId);
		
		if(productQuantity > cartItem.getQuantity()){
			productService.decreaseProductStock(productId, productQuantity-cartItem.getQuantity());
		}else if(productQuantity < cartItem.getQuantity()){
			productService.increaseProductStock(productId, cartItem.getQuantity()-productQuantity);
		}
		Product product = productService.loadProductDetailsBy(productId);
		cart.updateQuantity(new ShoppingCartItem(product, productQuantity));
	}
	
	public void removeProduct(String cartId,Long productId) throws InvalidProductOperationException {
		ShoppingCart cart = getShoppingCartBy(cartId);
		ShoppingCartItem cartItem = findCartItemBy(cart,productId);
		productService.increaseProductStock(productId, cartItem.getQuantity());
		cart.remove(cartItem);
	}
	
	public BigDecimal getPrice(String cartId) {
		return getShoppingCartBy(cartId).getPrice();
	}
	
	private ShoppingCartItem findCartItemBy(ShoppingCart cart, Long productId){
		Collection<ShoppingCartItem> cartItems= cart.getShoppingCartItems();
		for (ShoppingCartItem cartItem : cartItems) {
			if(cartItem.getProduct().getProductId().equals(productId)){
				return cartItem;
			}
		}
		return new ShoppingCartItem(new Product(productId), 0);
	}

	private ShoppingCart getShoppingCartBy(String cartId){
		if(!shoppingCartsCache.containsKey(cartId)){
			shoppingCartsCache.put(cartId, new ShoppingCart(cartId));
		}
		return shoppingCartsCache.get(cartId);
	}

}
