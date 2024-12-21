package co.com.nequi.franchise.infrastructure.persistence.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.com.nequi.franchise.domain.model.Product;
import co.com.nequi.franchise.infrastructure.persistence.entity.ProductEntity;

@Mapper
public interface ProductAdapterMapper {

	ProductAdapterMapper INSTANCE = Mappers.getMapper(ProductAdapterMapper.class);

	Product toProduct(ProductEntity productEntity);

	List<Product> toProduct(List<ProductEntity> productEntity);

	ProductEntity toProductEntity(Product product);
}