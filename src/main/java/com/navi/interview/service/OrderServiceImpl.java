package com.navi.interview.service;

import com.navi.interview.factory.OrderFactory;
import com.navi.interview.factory.OrderProcessor;
import com.navi.interview.model.Order;
import com.navi.interview.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FileUtil fileUtil;

    @Autowired
    private OrderFactory orderFactory;

    @Override
    public void readOrders(MultipartFile multipartFile) throws IOException {

        ArrayList<Order> orders = fileUtil.readInputFile(fileUtil.convert(multipartFile));
        processOrders(orders);
    }

    @Override
    public void processOrders(ArrayList<Order> orders) {

        for (int i = 0; i < orders.size(); i++) {
            Order currentOrder = orders.get(i);
            OrderProcessor orderProcessor = orderFactory.getOrderProcessorType(currentOrder.getOrderType());
            orderProcessor.processOrder(i,orders);
        }
    }
}
