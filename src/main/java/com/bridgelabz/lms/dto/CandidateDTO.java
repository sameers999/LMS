package com.bridgelabz.lms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CandidateDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String status;

}
