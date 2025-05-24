package pe.escuelaconductores.persistence;

import pe.escuelaconductores.clases.EstadoEntidad;
import pe.escuelaconductores.clases.TipoEntidad;
import pe.escuelaconductores.clases.User;
import pe.escuelaconductores.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static pe.escuelaconductores.constants.GlobalConstants.PASSWORD;
import static pe.escuelaconductores.constants.GlobalConstants.USER;

public class EstadoEntidadDBImpl implements EstadoEntidadDB{

    private final String SQL_SELECT = "SELECT a.IDESTADOENTIDAD, a.NOMBREESTADOENTIDAD FROM ESTADO_ENTIDAD a";

    @Override
    public List<EstadoEntidad> listado() throws SQLException {

        DBUtil db = new DBUtil();
        User user = new User(USER, PASSWORD);

        try (Connection cn = db.conectar(user); ) {

            PreparedStatement st= cn.prepareStatement(SQL_SELECT);
            ResultSet rs= st.executeQuery();

            List<EstadoEntidad> estados = new ArrayList<>();

            while (rs.next()){
                EstadoEntidad estado = new EstadoEntidad();
                estado.setIdEstadoEntidad(rs.getLong("IDESTADOENTIDAD"));
                estado.setNombreEstadoEntidad(rs.getString("NOMBREESTADOENTIDAD"));
                estados.add(estado);
            }
            rs.close();
            st.close();
            cn.close();
            return estados;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
