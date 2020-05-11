-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.10-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para raulbase
DROP DATABASE IF EXISTS `raulbase`;
CREATE DATABASE IF NOT EXISTS `raulbase` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `raulbase`;

-- Volcando estructura para tabla raulbase.instalacion
DROP TABLE IF EXISTS `instalacion`;
CREATE TABLE IF NOT EXISTS `instalacion` (
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `direccion` varchar(40) DEFAULT NULL,
  `cuota_mensual` float DEFAULT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.instalacion: ~1 rows (aproximadamente)
DELETE FROM `instalacion`;
/*!40000 ALTER TABLE `instalacion` DISABLE KEYS */;
INSERT INTO `instalacion` (`nombre`, `descripcion`, `direccion`, `cuota_mensual`) VALUES
	('Local1', 'Central', 'Comercio,72', 1000);
/*!40000 ALTER TABLE `instalacion` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.linea_pedido
DROP TABLE IF EXISTS `linea_pedido`;
CREATE TABLE IF NOT EXISTS `linea_pedido` (
  `id_linea_pedido` int(10) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `sTipo` varchar(15) DEFAULT NULL,
  `id_pedido` int(10) DEFAULT NULL,
  `nombre_producto` varchar(15) DEFAULT NULL,
  `nombre_material` varchar(15) DEFAULT NULL,
  `nombre_proveedor` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_linea_pedido`),
  KEY `id_pedido1` (`id_pedido`),
  KEY `nombre_proveedor` (`nombre_proveedor`),
  KEY `nombre_material1_fk` (`nombre_material`),
  KEY `nombre_producto_fk` (`nombre_producto`),
  CONSTRAINT `id_pedido1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nombre_material1_fk` FOREIGN KEY (`nombre_material`) REFERENCES `material` (`nombre_material`),
  CONSTRAINT `nombre_producto_fk` FOREIGN KEY (`nombre_producto`) REFERENCES `producto` (`nombre_producto`),
  CONSTRAINT `nombre_proveedor_fk` FOREIGN KEY (`nombre_proveedor`) REFERENCES `proveedor` (`nombre_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.linea_pedido: ~0 rows (aproximadamente)
DELETE FROM `linea_pedido`;
/*!40000 ALTER TABLE `linea_pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `linea_pedido` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.material
DROP TABLE IF EXISTS `material`;
CREATE TABLE IF NOT EXISTS `material` (
  `nombre_material` varchar(15) NOT NULL,
  `precio` float NOT NULL DEFAULT 0,
  `stock` int(3) NOT NULL DEFAULT 0,
  `tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`nombre_material`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.material: ~1 rows (aproximadamente)
DELETE FROM `material`;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` (`nombre_material`, `precio`, `stock`, `tipo`) VALUES
	('Cuchillo', 30, 10, 'COMPRA');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.metodo_pago
DROP TABLE IF EXISTS `metodo_pago`;
CREATE TABLE IF NOT EXISTS `metodo_pago` (
  `tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.metodo_pago: ~2 rows (aproximadamente)
DELETE FROM `metodo_pago`;
/*!40000 ALTER TABLE `metodo_pago` DISABLE KEYS */;
INSERT INTO `metodo_pago` (`tipo`) VALUES
	('efectivo'),
	('tarjeta');
/*!40000 ALTER TABLE `metodo_pago` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.pago
DROP TABLE IF EXISTS `pago`;
CREATE TABLE IF NOT EXISTS `pago` (
  `id_pago` int(10) NOT NULL,
  `fecha` date DEFAULT NULL,
  `tipo` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `tipo` FOREIGN KEY (`tipo`) REFERENCES `metodo_pago` (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.pago: ~29 rows (aproximadamente)
DELETE FROM `pago`;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` (`id_pago`, `fecha`, `tipo`) VALUES
	(1, '2020-05-06', 'efectivo'),
	(2, '2020-05-06', 'efectivo'),
	(3, '2020-05-07', 'efectivo'),
	(4, '2020-05-07', 'tarjeta'),
	(5, '2020-05-07', 'efectivo'),
	(6, '2020-05-07', 'efectivo'),
	(7, '2020-05-07', 'efectivo'),
	(8, '2020-05-07', 'efectivo'),
	(9, '2020-05-07', 'tarjeta'),
	(10, '2020-05-07', 'tarjeta'),
	(11, '2020-05-07', 'efectivo'),
	(12, '2020-05-07', 'tarjeta'),
	(13, '2020-05-07', 'efectivo'),
	(14, '2020-05-07', 'efectivo'),
	(15, '2020-05-07', 'tarjeta'),
	(16, '2020-05-07', 'tarjeta'),
	(17, '2020-05-07', 'tarjeta'),
	(18, '2020-05-10', 'efectivo'),
	(19, '2020-05-10', 'efectivo'),
	(20, '2020-05-10', 'efectivo'),
	(21, '2020-05-10', 'efectivo'),
	(22, '2020-05-10', 'efectivo'),
	(23, '2020-05-10', 'efectivo'),
	(24, '2020-05-10', 'efectivo'),
	(25, '2020-05-10', 'efectivo'),
	(26, '2020-05-10', 'efectivo'),
	(27, '2020-05-10', 'efectivo'),
	(28, '2020-05-10', 'efectivo'),
	(29, '2020-05-10', NULL);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.pedido
DROP TABLE IF EXISTS `pedido`;
CREATE TABLE IF NOT EXISTS `pedido` (
  `id_pedido` int(10) NOT NULL,
  `fecha` date DEFAULT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `id_pago` int(10) DEFAULT NULL,
  `nombre_instalacion` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `dni` (`dni`),
  KEY `nombre_instalacion` (`nombre_instalacion`),
  KEY `id_pago` (`id_pago`),
  CONSTRAINT `dni` FOREIGN KEY (`dni`) REFERENCES `usuario` (`dni`),
  CONSTRAINT `id_pago` FOREIGN KEY (`id_pago`) REFERENCES `pago` (`id_pago`),
  CONSTRAINT `nombre_instalacion` FOREIGN KEY (`nombre_instalacion`) REFERENCES `instalacion` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.pedido: ~31 rows (aproximadamente)
DELETE FROM `pedido`;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` (`id_pedido`, `fecha`, `dni`, `id_pago`, `nombre_instalacion`) VALUES
	(1, '2020-05-06', '49789078F', 1, 'Local1'),
	(2, '2020-05-06', '49789078F', NULL, 'Local1'),
	(3, '2020-05-06', '49789078F', 2, 'Local1'),
	(4, '2020-05-06', '49789078F', NULL, 'Local1'),
	(5, '2020-05-07', '49789078F', 3, 'Local1'),
	(6, '2020-05-07', '49789078F', NULL, 'Local1'),
	(7, '2020-05-07', '49789078F', 4, 'Local1'),
	(8, '2020-05-07', '49789078F', NULL, 'Local1'),
	(9, '2020-05-07', NULL, 7, 'Local1'),
	(10, '2020-05-07', NULL, 8, 'Local1'),
	(11, '2020-05-07', NULL, 9, 'Local1'),
	(12, '2020-05-07', NULL, 10, 'Local1'),
	(13, '2020-05-07', NULL, 11, 'Local1'),
	(14, '2020-05-07', NULL, 12, 'Local1'),
	(15, '2020-05-07', NULL, 13, 'Local1'),
	(16, '2020-05-07', NULL, 14, 'Local1'),
	(17, '2020-05-07', NULL, 15, 'Local1'),
	(18, '2020-05-07', NULL, 16, 'Local1'),
	(19, '2020-05-07', NULL, 17, 'Local1'),
	(20, '2020-05-10', NULL, 18, 'Local1'),
	(21, '2020-05-10', NULL, 19, 'Local1'),
	(22, '2020-05-10', NULL, 20, 'Local1'),
	(23, '2020-05-10', NULL, 21, 'Local1'),
	(24, '2020-05-10', NULL, 22, 'Local1'),
	(25, '2020-05-10', NULL, 23, 'Local1'),
	(26, '2020-05-10', NULL, 24, 'Local1'),
	(27, '2020-05-10', NULL, 25, 'Local1'),
	(28, '2020-05-10', NULL, 26, 'Local1'),
	(29, '2020-05-10', NULL, 27, 'Local1'),
	(30, '2020-05-10', NULL, 28, 'Local1'),
	(31, '2020-05-10', '49789078F', 29, 'Local1');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.producto
DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `nombre_producto` varchar(15) NOT NULL,
  `precio` float NOT NULL DEFAULT 0,
  `stock` int(3) NOT NULL DEFAULT 0,
  `tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`nombre_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.producto: ~5 rows (aproximadamente)
DELETE FROM `producto`;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`nombre_producto`, `precio`, `stock`, `tipo`) VALUES
	('Cerdo (Guiso) B', 3, 20, 'Derivados'),
	('Chipiron', 4, 20, 'Carta_comida'),
	('Cocacola', 2, 20, 'Carta_bebida'),
	('Fanta', 2, 20, 'Carta_bebida'),
	('Solomillo', 6, 20, 'Carta_comida');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.proveedor
DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE IF NOT EXISTS `proveedor` (
  `nombre_proveedor` varchar(30) NOT NULL,
  `telefono` int(9) DEFAULT NULL,
  `correo` varchar(30) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `nombre_tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`nombre_proveedor`),
  KEY `nombre_tipo` (`nombre_tipo`),
  CONSTRAINT `nombre_tipo` FOREIGN KEY (`nombre_tipo`) REFERENCES `tipo_proveedor` (`nombre_tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.proveedor: ~3 rows (aproximadamente)
DELETE FROM `proveedor`;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` (`nombre_proveedor`, `telefono`, `correo`, `direccion`, `nombre_tipo`) VALUES
	('Bernal', 87329098, 'Bernal@gmail.com', 'Mercasevilla,1', 'Alimentacion'),
	('Gastronomie', 654554321, 'Gastronomie@gmail.com', 'Gracia,22', 'Gastronomie'),
	('Ikea', 769211765, 'Ikea@gmail.com', 'El Pino,1', 'Materiales');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.proveedor_material
DROP TABLE IF EXISTS `proveedor_material`;
CREATE TABLE IF NOT EXISTS `proveedor_material` (
  `nombre_proveedor` varchar(30) NOT NULL,
  `nombre_material` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.proveedor_material: ~1 rows (aproximadamente)
DELETE FROM `proveedor_material`;
/*!40000 ALTER TABLE `proveedor_material` DISABLE KEYS */;
INSERT INTO `proveedor_material` (`nombre_proveedor`, `nombre_material`) VALUES
	('Ikea', 'Cuchillo');
/*!40000 ALTER TABLE `proveedor_material` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.proveedor_producto
DROP TABLE IF EXISTS `proveedor_producto`;
CREATE TABLE IF NOT EXISTS `proveedor_producto` (
  `nombre_producto` varchar(15) NOT NULL,
  `nombre_proveedor` varchar(30) NOT NULL,
  PRIMARY KEY (`nombre_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.proveedor_producto: ~1 rows (aproximadamente)
DELETE FROM `proveedor_producto`;
/*!40000 ALTER TABLE `proveedor_producto` DISABLE KEYS */;
INSERT INTO `proveedor_producto` (`nombre_producto`, `nombre_proveedor`) VALUES
	('Cerdo (Guiso) B', 'Bernal');
/*!40000 ALTER TABLE `proveedor_producto` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.tipo_proveedor
DROP TABLE IF EXISTS `tipo_proveedor`;
CREATE TABLE IF NOT EXISTS `tipo_proveedor` (
  `nombre_tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`nombre_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.tipo_proveedor: ~3 rows (aproximadamente)
DELETE FROM `tipo_proveedor`;
/*!40000 ALTER TABLE `tipo_proveedor` DISABLE KEYS */;
INSERT INTO `tipo_proveedor` (`nombre_tipo`) VALUES
	('Alimentacion'),
	('Gastronomie'),
	('Materiales');
/*!40000 ALTER TABLE `tipo_proveedor` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.tipo_usuario
DROP TABLE IF EXISTS `tipo_usuario`;
CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `nombre_tipo` varchar(500) NOT NULL,
  PRIMARY KEY (`nombre_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.tipo_usuario: ~2 rows (aproximadamente)
DELETE FROM `tipo_usuario`;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipo_usuario` (`nombre_tipo`) VALUES
	('Admin'),
	('Cliente');
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(20) DEFAULT NULL,
  `apellidos` varchar(20) DEFAULT NULL,
  `telefono` int(9) DEFAULT NULL,
  `correo` varchar(30) DEFAULT NULL,
  `nombre_usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`dni`),
  KEY `nombre_usuario` (`nombre_usuario`),
  CONSTRAINT `nombre_usuario` FOREIGN KEY (`nombre_usuario`) REFERENCES `tipo_usuario` (`nombre_tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.usuario: ~2 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`dni`, `nombre`, `apellidos`, `telefono`, `correo`, `nombre_usuario`) VALUES
	('49789078F', 'Raul', 'Lora', 873299056, 'lg@gmail.com', 'Admin'),
	('56432654D', 'Pera', 'Lope', 0, NULL, 'Cliente');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
