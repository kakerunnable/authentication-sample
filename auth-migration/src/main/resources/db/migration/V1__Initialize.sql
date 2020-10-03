CREATE TABLE user_info (
	id BIGINT NOT NULL,
	email VARCHAR(100) UNIQUE,
	enabled BOOLEAN,
	password VARCHAR(200),
	provider VARCHAR(255),
	username VARCHAR(50) UNIQUE,
    ${commonColumns},
	CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE authority (
	id BIGINT NOT NULL,
	name VARCHAR(100) UNIQUE,
	${commonColumns},
	CONSTRAINT authority_pk PRIMARY KEY (id)
);

CREATE TABLE user_authority (
	user_id bigint NOT NULL,
	authority_id bigint NOT NULL,
	${commonColumns},
	CONSTRAINT user_authority_pk PRIMARY KEY (user_id, authority_id),
	CONSTRAINT user_authority_fk1 FOREIGN KEY (user_id) REFERENCES user_info (id),
	CONSTRAINT user_authority_fk2 FOREIGN KEY (authority_id) REFERENCES authority (id)
);

CREATE SEQUENCE authority_sequence;
CREATE SEQUENCE user_sequence;


