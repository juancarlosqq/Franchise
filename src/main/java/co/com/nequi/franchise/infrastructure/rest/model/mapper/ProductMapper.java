package co.com.nequi.franchise.infrastructure.rest.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.com.nequi.franchise.domain.model.Product;
import co.com.nequi.franchise.infrastructure.rest.model.request.ProductRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.ProductInformationWithHighStockResponse;
import co.com.nequi.franchise.infrastructure.rest.model.response.ProductResponse;

@Mapper
public interface ProductMapper {

	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "uniqueId", ignore = true)
	@Mapping(target = "branch", ignore = true)
	Product toProduct(ProductRequest productRequest);

	ProductResponse toProductResponse(Product product);

	List<ProductResponse> toProductResponse(List<Product> product);

	List<ProductInformationWithHighStockResponse> toProductInformationWithHighStockResponse(List<Product> product);
}
