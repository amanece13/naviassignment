package com.navi.interview.factory;

import com.navi.interview.model.OrderType;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {

    public OrderProcessor getOrderProcessorType(OrderType type) {

        if (type.equals(OrderType.BUY)) {
            return new BuyOrderProcessor();
        }
        if (type.equals(OrderType.SELL)) {
            return new SellOrderProcessor();
        }

        return null;
    }
}
