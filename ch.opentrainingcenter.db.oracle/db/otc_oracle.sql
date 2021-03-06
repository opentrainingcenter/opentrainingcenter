CREATE TABLE ATHLETE 
(
	ID INTEGER NOT NULL,
	NAME VARCHAR(255), 
	BIRTHDAY DATE NOT NULL,
	MAXHEARTRATE INTEGER, 
	PRIMARY KEY (ID)
);

CREATE TABLE HEALTH 
( 
	ID INTEGER NOT NULL,
	WEIGHT NUMERIC, 
	CARDIO INTEGER, 
	DATEOFMEASURE DATE, 
	ID_FK_ATHLETE INTEGER,
	PRIMARY KEY (ID)
);

CREATE TABLE WEATHER 
(
	ID INTEGER NOT NULL, 
	WETTER VARCHAR(255), 
	IMAGEICON VARCHAR(255), 
	PRIMARY KEY (ID)
);

CREATE TABLE TRAININGTYPE 
(
	ID INTEGER NOT NULL, 
	TITLE VARCHAR(255), 
	DESCRIPTION VARCHAR(255), 
	IMAGEICON VARCHAR(255), 
	PRIMARY KEY (ID)
);

CREATE TABLE PLANUNGWOCHE
( 
	ID INTEGER NOT NULL, 
	KW INTEGER, 
	JAHR INTEGER,
	KMPROWOCHE INTEGER, 
	INTERVAL NUMBER(1),
	LANGERLAUF INTEGER, 
	ID_FK_ATHLETE INTEGER, 
	PRIMARY KEY (ID)
);

CREATE TABLE ROUTE 
( 
	ID INTEGER NOT NULL, 
	ID_FK_ATHLETE INTEGER, 
	ID_FK_TRAINING INTEGER,
	NAME VARCHAR(255), 
	BESCHREIBUNG VARCHAR(255), 
	PRIMARY KEY (ID)
);

CREATE TABLE STRECKENPUNKTE
(
	ID INTEGER NOT NULL, 
	LONGITUDE NUMBER(12,8), 
	LATITUDE NUMBER(12,8),
	PRIMARY KEY (ID)
); 

CREATE TABLE TRACKTRAININGPROPERTY
(
	ID INTEGER NOT NULL, 
	DISTANCE NUMERIC, 
	HEARTBEAT INTEGER, 
	ALTITUDE INTEGER,
	ZEIT INTEGER, -- war vorher bigint
	LAP INTEGER,
	ID_FK_STRECKENPUNKT INTEGER,
	IDX INTEGER,
	ID_TRAINING INTEGER,
	PRIMARY KEY (ID)
); 

CREATE TABLE TRAINING 
(
	ID_TRAINING INTEGER NOT NULL, 
	DATUM LONG, -- war vorher bigint
	DAUER NUMERIC, 
	LAENGEINMETER NUMERIC, 
	AVERAGEHEARTBEAT INTEGER, 
	MAXHEARTBEAT INTEGER, 
	MAXSPEED NUMERIC, 
	NOTE VARCHAR2(1000),
	DATEOFIMPORT TIMESTAMP,
	FILENAME VARCHAR2(500),
	UPMETER INTEGER,
	DOWNMETER INTEGER,
	ID_FK_WEATHER INTEGER, 
	ID_FK_TRAININGTYPE INTEGER, 
	ID_FK_ROUTE INTEGER,
	ID_FK_ATHLETE INTEGER,
	PRIMARY KEY (ID_TRAINING)
);


-- Foreign Keys
ALTER TABLE HEALTH ADD FOREIGN KEY (ID_FK_ATHLETE) REFERENCES ATHLETE (ID);
ALTER TABLE TRAINING ADD FOREIGN KEY (ID_FK_WEATHER) REFERENCES WEATHER (ID);
ALTER TABLE TRAINING ADD FOREIGN KEY (ID_FK_ATHLETE) REFERENCES ATHLETE (ID);
ALTER TABLE TRAINING ADD FOREIGN KEY (ID_FK_ROUTE) REFERENCES ROUTE (ID) ON DELETE SET NULL;
ALTER TABLE TRAINING ADD FOREIGN KEY (ID_FK_TRAININGTYPE) REFERENCES TRAININGTYPE (ID); 
ALTER TABLE TRACKTRAININGPROPERTY ADD FOREIGN KEY(ID_FK_STRECKENPUNKT) REFERENCES STRECKENPUNKTE(ID);
ALTER TABLE PLANUNGWOCHE ADD FOREIGN KEY (ID_FK_ATHLETE) REFERENCES ATHLETE (ID);
ALTER TABLE ROUTE ADD FOREIGN KEY (ID_FK_ATHLETE) REFERENCES ATHLETE (ID);
ALTER TABLE ROUTE ADD FOREIGN KEY (ID_FK_TRAINING) REFERENCES TRAINING (ID_TRAINING) ON DELETE SET NULL;

