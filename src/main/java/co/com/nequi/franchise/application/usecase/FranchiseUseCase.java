package co.com.nequi.franchise.application.usecase;

import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import co.com.nequi.franchise.domain.model.Franchise;
import co.com.nequi.franchise.domain.repository.FranchiseRepository;
import co.com.nequi.franchise.domain.service.FranchiseService;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Service
public class FranchiseUseCase implements FranchiseService {

	private final FranchiseRepository franchiseRepository;

	@Override
	public Franchise registerFranchise(Franchise franchise) {
		return this.getFranchiseRepository().save(franchise);
	}

	@Override
	public Franchise updateFranchise(UUID uniqueId, Franchise franchise) {
		Franchise franchiseCurrent = this.getFranchiseRepository().findByUniqueId(uniqueId);

		if (!Objects.isNull(franchiseCurrent)) {
			franchiseCurrent.setName(franchise.getName());

			return this.getFranchiseRepository().save(franchiseCurrent);
		}

		return null;
	}
}