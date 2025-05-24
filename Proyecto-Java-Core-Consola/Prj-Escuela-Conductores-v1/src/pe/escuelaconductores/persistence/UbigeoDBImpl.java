package pe.escuelaconductores.persistence;

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

public class UbigeoDBImpl implements UbigeoDB {

    private final String SQL_SELECT = "SELECT a.IDUBIGEO, a.NOMDEPARTAMENTO, a.NOMPROVINCIA, a.NOMDISTRITO FROM UBIGEO a ";

    @Override
    public List<Ubigeo> listado() throws SQLException {
        DBUtil db = new DBUtil();
        User user = new User(USER, PASSWORD);

        try (Connection cn = db.conectar(user); ) {

            PreparedStatement st= cn.prepareStatement(SQL_SELECT);
            ResultSet rs= st.executeQuery();

            List<Ubigeo> ubigeos = new ArrayList<>();
            while (rs.next()){
                Ubigeo ubigeo = new Ubigeo();
                ubigeo.setIdUbigeo(rs.getLong("IDUBIGEO"));
                ubigeo.setNomDepartamento(rs.getString("NOMDEPARTAMENTO"));
                ubigeo.setNomProvincia(rs.getString("NOMPROVINCIA"));
                ubigeo.setNomDistrito(rs.getString("NOMDISTRITO"));
                ubigeos.add(ubigeo);
            }

            rs.close();
            st.close();
            cn.close();
            return ubigeos;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
