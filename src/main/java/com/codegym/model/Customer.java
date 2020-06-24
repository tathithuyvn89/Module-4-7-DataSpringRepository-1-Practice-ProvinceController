package com.codegym.model;

import javax.persistence.*;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fistName;
    private String lastName;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;


    public Customer(long id, String fistName, String lastName) {
        this.id = id;
        this.fistName = fistName;
        this.lastName = lastName;
    }

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFistName() {
        return fistName;
    }

    public void setFistName(String fistName) {
        this.fistName = fistName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
