package co.com.nequi.franchise.infrastructure.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FRANCHISE")
public class FranchiseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "El campo identificador único de la franquicia no puede ser un valor nulo")
	@Column(name = "unique_id", nullable = false, unique = true)
	private UUID uniqueId;

	@NotBlank(message = "El campo nombre de la franquicia no es un valor válido")
	@Size(min = 5, max = 100, message = "El campo nombre de la franquicia debe tener entre {min} y {max} caracteres")
	@Column(name = "name", nullable = false)
	private String name;

	@PrePersist
	protected void onCreate() {
		this.uniqueId = UUID.randomUUID();
	}
}