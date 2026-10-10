package proyecto_recursoshumanos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Conexion_bd_Luis {
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

    public boolean validarLogin(String codigo, String password, String cargo) {
        String hashIngresado = generarSHA256(password);
        try (Connection cn = abrirConexion(); CallableStatement cs = cn.prepareCall("{CALL sp_usuario_validar_login(?,?)}")) {
            cs.setString(1, codigo);
            cs.setString(2, cargo);
            try (ResultSet rs = cs.executeQuery()) {
                return rs.next() && hashIngresado.equals(rs.getString("contrasena"));
            }
        } catch (SQLException e) {
            System.err.println("Error en validarLogin: " + e.getMessage());
            return false;
        }
    }

    // Devuelve ResultSet para conservar compatibilidad con Frm_LoginAcceso.
    // El llamador debe cerrar el ResultSet/Statement y después cerrarConexion().
    public ResultSet ComboBox_ListarRoles() throws SQLException {
        Connection cn = abrirConexion();
        conexion = cn;
        CallableStatement cs = cn.prepareCall("{CALL sp_cargo_listar()}");
        return cs.executeQuery();
    }

    private String generarSHA256(String texto) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(texto.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder resultado = new StringBuilder();
            for (byte b : hash) resultado.append(String.format("%02x", b));
            return resultado.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new IllegalStateException("No se pudo generar SHA-256", e);
        }
    }

    // EMPLEADOS: orden de parámetros igual a sp_empleado_*. Para modificar, el ID va primero.
    public boolean guardarEmpleado(Object... p) throws SQLException { return ejecutarSP("{CALL sp_empleado_insertar(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", p); }
    public boolean modificarEmpleado(Object... p) throws SQLException { return ejecutarSP("{CALL sp_empleado_modificar(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", p); }
    public boolean desactivarEmpleado(int id) throws SQLException { return ejecutarSP("{CALL sp_empleado_desactivar(?)}", id); }
    public CachedRowSet buscarEmpleado(String filtro) throws SQLException { return consultarSP("{CALL sp_empleado_buscar(?)}", filtro); }
    public CachedRowSet listarEmpleados() throws SQLException { return consultarSP("{CALL sp_empleado_listar()}"); }

    // CONTRATOS: guardar recibe 14 datos; modificar recibe primero id_contrato y luego esos 14.
    public boolean guardarContrato(Object... p) throws SQLException { return ejecutarSP("{CALL sp_contrato_insertar(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", p); }
    public boolean modificarContrato(Object... p) throws SQLException { return ejecutarSP("{CALL sp_contrato_modificar(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", p); }
    public boolean desactivarContrato(int id) throws SQLException { return ejecutarSP("{CALL sp_contrato_desactivar(?)}", id); }
    public CachedRowSet buscarContrato(String filtro) throws SQLException { return consultarSP("{CALL sp_contrato_buscar(?)}", filtro); }
    public CachedRowSet listarContratos() throws SQLException { return consultarSP("{CALL sp_contrato_listar()}"); }

    // ASISTENCIAS: guardar recibe 7 datos; modificar recibe id_asistencia y luego esos 7.
    public boolean guardarAsistencia(Object... p) throws SQLException { return ejecutarSP("{CALL sp_asistencia_insertar(?,?,?,?,?,?,?)}", p); }
    public boolean modificarAsistencia(Object... p) throws SQLException { return ejecutarSP("{CALL sp_asistencia_modificar(?,?,?,?,?,?,?,?)}", p); }
    public boolean eliminarAsistencia(int id) throws SQLException { return ejecutarSP("{CALL sp_asistencia_eliminar(?)}", id); }
    public CachedRowSet buscarAsistencia(String filtro) throws SQLException { return consultarSP("{CALL sp_asistencia_buscar(?)}", filtro); }
    public CachedRowSet listarAsistencias() throws SQLException { return consultarSP("{CALL sp_asistencia_listar()}"); }

}
