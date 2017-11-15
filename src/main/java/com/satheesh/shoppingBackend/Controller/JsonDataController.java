package com.satheesh.shoppingBackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.satheesh.shoppingBackend.dao.ProductDAO;
import com.satheesh.shoppingBackend.domain.Product;

@RestController
@RequestMapping("/json/data")
public class JsonDataController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/admin/all/products")
	public List<Product> getAllProductsList() {		
		return productDAO.list();
				
	}	
	
	
	@RequestMapping("/all/products")
	public List<Product> getAllProducts() {
		
		return productDAO.listActiveProducts();
				
	}
	
	@RequestMapping("/category/{id}/products")
	public List<Product> getProductsByCategory(@PathVariable int id) {
		
		return productDAO.listActiveProductsByCategory(id);
				
	}
	
	
	@RequestMapping("/mv/products")
	public List<Product> getMostViewedProducts() {		
		return productDAO.getProductsByParam("views", 5);				
	}
		
	@RequestMapping("/mp/products")
	public List<Product> getMostPurchasedProducts() {		
		return productDAO.getProductsByParam("purchases", 5);				
	}
	

}