-- Unique 
alter table athlete add unique ( name );

CREATE sequence TRACKPOINTPROPERTY_ID_SEQUENCE;
CREATE sequence ATHLETE_ID_SEQUENCE;
CREATE sequence TRAINING_ID_SEQUENCE;
CREATE sequence HEALTH_ID_SEQUENCE; 
CREATE sequence PLANUNGWOCHE_ID_SEQUENCE; 
CREATE sequence ROUTE_ID_SEQUENCE; 
CREATE sequence STRECKENPUNKTE_ID_SEQUENCE; 

INSERT INTO ATHLETE (ID,NAME,BIRTHDAY,MAXHEARTRATE) values(0,'Sascha',TO_DATE('1974-08-29','yyyy-mm-dd'), 200);
INSERT INTO ROUTE (ID,ID_FK_ATHLETE,NAME,BESCHREIBUNG) values(0,0,'Unbekannt','');

INSERT INTO TRAININGTYPE (ID,TITLE,DESCRIPTION, IMAGEICON) values(0,'NONE','Unbekannter Typ','icons/man_u_32_32.png');
INSERT INTO TRAININGTYPE (ID,TITLE,DESCRIPTION, IMAGEICON) values(1,'EXT_INTERVALL','Gleich wie intensives Intervalltraining. Der Unterschied liegt in der Länge der Intervalle und der damit verbundenen geringeren Laufgeschwindigkeit. Beispiel: 5 Minuten schnell, 2 Minuten Trabpause, 5 Minuten schnell etc. Extensive Intervalle werden bereits schon in der Aufbauetappe des Jahresplanes integriert.','icons/man_ei_32_32.png');
INSERT INTO TRAININGTYPE (ID,TITLE,DESCRIPTION, IMAGEICON) values(2,'INT_INTERVALL','Intervalltrainings werden in Serien gelaufen. Zum Beispiel: 6 x 200 m schnell, jeweils 2 Minuten Trabpause. Intervall bedeutet Pause. Der Kreislauf wird belastet, anschliessend erhält er Zeit, sich zum Teil wieder zu erholen. Dann folgt der nächste Intervall. Intensive Intervalltrainings werden meist in der Wettkampfvorbereitung durchgeführt.','icons/man_ii_32_32.png');
INSERT INTO TRAININGTYPE (ID,TITLE,DESCRIPTION, IMAGEICON) values(3,'LONG_JOG','Langer Dauerlauf 70-75% maximalen Herzfrequenz.','icons/man_lj_32_32.png');
INSERT INTO TRAININGTYPE (ID,TITLE,DESCRIPTION, IMAGEICON) values(4,'POWER_LONG_JOG','letztes drittel 80-85% der maximalen Herzfrequenz, die ersten zwei drittel wie {@link RunType#LONG_JOG}','icons/man_plj_32_32.png');
INSERT INTO TRAININGTYPE (ID,TITLE,DESCRIPTION, IMAGEICON) values(5,'TEMPO_JOG','UNBEKANNT','icons/man_tj_32_32.png');

INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(0,'SONNE','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(1,'LEICHT_BEWOELCKT','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(2,'BEWOELCKT','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(3,'STARK_BEWOELCKT','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(4,'LEICHTER_REGEN','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(5,'REGEN','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(6,'STARKER_REGEN','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(7,'LEICHTER_SCHNEE','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(8,'SCHNEE','icons/man_u_32_32.png');
INSERT INTO WEATHER (ID,WETTER, IMAGEICON) values(9,'UNBEKANNT','icons/man_u_32_32.png');
