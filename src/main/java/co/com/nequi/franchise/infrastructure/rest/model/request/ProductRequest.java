package co.com.nequi.franchise.infrastructure.rest.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Schema(name = "ProductRequest")
@Getter
public class ProductRequest {

	@Schema(description = "Nombre del producto")
	@NotBlank(message = "El nombre del producto no es un valor válido")
	@Size(max = 100, message = "Máximo {max} caracteres")
	private String name;

	@Schema(description = "Stock del producto")
	@NotNull(message = "La cantidad de productos no puede ser nula")
	private Long stock;
}
