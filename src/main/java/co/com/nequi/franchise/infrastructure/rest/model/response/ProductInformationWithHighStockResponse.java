package co.com.nequi.franchise.infrastructure.rest.model.response;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "ProductResponse")
@Getter
@Setter
public class ProductInformationWithHighStockResponse {

	@Schema(description = "Identificador único del producto", example = "65fd7ad3-ce3d-49dd-75de-b18dd1d2e99e")
	private UUID uniqueId;

	@Schema(description = "Información de la sucursal a la que pertenece")
	private BranchResponse branch;

	@Schema(description = "Nombre del producto")
	private String name;

	@Schema(description = "Stock del producto")
	private Long stock;
}
