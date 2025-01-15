package co.com.nequi.franchise.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import co.com.nequi.franchise.domain.repository.FranchiseRepository;

class FranchiseUseCaseTest {

	@Mock
	private FranchiseRepository franchiseRepository;

	@InjectMocks
	private FranchiseUseCase franchiseUseCase;

	private Franchise franchise;
	private UUID franchiseUuid;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		franchiseUuid = UUID.randomUUID();
		franchise = new Franchise();
		franchise.setUniqueId(franchiseUuid);
		franchise.setName("Franquicia 001");
	}

	@Test
    void registerFranchise() {
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(franchise);

        Franchise result = franchiseUseCase.registerFranchise(franchise);

        assertNotNull(result);
        assertEquals(franchise.getUniqueId(), result.getUniqueId());
        assertEquals(franchise.getName(), result.getName());
        verify(franchiseRepository, times(1)).save(any(Franchise.class));
    }

	@Test
    void updateFranchiseWhenFranchiseNotFound() {
        when(franchiseRepository.findByUniqueId(franchiseUuid)).thenReturn(null);

        Franchise result = franchiseUseCase.updateFranchise(franchiseUuid, franchise);

        assertNull(result);
        verify(franchiseRepository, times(1)).findByUniqueId(franchiseUuid);
        verify(franchiseRepository, times(0)).save(any(Franchise.class));
    }

	@Test
	void updateFranchiseWhenFranchiseFound() {
		Franchise existingFranchise = new Franchise();
		existingFranchise.setUniqueId(franchiseUuid);
		existingFranchise.setName("Franquicia Existente");

		when(franchiseRepository.findByUniqueId(franchiseUuid)).thenReturn(existingFranchise);
		when(franchiseRepository.save(any(Franchise.class))).thenReturn(franchise);

		Franchise result = franchiseUseCase.updateFranchise(franchiseUuid, franchise);

		assertNotNull(result);
		assertEquals(franchise.getName(), result.getName());
		verify(franchiseRepository, times(1)).findByUniqueId(franchiseUuid);
		verify(franchiseRepository, times(1)).save(any(Franchise.class));
	}
}