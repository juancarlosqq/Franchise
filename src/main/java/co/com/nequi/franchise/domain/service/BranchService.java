package co.com.nequi.franchise.domain.service;

import java.util.UUID;

import co.com.nequi.franchise.domain.model.Branch;

public interface BranchService {

	Branch registerBranch(UUID franchiseUuid, Branch branch);

	Branch updateBranch(UUID uniqueId, Branch branch);
}
