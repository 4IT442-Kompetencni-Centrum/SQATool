# --- !Ups
INSERT INTO sqa_academic_work (academicworkid, version, visible, description, name, supervisor, stateacademicwork_stateacademicworkid, typeacademicwork_typeacademicworkid) VALUES (239, 0, true, 'testovací popis akademické práce', 'Aplikace pro KC SQA', 'Luboš Pavlíček', 0, 0);


INSERT INTO sqa_activity (activityid, version, visible, capacity, capacitymax, description, name, place, timefrom, timeto, stateactivity_stateactivityid, typeactivity_typeactivityid) VALUES (223, 2, true, 0, 10, 'Popis této aktivity. Bude probíhat školení na aplikaci, kterou právě používáte.', 'Školení na práci s aplikací SQA', 'Akademický klub', '2015-05-21 10:00:00', '2015-05-21 15:00:00', 0, 0);


INSERT INTO sqa_partner (partnerid, version, visible, city, description, housenumber, ic, name, street) VALUES (214, 1, false, 's', NULL, 's', 456, 's', 's');
INSERT INTO sqa_partner (partnerid, version, visible, city, description, housenumber, ic, name, street) VALUES (216, 1, false, 's', NULL, 's', 5, 's', 's');
INSERT INTO sqa_partner (partnerid, version, visible, city, description, housenumber, ic, name, street) VALUES (212, 1, false, 'Praha', NULL, '25', 25, '02', 'Nuselská');
INSERT INTO sqa_partner (partnerid, version, visible, city, description, housenumber, ic, name, street) VALUES (249, 0, true, 'Praha', NULL, '13', 11111111, 'Firma Cooler', 'Revoluční');
INSERT INTO sqa_partner (partnerid, version, visible, city, description, housenumber, ic, name, street) VALUES (222, 1, false, 'Benešov nad Ploučnicí', NULL, '78a', 785, 'PVK', 'Podolí');


INSERT INTO sqa_contact_person (contactpersonid, version, visible, email, firstname, lastname, phonenumber, partner_partnerid) VALUES (211, 1, true, 'fslov@fslov.cz', 'Felix', 'Slováček', '', 212);
INSERT INTO sqa_contact_person (contactpersonid, version, visible, email, firstname, lastname, phonenumber, partner_partnerid) VALUES (213, 1, true, 's@s.cz', 's', 's', '', 214);
INSERT INTO sqa_contact_person (contactpersonid, version, visible, email, firstname, lastname, phonenumber, partner_partnerid) VALUES (215, 2, false, 's@s.cz', 's', 's', '', 216);
INSERT INTO sqa_contact_person (contactpersonid, version, visible, email, firstname, lastname, phonenumber, partner_partnerid) VALUES (221, 1, true, 'gott@gott.cz', 'Karel', 'Gott', '123456789', 222);
INSERT INTO sqa_contact_person (contactpersonid, version, visible, email, firstname, lastname, phonenumber, partner_partnerid) VALUES (248, 1, true, 'newman@cooler.new', 'Joe', 'Newman', '', 249);


