package com.jobify.microservices.entities.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document("referrer")
public class Referrer implements Serializable {

    @Id
    private String id;

    @Indexed(background = true, unique = true)
    private String personalCode;

    @Indexed(background = true)
    private String personalName;

    private String practiceCode;

    private String practiceName;

    private String phoneNumber;

    private String email;

    @CreatedDate
    private Date created;

    @LastModifiedDate
    private Date lastModified;
}
