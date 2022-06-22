package com.bridgelabz.lms.controller;

import com.bridgelabz.lms.dto.QualificationDto;
import com.bridgelabz.lms.dto.ResponseDTO;
import com.bridgelabz.lms.model.QualificationInfo;
import com.bridgelabz.lms.service.IQualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/qualification")
public class QualificationController {
    @Autowired
    private IQualificationService iQualificationService;

    /**
     *
     * @param qualificationDto
     * @return Ability to add qualification  of the candidate
     */
    @PostMapping("/addQualification")
    public ResponseEntity<ResponseDTO> addQualification(@Valid @RequestBody QualificationDto qualificationDto) {
        QualificationInfo qualificationInfo = iQualificationService.addQualificationData(qualificationDto);
        ResponseDTO response = new ResponseDTO(" Candidate Qualification Added Successfully !!!", qualificationInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param getAllCandidateQualification
     * @return  Ability to get all candidate qualification details
     */
    @GetMapping(value = "/getAllCandidateQualification")
    public ResponseEntity<String> getAll() {
        List<QualificationInfo> qualificationInfoList = iQualificationService.getAllQualificationData();
        ResponseDTO dto = new ResponseDTO("Candidate Qualification retrieved successfully (:", qualificationInfoList);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     *
     * @param qualificationId
     * @return Ability to get qualification details by id
     */
    @GetMapping("/get/{qualificationId}")
    ResponseEntity<ResponseDTO> getById(@PathVariable long qualificationId) {
        QualificationInfo qualificationInfo = iQualificationService.getById(qualificationId);
        ResponseDTO response = new ResponseDTO("Candidate Id found", qualificationInfo);
        return new ResponseEntity(response, HttpStatus.OK);
    }
    /**
     *
     * @param qualificationId
     * @return Ability to update qualification details by id
     */
    @PutMapping("/updateBy/{id}")
    public ResponseEntity<String> updateRecordById(@PathVariable long id, @Valid @RequestBody QualificationDto qualificationDto) {
        QualificationInfo updateRecord = iQualificationService.updateQualificationById(id, qualificationDto);
        ResponseDTO dto = new ResponseDTO(" Candidate Qualification Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
}
