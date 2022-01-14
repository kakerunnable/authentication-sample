INSERT INTO authority(id, name) VALUES (nextval('authority_sequence'), 'ROLE_USER');
INSERT INTO authority(id, name) VALUES (nextval('authority_sequence'), 'ROLE_ADMIN');

INSERT INTO user_info(id, email, enabled, password, provider, username)
VALUES (nextval('user_sequence'),'admin@mail.com', 'true', '$2a$10$zgPrRttBhV9k8kI5AOv2P.dVXX6HFdynM.7lXp4RJkLFaywzAxIOy', 'LOCAL', 'Admin');

INSERT INTO user_authority(user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority(user_id, authority_id) VALUES (1, 2);
