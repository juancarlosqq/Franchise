package co.com.nequi.franchise.domain.repository;

import java.util.List;
import java.util.UUID;

import co.com.nequi.franchise.domain.model.Branch;

public interface BranchRepository {
	
	List<Branch> findAllByFranchise(UUID franchiseUuid);

	Branch findByUniqueId(UUID uniqueId);

	Branch save(Branch branch);
}
