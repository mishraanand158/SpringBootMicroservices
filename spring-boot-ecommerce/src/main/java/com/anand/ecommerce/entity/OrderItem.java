package com.anand.ecommerce.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name ="image_url")
    private String imageUrl;

    @Column(name ="unit_price")
    private BigDecimal unitPrice;

    @Column(name ="quantity")
    private int quantity;

    @Column(name ="product_id")
    private Long productId;

}
