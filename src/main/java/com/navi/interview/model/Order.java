package com.navi.interview.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {

    private String orderId;
    private LocalTime time;
    private String stock;
    private OrderType orderType;
    private Double price;
    private int quantity;
}
