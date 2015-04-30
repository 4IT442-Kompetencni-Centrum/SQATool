# --- !Ups

INSERT INTO sqa_type_activity (typeactivityid, key, value) VALUES (0, 'interniSkoleni', 'Interní školení');
INSERT INTO sqa_type_activity (typeactivityid, key, value) VALUES (1, 'prezentaceZakaznikum', 'Prezentace zákazníkům');
INSERT INTO sqa_type_activity (typeactivityid, key, value) VALUES (2, 'schuzkaKc', 'Schůzka KC');
INSERT INTO sqa_type_activity (typeactivityid, key, value) VALUES (3, 'schuzkaManazeru', 'Schůzka Manažerů');
INSERT INTO sqa_type_activity (typeactivityid, key, value) VALUES (4, 'schuzkaProjektu', 'Schůzka Projektu');
INSERT INTO sqa_type_activity (typeactivityid, key, value) VALUES (5, 'ostatni', 'Ostatní');

INSERT INTO sqa_state_activity (stateactivityid, key, value) VALUES (0, 'volna', 'Volná');
INSERT INTO sqa_state_activity (stateactivityid, key, value) VALUES (1, 'plna', 'Plná');

INSERT INTO sqa_type_role_on_activity (typeroleonactivityid, key, value) VALUES (0 , 'zakladatel', 'Zakladatel');
INSERT INTO sqa_type_role_on_activity (typeroleonactivityid, key, value) VALUES (1 , 'prihlaseny', 'Přihlášený');

INSERT INTO SQA_TYPE_ROLE_IN_BUSINESS (id, key, value) VALUES (1, 'admin', 'Administrátor');
INSERT INTO SQA_TYPE_ROLE_IN_BUSINESS (id, key, value) VALUES (2, 'manazerKC', 'Manažer KC');
INSERT INTO SQA_TYPE_ROLE_IN_BUSINESS (id, key, value) VALUES (3, 'vedouciKC', 'Vedoucí KC');
INSERT INTO SQA_TYPE_ROLE_IN_BUSINESS (id, key, value) VALUES (4, 'clen', 'Člen');

INSERT INTO SQA_TYPE_USER_ON_PROJECT (typeUserOnProjectId, key, value) VALUES (1, 'vedouci', 'Vedoucí');
INSERT INTO SQA_TYPE_USER_ON_PROJECT (typeUserOnProjectId, key, value) VALUES (2, 'clen', 'Člen');

INSERT INTO SQA_STATE_HOURS_WORKED (stateHoursWorkedId, key, value) VALUES (1, 'zadana', 'Zadaná');
INSERT INTO SQA_STATE_HOURS_WORKED (stateHoursWorkedId, key, value) VALUES (2, 'schvalena', 'Schválená');
INSERT INTO SQA_STATE_HOURS_WORKED (stateHoursWorkedId, key, value) VALUES (3, 'zamitnuta', 'Zamítnutá');

# --- !Downs
DELETE FROM SQA_USER;
DELETE FROM SQA_ROLE_IN_BUSINESS;
DELETE FROM sqa_type_activity;
DELETE FROM sqa_state_activity;
DELETE FROM sqa_type_role_on_activity;
DELETE FROM SQA_TYPE_ROLE_IN_BUSINESS;
DELETE FROM SQA_TYPE_USER_ON_PROJECT;
DELETE FROM SQA_STATE_HOURS_WORKED;
