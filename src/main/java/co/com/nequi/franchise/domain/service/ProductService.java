package co.com.nequi.franchise.domain.service;

import java.util.List;
import java.util.UUID;

import co.com.nequi.franchise.domain.model.Product;

public interface ProductService {

	void deleteProduct(UUID uniqueId);

	List<Product> findAllByBranch(UUID branchUuid);

	List<Product> findAllProductsWithLargestStockByFranchise(UUID franchiseUuid);

	Product registerProduct(UUID branchUuid, Product product);

	Product updateProduct(UUID uniqueId, Product product);

	Product updateStockByProduct(UUID uniqueId, Long newStock);
}