-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le :  Dim 31 mars 2019 à 19:01
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
-- Base de données :  `myDB`
--

-- --------------------------------------------------------

--
-- Structure de la table `carte`
--

CREATE TABLE `carte` (
  `ref_carte` char(30) NOT NULL,
  `numero_carte` char(10) NOT NULL,
  `code` char(100) NOT NULL,
  `modele` char(15) NOT NULL,
  `date_expir` date NOT NULL,
  `num_client` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `carte`
--

INSERT INTO `carte` (`ref_carte`, `numero_carte`, `code`, `modele`, `date_expir`, `num_client`) VALUES
('123456789', '123456788', '1234', 'visa', '2017-03-12', '3900435702');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `num` char(30) NOT NULL,
  `nom` char(30) NOT NULL,
  `prenom` char(30) NOT NULL,
  `dtNaiss` date NOT NULL,
  `email` char(40) NOT NULL,
  `adresse` char(100) NOT NULL,
  `profession` char(30) DEFAULT NULL,
  `numero_tel` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`num`, `nom`, `prenom`, `dtNaiss`, `email`, `adresse`, `profession`, `numero_tel`) VALUES
('3900435702', 'Bouredjioua', 'Sofiane', '1996-09-27', 'bouredjiouasofiane@gmail.com', '31 avenue pasteur lyon', 'etudiant', '0695495703'),
('3966557802', 'moi', 'meme', '2000-01-01', 'bouredjsofiane@yahoo.fr', '15 rue d\'etiene d\'orves, paris', 'etudinat', '0666986654');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `num_compte` char(30) NOT NULL,
  `solde` float NOT NULL,
  `seuil` float DEFAULT NULL,
  `seuilQuot` float NOT NULL,
  `seuilHebdo` float NOT NULL,
  `RIB` char(30) NOT NULL,
  `IBAN` char(40) DEFAULT NULL,
  `num_client` char(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`num_compte`, `solde`, `seuil`, `seuilQuot`, `seuilHebdo`, `RIB`, `IBAN`, `num_client`) VALUES
('6096506348', 10284.8, 0, 150, 550, '30004008170000152651985', 'FR7630004008170000152651985', '3900435702');

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `date_oprt` datetime NOT NULL,
  `somme` float NOT NULL,
  `action_oprt` char(25) NOT NULL,
  `num_client` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`date_oprt`, `somme`, `action_oprt`, `num_client`) VALUES
