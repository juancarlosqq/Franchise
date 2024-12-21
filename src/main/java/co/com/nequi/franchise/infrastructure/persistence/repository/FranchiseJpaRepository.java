package co.com.nequi.franchise.infrastructure.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.com.nequi.franchise.infrastructure.persistence.entity.FranchiseEntity;

@Repository
public interface FranchiseJpaRepository
		extends JpaRepository<FranchiseEntity, Long>, JpaSpecificationExecutor<FranchiseEntity> {

	FranchiseEntity findByUniqueId(UUID uniqueId);
}