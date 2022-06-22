package com.bridgelabz.lms.service;

import com.bridgelabz.lms.dto.CandidateDTO;
import com.bridgelabz.lms.exception.CandidateException;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.repository.CandidateRepository;
import com.bridgelabz.lms.util.EmailSenderService;
import com.bridgelabz.lms.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements ICandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private TokenUtility util;
    @Autowired
    private EmailSenderService mailService;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public String insertCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate(candidateDTO);
        //generate sequence
        candidate.setId(sequenceGeneratorService.getSequenceNumber(Candidate.SEQUENCE_NAME));
        candidateRepository.save(candidate);
        String token = util.createToken((int) candidate.getId());
        mailService.sendEmail(candidate.getEmail(), "Welcome " + candidate.getFirstName(),
                " Your candidate application submitted successfully!! . Please Click here to get data-> " +
                        "http://localhost:8071/candidateDetails/getByToken/" + token);

        return token;
    }


    @Override
    public List<Candidate> getAll() {
        List<Candidate> getCandidates = candidateRepository.findAll();
        if (getCandidates.isEmpty()) {
            throw new CandidateException(HttpStatus.NOT_FOUND, "There is not data added yet!!");
        } else
            return getCandidates;
    }

    @Override
    public Candidate getById(long candidateId) {
        Optional<Candidate> candidate = candidateRepository.findById(candidateId);
        if (candidate.isPresent()) {
            return candidate.get();
        } else
            throw new CandidateException(HttpStatus.NOT_FOUND, "This Id is not found! ");
    }

    @Override
    public Candidate getByToken(String token) {
        long id = util.decodeToken(token);
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            return candidate.get();
        } else {
            throw new CandidateException("Exception with id" + id + "does not exist!!");
        }
    }

    @Override
    public Candidate updateRecordByToken(String token, CandidateDTO candidateDTO) {
        long id = util.decodeToken(token);
        Optional<Candidate> candidate = candidateRepository.findById(id);
        if (candidate.isPresent()) {
            Candidate newCandidate = new Candidate(id, candidateDTO);
            candidateRepository.save(newCandidate);
            return newCandidate;
        } else {
            throw new CandidateException(HttpStatus.NOT_FOUND, "Candidate not found by this token");
        }
    }

    @Override
    public List<Candidate> hiredCondidate(String status) {
        List<Candidate> candidate = candidateRepository.findCandidateByStatus(status);
        if (candidate.isEmpty()) {
            throw new CandidateException(HttpStatus.NOT_FOUND, "There are no candidate got hired yet!!");
        }
        return candidate;
    }

    @Override
    public long count1(String status) {
        return candidateRepository.countByStatusEquals(status);
    }

    @Override
    public long count() {
        return candidateRepository.count();
    }


    @Override
    public Candidate jobOfferMail(String token) {
        long id = util.decodeToken(token);
        Optional<Candidate> hiredCandidate = candidateRepository.findById(id);
        if (hiredCandidate.isPresent()) {
            mailService.sendEmail(hiredCandidate.get().getEmail(), "Job Offer", " Hi " + hiredCandidate.get().getFirstName()
                    + "\n You have been Shortlisted for the software engineer position. Congratulations!");

        }
        return hiredCandidate.get();
    }

    @Override
    public Candidate jobOfferMail(long id) {
        Optional<Candidate> hiredCandidate = candidateRepository.findById(id);
        if (hiredCandidate.isPresent()) {
            mailService.sendEmail(hiredCandidate.get().getEmail(), "Job Offer", " Hi " + hiredCandidate.get().getFirstName()
                    + "\n You have been Shortlisted for the software engineer position. Congratulations!");

        }
        return hiredCandidate.get();
    }
}
