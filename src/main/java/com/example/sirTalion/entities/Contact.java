package com.example.sirTalion.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contact 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 30, message = "Contact email should be less than 30 characters")
    private String contactEmail;
    private String contactContent;
    private String responseContent;
    @Size(max = 20, message = "Contact status should be less than 20 characters")
    private String status;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date contactDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date responseDate;

    // @ManyToOne
    // @JoinColumn(name = "respondent_id")
    // private User respondentId;
}
