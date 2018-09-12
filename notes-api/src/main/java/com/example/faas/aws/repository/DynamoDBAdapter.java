package com.example.faas.aws.repository;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

public class DynamoDBAdapter {

    private static DynamoDBAdapter instance;
    private AmazonDynamoDB client;
    private DynamoDBMapper dynamoDBMapper;

    private DynamoDBAdapter() {
        client = AmazonDynamoDBClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public static DynamoDBAdapter getInstance() {
        if (instance == null) {
            instance = new DynamoDBAdapter();
        }

        return instance;
    }

    public AmazonDynamoDB getClient() {
        return client;
    }

    public DynamoDBMapper createDynamoDBMapper(final DynamoDBMapperConfig mapperConfig) {
        if (client != null) {
            dynamoDBMapper = new DynamoDBMapper(client, mapperConfig);
        }

        return dynamoDBMapper;
    }

}
