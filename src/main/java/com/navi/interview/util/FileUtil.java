package com.navi.interview.util;

import com.navi.interview.exception.InvalidInputException;
import com.navi.interview.model.Order;
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
public class FileUtil {

    public ArrayList<Order> readInputFile(File file) throws IOException {
        ArrayList<Order> orders = new ArrayList<>();

        try{
            Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()));

            lines.forEach(s -> {
                String[] stockData = s.split(" ");
                Order newOrder = new Order();

                newOrder.setOrderId(stockData[0]);
                newOrder.setTime(LocalTime.parse(stockData[1], DateTimeFormatter.ofPattern("HH:mm")));
                newOrder.setStock(stockData[2]);
                newOrder.setOrderType(stockData[3]);
                newOrder.setQuantity(Integer.parseInt(stockData[4]));
                newOrder.setPrice(Double.parseDouble(stockData[5]));

                orders.add(newOrder);
            });
            lines.close();

        } catch (Exception e){
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
