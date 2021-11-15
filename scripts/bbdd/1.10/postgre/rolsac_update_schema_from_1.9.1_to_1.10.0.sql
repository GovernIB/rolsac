ALTER TABLE RSC_PROCED ADD PRO_PDTVAL BOOL DEFAULT FALSE;
ALTER TABLE RSC_SERVIC ADD SER_PDTVAL BOOL DEFAULT FALSE;

CREATE TABLE RSC_SERMSJ (
SMN_CODI int8 not null,
   SMN_USUARIO varchar(100),
   SMN_USULEC varchar(100),
   SMN_TEXTO varchar(256),
   SMN_FECCRE timestamp,
   SMN_FECLEC timestamp,
   SMN_GESTOR bool,
   SMN_LEIDO bool,
   SMN_SERCODI int8,
   primary key (SMN_CODI),
  CONSTRAINT RSC_SERMSJ_FK
    FOREIGN KEY (SMN_SERCODI)
    REFERENCES RSC_SERVIC (SER_CODI)

);


CREATE TABLE RSC_PROMSJ (
  PMN_CODI int8 not null,
   PMN_USUARIO varchar(100),
   PMN_USULEC varchar(100),
   PMN_TEXTO varchar(256),
   PMN_FECCRE timestamp,
   PMN_FECLEC timestamp,
   PMN_GESTOR bool,
   PMN_LEIDO bool,
   PMN_PROCODI int8,
   primary key (PMN_CODI),
  CONSTRAINT RSC_PROMSJ_FK
    FOREIGN KEY (PMN_PROCODI)
    REFERENCES RSC_PROCED (PRO_CODI)

);

create sequence RSC_SEQ_PMN;
create sequence RSC_SEQ_SMN;

CREATE TABLE RSC_MSJMAI (
  MAI_CODI int8 not null,
   MAI_FROM varchar(100),
   MAI_TO varchar(100),
   MAI_ENVIAD bool,
   MAI_TITUL varchar(356),
   MAI_CONTE varchar(500),
   MAI_ERROR varchar(500),
   MAI_TIPO varchar(3),
   MAI_PROSRV int8,
   MAI_FECCRE timestamp,
   MAI_FECENV timestamp,
   primary key (MAI_CODI)

);

create sequence RSC_SEQ_MAI;

/** SE AUMENTA A 356, SIENDO 256 EL TAMAÑO TITULO DE PROC/SERV Y 100 MÁS PARA EL LITERAL DEL JBOSS-SERVICE. **/
ALTER TABLE RSC_MSJMAI ALTER COLUMN MAI_TITUL TYPE VARCHAR(356);