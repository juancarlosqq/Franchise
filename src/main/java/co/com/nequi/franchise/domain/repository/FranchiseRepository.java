package co.com.nequi.franchise.domain.repository;

import java.util.UUID;

import co.com.nequi.franchise.domain.model.Franchise;

public interface FranchiseRepository {

	Franchise findByUniqueId(UUID uniqueId);
	
	Franchise save(Franchise franchise);
}
