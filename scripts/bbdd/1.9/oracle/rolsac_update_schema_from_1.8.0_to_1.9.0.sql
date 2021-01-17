--Anyade la plataforma de tramitacion
CREATE TABLE RSC_LOPDLEG
  (
    LEG_CODI    NUMBER(19,0) NOT NULL ENABLE,
    LEG_IDENTI  VARCHAR2(128 BYTE),
    LEG_DEFAULT number(1,0) default 0,
    CONSTRAINT "RSC_LET_PK" PRIMARY KEY ("LEG_CODI")
  );

COMMENT ON TABLE  RSC_LOPDLEG IS 'Tabla de lopd legitimacion';
COMMENT ON COLUMN RSC_LOPDLEG.LEG_CODI IS 'PK de la lopd legitimacion';
COMMENT ON COLUMN RSC_LOPDLEG.LEG_IDENTI IS 'Identificador del lopd legitimacion';
COMMENT ON COLUMN RSC_LOPDLEG.LEG_DEFAULT IS 'Indica el legitimacion por defecto';

CREATE TABLE  RSC_TRALET
  (
    TLE_CODLEG NUMBER(19,0) NOT NULL ENABLE,
    TLE_CODIDI VARCHAR2(2 CHAR) NOT NULL ENABLE,
    TLE_NOMBRE VARCHAR2(1024 CHAR),
    CONSTRAINT "RSC_TRALET_PK" PRIMARY KEY ("TLE_CODLEG", "TLE_CODIDI"),
    CONSTRAINT "RSC_TRALET_FK" FOREIGN KEY ("TLE_CODLEG") REFERENCES "RSC_LOPDLEG" ("LEG_CODI") ENABLE
  );

COMMENT ON TABLE  RSC_TRALET IS 'Tabla de traduccion plataformas de tramitacion';
COMMENT ON COLUMN RSC_TRALET.TLE_CODLEG IS 'PK de la legitimacion lopd';
COMMENT ON COLUMN RSC_TRALET.TLE_CODIDI IS 'Idioma';
COMMENT ON COLUMN RSC_TRALET.TLE_NOMBRE IS 'Nombre de la plataforma';

/**
PERFIL ROLSAC
GRANT SELECT,INSERT,UPDATE,DELETE ON RSC_LOPDLEG TO www_rolsac;
GRANT SELECT,INSERT,UPDATE,DELETE ON RSC_TRALET TO www_rolsac;

PERFIL WWW_ROLSAC
CREATE SYNONYM RSC_LOPDLEG FOR rolsac.RSC_LOPDLEG;
CREATE SYNONYM RSC_TRALET FOR rolsac.RSC_TRALET;

**/

/** Cambios en procs **/
ALTER TABLE RSC_PROCED ADD PRO_CODLEG NUMBER(19,0);
ALTER TABLE RSC_PROCED ADD CONSTRAINT "RSC_PRO_CODLEG_FK" FOREIGN KEY ("PRO_CODLEG") REFERENCES "RSC_LOPDLEG" ("LEG_CODI") ENABLE;
COMMENT ON COLUMN RSC_PROCED.PRO_CODLEG IS 'Indica la legitimacion del procedimiento';

ALTER TABLE RSC_TRAPRO ADD TPR_LOPDFI VARCHAR2(4000 CHAR);
ALTER TABLE RSC_TRAPRO ADD TPR_LOPDDS VARCHAR2(4000 CHAR);
ALTER TABLE RSC_TRAPRO ADD TPR_LOPDDR VARCHAR2(4000 CHAR);
COMMENT ON COLUMN RSC_TRAPRO.TPR_LOPDFI IS 'Indica el lopd finalidad';
COMMENT ON COLUMN RSC_TRAPRO.TPR_LOPDDS IS 'Indica el lopd destinatario';
COMMENT ON COLUMN RSC_TRAPRO.TPR_LOPDDR IS 'Indica el lopd derechos';

/** Cambios en servs **/
ALTER TABLE RSC_SERVIC ADD SER_CODLEG NUMBER(19,0);
ALTER TABLE RSC_SERVIC ADD CONSTRAINT "RSC_SER_CODLEG_FK" FOREIGN KEY ("SER_CODLEG") REFERENCES "RSC_LOPDLEG" ("LEG_CODI") ENABLE;
COMMENT ON COLUMN RSC_SERVIC.SER_CODLEG IS 'Indica la legitimacion del servicio';

ALTER TABLE RSC_TRASER ADD TSR_LOPDFI VARCHAR2(4000 CHAR);
ALTER TABLE RSC_TRASER ADD TSR_LOPDDS VARCHAR2(4000 CHAR);
ALTER TABLE RSC_TRASER ADD TSR_LOPDDR VARCHAR2(4000 CHAR);
COMMENT ON COLUMN RSC_TRASER.TSR_LOPDFI IS 'Indica el lopd finalidad';
COMMENT ON COLUMN RSC_TRASER.TSR_LOPDDS IS 'Indica el lopd destinatario';
COMMENT ON COLUMN RSC_TRASER.TSR_LOPDDR IS 'Indica el lopd derechos';

/** Lopd info adicional **/
ALTER TABLE RSC_TRAPRO ADD TPR_LOPDIA NUMBER(19,0);
ALTER TABLE RSC_TRAPRO ADD CONSTRAINT "RSC_TPRARC_FK" FOREIGN KEY ("TPR_LOPDIA") REFERENCES "RSC_ARCHIV" ("ARC_CODI") ENABLE;
COMMENT ON COLUMN RSC_TRAPRO.TPR_LOPDIA IS 'Indica el lopd archivo info adicional';
ALTER TABLE RSC_TRASER ADD TSR_LOPDIA NUMBER(19,0);
ALTER TABLE RSC_TRASER ADD CONSTRAINT "RSC_TSRARC_FK" FOREIGN KEY ("TSR_LOPDIA") REFERENCES "RSC_ARCHIV" ("ARC_CODI") ENABLE;
COMMENT ON COLUMN RSC_TRASER.TSR_LOPDIA IS 'Indica el lopd archivo info adicional';