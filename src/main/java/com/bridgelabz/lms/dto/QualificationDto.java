package com.bridgelabz.lms.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

public @Data class QualificationDto {

    @NotNull
    public String collegeName;
    @NotNull
    public String higherEducation;
    @NotNull
    public String percentage;
    @NotNull
    public String yearOfPassing;
    @NotNull
    public String course;
}
