package com.navi.interview.factory;

import com.navi.interview.model.Order;

import java.util.ArrayList;

public interface OrderProcessor {

    public void processOrder(int index, ArrayList<Order> orders);
}
