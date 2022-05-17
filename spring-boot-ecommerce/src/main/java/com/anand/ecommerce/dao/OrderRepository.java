package com.anand.ecommerce.dao;

import com.anand.ecommerce.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {

   Page<Order> findByCustomerEmail(@Param("email") String email, Pageable pageable);
   /**
    *  behind the scene code is working like this
    *  Select * from orders
    *  Left outer Join customer
    *  On orders.customer_id = customer_id
    *  Where customer email = :email;
    */

/**
 * http://localhost:8080/api/porder/search/findByCustomerEmail?email=anand@email.com
 */


}
