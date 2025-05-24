package pe.escuelaconductores.persistence;

import pe.escuelaconductores.bean.*;
import pe.escuelaconductores.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static pe.escuelaconductores.constants.GlobalConstants.PASSWORD;
import static pe.escuelaconductores.constants.GlobalConstants.USER;

public class EntidadDBImpl implements EntidadDB{

    private final String SQL_SELECT="SELECT e.identidad, e.ruc, e.nombre, e.direccion, e.correo, e.telefono, u.nomdepartamento, u.nomprovincia, u.nomdistrito, te.nombretipoentidad, ee.nombreestadoentidad FROM entidad e " +
    "INNER JOIN ubigeo u ON e.idubigeo = u.idubigeo " +
    "INNER JOIN tipo_entidad te ON e.idtipoentidad = te.idtipoentidad "+
    "INNER JOIN estado_entidad ee ON e.idestadoentidad = ee.idestadoentidad";

    private final String SQL_INSERT= "INSERT INTO ENTIDAD (IDENTIDAD, RUC, NOMBRE, DIRECCION, CORREO, TELEFONO, IDUBIGEO, IDTIPOENTIDAD, IDESTADOENTIDAD, USERCREATE) \n" + "VALUES (ENTIDAD_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private final String SQL_INSERT_SP = "{call PKG_ENTIDAD.INSERTAR_ENTIDAD(?, ?, ?, ?, ?, ?, ?, ?)}";

    private final String SQL_SELECT_BUSCAR_USP="{call PKG_ENTIDAD.BUSCAR_ENTIDAD_UBIGEO(?,?,?,?)}";

    private final String SQL_BUSCAR_NOMBRE_SP = "{call PKG_ENTIDAD.BUSCAR_ENTIDAD_NOMBRE(?, ?)}";

