package com.anand.ecommerce.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id ;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber ;

    @Column(name="total_quantity")
    private int totalQuantity ;

    @Column(name="total_price")
    private BigDecimal totalPrice ;

    @Column(name="status")
    private String status;

    @Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    //order has colletions of items

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    public void add(OrderItem item){
        if(item!=null)
        {
            if(orderItems==null)
            {
                orderItems =new HashSet<>();
            }
            orderItems.add(item);
            item.setOrder(this);
        }
    }


}
