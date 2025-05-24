package pe.escuelaconductores.clases;

import java.time.LocalDate;

public class TipoEntidad {
    private Long idTipoEntidad;
    private String nombreTipoEntidad;
    private String estado;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String userCreate;
    private String userUpdate;

    public TipoEntidad() {
    }

    public TipoEntidad(Long idTipoEntidad, String nombreTipoEntidad, String estado, LocalDate createDate, LocalDate updateDate, String userCreate, String userUpdate) {
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
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
        return "TipoEntidad{" +
                "idTipoEntidad=" + idTipoEntidad +
                ", nombreTipoEntidad='" + nombreTipoEntidad + '\'' +
                ", estado='" + estado + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", userCreate='" + userCreate + '\'' +
                ", userUpdate='" + userUpdate + '\'' +
                '}';
    }
}
