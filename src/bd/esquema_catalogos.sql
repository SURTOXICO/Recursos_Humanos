-- ============================================================
-- TABLAS SIN CLAVE FORÁNEA
-- Base de datos: proyecto_recursoshumanos
-- MySQL 8.x / phpMyAdmin
-- ============================================================

USE proyecto_recursoshumanos;

-- ============================================================
-- 1. PROFESION
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS profesion (
    id_profesion INT NOT NULL AUTO_INCREMENT,
    nombre_profesion VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_profesion),
    CONSTRAINT uq_profesion_nombre UNIQUE (nombre_profesion)
) ENGINE=InnoDB;


-- ============================================================
-- 2. TIPO_DOCUMENTO
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS tipo_documento (
    id_tipo_documento INT NOT NULL AUTO_INCREMENT,
    nombre_tipo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_tipo_documento),
    CONSTRAINT uq_tipo_documento_nombre UNIQUE (nombre_tipo)
) ENGINE=InnoDB;


-- ============================================================
-- 3. PAIS
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS pais (
    id_pais INT NOT NULL AUTO_INCREMENT,
    nombre_pais VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_pais),
    CONSTRAINT uq_pais_nombre UNIQUE (nombre_pais)
) ENGINE=InnoDB;


-- ============================================================
-- 4. GENERO
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS genero (
    id_genero INT NOT NULL AUTO_INCREMENT,
    nombre_genero VARCHAR(20) NOT NULL,
    PRIMARY KEY (id_genero),
    CONSTRAINT uq_genero_nombre UNIQUE (nombre_genero)
) ENGINE=InnoDB;


-- ============================================================
-- 5. ESTADO_ASISTENCIA
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS estado_asistencia (
    id_estado_asistencia INT NOT NULL AUTO_INCREMENT,
    nombre_estado_asistencia VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_estado_asistencia),
    CONSTRAINT uq_estado_asistencia_nombre
        UNIQUE (nombre_estado_asistencia)
) ENGINE=InnoDB;


-- ============================================================
-- 6. CAPACITACION
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS capacitacion (
    id_capacitacion INT NOT NULL AUTO_INCREMENT,
    nombre_capacitacion VARCHAR(150) NOT NULL,
    institucion_organizadora VARCHAR(150) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    horas_academicas INT NOT NULL,
    PRIMARY KEY (id_capacitacion)
) ENGINE=InnoDB;


-- ============================================================
-- 7. ESTADO_SOLICITUD
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS estado_solicitud (
    id_estado_solicitud INT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(65) NOT NULL,
    PRIMARY KEY (id_estado_solicitud),
    CONSTRAINT uq_estado_solicitud_desc
        UNIQUE (descripcion)
) ENGINE=InnoDB;


-- ============================================================
-- 8. TIPO_PERMISO
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS tipo_permiso (
    id_tipo_permiso INT NOT NULL AUTO_INCREMENT,
    descripcion VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_tipo_permiso),
    CONSTRAINT uq_tipo_permiso_desc
        UNIQUE (descripcion)
) ENGINE=InnoDB;


-- ============================================================
-- 9. SEDE
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS sede (
    id_sede INT NOT NULL AUTO_INCREMENT,
    nombre_sede VARCHAR(100) NOT NULL,
    direccion VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_sede),
    CONSTRAINT uq_sede_nombre UNIQUE (nombre_sede)
) ENGINE=InnoDB;


-- ============================================================
-- 10. MODALIDAD
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS modalidad (
    id_modalidad INT NOT NULL AUTO_INCREMENT,
    nombre_modalidad VARCHAR(80) NOT NULL,
    estado TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (id_modalidad),
    CONSTRAINT uq_modalidad_nombre UNIQUE (nombre_modalidad)
) ENGINE=InnoDB;


-- ============================================================
-- 11. CARGO
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS cargo (
    id_cargo INT NOT NULL AUTO_INCREMENT,
    nombre_cargo VARCHAR(100) NOT NULL,
    PRIMARY KEY (id_cargo),
    CONSTRAINT uq_cargo_nombre UNIQUE (nombre_cargo)
) ENGINE=InnoDB;


-- ============================================================
-- 12. TIPO_CONTRATO
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS tipo_contrato (
    id_tipo_contrato INT NOT NULL AUTO_INCREMENT,
    nombre_contrato VARCHAR(80) NOT NULL,
    estado TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (id_tipo_contrato),
    CONSTRAINT uq_tipo_contrato_nombre
        UNIQUE (nombre_contrato)
) ENGINE=InnoDB;


-- ============================================================
-- 13. DEPARTAMENTO_ACADEMICO
-- ============================================================
-- La tabla se conserva si ya existe; no se eliminan datos.
CREATE TABLE IF NOT EXISTS departamento_academico (
    id_departamento_academico INT NOT NULL AUTO_INCREMENT,
    nombre_departamento VARCHAR(150) NOT NULL,
    codigo_area VARCHAR(45) NOT NULL,
    descripcion VARCHAR(150) NULL,
    estado TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (id_departamento_academico),
    CONSTRAINT uq_departamento_academico_codigo
        UNIQUE (codigo_area)
) ENGINE=InnoDB;


-- ============================================================
-- FIN
-- 13 TABLAS SIN CLAVE FORÁNEA
-- ============================================================