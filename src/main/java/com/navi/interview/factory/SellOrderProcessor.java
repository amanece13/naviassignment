package com.navi.interview.factory;

import com.navi.interview.factory.OrderProcessor;
import com.navi.interview.model.Order;
import com.navi.interview.model.OrderType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service

public class SellOrderProcessor implements OrderProcessor {

    @Override
    public void processOrder(int index, ArrayList<Order> orders) {
        Order currentOrder = null;
        try {
            currentOrder = orders.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bound exception");
        }

        for (int j = index + 1; j < orders.size() && currentOrder.getQuantity() > 0; j++) {

            if (orders.get(j).getOrderType() == OrderType.BUY && orders.get(j).getStock().equals(currentOrder.getStock())) {

                if (orders.get(j).getPrice() >= currentOrder.getPrice()) {

                    int soldQuantity = (currentOrder.getQuantity() - orders.get(j).getQuantity()) > 0 ? (currentOrder.getQuantity() - orders.get(j).getQuantity()) : currentOrder.getQuantity();

                    System.out.println(orders.get(j).getOrderId() + " " + currentOrder.getPrice() + " " + soldQuantity + " " + currentOrder.getOrderId());

                    currentOrder.setQuantity(currentOrder.getQuantity() > orders.get(j).getQuantity() ? currentOrder.getQuantity() - orders.get(j).getQuantity() : 0);
                    orders.get(j).setQuantity(currentOrder.getQuantity() == 0 ? orders.get(j).getQuantity() - soldQuantity : 0);
                }
            }
        }
    }
}
