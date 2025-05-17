package com.eazybytes.accounts.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Customer extends AuditableEntity{
    private String name;
    private String email;
    private String mobileNumber;
}