    @Override
    public List<Entidad> list() throws SQLException {

        DBUtil db= new DBUtil();
        User user= new User(USER, PASSWORD);
        try (Connection cn= db.conectar(user);) {
            PreparedStatement st=  cn.prepareStatement(SQL_SELECT);
            ResultSet rs= st.executeQuery();

            //System.out.println("Consulta ejecutada. ResultSet vacío: " + !rs.isBeforeFirst());

            List<Entidad> entidades= new ArrayList<>();
            while (rs.next()){

                Entidad entidad = new Entidad();
                entidad.setIdEntidad(rs.getLong("IDENTIDAD"));
                entidad.setRuc(rs.getString("RUC"));
                entidad.setNombre(rs.getString("NOMBRE"));
                entidad.setDireccion(rs.getString("DIRECCION"));
                entidad.setCorreo(rs.getString("CORREO"));
                entidad.setTelefono(rs.getString("TELEFONO"));

                // Crear y asignar ubigeo (desde la tabla ubigeo)

                Ubigeo ubigeo = new Ubigeo();
                ubigeo.setNomDepartamento(rs.getString("NOMDEPARTAMENTO"));
                ubigeo.setNomProvincia(rs.getString("NOMPROVINCIA"));
                ubigeo.setNomDistrito(rs.getString("NOMDISTRITO"));
                entidad.setUbigeo(ubigeo);

                // Crear y asignar el tipo de entidad
                TipoEntidad tipo = new TipoEntidad();
                tipo.setNombreTipoEntidad(rs.getString("NOMBRETIPOENTIDAD"));
                entidad.setTipoEntidad(tipo);

                // Crear y asignar el estado de entidad
                EstadoEntidad estado = new EstadoEntidad();
                estado.setNombreEstadoEntidad(rs.getString("NOMBREESTADOENTIDAD"));
                entidad.setEstadoEntidad(estado);

                entidades.add(entidad);
            }
            rs.close();
            st.close();
            cn.close();
            return entidades;
        } catch ( SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean insertar(Entidad entidad) throws SQLException {
        DBUtil db = new DBUtil();
        User user = new User(USER, PASSWORD);

        try ( Connection cn = db.conectar(user); ) {

            PreparedStatement ps= cn.prepareStatement(SQL_INSERT);
            ps.setString(1, entidad.getRuc());
            ps.setString(2, entidad.getNombre());
            ps.setString(3, entidad.getDireccion());
            ps.setString(4, entidad.getCorreo());
            ps.setString(5, entidad.getTelefono());
            ps.setLong(6, entidad.getIdUbigeo());
            ps.setLong(7, entidad.getIdTipoEntidad());
            ps.setLong(8, entidad.getIdEstadoEntidad());
            ps.setString(9, entidad.getUserCreate());
            ps.executeUpdate();

            ps.close();
            cn.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public Boolean insertar_SP(Entidad entidad) throws SQLException {
        DBUtil db = new DBUtil();
        User user = new User(USER, PASSWORD);

        try ( Connection cn = db.conectar(user); ) {

            CallableStatement cs = cn.prepareCall(SQL_INSERT_SP);
            cs.setString(1, entidad.getRuc());
            cs.setString(2, entidad.getNombre());
            cs.setString(3, entidad.getDireccion());
            cs.setString(4, entidad.getCorreo());
            cs.setString(5, entidad.getTelefono());
            cs.setLong(6, entidad.getIdUbigeo());
            cs.setLong(7, entidad.getIdTipoEntidad());
            cs.setLong(8, entidad.getIdEstadoEntidad());
            cs.executeUpdate();

            cs.close();
            cn.close();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Entidad> buscarEntidadPorUbigeo(String departamento, String provincia, String distrito ) throws SQLException {

        DBUtil db= new DBUtil();
        User user= new User(USER, PASSWORD);
        try ( Connection cn= db.conectar(user);) {

         /*   CallableStatement cs=  cn.prepareCall(SQL_SELECT_BUSCAR_USP);
            cs.registerOutParameter (1, oracle.jdbc.OracleTypes.CURSOR);
            cs.setString(2,departamento);
            cs.setString(3,provincia);
            cs.setString(4,distrito);
            cs.executeQuery();
            ResultSet rs=(ResultSet)cs.getObject (1);*/

            CallableStatement cs = cn.prepareCall(SQL_SELECT_BUSCAR_USP);
            cs.setString(1, departamento);
            cs.setString(2, provincia);
            cs.setString(3, distrito);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = (ResultSet) cs.getObject(4);

            List<Entidad> entidades= new ArrayList<>();

            while (rs.next()){
                Entidad entidad= new Entidad();
                entidad.setIdTipoEntidad(rs.getLong("IDENTIDAD"));
                entidad.setRuc(rs.getString("RUC"));
                entidad.setNombre(rs.getString("NOMBRE"));
                entidad.setDireccion(rs.getString("DIRECCION"));
                entidad.setCorreo(rs.getString("CORREO"));
                entidad.setTelefono(rs.getString("TELEFONO"));
                // Ubigeo
                Ubigeo ubigeo = new Ubigeo();
                ubigeo.setNomDepartamento(rs.getString("NOMDEPARTAMENTO"));
                ubigeo.setNomProvincia(rs.getString("NOMPROVINCIA"));
                ubigeo.setNomDistrito(rs.getString("NOMDISTRITO"));
                entidad.setUbigeo(ubigeo);

                // Tipo de entidad
                TipoEntidad tipo = new TipoEntidad();
                tipo.setNombreTipoEntidad(rs.getString("NOMBRETIPOENTIDAD"));
                entidad.setTipoEntidad(tipo);

                // Estado entidad
                EstadoEntidad estado = new EstadoEntidad();
                estado.setNombreEstadoEntidad(rs.getString("NOMBREESTADOENTIDAD"));
                entidad.setEstadoEntidad(estado);

                entidades.add(entidad);
            }

            rs.close();
            cs.close();
            cn.close();
            return entidades;

        } catch ( SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Entidad> buscarEntidadPorNombre(String nombre) throws SQLException {
        DBUtil db = new DBUtil();
        User user = new User(USER, PASSWORD);

        try (Connection cn = db.conectar(user)) {
            CallableStatement cs = cn.prepareCall(SQL_BUSCAR_NOMBRE_SP);
            cs.setString(1, nombre);
            cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(2);
            List<Entidad> entidades = new ArrayList<>();

            while (rs.next()) {
                Entidad entidad = new Entidad();
                entidad.setIdEntidad(rs.getLong("IDENTIDAD"));
                entidad.setRuc(rs.getString("RUC"));
                entidad.setNombre(rs.getString("NOMBRE"));
                entidad.setDireccion(rs.getString("DIRECCION"));
                entidad.setCorreo(rs.getString("CORREO"));
                entidad.setTelefono(rs.getString("TELEFONO"));

                Ubigeo ubigeo = new Ubigeo();
                ubigeo.setNomDepartamento(rs.getString("NOMDEPARTAMENTO"));
                ubigeo.setNomProvincia(rs.getString("NOMPROVINCIA"));
                ubigeo.setNomDistrito(rs.getString("NOMDISTRITO"));
                entidad.setUbigeo(ubigeo);

                TipoEntidad tipo = new TipoEntidad();
                tipo.setNombreTipoEntidad(rs.getString("NOMBRETIPOENTIDAD"));
                entidad.setTipoEntidad(tipo);

                EstadoEntidad estado = new EstadoEntidad();
                estado.setNombreEstadoEntidad(rs.getString("NOMBREESTADOENTIDAD"));
                entidad.setEstadoEntidad(estado);

                entidades.add(entidad);
            }

            rs.close();
            cs.close();
            return entidades;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
