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

import co.com.nequi.franchise.domain.model.Branch;
import co.com.nequi.franchise.domain.service.BranchService;
import co.com.nequi.franchise.infrastructure.rest.model.mapper.BranchMapper;
import co.com.nequi.franchise.infrastructure.rest.model.request.BranchRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.BranchResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BranchRestControllerImplTest {

	public static final String PAYLOAD_REGISTER_BRANCH = "{\n" + " \"name\": \"Sucursal 1\" " + "\n}";
	public static final String PAYLOAD_UPDATE_BRANCH = "{\n" + " \"name\": \"Sucursal 001\" " + "\n}";

	private MockMvc mockMvc;

	@Mock
	private BranchService branchService;

	@Mock
	private BranchMapper branchMapper;

	@InjectMocks
	private BranchRestControllerImpl branchRestController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(branchRestController).build();
	}

	@Test
	void registerBranch() throws Exception {
		UUID franchiseUuid = UUID.randomUUID();
		BranchRequest branchRequest = new BranchRequest();
		BranchResponse branchResponse = new BranchResponse();

		when(branchMapper.toBranch(branchRequest)).thenReturn(new Branch());
		when(branchService.registerBranch(eq(franchiseUuid), any())).thenReturn(new Branch());
		when(branchMapper.toBranchResponse(any())).thenReturn(branchResponse);

		mockMvc.perform(post("/franchise/{uuid-franchise}/branch/register", franchiseUuid)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(PAYLOAD_REGISTER_BRANCH)).andExpect(status().isOk());
	}

	@Test
	void updateBranch() throws Exception {
		UUID branchUuid = UUID.randomUUID();
		BranchRequest branchRequest = new BranchRequest();
		BranchResponse branchResponse = new BranchResponse();

		when(branchMapper.toBranch(branchRequest)).thenReturn(new Branch());
		when(branchService.updateBranch(eq(branchUuid), any())).thenReturn(new Branch());
		when(branchMapper.toBranchResponse(any())).thenReturn(branchResponse);

		mockMvc.perform(put("/branch/{uuid-branch}", branchUuid).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(PAYLOAD_UPDATE_BRANCH)).andExpect(status().isOk());
	}
}