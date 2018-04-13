--Tabla de relacion de documentos con normativa y su traducci�n.
CREATE TABLE RSC_DOCNOR
(
  DNO_CODI   NUMBER(19,0) NOT NULL ENABLE,
  DNO_CODNOR NUMBER(19,0) NOT NULL ENABLE,
  DNO_ORDEN  NUMBER(19,0) NOT NULL ENABLE,
  PRIMARY KEY (DNO_CODI),
  CONSTRAINT RSC_DOCNOR_ORDEN_UNIQUE UNIQUE (DNO_CODNOR, DNO_ORDEN) ENABLE,
  CONSTRAINT RSC_DOCNOR_NORMAT_FK FOREIGN KEY (DNO_CODNOR) REFERENCES RSC_NORMAT (NOR_CODI) ENABLE
);
COMMENT ON TABLE RSC_DOCNOR IS 'Documento asociado a una normativa';
COMMENT ON COLUMN RSC_DOCNOR.DNO_CODI is 'Identificador del documento';
COMMENT ON COLUMN RSC_DOCNOR.DNO_CODNOR is 'Identificador de la normativa';
COMMENT ON COLUMN RSC_DOCNOR.ORDEN is 'Orden del documento';
 
CREATE TABLE RSC_TRADNR
(
    TDN_CODDNR NUMBER(19,0) NOT NULL ENABLE,
    TDN_TITULO VARCHAR2(512 CHAR),
    TDN_ENLACE VARCHAR2(512 CHAR),
    TDN_DESCRI VARCHAR2(4000 CHAR),
    TDN_CODARC NUMBER(19,0),
    TDN_CODIDI VARCHAR2(2 BYTE) NOT NULL ENABLE,
    PRIMARY KEY (TDN_CODDNR, TDN_CODIDI),
    CONSTRAINT RSC_TRADNR_ARCHIV_FK FOREIGN KEY (TDN_CODARC) REFERENCES RSC_ARCHIV (ARC_CODI) ENABLE,
    CONSTRAINT RSC_TRADNR_DOCNOR_FK FOREIGN KEY (TDN_CODDNR) REFERENCES RSC_DOCNOR (DNO_CODI) ENABLE
);

COMMENT ON TABLE RSC_TRADNR IS 'Traducci�n del documento asociado a una normativa';
COMMENT ON COLUMN RSC_TRADNR.TDN_CODDNR is 'Identificador de la normativa. PK - 1';
COMMENT ON COLUMN RSC_TRADNR.TDN_CODIDI is 'Identificador del idioma (es / ca). PK - 2';
COMMENT ON COLUMN RSC_TRADNR.TDN_TITULO is 'Titulo del documento para mostrar en el modulo.';
COMMENT ON COLUMN RSC_TRADNR.TDN_CODARC is 'Identificador del archivo.';
COMMENT ON COLUMN RSC_TRADNR.TDN_ENLACE is 'Texto con el enlace URL.';

--Tabla de relacion con las unidad administrativas y normativas.
CREATE TABLE RSC_UNANOR
(
  UNN_CODNOR NUMBER(19,0) NOT NULL ENABLE,
  UNN_CODUNA NUMBER(19,0) NOT NULL ENABLE,
  UNN_CODI   NUMBER(19,0) NOT NULL ENABLE,
  CONSTRAINT RSC_UNN_PK PRIMARY KEY (UNN_CODI),
  CONSTRAINT RSC_UNNUNA_FK FOREIGN KEY (UNN_CODUNA) REFERENCES RSC_UNIADM (UNA_CODI) ENABLE,
  CONSTRAINT RSC_UNNNOR_FK FOREIGN KEY (UNN_CODNOR) REFERENCES RSC_NORMAT (NOR_CODI) ENABLE,
  UNIQUE (UNN_CODNOR, UNN_CODUNA)
);
COMMENT ON TABLE RSC_UNANOR IS 'Tabla que relaciona las UAs y las normativas';
COMMENT ON COLUMN RSC_UNANOR.UNN_CODNOR is 'Identificador de la normativa.';
COMMENT ON COLUMN RSC_UNANOR.UNN_CODI is 'Identificador de la tabla que relaciona normativas y UAs.';
COMMENT ON COLUMN RSC_UNANOR.UNN_CODUNA is 'Identificador de la UA.';


