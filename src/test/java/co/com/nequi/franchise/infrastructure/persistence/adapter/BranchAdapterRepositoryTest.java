package co.com.nequi.franchise.infrastructure.persistence.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.nequi.franchise.domain.model.Branch;
import co.com.nequi.franchise.infrastructure.persistence.adapter.mapper.BranchAdapterMapper;
import co.com.nequi.franchise.infrastructure.persistence.entity.BranchEntity;
import co.com.nequi.franchise.infrastructure.persistence.repository.BranchJpaRepository;

class BranchAdapterRepositoryTest {

	@Mock
	private BranchJpaRepository branchJpaRepository;

	@Mock
	private BranchAdapterMapper branchAdapterMapper;

	@InjectMocks
	private BranchAdapterRepository branchAdapterRepository;

	private UUID franchiseUuid;
	private UUID branchUuid;
	private Branch branch;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		franchiseUuid = UUID.randomUUID();
		branchUuid = UUID.randomUUID();

		branch = new Branch();
		branch.setUniqueId(branchUuid);
		branch.setName("Sucursal 001");
	}

	@Test
	void findAllByFranchise() {
		List<BranchEntity> branchEntities = Arrays.asList(new BranchEntity());
		when(branchJpaRepository.findAllByFranchise(franchiseUuid)).thenReturn(branchEntities);
		when(branchAdapterMapper.toBranch(anyList())).thenReturn(Arrays.asList(branch));

		List<Branch> result = branchAdapterRepository.findAllByFranchise(franchiseUuid);

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(branchJpaRepository, times(1)).findAllByFranchise(franchiseUuid);
	}

	@Test
	void findByUniqueId() {
		BranchEntity branchEntity = new BranchEntity();
		branchEntity.setUniqueId(branchUuid);
		when(branchJpaRepository.findByUniqueId(branchUuid)).thenReturn(branchEntity);

		Branch result = branchAdapterRepository.findByUniqueId(branchUuid);

		assertNotNull(result);
		assertEquals(branchUuid, result.getUniqueId());
		verify(branchJpaRepository, times(1)).findByUniqueId(branchUuid);
	}

	@Test
	void save() {
		BranchEntity branchEntity = new BranchEntity();
		branchEntity.setUniqueId(branchUuid);
		when(branchAdapterMapper.toBranchEntity(any(Branch.class))).thenReturn(branchEntity);
		when(branchJpaRepository.save(any())).thenReturn(branchEntity);
		when(branchAdapterMapper.toBranch(any(BranchEntity.class))).thenReturn(branch);

		Branch result = branchAdapterRepository.save(branch);

		assertNotNull(result);
		assertEquals(branchUuid, result.getUniqueId());
		verify(branchJpaRepository, times(1)).save(any());
	}
}