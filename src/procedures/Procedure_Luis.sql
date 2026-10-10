-- Procedimientos corregidos para usar utf8mb4_general_ci
-- Base de datos: proyecto_recursoshumanos
-- Ejecutar este archivo completo desde la pestaña SQL de phpMyAdmin.
-- Recrea únicamente los procedimientos incluidos; no elimina tablas ni registros.
SET NAMES utf8mb4 COLLATE utf8mb4_general_ci;
SET collation_connection = 'utf8mb4_general_ci';
USE proyecto_recursoshumanos;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_empleado_insertar$$
CREATE PROCEDURE sp_empleado_insertar(IN p_id_tipo_documento INT, IN p_id_genero INT, IN p_id_profesion INT, IN p_id_distrito INT, IN p_numero_documento VARCHAR(25), IN p_nombres VARCHAR(100), IN p_apellidos VARCHAR(100), IN p_telefono1 VARCHAR(25), IN p_telefono2 VARCHAR(25), IN p_fecha_nacimiento DATE, IN p_direccion_actual VARCHAR(200), IN p_correo1 VARCHAR(150), IN p_correo2 VARCHAR(150), IN p_observaciones TEXT, IN p_estado VARCHAR(20))
BEGIN
    INSERT INTO empleado(id_tipo_documento,id_genero,id_profesion,id_distrito,numero_documento,nombres,apellidos,telefono1,telefono2,fecha_nacimiento,direccion_actual,correo1,correo2,observaciones,estado)
    VALUES(p_id_tipo_documento,p_id_genero,p_id_profesion,p_id_distrito,p_numero_documento,p_nombres,p_apellidos,p_telefono1,p_telefono2,p_fecha_nacimiento,p_direccion_actual,p_correo1,p_correo2,p_observaciones,p_estado);
END$$

DROP PROCEDURE IF EXISTS sp_empleado_modificar$$
CREATE PROCEDURE sp_empleado_modificar(IN p_id_empleado INT, IN p_id_tipo_documento INT, IN p_id_genero INT, IN p_id_profesion INT, IN p_id_distrito INT, IN p_numero_documento VARCHAR(25), IN p_nombres VARCHAR(100), IN p_apellidos VARCHAR(100), IN p_telefono1 VARCHAR(25), IN p_telefono2 VARCHAR(25), IN p_fecha_nacimiento DATE, IN p_direccion_actual VARCHAR(200), IN p_correo1 VARCHAR(150), IN p_correo2 VARCHAR(150), IN p_observaciones TEXT, IN p_estado VARCHAR(20))
BEGIN
    UPDATE empleado SET id_tipo_documento=p_id_tipo_documento,id_genero=p_id_genero,id_profesion=p_id_profesion,id_distrito=p_id_distrito,numero_documento=p_numero_documento,nombres=p_nombres,apellidos=p_apellidos,telefono1=p_telefono1,telefono2=p_telefono2,fecha_nacimiento=p_fecha_nacimiento,direccion_actual=p_direccion_actual,correo1=p_correo1,correo2=p_correo2,observaciones=p_observaciones,estado=p_estado
    WHERE id_empleado=p_id_empleado;
END$$

DROP PROCEDURE IF EXISTS sp_empleado_desactivar$$
CREATE PROCEDURE sp_empleado_desactivar(IN p_id_empleado INT)
BEGIN
    UPDATE empleado SET estado='INACTIVO' WHERE id_empleado=p_id_empleado;
END$$

DROP PROCEDURE IF EXISTS sp_empleado_buscar$$
CREATE PROCEDURE sp_empleado_buscar(IN p_filtro VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci)
BEGIN
    SELECT * FROM empleado WHERE numero_documento COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR nombres COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR apellidos COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) ORDER BY id_empleado;
END$$

DROP PROCEDURE IF EXISTS sp_empleado_listar$$
CREATE PROCEDURE sp_empleado_listar()
BEGIN
    SELECT * FROM empleado ORDER BY id_empleado;
END$$

DROP PROCEDURE IF EXISTS sp_contrato_insertar$$
CREATE PROCEDURE sp_contrato_insertar(IN p_id_empleado INT, IN p_id_tipo_contrato INT, IN p_id_cargo INT, IN p_id_sede INT, IN p_id_modalidad INT, IN p_id_departamento_academico INT, IN p_salario DECIMAL(10,2), IN p_horas_semanales INT, IN p_asignacion_familiar DECIMAL(10,2), IN p_fecha_inicio DATE, IN p_fecha_fin DATE, IN p_detalles TEXT, IN p_observaciones TEXT, IN p_estado VARCHAR(20))
BEGIN
    INSERT INTO contrato(id_empleado,id_tipo_contrato,id_cargo,id_sede,id_modalidad,id_departamento_academico,salario,horas_semanales,asignacion_familiar,fecha_inicio,fecha_fin,detalles,observaciones,estado) VALUES(p_id_empleado,p_id_tipo_contrato,p_id_cargo,p_id_sede,p_id_modalidad,p_id_departamento_academico,p_salario,p_horas_semanales,p_asignacion_familiar,p_fecha_inicio,p_fecha_fin,p_detalles,p_observaciones,p_estado);
END$$

