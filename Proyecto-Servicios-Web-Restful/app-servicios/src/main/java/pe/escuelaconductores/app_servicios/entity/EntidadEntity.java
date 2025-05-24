package pe.escuelaconductores.app_servicios.entity;

import java.time.LocalDateTime;

import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Para obtener lo getters and Setters en automatico, se reduce el codigo


@NamedStoredProcedureQuery(
	    name = "Entidad.insertarEntidad",
	    procedureName = "PKG_ENTIDAD.INSERTAR_ENTIDAD",
	    parameters = {
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_ruc", type = String.class),
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_nombre", type = String.class),
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_direccion", type = String.class),
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_correo", type = String.class),
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_telefono", type = String.class),
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_idubigeo", type = String.class),
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_idtipoentidad", type = Long.class),
	        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_idestadoentidad", type = Long.class)
	    }
	)

 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ENTIDAD") // DB
@Entity(name = "EntidadEntity") //Java

public class EntidadEntity {
	
	@Id // PK
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEntidad")
    @SequenceGenerator(sequenceName = "ENTIDAD_SEQ", allocationSize = 1, name = "seqEntidad")
	
    @Column(name = "IDENTIDAD", nullable = false, length = 4)	
    private Long idEntidad;
	
	@Column(name = "RUC", nullable = false, length = 11)		
    private String ruc;
	
	@Column(name = "NOMBRE", nullable = false, length = 300)	
    private String nombre;
	
	@Column(name = "DIRECCION", nullable = false, length = 300)	
    private String direccion;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "IDUBIGEO", nullable = false)	
	private UbigeoEntity idUbigeo;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "IDTIPOENTIDAD", nullable = false)	
	private TipoEntidadEntity idTipoEntidad;
	
	@Column(name = "CORREO", nullable = true, length = 100)	
    private String correo;
	
	@Column(name = "TELEFONO", nullable = true, length = 20)	
    private String telefono;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "IDESTADOENTIDAD", nullable = false)	
	private EstadoEntidadEntity idEstadoEntidad;
	
	@Column(name = "ESTADO", nullable = false, length = 1)	
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
