package com.bridgelabz.lms.model;

import com.bridgelabz.lms.dto.BankInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Document(collection = "bank_info")
public @Data class BankInfo {
    @Transient
    public static final String SEQUENCE_NAME = "bankId_sequence";

    @Id
    private long id;
    public String bankName;
    public String bankAccountNumber;
    public String bankBranch;
    public String ifscCode;

    public BankInfo() {
        super();
    }

    public BankInfo(BankInfoDto bankInfoDto) {
        this.bankName = bankInfoDto.getBankName();
        this.bankAccountNumber = bankInfoDto.getBankAccountNumber();
        this.bankBranch = bankInfoDto.getBankBranch();
        this.ifscCode = bankInfoDto.getIfscCode();
    }

    public BankInfo(long id, BankInfoDto bankInfoDto) {
        this.id = id;
        this.bankName = bankInfoDto.getBankName();
        this.bankAccountNumber = bankInfoDto.getBankAccountNumber();
        this.bankBranch = bankInfoDto.getBankBranch();
        this.ifscCode = bankInfoDto.getIfscCode();
    }
}
