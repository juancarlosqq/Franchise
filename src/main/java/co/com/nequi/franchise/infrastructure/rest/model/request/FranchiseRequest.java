package co.com.nequi.franchise.infrastructure.rest.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Schema(name = "FranchiseRequest")
@Getter
public class FranchiseRequest {

	@Schema(description = "Nombre de la franquicia")
	@NotBlank(message = "El nombre de la franquicia no es un valor válido")
	@Size(max = 100, message = "Máximo {max} caracteres")
	private String name;
}
