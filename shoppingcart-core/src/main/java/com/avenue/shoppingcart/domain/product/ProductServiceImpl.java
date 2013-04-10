package com.avenue.shoppingcart.domain.product;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import com.avenue.shoppingcart.exception.InvalidProductOperationException;

@Named
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;
	
	@Inject public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Collection<Product> listProducts() {
		return productRepository.list();
	}

	public Product loadProductDetailsBy(Long productId) {
		return productRepository.loadDetails(productId);
	}

	public void updateProductStock(Product product) {
		productRepository.update(product);
		
	}

	public void increaseProductStock(Long productId, Integer quantity) throws InvalidProductOperationException {
		Product product = this.loadProductDetailsBy(productId);
		product.increaseStock(quantity);
		this.updateProductStock(product);
	}

	public void decreaseProductStock(Long productId, Integer quantity) throws InvalidProductOperationException {
		Product product = this.loadProductDetailsBy(productId);
		product.decreaseStock(quantity);
		this.updateProductStock(product);
	}



}
