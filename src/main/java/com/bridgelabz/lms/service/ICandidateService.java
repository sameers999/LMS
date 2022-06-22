package com.bridgelabz.lms.service;

import com.bridgelabz.lms.dto.CandidateDTO;
import com.bridgelabz.lms.model.Candidate;

import java.util.List;

public interface ICandidateService {
    String insertCandidate(CandidateDTO candidateDTO);

    List<Candidate> getAll();

    Candidate getById(long candidateId);

    Candidate getByToken(String token);

    Candidate updateRecordByToken(String token, CandidateDTO candidateDTO);

    List<Candidate> hiredCondidate(String status);

    long count1(String status);
    long count();
    Candidate jobOfferMail(String token);

    Candidate jobOfferMail(long id);
}
