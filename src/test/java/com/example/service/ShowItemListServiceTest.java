package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品一覧サービスの単体テストを行う.
 * 
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class ShowItemListServiceTest {

	@Autowired
	private ItemRepository itemRepository;

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
	}

	@Test
	void 商品全件検索取得() {
		List<Item> itemList = itemRepository.findAll(null);
		assertEquals(18, itemList.size(), "名前が登録されていません");
		System.out.println("商品全件検索取得テスト開始");

	}

	@Test
	void 商品曖昧検索取得() {
		List<Item> itemList = itemRepository.findByName("じゃ", null);
		assertEquals(2, itemList.size(), "名前が登録されていません");
		System.out.println("商品曖昧検索取得テスト開始");

	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
