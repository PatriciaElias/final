/*INSERCION DE CATEGORIAS*/
INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('playa' , 'Almacena todas las playas turisticas que posee la zona costera de El Salvador');

INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('balneario' , 'Almacena todos los balnearios que posee El Salvador');

INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('lago' , 'Almacena todos los lagos y lagunas que posee El Salvador');

INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('catedral' , 'Almacena todas los catedrales que posee El Salvador');

INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('ruta' , 'Almacena todas las rutas turisticas que posee El Salvador');

INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('ruina' , 'Almacena todas las ruinas y sitios arqueologicos que posee El Salvador');

INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('volcan' , 'Almacena todos los volcanes activos e inactivos que posee El Salvador');

INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('montania' , 'Almacena todas las montanias que posee El Salvador');

INSERT INTO `categorias_lugares`(nombre_cat, descripcion)
VALUES ('mirador' , 'Almacena todos los miradores que posee El Salvador');

/*INSERCION DE DEPARTAMENTOS*/
INSERT INTO departamentos(`nombre_departamento`)VALUES('San Salvador');
INSERT INTO departamentos(`nombre_departamento`)VALUES('Ahuachapán');
INSERT INTO departamentos(`nombre_departamento`)VALUES('Cabañas');
INSERT INTO departamentos(`nombre_departamento`)VALUES('Chalatenango');
INSERT INTO departamentos(`nombre_departamento`)VALUES('Cuscatlán');
INSERT INTO departamentos(`nombre_departamento`)VALUES('La Libertad');
INSERT INTO departamentos(`nombre_departamento`)VALUES('Morazán');
INSERT INTO departamentos(`nombre_departamento`)VALUES('La Paz');
INSERT INTO departamentos(`nombre_departamento`)VALUES('Santa Ana');
INSERT INTO departamentos(`nombre_departamento`)VALUES('San Miguel');
INSERT INTO departamentos(`nombre_departamento`)VALUES('San Vicente	');
INSERT INTO departamentos(`nombre_departamento`)VALUES('Sonsonate');
INSERT INTO departamentos(`nombre_departamento`)VALUES('La Unión');
INSERT INTO departamentos(`nombre_departamento`)VALUES('Usulután');

/*INSERCION DE ESTADOS*/
INSERT INTO estados(`nombre_estado`,`descripcion`)VALUES('Aceptado','El comentario fue aceptado');
INSERT INTO estados(`nombre_estado`,`descripcion`)VALUES('Denegado','El comentario fue denegado');
INSERT INTO estados(`nombre_estado`,`descripcion`)VALUES('Revision','El comentario se encuentra en revision');

/*INSERCION DE ROL*/
INSERT INTO rol(`nombre_rol`,`descripcion`)VALUES('Administrador','Posee acceso total');


/*INSERCION DE USUARIOS*/
INSERT INTO usuario(`nombre`,`apellido`,`correo`,`username`,`pass`,`id_rol`)VALUES
('Andersson','Diaz','diaz@gmail.com','king','12345',1);

INSERT INTO usuario(`nombre`,`apellido`,`correo`,`username`,`pass`,`id_rol`)VALUES
('Magali','Dueñas','magali@gmail.com','tasty','12345',1);

INSERT INTO usuario(`nombre`,`apellido`,`correo`,`username`,`pass`,`id_rol`)VALUES
('Fhernanda','Elias','elias@gmail.com','fher','12345',1);

INSERT INTO usuario(`nombre`,`apellido`,`correo`,`username`,`pass`,`id_rol`)VALUES
('Luis','Garcia','luis@gmail.com','gilberto','12345',1);

INSERT INTO usuario(`nombre`,`apellido`,`correo`,`username`,`pass`,`id_rol`)VALUES
('Tania','Martir','tani@gmail.com','tania','12345',1);
                        
/*INSERCION DE PLAYAS DE PRUEBA*/
INSERT INTO playas (id_categorias, nombre_playa, departamento, municipio, direccion,descripcion)VALUES( 1, 'El tunco', 1 ,'Muni', 'Direc','Descrip');
INSERT INTO playas (id_categorias, nombre_playa, departamento, municipio, direccion,descripcion)VALUES( 1, 'El tunco2', 1 ,'Muni2', 'Direc2','Descrip2');
INSERT INTO playas (id_categorias, nombre_playa, departamento, municipio, direccion,descripcion)VALUES( 1, 'El tunco3', 1,'Muni3', 'Direc3','Descrip3');