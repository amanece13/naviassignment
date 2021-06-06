package com.navi.interview.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/check")
    public ResponseEntity testMethod(){
        System.out.println("Running.............");
        
        return new ResponseEntity(HttpStatus.OK);
    }
}
