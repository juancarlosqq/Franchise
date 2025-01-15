package co.com.nequi.franchise.infrastructure.persistence.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.com.nequi.franchise.domain.model.Product;
import co.com.nequi.franchise.infrastructure.persistence.adapter.mapper.ProductAdapterMapper;
import co.com.nequi.franchise.infrastructure.persistence.entity.ProductEntity;
import co.com.nequi.franchise.infrastructure.persistence.repository.ProductJpaRepository;

class ProductAdapterRepositoryTest {

	@Mock
	private ProductJpaRepository productJpaRepository;

	@Mock
	private ProductAdapterMapper productAdapterMapper;

	@InjectMocks
	private ProductAdapterRepository productAdapterRepository;

	private UUID branchUuid;
	private UUID franchiseUuid;
	private UUID productUuid;
	private Product product;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		branchUuid = UUID.randomUUID();
		franchiseUuid = UUID.randomUUID();
		productUuid = UUID.randomUUID();
		product = new Product();
		product.setUniqueId(productUuid);
		product.setName("Producto 001");
		product.setStock(100L);
	}

	@Test
    void delete() {
        when(productAdapterMapper.toProductEntity(any())).thenReturn(new ProductEntity());

        productAdapterRepository.delete(product);

        verify(productJpaRepository, times(1)).delete(any(ProductEntity.class));
    }

	@Test
	void findAllByBranch() {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setUniqueId(productUuid);
		productEntity.setName("Producto 001");
		productEntity.setStock(100L);
		when(productJpaRepository.findAllByBranch(branchUuid)).thenReturn(List.of(productEntity));
		when(productAdapterMapper.toProduct(anyList())).thenReturn(List.of(product));

		List<Product> result = productAdapterRepository.findAllByBranch(branchUuid);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(productUuid, result.get(0).getUniqueId());
		verify(productJpaRepository, times(1)).findAllByBranch(branchUuid);
	}

	@Test
	void findAllProductsWithLargestStockByFranchiseAndBranch() {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setUniqueId(productUuid);
		productEntity.setName("Producto 001");
		productEntity.setStock(100L);
		when(productJpaRepository.findAllProductsWithLargestStockByFranchiseAndBranch(franchiseUuid, branchUuid))
				.thenReturn(List.of(productEntity));
		when(productAdapterMapper.toProduct(anyList())).thenReturn(List.of(product));

		List<Product> result = productAdapterRepository
				.findAllProductsWithLargestStockByFranchiseAndBranch(franchiseUuid, branchUuid);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(productUuid, result.get(0).getUniqueId());
		verify(productJpaRepository, times(1)).findAllProductsWithLargestStockByFranchiseAndBranch(franchiseUuid,
				branchUuid);
	}

	@Test
	void findByUniqueId() {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setUniqueId(productUuid);
		productEntity.setName("Producto 001");
		productEntity.setStock(100L);
		when(productJpaRepository.findByUniqueId(productUuid)).thenReturn(productEntity);
		when(productAdapterMapper.toProduct(any(ProductEntity.class))).thenReturn(product);

		Product result = productAdapterRepository.findByUniqueId(productUuid);

		assertNotNull(result);
		assertEquals(productUuid, result.getUniqueId());
		assertEquals("Producto 001", result.getName());
		verify(productJpaRepository, times(1)).findByUniqueId(productUuid);
	}

	@Test
	void testSave() {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setUniqueId(productUuid);
		productEntity.setName("Producto 001");
		productEntity.setStock(100L);
		when(productAdapterMapper.toProductEntity(any())).thenReturn(productEntity);
		when(productJpaRepository.save(any())).thenReturn(productEntity);
		when(productAdapterMapper.toProduct(any(ProductEntity.class))).thenReturn(product);

		Product result = productAdapterRepository.save(product);

		assertNotNull(result);
		assertEquals(productUuid, result.getUniqueId());
		assertEquals("Producto 001", result.getName());
		verify(productJpaRepository, times(1)).save(any());
	}
}