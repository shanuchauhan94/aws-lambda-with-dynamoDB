package com.spring.aws.service;

import com.amazonaws.services.lambda.runtime.Context;
import com.spring.aws.model.StudentPayload;
import com.spring.aws.model.StudentResponse;

import java.util.Optional;

public interface StudentService {

    Optional<StudentResponse> saveUpdatedStudentsRecordsInPostgresSqlDb(StudentPayload payload, Context context);

}
