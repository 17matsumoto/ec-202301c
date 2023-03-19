package com.example.repository;

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

/**
 * 注文商品レポジトリーの単体テストを行う.
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class OrderItemRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void testSave() throws Exception {
		System.out.println("DB初期化処理");
		Order order = new Order();
		order.setUserId(1);
		order.setStatus(0);
		order.setTotalPrice(0);
		orderRepository.insert(order);
		// 注文商品にインサート
		OrderItem orderItem = new OrderItem();
		orderItem.setItemId(1);
		orderItem.setOrderId(1);
		orderItem.setQuantity(10);
		orderItem.setSize("M");
		System.out.println(orderItem);

		orderItemRepository.insert(orderItem);
		System.out.println("インサートが完了しました。");

		System.out.println("DB初期化処理終了");
	}

	@Test
	void 注文商品確認検索テスト1() throws Exception {

		// Id1で検索
		OrderItem resultOrderItem = orderItemRepository.load(1);
		assertEquals(1, resultOrderItem.getItemId(), "商品Idが登録されていません");
		assertEquals(1, resultOrderItem.getOrderId(), "注文Idが登録されていません");
		assertEquals(10, resultOrderItem.getQuantity(), "数量が登録されていません");
		assertEquals("M", resultOrderItem.getSize(), "サイズが登録されていません");

		System.out.println("注文商品確認検索テスト1テスト開始");
	}

//	@Test
//	void 注文商品確認検索テスト1() throws Exception {
//
//		// Id1で検索
//		OrderItem resultOrderItem = orderItemRepository.load(1);
//		assertEquals(1, resultOrderItem.getItemId(), "商品Idが登録されていません");
//		assertEquals(1, resultOrderItem.getOrderId(), "注文Idが登録されていません");
//		assertEquals(10, resultOrderItem.getQuantity(), "数量が登録されていません");
//		assertEquals("M", resultOrderItem.getSize(), "サイズが登録されていません");
//
//		System.out.println("注文商品確認検索テスト1テスト開始");
//	}

//	@Test
//	void 注文商品確認検索テスト2() throws Exception {
//		
//		//Id1で検索
//		OrderItem resultOrderItem = orderItemRepository.load(1);
//		assertEquals(1, resultOrderItem.getItemId(), "商品Idが登録されていません");
//		assertEquals(1, resultOrderItem.getOrderId(), "注文Idが登録されていません");
//		assertEquals(10, resultOrderItem.getQuantity(), "数量が登録されていません");
//		assertEquals("M", resultOrderItem.getSize(), "サイズが登録されていません");
//
//		System.out.println("注文商品確認検索テスト1テスト開始");
//	}
//	
//	
//	
//	@Test
//	void 注文商品確認検索テスト2() throws Exception {
//		
//		//Id1で検索
//		OrderItem resultOrderItem = orderItemRepository.load(2);
//		assertEquals(1, resultOrderItem.getItemId(), "商品Idが登録されていません");
//		assertEquals(1, resultOrderItem.getOrderId(), "注文Idが登録されていません");
//		assertEquals(10, resultOrderItem.getQuantity(), "数量が登録されていません");
//		assertEquals("M", resultOrderItem.getSize(), "サイズが登録されていません");
//
//		System.out.println("注文商品確認検索テスト1テスト開始");
//	}
//	

//	@Test
//	void 注文商品確認検索テスト2() throws Exception {
//		
//		//Id1で検索
//		OrderItem resultOrderItem = orderItemRepository.load(1);
//		assertEquals(1, resultOrderItem.getItemId(), "商品Idが登録されていません");
//		assertEquals(1, resultOrderItem.getOrderId(), "注文Idが登録されていません");
//		assertEquals(10, resultOrderItem.getQuantity(), "数量が登録されていません");
//		assertEquals("M", resultOrderItem.getSize(), "サイズが登録されていません");
//
//		System.out.println("注文確認検索テスト1テスト開始");
//	}

	
}
