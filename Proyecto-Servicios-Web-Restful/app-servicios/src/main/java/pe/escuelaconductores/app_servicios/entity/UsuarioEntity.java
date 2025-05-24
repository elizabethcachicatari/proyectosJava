package pe.escuelaconductores.app_servicios.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO") // DB
@Entity(name = "UsuarioEntity") //Java
public class UsuarioEntity {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUsuario")
    @SequenceGenerator(sequenceName = "SEQ_USUARIO", allocationSize = 1, name = "seqUsuario")
    @Column(name = "USUARIO_ID",nullable = false, length = 4)
	private Long id; 
    
	@Column(name = "USUARIO",nullable = false, length = 60, unique = true)
    private String usuario;

	@Column(name = "CLAVE",nullable = false, length = 360, unique = true)
    private String clave;
		
	@Column(name = "ESTADO",nullable = false, length = 1)
    private String estado;
    

}