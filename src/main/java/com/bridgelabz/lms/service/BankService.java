package com.bridgelabz.lms.service;

import com.bridgelabz.lms.dto.BankInfoDto;
import com.bridgelabz.lms.exception.CandidateException;
import com.bridgelabz.lms.model.BankInfo;
import com.bridgelabz.lms.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService implements IBankService{
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Override
    public BankInfo addBankDetails(BankInfoDto bankInfoDto) {
        BankInfo bankInfo= new BankInfo(bankInfoDto);
        //generate sequence
        bankInfo.setId(sequenceGeneratorService.getSequenceNumber(BankInfo.SEQUENCE_NAME));
        return bankRepository.save(bankInfo);
    }

    @Override
    public List<BankInfo> getAllBankDetails() {
        List<BankInfo> bankInfoList = bankRepository.findAll();
        if (bankInfoList.isEmpty()){
            throw new CandidateException(HttpStatus.NOT_FOUND,"There are no bank details added yet!!");
        }else
            return bankInfoList;
    }

    @Override
    public BankInfo getById(long id) {
        Optional<BankInfo> bankInfo = bankRepository.findById(id);
        if (bankInfo.isPresent()) {
            return bankInfo.get();
        } else
            throw new CandidateException(HttpStatus.NOT_FOUND, "This Id is not found! ");
    }

    @Override
    public BankInfo updateBankInfo(long id, BankInfoDto bankInfoDto) {
        Optional<BankInfo> bankInfo = bankRepository.findById(id);
        if (bankInfo.isPresent()) {
            BankInfo newBankInfo = new BankInfo(id, bankInfoDto);
            bankRepository.save(newBankInfo);
            return newBankInfo;
        } else {
            throw new CandidateException(HttpStatus.NOT_FOUND, "BankDetails not found by this ID");
        }
    }
}
