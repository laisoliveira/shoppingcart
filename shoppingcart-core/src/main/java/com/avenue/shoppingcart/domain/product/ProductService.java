package com.avenue.shoppingcart.domain.product;

import java.util.Collection;

import com.avenue.shoppingcart.exception.InvalidProductOperationException;

public interface ProductService {

	Collection<Product> listProducts();
	
	Product loadProductDetailsBy(Long productId);

	void increaseProductStock(Long productId, Integer quantity) throws InvalidProductOperationException;
	
	void decreaseProductStock(Long productId, Integer quantity) throws InvalidProductOperationException;
	
}
