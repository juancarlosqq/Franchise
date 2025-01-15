package co.com.nequi.franchise.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
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
import co.com.nequi.franchise.domain.model.Product;
import co.com.nequi.franchise.domain.repository.BranchRepository;
import co.com.nequi.franchise.domain.repository.ProductRepository;

class ProductUseCaseTest {

	@Mock
	private BranchRepository branchRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductUseCase productUseCase;

	private Product product;
	private Branch branch;
	private UUID branchUuid;
	private UUID productUuid;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		branchUuid = UUID.randomUUID();
		productUuid = UUID.randomUUID();

		branch = new Branch();
		branch.setUniqueId(branchUuid);
		branch.setName("Sucursal Ejemplo");

		product = new Product();
		product.setUniqueId(productUuid);
		product.setName("Producto Ejemplo");
		product.setStock(100L);
	}

	@Test
    void registerProductWhenBranchNotFound() {
        when(branchRepository.findByUniqueId(branchUuid)).thenReturn(null);

        Product result = productUseCase.registerProduct(branchUuid, product);

        assertNull(result, "Si no se encuentra la sucursal, el producto no debe ser registrado.");
        verify(branchRepository, times(1)).findByUniqueId(branchUuid);
        verify(productRepository, times(0)).save(any(Product.class));
    }

	@Test
    void registerProductWhenBranchFound() {
        when(branchRepository.findByUniqueId(branchUuid)).thenReturn(branch);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productUseCase.registerProduct(branchUuid, product);

        assertNotNull(result, "El producto debe ser registrado correctamente.");
        assertEquals(product.getUniqueId(), result.getUniqueId(), "Los UUIDs deben coincidir.");
        verify(branchRepository, times(1)).findByUniqueId(branchUuid);
        verify(productRepository, times(1)).save(any(Product.class));
    }

	@Test
    void deleteProductWhenProductNotFound() {
        when(productRepository.findByUniqueId(productUuid)).thenReturn(null);

        productUseCase.deleteProduct(productUuid);

        verify(productRepository, times(1)).findByUniqueId(productUuid);
        verify(productRepository, times(0)).delete(any(Product.class));
    }

	@Test
	void deleteProductWhenProductFound() {
		Product existingProduct = new Product();
		existingProduct.setUniqueId(productUuid);
		existingProduct.setName("Producto Existente");

		when(productRepository.findByUniqueId(productUuid)).thenReturn(existingProduct);

		productUseCase.deleteProduct(productUuid);

		verify(productRepository, times(1)).findByUniqueId(productUuid);
		verify(productRepository, times(1)).delete(any(Product.class));
	}

	@Test
    void updateProductWhenProductNotFound() {
        when(productRepository.findByUniqueId(productUuid)).thenReturn(null);

        Product result = productUseCase.updateProduct(productUuid, product);

        assertNull(result, "Si no se encuentra el producto, el resultado debe ser null.");
        verify(productRepository, times(1)).findByUniqueId(productUuid);
        verify(productRepository, times(0)).save(any(Product.class));
    }

	@Test
	void updateProductWhenProductFound() {
		Product existingProduct = new Product();
		existingProduct.setUniqueId(productUuid);
		existingProduct.setName("Producto Existente");
		existingProduct.setStock(50L);

		when(productRepository.findByUniqueId(productUuid)).thenReturn(existingProduct);
		when(productRepository.save(any(Product.class))).thenReturn(product);

		Product result = productUseCase.updateProduct(productUuid, product);

		assertNotNull(result, "El producto debe ser actualizado correctamente.");
		assertEquals(product.getName(), result.getName(), "Los nombres deben coincidir.");
		verify(productRepository, times(1)).findByUniqueId(productUuid);
		verify(productRepository, times(1)).save(any(Product.class));
	}

	@Test
	void findAllProductsWithLargestStockByFranchise() {
		Branch branch1 = new Branch();
		branch1.setUniqueId(UUID.randomUUID());
		branch1.setName("Sucursal 1");

		Branch branch2 = new Branch();
		branch2.setUniqueId(UUID.randomUUID());
		branch2.setName("Sucursal 2");

		List<Branch> branches = Arrays.asList(branch1, branch2);

		Product product1 = new Product();
		product1.setStock(200L);
		Product product2 = new Product();
		product2.setStock(150L);

		when(branchRepository.findAllByFranchise(any(UUID.class))).thenReturn(branches);
		when(productRepository.findAllProductsWithLargestStockByFranchiseAndBranch(any(UUID.class), any(UUID.class)))
				.thenReturn(Arrays.asList(product1, product2));

		List<Product> result = productUseCase.findAllProductsWithLargestStockByFranchise(UUID.randomUUID());

		assertNotNull(result, "La lista de productos debe ser devuelta correctamente.");
		assertEquals(2, result.size(), "El número de productos devueltos debe ser correcto.");
		verify(branchRepository, times(1)).findAllByFranchise(any(UUID.class));
		verify(productRepository, times(2)).findAllProductsWithLargestStockByFranchiseAndBranch(any(UUID.class),
				any(UUID.class));
	}
}