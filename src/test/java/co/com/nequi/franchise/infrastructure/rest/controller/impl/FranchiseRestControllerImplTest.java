package co.com.nequi.franchise.infrastructure.rest.controller.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import co.com.nequi.franchise.domain.model.Franchise;
import co.com.nequi.franchise.domain.service.FranchiseService;
import co.com.nequi.franchise.infrastructure.rest.model.mapper.FranchiseMapper;
import co.com.nequi.franchise.infrastructure.rest.model.request.FranchiseRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.FranchiseResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FranchiseRestControllerImplTest {

	public static final String PAYLOAD_REGISTER_FRANCHISE = "{\n" + "    \"name\": \"Muebles Envigado 1\"\n" + "}";
	public static final String PAYLOAD_UPDATE_FRANCHISE = "{\n" + "    \"name\": \"Muebles Envigado 001\"\n" + "}";

	private MockMvc mockMvc;

	@Mock
	private FranchiseService franchiseService;

	@Mock
	private FranchiseMapper franchiseMapper;

	@InjectMocks
	private FranchiseRestControllerImpl franchiseRestController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(franchiseRestController).build();
	}

	@Test
	void registerFranchise() throws Exception {
		FranchiseRequest franchiseRequest = new FranchiseRequest();
		FranchiseResponse franchiseResponse = new FranchiseResponse();

		when(franchiseMapper.toFranchise(franchiseRequest)).thenReturn(new Franchise());
		when(franchiseService.registerFranchise(any())).thenReturn(new Franchise());
		when(franchiseMapper.toFranchiseResponse(any())).thenReturn(franchiseResponse);

		mockMvc.perform(post("/franchise").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(PAYLOAD_REGISTER_FRANCHISE)).andExpect(status().isOk());
	}

	@Test
	void updateFranchise() throws Exception {
		UUID franchiseUuid = UUID.randomUUID();
		FranchiseRequest franchiseRequest = new FranchiseRequest();
		FranchiseResponse franchiseResponse = new FranchiseResponse();

		when(franchiseMapper.toFranchise(franchiseRequest)).thenReturn(new Franchise());
		when(franchiseService.updateFranchise(eq(franchiseUuid), any())).thenReturn(new Franchise());
		when(franchiseMapper.toFranchiseResponse(any())).thenReturn(franchiseResponse);

		mockMvc.perform(put("/franchise/{uuid-franchise}", franchiseUuid).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(PAYLOAD_UPDATE_FRANCHISE)).andExpect(status().isOk());
	}
}