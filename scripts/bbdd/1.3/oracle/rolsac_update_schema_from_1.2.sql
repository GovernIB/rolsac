-- TAULA RSC_PERGES
CREATE TABLE RSC_PERGES
(
	PEG_CODI    NUMBER(19)	NOT NULL,
 	PEG_ORDEN   NUMBER(10),
	PEG_CODEST  VARCHAR2(256),
	PEG_DUPLIC  VARCHAR2(1) 
);
ALTER TABLE RSC_PERGES ADD (CONSTRAINT RSC_PERGES_PK PRIMARY KEY (PEG_CODI));

-- TAULA RSC_TRAPEG
CREATE TABLE RSC_TRAPEG
(
  TPG_CODPEG  NUMBER(19)	NOT NULL,
  TPG_NOMBRE  VARCHAR2(256),
  TPG_DESCRI  VARCHAR2(4000),
  TPG_CODIDI  VARCHAR2(2)	NOT NULL
);
ALTER TABLE RSC_TRAPEG ADD (CONSTRAINT RSC_TRAPEG_PK PRIMARY KEY (TPG_CODPEG, TPG_CODIDI));
ALTER TABLE RSC_TRAPEG ADD (CONSTRAINT RSC_TPGPEG_FK FOREIGN KEY (TPG_CODPEG) REFERENCES RSC_PERGES (PEG_CODI));
CREATE INDEX RSC_TPG_CODPEG_FK_I ON RSC_TRAPEG(TPG_CODPEG);

-- TAULA RSC_PEGSEC
CREATE TABLE RSC_PEGSEC
(
  PGS_CODPEG  NUMBER(19)	NOT NULL,
  PGS_CODSEC  NUMBER(19)	NOT NULL
);

ALTER TABLE RSC_PEGSEC ADD (CONSTRAINT RSC_PEGSEC_PK PRIMARY KEY (PGS_CODPEG, PGS_CODSEC));
ALTER TABLE RSC_PEGSEC ADD (CONSTRAINT RSC_PGSPEG_FK FOREIGN KEY (PGS_CODPEG) REFERENCES RSC_PERGES (PEG_CODI));
ALTER TABLE RSC_PEGSEC ADD (CONSTRAINT RSC_PGSSEC_FK FOREIGN KEY (PGS_CODSEC) REFERENCES RSC_SECCIO (SEC_CODI));
CREATE INDEX RSC_PGS_CODPEG_FK_I ON RSC_PEGSEC(PGS_CODPEG);
CREATE INDEX RSC_PGS_CODSEC_FK_I ON RSC_PEGSEC(PGS_CODSEC);

-- TAULA RSC_USUPEG
CREATE TABLE RSC_USUPEG
(
  USP_CODUSU  NUMBER(19)	NOT NULL,
  USP_CODPEG  NUMBER(19)	NOT NULL
);
ALTER TABLE RSC_USUPEG ADD (CONSTRAINT RSC_USUPEG_PK PRIMARY KEY (USP_CODUSU, USP_CODPEG));
ALTER TABLE RSC_USUPEG ADD (CONSTRAINT RSC_USPUSU_FK FOREIGN KEY (USP_CODUSU) REFERENCES RSC_USUARI (USU_CODI));
ALTER TABLE RSC_USUPEG ADD (CONSTRAINT RSC_USPPGS_FK FOREIGN KEY (USP_CODPEG) REFERENCES RSC_PERGES (PEG_CODI));
CREATE INDEX RSC_USP_CODUSU_FK_I ON RSC_USUPEG(USP_CODUSU);
CREATE INDEX RSC_USP_CODUSU_FK_I ON RSC_USUPEG(USP_CODUSU);

ALTER TABLE RSC_EDIFIC MODIFY EDI_LAT VARCHAR2(64 CHAR);
ALTER TABLE RSC_EDIFIC MODIFY EDI_LNG VARCHAR2(64 CHAR);

-- TAULA RSC_PROCED ISSUE #351
ALTER TABLE RSC_PROCED ADD PRO_CODUNA_SERV number(19,0);  
  
