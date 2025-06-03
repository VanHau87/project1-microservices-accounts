package com.eazybytes.accounts.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "audit_log")
@Getter @Setter
public class AuditLog extends IdentifiableEntity{
	
	private String actor;           // ai thao tác
    private String action;          // loại hành động: CREATE, UPDATE, DELETE, LOGIN, etc
    private String entityName;      // tên bảng/loại dữ liệu
    private String entityId;        // id của record thao tác
    @Column(length = 1000)
    private String oldValue;        // dữ liệu cũ (json)
    @Column(length = 1000)
    private String newValue;        // dữ liệu mới (json)
    private String result;          // SUCCESS/FAIL
    private String channel;         // WEB, MOBILE, API
    private String ipAddress;       // IP nếu có
    private LocalDateTime createdAt;
}
