-- Vistas del proyecto Recursos Humanos
USE proyecto_recursoshumanos;

CREATE OR REPLACE VIEW view_estado_solicitud AS SELECT id_estado_solicitud, descripcion FROM estado_solicitud;
CREATE OR REPLACE VIEW view_tipo_permiso AS SELECT id_tipo_permiso, descripcion FROM tipo_permiso;
CREATE OR REPLACE VIEW view_departamento_academico AS SELECT id_departamento_academico,nombre_departamento,codigo_area,descripcion,estado FROM departamento_academico;
CREATE OR REPLACE VIEW view_permiso_licencia AS SELECT id_permiso_licencia,id_empleado,id_tipo_permiso,id_estado_solicitud,fecha_inicio,fecha_fin,motivo_detalle FROM permiso_licencia;
