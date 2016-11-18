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
    SLP_TXTERR character varying(4000)  
         
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
  
  create table RSC_SIA (
   SIA_ID integer not null,
   SIA_IDPROC character varying(12),
   SIA_TITULO character varying(100),
   SIA_DESCRI character varying(255),
   SIA_IDCENT character varying(255),
   SIA_UAGEST character varying(100),
   SIA_IDDEST character varying(100),
   SIA_NIVADM character varying(2),
   SIA_FIVIA character varying(2),
   SIA_IDNORM character varying(100),
   SIA_TINORM character varying(100),
   SIA_MATERI character varying(255),
   SIA_IDSIA character varying(12),
   primary key (SIA_ID)
);

COMMENT ON TABLE  RSC_SIA IS 'Envio SIA';
  COMMENT ON COLUMN RSC_SIA.SIA_ID       IS 'Identificador';
  COMMENT ON COLUMN RSC_SIA.SIA_IDPROC   IS 'Identificador Procedimiento';
  COMMENT ON COLUMN RSC_SIA.SIA_TITULO   IS 'Título procedimiento';
  COMMENT ON COLUMN RSC_SIA.SIA_DESCRI   IS 'Descripción procedimiento';
  COMMENT ON COLUMN RSC_SIA.SIA_IDCENT   IS 'Id centro directivo';
  COMMENT ON COLUMN RSC_SIA.SIA_UAGEST   IS 'Unidad gestora del trámite';
  COMMENT ON COLUMN RSC_SIA.SIA_IDDEST   IS 'Id del destinatario. 1:ciudadano; 2:empresa; 3:administraci�n.';
  COMMENT ON COLUMN RSC_SIA.SIA_NIVADM   IS '1:Información; 2:Descarga formulario; 3:Descarga y envío; 4:Tramitación electrónica; 5:Proactivo; 6:Sin tramitación electrónica';
  COMMENT ON COLUMN RSC_SIA.SIA_FIVIA    IS 'Fin Via';
  COMMENT ON COLUMN RSC_SIA.SIA_IDNORM   IS 'Identificador de la normativa';
  COMMENT ON COLUMN RSC_SIA.SIA_TINORM   IS 'Título de la normativa';
  COMMENT ON COLUMN RSC_SIA.SIA_DESTIN   IS 'Destinatario';
  COMMENT ON COLUMN RSC_SIA.SIA_TAXA     IS 'Taxa. 1:si; 0:no';
  COMMENT ON COLUMN RSC_SIA.SIA_MATERI   IS 'Identificadores de las materias';
  COMMENT ON COLUMN RSC_SIA.SIA_IDSIA    IS 'Id SIA';
 
 
create table RSC_SIAJOB  (
   SIJ_ID integer not null,
   SIJ_FECINI date,
   SIJ_FECFIN date,
   SIJ_DESBRE clob,
   SIJ_DESCRI clob,
   SIJ_ESTADO smallint,
   primary key (SIJ_ID)
);

COMMENT ON TABLE  RSC_SIAJOB IS 'SIA Job';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_ID       IS 'Identificador';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_FECINI   IS 'Fecha de creación';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_FECFIN   IS 'Fecha de finalización';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_DESBRE   IS 'Descripción breve del resultado';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_DESCRI   IS 'Descripción';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_ESTADO   IS '0: Creado y en proceso; 1:Ejecución; 2:Enviado y sin errores; 3:Enviado y con errores; -1:Problema grave';
  
create table RSC_SIAPDT  (
   SIP_ID integer not null,
   SIP_TIPO character varying(100),
   SIP_IDELEMENTO NUMBER(8,2),
   SIP_ESTADO smallint,
   SIP_FECALT date,
   SIP_FECIDX date,
   SIP_MENSA character varying(255),
   SIP_TIPOAC smallint,
   primary key (SIP_ID)
);
  COMMENT ON TABLE  RSC_SIAPDT IS 'SIA pendiente';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_ID       IS 'Identificador';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_TIPO   IS 'UA/PROC/NORM';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_IDELEMENTO   IS 'Código elemento';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_ESTADO   IS '0:Espera; 1:enviado correctamente; 2:enviado incorrectamente';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_FECALT   	IS 'Fecha creación';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_FECIDX   	IS 'Fecha envio';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_MENSA   	 	IS 'Mensaje';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_TIPOAC   	IS 'Tipo acción:alta,modificación,reactivación o baja';
  