-- Vistas del proyecto Recursos Humanos
USE proyecto_recursoshumanos;

CREATE OR REPLACE VIEW view_capacitacion AS SELECT id_capacitacion,nombre_capacitacion,institucion_organizadora,fecha_inicio,fecha_fin,horas_academicas FROM capacitacion;
CREATE OR REPLACE VIEW view_empleado_capacitacion AS SELECT id_empleado_capacitacion,id_capacitacion,id_empleado,estado_aprobacion,nota_obtenida,tiene_certificado FROM empleado_capacitacion;
CREATE OR REPLACE VIEW view_sede AS SELECT id_sede,nombre_sede,direccion FROM sede;
CREATE OR REPLACE VIEW view_modalidad AS SELECT id_modalidad,nombre_modalidad,estado FROM modalidad;
