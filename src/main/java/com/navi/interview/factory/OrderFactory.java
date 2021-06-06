package com.navi.interview;

import com.navi.interview.model.OrderType;
import com.navi.interview.factory.BuyOrderProcessor;
import com.navi.interview.factory.OrderProcessor;
import com.navi.interview.factory.SellOrderProcessor;
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
