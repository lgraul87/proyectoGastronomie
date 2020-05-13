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
CREATE DATABASE IF NOT EXISTS `raulbase` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `raulbase`;

-- Volcando estructura para tabla raulbase.instalacion
CREATE TABLE IF NOT EXISTS `instalacion` (
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `direccion` varchar(40) NOT NULL,
  `cuota_mensual` float NOT NULL,
  PRIMARY KEY (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.instalacion: ~1 rows (aproximadamente)
DELETE FROM `instalacion`;
/*!40000 ALTER TABLE `instalacion` DISABLE KEYS */;
INSERT INTO `instalacion` (`nombre`, `descripcion`, `direccion`, `cuota_mensual`) VALUES
	('Local1', 'Central', 'Comercio,72', 1000);
/*!40000 ALTER TABLE `instalacion` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.linea_pedido
CREATE TABLE IF NOT EXISTS `linea_pedido` (
  `id_linea_pedido` int(10) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `sTipo` varchar(30) NOT NULL,
  `id_pedido` int(10) DEFAULT NULL,
  `nombre_producto` varchar(15) DEFAULT NULL,
  `nombre_material` varchar(15) DEFAULT NULL,
  `nombre_proveedor` varchar(30) NOT NULL,
  PRIMARY KEY (`id_linea_pedido`),
  KEY `id_pedido1` (`id_pedido`),
  KEY `nombre_proveedor` (`nombre_proveedor`),
  KEY `nombre_material1_fk` (`nombre_material`),
  KEY `nombre_producto_fk` (`nombre_producto`),
  CONSTRAINT `id_pedido1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nombre_material1_fk` FOREIGN KEY (`nombre_material`) REFERENCES `material` (`nombre_material`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nombre_producto_fk` FOREIGN KEY (`nombre_producto`) REFERENCES `producto` (`nombre_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nombre_proveedor_fk` FOREIGN KEY (`nombre_proveedor`) REFERENCES `proveedor` (`nombre_proveedor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.linea_pedido: ~17 rows (aproximadamente)
DELETE FROM `linea_pedido`;
/*!40000 ALTER TABLE `linea_pedido` DISABLE KEYS */;
INSERT INTO `linea_pedido` (`id_linea_pedido`, `cantidad`, `sTipo`, `id_pedido`, `nombre_producto`, `nombre_material`, `nombre_proveedor`) VALUES
	(1, 1, 'Venta', 9, 'Chipiron', NULL, 'Gastronomie'),
	(2, 10, 'Venta', 10, 'Cocacola', NULL, 'Gastronomie'),
	(3, 1, 'Venta', 11, 'Cocacola', NULL, 'Gastronomie'),
	(4, 1, 'Venta', 12, 'Cocacola', NULL, 'Gastronomie'),
	(5, 1, 'Venta', 13, 'Cocacola', NULL, 'Gastronomie'),
	(6, 20, 'Compra', NULL, 'Cocacola', NULL, 'Gastronomie'),
	(7, 1, 'Venta', 14, 'Cocacola', NULL, 'Gastronomie'),
	(8, 20, 'Compra', NULL, 'Cocacola', NULL, 'Gastronomie'),
	(9, 10, 'Venta', 15, 'Chipiron', NULL, 'Gastronomie'),
	(10, 20, 'Compra', NULL, 'Chipiron', NULL, 'Gastronomie'),
	(11, 1, 'Venta', 16, 'Cocacola', NULL, 'Gastronomie'),
	(12, 1, 'Venta', 17, 'Cocacola', NULL, 'Gastronomie'),
	(13, 20, 'Compra', NULL, 'Cocacola', NULL, 'Gastronomie'),
	(14, 1, 'Venta', 18, 'Cocacola', NULL, 'Gastronomie'),
	(15, 1, 'Venta', 19, 'Cocacola', NULL, 'Gastronomie'),
	(16, 20, 'Compra', NULL, 'Cocacola', NULL, 'Gastronomie'),
	(17, 1, 'Venta', 20, 'Chipiron', NULL, 'Gastronomie'),
	(18, 20, 'Compra', NULL, 'Chipiron', NULL, 'Gastronomie');
/*!40000 ALTER TABLE `linea_pedido` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.material
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
	('Cuchillo', 1, 1, 'COMPRA');
/*!40000 ALTER TABLE `material` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.metodo_pago
CREATE TABLE IF NOT EXISTS `metodo_pago` (
  `tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.metodo_pago: ~2 rows (aproximadamente)
DELETE FROM `metodo_pago`;
/*!40000 ALTER TABLE `metodo_pago` DISABLE KEYS */;
INSERT INTO `metodo_pago` (`tipo`) VALUES
	('Efectivo'),
	('Tarjeta');
/*!40000 ALTER TABLE `metodo_pago` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.pago
CREATE TABLE IF NOT EXISTS `pago` (
  `id_pago` int(10) NOT NULL,
  `fecha` date NOT NULL,
  `tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `tipo` FOREIGN KEY (`tipo`) REFERENCES `metodo_pago` (`tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.pago: ~28 rows (aproximadamente)
DELETE FROM `pago`;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` (`id_pago`, `fecha`, `tipo`) VALUES
	(1, '2020-05-12', 'Efectivo'),
	(3, '2020-05-12', 'Efectivo'),
	(4, '2020-05-12', 'Tarjeta'),
	(5, '2020-05-12', 'efectivo'),
	(6, '2020-05-12', 'tarjeta'),
	(7, '2020-05-12', 'tarjeta'),
	(8, '2020-05-12', 'tarjeta'),
	(9, '2020-05-13', 'Efectivo'),
	(10, '2020-05-13', 'Efectivo'),
	(11, '2020-05-13', 'Tarjeta'),
	(12, '2020-05-13', 'Tarjeta'),
	(13, '2020-05-13', 'Tarjeta'),
	(14, '2020-05-13', 'Tarjeta'),
	(15, '2020-05-13', 'Tarjeta'),
	(16, '2020-05-13', 'Tarjeta'),
	(17, '2020-05-13', 'Efectivo'),
	(18, '2020-05-13', 'efectivo'),
	(19, '2020-05-13', 'Efectivo'),
	(20, '2020-05-13', 'Efectivo'),
	(21, '2020-05-13', 'Efectivo'),
	(22, '2020-05-13', 'Efectivo'),
	(23, '2020-05-13', 'Tarjeta'),
	(24, '2020-05-13', 'efectivo'),
	(25, '2020-05-13', 'tarjeta'),
	(26, '2020-05-13', 'Efectivo'),
	(27, '2020-05-13', 'tarjeta'),
	(28, '2020-05-13', 'Efectivo'),
	(29, '2020-05-13', 'tarjeta');
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.pedido
CREATE TABLE IF NOT EXISTS `pedido` (
  `id_pedido` int(10) NOT NULL,
  `fecha` date NOT NULL,
  `dni` varchar(9) NOT NULL,
  `id_pago` int(10) DEFAULT NULL,
  `nombre_instalacion` varchar(30) NOT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `dni` (`dni`),
  KEY `id_pago` (`id_pago`),
  KEY `nombre_instalacion` (`nombre_instalacion`),
  CONSTRAINT `dni` FOREIGN KEY (`dni`) REFERENCES `usuario` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `id_pago` FOREIGN KEY (`id_pago`) REFERENCES `pago` (`id_pago`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nombre_instalacion` FOREIGN KEY (`nombre_instalacion`) REFERENCES `instalacion` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.pedido: ~20 rows (aproximadamente)
DELETE FROM `pedido`;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` (`id_pedido`, `fecha`, `dni`, `id_pago`, `nombre_instalacion`) VALUES
	(1, '2020-05-12', '45808798C', 5, 'Local1'),
	(2, '2020-05-12', '23987098D', 6, 'Local1'),
	(3, '2020-05-12', '23987098D', 7, 'Local1'),
	(4, '2020-05-12', '23987098D', 8, 'Local1'),
	(5, '2020-05-13', '123456789', 14, 'Local1'),
	(6, '2020-05-13', '123456789', 15, 'Local1'),
	(7, '2020-05-13', '123456789', 16, 'Local1'),
	(8, '2020-05-13', '123456789', 17, 'Local1'),
	(9, '2020-05-13', '123456789', 18, 'Local1'),
	(10, '2020-05-13', '123456789', 19, 'Local1'),
	(11, '2020-05-13', '123456789', 20, 'Local1'),
	(12, '2020-05-13', '123456789', 21, 'Local1'),
	(13, '2020-05-13', '123456789', 22, 'Local1'),
	(14, '2020-05-13', '123456789', 23, 'Local1'),
	(15, '2020-05-13', '123456789', 24, 'Local1'),
	(16, '2020-05-13', '123456789', 25, 'Local1'),
	(17, '2020-05-13', '123456789', 26, 'Local1'),
	(18, '2020-05-13', '123456789', 27, 'Local1'),
	(19, '2020-05-13', '123456789', 28, 'Local1'),
	(20, '2020-05-13', '123456789', 29, 'Local1');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.producto
CREATE TABLE IF NOT EXISTS `producto` (
  `nombre_producto` varchar(15) NOT NULL,
  `precio` float NOT NULL DEFAULT 0,
  `stock` int(3) NOT NULL DEFAULT 0,
  `tipo` varchar(30) NOT NULL,
  PRIMARY KEY (`nombre_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.producto: ~3 rows (aproximadamente)
DELETE FROM `producto`;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`nombre_producto`, `precio`, `stock`, `tipo`) VALUES
	('Cerdo (Guiso)', 1, 20, 'Derivados'),
	('Chipiron', 1, 6, 'Carta_comida'),
	('Cocacola', 1, 2, 'Carta_bebida');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.proveedor
CREATE TABLE IF NOT EXISTS `proveedor` (
  `nombre_proveedor` varchar(30) NOT NULL,
  `telefono` int(9) NOT NULL,
  `correo` varchar(30) DEFAULT NULL,
  `direccion` varchar(50) NOT NULL,
  `nombre_tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`nombre_proveedor`),
  KEY `nombre_tipo` (`nombre_tipo`),
  CONSTRAINT `nombre_tipo` FOREIGN KEY (`nombre_tipo`) REFERENCES `tipo_proveedor` (`nombre_tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.proveedor: ~3 rows (aproximadamente)
DELETE FROM `proveedor`;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` (`nombre_proveedor`, `telefono`, `correo`, `direccion`, `nombre_tipo`) VALUES
	('Gastronomie', 654554321, 'Gastronomie@gmail.com', 'Comercio 73', 'Construccion'),
	('Leroy', 324677876, 'leroy@', 'Pino,2', 'Construccion'),
	('Merlin', 567322987, NULL, 'Pino,1', 'Construccion');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.proveedor_material
CREATE TABLE IF NOT EXISTS `proveedor_material` (
  `nombre_proveedor` varchar(30) NOT NULL,
  `nombre_material` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.proveedor_material: ~1 rows (aproximadamente)
DELETE FROM `proveedor_material`;
/*!40000 ALTER TABLE `proveedor_material` DISABLE KEYS */;
INSERT INTO `proveedor_material` (`nombre_proveedor`, `nombre_material`) VALUES
	('Leroy', 'Cuchillo');
/*!40000 ALTER TABLE `proveedor_material` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.proveedor_producto
CREATE TABLE IF NOT EXISTS `proveedor_producto` (
  `nombre_proveedor` varchar(30) NOT NULL,
  `nombre_producto` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.proveedor_producto: ~5 rows (aproximadamente)
DELETE FROM `proveedor_producto`;
/*!40000 ALTER TABLE `proveedor_producto` DISABLE KEYS */;
INSERT INTO `proveedor_producto` (`nombre_proveedor`, `nombre_producto`) VALUES
	('Merlin', 'Chipiron'),
	('Merlin', 'Cocacola'),
	('Merlin', 'Cerdo (Guiso)'),
	('Bernal', 'Fanta'),
	('Bernal', '7up');
/*!40000 ALTER TABLE `proveedor_producto` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.tipo_proveedor
CREATE TABLE IF NOT EXISTS `tipo_proveedor` (
  `nombre_tipo` varchar(15) NOT NULL,
  PRIMARY KEY (`nombre_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.tipo_proveedor: ~3 rows (aproximadamente)
DELETE FROM `tipo_proveedor`;
/*!40000 ALTER TABLE `tipo_proveedor` DISABLE KEYS */;
INSERT INTO `tipo_proveedor` (`nombre_tipo`) VALUES
	('Alimentacion'),
	('Construccion'),
	('Gastronomie');
/*!40000 ALTER TABLE `tipo_proveedor` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.tipo_usuario
CREATE TABLE IF NOT EXISTS `tipo_usuario` (
  `nombre_tipo` varchar(10) NOT NULL,
  PRIMARY KEY (`nombre_tipo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.tipo_usuario: ~7 rows (aproximadamente)
DELETE FROM `tipo_usuario`;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipo_usuario` (`nombre_tipo`) VALUES
	('Admin'),
	('Cliente'),
	('Herman'),
	('Jefa'),
	('qw'),
	('saw'),
	('ser');
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;

-- Volcando estructura para tabla raulbase.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(20) NOT NULL,
  `telefono` int(9) NOT NULL,
  `correo` varchar(30) DEFAULT NULL,
  `contrasenia` varchar(250) NOT NULL,
  `nombre_usuario` varchar(10) NOT NULL,
  PRIMARY KEY (`dni`),
  KEY `FK_usuario_tipo_usuario` (`nombre_usuario`),
  CONSTRAINT `FK_usuario_tipo_usuario` FOREIGN KEY (`nombre_usuario`) REFERENCES `tipo_usuario` (`nombre_tipo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla raulbase.usuario: ~6 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`dni`, `nombre`, `apellidos`, `telefono`, `correo`, `contrasenia`, `nombre_usuario`) VALUES
	('123456789', 'Cliente', 'Cliente', 0, NULL, 'f18f1a9ae2d57e370912076950ad004b72fe60c79103f08f6a1daa4006ca167dc4481f0d5ad04185534452d34956152a948ec5894a10b9b5dda266041337b084', 'Cliente'),
	('23098798T', 'alexio', 'lor', 437688976, 'alexio@', '1a1d6c589a976eff369e47a3de1c803a8c0c1e09303db9509a3f86db3a300f373a91d02cdaa0aab5eac8092cfce02527e2f7f17a0ab79ec6cbe9e57f15830962', 'ser'),
	('23987098D', 'Esty', 'lor', 0, NULL, '99581f436313ce5f1a9db17b8f0d01d08e313dab0d093ab7f3e57836c1113babecdc7fb84c85535ea267cf7f1cb541bd00590aa0a00fa66e92f2efae550751c0', 'Jefa'),
	('34908798V', 'gret', 'lor', 657833287, NULL, '24e242422fee97e3ff619ee3c440c83ad68dfd3c7ddeed612b9ad87eda9cca79f11e999093d24a7847b8651432cebf693d2a787ca8c02cf12bd89f70cac69aeb', 'qw'),
	('45808798C', 'Raul', 'Lora', 659366732, 'Raul@gmail.com', 'd49163640b91e9b486fba55f7bbee6234c86865f5eddbbce4793ee52fb84f12166ab379b12268abfe8155691e633a4ebc2b2fde1efac483d02faea6e7c4ce993', 'Admin'),
	('45980789D', 'Terew', 'der', 0, NULL, 'f6f1005953a1e2db97debe79358cba4bce64be9f84085d266c7040a9d4d281b56fd7acb2f9086606dbed2ad17e38c348a5ac6af66549eaef2f2fe6756784b677', 'saw');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
