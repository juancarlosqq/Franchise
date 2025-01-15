package co.com.nequi.franchise.infrastructure.persistence.adapter.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.com.nequi.franchise.domain.model.Branch;
import co.com.nequi.franchise.infrastructure.persistence.entity.BranchEntity;

@Mapper
public interface BranchAdapterMapper {

	BranchAdapterMapper INSTANCE = Mappers.getMapper(BranchAdapterMapper.class);

	Branch toBranch(BranchEntity branchEntity);

	List<Branch> toBranch(List<BranchEntity> branchEntity);

	BranchEntity toBranchEntity(Branch branch);

	List<BranchEntity> toBranchEntity(List<Branch> branchEntity);
}