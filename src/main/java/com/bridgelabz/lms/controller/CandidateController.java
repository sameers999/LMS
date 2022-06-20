package com.bridgelabz.lms.controller;

import com.bridgelabz.lms.dto.ResponseDTO;
import com.bridgelabz.lms.model.Candidate;
import com.bridgelabz.lms.service.ICandidateService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidateDetails")
public class CandidateController {
    @Autowired
    private ICandidateService iCandidateService;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @PostMapping("/importCustomers")
    public void importCsvToDBJob() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
                 JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAll() {
        List<Candidate> listOfUsers = iCandidateService.getAll();
        ResponseDTO dto = new ResponseDTO("Candidate retrieved successfully (:", listOfUsers);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/findById/{candidateId}")
    ResponseEntity<ResponseDTO> getById(@PathVariable long candidateId) {
        Candidate candidate = iCandidateService.getById(candidateId);
        ResponseDTO response = new ResponseDTO("Candidate Id found", candidate);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/get/{status}")
    public ResponseEntity<ResponseDTO> getHiredCandidatesNotHiredCandidates(@PathVariable String status) {
        List candidate = iCandidateService.hiredCondidate(status);
        ResponseDTO response = new ResponseDTO("Requested HiredCandidate or Not HiredCandidate : ", candidate);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }
}
