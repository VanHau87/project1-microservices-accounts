CREATE DATABASE microservices_accounts
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
-- utf8mb4 hỗ trợ đầy đủ ký tự Unicode, bao gồm emoji, ký tự đặc biệt.
--utf8mb4_unicode_ci là collation cho phép so sánh không phân biệt hoa thường và theo chuẩn Unicode.

CREATE TABLE Users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE, --Tên đăng nhập (có thể NULL nếu dùng email làm username)
  email VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT TRUE, --Trạng thái kích hoạt tài khoản
  full_name VARCHAR(100) NOT NULL,
  gender VARCHAR(10),
  date_of_birth DATE,
  mobile_number VARCHAR(20) NOT NULL,
  national_id VARCHAR(20),
  address VARCHAR(255),
  customer_type VARCHAR(20) NOT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(50) NOT NULL,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  updated_by VARCHAR(50) NOT NULL,
  deleted TINYINT NOT NULL DEFAULT 0,
  deleted_at DATETIME DEFAULT NULL,
  deleted_by VARCHAR(50) DEFAULT NULL,
  version INT NOT NULL DEFAULT 0
);

CREATE TABLE accounts (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  customer_id BIGINT NOT NULL,
  account_number VARCHAR(20) NOT NULL UNIQUE,
  account_type VARCHAR(50) NOT NULL,
  branch_address VARCHAR(200) NOT NULL,
  balance DECIMAL(18,2) NOT NULL DEFAULT 0,
  currency VARCHAR(10) NOT NULL,
  status VARCHAR(20) NOT NULL,
  opened_date DATE NOT NULL,
  closed_date DATE DEFAULT NULL,
  created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  created_by VARCHAR(50) NOT NULL,
  updated_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  updated_by VARCHAR(50) NOT NULL,
  deleted TINYINT NOT NULL DEFAULT 0,
  deleted_at DATETIME DEFAULT NULL,
  deleted_by VARCHAR(50) DEFAULT NULL,
  version INT NOT NULL DEFAULT 0,
  CONSTRAINT fk_account_customer FOREIGN KEY (customer_id) REFERENCES customers(id)
);

--Đặt index trên customer_id trong bảng account để join nhanh.
CREATE INDEX idx_account_customer_id ON accounts(customer_id);

CREATE TABLE roles (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY(user_id, role_id),
  CONSTRAINT fk_user FOREIGN KEY(user_id) REFERENCES customers(id),
  CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES roles(id)
);
