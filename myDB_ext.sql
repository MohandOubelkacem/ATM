-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  Dim 31 mars 2019 à 19:02
-- Version du serveur :  10.1.37-MariaDB
-- Version de PHP :  7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `myDB_ext`
--

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

CREATE TABLE `carte` (
  `ref_carte` char(30) CHARACTER SET latin1 NOT NULL,
  `numero_carte` char(10) COLLATE latin1_general_ci NOT NULL,
  `code` char(100) COLLATE latin1_general_ci NOT NULL,
  `modele` char(15) COLLATE latin1_general_ci NOT NULL,
  `date_expir` date NOT NULL,
  `num_client` char(30) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `carte`
--

INSERT INTO `carte` (`ref_carte`, `numero_carte`, `code`, `modele`, `date_expir`, `num_client`) VALUES
('123456782', '123456789', '2363', 'visa', '2019-12-10', '3900435702');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `num` char(30) COLLATE latin1_general_ci NOT NULL,
  `nom` char(30) COLLATE latin1_general_ci NOT NULL,
  `prenom` char(30) COLLATE latin1_general_ci NOT NULL,
  `dtNaiss` date NOT NULL,
  `email` char(40) COLLATE latin1_general_ci NOT NULL,
  `adresse` char(100) COLLATE latin1_general_ci NOT NULL,
  `profession` char(30) COLLATE latin1_general_ci DEFAULT NULL,
  `numero_tel` char(13) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`num`, `nom`, `prenom`, `dtNaiss`, `email`, `adresse`, `profession`, `numero_tel`) VALUES
('3900435702', 'Bouredjioua', 'Sofiane', '1996-01-20', 'bouredjiouasofiane@gmail.com', '31 avenue pasteur lyon', 'etudiant', '0695495703');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `num_compte` char(30) COLLATE latin1_general_ci NOT NULL,
  `solde` float NOT NULL,
  `seuil` float DEFAULT NULL,
  `seuilQuot` float NOT NULL,
  `seuilHebdo` float NOT NULL,
  `RIB` char(30) COLLATE latin1_general_ci NOT NULL,
  `IBAN` char(40) COLLATE latin1_general_ci DEFAULT NULL,
  `num_client` char(30) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`num_compte`, `solde`, `seuil`, `seuilQuot`, `seuilHebdo`, `RIB`, `IBAN`, `num_client`) VALUES
('6296526350', 340, 0, 290, 690, '30004008170000178671287', 'FR7630004008170000178671287', '3900435702');

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `date_oprt` datetime NOT NULL,
  `somme` float NOT NULL,
  `action_oprt` char(15) COLLATE latin1_general_ci NOT NULL,
  `num_client` char(30) COLLATE latin1_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`date_oprt`, `somme`, `action_oprt`, `num_client`) VALUES
('2019-03-06 02:39:28', 20, 'retrait', '3900435702'),
('2019-03-06 03:22:24', 0, 'Impression_RIB', '3900435702'),
('2019-03-06 03:23:43', 0, 'Impression_RIB', '3900435702'),
('2019-03-06 03:24:26', 0, 'Impression_RIB', '3900435702'),
('2019-03-08 02:53:57', 20, 'retrait', '3900435702'),
('2019-03-16 01:53:57', 10, 'retrait', '3900435702'),
('2019-03-24 22:40:20', 10, 'retrait', '3900435702'),
('2019-03-25 00:08:28', 30, 'retrait', '3900435702'),
('2019-03-25 00:09:52', 30, 'retrait', '3900435702'),
('2019-03-25 00:10:53', 220, 'retrait', '3900435702'),
('2019-03-25 00:16:52', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:18:28', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:18:38', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:18:52', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:19:31', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:20:38', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:20:56', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:21:09', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:23:16', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:23:42', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:24:05', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:25:46', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:27:04', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:28:11', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:29:41', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:30:04', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:32:45', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:33:04', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:33:48', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:34:00', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:34:55', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:35:13', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 00:35:52', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 13:54:43', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 13:58:03', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 13:59:40', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:00:40', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:06:14', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:07:19', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:23:17', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:25:50', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:26:36', 0, 'Impression_Rib', '3900435702'),
('2019-03-27 19:16:11', 10, 'retrait', '3900435702'),
('2019-03-27 19:16:53', 0, 'Impression_Rib', '3900435702'),
('2019-03-27 19:17:28', 0, 'Impression_Rib', '3900435702'),
('2019-03-27 19:19:48', 0, 'Impression_Rib', '3900435702'),
('2019-03-27 19:20:12', 0, 'Impression_Rib', '3900435702'),
('2019-03-27 19:20:56', 0, 'Impression_Rib', '3900435702'),
('2019-03-27 19:23:29', 0, 'Impression_Rib', '3900435702'),
('2019-03-27 22:40:20', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 17:23:40', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 17:25:14', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 17:26:36', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 17:27:16', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 18:24:13', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 00:30:40', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 00:31:27', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 00:33:44', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 00:34:05', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 00:34:19', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:10:05', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:10:33', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:10:52', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:11:54', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:13:17', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:13:31', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:13:56', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:14:42', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:50:06', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:50:44', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:50:53', 0, 'Impression_Rib', '3900435702');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `carte`
--
ALTER TABLE `carte`
  ADD PRIMARY KEY (`ref_carte`),
  ADD KEY `carte_fk` (`num_client`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`num`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`num_compte`),
  ADD KEY `compte_fk` (`num_client`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`date_oprt`,`somme`,`action_oprt`,`num_client`) USING BTREE,
  ADD KEY `operation_fk` (`num_client`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `carte`
--
ALTER TABLE `carte`
  ADD CONSTRAINT `carte_fk` FOREIGN KEY (`num_client`) REFERENCES `client` (`num`);

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_fk` FOREIGN KEY (`num_client`) REFERENCES `client` (`num`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `operation_fk` FOREIGN KEY (`num_client`) REFERENCES `client` (`num`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