('2019-02-28 00:00:00', 200, 'depot', '3900435702'),
('2019-03-01 00:00:00', 150, 'depot', '3900435702'),
('2019-03-02 00:00:00', 150, 'depot', '3900435702'),
('2019-03-03 00:00:00', 10, 'depot', '3900435702'),
('2019-03-03 00:00:00', 15.5, 'depot', '3900435702'),
('2019-03-03 00:00:00', 15.5, 'retrait', '3900435702'),
('2019-03-03 00:00:00', 20.5, 'retrait', '3900435702'),
('2019-03-03 00:00:00', 100, 'retrait', '3900435702'),
('2019-03-03 00:00:00', 150, 'depot', '3900435702'),
('2019-03-03 00:00:00', 1000, 'depot', '3900435702'),
('2019-03-03 00:00:00', 1400, 'retrait', '3900435702'),
('2019-03-03 14:54:22', 160.5, 'depot', '3900435702'),
('2019-03-03 14:55:20', 60.75, 'depot', '3900435702'),
('2019-03-04 01:25:22', 140, 'retrait', '3900435702'),
('2019-03-04 02:14:37', 200, 'depot', '3900435702'),
('2019-03-04 02:15:19', 140, 'retrait', '3900435702'),
('2019-03-04 03:22:02', 0, 'Impression_RIB', '3900435702'),
('2019-03-04 03:28:33', 0, 'Impression_RIB', '3900435702'),
('2019-03-04 15:46:18', 9920, 'depot', '3900435702'),
('2019-03-04 15:47:18', 200, 'retrait', '3900435702'),
('2019-03-04 15:48:12', 0, 'Impression_RIB', '3900435702'),
('2019-03-05 03:38:24', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-05 03:39:05', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-05 03:39:59', 0, 'Impression_RIB', '3900435702'),
('2019-03-05 03:39:59', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-05 03:43:25', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-05 03:45:01', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-05 03:47:26', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-05 03:48:18', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-05 03:48:27', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-07 19:31:46', 1184, 'depot', '3900435702'),
('2019-03-07 19:33:30', 200, 'retrait', '3900435702'),
('2019-03-07 19:36:55', 0, 'Impression_RIB', '3900435702'),
('2019-03-07 19:37:49', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-08 01:45:03', 200, 'retrait', '3900435702'),
('2019-03-08 01:54:04', 100, 'retrait', '3900435702'),
('2019-03-08 02:43:15', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-08 02:43:55', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-08 18:44:13', 50, 'depot', '3900435702'),
('2019-03-08 18:52:45', 0, 'Impression_RIB', '3900435702'),
('2019-03-08 18:55:33', 0, 'Imprssion_Historique', '3900435702'),
('2019-03-11 21:53:21', 100, 'retrait', '3900435702'),
('2019-03-11 21:53:34', 100, 'retrait', '3900435702'),
('2019-03-11 23:47:46', 100, 'retrait', '3900435702'),
('2019-03-13 00:33:42', 50, 'retrait', '3900435702'),
('2019-03-16 01:55:11', 100, 'retrait', '3900435702'),
('2019-03-22 11:15:04', 0, 'Impression_Rib', '3900435702'),
('2019-03-22 11:18:36', 0, 'Impression_Rib', '3900435702'),
('2019-03-22 11:22:33', 0, 'Impression_Rib', '3900435702'),
('2019-03-22 11:24:17', 0, 'Impression_Rib', '3900435702'),
('2019-03-22 17:16:43', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 01:45:38', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 01:47:09', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 01:51:04', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 01:51:37', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 01:53:09', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 01:54:07', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 01:54:30', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 01:54:50', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 02:14:49', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 02:15:55', 0, 'Impr_Historique', '3900435702'),
('2019-03-23 02:28:09', 0, 'Impression_Rib', '3900435702'),
('2019-03-23 23:31:37', 0, 'Impression_Rib', '3900435702'),
('2019-03-23 23:32:28', 0, 'Impression_Rib', '3900435702'),
('2019-03-23 23:32:49', 0, 'Impression_Rib', '3900435702'),
('2019-03-23 23:33:04', 0, 'Impression_Rib', '3900435702'),
('2019-03-23 23:33:47', 0, 'Impression_Rib', '3900435702'),
('2019-03-23 23:41:53', 0, 'Impression_Rib', '3900435702'),
('2019-03-23 23:42:31', 0, 'Impression_Rib', '3900435702'),
('2019-03-23 23:43:07', 0, 'Impression_Rib', '3900435702'),
('2019-03-24 00:11:36', 0, 'Impression_Rib', '3900435702'),
('2019-03-24 00:17:44', 0, 'Impression_Historique', '3900435702'),
('2019-03-24 00:18:03', 0, 'Impression_Historique', '3900435702'),
('2019-03-24 00:21:48', 0, 'Impression_Historique', '3900435702'),
('2019-03-24 00:22:50', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 13:39:16', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 13:40:46', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 13:53:16', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 13:53:41', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 13:54:09', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 14:08:40', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 14:09:18', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 14:09:47', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 14:10:14', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:10:14', 0, 'Impr_Historique', '3900435702'),
('2019-03-25 14:10:48', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:11:10', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:11:33', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:11:53', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:12:18', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:12:40', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:15:22', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:16:54', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:17:20', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:17:34', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:18:05', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:18:34', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:18:45', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:18:58', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:19:37', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:19:46', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:20:16', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:20:25', 0, 'Impression_Rib', '3900435702'),
('2019-03-25 14:20:39', 0, 'Impression_Rib', '3900435702'),
('2019-03-26 00:13:43', 0, 'Impression_Rib', '3900435702'),
('2019-03-26 00:14:37', 0, 'Impr_Historique', '3900435702'),
('2019-03-26 00:15:27', 200, 'depot', '3900435702'),
('2019-03-28 17:18:18', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 18:24:43', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 18:25:13', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 18:25:40', 0, 'Impression_Rib', '3900435702'),
('2019-03-28 18:26:52', 0, 'Impr_Historique', '3900435702'),
('2019-03-29 00:25:05', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 00:26:59', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 00:27:45', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 00:29:14', 0, 'Impr_Historique', '3900435702'),
('2019-03-29 00:34:35', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:12:55', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:15:02', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:15:53', 0, 'Impression_Rib', '3900435702'),
('2019-03-29 01:51:16', 0, 'Impr_Historique', '3900435702'),
('2019-03-29 01:52:47', 0, 'Impression_Rib', '3900435702');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `carte`
--
ALTER TABLE `carte`
  ADD PRIMARY KEY (`ref_carte`),
  ADD KEY `num_client` (`num_client`);

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
  ADD KEY `num_client` (`num_client`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`date_oprt`,`somme`,`action_oprt`,`num_client`) USING BTREE,
  ADD KEY `num_client` (`num_client`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `carte`
--
ALTER TABLE `carte`
  ADD CONSTRAINT `carte_ibfk_1` FOREIGN KEY (`num_client`) REFERENCES `client` (`num`);

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`num_client`) REFERENCES `client` (`num`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`num_client`) REFERENCES `client` (`num`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
