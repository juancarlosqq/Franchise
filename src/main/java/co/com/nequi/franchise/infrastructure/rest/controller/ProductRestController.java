package co.com.nequi.franchise.infrastructure.rest.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.com.nequi.franchise.infrastructure.rest.model.request.ProductRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.ProductResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Product API")
public interface ProductRestController {

	ResponseEntity<Void> deleteProduct(@PathVariable(name = "uuid-product", required = true) UUID uniqueId);

	ResponseEntity<List<ProductResponse>> findAllByBranch(
			@PathVariable(name = "uuid-branch", required = true) UUID branchUuid);

	ResponseEntity<List<ProductResponse>> findAllProductsWithLargestStockByFranchise(
			@PathVariable(name = "uuid-franchise", required = true) UUID franchiseUuid);

	ResponseEntity<ProductResponse> registerProduct(
			@PathVariable(name = "uuid-branch", required = true) UUID branchUuid,
			@RequestBody @Valid ProductRequest productRequest);

	ResponseEntity<ProductResponse> updateProduct(@PathVariable(name = "uuid-product", required = true) UUID uniqueId,
			@RequestBody @Valid ProductRequest productRequest);
}
