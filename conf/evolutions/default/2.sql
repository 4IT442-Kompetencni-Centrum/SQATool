# --- !Ups
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(1, 0, TRUE, 'Jan', 'Admin', 'admin', 'admin', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(2, 0, TRUE, 'Karel', 'Vedouci', 'head', 'head', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(3, 0, TRUE, 'Filip', 'Manazer', 'manager', 'manager', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(4, 0, TRUE, 'Dominik', 'Clen', 'member', 'member', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(5, 0, TRUE, 'Tomas', 'Michalicka', 'tomas', 'tomas', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(6, 0, TRUE, 'Ales', 'Jiranek', 'ales', 'ales', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(7, 0, TRUE, 'Petr', 'Kadlec', 'petr', 'petr', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(8, 0, TRUE, 'Miroslav', 'Cech', 'miroslav', 'miroslav', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(9, 0, TRUE, 'Jiri', 'Siroky', 'jiri', 'jiri', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(10, 0, TRUE, 'Ondrej', 'Kostal', 'ondrej', 'ondrej', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(11, 0, TRUE, 'Radek', 'Slavetinsky', 'radek', 'radek', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(12, 0, TRUE, 'Vladimir', 'Rahm', 'vladimir', 'vladimir', '1');
INSERT INTO sqa_user (id, version, visible, firstname, lastname, password, username, stateuser_stateuserid) VALUES(13, 0, TRUE, 'Jan', 'Smetana', 'jan', 'jan', '1');

# --- !Downs

DELETE FROM sqa_user;