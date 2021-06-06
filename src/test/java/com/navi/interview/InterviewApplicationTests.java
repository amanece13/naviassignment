package com.navi.interview;

import com.navi.interview.model.Order;
import com.navi.interview.service.OrderServiceImpl;
import com.navi.interview.util.FileUtil;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
class InterviewApplicationTests {

	@Autowired
	private OrderServiceImpl orderService;

	@Autowired
	private FileUtil fileUtil;

	@Test
	public void readFileFromInputTest() throws IOException {
		File file = new File("/home/abhishek/Desktop/orders.txt");
		ArrayList<Order> orders = fileUtil.readInputFile(file);
		Assertions.assertNotNull(orders.size());
	}

	@Test
	public void readEmptyFileTest() throws IOException{
		File file = new File("/home/abhishek/Desktop/empty.txt");
		ArrayList<Order> orders = fileUtil.readInputFile(file);
		Assertions.assertTrue(orders.size()==0);
	}


	//write a few more tests to check the execution of the orders perfectly

}
