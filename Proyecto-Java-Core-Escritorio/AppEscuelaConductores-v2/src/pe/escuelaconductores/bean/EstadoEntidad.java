package pe.escuelaconductores.bean;

import java.time.LocalDate;

public class EstadoEntidad {

    private Long idEstadoEntidad;
    private String nombreEstadoEntidad;
    private String estado;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String userCreate;
    private String userUpdate;

    public EstadoEntidad() {
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
        return nombreEstadoEntidad;
    }
}
