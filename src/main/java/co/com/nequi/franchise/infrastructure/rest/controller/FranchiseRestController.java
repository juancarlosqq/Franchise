package co.com.nequi.franchise.infrastructure.rest.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.nequi.franchise.infrastructure.rest.model.request.FranchiseRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.FranchiseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Franchise API")
public interface FranchiseRestController {

	ResponseEntity<FranchiseResponse> registerFranchise(@RequestBody @Valid FranchiseRequest franchiseRequest);

	ResponseEntity<FranchiseResponse> updateFranchise(
			@PathVariable(name = "uuid-franchise", required = true) UUID uniqueId,
			@RequestBody @Valid FranchiseRequest franchiseRequest);
}
