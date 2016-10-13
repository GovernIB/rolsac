 --AFEGIR CAMP PER CORRESPONDENCIA UAS DIR3-ROLSAC
ALTER TABLE RSC_UNIADM ADD UNA_CODDR3 VARCHAR2(12 CHAR);
COMMENT ON COLUMN RSC_UNIADM.UNA_CODDR3 IS 'CODI UNITAT ADMINISTRATIVA DIR3';  

 --AFEGIR TAULA TIPUS NORMATIVES SIA
CREATE TABLE RSC_SIATNR (
  SNR_CODTNR bigint,
  SNR_NOMBRE character varying(256)
);
ALTER TABLE RSC_SIATNR ADD (CONSTRAINT RSC_SIATNR_PK PRIMARY KEY (SNR_CODTNR));
COMMENT ON COLUMN RSC_SIATNR.SNR_CODTNR IS 'CODI TIPUS NORMA SIA';
COMMENT ON COLUMN RSC_SIATNR.SNR_NOMBRE IS 'NOM TIPUS NORMA SIA';

 --AFEGIR TAULA MATERIES SIA
CREATE TABLE RSC_SIAMAT (
  SMT_CODMAT bigint,
  SMT_NOMBRE character varying(256)
);
ALTER TABLE RSC_SIAMAT ADD (CONSTRAINT RSC_SIAMAT_PK PRIMARY KEY (SMT_CODMAT));
COMMENT ON COLUMN RSC_SIAMAT.SMT_CODMAT IS 'CODI MATERIA SIA';
COMMENT ON COLUMN RSC_SIAMAT.SMT_NOMBRE IS 'NOM MATERIA SIA';

 --AFEGIR CAMP PER CORRESPONDENCIA MATERIES SIA-ROLSAC
ALTER TABLE RSC_MATERI ADD (MAT_CODSIA NUMBER(19,0));
COMMENT ON COLUMN RSC_MATERI.MAT_CODSIA IS 'CODI MATERIA SIA';
ALTER TABLE RSC_MATERI ADD (CONSTRAINT RSC_MATSMT_FK  FOREIGN KEY (MAT_CODSIA) REFERENCES RSC_SIAMAT (SMT_CODMAT));

 --AFEGIR CAMP PER CORRESPONDENCIA TIPUS NORMATIVES SIA-ROLSAC
ALTER TABLE RSC_TIPO ADD (TIP_CODSIA NUMBER(19,0));
COMMENT ON COLUMN RSC_TIPO.TIP_CODSIA IS 'CODI TIPUS NORMA SIA';
ALTER TABLE RSC_TIPO ADD (CONSTRAINT RSC_TIPSNR_FK  FOREIGN KEY (TIP_CODSIA) REFERENCES RSC_SIATNR (SNR_CODTNR));

 --AFEGIR CAMP PER CORRESPONDENCIA UAS SIA-ROLSAC
ALTER TABLE RSC_PROCED ADD PRO_CODSIA VARCHAR2(12 CHAR);
COMMENT ON COLUMN RSC_PROCED.PRO_CODSIA IS 'CODI SIA';

 --Crear tabla silenci
CREATE TABLE RSC_SILADM (
  SIL_CODI integer NOT NULL ENABLE,
  primary key (SIL_CODI)
);
COMMENT ON COLUMN RSC_SILADM.SIL_CODI IS 'CODI SILENCI ADM';

create table RSC_TRASIL (
   TSI_CODSIL integer not null,
   TSI_NOMBRE character varying(256),
   TSI_DESCRI character varying(4000),
   TSI_CODIDI character varying(2) not null,
   primary key (TSI_CODSIL, TSI_CODIDI)
);
alter table RSC_TRASIL add constraint RSC_TSISIL_FK foreign key (TSI_CODSIL) references RSC_SILADM;

 --AFEGIR CAMP PER SILENCI
ALTER TABLE RSC_PROCED ADD PRO_CODSIL integer;
COMMENT ON COLUMN RSC_PROCED.PRO_CODSIL IS 'CODI SILENCI';
alter table RSC_PROCED add constraint RSC_PRO_CODSIL_SERV_FK foreign key (PRO_CODSIL) references RSC_SILADM;
  