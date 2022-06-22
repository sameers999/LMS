package com.bridgelabz.lms.model;

import com.bridgelabz.lms.dto.QualificationDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Document(collection = "qualification_info")
public @Data class QualificationInfo {
    @Transient
    public static final String SEQUENCE_NAME = "qualification_sequence";

    @Id
    private long id;

    public String collegeName;

    public String higherEducation;

    public String percentage;

    public String yearOfPassing;

    public String course;

    public QualificationInfo(QualificationDto qualificationDto) {
        super();
        this.collegeName = qualificationDto.getCollegeName();
        this.higherEducation = qualificationDto.getHigherEducation();
        this.percentage = qualificationDto.getPercentage();
        this.yearOfPassing = qualificationDto.getYearOfPassing();
        this.course = qualificationDto.getCourse();

    }

    public QualificationInfo(long id, QualificationDto qualificationDto) {
        this.id=id;
        this.collegeName = qualificationDto.getCollegeName();
        this.higherEducation = qualificationDto.getHigherEducation();
        this.percentage = qualificationDto.getPercentage();
        this.yearOfPassing = qualificationDto.getYearOfPassing();
        this.course = qualificationDto.getCourse();
    }
}
