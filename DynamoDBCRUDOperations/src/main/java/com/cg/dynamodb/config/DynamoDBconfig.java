package com.cg.dynamodb.config;

import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBconfig {

	@Autowired
	private Environment environment;

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(amazonDynamoDB);
	}

	@Bean
	public AmazonDynamoDB amazonDyanamoDB() {

		// System.out.println(awsSecretKey);
		// System.out.println(awsAccessKey);
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
						environment.getProperty("AwsDynamoDBEndpoint"), environment.getProperty("AwsRegion")))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
						environment.getProperty("AwsAccessKey"), environment.getProperty("AwsSecretkey"))))
				.build();
	}

}
