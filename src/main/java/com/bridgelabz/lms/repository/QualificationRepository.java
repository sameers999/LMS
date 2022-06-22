package com.bridgelabz.lms.repository;

import com.bridgelabz.lms.model.QualificationInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QualificationRepository extends MongoRepository<QualificationInfo,Long> {

}
