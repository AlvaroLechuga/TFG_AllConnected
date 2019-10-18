-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-07-2019 a las 14:20:45
-- Versión del servidor: 10.1.35-MariaDB
-- Versión de PHP: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `red_social`
--
CREATE DATABASE IF NOT EXISTS `red_social` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `red_social`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorys`
--

CREATE TABLE `categorys` (
  `id` int(255) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categorys`
--

INSERT INTO `categorys` (`id`, `name`) VALUES
(1, 'Política'),
(2, 'Deportes'),
(3, 'Pruebas'),
(4, 'Educación');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `follows`
--

CREATE TABLE `follows` (
  `id` int(255) NOT NULL,
  `user` int(255) NOT NULL,
  `followed` int(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `follows`
--

INSERT INTO `follows` (`id`, `user`, `followed`, `updated_at`, `created_at`) VALUES
(1, 4, 5, '2019-06-11 00:00:00', '2019-06-02 00:00:00'),
(2, 5, 4, '2019-06-11 00:00:00', '2019-06-02 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `likes`
--

CREATE TABLE `likes` (
  `id` int(255) NOT NULL,
  `id_publication` int(255) NOT NULL,
  `id_user_emmiter` int(255) NOT NULL,
  `id_user_reciver` int(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `messages`
--

CREATE TABLE `messages` (
  `id` int(255) NOT NULL,
  `emmiter` int(255) NOT NULL,
  `reciver` int(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `noticies`
--

CREATE TABLE `noticies` (
  `id` int(255) NOT NULL,
  `title` varchar(25) NOT NULL,
  `id_category` int(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `id_user` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `noticies`
--

INSERT INTO `noticies` (`id`, `title`, `id_category`, `text`, `image`, `id_user`) VALUES
(6, 'Test', 1, 'asdas.jpg', 'Vamos a nuevas elecciones', 4),
(7, 'Test de modificar', 4, 'Prueba', 'test', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notifications`
--

CREATE TABLE `notifications` (
  `id` int(255) NOT NULL,
  `id_user_emmit` int(255) NOT NULL,
  `id_user_recep` int(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publications`
--

CREATE TABLE `publications` (
  `id` int(255) NOT NULL,
  `id_user` int(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `response_id` int(255) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `publications`
--

INSERT INTO `publications` (`id`, `id_user`, `text`, `response_id`, `created_at`, `updated_at`) VALUES
(1, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:06:04', '2019-05-08 14:06:04'),
(2, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:08:40', '2019-05-08 14:08:40'),
(3, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:09:11', '2019-05-08 14:09:11'),
(4, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:09:48', '2019-05-08 14:09:48'),
(5, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:16:27', '2019-05-08 14:16:27'),
(6, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:17:58', '2019-05-08 14:17:58'),
(7, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:18:16', '2019-05-08 14:18:16'),
(8, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:18:28', '2019-05-08 14:18:28'),
(9, 3, 'Hola esto es una prueba', NULL, '2019-05-08 14:20:35', '2019-05-08 14:20:35'),
(10, 3, 'Hola esto es una prueba', NULL, '2019-05-08 18:52:20', '2019-05-08 18:52:20'),
(11, 3, 'Hola esto es una prueba', NULL, '2019-05-08 18:53:02', '2019-05-08 18:53:02'),
(12, 3, 'Hola esto es una prueba', NULL, '2019-05-08 19:03:58', '2019-05-08 19:03:58'),
(13, 4, 'Hola esto es una prueba', NULL, '2019-05-23 14:44:09', '2019-05-23 14:44:09'),
(17, 4, 'Hola buenas tardes, ¿Qué tal todo?', NULL, '2019-05-31 14:34:53', '2019-05-31 14:34:53'),
(18, 3, 'asdasdad', NULL, '0000-00-00 00:00:00', '0000-00-00 00:00:00'),
(19, 4, 'Hola esto es una prueba ahora', NULL, '2019-06-27 09:26:56', '2019-06-27 09:26:56'),
(20, 4, 'sdasda', NULL, '2019-06-27 09:29:13', '2019-06-27 09:29:13'),
(21, 4, 'Hola esto es una prueba ahora', 5, '2019-06-27 09:33:43', '2019-06-27 09:33:43'),
(22, 5, 'sdasadsdasdaa', NULL, '2019-06-27 00:00:00', '2019-06-27 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `direction` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `birthday` datetime NOT NULL,
  `nick` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(100) NOT NULL,
  `description` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `updated_at` datetime NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `name`, `surname`, `direction`, `country`, `birthday`, `nick`, `email`, `password`, `role`, `description`, `image`, `updated_at`, `created_at`) VALUES
(3, 'aaa', 'jajaj', 'Jerez', 'Spain', '1998-11-19 00:00:00', 'Pruebas', 'alvaro@alvaro.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'user', '', 'default.png', '2019-05-06 22:03:49', '2019-05-05 15:54:18'),
(4, 'alvaro', 'Lechuga', 'Jerez', 'España', '1998-11-19 00:00:00', 'Lechu', 'alvarolechugahierro@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'admin', 'Hola mi nombre es Alvaro Contacta conmigo si necesitas algo', '155855157027a03900e09d13f67086b091c997c33f.jpg', '2019-05-22 18:59:34', '2019-05-12 19:06:02'),
(5, 'sdasda', 'sdasda', 'sdasda', 'España', '1994-05-11 00:00:00', 'dssdasaddas', 'sadasda@gmail.com', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 'user', '', 'default.png', '2019-06-27 10:34:39', '2019-06-27 10:34:39'),
(6, 'Jesús', 'Lechuga', 'Jerez de la Frontera', 'España', '1111-11-11 00:00:00', 'dssdasaddas', 'asd@gmail.com', '688787d8ff144c502c7f5cffaafe2cc588d86079f9de88304c26b0cb99ce91c6', 'user', '', 'default.png', '2019-06-27 10:35:19', '2019-06-27 10:35:19');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorys`
--
ALTER TABLE `categorys`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_follows_user` (`user`),
  ADD KEY `fk_follows_followed` (`followed`);

--
-- Indices de la tabla `likes`
--
ALTER TABLE `likes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_likes_message` (`id_publication`),
  ADD KEY `fk_likes_userEmmiter` (`id_user_emmiter`),
  ADD KEY `fk_likes_userReciver` (`id_user_reciver`);

--
-- Indices de la tabla `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_messages_emmiter` (`emmiter`),
  ADD KEY `fk_messages_reciver` (`reciver`);

--
-- Indices de la tabla `noticies`
--
ALTER TABLE `noticies`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_category` (`id_category`);

--
-- Indices de la tabla `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_likes_emmit` (`id_user_emmit`),
  ADD KEY `fk_likes_recep` (`id_user_recep`);

--
-- Indices de la tabla `publications`
--
ALTER TABLE `publications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_publications_user` (`id_user`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorys`
--
ALTER TABLE `categorys`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `follows`
--
ALTER TABLE `follows`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `likes`
--
ALTER TABLE `likes`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `noticies`
--
ALTER TABLE `noticies`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `publications`
--
ALTER TABLE `publications`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `follows`
--
ALTER TABLE `follows`
  ADD CONSTRAINT `fk_follows_followed` FOREIGN KEY (`followed`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `fk_follows_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `likes`
--
ALTER TABLE `likes`
  ADD CONSTRAINT `fk_likes_message` FOREIGN KEY (`id_publication`) REFERENCES `publications` (`id`),
  ADD CONSTRAINT `fk_likes_userEmmiter` FOREIGN KEY (`id_user_emmiter`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `fk_likes_userReciver` FOREIGN KEY (`id_user_reciver`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `fk_messages_emmiter` FOREIGN KEY (`emmiter`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `fk_messages_reciver` FOREIGN KEY (`reciver`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `noticies`
--
ALTER TABLE `noticies`
  ADD CONSTRAINT `noticies_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `categorys` (`id`),
  ADD CONSTRAINT `noticies_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `fk_likes_emmit` FOREIGN KEY (`id_user_emmit`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `fk_likes_recep` FOREIGN KEY (`id_user_recep`) REFERENCES `users` (`id`);

--
-- Filtros para la tabla `publications`
--
ALTER TABLE `publications`
  ADD CONSTRAINT `fk_publications_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
