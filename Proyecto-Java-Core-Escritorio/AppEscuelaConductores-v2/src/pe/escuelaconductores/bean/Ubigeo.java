package pe.escuelaconductores.bean;

import java.time.LocalDate;

public class Ubigeo {

    private Long idUbigeo;
    private String codDepartamento;
    private String nomDepartamento;
    private String codProvincia;
    private String nomProvincia;
    private String codDistrito;
    private String nomDistrito;
    private String codUbigeo;
    private String estado;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String userCreate;
    private String userUpdate;

    public Ubigeo() {
    }

    public Ubigeo(Long idUbigeo, String nomDepartamento, String nomProvincia, String nomDistrito) {
        this.idUbigeo = idUbigeo;
        this.nomDepartamento = nomDepartamento;
        this.nomProvincia = nomProvincia;
        this.nomDistrito = nomDistrito;
    }

    public Ubigeo(Long idUbigeo, String codDepartamento, String nomDepartamento, String codProvincia, String nomProvincia, String codDistrito, String nomDistrito, String codUbigeo, String estado, LocalDate createDate, LocalDate updateDate, String userCreate, String userUpdate) {
        this.idUbigeo = idUbigeo;
        this.codDepartamento = codDepartamento;
        this.nomDepartamento = nomDepartamento;
        this.codProvincia = codProvincia;
        this.nomProvincia = nomProvincia;
        this.codDistrito = codDistrito;
        this.nomDistrito = nomDistrito;
        this.codUbigeo = codUbigeo;
        this.estado = estado;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.userCreate = userCreate;
        this.userUpdate = userUpdate;
    }


    public Long getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(Long idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public String getCodDepartamento() {
        return codDepartamento;
    }

    public void setCodDepartamento(String codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public String getNomDepartamento() {
        return nomDepartamento;
    }

    public void setNomDepartamento(String nomDepartamento) {
        this.nomDepartamento = nomDepartamento;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getNomProvincia() {
        return nomProvincia;
    }

    public void setNomProvincia(String nomProvincia) {
        this.nomProvincia = nomProvincia;
    }

    public String getCodDistrito() {
        return codDistrito;
    }

    public void setCodDistrito(String codDistrito) {
        this.codDistrito = codDistrito;
    }

    public String getNomDistrito() {
        return nomDistrito;
    }

    public void setNomDistrito(String nomDistrito) {
        this.nomDistrito = nomDistrito;
    }

    public String getCodUbigeo() {
        return codUbigeo;
    }

    public void setCodUbigeo(String codUbigeo) {
        this.codUbigeo = codUbigeo;
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
        return "Ubigeo{" + "idUbigeo=" + idUbigeo + ", nomDepartamento=" + nomDepartamento + ", nomProvincia=" + nomProvincia + ", nomDistrito=" + nomDistrito + '}';
    }

    
    
    
}
