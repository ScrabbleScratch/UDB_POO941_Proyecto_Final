-- CREATE FRESH DATABASE
DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca;

USE biblioteca;

-- CREATE USER FOR THE PROGRAM
CREATE USER IF NOT EXISTS 'biblioteca'@'%' IDENTIFIED WITH mysql_native_password BY 'biblioteca';
GRANT ALL PRIVILEGES ON biblioteca.* TO 'biblioteca'@'%';

-- CREATE TABLES

-- USUARIOS
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `passwd` varchar(100) NOT NULL,
  `tipo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` VALUES
	(1,'admin','admin',0),
    (2,'AA230040','123456',1);
UNLOCK TABLES;

-- LIBROS
DROP TABLE IF EXISTS `libros`;
CREATE TABLE `libros` (
  `codigo` varchar(8) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `anio_publicacion` int DEFAULT NULL,
  `edicion` int DEFAULT NULL,
  `unidades` int DEFAULT NULL,
  `disponible` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  `palabras_clave` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `libros` WRITE;
INSERT INTO `libros` VALUES
	('LIB12345','Ciencias Naturales','Santillana','Educativo','Santillana','123-4-56-789456-1',2010,13,3,1,'4','ciencias, educacion');
UNLOCK TABLES;

-- OBRAS
DROP TABLE IF EXISTS `obras`;
CREATE TABLE `obras` (
  `codigo` varchar(8) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `anio_publicacion` int DEFAULT NULL,
  `edicion` int DEFAULT NULL,
  `unidades` int DEFAULT NULL,
  `disponible` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  `palabras_clave` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `obras` WRITE;
INSERT INTO `obras` VALUES
	('LIB12345','Don Quijote','Miguel de Cervantes','Clasico','Santillana','123-4-56-789456-1',2010,13,3,1,'4','Clasico');
UNLOCK TABLES;

-- REVISTAS
DROP TABLE IF EXISTS `revistas`;
CREATE TABLE `revistas` (
  `codigo` varchar(8) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `frecuencia` varchar(100) DEFAULT NULL,
  `issn` varchar(15) DEFAULT NULL,
  `tematica` varchar(100) DEFAULT NULL,
  `volumen` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  `disponible` int DEFAULT NULL,
  `palabras_clave` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `revistas` WRITE;
INSERT INTO `revistas` VALUES
	('REV12345','National Geographic','National Geograpic','Mensual','1234-5678','Ciencia',25,'2',1,'Ciencia');
UNLOCK TABLES;

-- CD
DROP TABLE IF EXISTS `cds`;
CREATE TABLE `cds` (
  `codigo` varchar(8) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `anio_publicacion` int DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  `disponible` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `cds` WRITE;
INSERT INTO `cds` VALUES
	('CD123456','Hibryd Theory','Linking Park','Alternativo',2006,130,1,'6');
UNLOCK TABLES;

-- TESIS
DROP TABLE IF EXISTS `tesis`;
CREATE TABLE `tesis` (
  `codigo` varchar(8) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `fecha_publicacion` varchar(100) DEFAULT NULL,
  `institucion` varchar(100) DEFAULT NULL,
  `facultad` varchar(100) DEFAULT NULL,
  `paginas` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  `disponible` int DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tesis` WRITE;
INSERT INTO `tesis` VALUES
	('T1234567','Tesis 1','Enero 2010','Universidad Don Bosco','Ingenieria',200,'1',1);
UNLOCK TABLES;