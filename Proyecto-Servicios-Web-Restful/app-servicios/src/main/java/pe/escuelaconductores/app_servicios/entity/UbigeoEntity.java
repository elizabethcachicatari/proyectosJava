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

//Para obtener lo getters and Setters en automatico, se reduce el codigo
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UBIGEO") // DB
@Entity(name = "UbigeoEntity") //Java

public class UbigeoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUbigeo")
    @SequenceGenerator(sequenceName = "SEQ_UBIGEO", allocationSize = 1, name = "seqUbigeo")
	
	@Column(name="IDUBIGEO", nullable=false)
    private Long idUbigeo;
	
	@Column(name="CODDEPARTAMENTO", nullable=false, length=2)
    private String codDepartamento;
	
	@Column(name="NOMDEPARTAMENTO", nullable=false, length=100)
    private String nomDepartamento;
	
	@Column(name="CODPROVINCIA", nullable=false, length=2)
    private String codProvincia;
	
	@Column(name="NOMPROVINCIA", nullable=false, length=100)
    private String nomProvincia;
	
	@Column(name="CODDISTRITO", nullable=false, length=2)
    private String codDistrito;
	
	@Column(name="NOMDISTRITO", nullable=false, length=100)
    private String nomDistrito;
	
	@Column(name="CODUBIGEO", nullable=false, length=6)
    private String codUbigeo;
	
	@Column(name="ESTADO", nullable=false, length=1)
    private String estado;
	
	@Column(name="CREATEDATE", nullable=false)
    private LocalDateTime createDate;
	
	@Column(name="UPDATEDATE", nullable=true)
    private LocalDateTime updateDate;
	
	@Column(name="USERCREATE", nullable=true, length=50)
    private String userCreate;
	
	@Column(name="USERUPDATE", nullable=true, length=50)
    private String userUpdate;

    
}
