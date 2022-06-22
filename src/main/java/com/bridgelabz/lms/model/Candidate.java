package com.bridgelabz.lms.model;


import com.bridgelabz.lms.dto.CandidateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Candidate")
public @Data class Candidate {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String status;
    private String email;
    private String phonenumber;
    private String city;

    public Candidate(CandidateDTO candidateDTO) {
        super();
        this.firstName = candidateDTO.getFirstName();
        this.lastName=candidateDTO.getLastName();
        this.status = candidateDTO.getStatus();
        this.email = candidateDTO.getEmail();
        this.phonenumber = candidateDTO.getPhonenumber();
        this.city = candidateDTO.getCity();
    }

    public Candidate(long id, CandidateDTO candidateDTO) {
        this.id=id;
        this.firstName = candidateDTO.getFirstName();
        this.lastName=candidateDTO.getLastName();
        this.status = candidateDTO.getStatus();
        this.email = candidateDTO.getEmail();
        this.phonenumber = candidateDTO.getPhonenumber();
        this.city = candidateDTO.getCity();
    }
}
