package com.example.contorller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.domain.UserInfo;
import com.example.form.ResisterUserForm;
import com.example.service.ResisterUserService;

/**
 * ユーザー登録関連コントローラー単体テスト.
 * 
 * @author matsumotoyuyya
 *
 */
//@RunWith(SpringRunner.class)
//@ContextConfiguration(locations = { "classpath:/application.yml" })
@SpringBootTest
class ResisterUserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private ResisterUserService resisterUserService;
	@InjectMocks
	private ResisterUserController resisterUserController;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

//	@Before
//	public void setUp() {
//		resisterUserService = new ResisterUserService();
//		assertNotNull(resisterUserService);
//	}

	@BeforeEach
	void save() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ResisterUserController()).build();
	}

	@Test
	void ユーザー登録画面が表示される200() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/resister/toResister")).andExpect(status().isOk())
				.andExpect(view().name("/materialize-version/register_user"));

	}

	@Test
	void ユーザー登録画面が表示される404() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/resister/to")).andExpect(status().isNotFound());

	}

	@Test
	void ユーザー登録画面が表示される405() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/resister/toResister")).andExpect(status().isMethodNotAllowed());
	}

//	@Test
//	void resisterUser() throws Exception {
//		UserInfo user = new UserInfo();
//		user.setName(resisterUserForm.getName());
//		user.setEmail(resisterUserForm.getEmail());
//		user.setPassword(resisterUserForm.getPassword());
//		user.setZipcode(resisterUserForm.getZipcode());
//		user.setAddress(resisterUserForm.getAddress());
//		user.setTelephone(resisterUserForm.getTelephone());
//		mockMvc.perform((post("/resister/resisterUser")).flashAttr("resisterUserForm", resisterUserForm))
//				.andExpect(model().hasErrors()).andExpect(model().attribute("resisterUserForm", resisterUserForm))
//				.andExpect(view().name("/materialize-version/register_user"));
//	}
//		ResultActions actions = mockMvc.perform();
//		// テスト実施
//		validator.validate(resisterUserForm, bindingResult);
//		// 結果検証
//		assertTrue(bindingResult.getFieldError().toString().contains("パスワードと確認用パスワードが不一致です"));
//		mockMvc.perform(MockMvcRequestBuilders.post("/resister/toResister")).andExpect(status().isOk());

	@Test
	public void ユーザー登録() throws Exception {
		// パラメータ設定
		ResisterUserForm form = new ResisterUserForm();
		form.setFirstName("テスト");
		form.setLastName("テスト");
		form.setEmail("test@test.co.jp");
		form.setAddress("テスト");
		form.setZipcode("160-0022");
		form.setTelephone("090-0000-0000");
		form.setPassword("Yuyatest?");
		form.setConfirmationPassword("Yuyatest?");
		UserInfo userInfo = new UserInfo();
		userInfo.setPassword(form.getPassword());
		// 実行
//		Mockito.when(resisterUserService.findByEmail("test@test.co.jp")).thenReturn(null);
		Mockito.doReturn(null).when(resisterUserService).findByEmail(anyString());
		Mockito.doReturn(form.getPassword()).when(resisterUserService).resisterUser(userInfo);
		mockMvc.perform(MockMvcRequestBuilders.post("/resister/resisterUser").flashAttr("resisterUserForm", form))
				.andExpect(model().hasNoErrors()).andExpect(model().attribute("resisterUserForm", form))
				.andExpect(view().name("/materialize-version/login.html"));

	}
}
