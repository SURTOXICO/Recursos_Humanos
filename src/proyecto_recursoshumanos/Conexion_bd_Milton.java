package proyecto_recursoshumanos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Conexion_bd_Milton {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_recursoshumanos?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private Connection conexion;

    // Se conserva la firma sin throws para no romper los formularios existentes.
    public Connection conectar() {
        try {
            conexion = abrirConexion();
            return conexion;
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            return null;
        }
    }

    private Connection abrirConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver MySQL Connector/J.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    public void cerrarConexion() {
        try { if (conexion != null && !conexion.isClosed()) conexion.close(); }
        catch (SQLException e) { System.err.println("Error al cerrar conexión: " + e.getMessage()); }
    }

    private boolean ejecutarSP(String llamada, Object... parametros) throws SQLException {
        try (Connection cn = abrirConexion(); CallableStatement cs = cn.prepareCall(llamada)) {
            for (int i = 0; i < parametros.length; i++) cs.setObject(i + 1, parametros[i]);
            cs.execute();
            return true;
        }
    }

    private CachedRowSet consultarSP(String llamada, Object... parametros) throws SQLException {
        try (Connection cn = abrirConexion(); CallableStatement cs = cn.prepareCall(llamada)) {
            for (int i = 0; i < parametros.length; i++) cs.setObject(i + 1, parametros[i]);
            try (ResultSet rs = cs.executeQuery()) {
                CachedRowSet copia = RowSetProvider.newFactory().createCachedRowSet();
                copia.populate(rs);
                return copia;
            }
        }
    }


    // CAPACITACIONES: guardar recibe 5 datos; modificar inicia con id_capacitacion.
    public boolean guardarCapacitacion(Object... p) throws SQLException { return ejecutarSP("{CALL sp_capacitacion_insertar(?,?,?,?,?)}", p); }
    public boolean modificarCapacitacion(Object... p) throws SQLException { return ejecutarSP("{CALL sp_capacitacion_modificar(?,?,?,?,?,?)}", p); }
    public boolean eliminarCapacitacion(int id) throws SQLException { return ejecutarSP("{CALL sp_capacitacion_eliminar(?)}", id); }
    public CachedRowSet buscarCapacitacion(String filtro) throws SQLException { return consultarSP("{CALL sp_capacitacion_buscar(?)}", filtro); }
    public CachedRowSet listarCapacitaciones() throws SQLException { return consultarSP("{CALL sp_capacitacion_listar()}"); }

    // RELACIÓN EMPLEADO-CAPACITACIÓN: guardar recibe 5 datos; modificar inicia con el ID.
    public boolean guardarEmpleadoCapacitacion(Object... p) throws SQLException { return ejecutarSP("{CALL sp_empleado_capacitacion_insertar(?,?,?,?,?)}", p); }
    public boolean modificarEmpleadoCapacitacion(Object... p) throws SQLException { return ejecutarSP("{CALL sp_empleado_capacitacion_modificar(?,?,?,?,?,?)}", p); }
    public boolean eliminarEmpleadoCapacitacion(int id) throws SQLException { return ejecutarSP("{CALL sp_empleado_capacitacion_eliminar(?)}", id); }
    public CachedRowSet buscarEmpleadoCapacitacion(String filtro) throws SQLException { return consultarSP("{CALL sp_empleado_capacitacion_buscar(?)}", filtro); }
    public CachedRowSet listarEmpleadosCapacitaciones() throws SQLException { return consultarSP("{CALL sp_empleado_capacitacion_listar()}"); }
}