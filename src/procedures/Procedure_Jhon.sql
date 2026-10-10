-- Procedimientos corregidos para usar utf8mb4_general_ci
-- Base de datos: proyecto_recursoshumanos
-- Ejecutar este archivo completo desde la pestaña SQL de phpMyAdmin.
-- Recrea únicamente los procedimientos incluidos; no elimina tablas ni registros.
SET NAMES utf8mb4 COLLATE utf8mb4_general_ci;
SET collation_connection = 'utf8mb4_general_ci';
USE proyecto_recursoshumanos;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_permiso_licencia_insertar$$
CREATE PROCEDURE sp_permiso_licencia_insertar(IN p_id_empleado INT, IN p_id_tipo_permiso INT, IN p_id_estado_solicitud INT, IN p_fecha_inicio DATE, IN p_fecha_fin DATE, IN p_motivo_detalle TEXT)
BEGIN
    INSERT INTO permiso_licencia(id_empleado,id_tipo_permiso,id_estado_solicitud,fecha_inicio,fecha_fin,motivo_detalle)
    VALUES(p_id_empleado,p_id_tipo_permiso,p_id_estado_solicitud,p_fecha_inicio,p_fecha_fin,p_motivo_detalle);
END$$

DROP PROCEDURE IF EXISTS sp_permiso_licencia_modificar$$
CREATE PROCEDURE sp_permiso_licencia_modificar(IN p_id_permiso_licencia INT, IN p_id_empleado INT, IN p_id_tipo_permiso INT, IN p_id_estado_solicitud INT, IN p_fecha_inicio DATE, IN p_fecha_fin DATE, IN p_motivo_detalle TEXT)
BEGIN
    UPDATE permiso_licencia SET id_empleado=p_id_empleado,id_tipo_permiso=p_id_tipo_permiso,id_estado_solicitud=p_id_estado_solicitud,fecha_inicio=p_fecha_inicio,fecha_fin=p_fecha_fin,motivo_detalle=p_motivo_detalle WHERE id_permiso_licencia=p_id_permiso_licencia;
END$$

DROP PROCEDURE IF EXISTS sp_permiso_licencia_eliminar$$
CREATE PROCEDURE sp_permiso_licencia_eliminar(IN p_id_permiso_licencia INT)
BEGIN
    DELETE FROM permiso_licencia WHERE id_permiso_licencia=p_id_permiso_licencia;
END$$

DROP PROCEDURE IF EXISTS sp_permiso_licencia_buscar$$
CREATE PROCEDURE sp_permiso_licencia_buscar(IN p_filtro VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci)
BEGIN
    SELECT * FROM permiso_licencia WHERE CAST(id_permiso_licencia AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR CAST(id_empleado AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR motivo_detalle COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) ORDER BY id_permiso_licencia;
END$$

DROP PROCEDURE IF EXISTS sp_permiso_licencia_listar$$
CREATE PROCEDURE sp_permiso_licencia_listar()
BEGIN
    SELECT * FROM permiso_licencia ORDER BY id_permiso_licencia;
END$$

DROP PROCEDURE IF EXISTS sp_departamento_academico_insertar$$
CREATE PROCEDURE sp_departamento_academico_insertar(IN p_nombre_departamento VARCHAR(150), IN p_codigo_area VARCHAR(45), IN p_descripcion VARCHAR(150), IN p_estado TINYINT)
BEGIN
    INSERT INTO departamento_academico(nombre_departamento,codigo_area,descripcion,estado) VALUES(p_nombre_departamento,p_codigo_area,p_descripcion,p_estado);
END$$

DROP PROCEDURE IF EXISTS sp_departamento_academico_modificar$$
CREATE PROCEDURE sp_departamento_academico_modificar(IN p_id_departamento_academico INT, IN p_nombre_departamento VARCHAR(150), IN p_codigo_area VARCHAR(45), IN p_descripcion VARCHAR(150), IN p_estado TINYINT)
BEGIN
    UPDATE departamento_academico SET nombre_departamento=p_nombre_departamento,codigo_area=p_codigo_area,descripcion=p_descripcion,estado=p_estado WHERE id_departamento_academico=p_id_departamento_academico;
END$$

DROP PROCEDURE IF EXISTS sp_departamento_academico_desactivar$$
CREATE PROCEDURE sp_departamento_academico_desactivar(IN p_id_departamento_academico INT)
BEGIN
    UPDATE departamento_academico SET estado=0 WHERE id_departamento_academico=p_id_departamento_academico;
END$$

DROP PROCEDURE IF EXISTS sp_departamento_academico_buscar$$
CREATE PROCEDURE sp_departamento_academico_buscar(IN p_filtro VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci)
BEGIN
    SELECT * FROM departamento_academico WHERE nombre_departamento COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR codigo_area COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR descripcion COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) ORDER BY id_departamento_academico;
END$$

DROP PROCEDURE IF EXISTS sp_departamento_academico_listar$$
CREATE PROCEDURE sp_departamento_academico_listar()
BEGIN
    SELECT * FROM departamento_academico ORDER BY id_departamento_academico;
END$$

DELIMITER ;
