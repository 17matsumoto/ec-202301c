package com.example.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品詳細サービスの単体テストを行う.
 * 
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class ShowItemDetailServiceTest {

	@Autowired
	private ItemRepository itemRepository;

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
	void 商品検索1() {
		Item item = itemRepository.load(1);
		assertEquals("じゃがバターベーコン", item.getName(), "商品が登録されていません");

		System.out.println("商品検索1テストが開始されました");
	}

	@Test
	void 商品検索2() {
		Item item = itemRepository.load(18);
		assertEquals("贅沢フォルマッジ", item.getName(), "商品が登録されていません");

		System.out.println("商品検索2テストが開始されました");
	}

}
