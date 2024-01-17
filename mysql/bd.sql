CREATE DATABASE  IF NOT EXISTS `inspeccion_smv` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `inspeccion_smv`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: inspeccion_smv
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actas`
--

DROP TABLE IF EXISTS `actas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actas` (
  `id_acta` int NOT NULL AUTO_INCREMENT,
  `tipo` int DEFAULT NULL,
  `destinatario` varchar(30) NOT NULL,
  `emisor` varchar(30) NOT NULL,
  `fecha` varchar(30) DEFAULT NULL,
  `documento` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_acta`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `actas_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `tipoactas` (`id_tipoacta`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actas`
--

LOCK TABLES `actas` WRITE;
/*!40000 ALTER TABLE `actas` DISABLE KEYS */;
INSERT INTO `actas` VALUES (1,1,'Elver','Peet','2023-06-27','Reglamento de Agentes de Intermediación');
/*!40000 ALTER TABLE `actas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `memorandum`
--

DROP TABLE IF EXISTS `memorandum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memorandum` (
  `id_memorandum` int NOT NULL AUTO_INCREMENT,
  `tipo` int DEFAULT NULL,
  `destinatario` varchar(30) DEFAULT NULL,
  `emisor` varchar(30) DEFAULT NULL,
  `asunto` varchar(30) DEFAULT NULL,
  `referencia` varchar(100) DEFAULT NULL,
  `fecha` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_memorandum`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `memorandum_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `tipomemorandun` (`id_tipomemorandum`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memorandum`
--

LOCK TABLES `memorandum` WRITE;
/*!40000 ALTER TABLE `memorandum` DISABLE KEYS */;
INSERT INTO `memorandum` VALUES (1,1,'Enzo','Anakin','Advertencia','Falta de Informacion','2023-06-27');
/*!40000 ALTER TABLE `memorandum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `observaciones`
--

DROP TABLE IF EXISTS `observaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `observaciones` (
  `id_observacion` int NOT NULL AUTO_INCREMENT,
  `fecha` varchar(30) NOT NULL,
  `observacion1` varchar(30) NOT NULL,
  `observacion2` varchar(30) NOT NULL,
  `observacion3` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id_observacion`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `observaciones`
--

LOCK TABLES `observaciones` WRITE;
/*!40000 ALTER TABLE `observaciones` DISABLE KEYS */;
INSERT INTO `observaciones` VALUES (1,'2023-06-25','Se requiere más información','Adjuntar documenos restantes','Revisar horario');
/*!40000 ALTER TABLE `observaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ordeninspeccion`
--

DROP TABLE IF EXISTS `tb_ordeninspeccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_ordeninspeccion` (
  `id_orden` int NOT NULL AUTO_INCREMENT,
  `numeroruc` char(11) NOT NULL,
  `nombreempresa` varchar(30) NOT NULL,
  `direccion` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `responsable` varchar(30) NOT NULL,
  PRIMARY KEY (`id_orden`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ordeninspeccion`
--

LOCK TABLES `tb_ordeninspeccion` WRITE;
/*!40000 ALTER TABLE `tb_ordeninspeccion` DISABLE KEYS */;
INSERT INTO `tb_ordeninspeccion` VALUES (1,'12345678912','API','Av.Argentina','api@gmial.com','Enzo Limay');
/*!40000 ALTER TABLE `tb_ordeninspeccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_proyecto_oficio`
--

DROP TABLE IF EXISTS `tb_proyecto_oficio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_proyecto_oficio` (
  `id_proyecto` int NOT NULL AUTO_INCREMENT,
  `id_orden` int DEFAULT NULL,
  `asunto` varchar(30) NOT NULL,
  `informacionrequerida` varchar(100) NOT NULL,
  `referencia` varchar(30) NOT NULL,
  PRIMARY KEY (`id_proyecto`),
  KEY `id_orden` (`id_orden`),
  CONSTRAINT `tb_proyecto_oficio_ibfk_1` FOREIGN KEY (`id_orden`) REFERENCES `tb_ordeninspeccion` (`id_orden`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_proyecto_oficio`
--

LOCK TABLES `tb_proyecto_oficio` WRITE;
/*!40000 ALTER TABLE `tb_proyecto_oficio` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_proyecto_oficio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_tipos`
--

DROP TABLE IF EXISTS `tb_tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_tipos` (
  `id_tipo` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_tipos`
--

LOCK TABLES `tb_tipos` WRITE;
/*!40000 ALTER TABLE `tb_tipos` DISABLE KEYS */;
INSERT INTO `tb_tipos` VALUES (1,'Jefe de Inspección TR'),(2,'Integrante de Inspección TR');
/*!40000 ALTER TABLE `tb_tipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_usuarios`
--

DROP TABLE IF EXISTS `tb_usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nom_user` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `username` char(5) NOT NULL,
  `clave` char(6) NOT NULL,
  `fec_acceso` date DEFAULT NULL,
  `tipo` int DEFAULT '2',
  `estado` int DEFAULT '1',
  PRIMARY KEY (`id_usuario`),
  KEY `tipo` (`tipo`),
  CONSTRAINT `tb_usuarios_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `tb_tipos` (`id_tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_usuarios`
--

LOCK TABLES `tb_usuarios` WRITE;
/*!40000 ALTER TABLE `tb_usuarios` DISABLE KEYS */;
INSERT INTO `tb_usuarios` VALUES (1,'Ernesto','Chávez','ERNCH','123456','2023-06-01',1,2),(2,'Adriana','Guiterrez','ADRIG','654321','2023-05-31',2,2);
/*!40000 ALTER TABLE `tb_usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoactas`
--

DROP TABLE IF EXISTS `tipoactas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipoactas` (
  `id_tipoacta` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(30) NOT NULL,
  PRIMARY KEY (`id_tipoacta`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoactas`
--

LOCK TABLES `tipoactas` WRITE;
/*!40000 ALTER TABLE `tipoactas` DISABLE KEYS */;
INSERT INTO `tipoactas` VALUES (1,'Inicio'),(9,'aaaa');
/*!40000 ALTER TABLE `tipoactas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipomemorandun`
--

DROP TABLE IF EXISTS `tipomemorandun`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipomemorandun` (
  `id_tipomemorandum` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(30) NOT NULL,
  PRIMARY KEY (`id_tipomemorandum`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipomemorandun`
--

LOCK TABLES `tipomemorandun` WRITE;
/*!40000 ALTER TABLE `tipomemorandun` DISABLE KEYS */;
INSERT INTO `tipomemorandun` VALUES (1,'Reiterativo'),(2,'Requerimiento');
/*!40000 ALTER TABLE `tipomemorandun` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'inspeccion_smv'
--

--
-- Dumping routines for database 'inspeccion_smv'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-15 12:33:48
