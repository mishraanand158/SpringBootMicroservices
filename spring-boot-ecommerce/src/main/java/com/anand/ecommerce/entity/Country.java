package com.anand.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="country")
@Getter
@Setter
public class Country {

    @Id
    @Column(name="id")
    private int id ;

    @Column(name="code")
    private String code;

    @Column(name= "name")
    private String name ;

    @OneToMany(mappedBy = "country")
    private List<State> states;


}