--Cambios en las normativas. 
ALTER TABLE RSC_NORMAT ADD NOR_TYPEN	VARCHAR2(64 CHAR); --Nuevo tipo que es normativa o normativaExterna
ALTER TABLE RSC_NORMAT ADD NOR_VALIDN	NUMBER(10, 0); --Nuevo valida
ALTER TABLE RSC_NORMAT ADD NOR_CODBOL_ANT	NUMBER(19, 0); --Codigo bolet�n antiguo
ALTER TABLE RSC_NORMAT ADD NOR_NUMNOR VARCHAR2(10 CHAR); --Numero normativa.
ALTER TABLE RSC_NORMAT MODIFY (NOR_TYPE NULL); --Ahora el tipo puede ser nulo
--ALTER TABLE RSC_NORMAT MODIFY (NOR_TYPEN NOT NULL); --Ahora el tipon ya no es nulo
ALTER TABLE RSC_NORMAT ADD NOR_DATVAL NUMBER(1,0); --Datos validados
COMMENT ON COLUMN RSC_NORMAT.NOR_NUMNOR is 'Numero de la normativa.';
COMMENT ON COLUMN RSC_NORMAT.NOR_TYPEN is 'Nuevo tipo de la normativa.';
COMMENT ON COLUMN RSC_NORMAT.NOR_VALIDN is 'Nuevo valida de la normativa.';
COMMENT ON COLUMN RSC_NORMAT.NOR_DATVAL is 'Indica si los datos de la normativa estan validados siendo 0 NO y 1 SI (y por tanto esta correcto y bien depurado).';

  
--Renombra la vista de traduccion de normativas.  
RENAME RSC_TRANOR to RSC_TRANOR_VIEW;

--Crea la tabla de traduccion de normativas.
CREATE TABLE RSC_TRANOR
  (
    TNO_CODNOR NUMBER(19,0) NOT NULL ENABLE,
    TNO_SECCIO VARCHAR2(256 CHAR),
    TNO_APARTA VARCHAR2(256 CHAR),
    TNO_PAGINI NUMBER(10,0),
    TNO_PAGFIN NUMBER(10,0),
    TNO_TITULO VARCHAR2(1024 CHAR),
    TNO_ENLACE VARCHAR2(512 CHAR),
    TNO_RESPON VARCHAR2(512 CHAR),
    TNO_CODARC NUMBER(19,0),
    TNO_OBSERV VARCHAR2(4000 CHAR),
    TNO_CODIDI VARCHAR2(2 CHAR) NOT NULL ENABLE,
    CONSTRAINT RSC_TNO_PK PRIMARY KEY (TNO_CODNOR, TNO_CODIDI) ENABLE,
    CONSTRAINT RSC_TNONOR_FK FOREIGN KEY (TNO_CODNOR) REFERENCES RSC_NORMAT (NOR_CODI) ENABLE,
    CONSTRAINT RSC_TNOARC_FK FOREIGN KEY (TNO_CODARC) REFERENCES RSC_ARCHIV (ARC_CODI) ENABLE
  ) ;
  
  
--Tabla servicios y sus traducciones
CREATE TABLE RSC_SERVIC
(
    SER_CODI         NUMBER(19,0) NOT NULL ENABLE,
    SER_VALIDA       NUMBER(10,0),
    SER_CODIGO       VARCHAR2(256 CHAR),
    SER_CODSIA       VARCHAR2(12 CHAR),
    SER_ESTSIA       VARCHAR2(1 BYTE),
    SER_FECSIA       DATE,
    SER_TASURL       VARCHAR2(256 CHAR),
    SER_NOMRSP       VARCHAR2(256 CHAR),
    SER_CORREO       VARCHAR2(256 CHAR),
    SER_TELEFO       VARCHAR2(256 CHAR),
    SER_INSTRU       NUMBER(19,0),
    SER_SERRSP       NUMBER(19,0),
    SER_FECPUB       DATE,
    SER_FECDES       DATE,
    SER_FECACT       DATE,
    SER_TRAULR		 VARCHAR2(256 CHAR),
    SER_TRAID		 VARCHAR2(256 CHAR),
    SER_TRAVER		 VARCHAR2(256 CHAR),
    CONSTRAINT RSC_SER_PK             PRIMARY KEY (SER_CODI),
    CONSTRAINT RSC_SER_CODSIA_UNIQUE  UNIQUE (SER_CODSIA),
    CONSTRAINT RSC_SER_INSTRU_FK      FOREIGN KEY (SER_INSTRU)        REFERENCES RSC_UNIADM (UNA_CODI) ENABLE,
    CONSTRAINT RSC_SET_SERRSP_FK      FOREIGN KEY (SER_SERRSP)        REFERENCES RSC_UNIADM (UNA_CODI) ENABLE
);
  
