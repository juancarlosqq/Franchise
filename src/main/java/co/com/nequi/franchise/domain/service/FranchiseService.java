package co.com.nequi.franchise.domain.service;

import java.util.UUID;

import co.com.nequi.franchise.domain.model.Franchise;

public interface FranchiseService {

	Franchise registerFranchise(Franchise franchise);

	Franchise updateFranchise(UUID uniqueId, Franchise franchise);
}
