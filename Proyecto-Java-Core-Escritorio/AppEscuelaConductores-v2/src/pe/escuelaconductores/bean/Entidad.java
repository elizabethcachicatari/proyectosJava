package pe.escuelaconductores.bean;

import java.time.LocalDate;

public class Entidad {
    private Long idEntidad;
    private String ruc;
    private String nombre;
    private String direccion;
    private Long idUbigeo;
    private Long idTipoEntidad;
    private String correo;
    private String telefono;
    private Long idEstadoEntidad;
    private String estado;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String userCreate;
    private String userUpdate;


    // De La relación con las tablas TIPO_ENTIDAD y ESTADO_ENTIDAD
    private TipoEntidad tipoEntidad;
    private EstadoEntidad estadoEntidad;
    private Ubigeo ubigeo;


    public Entidad() {
    }

    public Entidad(Long idEntidad, String ruc, String nombre, String direccion, Long idUbigeo, Long idTipoEntidad, String correo, String telefono, Long idEstadoEntidad, String estado, LocalDate createDate, LocalDate updateDate, String userCreate, String userUpdate) {
        this.idEntidad = idEntidad;
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.idUbigeo = idUbigeo;
        this.idTipoEntidad = idTipoEntidad;
        this.correo = correo;
        this.telefono = telefono;
        this.idEstadoEntidad = idEstadoEntidad;
        this.estado = estado;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.userCreate = userCreate;
        this.userUpdate = userUpdate;
    }

    public Entidad(String ruc, String nombre, String direccion, String correo, String telefono, Long idUbigeo, Long idTipoEntidad, Long idEstadoEntidad) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.idUbigeo = idUbigeo;
        this.idTipoEntidad = idTipoEntidad;
        this.correo = correo;
        this.telefono = telefono;
        this.idEstadoEntidad = idEstadoEntidad;
    }

    public Long getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Long idEntidad) {
        this.idEntidad = idEntidad;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Long getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Long idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public Long getIdTipoEntidad() {
        return idTipoEntidad;
    }

    public void setIdTipoEntidad(Long idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getIdEstadoEntidad() {
        return idEstadoEntidad;
    }

    public void setIdEstadoEntidad(Long idEstadoEntidad) {
        this.idEstadoEntidad = idEstadoEntidad;
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
// Getters and Setters para las tablas relacionadas
    public Ubigeo getUbigeo() { return ubigeo;    }
    public TipoEntidad getTipoEntidad() { return tipoEntidad; }
    public EstadoEntidad getEstadoEntidad() { return estadoEntidad;  }

    public void setTipoEntidad(TipoEntidad tipoEntidad) { this.tipoEntidad = tipoEntidad; }
    public void setEstadoEntidad(EstadoEntidad estadoEntidad) { this.estadoEntidad = estadoEntidad; }
    public void setUbigeo(Ubigeo ubigeo) { this.ubigeo = ubigeo;   }

    @Override
    public String toString() {
        return "Entidad{" +
                "idEntidad=" + idEntidad +
                ", ruc='" + ruc + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", idUbigeo=" + idUbigeo +
                ", idTipoEntidad=" + idTipoEntidad +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", idEstadoEntidad=" + idEstadoEntidad +
                ", estado='" + estado + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", userCreate='" + userCreate + '\'' +
                ", userUpdate='" + userUpdate + '\'' +
                '}';
    }
}
