package pe.escuelaconductores.persistence;

import pe.escuelaconductores.clases.TipoEntidad;
import pe.escuelaconductores.clases.Ubigeo;
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

public class TipoEntidadDBImpl implements TipoEntidadDB{

    private final String SQL_SELECT = "SELECT a.IDTIPOENTIDAD, a.NOMBRETIPOENTIDAD FROM TIPO_ENTIDAD a";

     @Override
     public List<TipoEntidad> listado() throws SQLException {
            DBUtil db = new DBUtil();
            User user = new User(USER, PASSWORD);

            try (Connection cn = db.conectar(user); ) {

                PreparedStatement st= cn.prepareStatement(SQL_SELECT);
                ResultSet rs= st.executeQuery();

                List<TipoEntidad> tipos = new ArrayList<>();
                while (rs.next()){
                    TipoEntidad tipo = new TipoEntidad();
                    tipo.setIdTipoEntidad(rs.getLong("IDTIPOENTIDAD"));
                    tipo.setNombreTipoEntidad(rs.getString("NOMBRETIPOENTIDAD"));
                    tipos.add(tipo);
                }

                rs.close();
                st.close();
                cn.close();
                return tipos;
            }
            catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
     }

}
