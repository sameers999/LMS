package com.bridgelabz.lms.service;

import com.bridgelabz.lms.dto.BankInfoDto;
import com.bridgelabz.lms.model.BankInfo;

import java.util.List;

public interface IBankService {
    BankInfo addBankDetails(BankInfoDto bankInfoDto);

    List<BankInfo> getAllBankDetails();
    BankInfo getById(long id);
    BankInfo updateBankInfo(long id, BankInfoDto bankInfoDto);
}
