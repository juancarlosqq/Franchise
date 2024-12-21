package co.com.nequi.franchise.infrastructure.rest.model.response;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "BranchOfficeResponse")
@Getter
@Setter
public class BranchResponse {

	@Schema(description = "Identificador único de la sucursal", example = "65fd7ad3-ce3d-49dd-75de-b18dd1d2e99e")
	private UUID uniqueId;

	@Schema(description = "Nombre de la sucursal")
	private String name;
}
