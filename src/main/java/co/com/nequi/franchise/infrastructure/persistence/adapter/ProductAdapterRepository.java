package co.com.nequi.franchise.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import co.com.nequi.franchise.domain.model.Product;
import co.com.nequi.franchise.domain.repository.ProductRepository;
import co.com.nequi.franchise.infrastructure.persistence.adapter.mapper.ProductAdapterMapper;
import co.com.nequi.franchise.infrastructure.persistence.repository.ProductJpaRepository;
import lombok.Getter;

@Getter
@Component
public class ProductAdapterRepository implements ProductRepository {

	private final ProductAdapterMapper productAdapterMapper;
	private final ProductJpaRepository productJpaRepository;

	public ProductAdapterRepository(ProductJpaRepository productJpaRepository) {
		super();
		this.productAdapterMapper = ProductAdapterMapper.INSTANCE;
		this.productJpaRepository = productJpaRepository;
	}

	@Override
	public void delete(Product product) {
		this.getProductJpaRepository().delete(this.getProductAdapterMapper().toProductEntity(product));
	}

	@Override
	public List<Product> findAllByBranch(UUID branchUuid) {
		return this.getProductAdapterMapper().toProduct(this.getProductJpaRepository().findAllByBranch(branchUuid));
	}

	@Override
	public List<Product> findAllProductsWithLargestStockByFranchiseAndBranch(UUID franchiseUuid, UUID branchUuid) {
		return this.getProductAdapterMapper().toProduct(this.getProductJpaRepository()
				.findAllProductsWithLargestStockByFranchiseAndBranch(franchiseUuid, branchUuid));
	}

	@Override
	public Product findByUniqueId(UUID uniqueId) {
		return this.getProductAdapterMapper().toProduct(this.getProductJpaRepository().findByUniqueId(uniqueId));
	}

	@Override
	public Product save(Product product) {
		return this.getProductAdapterMapper().toProduct(
				this.getProductJpaRepository().save(this.getProductAdapterMapper().toProductEntity(product)));
	}
}