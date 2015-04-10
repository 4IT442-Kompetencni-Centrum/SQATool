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

# --- !Downs

DELETE FROM sqa_type_activity;
DELETE FROM sqa_state_activity;
DELETE FROM sqa_type_role_on_activity;