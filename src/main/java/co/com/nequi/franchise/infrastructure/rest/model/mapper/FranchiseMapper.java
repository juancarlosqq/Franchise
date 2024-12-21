package co.com.nequi.franchise.infrastructure.rest.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.com.nequi.franchise.domain.model.Franchise;
import co.com.nequi.franchise.infrastructure.rest.model.request.FranchiseRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.FranchiseResponse;

@Mapper
public interface FranchiseMapper {

	FranchiseMapper INSTANCE = Mappers.getMapper(FranchiseMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "uniqueId", ignore = true)
	Franchise toFranchise(FranchiseRequest franchiseRequest);

	FranchiseResponse toFranchiseResponse(Franchise franchise);
}
