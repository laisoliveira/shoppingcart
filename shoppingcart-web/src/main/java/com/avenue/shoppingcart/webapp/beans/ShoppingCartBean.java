package com.avenue.shoppingcart.webapp.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;

import com.avenue.shoppingcart.domain.cart.ShoppingCartItem;
import com.avenue.shoppingcart.domain.cart.ShoppingCartService;
import com.avenue.shoppingcart.domain.product.ProductService;
import com.avenue.shoppingcart.exception.InvalidProductOperationException;

@Named
@Scope("request")
public class ShoppingCartBean {

	@Inject
	private ProductService productService;
	
	@Inject
	private ShoppingCartService shoppingCartService;
	
	private List<ShoppingCartItem> shoppingCartItems;
	
	@PostConstruct
	public void init(){
		shoppingCartItems = new ArrayList<ShoppingCartItem>(shoppingCartService.listCartItems(getCartId()));
	}

	public void updateItem(ShoppingCartItem cartItem){
		try {
			shoppingCartService.updateItem(getCartId(), cartItem.getProduct().getProductId(),cartItem.getQuantity());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,e.getMessage(), null));
		}finally{
			shoppingCartItems = new ArrayList<ShoppingCartItem>(shoppingCartService.listCartItems(getCartId()));
		}
	}
	
	public void removeProductFromCart(ShoppingCartItem cartItem){
		try {
			shoppingCartService.removeProduct(getCartId(),cartItem.getProduct().getProductId());
		} catch (InvalidProductOperationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,e.getMessage(), null));
		}finally{
			shoppingCartItems = new ArrayList<ShoppingCartItem>(shoppingCartService.listCartItems(getCartId()));
		}
	}
	
	public void clearCart(){
		try {
			shoppingCartService.removeAllItems(getCartId());
		} catch (InvalidProductOperationException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR ,e.getMessage(), null));
		}finally{
			shoppingCartItems = new ArrayList<ShoppingCartItem>(shoppingCartService.listCartItems(getCartId()));
		}
	}
	
	public List<ShoppingCartItem> getShoppingCartItems() {
		return shoppingCartItems;
	}
	
	public BigDecimal getAmount() {
		return shoppingCartService.getPrice(getCartId());
	}
	
	private String getCartId(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		return session.getId();
	}
	
	
	
}
