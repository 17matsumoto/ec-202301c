package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

/**
 * 注文レポジトリーの単体テストを行う.
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void saveOrder() throws Exception {
		Order order = new Order();
		order.setUserId(1);
		order.setStatus(0);
		order.setTotalPrice(0);
		orderRepository.insert(order);

	}

	@Test
	void 注文情報の主キー検索1() {
		Order order = orderRepository.load(1);

		assertEquals(1, order.getUserId(), "ユーザーIdが登録されていません");
		assertEquals(null, order.getStatus(), "状態が登録されていません");
		assertEquals(0, order.getTotalPrice(), "合計金額が登録されていません");
	}

	@AfterEach
	void tearDown() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("id",1);
		template.update("delete from orders where id = :id", param);
		System.out.println("入れたデータを削除しました。");
	}

}
