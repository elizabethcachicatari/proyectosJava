package pe.escuelaconductores.persistence;
import pe.escuelaconductores.bean.EstadoEntidad;
import java.sql.SQLException;
import java.util.List;

public interface EstadoEntidadDB {
    List<EstadoEntidad> listado()  throws SQLException;
}