INSERT INTO sqa_project (projectid, version, visible, dateend, datestart, description, laboriousnessgues, laboriousnessreal, name, shortcut, stateproject_id) VALUES (219, 2, false, '2015-05-13 00:00:00', '2015-05-13 00:00:00', 'PROJ_test99', 5, NULL, 'OK_PROJ_test99', 'PROJ_test99', NULL);
INSERT INTO sqa_project (projectid, version, visible, dateend, datestart, description, laboriousnessgues, laboriousnessreal, name, shortcut, stateproject_id) VALUES (244, 1, true, '2015-05-31 00:00:00', '2015-02-21 00:00:00', 'Projekt s vedoucím projektu je uživatel s rolí Člen KC.', 125, NULL, 'Velice tvrdá práce', 'PR001', NULL);
INSERT INTO sqa_project (projectid, version, visible, dateend, datestart, description, laboriousnessgues, laboriousnessreal, name, shortcut, stateproject_id) VALUES (229, 4, false, '2015-08-31 00:00:00', '2015-03-01 00:00:00', 'Vedoucím je nejmenší člen', 100, NULL, 'Projekt - vedoucí člen', 'PROJ003', NULL);
INSERT INTO sqa_project (projectid, version, visible, dateend, datestart, description, laboriousnessgues, laboriousnessreal, name, shortcut, stateproject_id) VALUES (225, 1, false, '2015-05-30 00:00:00', '2015-05-06 00:00:00', 'Test', 200, NULL, 'Test projekt', 'PROJ002', NULL);
INSERT INTO sqa_project (projectid, version, visible, dateend, datestart, description, laboriousnessgues, laboriousnessreal, name, shortcut, stateproject_id) VALUES (217, 1, false, '2015-05-13 00:00:00', '2015-05-13 00:00:00', 's', 50, NULL, 's', 's', NULL);
INSERT INTO sqa_project (projectid, version, visible, dateend, datestart, description, laboriousnessgues, laboriousnessreal, name, shortcut, stateproject_id) VALUES (250, 1, true, '2015-06-01 00:00:00', '2015-05-13 00:00:00', 'Zde má být Člen projektu projektu Dominik Clen (Člen KC)', 241, NULL, 'Projekt Cool App ', 'PR001', NULL);


INSERT INTO sqa_hours_worked (hoursworkedid, version, visible, description, numberofhours, timefrom, timeto, project_projectid, statehoursworked_statehoursworkedid, user_id) VALUES (210, 0, true, 'eřřšt', -10, '2015-04-29 00:00:00', '2015-04-29 00:00:00', NULL, 1, 10);
INSERT INTO sqa_hours_worked (hoursworkedid, version, visible, description, numberofhours, timefrom, timeto, project_projectid, statehoursworked_statehoursworkedid, user_id) VALUES (233, 0, true, 'velice tvrdá práce', 1, '2015-05-05 00:00:00', '2015-05-05 00:00:00', 229, 1, 4);
INSERT INTO sqa_hours_worked (hoursworkedid, version, visible, description, numberofhours, timefrom, timeto, project_projectid, statehoursworked_statehoursworkedid, user_id) VALUES (241, 1, true, '3 dny bez přestání pracoval', 2, '2015-05-07 00:00:00', '2015-05-08 00:00:00', 217, 2, 4);
INSERT INTO sqa_hours_worked (hoursworkedid, version, visible, description, numberofhours, timefrom, timeto, project_projectid, statehoursworked_statehoursworkedid, user_id) VALUES (234, 1, true, 'flákání', 17, '2015-05-06 00:00:00', '2015-05-07 00:00:00', 225, 3, 13);
INSERT INTO sqa_hours_worked (hoursworkedid, version, visible, description, numberofhours, timefrom, timeto, project_projectid, statehoursworked_statehoursworkedid, user_id) VALUES (255, 1, true, 'flákání', 17, '2015-05-06 00:00:00', '2015-05-09 00:00:00', 244, 3, 13);
INSERT INTO sqa_hours_worked (hoursworkedid, version, visible, description, numberofhours, timefrom, timeto, project_projectid, statehoursworked_statehoursworkedid, user_id) VALUES (256, 1, true, 'vážně těžká a nekonečná práce', 1, '2015-05-09 00:00:00', '2015-05-09 00:00:00', 250, 2, 4);


INSERT INTO sqa_type_knowledge (typeKnowledgeId,value) VALUES (103, 'Testování');
INSERT INTO sqa_type_knowledge (typeKnowledgeId,value) VALUES (104, 'Linux server');
INSERT INTO sqa_type_knowledge (typeKnowledgeId,value) VALUES (113, 'C/C++/C#');
INSERT INTO sqa_type_knowledge (typeKnowledgeId,value) VALUES (114, 'HTML, CSS');
INSERT INTO sqa_type_knowledge (typeKnowledgeId,value) VALUES (115, 'JavaScript');
INSERT INTO sqa_type_knowledge (typeKnowledgeId,value) VALUES (190, 'JQuery');


