package com.example.form;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@SpringBootTest
class ResisterUserFormTest {

	@Autowired
	Validator validator;

	private ResisterUserForm resisterUserForm = new ResisterUserForm();
	private BindingResult bindingResult = new BindException(resisterUserForm, "resisterUserForm");

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		resisterUserForm.setFirstName("テスト");
		resisterUserForm.setLastName("テスト");
		resisterUserForm.setEmail("test@sample.com");
		resisterUserForm.setAddress("テスト");
		resisterUserForm.setZipcode("160-0022");
		resisterUserForm.setTelephone("090-0000-0000");
		resisterUserForm.setPassword("Yuyatest?");
		resisterUserForm.setConfirmationPassword("Yuyatest?");
	}

	@Test
	void 名前入力正常系() {
		// テスト準備
		resisterUserForm.setLastName("テスト");

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 名前入力異常系() {
		// テスト準備
		resisterUserForm.setLastName(null);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("名字を入力して下さい"));
	}

	@Test
	void 名字入力正常系() {
		// テスト準備
		resisterUserForm.setFirstName("テスト");

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@Test
	void 名字入力異常系() {
		// テスト準備
		resisterUserForm.setFirstName(null);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("名前を入力して下さい"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "test_test@sample.com", "test-test@test", "test.test@gmail.com" })
	void メール入力正常系(String s) {
		// テスト準備
		resisterUserForm.setEmail(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "test?test@con", "test<test>test@com", "test@" })
	void メール入力異常系(String s) {
		// テスト準備
		resisterUserForm.setEmail(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("メールアドレスを入力して下さい"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "Testtest?1", "testtest$$1" })
	void パスワード入力正常系(String s) {
		// テスト準備
		resisterUserForm.setPassword(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "test", "testtest", "testtest?", "テストテストテスト" })
	void パスワード入力異常系(String s) {
		// テスト準備
		resisterUserForm.setPassword(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertTrue(
				bindingResult.getFieldError().toString().contains("パスワードはa-z,A-Z,記号,数字それぞれ1つずつ含めた8文字以上16文字以内で入力して下さい"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "000-0000" })
	void 郵便番号入力正常系(String s) {
		// テスト準備
		resisterUserForm.setZipcode(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "0000-0000", "000-000", "テスト-テストて", "000-?1111" })
	void 郵便番号入力異常系(String s) {
		// テスト準備
		resisterUserForm.setZipcode(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("郵便番号はXXX-XXXXの形式で入力して下さい"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "テスト県テスト市" })
	void 住所入力正常系(String s) {
		// テスト準備
		resisterUserForm.setAddress(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "" })
	void 住所入力異常系(String s) {
		// テスト準備
		resisterUserForm.setAddress(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("住所を入力して下さい"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "090-090-090", "0120-000-000", "00-000-000" })
	void 電話番号入力正常系(String s) {
		// テスト準備
		resisterUserForm.setTelephone(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertNull(bindingResult.getFieldError());
	}

	@ParameterizedTest
	@ValueSource(strings = { "0-090-090", "0000-000-000001", "00!-000-000", "あああ-000-000" ,"123456789"})
	void 電話番号入力異常系(String s) {
		// テスト準備
		resisterUserForm.setTelephone(s);

		// テスト実施
		validator.validate(resisterUserForm, bindingResult);
		// 結果検証
		assertTrue(bindingResult.getFieldError().toString().contains("電話番号はXXXX-XXXX-XXXXの形式で入力して下さい"));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

}
