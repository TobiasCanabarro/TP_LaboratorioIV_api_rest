
CREATE SEQUENCE lab.user_id_user_seq;

CREATE TABLE lab.user (
                id_user BIGINT NOT NULL DEFAULT nextval('lab.user_id_user_seq'),
                name VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                surname VARCHAR NOT NULL,
                email VARCHAR NOT NULL,
                nickname VARCHAR NOT NULL,
                birthday DATE NOT NULL,
                attempt_login INTEGER NOT NULL,
                log_in BOOLEAN NOT NULL,
                locked BOOLEAN NOT NULL,
                CONSTRAINT user_pk PRIMARY KEY (id_user)
);


ALTER SEQUENCE lab.user_id_user_seq OWNED BY lab.user.id_user;

CREATE SEQUENCE lab.request_relationship_id_request_seq;

CREATE TABLE lab.request_relationship (
                id_request BIGINT NOT NULL DEFAULT nextval('lab.request_relationship_id_request_seq'),
                id_user_receive BIGINT NOT NULL,
                id_user_send BIGINT NOT NULL,
                state BOOLEAN NOT NULL,
                CONSTRAINT request_relationship_pk PRIMARY KEY (id_request)
);


ALTER SEQUENCE lab.request_relationship_id_request_seq OWNED BY lab.request_relationship.id_request;

CREATE SEQUENCE lab.user_post_id_publication_seq;

CREATE TABLE lab.user_post (
                id_publication BIGINT NOT NULL DEFAULT nextval('lab.user_post_id_publication_seq'),
                id_user BIGINT NOT NULL,
                date_publication DATE NOT NULL,
                post VARCHAR NOT NULL,
                CONSTRAINT user_post_pk PRIMARY KEY (id_publication)
);


ALTER SEQUENCE lab.user_post_id_publication_seq OWNED BY lab.user_post.id_publication;

CREATE SEQUENCE lab.image_id_image_seq;

CREATE TABLE lab.image (
                id_image BIGINT NOT NULL DEFAULT nextval('lab.image_id_image_seq'),
                id_publication BIGINT NOT NULL,
                image VARCHAR NOT NULL,
                CONSTRAINT image_pk PRIMARY KEY (id_image)
);


ALTER SEQUENCE lab.image_id_image_seq OWNED BY lab.image.id_image;

ALTER TABLE lab.user_post ADD CONSTRAINT user_publication_fk
FOREIGN KEY (id_user)
REFERENCES lab.user (id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE lab.request_relationship ADD CONSTRAINT user_request_relationship_fk
FOREIGN KEY (id_user_send)
REFERENCES lab.user (id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE lab.request_relationship ADD CONSTRAINT user_request_relationship_fk1
FOREIGN KEY (id_user_receive)
REFERENCES lab.user (id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE lab.image ADD CONSTRAINT publication_image_fk
FOREIGN KEY (id_publication)
REFERENCES lab.user_post (id_publication)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;