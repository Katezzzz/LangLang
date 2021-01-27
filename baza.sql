-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 27 Sty 2021, 11:57
-- Wersja serwera: 10.4.14-MariaDB
-- Wersja PHP: 7.2.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `ll`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `courses`
--

CREATE TABLE `courses` (
  `idCourse` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `language` varchar(20) NOT NULL,
  `teacher` varchar(60) NOT NULL,
  `seats` int(11) NOT NULL,
  `time` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `courses`
--

INSERT INTO `courses` (`idCourse`, `name`, `language`, `teacher`, `seats`, `time`) VALUES
(1, 'Angielski od Podstaw', 'Angielski', 'Anna Frania', 20, 'Pon/Śr 18:00'),
(2, 'UP INT  English', 'Angielski', 'Henryk Kania', 12, 'Pon/Śr 16:30');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `participants`
--

CREATE TABLE `participants` (
  `idPart` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL,
  `SignOut` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `participants`
--

INSERT INTO `participants` (`idPart`, `idUser`, `idCourse`, `SignOut`) VALUES
(8, 13, 1, NULL),
(21, 1, 1, NULL),
(22, 1, 2, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `scores`
--

CREATE TABLE `scores` (
  `idScore` int(11) NOT NULL,
  `idUser` int(11) NOT NULL,
  `scorePerc` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subjects`
--

CREATE TABLE `subjects` (
  `idSubject` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `text` varchar(255) NOT NULL,
  `homework` varchar(255) DEFAULT NULL,
  `words` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `subjects`
--

INSERT INTO `subjects` (`idSubject`, `idCourse`, `title`, `text`, `homework`, `words`) VALUES
(1, 1, 'Pierwsza lekcja', 'Zapoznanie się w gronie grupy. Rozmowa o naszych wakacjach', 'Napisz 6 zdań na temat twoich wakacji :)', 'holiday, sea, beach, hotel, hiking, walking'),
(2, 1, 'Nasze hobby', 'Rozmowy na temat tego co lubimy robić w życiu i dlaczego nie jest to android', 'Powtórzyć czas past simple', 'Relax :)');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tests`
--

CREATE TABLE `tests` (
  `idRow` int(11) NOT NULL,
  `idTest` int(11) NOT NULL,
  `question` varchar(100) NOT NULL,
  `A` varchar(50) NOT NULL,
  `B` varchar(50) NOT NULL,
  `C` varchar(50) NOT NULL,
  `D` varchar(50) NOT NULL,
  `correctAns` enum('A','B','C','D') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `tests`
--

INSERT INTO `tests` (`idRow`, `idTest`, `question`, `A`, `B`, `C`, `D`, `correctAns`) VALUES
(1, 1, 'TESTA', 'A', 'B', 'C', 'D', 'A'),
(2, 1, 'TESTB', 'A', 'B', 'C', 'D', 'B');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(20) NOT NULL,
  `username` varchar(70) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `name`, `lastname`) VALUES
(1, 'kasia', 'e10adc3949ba59abbe56e057f20f883e', 'z@wp.pl', ' Kasia', ' Maz'),
(13, 'antek', 'e10adc3949ba59abbe56e057f20f883e', 'a@wp.pl', ' Antek', ' A');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`idCourse`);

--
-- Indeksy dla tabeli `participants`
--
ALTER TABLE `participants`
  ADD PRIMARY KEY (`idPart`),
  ADD KEY `idCourse_2` (`idCourse`),
  ADD KEY `idUser_2` (`idUser`);

--
-- Indeksy dla tabeli `scores`
--
ALTER TABLE `scores`
  ADD PRIMARY KEY (`idScore`);

--
-- Indeksy dla tabeli `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`idSubject`),
  ADD KEY `idCourse` (`idCourse`);

--
-- Indeksy dla tabeli `tests`
--
ALTER TABLE `tests`
  ADD PRIMARY KEY (`idRow`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `courses`
--
ALTER TABLE `courses`
  MODIFY `idCourse` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `participants`
--
ALTER TABLE `participants`
  MODIFY `idPart` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT dla tabeli `scores`
--
ALTER TABLE `scores`
  MODIFY `idScore` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `subjects`
--
ALTER TABLE `subjects`
  MODIFY `idSubject` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `tests`
--
ALTER TABLE `tests`
  MODIFY `idRow` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `participants`
--
ALTER TABLE `participants`
  ADD CONSTRAINT `participants_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `participants_ibfk_2` FOREIGN KEY (`idCourse`) REFERENCES `courses` (`idCourse`);

--
-- Ograniczenia dla tabeli `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `subjects_ibfk_1` FOREIGN KEY (`idCourse`) REFERENCES `courses` (`idCourse`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
