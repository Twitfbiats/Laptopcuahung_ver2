package com.example.sirTalion.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "user_id", referencedColumnName = "id")
    // private User user;

    // @OneToMany(mappedBy = "cart")
    // private List<CartInfo> cartInfos;
}
