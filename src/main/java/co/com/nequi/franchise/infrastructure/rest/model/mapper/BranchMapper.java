package co.com.nequi.franchise.infrastructure.rest.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import co.com.nequi.franchise.domain.model.Branch;
import co.com.nequi.franchise.infrastructure.rest.model.request.BranchRequest;
import co.com.nequi.franchise.infrastructure.rest.model.response.BranchResponse;

@Mapper
public interface BranchMapper {

	BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "uniqueId", ignore = true)
	@Mapping(target = "franchise", ignore = true)
	Branch toBranch(BranchRequest branchRequest);

	BranchResponse toBranchResponse(Branch branch);
}
