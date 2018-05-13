<h1>KozosKoltsegFX</h1>


Ezzel a programmal egy Társasház közösköltség táblázatát lehet kezelni JavaFX felületen keresztül. Külön panellal rendelkezik a lakó és a közösképviselő is.
A program működéséhez egy adatbázis-ra lesz szükség 3 táblával: Kepviselok, Lakok, Tarsashaz.
A kepviselok táblának állnia kell egy id, azonosito, jelszo, és nev táblából.
A lakok táblának állnia kell egy id, emelet, lakas, befizetett, és telefonszam táblából.
A tarsashaz táblának állnia kell egy id, cim, uzenet, és kozoskoltseg táblából.
A táblák létrehozásához szükséges SQL parancsok:

CREATE TABLE Kepviselok (
    id int NOT NULL PRIMARY KEY,
    azonosito varchar(255) NOT NULL,
    jelszo varchar(255) NOT NULL,
    nev varchar(255)
);

CREATE TABLE Lakok (
    id int NOT NULL PRIMARY KEY,
    emelet int NOT NULL,
    lakas int NOT NULL,
    jelszo varchar(255) NOT NULL,
    befizetett int,
    telefonszam int
);

CREATE TABLE Tarsashaz (
    id int NOT NULL PRIMARY KEY,
    cim varchar(255) NOT NULL,
    kozoskoltseg int NOT NULL,
    uzenet varchar(255)
);

A belépéshez szükséges még egy képviselő is amennyiben üres a tábla, a következő SQl parancsal lehet hozzáaadni a táblához:
INSERT INTO Kepviselok VALUES (1,'Azonosito','Jelszo','Nev');
De ezek módosíthatók az adott osztályokon belül @Column annotációk utáni lévő name tagban, a táblák nevei pedig a @Table és az @Entity annotációk után lévő name tagban.

