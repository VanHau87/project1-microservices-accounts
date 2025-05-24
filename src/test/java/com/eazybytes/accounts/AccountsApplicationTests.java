package com.eazybytes.accounts;

import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.repository.CustomerRepository;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AccountsApplicationTests {
	@Autowired
	private CustomerRepository customerRepository;
	
	@BeforeEach
    void initData() {
        Customer customer = new Customer();
        customer.setName("Original");
        customer.setEmail("test@example.com");
        customer.setMobileNumber("0123456789");
        customerRepository.save(customer);
    }
	@Test
	void testOptimisticConflict() {
        Customer customer1 = customerRepository.findAll().get(0);
        Customer customer2 = customerRepository.findById(customer1.getId()).orElse(null);
        customer1.setMobileNumber("9999999999");
        customerRepository.save(customer1);
        
        try {
            customer2.setMobileNumber("8888888888");
            customerRepository.save(customer2);
            fail("Expected OptimisticLockingFailureException not thrown");
        } catch (OptimisticLockingFailureException e) {
            System.out.println("✅ Exception caught as expected: " + e.getMessage());
        }
    }
}
