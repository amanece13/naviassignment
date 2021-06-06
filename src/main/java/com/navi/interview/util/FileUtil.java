package com.navi.interview.util;

import com.navi.interview.exception.InvalidInputException;
import com.navi.interview.model.Order;
import com.navi.interview.model.OrderType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;

@Component
@Slf4j
public class FileUtil {

    public ArrayList<Order> readInputFile(File file) throws IOException {
        ArrayList<Order> orders = new ArrayList<>();

        try{
            Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()));

            lines.forEach(s -> {
                String[] stockData = s.split(" ");

                Order newOrder = Order.builder()
                        .orderId(stockData[0])
                        .time(LocalTime.parse(stockData[1], DateTimeFormatter.ofPattern("HH:mm")))
                        .stock(stockData[2])
                        .quantity(Integer.parseInt(stockData[4]))
                        .price(Double.parseDouble(stockData[5]))
                        .build();

                if(stockData[3].equalsIgnoreCase(String.valueOf(OrderType.BUY)))
                    newOrder.setOrderType(OrderType.BUY);
                else
                    newOrder.setOrderType(OrderType.SELL);

                orders.add(newOrder);
            });
            lines.close();

        } catch (Exception e){
            log.info("Unable to read orders from the file provided");
            throw new InvalidInputException(System.currentTimeMillis(),
                    HttpStatus.BAD_REQUEST,
                    "Invalid data format",
                    "Please check for missing information",
                    false);
        }
        return orders;
    }

    public File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
