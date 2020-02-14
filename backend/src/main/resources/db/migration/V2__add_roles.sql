CREATE TABLE public.roles (
	id serial NOT NULL,
	"name" varchar(60) NULL,
	CONSTRAINT roles_pk PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
) ;

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

CREATE TABLE public.user_roles (
	user_id int4 NOT NULL,
	role_id int4 NOT NULL,
	CONSTRAINT user_roles_pk PRIMARY KEY (user_id,role_id)
)
WITH (
	OIDS=FALSE
) ;
