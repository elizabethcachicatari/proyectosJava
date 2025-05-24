package pe.escuelaconductores.persistence;

import pe.escuelaconductores.bean.Ubigeo;
import pe.escuelaconductores.bean.User;
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
    private final String SQL_SELECT_DEPARTAMENTOS = "SELECT DISTINCT CODDEPARTAMENTO, NOMDEPARTAMENTO FROM UBIGEO WHERE estado = '1' ORDER BY NOMDEPARTAMENTO";
    private final String SQL_SELECT_PROVINCIAS = "SELECT DISTINCT a.CODPROVINCIA, a.NOMPROVINCIA FROM UBIGEO a WHERE a.NOMDEPARTAMENTO = ? AND a.ESTADO = '1' ORDER BY NOMPROVINCIA";
    private final String SQL_SELECT_DISTRITOS = "SELECT DISTINCT a.IDUBIGEO, a.CODDISTRITO, a.NOMDISTRITO FROM UBIGEO a WHERE a.NOMDEPARTAMENTO = ?  AND a.NOMPROVINCIA = ? AND a.ESTADO = '1' ORDER BY NOMDISTRITO";
    private final String SQL_SELECT_ID_UBIGEO = "SELECT IDUBIGEO FROM UBIGEO WHERE NOMDEPARTAMENTO = ? AND NOMPROVINCIA = ? AND NOMDISTRITO = ?";
    
    private final DBUtil db = new DBUtil();
    private final User user = new User(USER, PASSWORD);
   
    @Override
    public List<Ubigeo> listado() throws SQLException {

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

    @Override
    public Long ObtenerIdUbigeo(String departamento, String provincia, String distrito) throws SQLException {
        
        try (Connection cn = db.conectar(user); 
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_ID_UBIGEO);) {
            
            st.setString(1, departamento);
            st.setString(2, provincia);
            st.setString(3, distrito);
            
            try(ResultSet rs = st.executeQuery()){

                if (rs.next()) {
                    return rs.getLong("IDUBIGEO");
                } else {
                        return null; // o lanzar una excepción si no se encuentra
                }
            } catch (SQLException e) {
                    e.printStackTrace();
                    throw new SQLException("Error al buscar Id Ubigeo", e);
                }
        }  
    }
    
    @Override
    public List<Ubigeo> obtenerDepartamentos() throws SQLException {

        List<Ubigeo> departamentos = new ArrayList<>();
        try (Connection cn = db.conectar(user); 
             PreparedStatement pst = cn.prepareStatement(SQL_SELECT_DEPARTAMENTOS); 
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Ubigeo ubigeo = new Ubigeo();
                ubigeo.setCodDepartamento(rs.getString("coddepartamento"));
                ubigeo.setNomDepartamento(rs.getString("nomdepartamento"));
                departamentos.add(ubigeo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;   
        }
        return departamentos;
    }
    
    @Override
    public List<Ubigeo> obtenerProvinciasPorDepartamento(String departamento) throws SQLException {
        if (departamento == null || departamento.isEmpty()) {
            throw new IllegalArgumentException("El código de departamento no puede ser nulo o vacío");
        }

        List<Ubigeo> provincias = new ArrayList<>();
        try (Connection cn = db.conectar(user);
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_PROVINCIAS)) {
            st.setString(1, departamento);
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Ubigeo ubigeo = new Ubigeo();
                    ubigeo.setCodProvincia(rs.getString("CODPROVINCIA"));
                    ubigeo.setNomProvincia(rs.getString("NOMPROVINCIA"));
                    provincias.add(ubigeo);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener provincias por departamento: " + departamento, e);
        }
        return provincias;
    }

    @Override
    public List<Ubigeo> obtenerDistritosPorProvincia(String departamento, String provincia) throws SQLException {
   
        List<Ubigeo> distritos = new ArrayList<>();

        try (Connection cn = db.conectar(user);
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_DISTRITOS)) {
         
            st.setString(1, departamento);
            st.setString(2, provincia);
            
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Ubigeo ubigeo = new Ubigeo();
                ubigeo.setIdUbigeo(rs.getLong("IDUBIGEO"));
                ubigeo.setCodDistrito(rs.getString("CODDISTRITO"));
                ubigeo.setNomDistrito(rs.getString("NOMDISTRITO"));
                distritos.add(ubigeo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al obtener distritos por provincia", e);
        }

        return distritos;
    }

}
