package co.com.nequi.franchise.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.nequi.franchise.infrastructure.persistence.entity.ProductEntity;

@Repository
public interface ProductJpaRepository
		extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

	@Query("SELECT p FROM ProductEntity p WHERE p.branch.uniqueId = :branchUuid")
	List<ProductEntity> findAllByBranch(UUID branchUuid);
	
	@Query("SELECT p2 FROM ProductEntity p2 WHERE p2.stock = ("
			+ "SELECT max(p1.stock) FROM ProductEntity p1 "
			+ "WHERE p1.branch.uniqueId = :branchUuid AND p1.branch.franchise.uniqueId = :franchiseUuid)")
	List<ProductEntity> findAllProductsWithLargestStockByFranchiseAndBranch(UUID franchiseUuid, UUID branchUuid);

	ProductEntity findByUniqueId(UUID uniqueId);
}