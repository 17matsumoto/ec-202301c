package com.example.contorller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * ログインログアウトコントローラー単体テスト.
 * 
 * @author matsumotoyuyya
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:/application.yml" })
class LoginLogoutUserControllerTest {

	@MockBean
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		// Spring MVCのモックを設定する
		this.mockMvc = MockMvcBuilders.standaloneSetup(new LoginLogoutUserController()).build();
	}

	@Test
	void GETでアクセスする200() throws Exception {
		// GETで「/」にアクセスする
		mockMvc.perform(MockMvcRequestBuilders.get("/Login/toLogin")).andExpect(status().isOk())
				.andExpect(view().name("/materialize-version/login.html"));
	}

	@Test
	void GETでアクセスする404() throws Exception {
		// GETで「/」にアクセスする
		mockMvc.perform(MockMvcRequestBuilders.get("/Login/toLo")).andExpect(status().isNotFound());
	}

	@Test
	void POSTでアクセスする405() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/Login/toLogin")).andExpect(status().isMethodNotAllowed());
		;

	}

}
