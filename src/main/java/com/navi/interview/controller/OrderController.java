package com.navi.interview.controller;

import com.navi.interview.exception.EmptyFileException;
import com.navi.interview.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/orders/upload")
    public ResponseEntity uploadOrders(@RequestParam("file") MultipartFile ordersDataFile) throws IOException {
        log.info("request received ");

        if(ordersDataFile.isEmpty()){
            log.info("Empty file provided");
            throw new EmptyFileException(System.currentTimeMillis(),
                    HttpStatus.BAD_REQUEST,
                    "Empty file provided",
                    "Please check for missing information",
                    false);

        }
        orderService.readOrders(ordersDataFile);
        return new ResponseEntity(HttpStatus.OK);
    }

}
