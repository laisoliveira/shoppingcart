package com.avenue.shoppingcart.webapp.beans;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.avenue.shoppingcart.domain.product.Product;
import com.avenue.shoppingcart.domain.product.ProductService;

@Named
@Scope("request")
public class ProductBrowserBean {

	@Inject
	private ProductService productService;
	
	private Collection<Product> featuredProducts;
	
	@PostConstruct
	private void init(){
		listFeaturedProducts();
	}
	
	public void listFeaturedProducts(){
		featuredProducts = productService.listProducts();
	}
	
	public Collection<Product> getFeaturedProducts() {
		return featuredProducts;
	}

	public void setFeaturedProducts(List<Product> featuredProducts) {
		this.featuredProducts = featuredProducts;
	}

}
