package co.com.nequi.franchise.infrastructure.persistence.adapter;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import co.com.nequi.franchise.domain.model.Branch;
import co.com.nequi.franchise.domain.repository.BranchRepository;
import co.com.nequi.franchise.infrastructure.persistence.adapter.mapper.BranchAdapterMapper;
import co.com.nequi.franchise.infrastructure.persistence.repository.BranchJpaRepository;
import lombok.Getter;

@Getter
@Component
public class BranchAdapterRepository implements BranchRepository {

	private final BranchAdapterMapper branchAdapterMapper;
	private final BranchJpaRepository branchJpaRepository;

	public BranchAdapterRepository(BranchJpaRepository branchJpaRepository) {
		super();
		this.branchAdapterMapper = BranchAdapterMapper.INSTANCE;
		this.branchJpaRepository = branchJpaRepository;
	}

	@Override
	public List<Branch> findAllByFranchise(UUID franchiseUuid) {
		return this.getBranchAdapterMapper().toBranch(this.getBranchJpaRepository().findAllByFranchise(franchiseUuid));
	}

	@Override
	public Branch findByUniqueId(UUID uniqueId) {
		return this.getBranchAdapterMapper().toBranch(this.getBranchJpaRepository().findByUniqueId(uniqueId));
	}

	@Override
	public Branch save(Branch branch) {
		return this.getBranchAdapterMapper()
				.toBranch(this.getBranchJpaRepository().save(this.getBranchAdapterMapper().toBranchEntity(branch)));
	}

}
