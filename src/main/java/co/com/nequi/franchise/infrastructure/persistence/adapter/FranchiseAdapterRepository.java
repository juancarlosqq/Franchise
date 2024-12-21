package co.com.nequi.franchise.infrastructure.persistence.adapter;

import java.util.UUID;

import org.springframework.stereotype.Component;

import co.com.nequi.franchise.domain.model.Franchise;
import co.com.nequi.franchise.domain.repository.FranchiseRepository;
import co.com.nequi.franchise.infrastructure.persistence.adapter.mapper.FranchiseAdapterMapper;
import co.com.nequi.franchise.infrastructure.persistence.repository.FranchiseJpaRepository;
import lombok.Getter;

@Getter
@Component
public class FranchiseAdapterRepository implements FranchiseRepository {

	private final FranchiseAdapterMapper franchiseAdapterMapper;
	private final FranchiseJpaRepository franchiseJpaRepository;

	public FranchiseAdapterRepository(FranchiseJpaRepository franchiseJpaRepository) {
		super();
		this.franchiseAdapterMapper = FranchiseAdapterMapper.INSTANCE;
		this.franchiseJpaRepository = franchiseJpaRepository;
	}

	@Override
	public Franchise findByUniqueId(UUID uniqueId) {
		return this.getFranchiseAdapterMapper().toFranchise(this.getFranchiseJpaRepository().findByUniqueId(uniqueId));
	}

	@Override
	public Franchise save(Franchise franchise) {
		return this.getFranchiseAdapterMapper().toFranchise(
				this.getFranchiseJpaRepository().save(this.getFranchiseAdapterMapper().toFranchiseEntity(franchise)));
	}
}