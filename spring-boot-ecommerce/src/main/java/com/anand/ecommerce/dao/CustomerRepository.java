package com.anand.ecommerce.dao;

import com.anand.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //Select * from Customer c WHERE c.email = theEmail;

    Customer findByEmail(String theEmail);
}
