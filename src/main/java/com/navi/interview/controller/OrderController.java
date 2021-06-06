package com.navi.interview.controller;

import com.navi.interview.exception.EmptyFileException;
import com.navi.interview.service.OrderServiceImpl;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@RestController
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/orders/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) throws IOException {

        if(file.isEmpty())
            throw new EmptyFileException(System.currentTimeMillis(),
                    HttpStatus.BAD_REQUEST,
                    "Empty file provided",
                    "Please check for missing information",
                    false);

        orderService.readOrders(file);
        return new ResponseEntity(HttpStatus.OK);
    }

}
