-- Indicador para público objetivo . 
ALTER TABLE RSC_PUBOBJ ADD POB_INTERNO bool DEFAULT FALSE;
COMMENT ON COLUMN RSC_PUBOBJ.POB_INTERNO is 'Indica si el publico objetivo es interno.';