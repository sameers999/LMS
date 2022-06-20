package com.bridgelabz.lms.repository;

import com.bridgelabz.lms.model.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableMongoRepositories
@Repository
public interface CandidateRepository extends MongoRepository<Candidate,Long> {

    List<Candidate> findCandidateByStatus(String status);
}
