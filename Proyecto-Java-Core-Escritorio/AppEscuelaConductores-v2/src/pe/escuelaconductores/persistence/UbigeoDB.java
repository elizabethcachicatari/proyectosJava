package pe.escuelaconductores.persistence;

import pe.escuelaconductores.bean.Ubigeo;

import java.sql.SQLException;
import java.util.List;

public interface UbigeoDB {
    List<Ubigeo> listado()  throws SQLException;
    List<Ubigeo> obtenerDepartamentos() throws SQLException;
    List<Ubigeo> obtenerProvinciasPorDepartamento(String departamento)throws SQLException;
    List<Ubigeo> obtenerDistritosPorProvincia(String departamento, String provincia)throws SQLException;
    Long ObtenerIdUbigeo(String departamento, String provincia, String distrito) throws SQLException;
}
