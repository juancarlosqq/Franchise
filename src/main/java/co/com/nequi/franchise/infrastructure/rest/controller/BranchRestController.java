package co.com.nequi.franchise.infrastructure.rest.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.nequi.franchise.infrastructure.rest.model.request.BranchRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.BranchResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Branch API")
public interface BranchRestController {

	ResponseEntity<BranchResponse> registerBranch(
			@PathVariable(name = "uuid-franchise", required = true) UUID franchiseUuid,
			@RequestBody @Valid BranchRequest branchRequest);

	ResponseEntity<BranchResponse> updateBranch(@PathVariable(name = "uuid-branch", required = true) UUID uniqueId,
			@RequestBody @Valid BranchRequest branchRequest);
}
