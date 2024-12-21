package co.com.nequi.franchise.infrastructure.rest.controller.impl;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.nequi.franchise.domain.service.FranchiseService;
import co.com.nequi.franchise.infrastructure.rest.controller.FranchiseRestController;
import co.com.nequi.franchise.infrastructure.rest.model.mapper.FranchiseMapper;
import co.com.nequi.franchise.infrastructure.rest.model.request.FranchiseRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.FranchiseResponse;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
@RestController
@RequestMapping("/franchise")
@CrossOrigin("*")
public class FranchiseRestControllerImpl implements FranchiseRestController {

	private final FranchiseMapper franchiseMapper;
	private final FranchiseService franchiseService;

	public FranchiseRestControllerImpl(FranchiseService franchiseService) {
		super();
		this.franchiseMapper = FranchiseMapper.INSTANCE;
		this.franchiseService = franchiseService;
	}

	@Override
	@PostMapping
	public ResponseEntity<FranchiseResponse> registerFranchise(@RequestBody @Valid FranchiseRequest franchiseRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.getFranchiseMapper().toFranchiseResponse(
				this.getFranchiseService().registerFranchise(this.getFranchiseMapper().toFranchise(franchiseRequest))));
	}

	@Override
	@PutMapping("/{uuid-franchise}")
	public ResponseEntity<FranchiseResponse> updateFranchise(
			@PathVariable(name = "uuid-franchise", required = true) UUID uniqueId,
			@RequestBody @Valid FranchiseRequest franchiseRequest) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.getFranchiseMapper().toFranchiseResponse(this.getFranchiseService().updateFranchise(uniqueId,
						this.getFranchiseMapper().toFranchise(franchiseRequest))));
	}
}
