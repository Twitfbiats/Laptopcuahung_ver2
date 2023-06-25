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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.sirTalion.validation.ValidUser;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@ValidUser
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "User address can't be blank")
    private String address;

    @NotBlank(message = "User email can't be blank")
    @Size(max = 30, message = "User email should be less than 30 characters")
    @Pattern(regexp = ".+@[^.]+[.]{1}[^.]{1}.+",
    message = "User email format is incorrect")
    private String email;

    @Size(max = 50, message = "User full name should be less than 50 characters")
    @NotBlank(message = "User full name can't be blank")
    private String fullName;

    @NotBlank(message = "User password can't be blank")
    private String password;

    @NotBlank(message = "Confirm password can't be blank")
    @Transient private String confirmPassword;

    @Size(max = 15, message = "User phone number should be less than 15 characters")
    @NotBlank(message = "User phone number can't be blank")
    private String phoneNumber;

    @Enumerated(EnumType.STRING) @Column(length = 15) private Provider provider;

    @ManyToMany
    @JoinTable
    (name = "user_role",
    joinColumns = @JoinColumn(name = "user"),
    inverseJoinColumns = @JoinColumn(name = "role"))
    private List<Role> roles;

    // @OneToMany(mappedBy = "purchaser")
    // private List<Order> orders;

    // @OneToMany(mappedBy = "shipper")
    // private List<Order> ships;

    @OneToOne()
    private Cart cart;

    @OneToMany()
    private List<Contact> responses;
}
