# --- !Ups

INSERT INTO sqa_type_activity (typeactivityid, value) VALUES (0, 'Interní školení');
INSERT INTO sqa_type_activity (typeactivityid, value) VALUES (1, 'Prezentace zákazníkům');
INSERT INTO sqa_type_activity (typeactivityid, value) VALUES (2, 'Schůzka KC');
INSERT INTO sqa_type_activity (typeactivityid, value) VALUES (3, 'Schůzka Manažerů');
INSERT INTO sqa_type_activity (typeactivityid, value) VALUES (4, 'Schůzka Projektu');
INSERT INTO sqa_type_activity (typeactivityid, value) VALUES (5, 'Ostatní');

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

INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId, value) VALUES (0, 'Žádná');
INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId, value) VALUES (1, 'Základní');
INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId, value) VALUES (2, 'Střední');
INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId, value) VALUES (3, 'Pokročilá');
INSERT INTO sqa_level_of_knowledge(levelOfKnowledgeId, value) VALUES (4, 'Prezentovatelná');

INSERT INTO sqa_state_academic_work(stateAcademicWorkId,key,value) VALUES (0,'aktivni', 'Aktivní');
INSERT INTO sqa_state_academic_work(stateAcademicWorkId,key,value) VALUES (1,'ukoncena', 'Ukončená');

INSERT INTO sqa_type_academic_work(typeAcademicWorkId,key,value) VALUES (0, 'bakalarskaPrace', 'Bakalářská práce');
INSERT INTO sqa_type_academic_work(typeAcademicWorkId,key,value) VALUES (1, 'diplomovaPrace', 'Diplomová práce');
INSERT INTO sqa_type_academic_work(typeAcademicWorkId,key,value) VALUES (2, 'disertacniPrace', 'Disertační práce');

INSERT INTO sqa_type_knowledge(typeKnowledgeId,value) VALUES (0,'UML');
INSERT INTO sqa_type_knowledge(typeKnowledgeId,value) VALUES (1,'Projektové řízení');
INSERT INTO sqa_type_knowledge(typeKnowledgeId,value) VALUES (2,'Java');

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
DELETE FROM sqa_type_knowledge;