COMMENT ON TABLE RSC_SERVIC IS 'Catalogo de servicio.';
COMMENT ON COLUMN RSC_SERVIC.SER_CODI   is 'Identificador del documento';  
COMMENT ON COLUMN RSC_SERVIC.SER_VALIDA is 'Valida';  
COMMENT ON COLUMN RSC_SERVIC.SER_CODIGO is 'Codigo';  
COMMENT ON COLUMN RSC_SERVIC.SER_CODSIA is 'Codigo SIA';  
COMMENT ON COLUMN RSC_SERVIC.SER_ESTSIA is 'Estado SIA';  
COMMENT ON COLUMN RSC_SERVIC.SER_FECSIA is 'Fecha  SIA';  
COMMENT ON COLUMN RSC_SERVIC.SER_TASURL is 'Tasa   URL';  
COMMENT ON COLUMN RSC_SERVIC.SER_NOMRSP is 'Nombre responsable';  
COMMENT ON COLUMN RSC_SERVIC.SER_CORREO is 'Correo';  
COMMENT ON COLUMN RSC_SERVIC.SER_TELEFO is 'Telefono';  
COMMENT ON COLUMN RSC_SERVIC.SER_INSTRU is 'Organo Instructor';  
COMMENT ON COLUMN RSC_SERVIC.SER_SERRSP is 'Servicio responsable';  


CREATE TABLE RSC_TRASER (
  TSR_CODSER NUMBER(19,0) NOT NULL ENABLE,
  TSR_NOMBRE VARCHAR2(256 CHAR),
  TSR_OBJETO VARCHAR2(4000 CHAR),
  TSR_DESTIN CLOB,
  TSR_REQUIS CLOB,
  TSR_OBSERV CLOB,
  TSR_CODIDI       VARCHAR2(2 CHAR) NOT NULL ENABLE,
  CONSTRAINT RSC_TSR_PK PRIMARY KEY (TSR_CODSER, TSR_CODIDI),
  CONSTRAINT RSC_TRASER_SER_FK FOREIGN KEY (TSR_CODSER) REFERENCES RSC_SERVIC (SER_CODI) ENABLE
);  

COMMENT ON TABLE  RSC_TRASER IS 'Traduccion Catalogo de servicio.';
COMMENT ON COLUMN RSC_TRASER.TSR_CODSER   is 'Identificador del servicio';  
COMMENT ON COLUMN RSC_TRASER.TSR_NOMBRE is 'Nombre';  
COMMENT ON COLUMN RSC_TRASER.TSR_OBJETO is 'Objeto';  
COMMENT ON COLUMN RSC_TRASER.TSR_DESTIN is 'Codigo SIA';  
COMMENT ON COLUMN RSC_TRASER.TSR_REQUIS is 'Requisitos';  
COMMENT ON COLUMN RSC_TRASER.TSR_OBSERV is 'Observaciones';  
COMMENT ON COLUMN RSC_TRASER.TSR_CODIDI is 'Codigo del idioma';  
  
  
  
--Tabla de relacion de documentos con servicios y su traducci�n.
CREATE TABLE RSC_DOCSER
(
  DSR_CODI   NUMBER(19,0) NOT NULL ENABLE,
  DSR_CODSER NUMBER(19,0) NOT NULL ENABLE,
  ORDEN  NUMBER(19,0) NOT NULL ENABLE,
  PRIMARY KEY (DSR_CODI),
  CONSTRAINT RSC_DSRSER_ORDEN_UNIQUE UNIQUE (DSR_CODSER, ORDEN) ENABLE,
  CONSTRAINT RSC_DSRSER_NORMAT_FK FOREIGN KEY (DSR_CODSER) REFERENCES RSC_SERVIC (SER_CODI) ENABLE
);
COMMENT ON TABLE RSC_DOCSER IS 'Documento asociado a un servicio';
COMMENT ON COLUMN RSC_DOCSER.DSR_CODI is 'Identificador del documento';
COMMENT ON COLUMN RSC_DOCSER.DSR_CODSER is 'Identificador del servicio';
COMMENT ON COLUMN RSC_DOCSER.ORDEN is 'Orden del documento';

