package co.com.nequi.franchise.infrastructure.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.nequi.franchise.infrastructure.persistence.entity.BranchEntity;

@Repository
public interface BranchJpaRepository extends JpaRepository<BranchEntity, Long>, JpaSpecificationExecutor<BranchEntity> {

	@Query("SELECT b FROM BranchEntity b WHERE b.franchise.uniqueId = :franchiseUuid")
	List<BranchEntity> findAllByFranchise(UUID franchiseUuid);

	BranchEntity findByUniqueId(UUID uniqueId);
}