package com.spring.aws.service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spring.aws.config.DynamoDBConfig;
import com.spring.aws.model.StudentInfo;
import com.spring.aws.model.StudentPayload;
import com.spring.aws.model.StudentResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public Optional<StudentResponse> saveUpdatedStudentsRecordsInPostgresSqlDb(StudentPayload payload, Context context) {

        DynamoDBConfig dynamoDBMapper = new DynamoDBConfig();
        context.getLogger().log("\n payload in student service class : " + new Gson().toJson(payload));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            String inputRequestJson = objectMapper.writeValueAsString(payload);
            StudentResponse reqData = gson.fromJson(inputRequestJson, StudentResponse.class);
            boolean status = saveStudentInfoInDB(context, reqData, dynamoDBMapper.dynamoDBMapper());
            reqData.setIsDataSavedIntoDB(status);
            context.getLogger().log("\n is data saved into Postgres-SQL DB : " + status);
            return Optional.of(reqData);

        } catch (Exception e) {
            context.getLogger().log("\n Exception in student service : " + e.getMessage());
        }
        return Optional.of(new StudentResponse());
    }

    private boolean saveStudentInfoInDB(Context context, StudentResponse reqData, DynamoDBMapper dynamoDBMapper) {

        context.getLogger().log("\n student data save method : ");
        StudentInfo studentInfo = new StudentInfo();
        BeanUtils.copyProperties(reqData, studentInfo);
        dynamoDBMapper.save(studentInfo);
        return true;
    }

}
