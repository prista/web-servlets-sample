CREATE TABLE "user_profile" (
  "id" serial NOT NULL,
  "name" character varying NOT NULL,
  "password" character varying NOT NULL,
  CONSTRAINT user_profile_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "resource" (
  "id" serial NOT NULL,
  "path" character varying NOT NULL,
  CONSTRAINT resource_pk PRIMARY KEY ("id")
) WITH (
OIDS=FALSE
);



CREATE TABLE "user_profile_2_resource" (
  "user_profile_id" integer NOT NULL,
  "resource_id" integer NOT NULL
) WITH (
OIDS=FALSE
);





ALTER TABLE "user_profile_2_resource" ADD CONSTRAINT "user_profile_2_resource_fk0" FOREIGN KEY ("user_profile_id") REFERENCES "user_profile"("id");
ALTER TABLE "user_profile_2_resource" ADD CONSTRAINT "user_profile_2_resource_fk1" FOREIGN KEY ("resource_id") REFERENCES "resource"("id");


ALTER TABLE user_profile_2_resource
  ADD CONSTRAINT user_profile_2_resource_user_profile_id_resource_id_key UNIQUE (resource_id, user_profile_id);