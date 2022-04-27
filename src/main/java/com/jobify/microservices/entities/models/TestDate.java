package com.jobify.microservices.entities.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Document(collection = "date_test")
public class TestDate {
    private Date startDate;
    private Date endDate;
}
