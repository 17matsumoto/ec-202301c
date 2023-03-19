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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.domain.UserInfo;
import com.example.repository.UserRepository;


/**
 * ユーザー登録サービスの単体テストを行う.
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class ResisterUserServiceTest {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void ユーザー登録() throws Exception {
		UserInfo userInfo = new UserInfo();
		userInfo.setName("dummy名");
		userInfo.setEmail("dummytest@t.co.jp");
		userInfo.setPassword(passwordEncoder.encode("Yuya000?"));
		userInfo.setAddress("dummy住所?");
		userInfo.setZipcode("000-0000");
		userInfo.setTelephone("000-000-000");
		userRepository.save(userInfo);

	}

	
	@Test
	void メールアドレス検索重複あり() {
		UserInfo getUserEmail = userRepository.findByEmail("dummytest@t.co.jp");
		assertEquals("dummytest@t.co.jp", getUserEmail.getEmail(), "メールアドレスが登録されていません");


	}
	
	@Test
	void メールアドレス検索重複なし() {
		UserInfo getUserEmail = userRepository.findByEmail("dummytest2@t.co.jp");
		assertEquals(null, getUserEmail, "メールアドレスが登録されていません");

	}
	
	@AfterEach
	void ユーザ情報削除() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("email", "dummytest@t.co.jp");
		template.update("delete from users where email = :email", param);
		System.out.println("入れたデータを削除しました。");
	}


}
