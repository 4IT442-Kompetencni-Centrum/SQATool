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

INSERT INTO sqa_type_role_in_business (id, key, value) VALUES (1, 'admin', 'Administrátor');
INSERT INTO sqa_type_role_in_business (id, key, value) VALUES (2, 'manazerKC', 'Manažer KC');
INSERT INTO sqa_type_role_in_business (id, key, value) VALUES (3, 'vedouciKC', 'Vedoucí KC');
INSERT INTO sqa_type_role_in_business (id, key, value) VALUES (4, 'clen', 'Člen');

INSERT INTO sqa_type_user_on_project (typeUserOnProjectId, key, value) VALUES (1, 'vedouci', 'Vedoucí');
INSERT INTO sqa_type_user_on_project (typeUserOnProjectId, key, value) VALUES (2, 'clen', 'Člen');

INSERT INTO sqa_state_hours_worked (stateHoursWorkedId, key, value) VALUES (1, 'zadana', 'Zadaná');
INSERT INTO sqa_state_hours_worked (stateHoursWorkedId, key, value) VALUES (2, 'schvalena', 'Schválená');
INSERT INTO sqa_state_hours_worked (stateHoursWorkedId, key, value) VALUES (3, 'zamitnuta', 'Zamítnutá');

INSERT INTO sqa_state_user (stateUserId, key, value) VALUES (0,'neaktivni','Neaktivní');
INSERT INTO sqa_state_user (stateUserId, key, value) VALUES (1,'aktivni','Aktivní');

INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId,key,value) VALUES (0, 'zadna', 'Žádná');
INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId,key,value) VALUES (1, 'zakladni', 'Základní');
INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId,key,value) VALUES (2, 'stredni', 'Střední');
INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId,key,value) VALUES (3, 'pokrocila', 'Pokročilá');
INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId,key,value) VALUES (4, 'prezentovatelna', 'Prezentovatelná');

INSERT INTO sqa_state_academic_work(stateAcademicWorkId,key,value) VALUES (0,'aktivni', 'Aktivní');
INSERT INTO sqa_state_academic_work(stateAcademicWorkId,key,value) VALUES (1,'ukoncena', 'Ukončená');

INSERT INTO sqa_type_academic_work(typeAcademicWorkId,key,value) VALUES (0, 'bakalarskaPrace', 'Bakalářská práce');
INSERT INTO sqa_type_academic_work(typeAcademicWorkId,key,value) VALUES (1, 'diplomovaPrace', 'Diplomová práce');
INSERT INTO sqa_type_academic_work(typeAcademicWorkId,key,value) VALUES (2, 'disertacniPrace', 'Disertační práce');


# --- !Downs
DELETE FROM sqa_type_activity;
DELETE FROM sqa_state_activity;
DELETE FROM sqa_type_role_on_activity;
DELETE FROM sqa_type_role_in_business;
DELETE FROM sqa_type_user_on_project;
DELETE FROM sqa_state_hours_worked;
DELETE FROM sqa_state_user;
DELETE FROM sqa_level_of_knowledge;
DELETE FROM sqa_state_academic_work;
DELETE FROM sqa_type_academic_work;