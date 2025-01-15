package co.com.nequi.franchise.infrastructure.rest.controller.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import co.com.nequi.franchise.domain.model.Product;
import co.com.nequi.franchise.domain.service.ProductService;
import co.com.nequi.franchise.infrastructure.rest.model.mapper.ProductMapper;
import co.com.nequi.franchise.infrastructure.rest.model.request.ProductRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.ProductInformationWithHighStockResponse;
import co.com.nequi.franchise.infrastructure.rest.model.response.ProductResponse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductRestControllerImplTest {

	private MockMvc mockMvc;

	@Mock
	private ProductService productService;

	@Mock
	private ProductMapper productMapper;

	@InjectMocks
	private ProductRestControllerImpl productRestController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productRestController).build();
	}

	@Test
	void deleteProduct() throws Exception {
		UUID productUuid = UUID.randomUUID();

		doNothing().when(productService).deleteProduct(productUuid);

		mockMvc.perform(delete("/product/{uuid-product}", productUuid)).andExpect(status().isOk());
	}

	@Test
	void findAllByBranch() throws Exception {
		UUID branchUuid = UUID.randomUUID();
		List<ProductResponse> productResponses = List.of(new ProductResponse(), new ProductResponse());

		when(productService.findAllByBranch(branchUuid)).thenReturn(List.of(new Product(), new Product()));
		when(productMapper.toProductResponse(anyList())).thenReturn(productResponses);

		mockMvc.perform(get("/branch/{uuid-branch}/product/all", branchUuid)).andExpect(status().isOk());
	}

	@Test
	void findAllProductsWithLargestStockByFranchise() throws Exception {
		UUID franchiseUuid = UUID.randomUUID();
		List<ProductInformationWithHighStockResponse> productResponses = List
				.of(new ProductInformationWithHighStockResponse(), new ProductInformationWithHighStockResponse());

		when(productService.findAllProductsWithLargestStockByFranchise(franchiseUuid))
				.thenReturn(List.of(new Product(), new Product()));
		when(productMapper.toProductInformationWithHighStockResponse(anyList())).thenReturn(productResponses);

		mockMvc.perform(get("/franchise/{uuid-franchise}/product/largest-stock", franchiseUuid))
				.andExpect(status().isOk());
	}

	@Test
	void registerProduct() throws Exception {
		UUID branchUuid = UUID.randomUUID();
		ProductRequest productRequest = new ProductRequest();
		ProductResponse productResponse = new ProductResponse();

		when(productMapper.toProduct(productRequest)).thenReturn(new Product());
		when(productService.registerProduct(eq(branchUuid), any())).thenReturn(new Product());
		when(productMapper.toProductResponse(any(Product.class))).thenReturn(productResponse);

		mockMvc.perform(
				post("/branch/{uuid-branch}/product/register", branchUuid).contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON).content("{\"name\": \"Producto 1\", \"stock\": 100}"))
				.andExpect(status().isOk());
	}

	@Test
	void updateProduct() throws Exception {
		UUID productUuid = UUID.randomUUID();
		ProductRequest productRequest = new ProductRequest();
		ProductResponse productResponse = new ProductResponse();

		when(productMapper.toProduct(productRequest)).thenReturn(new Product());
		when(productService.updateProduct(eq(productUuid), any())).thenReturn(new Product());
		when(productMapper.toProductResponse(any(Product.class))).thenReturn(productResponse);

		mockMvc.perform(put("/product/{uuid-product}", productUuid).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content("{\"name\": \"Producto 001\", \"stock\": 150}"))
				.andExpect(status().isOk());
	}
}