CREATE TABLE RSC_TRADSR
(
    TDS_CODDSR NUMBER(19,0) NOT NULL ENABLE,
    TDS_TITULO VARCHAR2(512 CHAR),
    TDS_ENLACE VARCHAR2(512 CHAR),
    TDS_DESCRI VARCHAR2(4000 CHAR),
    TDS_CODARC NUMBER(19,0),
    TDS_CODIDI VARCHAR2(2 BYTE) NOT NULL ENABLE,
    PRIMARY KEY (TDS_CODDSR, TDS_CODIDI),
    CONSTRAINT RSC_TRADSR_ARCHIV_FK FOREIGN KEY (TDS_CODARC) REFERENCES RSC_ARCHIV (ARC_CODI) ENABLE,
    CONSTRAINT RSC_TRADSR_DOCNOR_FK FOREIGN KEY (TDS_CODDSR) REFERENCES RSC_DOCSER (DSR_CODI) ENABLE
);

COMMENT ON TABLE RSC_TRADSR IS 'Traducci�n del documento asociado a un servicios';
COMMENT ON COLUMN RSC_TRADSR.TDS_CODDSR is 'Identificador del doc servicio. PK - 1';
COMMENT ON COLUMN RSC_TRADSR.TDS_CODIDI is 'Identificador del idioma (es / ca). PK - 2';
COMMENT ON COLUMN RSC_TRADSR.TDS_TITULO is 'Titulo del documento para mostrar en el modulo.';
COMMENT ON COLUMN RSC_TRADSR.TDS_CODARC is 'Identificador del archivo.';
COMMENT ON COLUMN RSC_TRADSR.TDS_ENLACE is 'Texto con el enlace URL.';

--Tabla de hechos vitales con servicios
CREATE TABLE RSC_HEVISR
  (
    HVS_CODI   NUMBER(19,0) NOT NULL ENABLE,
    HVS_CODHEV NUMBER(19,0),
    HVS_CODSER NUMBER(19,0),
    HVS_ORDEN  NUMBER(10,0),
    CONSTRAINT RSC_HVS_PK PRIMARY KEY (HVS_CODI) ENABLE,
    CONSTRAINT RSC_HVSHEV_FK FOREIGN KEY (HVS_CODHEV) REFERENCES RSC_HECVIT (HEV_CODI) ENABLE,
    CONSTRAINT RSC_HVSSER_FK FOREIGN KEY (HVS_CODSER) REFERENCES RSC_SERVIC (SER_CODI) ENABLE
  );
  
  
COMMENT ON TABLE RSC_HEVISR IS 'Tabla de la relacion entre hechos vitales y servicios.';
COMMENT ON COLUMN RSC_HEVISR.HVS_CODI is 'Identificador de la tabla';
COMMENT ON COLUMN RSC_HEVISR.HVS_CODHEV is 'Identificador de los hechos vitales';
COMMENT ON COLUMN RSC_HEVISR.HVS_CODSER is 'Identificador del servicio';
COMMENT ON COLUMN RSC_HEVISR.HVS_ORDEN is 'Orden';

--Tabla de historicos
alter table RSC_HISTOR add HIS_CODSER	NUMBER(19,0);
alter table RSC_HISTOR add constraint RSC_HISSER_FK foreign key (HIS_CODSER) references RSC_SERVIC;
COMMENT ON COLUMN RSC_HISTOR.HIS_CODSER is 'Identificador del servicio';


---Tabla de materias con servicios.
CREATE TABLE RSC_SERMAT (
    SRM_CODMAT NUMBER(19,0) NOT NULL ENABLE,
    SRM_CODSER NUMBER(19,0) NOT NULL ENABLE,
    CONSTRAINT RSC_SRM_PK PRIMARY KEY (SRM_CODSER, SRM_CODMAT) ENABLE,
    CONSTRAINT RSC_SRMPRO_FK FOREIGN KEY (SRM_CODSER) REFERENCES RSC_SERVIC (SER_CODI) ENABLE,
    CONSTRAINT RSC_SRMMAT_FK FOREIGN KEY (SRM_CODMAT) REFERENCES RSC_MATERI (MAT_CODI) ENABLE
);

  
COMMENT ON TABLE RSC_SERMAT IS 'Tabla de la relacion entre materias y servicios.';
COMMENT ON COLUMN RSC_SERMAT.SRM_CODSER is 'Identificador del servicio';
COMMENT ON COLUMN RSC_SERMAT.SRM_CODMAT is 'Identificador de la materia';

