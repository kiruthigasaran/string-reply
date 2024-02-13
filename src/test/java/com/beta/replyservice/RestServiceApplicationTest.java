package com.beta.replyservice;

import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestServiceApplicationTest {

	@Autowired MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}

	@Test
	void check() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/reply").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
						.andExpect(jsonPath("$.data").value("Message is empty"));
	}

	@Test
	void checkValue() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/reply/abc").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("abc"));
	}


	@Test
	void checkValue_invalid() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/abc").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").value("invalid input"));
	}

	@Test
	void checkValue_invalid_regex_case1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/ab-abc").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").value("invalid input"));
	}

	@Test
	void checkValue_invalid_regex_case2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/13-abc").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").value("invalid input"));
	}

	@Test
	void checkValue_invalid_regex_case3() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/31-abc").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").value("invalid input"));
	}

	@Test
	void checkValue_invalid_regex_case4() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/11abc").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.data").value("invalid input"));
	}

	@Test
	void checkValue_valid_input_case1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/11-kbzw9ru").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("kbzw9ru"));
	}

	@Test
	void checkValue_valid_input_case2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/12-kbzw9ru").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("5a8973b3b1fafaeaadf10e195c6e1dd4"));
	}
	@Test
	void checkValue_valid_input_case3() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/22-kbzw9ru").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("e8501e64cf0a9fa45e3c25aa9e77ffd5"));
	}

	@Test
	void checkValue_valid_input_case4() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v2/reply/21-kbzw9ru").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("daf168567f92b1c464459087eaaefaf0"));
	}

}
