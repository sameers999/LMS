package com.bridgelabz.lms.repository;

import com.bridgelabz.lms.model.BankInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository<BankInfo,Long> {

}
