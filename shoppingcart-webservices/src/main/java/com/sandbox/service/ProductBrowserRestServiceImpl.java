package com.sandbox.service;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import com.avenue.shoppingcart.domain.product.Product;
import com.avenue.shoppingcart.domain.product.ProductService;

@Named
public class ProductBrowserRestServiceImpl implements ProductBrowserRestService{

	@Inject
	private ProductService productService;
	
	
	public Collection<Product> getProducts() {
		return productService.listProducts();
	}

	public Product getProductByName(Long productId) {
		return productService.loadProductDetailsBy(productId);
	}
}
