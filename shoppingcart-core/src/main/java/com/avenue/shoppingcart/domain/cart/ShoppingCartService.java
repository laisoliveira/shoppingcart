package com.avenue.shoppingcart.domain.cart;

import java.math.BigDecimal;
import java.util.Collection;

import com.avenue.shoppingcart.exception.InvalidProductOperationException;
import com.avenue.shoppingcart.exception.InvalidShoppingCartOperationException;


public interface ShoppingCartService {

	public Collection<ShoppingCartItem> listCartItems(String cartId);
	
	void addNewItem(String cartId, Long productId, Integer productQuantity) throws InvalidProductOperationException;
		
	void updateItem(String cartId, Long productId, Integer productQuantity) throws InvalidShoppingCartOperationException, InvalidProductOperationException;
		
	void removeAllItems(String cartId) throws InvalidProductOperationException;
	
	void removeProduct(String cartId,Long productId) throws InvalidProductOperationException ;
	
	BigDecimal getPrice(String cartId);

}
