package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Topping;

/**
 * トッピングレポジトリーの単体テストを行う.
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class ToppingRepositoryTest {

	
	
	
	@Autowired
	private ToppingRepository toppingRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void トッピング全件検索1() {
		List<Topping> resultTopping = toppingRepository.findAll();
		for (Topping topping : resultTopping) {
			assertEquals("オニオン", topping.getName(), "名前が登録されていません");
			assertEquals(200, topping.getPriceM(), "価格Mが登録されていません");
			assertEquals(300, topping.getPriceL(), "価格Lが登録されていません");
			break;
		}
		System.out.println("トッピング全件検索1テスト開始");
	}

	@Test
	void トッピング全件検索2() {
		List<Topping> resultTopping = toppingRepository.findAll();
		Topping lastTopping = null;
		for (Topping topping : resultTopping) {
			lastTopping = topping;

		}

		assertEquals("チーズ増量", lastTopping.getName(), "名前が登録されていません");
		assertEquals(200, lastTopping.getPriceM(), "価格Mが登録されていません");
		assertEquals(300, lastTopping.getPriceL(), "価格Lが登録されていません");

		System.out.println("トッピング全件検索2テスト開始");

	}

	@Test
	void トッピング主キー検索1() {
		Topping resultTopping = toppingRepository.load(1);
		assertEquals("オニオン", resultTopping.getName(), "名前が登録されていません");
		assertEquals(200, resultTopping.getPriceM(), "価格Mが登録されていません");
		assertEquals(300, resultTopping.getPriceL(), "価格Lが登録されていません");
		System.out.println("トッピング主キー検索1テスト開始");
	}

	@Test
	void トッピング主キー検索2() {
		Topping resultTopping = toppingRepository.load(28);
		assertEquals("チーズ増量", resultTopping.getName(), "名前が登録されていません");
		assertEquals(200, resultTopping.getPriceM(), "価格Mが登録されていません");
		assertEquals(300, resultTopping.getPriceL(), "価格Lが登録されていません");
		System.out.println("トッピング主キー検索2テスト開始");
	}

}
