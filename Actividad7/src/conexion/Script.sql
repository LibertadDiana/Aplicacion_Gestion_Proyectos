-- Eliminación inicial por si existe la BBDD antes de lanzar el script
-- DROP DATABASE ad7;
CREATE DATABASE ad7;
USE ad7;

-- Creación tabla clientes
CREATE TABLE clientes(
	cif					VARCHAR(10) NOT NULL,
    nombre				VARCHAR (20) NOT NULL,
    apellidos			VARCHAR (45) NOT NULL,
    domicilio			VARCHAR (100),
    facturacion_anual	DECIMAL (15,2),
    numero_empleados	INT
);

-- Creación restricciones tabla 
    ALTER TABLE clientes ADD PRIMARY KEY (cif);

-- Creación tabla perfiles
CREATE TABLE perfiles(
	id_perfi			INT,
    nombre 				VARCHAR (20),
    precio_hora			DECIMAL (7,2) NOT NULL
);

-- Creación restricciones tabla
ALTER TABLE perfiles 	ADD PRIMARY KEY (id_perfi);

-- Creación tabla departamentos
CREATE TABLE departamentos(
	id_depar 			INT,
    nombre 				VARCHAR (45) NOT NULL,
    direccion 			VARCHAR (100)
);

-- Creación restricciones tabla 
ALTER TABLE departamentos ADD PRIMARY KEY (id_depar);

-- Creación tabla empleados
-- Se ha añadido "comision" para poder implementar el método "salarioBruto" de la clase Empleado
-- Se ha añadido "genero" para poder implementar el método "literalSexo" de la clase Empleado 
-- y "empleadosBySexo" de "EmpleadoDao"
CREATE TABLE empleados(
	id_empl				INT,
    nombre				VARCHAR (20) NOT NULL,
    apellidos			VARCHAR (45) NOT NULL,
    email				VARCHAR (100) NOT NULL,
    password			VARCHAR (45) NOT NULL,
    salario				DECIMAL (9,2),
    comision			DECIMAL (9,2),
    fecha_ingreso		DATE,
    fecha_nacimiento	DATE,
    genero				CHAR(1),
    id_perfi			INT NOT NULL,
    id_depar			INT NOT NULL
);

-- Creación restricciones tabla     
ALTER TABLE empleados ADD PRIMARY KEY (id_empl);
ALTER TABLE empleados
	ADD CONSTRAINT empleados_perfiles_FK1 FOREIGN KEY (id_perfi) REFERENCES perfiles (id_perfi),
	ADD CONSTRAINT empleados_departamentos_FK1 FOREIGN KEY (id_depar) REFERENCES departamentos (id_depar);

-- Creación tabla proyectos
CREATE TABLE proyectos(
	id_proyecto			VARCHAR (10),
    descripcion			VARCHAR (45) NOT NULL,
    fecha_inicio		DATE,
    fecha_fin_previsto	DATE,
    fecha_fin_real		DATE,
    venta_prevista		DECIMAL (11,2),
    costes_previsto 	DECIMAL (11,2),
    coste_real			DECIMAL (11,2),
    estado				VARCHAR (15),
    jefe_proyecto		INT,
    cif					VARCHAR (10) NOT NULL
);

-- Creación restricciones tabla 
ALTER TABLE proyectos ADD PRIMARY KEY (id_proyecto);
ALTER TABLE proyectos
	ADD CONSTRAINT proyectos_clientes_FK1 FOREIGN KEY (cif) REFERENCES clientes (cif);

-- Creación tabla proyecto_con_empleado
CREATE TABLE empleado_en_proyecto(
	numero_orden		INT,
    id_proyecto			VARCHAR (10),
    id_empl				INT,
    horas_asignadas		INT,
    fecha_incorporacion	DATE
);
    
-- Creación restricciones tabla     
ALTER TABLE empleado_en_proyecto ADD PRIMARY KEY (numero_orden);
ALTER TABLE empleado_en_proyecto
	ADD CONSTRAINT empleado_en_proyecto_proyectos_FK1 FOREIGN KEY (id_proyecto) REFERENCES proyectos (id_proyecto),
	ADD CONSTRAINT empleado_en_proyecto_empleados_FK1 FOREIGN KEY (id_empl) REFERENCES empleados (id_empl);

-- Creación tabla facturas
CREATE TABLE facturas(
	id_factura			VARCHAR (15),
    descripcion			VARCHAR (45) NOT NULL,
    id_proyecto 		VARCHAR (10) NOT NULL
);

-- Creación restricciones tabla 
ALTER TABLE facturas ADD PRIMARY KEY (id_factura);
ALTER TABLE facturas
	ADD CONSTRAINT facturas_proyectos_FK1 FOREIGN KEY (id_proyecto) REFERENCES proyectos (id_proyecto);
    
