-- TAULA RSC_SOLRPD
CREATE TABLE RSC_SOLRPD
  (
    SLP_ID   integer NOT NULL,
    SLP_TIPO character varying(128),
    SLP_IDELEM integer,
    SLP_ACCION integer,
    SLP_FECCRE date,
    SLP_FECIDX date,
    SLP_RESULT smallint,
    SLP_TXTERR character varying(128)  
         
  );
  
  ALTER TABLE ONLY RSC_SOLRPD
    ADD CONSTRAINT RSC_SOLRPD_PK PRIMARY KEY (SLP_ID);

  COMMENT ON TABLE  RSC_SOLRPD IS 'Indexacion';
  COMMENT ON COLUMN RSC_SOLRPD.SLP_ID       IS 'Identificador';
  COMMENT ON COLUMN RSC_SOLRPD.SLP_TIPO     IS 'Tipo';
  COMMENT ON COLUMN RSC_SOLRPD.SLP_IDELEM   IS 'Identificador Elemento';
  COMMENT ON COLUMN RSC_SOLRPD.SLP_ACCION   IS 'Accion. 1 - Indexar, 2 - Borrar';
  COMMENT ON COLUMN RSC_SOLRPD.SLP_FECCRE   IS 'Fecha Creacion';
  COMMENT ON COLUMN RSC_SOLRPD.SLP_FECIDX   IS 'Fecha Ejecucion de indexacion';
  COMMENT ON COLUMN RSC_SOLRPD.SLP_RESULT   IS 'Resultado.  0 - Pendiente, 1 Ejecutado correctamente, -1 Ejecutado con errores';
  COMMENT ON COLUMN RSC_SOLRPD.SLP_TXTERR   IS 'Mensaje de error que indique porque no se ha indexado';
  
  
---tabla solr job 
CREATE TABLE RSC_SOLJOB
  (
    JOB_ID NUMBER(19,0) NOT NULL ENABLE,
    JOB_FECINI DATE DEFAULT SYSDATE,
    JOB_FECFIN DATE,
    JOB_FECFCH DATE,
    JOB_FECPRO DATE,
    JOB_FECNOR DATE,
    JOB_FECTRA DATE,
    JOB_FECUNA DATE,
    JOB_TOTFCH NUMBER(5,2) DEFAULT 0,
    JOB_TOTDFC NUMBER(5,2) DEFAULT 0,
    JOB_TOTPRO NUMBER(5,2) DEFAULT 0,
    JOB_TOTDPR NUMBER(5,2) DEFAULT 0,
    JOB_TOTNOR NUMBER(5,2) DEFAULT 0,
    JOB_TOTDNO NUMBER(5,2) DEFAULT 0,
    JOB_TOTTRA NUMBER(5,2) DEFAULT 0,
    JOB_TOTUNA NUMBER(5,2) DEFAULT 0,
    CONSTRAINT RSC_JOB_PK PRIMARY KEY (JOB_ID) 
  );

 -- Comentarios de la tabla solr job
  COMMENT ON COLUMN RSC_SOLJOB.JOB_ID IS 'Identificador de la clase.';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_FECINI IS 'Fecha de inicio';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_FECFIN IS 'Fecha fin';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_FECFCH IS 'Fecha fin ejecutando fichas.';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_FECPRO IS 'Fecha fin ejecutando procedimientos.';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_FECNOR IS 'Fecha fin ejecutando normativas.';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_FECTRA IS 'Fecha fin ejecutando trámites.';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_FECUNA IS 'Fecha fin ejecutando unidad administrativa';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTFCH IS 'Porcentaje de total fichas';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTDFC IS 'Porcentaje de total fichas documento';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTPRO IS 'Porcentaje de total procedimiento';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTDPR IS 'Porcentaje de total procedimiento documento';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTNOR IS 'Porcentaje de total normativas';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTDNO IS 'Porcentaje de total normativas documento';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTTRA IS 'Porcentaje de total tramite';
  COMMENT ON COLUMN RSC_SOLJOB.JOB_TOTUNA IS 'Porcentaje de total Unid. Adminitr.';
  