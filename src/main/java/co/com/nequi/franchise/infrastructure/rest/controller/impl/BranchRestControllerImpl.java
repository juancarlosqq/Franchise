package co.com.nequi.franchise.infrastructure.rest.controller.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.nequi.franchise.domain.service.BranchService;
import co.com.nequi.franchise.infrastructure.rest.controller.BranchRestController;
import co.com.nequi.franchise.infrastructure.rest.model.mapper.BranchMapper;
import co.com.nequi.franchise.infrastructure.rest.model.request.BranchRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.BranchResponse;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
@RestController
@CrossOrigin("*")
public class BranchRestControllerImpl implements BranchRestController {

	private final BranchMapper branchMapper;
	private final BranchService branchService;

	public BranchRestControllerImpl(BranchService branchService) {
		super();
		this.branchMapper = BranchMapper.INSTANCE;
		this.branchService = branchService;
	}

	@Override
	@PostMapping("/franchise/{uuid-franchise}/branch/register")
	public ResponseEntity<BranchResponse> registerBranch(
			@PathVariable(name = "uuid-franchise", required = true) UUID franchiseUuid,
			@RequestBody @Valid BranchRequest branchRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.getBranchMapper().toBranchResponse(
				this.getBranchService().registerBranch(franchiseUuid, this.getBranchMapper().toBranch(branchRequest))));
	}

	@Override
	@PutMapping("/branch/{uuid-branch}")
	public ResponseEntity<BranchResponse> updateBranch(
			@PathVariable(name = "uuid-branch", required = true) UUID uniqueId,
			@RequestBody @Valid BranchRequest branchRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.getBranchMapper().toBranchResponse(
				this.getBranchService().updateBranch(uniqueId, this.getBranchMapper().toBranch(branchRequest))));
	}

}
