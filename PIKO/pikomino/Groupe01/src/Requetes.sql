DROP Table Partie;
DROP Table Joueur;
DROP TABLE Pickominos;

PRAGMA foreign_keys = ON;
CREATE TABLE Partie(
  numPartie INTEGER NOT NULL,
  datePartie date DEFAULT NULL,
  PRIMARY KEY (numPartie)
);

CREATE TABLE Joueur(
    id INTEGER NOT NULL,
    nbVers INTEGER DEFAULT NULL,
    idPartie INTEGER NOT NULL,
    PRIMARY KEY (id, idPartie),
    FOREIGN KEY (idPartie) REFERENCES Partie(numPartie)
);

CREATE TABLE Pickominos(
    valeur INTEGER NOT NULL,
    etat INTEGER,
    idPartie INTEGER NOT NULL,
    PRIMARY KEY (idPartie, valeur),
    FOREIGN KEY (idPartie) REFERENCES Partie(numPartie)
);

SELECT * FROM Partie;
SELECT * FROM Joueur;
SELECT * FROM Pickominos;

--Requete 1: Afficher les scores dans l'ordre croissant
SELECT id, nbVers, idPartie, datePartie FROM Joueur j
INNER JOIN Partie p ON j.idPartie = p.numPartie
ORDER BY nbVers;

--Requete 2: Compter le nombre de victoire de chaque joueur
SELECT id, COUNT(id) as Nombre_Victoires FROM
(SELECT id, MAX(nbVers) FROM Joueur GROUP BY idPartie)
GROUP BY id;

--Requete 3: Voir le taux de tournement de chaque pickominos
SELECT valeur, NbRetournement,((NbRetournement * 100) / (NbPartie)) as TauxDeRetournement
FROM
(SELECT valeur, COUNT(valeur) as NbRetournement FROM Pickominos
WHERE etat == 0
GROUP BY valeur)
NATURAL JOIN (SELECT COUNT(DISTINCT idPartie) as NbPartie FROM Pickominos)
GROUP BY valeur;