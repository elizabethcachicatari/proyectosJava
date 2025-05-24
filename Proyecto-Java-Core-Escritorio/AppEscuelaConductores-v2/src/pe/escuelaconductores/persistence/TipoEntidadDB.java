package pe.escuelaconductores.persistence;
import pe.escuelaconductores.bean.TipoEntidad;
import java.sql.SQLException;
import java.util.List;

public interface TipoEntidadDB {
    List<TipoEntidad> listado()  throws SQLException;
}
