package pe.escuelaconductores.app_servicios.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Table(name = "ESTADO_ENTIDAD") // DB
@Entity(name = "EstadoEntidadEntity") //Java

public class EstadoEntidadEntity {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqEstadoEntidad")
    @SequenceGenerator(sequenceName = "SEQ_ESTADOENTIDAD", allocationSize = 1, name = "seqEstadoEntidad")
	
    @Column(name = "IDESTADOENTIDAD", nullable = false, length = 4)	
    private Long idEstadoEntidad;
	
	@Column(name = "NOMBREESTADOENTIDAD", nullable = false, length = 100)	
    private String nombreEstadoEntidad;
	
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
	
 
    public EstadoEntidadEntity() {
    }
    

    public EstadoEntidadEntity(Long idEstadoEntidad, String nombreEstadoEntidad, String estado,
			LocalDateTime createDate, LocalDateTime updateDate, String userCreate, String userUpdate) {
		super();
		this.idEstadoEntidad = idEstadoEntidad;
		this.nombreEstadoEntidad = nombreEstadoEntidad;
		this.estado = estado;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.userCreate = userCreate;
		this.userUpdate = userUpdate;
	}


	public Long getIdEstadoEntidad() {
        return idEstadoEntidad;
    }

    public void setIdEstadoEntidad(Long idEstadoEntidad) {
        this.idEstadoEntidad = idEstadoEntidad;
    }

    public String getNombreEstadoEntidad() {
        return nombreEstadoEntidad;
    }

    public void setNombreEstadoEntidad(String nombreEstadoEntidad) {
        this.nombreEstadoEntidad = nombreEstadoEntidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    @Override
    public String toString() {
        return nombreEstadoEntidad;
    }
}
