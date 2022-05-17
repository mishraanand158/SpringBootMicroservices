package com.anand.ecommerce.service;

import com.anand.ecommerce.dao.CustomerRepository;
import com.anand.ecommerce.dto.Purchase;
import com.anand.ecommerce.dto.PurchaseResponse;
import com.anand.ecommerce.entity.Customer;
import com.anand.ecommerce.entity.Order;
import com.anand.ecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;


    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the orderInfo from DTO
        Order order = purchase.getOrder();

        // generate tracking number

        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with OrderItems

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with BillingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate Customer with Order
        Customer customer = purchase.getCustomer();
        //check if existing customer or not
        String theEmail = customer.getEmail();
        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if (customerFromDB != null) {
            customer = customerFromDB;
        }

        customer.add(order);

        // Save to Database
        customerRepository.save(customer);

        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {

        // generate unique UUID number (UUID version -4 )
        //  https://en.wikipedia.org/wiki/Universally_unique_identifier

        return UUID.randomUUID().toString();
    }
}
