package com.anand.ecommerce.dao;

import com.anand.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    //Select * from Customer c WHERE c.email = theEmail;

    Customer findByEmail(String theEmail);
}
