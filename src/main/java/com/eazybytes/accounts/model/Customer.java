package com.eazybytes.accounts.model;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@SQLDelete(sql = "UPDATE customer SET deleted = true, deleted_at = NOW(), deleted_by = ? WHERE id = ?")
@SQLRestriction("deleted = false")
@Entity
@Indexed
public class Customer extends OptimisticLockEntity {
	@FullTextField(analyzer = "standard")
    private String name;
	@FullTextField(analyzer = "standard")
    private String email;
	@FullTextField(analyzer = "standard")
    private String mobileNumber;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
