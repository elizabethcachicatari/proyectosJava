package pe.escuelaconductores.app_servicios.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "TIPO_ENTIDAD") // DB
@Entity(name = "TipoEntidadEntity") //Java

public class TipoEntidadEntity {
	
	@Id // PK
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTipoEntidad")
    @SequenceGenerator(sequenceName = "SEQ_TIPOENTIDAD", allocationSize = 1, name = "seqTipoEntidad")
	
    @Column(name = "IDTIPOENTIDAD",nullable = false, length = 4)
	private Long idTipoEntidad;
	
	@Column(name = "NOMBRETIPOENTIDAD",nullable = false, length = 200)
    private String nombreTipoEntidad;
	
	@Column(name = "ESTADO",nullable = false, length = 1)
    private String estado;
		 
	@Column(name="CREATEDATE", nullable=false)
    private LocalDateTime createDate;
	
	@Column(name="UPDATEDATE", nullable=true)
    private LocalDateTime updateDate;
	
	@Column(name="USERCREATE", nullable=true, length=50)
    private String userCreate;
	
	@Column(name="USERUPDATE", nullable=true, length=50)
    private String userUpdate;
	
    public TipoEntidadEntity() {
    }

    public TipoEntidadEntity(Long idTipoEntidad, String nombreTipoEntidad, String estado) {
        this.idTipoEntidad = idTipoEntidad;
        this.nombreTipoEntidad = nombreTipoEntidad;
        this.estado = estado;
    }
    
    public TipoEntidadEntity(Long idTipoEntidad, String nombreTipoEntidad, String estado, LocalDateTime createDate, LocalDateTime updateDate, String userCreate, String userUpdate) {
        this.idTipoEntidad = idTipoEntidad;
        this.nombreTipoEntidad = nombreTipoEntidad;
        this.estado = estado;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.userCreate = userCreate;
        this.userUpdate = userUpdate;
    }

    public Long getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public void setIdTipoEntidad(Long idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    public String getNombreTipoEntidad() {
        return nombreTipoEntidad;
    }

    public void setNombreTipoEntidad(String nombreTipoEntidad) {
        this.nombreTipoEntidad = nombreTipoEntidad;
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
        return this.nombreTipoEntidad;
    }


}
