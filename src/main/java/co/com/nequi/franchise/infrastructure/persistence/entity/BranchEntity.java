package co.com.nequi.franchise.infrastructure.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "BRANCH")
public class BranchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "El campo identificador único de la sucursal no puede ser un valor nulo")
	@Column(name = "unique_id", nullable = false, unique = true)
	private UUID uniqueId;

	@NotBlank(message = "El campo nombre de la sucursal no es un valor válido")
	@Size(min = 5, max = 100, message = "El campo nombre de la sucursal debe tener entre {min} y {max} caracteres")
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull(message = "El campo sucursal no es un valor válido")
	@ManyToOne(optional = false)
	@JoinColumn(name = "franchise_id", referencedColumnName = "id", nullable = false)
	private FranchiseEntity franchise;

	@PrePersist
	protected void onCreate() {
		this.uniqueId = UUID.randomUUID();
	}
}