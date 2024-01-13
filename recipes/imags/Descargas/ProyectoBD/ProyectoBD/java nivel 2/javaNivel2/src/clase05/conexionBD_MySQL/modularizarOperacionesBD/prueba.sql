
SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Base de datos: `prueba`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `id_ciudad` int(3) NOT NULL,
  `ciudad` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcar la base de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id_ciudad`, `ciudad`) VALUES
(1, 'caracas'),
(2, 'maracay');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `titulo` varchar(30) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcar la base de datos para la tabla `libros`
--

INSERT INTO `libros` (`titulo`) VALUES
('Programación Objetos'),
('Java2D'),
('Java3D'),
('');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parroquia`
--

CREATE TABLE `parroquia` (
  `id_parroquia` int(3) NOT NULL,
  `id_ciudad` int(3) NOT NULL,
  `parroquia` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Volcar la base de datos para la tabla `parroquia`
--

INSERT INTO `parroquia` (`id_parroquia`, `id_ciudad`, `parroquia`) VALUES
(1, 1, 'caricuao'),
(2, 1, '23 de enero'),
(3, 2, 'las delicias'),
(4, 2, 'centro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla`
--

CREATE TABLE `tabla` (
  `nombre` varchar(30) collate utf8_spanish_ci NOT NULL,
  `apellido` varchar(30) collate utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcar la base de datos para la tabla `tabla`
--

INSERT INTO `tabla` (`nombre`, `apellido`) VALUES
('Jose', 'Sanchez'),
('Angel ', 'Gil');
