package com.bridgelabz.lms.service;

import com.bridgelabz.lms.dto.QualificationDto;
import com.bridgelabz.lms.exception.CandidateException;
import com.bridgelabz.lms.model.QualificationInfo;
import com.bridgelabz.lms.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QualificationService implements IQualificationService{
    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Override
    public QualificationInfo addQualificationData(QualificationDto qualificationDto) {
        QualificationInfo qualificationInfo = new QualificationInfo(qualificationDto);
        //generate sequence
        qualificationInfo.setId(sequenceGeneratorService.getSequenceNumber(QualificationInfo.SEQUENCE_NAME));
        return qualificationRepository.save(qualificationInfo);
    }

    @Override
    public List<QualificationInfo> getAllQualificationData() {
        List<QualificationInfo> qualificationInfoList = qualificationRepository.findAll();
        if (qualificationInfoList.isEmpty()) {
            throw new CandidateException(HttpStatus.NOT_FOUND, "There are no bank details added yet!!");
        } else
            return qualificationInfoList;
    }

    @Override
    public QualificationInfo getById(long id) {
        Optional<QualificationInfo> qualificationInfo = qualificationRepository.findById(id);
        if (qualificationInfo.isPresent()) {
            return qualificationInfo.get();
        } else
            throw new CandidateException(HttpStatus.NOT_FOUND, "This Id is not found! ");
    }

    @Override
    public QualificationInfo updateQualificationById(long id, QualificationDto qualificationDto) {
        Optional<QualificationInfo> qualificationInfo = qualificationRepository.findById(id);
        if (qualificationInfo.isPresent()) {
            QualificationInfo qualificationInfo1 = new QualificationInfo(id, qualificationDto);
            qualificationRepository.save(qualificationInfo1);
            return qualificationInfo1;
        } else {
            throw new CandidateException(HttpStatus.NOT_FOUND, "BankDetails not found by this ID");
        }
    }
}
