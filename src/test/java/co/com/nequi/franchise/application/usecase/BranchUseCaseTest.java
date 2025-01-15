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

import co.com.nequi.franchise.domain.model.Branch;
import co.com.nequi.franchise.domain.model.Franchise;
import co.com.nequi.franchise.domain.repository.BranchRepository;
import co.com.nequi.franchise.domain.repository.FranchiseRepository;

class BranchUseCaseTest {

	@Mock
	private BranchRepository branchRepository;

	@Mock
	private FranchiseRepository franchiseRepository;

	@InjectMocks
	private BranchUseCase branchUseCase;

	private Franchise franchise;
	private Branch branch;
	private UUID franchiseUuid;
	private UUID branchUuid;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		franchiseUuid = UUID.randomUUID();
		branchUuid = UUID.randomUUID();
		franchise = new Franchise();
		franchise.setUniqueId(franchiseUuid);

		branch = new Branch();
		branch.setUniqueId(branchUuid);
		branch.setName("Sucursal Ejemplo");
	}

	@Test
     void registerBranchWhenFranchiseNotFound() {
        when(franchiseRepository.findByUniqueId(franchiseUuid)).thenReturn(null);

        Branch result = branchUseCase.registerBranch(franchiseUuid, branch);

        assertNull(result);
        verify(franchiseRepository, times(1)).findByUniqueId(franchiseUuid);
    }

	@Test
     void registerBranchWhenFranchiseFound() {
        when(franchiseRepository.findByUniqueId(franchiseUuid)).thenReturn(franchise);
        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch result = branchUseCase.registerBranch(franchiseUuid, branch);

        assertNotNull(result);
        assertEquals(branch.getUniqueId(), result.getUniqueId());
        verify(franchiseRepository, times(1)).findByUniqueId(franchiseUuid);
        verify(branchRepository, times(1)).save(any(Branch.class));
    }

	@Test
    void updateBranchWhenBranchNotFound() {
        when(branchRepository.findByUniqueId(branchUuid)).thenReturn(null);

        Branch result = branchUseCase.updateBranch(branchUuid, branch);

        assertNull(result);
        verify(branchRepository, times(1)).findByUniqueId(branchUuid);
    }

	@Test
	void updateBranchWhenBranchFound() {
		Branch existingBranch = new Branch();
		existingBranch.setUniqueId(branchUuid);
		existingBranch.setName("Sucursal Existente");

		when(branchRepository.findByUniqueId(branchUuid)).thenReturn(existingBranch);
		when(branchRepository.save(any(Branch.class))).thenReturn(branch);

		Branch result = branchUseCase.updateBranch(branchUuid, branch);

		assertNotNull(result);
		assertEquals(branch.getName(), result.getName());
		verify(branchRepository, times(1)).findByUniqueId(branchUuid);
		verify(branchRepository, times(1)).save(any(Branch.class));
	}
}