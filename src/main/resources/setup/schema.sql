CREATE TABLE public.task (
  id BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('task_seq'::regclass),
  completed BOOLEAN,
  created_at TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL::timestamp without time zone,
  description CHARACTER VARYING(64) NOT NULL,
  due_to TIMESTAMP(0) WITHOUT TIME ZONE DEFAULT NULL::timestamp without time zone,
  email CHARACTER VARYING(64) NOT NULL,
  task_priority CHARACTER VARYING(30) NOT NULL,
  task_status CHARACTER VARYING(30) NOT NULL
);
CREATE TABLE public.users (
  username CHARACTER VARYING(100) PRIMARY KEY NOT NULL,
  password CHARACTER VARYING(100) NOT NULL,
  enabled SMALLINT NOT NULL
);
CREATE TABLE public.authorities (
  username CHARACTER VARYING(100) NOT NULL,
  authority CHARACTER VARYING(100) NOT NULL,
  FOREIGN KEY (username) REFERENCES public.users (username)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
);
CREATE UNIQUE INDEX ix_auth_username ON authorities USING BTREE (username, authority);




