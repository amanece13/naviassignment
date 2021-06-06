package com.navi.interview.service;

import com.navi.interview.model.Order;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

public interface OrderService {

    public void readOrders(MultipartFile multipartFile) throws IOException;

    public boolean processOrders(ArrayList<Order> orders);

//    public void printTransactions(ArrayList<Order> orders);
}
