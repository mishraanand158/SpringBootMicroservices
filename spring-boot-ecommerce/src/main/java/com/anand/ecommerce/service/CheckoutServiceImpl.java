package com.anand.ecommerce.service;

import com.anand.ecommerce.dao.CustomerRepository;
import com.anand.ecommerce.dto.Purchase;
import com.anand.ecommerce.dto.PurchaseResponse;
import com.anand.ecommerce.entity.Customer;
import com.anand.ecommerce.entity.Order;
import com.anand.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;


    public CheckoutServiceImpl(CustomerRepository customerRepository)
    {
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

           Set<OrderItem> orderItems =  purchase.getOrderItems();
           orderItems.forEach(item->order.add(item));

        // populate order with BillingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate Customer with Order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // Save to Database
        System.out.println("/n################SAve##########################/n");
        customerRepository.save(customer);

        // return the responses
        System.out.println("/n#################Responces#####################/n");

        return  new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {

        // generate unique UUID number (UUID version -4 )
        //  https://en.wikipedia.org/wiki/Universally_unique_identifier
        System.out.println("/n##########################################/n");

        return UUID.randomUUID().toString();
    }
}
