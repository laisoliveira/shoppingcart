package com.avenue.shoppingcart.domain.product;
import java.util.Collection;


public interface ProductRepository {

	void save(Product product);
	
	void update(Product product);
	
	void remove(Product product);
	
	Product loadDetails(Long productId);
	
	Collection<Product> list();
}
