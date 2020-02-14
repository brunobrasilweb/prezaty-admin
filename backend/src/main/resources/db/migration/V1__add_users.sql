CREATE TABLE public.users (
	id serial NOT NULL,
	name varchar(60) NOT NULL,
	email varchar(150) NOT NULL,
	password varchar(100) NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id)
)
WITH (
	OIDS=FALSE
);
