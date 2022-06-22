package com.bridgelabz.lms.controller;

import com.bridgelabz.lms.dto.CandidateDTO;
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

import javax.validation.Valid;
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

    /**
     * @param importCustomers
     * @return Ability to transfer CSV to MongoDB
     */

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

    /**
     *
     * @param candidateDTO
     * @return  Ability to Add candidate
     */
    @PostMapping("/addCandidate")
    public ResponseEntity<ResponseDTO> createUser(@Valid @RequestBody CandidateDTO candidateDTO) {
        String candidate = iCandidateService.insertCandidate(candidateDTO);
        ResponseDTO response = new ResponseDTO(" Candidate Added Successfully !!!", candidate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param getAll
     * @return Ability to gel All candidate details
     */
    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAll() {
        List<Candidate> listOfUsers = iCandidateService.getAll();
        ResponseDTO dto = new ResponseDTO("Candidate retrieved successfully (:", listOfUsers);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     * @param candidateId
     * @return Ability to get candidate by Id
     */

    @GetMapping("/get/{candidateId}")
    ResponseEntity<ResponseDTO> getById(@PathVariable long candidateId) {
        Candidate candidate = iCandidateService.getById(candidateId);
        ResponseDTO response = new ResponseDTO("Candidate Id found", candidate);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     *
     * @param token
     * @return Ability to get candidate details by token
     */

    @GetMapping(value = "/getByToken/{token}")
    public ResponseEntity<String> getBookDataById(@PathVariable String token) {
        Candidate candidate = iCandidateService.getByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id (:", candidate);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     *
     * @param token
     * @param candidateDTO
     * @return Ability to update candidate details by token
     */
    @PutMapping("/updateCandidateByToken/{token}")
    public ResponseEntity<String> updateRecordById(@PathVariable String token,@Valid @RequestBody CandidateDTO candidateDTO) {
        Candidate updateRecord = iCandidateService.updateRecordByToken(token, candidateDTO);
        ResponseDTO dto = new ResponseDTO(" Candidate Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    /**
     *
     * @param status
     * @return Ability to get candidate details by candidate status
     */
    @GetMapping("/getBy/{status}")
    public ResponseEntity<ResponseDTO> getHiredCandidatesNotHiredCandidates(@PathVariable String status) {
        List candidate = iCandidateService.hiredCondidate(status);
        ResponseDTO response = new ResponseDTO("Requested HiredCandidate or Not HiredCandidate : ", candidate);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    /**
     *
     * @param status
     * @return Ability to count how many are hired and how many are not hired
     */
    @GetMapping("/count/{status}")
    public ResponseEntity<ResponseDTO> getCount1 (@PathVariable  String status){
        long hiredCandidate = iCandidateService.count1(status);
        ResponseDTO response = new ResponseDTO("Candidates Count",hiredCandidate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param count
     * @return Ability to count how many candidate are there
     */

    @GetMapping("/count")
    public ResponseEntity<ResponseDTO> getCount (){
        long hiredCandidate = iCandidateService.count();
        ResponseDTO response = new ResponseDTO("Candidates Count",hiredCandidate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param token
     * @return Ability to send message regarding offer letter and shortlist by token
     */
    @PostMapping("/jobofferByToken/{token}")
    public ResponseEntity<ResponseDTO> sendOffer(@PathVariable String token){
        Candidate hiredCandidate = iCandidateService.jobOfferMail(token);
        ResponseDTO response = new ResponseDTO("Email sent successfully", hiredCandidate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return Ability to send message regarding offer letter and shortlist by token
     */

    @PostMapping("/jobofferById/{id}")
    public ResponseEntity<ResponseDTO> sendOffer(@PathVariable long id){
        Candidate hiredCandidate = iCandidateService.jobOfferMail(id);
        ResponseDTO response = new ResponseDTO("Email sent successfully", hiredCandidate);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
