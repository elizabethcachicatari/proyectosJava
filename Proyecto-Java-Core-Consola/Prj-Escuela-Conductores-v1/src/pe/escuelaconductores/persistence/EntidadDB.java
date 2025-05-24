package pe.escuelaconductores.persistence;

import pe.escuelaconductores.clases.Entidad;

import java.sql.SQLException;
import java.util.List;

public interface EntidadDB {

    List<Entidad> list() throws SQLException;
    Boolean insertar(Entidad entidad) throws SQLException;
    Boolean insertar_SP(Entidad entidad) throws SQLException;
    List<Entidad> buscarEntidadPorUbigeo(String departamento, String provincia, String distrito)throws SQLException;
    List<Entidad> buscarEntidadPorNombre(String nombre)throws SQLException;
}