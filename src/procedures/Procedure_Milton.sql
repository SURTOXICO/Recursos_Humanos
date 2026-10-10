-- Procedimientos corregidos para usar utf8mb4_general_ci
-- Base de datos: proyecto_recursoshumanos
-- Ejecutar este archivo completo desde la pestaña SQL de phpMyAdmin.
-- Recrea únicamente los procedimientos incluidos; no elimina tablas ni registros.
SET NAMES utf8mb4 COLLATE utf8mb4_general_ci;
SET collation_connection = 'utf8mb4_general_ci';
USE proyecto_recursoshumanos;

DELIMITER $$

DROP PROCEDURE IF EXISTS sp_capacitacion_insertar$$
CREATE PROCEDURE sp_capacitacion_insertar(IN p_nombre_capacitacion VARCHAR(150), IN p_institucion_organizadora VARCHAR(150), IN p_fecha_inicio DATE, IN p_fecha_fin DATE, IN p_horas_academicas INT)
BEGIN
    INSERT INTO capacitacion(nombre_capacitacion,institucion_organizadora,fecha_inicio,fecha_fin,horas_academicas) VALUES(p_nombre_capacitacion,p_institucion_organizadora,p_fecha_inicio,p_fecha_fin,p_horas_academicas);
END$$

DROP PROCEDURE IF EXISTS sp_capacitacion_modificar$$
CREATE PROCEDURE sp_capacitacion_modificar(IN p_id_capacitacion INT, IN p_nombre_capacitacion VARCHAR(150), IN p_institucion_organizadora VARCHAR(150), IN p_fecha_inicio DATE, IN p_fecha_fin DATE, IN p_horas_academicas INT)
BEGIN
    UPDATE capacitacion SET nombre_capacitacion=p_nombre_capacitacion,institucion_organizadora=p_institucion_organizadora,fecha_inicio=p_fecha_inicio,fecha_fin=p_fecha_fin,horas_academicas=p_horas_academicas WHERE id_capacitacion=p_id_capacitacion;
END$$

DROP PROCEDURE IF EXISTS sp_capacitacion_eliminar$$
CREATE PROCEDURE sp_capacitacion_eliminar(IN p_id_capacitacion INT)
BEGIN
    DELETE FROM capacitacion WHERE id_capacitacion=p_id_capacitacion;
END$$

DROP PROCEDURE IF EXISTS sp_capacitacion_buscar$$
CREATE PROCEDURE sp_capacitacion_buscar(IN p_filtro VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci)
BEGIN
    SELECT * FROM capacitacion WHERE nombre_capacitacion COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR institucion_organizadora COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR CAST(id_capacitacion AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) ORDER BY id_capacitacion;
END$$

DROP PROCEDURE IF EXISTS sp_capacitacion_listar$$
CREATE PROCEDURE sp_capacitacion_listar()
BEGIN
    SELECT * FROM capacitacion ORDER BY id_capacitacion;
END$$

DROP PROCEDURE IF EXISTS sp_empleado_capacitacion_insertar$$
CREATE PROCEDURE sp_empleado_capacitacion_insertar(IN p_id_capacitacion INT, IN p_id_empleado INT, IN p_estado_aprobacion VARCHAR(40), IN p_nota_obtenida DECIMAL(5,2), IN p_tiene_certificado TINYINT)
BEGIN
    INSERT INTO empleado_capacitacion(id_capacitacion,id_empleado,estado_aprobacion,nota_obtenida,tiene_certificado) VALUES(p_id_capacitacion,p_id_empleado,p_estado_aprobacion,p_nota_obtenida,p_tiene_certificado);
END$$

DROP PROCEDURE IF EXISTS sp_empleado_capacitacion_modificar$$
CREATE PROCEDURE sp_empleado_capacitacion_modificar(IN p_id_empleado_capacitacion INT, IN p_id_capacitacion INT, IN p_id_empleado INT, IN p_estado_aprobacion VARCHAR(40), IN p_nota_obtenida DECIMAL(5,2), IN p_tiene_certificado TINYINT)
BEGIN
    UPDATE empleado_capacitacion SET id_capacitacion=p_id_capacitacion,id_empleado=p_id_empleado,estado_aprobacion=p_estado_aprobacion,nota_obtenida=p_nota_obtenida,tiene_certificado=p_tiene_certificado WHERE id_empleado_capacitacion=p_id_empleado_capacitacion;
END$$

DROP PROCEDURE IF EXISTS sp_empleado_capacitacion_eliminar$$
CREATE PROCEDURE sp_empleado_capacitacion_eliminar(IN p_id_empleado_capacitacion INT)
BEGIN
    DELETE FROM empleado_capacitacion WHERE id_empleado_capacitacion=p_id_empleado_capacitacion;
END$$

DROP PROCEDURE IF EXISTS sp_empleado_capacitacion_buscar$$
CREATE PROCEDURE sp_empleado_capacitacion_buscar(IN p_filtro VARCHAR(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci)
BEGIN
    SELECT * FROM empleado_capacitacion WHERE CAST(id_empleado_capacitacion AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR CAST(id_empleado AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) OR CAST(id_capacitacion AS CHAR CHARACTER SET utf8mb4) COLLATE utf8mb4_general_ci LIKE CONCAT(_utf8mb4'%' COLLATE utf8mb4_general_ci, p_filtro COLLATE utf8mb4_general_ci, _utf8mb4'%' COLLATE utf8mb4_general_ci) ORDER BY id_empleado_capacitacion;
END$$

DROP PROCEDURE IF EXISTS sp_empleado_capacitacion_listar$$
CREATE PROCEDURE sp_empleado_capacitacion_listar()
BEGIN
    SELECT * FROM empleado_capacitacion ORDER BY id_empleado_capacitacion;
END$$

DELIMITER ;
