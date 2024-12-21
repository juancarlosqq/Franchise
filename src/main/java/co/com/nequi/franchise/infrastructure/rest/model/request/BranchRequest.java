package co.com.nequi.franchise.infrastructure.rest.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Schema(name = "BranchOfficeRequest")
@Getter
public class BranchRequest {

	@Schema(description = "Nombre de la sucursal")
	@NotBlank(message = "El nombre de la sucursal no es un valor válido")
	@Size(max = 100, message = "Máximo {max} caracteres")
	private String name;
}
