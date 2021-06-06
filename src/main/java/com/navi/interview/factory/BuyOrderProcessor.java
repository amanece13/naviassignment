package com.navi.interview.factory;

import com.navi.interview.exception.OrderProcessingException;
import com.navi.interview.model.Order;
import com.navi.interview.model.OrderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class BuyOrderProcessor implements OrderProcessor {

    @Override
    public void processOrder(int index, ArrayList<Order> orders) {
//        log.info("executing the order since it is a buy order");
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


        for (int j = 0; j < index && currentOrder.getQuantity() > 0; j++) {

            if (orders.get(j).getOrderType() == OrderType.SELL && orders.get(j).getStock().equals(currentOrder.getStock())) {

                if (orders.get(j).getPrice() <= currentOrder.getPrice()) {
                    if (currentOrder.getQuantity() > orders.get(j).getQuantity() && orders.get(j).getQuantity() != 0) {
                        int soldQuantity = orders.get(j).getQuantity();
                        System.out.println(orders.get(j).getOrderId() + " " + soldQuantity + " " + orders.get(j).getPrice() + " " + currentOrder.getOrderId());
                        currentOrder.setQuantity(currentOrder.getQuantity() > orders.get(j).getQuantity() ? currentOrder.getQuantity() - orders.get(j).getQuantity() : 0);
                        orders.get(j).setQuantity(orders.get(j).getQuantity() - soldQuantity);

                    } else if (currentOrder.getQuantity() < orders.get(j).getQuantity() && orders.get(j).getQuantity() != 0) {
                        int soldQuantity = currentOrder.getQuantity();
                        System.out.println(orders.get(j).getOrderId() + " " + soldQuantity + " " + orders.get(j).getPrice() + " " + currentOrder.getOrderId());
                        currentOrder.setQuantity(currentOrder.getQuantity() > orders.get(j).getQuantity() ? currentOrder.getQuantity() - orders.get(j).getQuantity() : 0);
                        orders.get(j).setQuantity(orders.get(j).getQuantity() - soldQuantity);
                    } else {
                        if (orders.get(j).getQuantity() != 0) {
                            int soldQuantity = currentOrder.getQuantity();
                            System.out.println(orders.get(j).getOrderId() + " " + soldQuantity + " " + orders.get(j).getPrice() + " " + currentOrder.getOrderId());
                            currentOrder.setQuantity(0);
                            orders.get(j).setQuantity(0);
                        }
                    }
                }
            }
        }
    }
}
