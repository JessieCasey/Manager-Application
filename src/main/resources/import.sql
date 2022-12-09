INSERT INTO TEAM (id, club_name, founder, founded_date, commission, budget) values (1, 'Manchester United F.C.', 'Lancashire and Yorkshire Railway', '1878-01-14', 8, 13000000);
INSERT INTO TEAM (id, club_name, founder, founded_date, commission, budget) values (2, 'Real Madrid', 'Adolfo Melendez', '1902-04-6', 10, 5414207);
INSERT INTO TEAM (id, club_name, founder, founded_date, commission, budget) values (3, 'F.C. Barcelona', 'Joan Gamper', '1899-12-29', 9, 831450);
INSERT INTO TEAM (id, club_name, founder, founded_date, commission, budget) values (4, 'Bayern Munich', 'Franz John', '1900-02-27', 6, 450687);
INSERT INTO TEAM (id, club_name, founder, founded_date, commission, budget) values (5, 'Liverpool', 'John Houlding', '1892-06-18', 3, 125042);
INSERT INTO TEAM (id, club_name, founder, founded_date, commission, budget) values (6, 'Paris Saint Germain', 'Henri Patrelle', '1970-04-19', 4, 1414501);

INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (1, 'Lionel', 'Messi', 'Argentina', 123, '1987-06-24', '2020-01-20', 'FORWARD', 3);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (2, 'Cristiano', 'Ronaldo', 'Portugal', 150, '1985-02-05', '2018-10-03', 'FORWARD', 2);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (3, 'Andrea', 'Pirlo', 'Italy', 34, '1982-03-10', '2014-10-03', 'GOALKEEPER', 5);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (4, 'Yaya', 'Toure', 'Ivory Coast', 67, '1986-02-13', '2014-10-03', 'MIDFIELDER', 3);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (5, 'Edinson ', 'Cavani', 'Colombia', 34, '1986-06-10', '2014-10-03', 'DEFENDER', 2);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (6, 'Sergio', 'Aguero', 'Spain', 20, '1990-05-10', '2014-10-03', 'FORWARD', 1);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (7, 'Iker', 'Casillas', 'Spain', 34, '1983-03-20', '2014-10-03', 'MIDFIELDER', 1);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (8, 'David', 'Silva', 'Brazil', 70, '1988-08-14', '1999-11-23', 'GOALKEEPER', 3);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (9, 'Mesut', 'Ozil', 'Poland', 34, '2000-02-17', '2017-10-03', 'MIDFIELDER', 2);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (10, 'Bastian', 'Schweinsteiger', 'Germany', 93, '1986-02-10', '2014-10-03', 'DEFENDER', 3);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (11, 'Ashley', 'Cole', 'England', 51, '1999-04-10', '2015-05-04', 'DEFENDER', 3);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (12, 'Luis', 'Suarez', 'Uruguay', 55, '1994-02-06', '2014-10-03', 'FORWARD', 4);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (13, 'Marco', 'Reus', 'Uruguay', 52, '2008-10-16', '2015-11-13', 'FORWARD', 4);
INSERT INTO PLAYER (id, firstname, lastname, nationality, monthsExperience, birthday, arrival_date, position, motherClub_id) VALUES (14, 'Franck', 'Ribery', 'France', 31, '2002-05-08', '2014-10-03', 'FORWARD', 6);


INSERT INTO TEAM_PLAYER (players_id, Team_id) VALUES (1, 3);
INSERT INTO TEAM_PLAYER (players_id, Team_id) VALUES (2, 2);
INSERT INTO TEAM_PLAYER (players_id, Team_id) VALUES (3, 5);
INSERT INTO TEAM_PLAYER (players_id, Team_id) VALUES (4, 3);
INSERT INTO TEAM_PLAYER (players_id, Team_id) VALUES (5, 2);
INSERT INTO TEAM_PLAYER (players_id, Team_id) VALUES (6, 1);
INSERT INTO TEAM_PLAYER (players_id, Team_id) VALUES (7, 1);
