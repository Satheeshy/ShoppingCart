package com.satheesh.shoppingBackend.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.satheesh.shoppingBackend.domain.Product;

public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors e) {
		// TODO Auto-generated method stub
		
		Product product = (Product) target;
		if(product.getFile() == null || product.getFile().getOriginalFilename().equals("")) {
			e.rejectValue("file", null,"Please select a file to upload");
			return;
		}
		if(! (product.getFile().getContentType().equals("image/jpeg") || product.getFile().getContentType().equals("image/png")) ||
				product.getFile().getContentType().equals("image/gif")) {
			e.rejectValue("file",null, "Please select an image to upload!");
			return;
		}
		
	}

}
