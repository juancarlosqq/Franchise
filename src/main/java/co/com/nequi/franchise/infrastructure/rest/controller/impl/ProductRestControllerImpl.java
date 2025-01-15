package co.com.nequi.franchise.infrastructure.rest.controller.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.com.nequi.franchise.domain.service.ProductService;
import co.com.nequi.franchise.infrastructure.rest.controller.ProductRestController;
import co.com.nequi.franchise.infrastructure.rest.model.mapper.ProductMapper;
import co.com.nequi.franchise.infrastructure.rest.model.request.ProductRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.ProductInformationWithHighStockResponse;
import co.com.nequi.franchise.infrastructure.rest.model.response.ProductResponse;
import jakarta.validation.Valid;
import lombok.Getter;

@Getter
@RestController
@CrossOrigin("*")
public class ProductRestControllerImpl implements ProductRestController {

	private final ProductMapper productMapper;
	private final ProductService productService;

	public ProductRestControllerImpl(ProductService productService) {
		super();
		this.productMapper = ProductMapper.INSTANCE;
		this.productService = productService;
	}

	@Override
	@DeleteMapping("/product/{uuid-product}")
	public ResponseEntity<Void> deleteProduct(@PathVariable(name = "uuid-product", required = true) UUID uniqueId) {
		this.getProductService().deleteProduct(uniqueId);

		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Override
	@GetMapping("/branch/{uuid-branch}/product/all")
	public ResponseEntity<List<ProductResponse>> findAllByBranch(
			@PathVariable(name = "uuid-branch", required = true) UUID branchUuid) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.getProductMapper().toProductResponse(this.getProductService().findAllByBranch(branchUuid)));
	}

	@Override
	@GetMapping("/franchise/{uuid-franchise}/product/largest-stock")
	public ResponseEntity<List<ProductInformationWithHighStockResponse>> findAllProductsWithLargestStockByFranchise(
			@PathVariable(name = "uuid-franchise", required = true) UUID franchiseUuid) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(this.getProductMapper().toProductInformationWithHighStockResponse(
						this.getProductService().findAllProductsWithLargestStockByFranchise(franchiseUuid)));
	}

	@Override
	@PostMapping("/branch/{uuid-branch}/product/register")
	public ResponseEntity<ProductResponse> registerProduct(
			@PathVariable(name = "uuid-branch", required = true) UUID branchUuid,
			@RequestBody @Valid ProductRequest productRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.getProductMapper().toProductResponse(this
				.getProductService().registerProduct(branchUuid, this.getProductMapper().toProduct(productRequest))));
	}

	@Override
	@PutMapping("/product/{uuid-product}")
	public ResponseEntity<ProductResponse> updateProduct(
			@PathVariable(name = "uuid-product", required = true) UUID uniqueId,
			@RequestBody @Valid ProductRequest productRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(this.getProductMapper().toProductResponse(
				this.getProductService().updateProduct(uniqueId, this.getProductMapper().toProduct(productRequest))));
	}
}
