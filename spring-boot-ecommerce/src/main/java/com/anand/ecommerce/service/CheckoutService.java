package com.anand.ecommerce.service;

import com.anand.ecommerce.dto.Purchase;
import com.anand.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
