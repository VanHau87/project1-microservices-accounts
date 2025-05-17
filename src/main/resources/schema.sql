CREATE TABLE customer (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  mobile_number VARCHAR(20) NOT NULL,
  created_at DATETIME NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at DATETIME NOT NULL,
  updated_by VARCHAR(50) NOT NULL,
  deleted BOOLEAN DEFAULT FALSE,
  deleted_at DATETIME DEFAULT NULL,
  deleted_by VARCHAR(50) DEFAULT NULL,
  version INT DEFAULT 0
);

CREATE TABLE account (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  customer_id CHAR(36) NOT NULL,
  account_number VARCHAR(20) NOT NULL,
  account_type VARCHAR(100) NOT NULL,
  branch_address VARCHAR(200) NOT NULL,
  created_at DATETIME NOT NULL,
  created_by VARCHAR(50) NOT NULL,
  updated_at DATETIME NOT NULL,
  updated_by VARCHAR(50) NOT NULL,
  deleted BOOLEAN DEFAULT FALSE,
  deleted_at DATETIME DEFAULT NULL,
  deleted_by VARCHAR(50) DEFAULT NULL,
  version INT DEFAULT 0,
  CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
);