INSERT INTO sqa_knowledge (userhasknowledgeid, version, visible, levelofknowledge_levelofknowledgeid, typeknowledge_typeknowledgeid) VALUES (235, 0, true, 0, 2);
INSERT INTO sqa_knowledge (userhasknowledgeid, version, visible, levelofknowledge_levelofknowledgeid, typeknowledge_typeknowledgeid) VALUES (236, 0, true, 4, 103);
INSERT INTO sqa_knowledge (userhasknowledgeid, version, visible, levelofknowledge_levelofknowledgeid, typeknowledge_typeknowledgeid) VALUES (237, 0, true, 2, 113);
INSERT INTO sqa_knowledge (userhasknowledgeid, version, visible, levelofknowledge_levelofknowledgeid, typeknowledge_typeknowledgeid) VALUES (238, 0, true, 1, 0);
INSERT INTO sqa_knowledge (userhasknowledgeid, version, visible, levelofknowledge_levelofknowledgeid, typeknowledge_typeknowledgeid) VALUES (259, 0, true, 2, 0);
INSERT INTO sqa_knowledge (userhasknowledgeid, version, visible, levelofknowledge_levelofknowledgeid, typeknowledge_typeknowledgeid) VALUES (260, 0, true, 1, 2);
INSERT INTO sqa_knowledge (userhasknowledgeid, version, visible, levelofknowledge_levelofknowledgeid, typeknowledge_typeknowledgeid) VALUES (261, 0, true, 0, 1);


INSERT INTO sqa_project_partner (projects_projectid, partners_partnerid) VALUES (229, 222);
INSERT INTO sqa_project_partner (projects_projectid, partners_partnerid) VALUES (250, 249);


INSERT INTO sqa_reward (rewardid, version, visible, amount, date, description, project_projectid, user_id)  VALUES (232, 0, true, 500, '2015-05-06 00:00:00', 'peníze za účast na projektu', 229, 4);

INSERT INTO sqa_user_logged_on_activity (userloggedonactivityid, version, visible, activity_activityid, typeroleonactivity_typeroleonactivityid, user_id) VALUES (224, 0, true, 223, 0, 2);


INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (218, 0, true, 217, 1, 3);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (220, 0, true, 219, 1, 3);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (226, 0, true, 225, 1, 9);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (227, 0, true, 225, 2, 12);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (228, 0, true, 225, 2, 13);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (230, 0, true, 229, 1, 4);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (231, 0, true, 229, 2, 11);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (240, 0, true, 217, 2, 4);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (246, 0, true, 244, 1, 4);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (247, 0, true, 244, 2, 11);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (245, 1, true, 244, 2, 3);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (251, 0, true, 250, 1, 3);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (252, 0, true, 250, 2, 4);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (253, 0, true, 250, 2, 11);
INSERT INTO sqa_user_on_project (useronprojectid, version, visible, project_projectid,typeuseronproject_typeuseronprojectid, user_id) VALUES (254, 0, true, 244, 2, 13);


INSERT INTO sqa_user_sqa_academic_work (sqa_user_id, academicworks_academicworkid) VALUES (13, 239);


INSERT INTO sqa_user_sqa_knowledge (sqa_user_id, knowledges_userhasknowledgeid) VALUES (13, 236);
INSERT INTO sqa_user_sqa_knowledge (sqa_user_id, knowledges_userhasknowledgeid) VALUES (13, 237);
INSERT INTO sqa_user_sqa_knowledge (sqa_user_id, knowledges_userhasknowledgeid) VALUES (13, 238);
INSERT INTO sqa_user_sqa_knowledge (sqa_user_id, knowledges_userhasknowledgeid) VALUES (13, 235);
INSERT INTO sqa_user_sqa_knowledge (sqa_user_id, knowledges_userhasknowledgeid) VALUES (11, 261);
INSERT INTO sqa_user_sqa_knowledge (sqa_user_id, knowledges_userhasknowledgeid) VALUES (11, 259);
INSERT INTO sqa_user_sqa_knowledge (sqa_user_id, knowledges_userhasknowledgeid) VALUES (11, 260);

# --- !Downs
