package com.bridgelabz.lms.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "database_sequences")
@Data
@Component
public class DatabaseSequence {
    @Id
    private String id;
    private long seq;
}
