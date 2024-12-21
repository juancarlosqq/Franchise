package co.com.nequi.franchise.infrastructure.persistence.adapter.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.com.nequi.franchise.domain.model.Franchise;
import co.com.nequi.franchise.infrastructure.persistence.entity.FranchiseEntity;

@Mapper
public interface FranchiseAdapterMapper {

	FranchiseAdapterMapper INSTANCE = Mappers.getMapper(FranchiseAdapterMapper.class);

	Franchise toFranchise(FranchiseEntity franchiseEntity);

	FranchiseEntity toFranchiseEntity(Franchise franchise);
}