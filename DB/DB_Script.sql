UNLOCK TABLES;

-- CREATE FRESH DATABASE
DROP DATABASE IF EXISTS biblioteca;
CREATE DATABASE biblioteca;

USE biblioteca;

-- CREATE USER FOR THE PROGRAM
CREATE USER IF NOT EXISTS 'biblioteca'@'%' IDENTIFIED WITH mysql_native_password BY 'biblioteca';
GRANT ALL PRIVILEGES ON biblioteca.* TO 'biblioteca'@'%';

-- CREATE TABLES

-- ROLES
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `roles` WRITE;
INSERT INTO `roles` VALUES
	(1, 'Administrador','Encargado de la gestion del contenido'),
    (2, 'Profesor','Usuario con privilegios limitados'),
    (3, 'Alumno','Usuario con privilegios limitados');
UNLOCK TABLES;

-- PARAMETROS DE ROLES
DROP TABLE IF EXISTS `rolparams`;
CREATE TABLE `rolparams` (
  `rol` INT NOT NULL,
  `max_prestamos` INT NOT NULL,
  `max_dias` INT NOT NULL,
  `mora_diaria` FLOAT NOT NULL,
  UNIQUE KEY (`rol`),
  FOREIGN KEY (`rol`) REFERENCES `roles`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `rolparams` WRITE;
INSERT INTO `rolparams` VALUES
	(1, 10, 10, 0),
    (2, 4, 5, 0.25),
    (3, 3, 3, 0.5);
UNLOCK TABLES;

-- USUARIOS
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `passwd` varchar(100) NOT NULL,
  `rol` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`nombre`),
  FOREIGN KEY (`rol`) REFERENCES `roles`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `usuarios` WRITE;
INSERT INTO `usuarios` VALUES
	(1, 'admin', 'admin', 1);
UNLOCK TABLES;

-- LIBROS
DROP TABLE IF EXISTS `libros`;
CREATE TABLE `libros` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `anio_publicacion` int DEFAULT NULL,
  `edicion` int DEFAULT NULL,
  `unidades` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  `palabras_clave` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `libros` WRITE;
INSERT INTO `libros` VALUES
	(1,'Ciencias Naturales','Santillana','Educativo','Santillana','123-4-56-789456-1',2010,13,3,'4','ciencias, educacion');
UNLOCK TABLES;

-- OBRAS
DROP TABLE IF EXISTS `obras`;
CREATE TABLE `obras` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `anio_publicacion` int DEFAULT NULL,
  `edicion` int DEFAULT NULL,
  `unidades` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  `palabras_clave` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `obras` WRITE;
INSERT INTO `obras` VALUES
	(1,'Don Quijote','Miguel de Cervantes','Clasico','Santillana','123-4-56-789456-1',2010,13,3,'4','Clasico');
UNLOCK TABLES;

-- REVISTAS
DROP TABLE IF EXISTS `revistas`;
CREATE TABLE `revistas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `frecuencia` varchar(100) DEFAULT NULL,
  `issn` varchar(15) DEFAULT NULL,
  `tematica` varchar(100) DEFAULT NULL,
  `volumen` int DEFAULT NULL,
  `unidades` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  `palabras_clave` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `revistas` WRITE;
INSERT INTO `revistas` VALUES
	(1,'National Geographic','National Geograpic','Mensual','1234-5678','Ciencia',25,3,'2','Ciencia');
UNLOCK TABLES;

-- CD
DROP TABLE IF EXISTS `cds`;
CREATE TABLE `cds` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `autor` varchar(100) DEFAULT NULL,
  `genero` varchar(100) DEFAULT NULL,
  `anio_publicacion` int DEFAULT NULL,
  `duracion` int DEFAULT NULL,
  `unidades` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `cds` WRITE;
INSERT INTO `cds` VALUES
	(1,'Hibryd Theory','Linking Park','Alternativo',2006,130,5,'6');
UNLOCK TABLES;

-- TESIS
DROP TABLE IF EXISTS `tesis`;
CREATE TABLE `tesis` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `fecha_publicacion` varchar(100) DEFAULT NULL,
  `institucion` varchar(100) DEFAULT NULL,
  `facultad` varchar(100) DEFAULT NULL,
  `paginas` int DEFAULT NULL,
  `unidades` int DEFAULT NULL,
  `estante` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `tesis` WRITE;
INSERT INTO `tesis` VALUES
	(1,'Tesis 1','Enero 2010','Universidad Don Bosco','Ingenieria',200,1,'1');
UNLOCK TABLES;

-- PRESTAMOS
DROP TABLE IF EXISTS `prestamos_libros`;
CREATE TABLE `prestamos_libros` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `fecha_prestamo` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `fecha_devolucion` TIMESTAMP DEFAULT NULL,
  `libro` INT NOT NULL,
  `fecha_devuelto` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`libro`) REFERENCES `libros`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `prestamos_obras`;
CREATE TABLE `prestamos_obras` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `fecha_prestamo` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `fecha_devolucion` TIMESTAMP DEFAULT NULL,
  `obra` INT NOT NULL,
  `fecha_devuelto` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`obra`) REFERENCES `obras`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `prestamos_revistas`;
CREATE TABLE `prestamos_revistas` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `fecha_prestamo` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `fecha_devolucion` TIMESTAMP DEFAULT NULL,
  `revista` INT NOT NULL,
  `fecha_devuelto` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`revista`) REFERENCES `revistas`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `prestamos_cds`;
CREATE TABLE `prestamos_cds` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `fecha_prestamo` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `fecha_devolucion` TIMESTAMP DEFAULT NULL,
  `cd` INT NOT NULL,
  `fecha_devuelto` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`cd`) REFERENCES `cds`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `prestamos_tesis`;
CREATE TABLE `prestamos_tesis` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` INT NOT NULL,
  `fecha_prestamo` TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `fecha_devolucion` TIMESTAMP DEFAULT NULL,
  `tesis` INT NOT NULL,
  `fecha_devuelto` TIMESTAMP DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`tesis`) REFERENCES `tesis`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;