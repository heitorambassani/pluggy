-- -----------------------------------------------------
-- Table EventEntity
-- -----------------------------------------------------
CREATE TABLE EventEntity
(
    id          bigserial              NOT NULL,
    itemid      character varying(200) NOT NULL,
    event       character varying(200) NOT NULL,
    eventid     character varying(200) NOT NULL,
    triggeredby character varying(200) NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY (id)
);