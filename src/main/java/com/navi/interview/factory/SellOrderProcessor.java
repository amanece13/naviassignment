package com.navi.interview.factory;

import com.navi.interview.exception.OrderProcessingException;
import com.navi.interview.factory.OrderProcessor;
import com.navi.interview.model.Order;
import com.navi.interview.model.OrderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class SellOrderProcessor implements OrderProcessor {

    @Override
    public void processOrder(int index, ArrayList<Order> orders) {
//        log.info("recording the order since it is a sell order");
        Order currentOrder = null;
        try {
            currentOrder = orders.get(index);
        } catch (IndexOutOfBoundsException e) {
            log.info("no order found");
            throw new OrderProcessingException(System.currentTimeMillis(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Order is empty",
                    "No order found for processing",
                    false);
        }
    }
}
