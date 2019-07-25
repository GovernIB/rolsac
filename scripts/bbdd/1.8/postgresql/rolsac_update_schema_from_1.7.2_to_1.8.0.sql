-- Indicador para público objetivo .
ALTER TABLE RSC_PUBOBJ ADD POB_INTERNO bool DEFAULT FALSE;
COMMENT ON COLUMN RSC_PUBOBJ.POB_INTERNO is 'Indica si el publico objetivo es interno.';

--Indica si un procedimiento/servicio es comun
ALTER TABLE RSC_SERVIC ADD SER_COMUN BOOL DEFAULT FALSE;
ALTER TABLE RSC_PROCED ADD PRO_COMUN BOOL DEFAULT FALSE;