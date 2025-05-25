package com.eazybytes.accounts.model;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import com.eazybytes.accounts.enums.CustomerStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@SQLDelete(sql = "UPDATE customer SET deleted = true, deleted_at = NOW(), deleted_by = ? WHERE id = ?")
@SQLRestriction("deleted = false")
@Entity
@Indexed
public class Customer extends OptimisticLockEntity {
	
	@FullTextField(analyzer = "standard")
	@Column(nullable = false, length = 100)
    private String fullName;
	
	private LocalDate dateOfBirth;
	@Column(length = 10)
	private String gender; // Male, Female, Other
	
	@Column(nullable = false, length = 100, unique = true)
    private String email;
	
	@Column(nullable = false, length = 15, unique = true)
    private String mobileNumber;
	
	@Column(length = 20, unique = true)
    private String nationalId; // CCCD/CMND/Hộ chiếu
	
	@Column(nullable = false, length = 20)
    private String customerType; // PERSONAL, BUSINESS
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CustomerStatus status;
	
	@Column(length = 255)
    private String address;
	
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
