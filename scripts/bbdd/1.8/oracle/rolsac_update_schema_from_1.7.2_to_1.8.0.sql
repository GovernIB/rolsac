-- Indicador para público objetivo .
ALTER TABLE RSC_PUBOBJ ADD POB_INTERNO NUMBER(1,0) DEFAULT (0);
COMMENT ON COLUMN RSC_PUBOBJ.POB_INTERNO is 'Indica si el publico objetivo es interno.';

--Indica si un procedimiento/servicio es comun
ALTER TABLE RSC_SERVIC ADD SER_COMUN NUMBER(1,0) DEFAULT 0;
ALTER TABLE RSC_PROCED ADD PRO_COMUN NUMBER(1,0) DEFAULT 0;


--Anyade la plataforma de tramitacion
CREATE TABLE RSC_PLATAF
  (
    PLT_CODI    NUMBER(19,0) NOT NULL ENABLE,
    PLT_NOMBRE  VARCHAR2(128 BYTE),
    PLT_ORDEN	number(10,0),
    CONSTRAINT "RSC_PLT_PK" PRIMARY KEY ("PLT_CODI")
  );

COMMENT ON TABLE RSC_PLATAF IS 'Tabla de plataformas de tramitacion';
COMMENT ON COLUMN RSC_PLATAF.PLT_CODI IS 'PK de la plataforma';
COMMENT ON COLUMN RSC_PLATAF.PLT_NOMBRE IS 'Nombre de la plataforma';

CREATE TABLE  RSC_TRAPLT
  (
    TPT_CODPLT NUMBER(19,0) NOT NULL ENABLE,
    TPT_CODIDI VARCHAR2(2 BYTE) NOT NULL ENABLE,
    TPT_DESCRI VARCHAR2(1024 BYTE),
    TPT_URL VARCHAR2(1024 BYTE),
    CONSTRAINT "RSC_TPT_PK" PRIMARY KEY ("TPT_CODPLT", "TPT_CODIDI"),
    CONSTRAINT "RSC_TRAPLT_FK" FOREIGN KEY ("TPT_CODPLT") REFERENCES "RSC_PLATAF" ("PLT_CODI") ENABLE
  );

COMMENT ON TABLE RSC_TRAPLT IS 'Tabla de traduccion plataformas de tramitacion';
COMMENT ON COLUMN RSC_TRAPLT.TPT_CODPLT IS 'PK de la plataforma';
COMMENT ON COLUMN RSC_TRAPLT.TPT_CODIDI IS 'Idioma';
COMMENT ON COLUMN RSC_TRAPLT.TPT_DESCRI IS 'Descripcion de la plataforma';
COMMENT ON COLUMN RSC_TRAPLT.TPT_URL IS 'Url de la plataforma';

--- Anayadido la info para tramite
ALTER TABLE RSC_TRAMIT ADD TRA_CODPLT	NUMBER(19,0);
ALTER TABLE RSC_TRAMIT ADD TRA_PARAMS VARCHAR2(1024 BYTE);
COMMENT ON COLUMN RSC_TRAMIT.TRA_CODPLT IS 'FK a plataforma de tramitacion';
COMMENT ON COLUMN RSC_TRAMIT.TRA_PARAMS IS 'Parametros en caso de ser telematico';
ALTER TABLE RSC_TRAMIT ADD CONSTRAINT "RSC_TRAMPLT_FK" FOREIGN KEY ("TRA_CODPLT") REFERENCES RSC_PLATAF ("PLT_CODI");

ALTER TABLE RSC_SERVIC ADD SER_CODPLT	NUMBER(19,0);
ALTER TABLE RSC_SERVIC ADD SER_PARAMS VARCHAR2(1024 BYTE);
COMMENT ON COLUMN RSC_SERVIC.SER_CODPLT IS 'FK a plataforma de tramitacion';
COMMENT ON COLUMN RSC_SERVIC.SER_PARAMS IS 'Parametros en caso de ser telematico';
ALTER TABLE RSC_SERVIC ADD CONSTRAINT "RSC_SERPLT_FK" FOREIGN KEY ("SER_CODPLT") REFERENCES RSC_PLATAF ("PLT_CODI");