DROP PROCEDURE IF EXISTS sp_contrato_modificar$$
CREATE PROCEDURE sp_contrato_modificar(IN p_id_contrato INT, IN p_id_empleado INT, IN p_id_tipo_contrato INT, IN p_id_cargo INT, IN p_id_sede INT, IN p_id_modalidad INT, IN p_id_departamento_academico INT, IN p_salario DECIMAL(10,2), IN p_horas_semanales INT, IN p_asignacion_familiar DECIMAL(10,2), IN p_fecha_inicio DATE, IN p_fecha_fin DATE, IN p_detalles TEXT, IN p_observaciones TEXT, IN p_estado VARCHAR(20))
BEGIN
    UPDATE contrato SET id_empleado=p_id_empleado,id_tipo_contrato=p_id_tipo_contrato,id_cargo=p_id_cargo,id_sede=p_id_sede,id_modalidad=p_id_modalidad,id_departamento_academico=p_id_departamento_academico,salario=p_salario,horas_semanales=p_horas_semanales,asignacion_familiar=p_asignacion_familiar,fecha_inicio=p_fecha_inicio,fecha_fin=p_fecha_fin,detalles=p_detalles,observaciones=p_observaciones,estado=p_estado WHERE id_contrato=p_id_contrato;
END$$

DROP PROCEDURE IF EXISTS sp_contrato_desactivar$$
CREATE PROCEDURE sp_contrato_desactivar(IN p_id_contrato INT)
BEGIN
    UPDATE contrato SET estado='INACTIVO' WHERE id_contrato=p_id_contrato;
END$$

DROP PROCEDURE IF EXISTS sp_contrato_buscar$$
CREATE PROCEDURE sp_contrato_buscar(IN p_filtro VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci)
BEGIN
    SELECT * FROM contrato WHERE CAST(id_contrato AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR CAST(id_empleado AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR estado COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) ORDER BY id_contrato;
END$$

DROP PROCEDURE IF EXISTS sp_contrato_listar$$
CREATE PROCEDURE sp_contrato_listar()
BEGIN
    SELECT * FROM contrato ORDER BY id_contrato;
END$$

DROP PROCEDURE IF EXISTS sp_asistencia_insertar$$
CREATE PROCEDURE sp_asistencia_insertar(IN p_id_empleado INT, IN p_id_estado_asistencia INT, IN p_fecha DATE, IN p_hora_entrada TIME, IN p_hora_salida TIME, IN p_minutos_tardanza INT, IN p_observaciones TEXT)
BEGIN
    INSERT INTO asistencias(id_empleado,id_estado_asistencia,fecha,hora_entrada,hora_salida,minutos_tardanza,observaciones)
    VALUES(p_id_empleado,p_id_estado_asistencia,p_fecha,p_hora_entrada,p_hora_salida,p_minutos_tardanza,p_observaciones);
END$$

DROP PROCEDURE IF EXISTS sp_asistencia_modificar$$
CREATE PROCEDURE sp_asistencia_modificar(IN p_id_asistencia INT, IN p_id_empleado INT, IN p_id_estado_asistencia INT, IN p_fecha DATE, IN p_hora_entrada TIME, IN p_hora_salida TIME, IN p_minutos_tardanza INT, IN p_observaciones TEXT)
BEGIN
    UPDATE asistencias SET id_empleado=p_id_empleado,id_estado_asistencia=p_id_estado_asistencia,fecha=p_fecha,hora_entrada=p_hora_entrada,hora_salida=p_hora_salida,minutos_tardanza=p_minutos_tardanza,observaciones=p_observaciones WHERE id_asistencia=p_id_asistencia;
END$$

DROP PROCEDURE IF EXISTS sp_asistencia_eliminar$$
CREATE PROCEDURE sp_asistencia_eliminar(IN p_id_asistencia INT)
BEGIN
    DELETE FROM asistencias WHERE id_asistencia=p_id_asistencia;
END$$

DROP PROCEDURE IF EXISTS sp_asistencia_buscar$$
CREATE PROCEDURE sp_asistencia_buscar(IN p_filtro VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci)
BEGIN
    SELECT * FROM asistencias WHERE CAST(id_asistencia AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR CAST(id_empleado AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR DATE_FORMAT(fecha,'%Y-%m-%d') COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) ORDER BY id_asistencia;
END$$

DROP PROCEDURE IF EXISTS sp_asistencia_listar$$
CREATE PROCEDURE sp_asistencia_listar()
BEGIN
    SELECT * FROM asistencias ORDER BY id_asistencia;
END$$

DROP PROCEDURE IF EXISTS sp_usuario_validar_login$$
CREATE PROCEDURE sp_usuario_validar_login(IN p_codigo VARCHAR(50), IN p_cargo VARCHAR(100))
BEGIN
    SELECT u.contrasena FROM usuario u INNER JOIN cargo c ON u.id_cargo=c.id_cargo WHERE u.codigo_usuario=p_codigo AND c.nombre_cargo=p_cargo AND u.estado='ACTIVO' LIMIT 1;
END$$

DROP PROCEDURE IF EXISTS sp_cargo_listar$$
CREATE PROCEDURE sp_cargo_listar()
BEGIN
    SELECT nombre_cargo AS Nombre_Rol FROM cargo ORDER BY nombre_cargo;
END$$

DELIMITER ;
