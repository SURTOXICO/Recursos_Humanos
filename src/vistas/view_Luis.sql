-- Vistas del proyecto Recursos Humanos
USE proyecto_recursoshumanos;

CREATE OR REPLACE VIEW view_profesion AS SELECT id_profesion, nombre_profesion FROM profesion;
CREATE OR REPLACE VIEW view_tipo_documento AS SELECT id_tipo_documento, nombre_tipo FROM tipo_documento;
CREATE OR REPLACE VIEW view_pais AS SELECT id_pais, nombre_pais FROM pais;
CREATE OR REPLACE VIEW view_genero AS SELECT id_genero, nombre_genero FROM genero;
CREATE OR REPLACE VIEW view_estado_asistencia AS SELECT id_estado_asistencia, nombre_estado_asistencia FROM estado_asistencia;
CREATE OR REPLACE VIEW view_cargo AS SELECT id_cargo, nombre_cargo FROM cargo;
CREATE OR REPLACE VIEW view_tipo_contrato AS SELECT id_tipo_contrato, nombre_contrato, estado FROM tipo_contrato;
CREATE OR REPLACE VIEW view_empleado AS SELECT id_empleado,id_tipo_documento,id_genero,id_profesion,id_distrito,numero_documento,nombres,apellidos,telefono1,telefono2,fecha_nacimiento,direccion_actual,correo1,correo2,observaciones,estado FROM empleado;
CREATE OR REPLACE VIEW view_contrato AS SELECT id_contrato,id_empleado,id_tipo_contrato,id_cargo,id_sede,id_modalidad,id_departamento_academico,salario,horas_semanales,asignacion_familiar,fecha_inicio,fecha_fin,detalles,observaciones,estado FROM contrato;
CREATE OR REPLACE VIEW view_asistencias AS SELECT id_asistencia,id_empleado,id_estado_asistencia,fecha,hora_entrada,hora_salida,minutos_tardanza,observaciones FROM asistencias;
