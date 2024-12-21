package co.com.nequi.franchise.application.usecase;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import co.com.nequi.franchise.domain.model.Branch;
import co.com.nequi.franchise.domain.model.Product;
import co.com.nequi.franchise.domain.repository.BranchRepository;
import co.com.nequi.franchise.domain.repository.ProductRepository;
import co.com.nequi.franchise.domain.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Service
public class ProductUseCase implements ProductService {

	private final BranchRepository branchRepository;
	private final ProductRepository productRepository;

	@Override
	public void deleteProduct(UUID uniqueId) {
		Product currentProduct = this.getProductRepository().findByUniqueId(uniqueId);

		if (Objects.isNull(currentProduct))
			return;

		this.getProductRepository().delete(currentProduct);
	}

	@Override
	public List<Product> findAllByBranch(UUID branchUuid) {
		return this.getProductRepository().findAllByBranch(branchUuid);
	}

	public List<Product> findAllProductsWithLargestStockByFranchise(UUID franchiseUuid) {
		return this.getBranchRepository().findAllByFranchise(franchiseUuid).stream()
				.map(branch -> this.getProductRepository()
						.findAllProductsWithLargestStockByFranchiseAndBranch(franchiseUuid, branch.getUniqueId()))
				.flatMap(List::stream).collect(Collectors.toList());
	}

	@Override
	public Product registerProduct(UUID branchUuid, Product product) {
		Branch branch = this.getBranchRepository().findByUniqueId(branchUuid);

		if (Objects.isNull(branch)) {
			return null;
		}

		product.setBranch(branch);
		return this.getProductRepository().save(product);
	}

	@Override
	public Product updateProduct(UUID uniqueId, Product product) {
		Product currentProduct = this.getProductRepository().findByUniqueId(uniqueId);

		if (Objects.isNull(currentProduct))
			return null;

		currentProduct.setName(product.getName());
		currentProduct.setStock(product.getStock());

		return this.getProductRepository().save(currentProduct);
	}

	@Override
	public Product updateStockByProduct(UUID uniqueId, Long newStock) {
		Product currentProduct = this.getProductRepository().findByUniqueId(uniqueId);

		if (Objects.isNull(currentProduct))
			return null;

		currentProduct.setStock(newStock);

		return this.getProductRepository().save(currentProduct);
	}
}