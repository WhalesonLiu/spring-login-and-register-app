package com.caroline.fruit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_message_template",indexes = {@Index(columnList = "formatKey", unique = true)})
public class MessageTemplate {

    @Id
    @GeneratedValue
    private Long messageId;

    private String formatKey;

    private String formatMeaage;

    @Temporal(DATE)
    private Date creationDate;
}
