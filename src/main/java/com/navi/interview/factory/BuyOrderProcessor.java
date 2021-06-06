package com.navi.interview.factory;

import com.navi.interview.exception.OrderProcessingException;
import com.navi.interview.model.Order;
import com.navi.interview.model.OrderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@Service
@Slf4j
public class BuyOrderProcessor implements OrderProcessor {

    @Override
    public void processOrder(int index, ArrayList<Order> orders, ArrayList<Order> sellOrders) {

        /*
         * In case of buy, we are executing the order
         *
         * */
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

        Collections.sort(sellOrders,
                Comparator.comparingDouble(Order::getPrice));

        /*
        * We are trying to find the match for the buy order amongst the list of sell orders available
        *
        * To meet the match we have already sorted the sellOrderList on the basis of lower price
        *
        * */

        for (int j = 0; j < sellOrders.size(); j++) {
            if (sellOrders.get(j).getStock().equals(currentOrder.getStock())) {
                if (sellOrders.get(j).getPrice() <= currentOrder.getPrice()) {
                    if (currentOrder.getQuantity() > sellOrders.get(j).getQuantity() && sellOrders.get(j).getQuantity() != 0) {
                        int soldQuantity = sellOrders.get(j).getQuantity();
                        System.out.println(sellOrders.get(j).getOrderId() + " " + soldQuantity + " " + sellOrders.get(j).getPrice() + " " + currentOrder.getOrderId());
                        currentOrder.setQuantity(currentOrder.getQuantity() > sellOrders.get(j).getQuantity() ? currentOrder.getQuantity() - sellOrders.get(j).getQuantity() : 0);
                        sellOrders.get(j).setQuantity(sellOrders.get(j).getQuantity() - soldQuantity);
                    } else if (currentOrder.getQuantity() < sellOrders.get(j).getQuantity() && sellOrders.get(j).getQuantity() != 0) {
                        int soldQuantity = currentOrder.getQuantity();
                        System.out.println(sellOrders.get(j).getOrderId() + " " + soldQuantity + " " + sellOrders.get(j).getPrice() + " " + currentOrder.getOrderId());
                        currentOrder.setQuantity(currentOrder.getQuantity() > sellOrders.get(j).getQuantity() ? currentOrder.getQuantity() - sellOrders.get(j).getQuantity() : 0);
                        sellOrders.get(j).setQuantity(sellOrders.get(j).getQuantity() - soldQuantity);
                    } else {
                        if (sellOrders.get(j).getQuantity() != 0) {
                            int soldQuantity = currentOrder.getQuantity();
                            System.out.println(sellOrders.get(j).getOrderId() + " " + soldQuantity + " " + sellOrders.get(j).getPrice() + " " + currentOrder.getOrderId());
                            currentOrder.setQuantity(0);
                            sellOrders.get(j).setQuantity(0);
                        }
                    }
                }
            }
        }
    }
}
