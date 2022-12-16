package com.spring.aws.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {

    private String studentId;
    private String name;
    private String email;
    private Department department;
    private Boolean isDataSavedIntoDB;
}
