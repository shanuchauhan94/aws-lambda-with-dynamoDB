package com.spring.aws.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.spring.aws.model.StudentPayload;
import com.spring.aws.model.StudentResponse;
import com.spring.aws.service.StudentService;
import com.spring.aws.service.StudentServiceImpl;

import java.util.Optional;

public class StudentAwsHandler implements RequestHandler<StudentPayload, StudentResponse> {

    @Override
    public StudentResponse handleRequest(StudentPayload studentPayload, Context context) {

        StudentService studentService = new StudentServiceImpl();

        try {
            context.getLogger().log("\n calling studentService in studentService handler");
            Optional<StudentResponse> response = studentService.saveUpdatedStudentsRecordsInPostgresSqlDb(studentPayload, context);
            if (response.isPresent()) {
                return response.get();
            }
        } catch (Exception e) {
            context.getLogger().log("\n Exception occurred in studentService handler : ");
            e.printStackTrace();
        }
        throw new RuntimeException("Exception occurred in studentService handler");
    }


}
