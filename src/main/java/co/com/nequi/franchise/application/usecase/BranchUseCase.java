package co.com.nequi.franchise.application.usecase;

import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.com.nequi.franchise.domain.model.Branch;
import co.com.nequi.franchise.domain.model.Franchise;
import co.com.nequi.franchise.domain.repository.BranchRepository;
import co.com.nequi.franchise.domain.repository.FranchiseRepository;
import co.com.nequi.franchise.domain.service.BranchService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Service
public class BranchUseCase implements BranchService {

	private final BranchRepository branchRepository;
	private final FranchiseRepository franchiseRepository;

	@Override
	public Branch registerBranch(UUID franchiseUuid, Branch branch) {
		Franchise franchise = this.getFranchiseRepository().findByUniqueId(franchiseUuid);

		if (Objects.isNull(franchise))
			return null;

		branch.setFranchise(franchise);
		return this.getBranchRepository().save(branch);
	}

	@Override
	public Branch updateBranch(UUID uniqueId, Branch branch) {
		Branch branchCurrent = this.getBranchRepository().findByUniqueId(uniqueId);

		if (Objects.isNull(branchCurrent))
			return null;

		branchCurrent.setName(branch.getName());
		return this.getBranchRepository().save(branchCurrent);
	}
}