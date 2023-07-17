/*===============================================CREACION DE TABLAS Y RELACIONES=================================================*/

use  atractivos;
CREATE TABLE `rol` (
  `id_rol` 		int 			NOT NULL AUTO_INCREMENT,
  `nombre_rol` 	varchar(50) 	NOT NULL,
  `descripcion` varchar(100) 	NOT NULL,
  PRIMARY KEY (`id_rol`)
) ;

CREATE TABLE `usuario` (
  `cod_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` 		varchar(75) 	NOT NULL,
  `apellido` 	varchar(75) 	NOT NULL,
  `correo` 		varchar(100) 	NOT NULL,
  `username` 	varchar(100) 	NOT NULL,
  `pass` 		varchar(100) 	NOT NULL,
  `id_rol` 		int 			NOT NULL,
  PRIMARY KEY (`cod_usuario`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  KEY `fK_rol_usuario` (`id_rol`),
  CONSTRAINT `fK_rol_usuario` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`)
);

CREATE TABLE `categorias_lugares` (
  `id_categorias` 	int 			NOT NULL AUTO_INCREMENT,
  `nombre_cat` 		varchar(50) 	NOT NULL,
  `descripcion` 	varchar(100) 	NOT NULL,
  PRIMARY KEY (`id_categorias`)
) ;

CREATE TABLE `departamentos` (
  `cod_departamento` 	int 	 		NOT NULL AUTO_INCREMENT,
  `nombre_departamento` varchar(50) 	NOT NULL,
  PRIMARY KEY (`cod_departamento`)
) ;

CREATE TABLE `acceso` (
  `id_acceso` 		int NOT NULL,
  `id_categorias` 	int NOT NULL,
  `cod_usuario` 	int NOT NULL,
  PRIMARY KEY (`id_acceso`),
  KEY `cod_usuario` (`cod_usuario`),
  KEY `id_categorias` (`id_categorias`),
  CONSTRAINT `acceso_ibfk_1` FOREIGN KEY (`cod_usuario`) REFERENCES `usuario` (`cod_usuario`),
  CONSTRAINT `acceso_ibfk_2` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`)
) ;

CREATE TABLE `balnearios` (
  `id_balnearios` 	int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 	int 			NOT NULL,
  `nombre` 			varchar(75) 	NOT NULL,
  `cod_departamento`int			 	NOT NULL,
  `municipio` 		varchar(75) 	NOT NULL,
  `direccion` 		varchar(200) 	NOT NULL,
  `descripcion` 	varchar(2000) 	NOT NULL,
  `foto` 			longblob,
  PRIMARY KEY (`id_balnearios`),
  KEY `fK_cat_balneario` (`id_categorias`),
  CONSTRAINT `fK_cat_balneario` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_balneario`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE
) ;

CREATE TABLE `catedrales` (
  `id_catedrales` 	int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 	int 			NOT NULL,
  `nombre` 			varchar(75) 	NOT NULL,
  `cod_departamento`int				NOT NULL,
  `municipio` 		varchar(75) 	NOT NULL,
  `direccion` 		varchar(200) 	NOT NULL,
  `descripcion` 	varchar(2000) 	NOT NULL,
  `foto` 			longblob,
  PRIMARY KEY (`id_catedrales`),
  KEY `fK_cat_catedral` (`id_categorias`),
  CONSTRAINT `fK_cat_catedral`   FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_catedral`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE
) ;

CREATE TABLE `lagos` (
  `id_lagos` 		int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 	int 			NOT NULL,
  `nombre_lago` 			varchar(75) 	NOT NULL,
  `cod_departamento`int			 	NOT NULL,
  `municipio` 		varchar(75) 	NOT NULL,
  `direccion` 		varchar(200) 	NOT NULL,
  `descripcion` 	varchar(2000) 	NOT NULL,
  `foto` 			longblob,
  PRIMARY KEY (`id_lagos`),
  KEY `fK_cat_lago` (`id_categorias`),
  CONSTRAINT `fK_cat_lago` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_lago`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE
) ;

CREATE TABLE `miradores` (
  `id_mirador` 		int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 	int 			NOT NULL,
  `nombre_mirador` 			        varchar(50) 	NOT NULL,
  `cod_departamento`int			 	NOT NULL,
  `municipio` 		varchar(50) 	NOT NULL,
  `direccion` 		varchar(200) 	NOT NULL,
  `descripcion` 	varchar(2000) 	NOT NULL,
  `foto` 			longblob,
  PRIMARY KEY (`id_mirador`),
  KEY `fK_cat_mirador` (`id_categorias`),
  CONSTRAINT `fK_cat_mirador` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_mirador`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE
) ;

