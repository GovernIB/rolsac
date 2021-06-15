ALTER TABLE RSC_PROCED ADD PRO_PDTVAL NUMBER(1,0) DEFAULT 0;
ALTER TABLE RSC_SERVIC ADD SER_PDTVAL NUMBER(1,0) DEFAULT 0;


CREATE TABLE RSC_SERMSJ (
  SMN_CODI NUMBER(19,0),
  SMN_SERCODI NUMBER(19,0),
  SMN_USUARIO VARCHAR2(100 CHAR),
  SMN_USULEC VARCHAR2(100 CHAR),
  SMN_GESTOR NUMBER(1,0) DEFAULT 0,
  SMN_TEXTO VARCHAR2(256 CHAR),
  SMN_FECCRE DATE,
  SMN_FECLEC DATE,
  SMN_LEIDO NUMBER(1,0) DEFAULT 0,
  CONSTRAINT RSC_SERMSJ_PK PRIMARY KEY (SMN_CODI),
  CONSTRAINT RSC_SERMSJ_FK
    FOREIGN KEY (SMN_SERCODI)
    REFERENCES RSC_SERVIC (SER_CODI)

);


CREATE TABLE RSC_PROMSJ (
  PMN_CODI NUMBER(19,0),
  PMN_PROCODI NUMBER(19,0),
  PMN_USUARIO VARCHAR2(100 CHAR),
  PMN_USULEC VARCHAR2(100 CHAR),
  PMN_GESTOR NUMBER(1,0) DEFAULT 0,
  PMN_TEXTO VARCHAR2(256 CHAR),
  PMN_FECCRE DATE,
  PMN_FECLEC DATE,
  PMN_LEIDO NUMBER(1,0) DEFAULT 0,
  CONSTRAINT RSC_PROMSJ_PK PRIMARY KEY (PMN_CODI),
  CONSTRAINT RSC_PROMSJ_FK
    FOREIGN KEY (PMN_PROCODI)
    REFERENCES RSC_PROCED (PRO_CODI)

);

create sequence RSC_SEQ_PMN;
create sequence RSC_SEQ_SMN;