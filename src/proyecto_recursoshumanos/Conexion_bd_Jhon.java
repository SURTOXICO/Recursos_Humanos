package proyecto_recursoshumanos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Conexion_bd_Jhon {
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


    // PERMISOS/LICENCIAS: guardar recibe 6 parámetros; modificar inicia con el ID.
    public boolean guardarPermisoLicencia(Object... p) throws SQLException { return ejecutarSP("{CALL sp_permiso_licencia_insertar(?,?,?,?,?,?)}", p); }
    public boolean modificarPermisoLicencia(Object... p) throws SQLException { return ejecutarSP("{CALL sp_permiso_licencia_modificar(?,?,?,?,?,?,?)}", p); }
    public boolean eliminarPermisoLicencia(int id) throws SQLException { return ejecutarSP("{CALL sp_permiso_licencia_eliminar(?)}", id); }
    public CachedRowSet buscarPermisoLicencia(String filtro) throws SQLException { return consultarSP("{CALL sp_permiso_licencia_buscar(?)}", filtro); }
    public CachedRowSet listarPermisosLicencias() throws SQLException { return consultarSP("{CALL sp_permiso_licencia_listar()}"); }

    // DEPARTAMENTO ACADÉMICO: estado usa 1=activo y 0=inactivo.
    public boolean guardarDepartamentoAcademico(Object... p) throws SQLException { return ejecutarSP("{CALL sp_departamento_academico_insertar(?,?,?,?)}", p); }
    public boolean modificarDepartamentoAcademico(Object... p) throws SQLException { return ejecutarSP("{CALL sp_departamento_academico_modificar(?,?,?,?,?)}", p); }
    public boolean desactivarDepartamentoAcademico(int id) throws SQLException { return ejecutarSP("{CALL sp_departamento_academico_desactivar(?)}", id); }
    public CachedRowSet buscarDepartamentoAcademico(String filtro) throws SQLException { return consultarSP("{CALL sp_departamento_academico_buscar(?)}", filtro); }
    public CachedRowSet listarDepartamentosAcademicos() throws SQLException { return consultarSP("{CALL sp_departamento_academico_listar()}"); }
}
