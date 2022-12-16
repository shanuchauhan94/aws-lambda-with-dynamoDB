package com.spring.aws.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
@ToString
@DynamoDBDocument
public class Department implements Serializable {

    @DynamoDBAttribute
    private String departmentId;
    @DynamoDBAttribute
    private String departmentName;
}
