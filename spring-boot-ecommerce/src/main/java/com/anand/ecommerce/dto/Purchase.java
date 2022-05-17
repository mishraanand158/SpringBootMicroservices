package com.anand.ecommerce.dto;

import com.anand.ecommerce.entity.Address;
import com.anand.ecommerce.entity.Customer;
import com.anand.ecommerce.entity.Order;
import com.anand.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
