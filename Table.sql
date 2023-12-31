create  database cinema;
\c cinema;
-- Création de la table "client"
CREATE TABLE client
(
    id     SERIAL PRIMARY KEY,
    nom    VARCHAR(100)        NOT NULL,
    prenom VARCHAR(100)        NOT NULL,
    email  VARCHAR(100) UNIQUE NOT NULL
);
-- Création de la table "film"
CREATE TABLE film
(
    id           SERIAL PRIMARY KEY,
    titre        VARCHAR(255) NOT NULL,
    realisateur  VARCHAR(100),
    duree        int,
    genre        VARCHAR(100),
    annee_sortie int          not null
);
-- Création de la table "salle"
CREATE TABLE salle
(
    id            SERIAL PRIMARY KEY,
    nom           VARCHAR(100) NOT NULL,
    capacite      int   NOT NULL
);

-- Création de la table "projection"
CREATE TABLE projection
(
    id         SERIAL PRIMARY KEY,
    id_salle   int REFERENCES salle (id) on delete cascade,
    id_film    int REFERENCES film (id) on delete cascade,
    date_heure timestamp NOT NULL
);



--creation table reservation
CREATE TABLE reservation
(
    id               SERIAL PRIMARY KEY,
    id_client        INT REFERENCES client (id) on delete cascade,
    id_projection    INT REFERENCES projection (id) on delete cascade,
    reservation_date date
);
