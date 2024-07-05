CREATE TABLE GASTOS (
    ID          SERIAL              not null,
    NOME        VARCHAR(200)        not null,
    DESCRICAO   VARCHAR(1000)       not null,
    DATA        DATE                not null,
    VALOR       NUMERIC(15, 2)      not null,
    constraint GASTOS_PK primary key(ID)
);

CREATE SEQUENCE gastos_seq
INCREMENT 1
START 1
OWNED BY GASTOS.ID;

CREATE TABLE TAGS (
    ID          SERIAL              not null,
    NOME        VARCHAR(200)        not null,
    constraint TAGS_PK primary key(ID)
);

CREATE SEQUENCE tags_seq
INCREMENT 1
START 1
OWNED BY TAGS.ID;

CREATE TABLE GASTOS_TAGS (
    ID          SERIAL          not null,
    GASTO_ID    SERIAL          not null,
    TAG_ID      SERIAL          not null,
    constraint GASTOS_TAGS_PK primary key(ID),
    constraint GASTOS_FK foreign key(GASTO_ID) references GASTOS(ID),
    constraint TAGS_FK foreign key(TAG_ID) references TAGS(ID)
);

CREATE SEQUENCE gastos_tags_seq
INCREMENT 1
START 1
OWNED BY GASTOS_TAGS.ID;