package com.example.sirTalion.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String address;

    @NotBlank(message = "User email can't be blank")
    @Size(max = 30, message = "User email should be less than 30 characters")
    private String email;

    @Size(max = 50, message = "User full name should be less than 50 characters")
    private String fullName;

    private String password;

    @Transient private String confirmPassword;

    @Size(max = 15, message = "User phone number should be less than 15 characters")
    private String phoneNumber;

    @Enumerated(EnumType.STRING) @Column(length = 15) private Provider provider;

    @ManyToMany
    @JoinTable
    (name = "user_role",
    joinColumns = @JoinColumn(name = "user"),
    inverseJoinColumns = @JoinColumn(name = "role"))
    private List<Role> roles;

    @OneToMany(mappedBy = "purchaser")
    private List<Order> orders;

    @OneToMany(mappedBy = "shipper")
    private List<Order> ships;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @OneToMany(mappedBy = "respondentId")
    private List<Contact> responses;
}
