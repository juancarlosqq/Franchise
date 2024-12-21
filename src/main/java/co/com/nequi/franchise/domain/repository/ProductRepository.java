package co.com.nequi.franchise.domain.repository;

import java.util.List;
import java.util.UUID;

import co.com.nequi.franchise.domain.model.Product;

public interface ProductRepository {

	void delete(Product product);

	List<Product> findAllByBranch(UUID branchUuid);

	List<Product> findAllProductsWithLargestStockByFranchiseAndBranch(UUID franchiseUuid, UUID branchUuid);

	Product findByUniqueId(UUID uniqueId);

	Product save(Product product);
}