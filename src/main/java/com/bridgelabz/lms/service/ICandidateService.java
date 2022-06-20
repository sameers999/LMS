package com.bridgelabz.lms.service;

import com.bridgelabz.lms.model.Candidate;

import java.util.List;

public interface ICandidateService {
    List<Candidate> getAll();
    Candidate getById(long candidateId);

    List<Candidate> hiredCondidate(String status);
}