--Tabla de normativas con servicios.
CREATE TABLE RSC_SERNOR (
    SRN_CODNOR NUMBER(19,0) NOT NULL ENABLE,
    SRN_CODSER NUMBER(19,0) NOT NULL ENABLE,
    CONSTRAINT RSC_SRN_PK PRIMARY KEY (SRN_CODSER, SRN_CODNOR) ENABLE,
    CONSTRAINT RSC_SRNSER_FK FOREIGN KEY (SRN_CODSER) REFERENCES RSC_SERVIC (SER_CODI) ENABLE,
    CONSTRAINT RSC_SRNNOR_FK FOREIGN KEY (SRN_CODNOR) REFERENCES RSC_NORMAT (NOR_CODI) ENABLE
);
  
COMMENT ON TABLE RSC_SERNOR IS 'Tabla de la relacion entre normativa y servicios.';
COMMENT ON COLUMN RSC_SERNOR.SRN_CODSER is 'Identificador del servicio';
COMMENT ON COLUMN RSC_SERNOR.SRN_CODNOR is 'Identificador de la normativa';

--Tabla de poblacion con servicios
CREATE TABLE RSC_POBSER
(
    PSR_CODSER NUMBER(19,0) NOT NULL ENABLE,
    PSR_CODPOB NUMBER(19,0) NOT NULL ENABLE,
    CONSTRAINT RSC_POBSER_PK PRIMARY KEY (PSR_CODSER, PSR_CODPOB) ENABLE,
    CONSTRAINT RSC_PSRSER_FK FOREIGN KEY (PSR_CODSER) REFERENCES RSC_SERVIC (SER_CODI) ENABLE,
    CONSTRAINT RSC_PSRPOB_FK FOREIGN KEY (PSR_CODPOB) REFERENCES RSC_PUBOBJ (POB_CODI) ENABLE
);

COMMENT ON TABLE RSC_POBSER IS 'Tabla de la relacion entre poblacion y servicios.';
COMMENT ON COLUMN RSC_POBSER.PSR_CODSER is 'Identificador del servicio';
COMMENT ON COLUMN RSC_POBSER.PSR_CODPOB is 'Identificador de la poblacion';

--Alterar la table de solrjob para que tenga en cuenta el servicio.
ALTER TABLE RSC_SOLJOB ADD JOB_FECSER DATE;
ALTER TABLE RSC_SOLJOB ADD JOB_TOTSER NUMBER(5,2);
ALTER TABLE RSC_SOLJOB ADD JOB_TOTDSR NUMBER(5,2);
COMMENT ON COLUMN RSC_SOLJOB.JOB_FECSER is 'Fecha fin ejecutando servicios.';
COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTSER is 'Porcentaje de total servicios.';
COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTDSR is 'Porcentaje de total servicios documento.';

--Anyadimos el tipo cod pidip al tipo SIA.
ALTER TABLE RSC_TIPO ADD TIP_IDBOIB NUMBER(19,0);
COMMENT ON COLUMN RSC_TIPO.TIP_IDBOIB is 'Codigo de BOIB.';

--Renombramiento del campo orden para adecuarse a las nomenclaturas.
ALTER TABLE RSC_DOCSER RENAME COLUMN ORDEN TO DSR_ORDEN;
ALTER TABLE RSC_DOCTRA RENAME COLUMN DOC_CODI TO DTR_CODI;
ALTER TABLE RSC_DOCTRA RENAME COLUMN CODITRA TO DTR_CODTRA;
ALTER TABLE RSC_DOCTRA RENAME COLUMN DOC_CODARC TO DTR_CODARC;
ALTER TABLE RSC_DOCTRA RENAME COLUMN ORDEN TO DTR_ORDEN;
ALTER TABLE RSC_DOCTRA RENAME COLUMN TIPUS TO DTR_TIPUS;
ALTER TABLE RSC_DOCTRA RENAME COLUMN DOC_CODCDC TO DTR_CODCDC;
ALTER TABLE RSC_DOCTRA RENAME COLUMN DOC_CODEXD TO DTR_CODEXD;

