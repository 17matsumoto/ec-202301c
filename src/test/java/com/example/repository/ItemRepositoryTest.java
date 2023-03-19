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

import com.example.domain.Item;

/**
 * 商品レポジトリーの単体テストを行う.
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 商品インサート.
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void testSave() throws Exception {
	}

	@Test
	void 商品確認検索テスト1()  {

		// Id1で検索
		Item resultItem = itemRepository.load(1);
		assertEquals("じゃがバターベーコン", resultItem.getName(), "名前が登録されていません");
		assertEquals("ホクホクのポテトと旨味が凝縮されたベーコンを特製マヨソースで味わって頂く商品です。バター風味豊かなキューブチーズが食材の味を一層引き立てます。",
				resultItem.getDescription(), "説明が登録されていません");
		assertEquals(1490, resultItem.getPriceM(), "価格Mが登録されていません");
		assertEquals(2570, resultItem.getPriceL(), "価格Lが登録されていません");
		assertEquals("1.jpg", resultItem.getImagePath(), "画像が登録されていません");

		System.out.println("商品を検索するテスト開始");
	}

	@Test
	void 商品全件検索を値段が高い順で検索できる() throws Exception {
		// 値段が高い順で検索
		List<Item> resultItem = itemRepository.findAll("high");
		for (Item item : resultItem) {
			assertEquals("とろけるビーフシチュー", item.getName(), "名前が登録されていません");
			assertEquals("デミグラスソースでじっくり煮込んだ旨味たっぷりのビーフシチューのピザ", item.getDescription(), "説明が登録されていません");
			assertEquals(2980, item.getPriceM(), "価格Mが登録されていません");
			assertEquals(4460, item.getPriceL(), "価格Lが登録されていません");
			assertEquals("14.jpg", item.getImagePath(), "画像が登録されていません");
			break;
		}
		System.out.println("商品全件検索を値段が高い順で検索できるテスト開始");
	}

	@Test
	void 商品全件検索を値段が低い順で検索できる() throws Exception {
		// 値段が低い順で検索
		List<Item> resultItem = itemRepository.findAll("low");
		for (Item item : resultItem) {
			assertEquals("じゃがバターベーコン", item.getName(), "名前が登録されていません");
			assertEquals("ホクホクのポテトと旨味が凝縮されたベーコンを特製マヨソースで味わって頂く商品です。バター風味豊かなキューブチーズが食材の味を一層引き立てます。",
					item.getDescription(), "説明が登録されていません");
			assertEquals(1490, item.getPriceM(), "価格Mが登録されていません");
			assertEquals(2570, item.getPriceL(), "価格Lが登録されていません");
			assertEquals("1.jpg", item.getImagePath(), "画像が登録されていません");

			break;
		}
		System.out.println("商品全件検索を値段が低い順で検索できるテスト開始");
	}

	@Test
	void 商品全件検索で18件検索できる() throws Exception {
		// 純粋全件検索
		List<Item> resultItem = itemRepository.findAll(null);
		assertEquals(18, resultItem.size());
		System.out.println("商品全件検索で18件検索できるテスト開始");
	}

	@Test
	void 値段が高い順で商品曖昧検索できる() throws Exception {
		// 値段が高い順曖昧検索
		List<Item> resultItem = itemRepository.findByName("じゃ", "high");
		assertEquals(2, resultItem.size());
		System.out.println("商品曖昧検索を値段が高い順で検索できるテスト開始");
	}

	@Test
	void 値段が低い順で商品曖昧検索できる() throws Exception {
		// 値段が低い順曖昧検索
		List<Item> resultItem = itemRepository.findByName("じゃ", "low");
		assertEquals(2, resultItem.size());
		System.out.println("商品曖昧検索を値段が低い順で検索できるテスト開始");
	}

	@Test
	void 商品曖昧検索できる() throws Exception {
		// 純粋曖昧検索
		List<Item> resultItem = itemRepository.findByName("じゃ", null);
		assertEquals(2, resultItem.size());
		System.out.println("商品曖昧検索できるテスト開始");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
