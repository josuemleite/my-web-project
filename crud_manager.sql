-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 08-Jun-2023 às 22:34
-- Versão do servidor: 8.0.27
-- versão do PHP: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `crud_manager`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `companies`
--

DROP TABLE IF EXISTS `companies`;
CREATE TABLE IF NOT EXISTS `companies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `role` varchar(128) NOT NULL,
  `start` date NOT NULL,
  `end` date DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `companies`
--

INSERT INTO `companies` (`id`, `name`, `role`, `start`, `end`, `user_id`) VALUES
(2, 'Avatech', 'TI', '2023-05-05', '2023-05-07', 3),
(3, 'Tesla', 'Automóvel', '2006-04-05', NULL, 4),
(4, 'Tupã', 'Hardware', '2023-06-08', NULL, 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `posts`
--

DROP TABLE IF EXISTS `posts`;
CREATE TABLE IF NOT EXISTS `posts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  `post_date` date NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `posts`
--

INSERT INTO `posts` (`id`, `content`, `post_date`, `user_id`) VALUES
(1, 'Olá do Emerson', '2023-05-06', 1),
(2, 'Olá da Luiza', '2023-05-06', 2),
(3, 'Olá da Denise', '2023-05-06', 3),
(4, 'Olá do Noé', '2023-05-06', 4),
(5, 'Olá da Rosânia', '2023-05-06', 5),
(6, 'Olá da Rosânia 1', '2023-05-06', 5),
(7, 'Olá da Rosânia 2', '2023-05-06', 5),
(8, 'Olá da Rosânia 3', '2023-05-06', 5),
(9, 'Hello World!', '2023-06-08', 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE IF NOT EXISTS `services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_of_service` varchar(128) NOT NULL,
  `value_per_hour` double NOT NULL,
  `expertise_level` varchar(128) NOT NULL,
  `portfolio` varchar(256) NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `services`
--

INSERT INTO `services` (`id`, `type_of_service`, `value_per_hour`, `expertise_level`, `portfolio`, `user_id`) VALUES
(4, 'Back-end', 200.5, 'Estagiário', 'github.com/devfalido', 6);

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `sexo` enum('M','F') DEFAULT NULL,
  `email` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `nome`, `sexo`, `email`) VALUES
(1, 'Emerson Carvalho', 'M', 'emerson@mail.com'),
(2, 'Luiza Carvalho', 'F', 'lu@mail.com'),
(3, 'Elenice Carvalho', 'F', 'le@mail.com'),
(4, 'Noé Carvalho', 'M', 'noe@mail.com'),
(5, 'Rosânia Carvalho', 'F', 'ro@mail.com'),
(6, 'Josué M. Leite', 'M', 'josue@email.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
