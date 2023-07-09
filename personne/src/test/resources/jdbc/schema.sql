Create SCHEMA public;
SET SCHEMA public;

CREATE SEQUENCE public.person_ID_seq;

CREATE TABLE IF NOt EXISTS public.Person (
    id integer AUTO_INCREMENT,
    lastname character varying(15) NOT NULL,
    firstname character varying(15) NOT NULL,
    birthday date NOT NULL
);