CREATE TABLE `montanias` (
  `id_montanias` 	int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 	int 			NOT NULL,
  `nombre` 			varchar(50) 	NOT NULL,
  `cod_departamento`int			 	NOT NULL,
  `municipio` 		varchar(50) 	NOT NULL,
  `direccion` 		varchar(200) 	NOT NULL,
  `descripcion` 	varchar(2000) 	NOT NULL,
  `foto` 			longblob,
  PRIMARY KEY (`id_montanias`),
  KEY `fK_cat_montania` (`id_categorias`),
  CONSTRAINT `fK_cat_montania` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_montania`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE
) ;

CREATE TABLE `playas` (
  `id_playas` 		int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 	int 			NOT NULL,
  `nombre_playa` 	varchar(75) 	NOT NULL,
  `cod_departamento`int			 	NOT NULL,
  `municipio` 		varchar(75) 	DEFAULT NULL,
  `direccion` 		varchar(200) 	DEFAULT NULL,
  `descripcion` 	varchar(2000) 	DEFAULT NULL,
  `foto` 			longblob,
  PRIMARY KEY (`id_playas`),
  KEY `fK_cat_playa` (`id_categorias`),
  CONSTRAINT `fK_cat_playa` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_playa`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE
) ;

CREATE TABLE `rutas` (
  `id_ruta` 		int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 	int 			NOT NULL,
  `nombre` 			varchar(75) 	NOT NULL,
  `duracion` 		varchar(75) 	DEFAULT NULL,
  `descripcion` 	varchar(2000) 	NOT NULL,
  `foto` 			longblob,
  PRIMARY KEY (`id_ruta`),
  KEY `fK_cat_ruta` (`id_categorias`),
  CONSTRAINT `fK_cat_ruta` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE
) ;

CREATE TABLE `ruinas` (
  `id_ruinas` int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 	int 			NOT NULL,
  `nombre_ruina` 			varchar(50) 	NOT NULL,
  `cod_departamento`int			 	NOT NULL,
  `municipio` 		varchar(50) 	NOT NULL,
  `direccion` 		varchar(200) 	NOT NULL,
  `descripcion` 	varchar(2000) 	NOT NULL,
  `foto` 			longblob,
  PRIMARY KEY (`id_ruina`),
  KEY `fK_cat_ruina` (`id_categorias`),
  CONSTRAINT `fK_cat_ruina` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_ruina`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE
) ;

CREATE TABLE `volcanes` (
  `id_volcanes` 		int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 		int				NOT NULL,
  `nombre_volcan` 				varchar(50) 	NOT NULL,
  `cod_departamento` 	int			 	NOT NULL,
  `municipio` 			varchar(45) 	NOT NULL,
  `direccion` 			varchar(200) 	NOT NULL,
  `descripcion` 		varchar(2000) 	NOT NULL,
  `foto` 				longblob,
  PRIMARY KEY (`id_volcanes`),
  KEY `fK_cat_volcan` (`id_categorias`),
  CONSTRAINT `fK_cat_volcan` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_volcan`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE
) ;

CREATE TABLE `estados`(
  `cod_estado`		 	int 			NOT NULL AUTO_INCREMENT,
  `nombre_estado` 		varchar(25)		NOT NULL,
  `descripcion` 		varchar(2000) 	NOT NULL,
  PRIMARY KEY (`cod_estado`)
 ) ;

CREATE TABLE `recomendaciones` (
  `id_recomendacion` 	int 			NOT NULL AUTO_INCREMENT,
  `id_categorias` 		int				NOT NULL,
  `nombre_lugar` 		varchar(50) 	NOT NULL,
  `cod_departamento` 	int			 	NOT NULL,
  `municipio` 			varchar(45) 	NOT NULL,
  `resenia` 		    varchar(2000) 	NOT NULL,
  `cod_estado`          int  			NOT NULL,
  PRIMARY KEY (`id_recomendacion`),
  KEY `fK_cat_recomendacion` (`id_categorias`),
  CONSTRAINT `fK_cat_recomendacion` FOREIGN KEY (`id_categorias`) REFERENCES `categorias_lugares` (`id_categorias`) ON DELETE CASCADE,
  CONSTRAINT `fK_depa_recomendacion`  FOREIGN KEY (`cod_departamento`) REFERENCES `departamentos` (`cod_departamento`) ON DELETE CASCADE,
  CONSTRAINT `fK_state_recomendacion`  FOREIGN KEY (`cod_estado`) REFERENCES `estados` (`cod_estado`) ON DELETE CASCADE
) ;