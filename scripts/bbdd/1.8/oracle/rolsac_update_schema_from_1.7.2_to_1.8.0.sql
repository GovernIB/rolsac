-- Indicador para público objetivo .
ALTER TABLE RSC_PUBOBJ ADD POB_INTERNO NUMBER(1,0) DEFAULT (0);
COMMENT ON COLUMN RSC_PUBOBJ.POB_INTERNO is 'Indica si el publico objetivo es interno.';

--Indica si un procedimiento/servicio es comun
ALTER TABLE RSC_SERVIC ADD SER_COMUN NUMBER(1,0) DEFAULT 0;
ALTER TABLE RSC_PROCED ADD PRO_COMUN NUMBER(1,0) DEFAULT 0;