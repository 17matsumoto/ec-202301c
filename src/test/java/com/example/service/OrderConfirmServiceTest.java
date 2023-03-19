package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 注文確認サービスの単体テストを行う.
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class OrderConfirmServiceTest {

	@Autowired
	private OrderRepository orderRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	
	@Test
	void オーダ情報を取得する() {
		Order order = orderRepository.load(1);
		assertEquals(1, order.getId(), "メールアドレスが登録されていません");
		System.out.println("オーダ情報を取得する");

	}
	
	@AfterEach
	void tearDown() throws Exception {
	}


}
