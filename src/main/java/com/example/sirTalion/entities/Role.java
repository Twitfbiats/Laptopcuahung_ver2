package com.example.sirTalion.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 20, message = "Role name should be less than 20 characters")
    private String roleName;

    // @ManyToMany(mappedBy = "roles")
    // List<User> users;

    public Role(String roleName) {this.roleName = roleName;}
    public Role() {}
}
