package com.example.service;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.domain.UserInfo;
import com.example.form.LoginLogoutUserForm;
import com.example.repository.UserRepository;

/**
 * ログインログアウトサービスの単体テストを行う.
 * @author matsumotoyuyya
 *
 */
@SpringBootTest
class LoginLogoutUserServiceTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
	void ユーザー情報でログインする() {
		UserInfo user = userRepository.findByEmail("dummytest@t.co.jp");
		assertEquals("dummytest@t.co.jp", user.getEmail(), "メールアドレスが登録されていません");
		assertEquals(true, passwordEncoder.matches("Yuya000?", user.getPassword()), "メールアドレスが登録されていません");

	}
	
//	@Test
//	void ユーザー情報でログインするNull() {
//		UserInfo user = userRepository.findByEmail("test@t.co.jp");
//		assertEquals("dummytest@t.co.jp", user.getEmail(), "メールアドレスが登録されていません");
//		assertEquals(true, passwordEncoder.matches("Yuya000?", user.getPassword()), "メールアドレスが登録されていません");
//
//	}

	@AfterEach
	void tearDown() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("email", "dummytest@t.co.jp");
		template.update("delete from users where email = :email", param);
		System.out.println("入れたデータを削除しました。");
	}

}
