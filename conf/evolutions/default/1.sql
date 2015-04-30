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

INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(1, 0, TRUE, 'Jan', 'Admin', 'admin', 'admin');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(2, 0, TRUE, 'Karel', 'Vedouci', 'head', 'head');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(3, 0, TRUE, 'Filip', 'Manazer', 'manager', 'manager');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(4, 0, TRUE, 'Dominik', 'Clen', 'member', 'member');

INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(5, 0, TRUE, 'Tomas', 'Michalicka', 'tomas', 'tomas');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(6, 0, TRUE, 'Ales', 'Jiranek', 'ales', 'ales');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(7, 0, TRUE, 'Petr', 'Kadlec', 'petr', 'petr');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(8, 0, TRUE, 'Miroslav', 'Cech', 'miroslav', 'miroslav');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(9, 0, TRUE, 'Jiri', 'Siroky', 'jiri', 'jiri');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(10, 0, TRUE, 'Ondrej', 'Kostal', 'ondrej', 'ondrej');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(11, 0, TRUE, 'Radek', 'Slavetinsky', 'radek', 'radek');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(12, 0, TRUE, 'Vladimir', 'Rahm', 'vladimir', 'vladimir');
INSERT INTO SQA_USER (id, version, visible, firstname, lastname, password, username) VALUES(13, 0, TRUE, 'Jan', 'Smetana', 'jan', 'jan');

INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(1, 1, 1);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(2, 3, 2);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(3, 2, 3);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(4, 4, 4);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(5, 1, 5);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(6, 1, 6);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(7, 1, 7);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(8, 1, 8);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(9, 1, 9);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(10, 1, 10);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(11, 1, 11);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(12, 1, 12);
INSERT INTO SQA_ROLE_IN_BUSINESS (roleinbusinessid, typeroleinbusiness_id, user_id) VALUES(13, 1, 13);

# --- !Downs
DELETE FROM SQA_ROLE_IN_BUSINESS;
DELETE FROM SQA_USER;
DELETE FROM sqa_type_activity;
DELETE FROM sqa_state_activity;
DELETE FROM sqa_type_role_on_activity;
DELETE FROM SQA_TYPE_ROLE_IN_BUSINESS;
DELETE FROM SQA_TYPE_USER_ON_PROJECT;
DELETE FROM SQA_STATE_HOURS_WORKED;
