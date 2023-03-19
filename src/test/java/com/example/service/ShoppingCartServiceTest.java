package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;

/**
 * ショッピングカートサービスの単体テストを行う.
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class ShoppingCartServiceTest {

	@Autowired
	private OrderToppingRepository orderToppingRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderitemRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("DB初期化処理");
		Order order = new Order();
		order.setUserId(1);
		order.setStatus(0);
		order.setTotalPrice(0);
		orderRepository.insert(order);
		System.out.println("DB初期化処理完了");
	}

	@Test
	void オーダーがない場合の注文インサート処理() {
		Order order = orderRepository.findByUserIdAndStatus(2, 0);
		Order orderObject = new Order();
		assertEquals(null, order, "オーダーが登録されています");
		// オーダーテーブルにユーザー情報がない場合注文テーブにインサート
		if (order == null) {
			orderObject.setUserId(2);
			orderObject.setStatus(0);
			orderObject.setTotalPrice(0);
			orderRepository.insert(orderObject);
		}

		System.out.println("オーダーがない場合の注文インサート処理");

	}

	@Test
	void オーダーがある場合の注文インサート処理() {
		Order order = orderRepository.findByUserIdAndStatus(1, 0);
		Order orderObject = new Order();
		assertEquals(1, order.getUserId(), "オーダーが登録されていません");
		System.out.println("オーダーがある場合の注文インサート処理");

	}

	@Test
	void カートの中身を表示させる処理1() {
		Order order = orderRepository.findByUserIdAndStatus(1, 0);
		Order orderObject = new Order();
		assertEquals(1, order.getUserId(), "オーダーが登録されていません");
		System.out.println("オーダーがある場合の注文インサート処理");

	}

	@AfterEach
	void tearDown() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("status", 0);
		template.update("delete from orders where status =:status", param);
		System.out.println("入れたデータを削除しました。");
	}

}
