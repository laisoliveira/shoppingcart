package com.avenue.shoppingcart.webapp.beans;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.avenue.shoppingcart.domain.cart.ShoppingCartService;
import com.avenue.shoppingcart.domain.product.Product;
import com.avenue.shoppingcart.domain.product.ProductService;

@Named
@Scope("request")
public class ProductDetailsBean {

	@Inject
	private ProductService productService;
	
	@Inject
	private ShoppingCartService shoppingCartService;
	
	private String productQuantity;
	private Product product;
	private Long productId;
	
	public String showProductDetails(){
		product = productService.loadProductDetailsBy(productId);
		return "productDetail";
	}
	
	public void addToCart(){
		try {
			shoppingCartService.addNewItem(getCartId(), productId, Integer.parseInt(productQuantity));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,e.getMessage(), null));
		}
		setProduct(productService.loadProductDetailsBy(productId));
	}

	private String getCartId(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return session.getId();
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	
}
