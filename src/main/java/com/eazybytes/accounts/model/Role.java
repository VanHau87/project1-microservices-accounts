package com.eazybytes.accounts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "roles")
public class Role extends IdentifiableEntity {

    @Column(nullable = false, unique = true)
    private String name;  // e.g. ADMIN, USER

}
