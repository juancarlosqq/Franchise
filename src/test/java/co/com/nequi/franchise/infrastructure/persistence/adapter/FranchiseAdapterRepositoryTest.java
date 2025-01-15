package co.com.nequi.franchise.infrastructure.persistence.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.nequi.franchise.domain.model.Franchise;
import co.com.nequi.franchise.infrastructure.persistence.adapter.mapper.FranchiseAdapterMapper;
import co.com.nequi.franchise.infrastructure.persistence.entity.FranchiseEntity;
import co.com.nequi.franchise.infrastructure.persistence.repository.FranchiseJpaRepository;

class FranchiseAdapterRepositoryTest {

	@Mock
	private FranchiseJpaRepository franchiseJpaRepository;

	@Mock
	private FranchiseAdapterMapper franchiseAdapterMapper;

	@InjectMocks
	private FranchiseAdapterRepository franchiseAdapterRepository;

	private UUID franchiseUuid;
	private Franchise franchise;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		franchiseUuid = UUID.randomUUID();
		franchise = new Franchise();
		franchise.setUniqueId(franchiseUuid);
		franchise.setName("Franquicia 001");
	}

	@Test
	void findByUniqueId() {
		FranchiseEntity franchiseEntity = new FranchiseEntity();
		franchiseEntity.setUniqueId(franchiseUuid);
		franchiseEntity.setName("Franquicia 001");
		when(franchiseJpaRepository.findByUniqueId(franchiseUuid)).thenReturn(franchiseEntity);
		when(franchiseAdapterMapper.toFranchise(any())).thenReturn(franchise);

		Franchise result = franchiseAdapterRepository.findByUniqueId(franchiseUuid);

		assertNotNull(result);
		assertEquals(franchiseUuid, result.getUniqueId());
		assertEquals("Franquicia 001", result.getName());
		verify(franchiseJpaRepository, times(1)).findByUniqueId(franchiseUuid);
	}

	@Test
	void save() {
		FranchiseEntity franchiseEntity = new FranchiseEntity();
		franchiseEntity.setUniqueId(franchiseUuid);
		franchiseEntity.setName("Franquicia 001");
		when(franchiseAdapterMapper.toFranchiseEntity(any(Franchise.class))).thenReturn(franchiseEntity);
		when(franchiseJpaRepository.save(any())).thenReturn(franchiseEntity);
		when(franchiseAdapterMapper.toFranchise(any(FranchiseEntity.class))).thenReturn(franchise);

		Franchise result = franchiseAdapterRepository.save(franchise);

		assertNotNull(result);
		assertEquals(franchiseUuid, result.getUniqueId());
		assertEquals("Franquicia 001", result.getName());
		verify(franchiseJpaRepository, times(1)).save(any());
	}
}