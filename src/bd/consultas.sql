-- ============================================================
-- CONSULTAS DE PRUEBA - proyecto_recursoshumanos
-- Ejecutar después de esquema_catalogos.sql, esquema_complementario.sql,
-- los tres archivos Procedure_*.sql y los tres archivos view_*.sql.
-- Las consultas SELECT son seguras. Los CALL que modifican datos van comentados.
-- ============================================================
USE proyecto_recursoshumanos;

-- 1) Verificar catálogos y vistas
SELECT * FROM view_profesion;
SELECT * FROM view_tipo_documento;
SELECT * FROM view_pais;
SELECT * FROM view_genero;
SELECT * FROM view_estado_asistencia;
SELECT * FROM view_cargo;
SELECT * FROM view_tipo_contrato;
SELECT * FROM view_sede;
SELECT * FROM view_modalidad;
SELECT * FROM view_estado_solicitud;
SELECT * FROM view_tipo_permiso;
SELECT * FROM view_departamento_academico;

-- 2) Verificar los listados de cada módulo
CALL sp_empleado_listar();
CALL sp_contrato_listar();
CALL sp_asistencia_listar();
CALL sp_permiso_licencia_listar();
CALL sp_departamento_academico_listar();
CALL sp_capacitacion_listar();
CALL sp_empleado_capacitacion_listar();

-- 3) Ejemplos de búsqueda (ajusta el texto según tus datos)
CALL sp_empleado_buscar('');
CALL sp_contrato_buscar('');
CALL sp_asistencia_buscar('');
CALL sp_permiso_licencia_buscar('');
CALL sp_departamento_academico_buscar('');
CALL sp_capacitacion_buscar('');
CALL sp_empleado_capacitacion_buscar('');

-- 4) Ejemplos de modificación/alta/eliminación: DESCOMENTA solo si deseas probar.
-- CALL sp_capacitacion_insertar('Capacitación de prueba','SENATI','2026-10-01','2026-10-02',8);
-- CALL sp_capacitacion_modificar(1,'Capacitación actualizada','SENATI','2026-10-01','2026-10-02',10);
-- CALL sp_capacitacion_eliminar(1);

-- 5) Verificar procedimientos instalados
SHOW PROCEDURE STATUS WHERE Db = 'proyecto_recursoshumanos';
SHOW FULL TABLES IN proyecto_recursoshumanos WHERE Table_type = 'VIEW';
