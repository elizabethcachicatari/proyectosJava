package pe.escuelaconductores.persistence;

import pe.escuelaconductores.clases.Ubigeo;

import java.sql.SQLException;
import java.util.List;

public interface UbigeoDB {
    List<Ubigeo> listado()  throws SQLException;
}
