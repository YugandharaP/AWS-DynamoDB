package com.cg.dynamodb.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.cg.dynamodb.model.Product;

/**
 * @author yparanja
 *
 */
@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private DynamoDBMapper dynamoDBMapper;

	@Override
	public String createProduct(Product product) {
		dynamoDBMapper.save(product);
		return product.getProductName();
	}

	@Override
	public Product getProduct(String id) {
		return dynamoDBMapper.load(Product.class, id);
	}

	@Override
	public void deleteProduct(String id) {
		Product product = dynamoDBMapper.load(Product.class, id);
		dynamoDBMapper.delete(product);
	}

	@Override
	public Product updateProduct(Product product) {

		try {
			dynamoDBMapper.save(product, buildDynamoDBSaveExpression(product));
		} catch (Exception exception) {
			exception.getMessage();
		}
		return product;
	}

	private DynamoDBSaveExpression buildDynamoDBSaveExpression(Product product) {
		DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expected = new HashMap<>();
		expected.put("id", new ExpectedAttributeValue(new AttributeValue(product.getId()))
				.withComparisonOperator(ComparisonOperator.EQ));
		dynamoDBSaveExpression.setExpected(expected);
		return dynamoDBSaveExpression;
	}

}
