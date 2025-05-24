package pe.escuelaconductores.persistence;
import pe.escuelaconductores.clases.EstadoEntidad;
import java.sql.SQLException;
import java.util.List;

public interface EstadoEntidadDB {
    List<EstadoEntidad> listado()  throws SQLException;
}
