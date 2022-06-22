package com.bridgelabz.lms.controller;

import com.bridgelabz.lms.dto.BankInfoDto;
import com.bridgelabz.lms.dto.ResponseDTO;
import com.bridgelabz.lms.model.BankInfo;
import com.bridgelabz.lms.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bankinfo")
public class BankController {
    @Autowired
    private IBankService iBankService;

    /**
     *
     * @param bankInfo(addBankDetails)
     * @return Ability to add bank details
     */
    @PostMapping("/addBankDetails")
    public ResponseEntity<ResponseDTO> insertBankDetails(@Valid @RequestBody BankInfoDto bankInfoDto) {
        BankInfo bankInfo = iBankService.addBankDetails(bankInfoDto);
        ResponseDTO response = new ResponseDTO(" BankDetails Added Successfully !!!", bankInfo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param getAllBankDetails
     * @return Ability to get all Bank Details
     */
    @GetMapping(value = "/getAllBankDetails")
    public ResponseEntity<String> getAll() {
        List<BankInfo> listOfBankDetails = iBankService.getAllBankDetails();
        ResponseDTO dto = new ResponseDTO("BankDetails retrieved successfully (:", listOfBankDetails);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    /**
     *
     * @param bankID
     * @return Ability to get bank Details by Id
     */
    @GetMapping("/get/{bankID}")
    ResponseEntity<ResponseDTO> getById(@PathVariable long bankID) {
        BankInfo bankInfo = iBankService.getById(bankID);
        ResponseDTO response = new ResponseDTO("Candidate Id found", bankInfo);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @param bankInfoDto
     * @return Ability to update bank Details by Id
     */
    @PutMapping("/updateBy/{id}")
    public ResponseEntity<String> updateRecordById(@PathVariable long id, @Valid @RequestBody BankInfoDto bankInfoDto) {
        BankInfo updateRecord = iBankService.updateBankInfo(id, bankInfoDto);
        ResponseDTO dto = new ResponseDTO(" Bankdetails Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
}
