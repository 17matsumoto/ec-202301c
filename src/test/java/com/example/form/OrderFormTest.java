package com.example.form;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
/**
 * 注文form単体テスト.
 * 
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class OrderFormTest {

	@Autowired
	Validator validator;

	private OrderForm orderForm = new OrderForm();
	private BindingResult bindingResult = new BindException(orderForm, "orderForm");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		orderForm.setStatus(0);
		orderForm.setTotalPrice(1000);
		orderForm.setDestinationName("テスト");
		orderForm.setDestinationEmail("testtest@com");
		orderForm.setDestinationZipcode("000-0000");
		orderForm.setDestinationAddress("テスト県テスト市");
		orderForm.setDestinationTel("090-090-090");
		orderForm.setDeliveryDate("20230101");
		orderForm.setDeliveryTime(13);
		orderForm.setPaymentMethod(1);
	}

	@ParameterizedTest
	@ValueSource(strings = { "テスト", "テスト？" })
	void 宛名氏名正常系(String s) {
		// テスト準備
		orderForm.setDestinationName(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "" })
	void 宛名氏名異常系(String s) {
		// テスト準備
		orderForm.setDestinationName(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("名前を入力して下さい"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "test@com", "test-test@com", "test_test@com" })
	void 宛先Eメール正常系(String s) {
		// テスト準備
		orderForm.setDestinationEmail(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "テスト.com", "test>test@com", ".test@com" })
	void 宛先Eメール異常系(String s) {
		// テスト準備
		orderForm.setDestinationEmail(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("メールアドレスの形式が不正です"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "000-0000", "123-4567", "891-2345" })
	void 宛先郵便番号正常系(String s) {
		// テスト準備
		orderForm.setDestinationZipcode(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "0000-0000", "000-000", "テスト-0000", "" })
	void 宛先郵便番号異常系(String s) {
		// テスト準備
		orderForm.setDestinationZipcode(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("郵便番号形式にしてください"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "テスト県テスト市", "テスト県１テスト市", "891-2345" })
	void 宛先住所正常系(String s) {
		// テスト準備
		orderForm.setDestinationAddress(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "" })
	void 宛先住所異常系(String s) {
		// テスト準備
		orderForm.setDestinationAddress(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("住所を入力して下さい"));

	}

	
	@ParameterizedTest
	@ValueSource(strings = { "000-0000-0000", "00-0000-0000", "0000-0000-0000","000-00-0000","000-000-0000" })
	void 宛先電話番号正常系(String s) {
		// テスト準備
		orderForm.setDestinationTel(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "テスト-000-0000" , "0-0000-0000" , "000-0-0000" ,"00000000" })
	void 宛先電話番号異常系(String s) {
		// テスト準備
		orderForm.setDestinationTel(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("電話番号はXXXX-XXXX-XXXXの形式で入力して下さい"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = { "2023-01-01" })
	void 配達日正常系(String s) {
		// テスト準備
		orderForm.setDeliveryDate(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "" })
	void 配達日異常系(String s) {
		// テスト準備
		orderForm.setDeliveryDate(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("配達日を入力して下さい"));
	}

	@ParameterizedTest
	@ValueSource(ints = { 10 , 11, 12 })
	void 配達時間正常系(Integer s) {
		// テスト準備
		orderForm.setDeliveryTime(s);

		// テスト実施
		validator.validate(orderForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

//	@ParameterizedTest
//	@ValueSource(ints = {})
//	void 配達時間異常系(Integer s) {
//		// テスト準備
//		orderForm.setDeliveryTime(s);
//
//		// テスト実施
//		validator.validate(orderForm, bindingResult);
//		// 結果検証
//		assertTrue(bindingResult.getFieldError().toString().contains("配達時間を入力してください"));
//	}

	
}
