-- ============================================================
-- ESQUEMA COMPLEMENTARIO PARA LOS FORMULARIOS EXISTENTES
-- Base: proyecto_recursoshumanos | MySQL 8 / phpMyAdmin
-- Ejecutar después de bd/esquema_catalogos.sql
-- Usa CREATE TABLE IF NOT EXISTS para no reemplazar tablas existentes.
-- ============================================================
USE proyecto_recursoshumanos;

CREATE TABLE IF NOT EXISTS empleado (
 id_empleado INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 id_tipo_documento INT NOT NULL,
 id_genero INT NOT NULL,
 id_profesion INT NOT NULL,
 id_distrito INT NOT NULL,
 numero_documento VARCHAR(25) NOT NULL,
 nombres VARCHAR(100) NOT NULL,
 apellidos VARCHAR(100) NOT NULL,
 telefono1 VARCHAR(25) NOT NULL,
 telefono2 VARCHAR(25) NULL,
 fecha_nacimiento DATE NOT NULL,
 direccion_actual VARCHAR(200) NOT NULL,
 correo1 VARCHAR(150) NOT NULL,
 correo2 VARCHAR(150) NULL,
 observaciones TEXT NULL,
 estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
 UNIQUE KEY uq_empleado_documento (numero_documento)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS usuario (
 id_usuario INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 codigo_usuario VARCHAR(50) NOT NULL,
 contrasena VARCHAR(255) NOT NULL,
 id_cargo INT NOT NULL,
 estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO',
 UNIQUE KEY uq_usuario_codigo (codigo_usuario)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS contrato (
 id_contrato INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 id_empleado INT NOT NULL,
 id_tipo_contrato INT NOT NULL,
 id_cargo INT NOT NULL,
 id_sede INT NOT NULL,
 id_modalidad INT NOT NULL,
 id_departamento_academico INT NULL,
 salario DECIMAL(10,2) NOT NULL,
 horas_semanales INT NOT NULL,
 asignacion_familiar DECIMAL(10,2) NOT NULL DEFAULT 0,
 fecha_inicio DATE NOT NULL,
 fecha_fin DATE NULL,
 detalles TEXT NULL,
 observaciones TEXT NULL,
 estado VARCHAR(20) NOT NULL DEFAULT 'ACTIVO'
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS asistencias (
 id_asistencia INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 id_empleado INT NOT NULL,
 id_estado_asistencia INT NOT NULL,
 fecha DATE NOT NULL,
 hora_entrada TIME NULL,
 hora_salida TIME NULL,
 minutos_tardanza INT NOT NULL DEFAULT 0,
 observaciones TEXT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS permiso_licencia (
 id_permiso_licencia INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 id_empleado INT NOT NULL,
 id_tipo_permiso INT NOT NULL,
 id_estado_solicitud INT NOT NULL,
 fecha_inicio DATE NOT NULL,
 fecha_fin DATE NOT NULL,
 motivo_detalle TEXT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS empleado_capacitacion (
 id_empleado_capacitacion INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 id_capacitacion INT NOT NULL,
 id_empleado INT NOT NULL,
 estado_aprobacion VARCHAR(40) NOT NULL,
 nota_obtenida DECIMAL(5,2) NULL,
 tiene_certificado TINYINT(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB;

-- NOTA: el formulario frm_Mantasistencia mezcla los nombres 'asistencia' y
-- 'asistencias'. Se usa 'asistencias' como nombre estándar en los procedimientos.
-- Ajusta las consultas antiguas del formulario para llamar a los procedimientos.
