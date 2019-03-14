package com.cg.dynamodb.service;


import com.cg.dynamodb.model.Product;

/**
 * @author yparanja
 *
 */
public interface IProductService {

	/**
	 * @param product
	 * @return product name
	 */
	public String createProduct(Product product);

	/**
	 * @param id
	 * @return product object
	 */
	public Product getProduct(String id);

	/**
	 * @param id
	 */
	public void deleteProduct(String id);

	/**
	 * @param product
	 * @return product object
	 */
	public Product updateProduct(Product product);

}
