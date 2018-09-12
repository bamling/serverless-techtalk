package com.example.faas.aws.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.HashMap;
import java.util.List;

public class NotesRepository {

    private DynamoDBMapper dynamoDBMapper;

    public NotesRepository() {
        final DynamoDBMapperConfig mapperConfig = DynamoDBMapperConfig.builder()
                .withTableNameOverride(new DynamoDBMapperConfig.TableNameOverride(Note.NOTES_TABLE_NAME))
                .build();

        final DynamoDBAdapter dbAdapter = DynamoDBAdapter.getInstance();
        dynamoDBMapper = dbAdapter.createDynamoDBMapper(mapperConfig);
    }

    public void save(final Note note) {
        dynamoDBMapper.save(note);
    }

    public Note get(final String id) {
        final HashMap<String, AttributeValue> attributes = new HashMap<>();
        attributes.put(":v1", new AttributeValue().withS(id));

        final DynamoDBQueryExpression<Note> queryExpression = new DynamoDBQueryExpression<Note>()
                .withKeyConditionExpression("id = :v1")
                .withExpressionAttributeValues(attributes);

        final PaginatedQueryList<Note> result = dynamoDBMapper.query(Note.class, queryExpression);
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public List<Note> list() {
        final DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDBMapper.scan(Note.class, scanExpression);
    }

    public void delete(final String id) {
        final Note note = get(id);
        if (note != null) {
            dynamoDBMapper.delete(note);
        }
    }
}
