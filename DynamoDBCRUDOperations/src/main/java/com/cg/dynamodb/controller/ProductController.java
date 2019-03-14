package com.cg.dynamodb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dynamodb.model.Product;
import com.cg.dynamodb.service.IProductService;

/**
 * @author yparanja
 *
 */
@RestController
public class ProductController {

	@Autowired
	private IProductService productService;

	@PostMapping("/create")
	public ResponseEntity<String> createProduct(@RequestBody Product product) {
		String productname = productService.createProduct(product);
		return new ResponseEntity<String>("Successfull added product-details in DynamoDB : " + productname, HttpStatus.OK);
	}

	@GetMapping("/getproduct{id}")
	public ResponseEntity<String> getProduct(String id) {
		Product product = productService.getProduct(id);
		return new ResponseEntity<String>("product-details are here : " + product.toString(), HttpStatus.OK);
	}

	@DeleteMapping("/delete{id}")
	public ResponseEntity<String> deleteProduct(String id) {
		productService.deleteProduct(id);
		return new ResponseEntity<String>("Successfull deleted product-details from DynamoDB", HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		product = productService.updateProduct(product);
		return new ResponseEntity<String>("update successfully in dynamo-DB : " + product, HttpStatus.OK);
	}
}
