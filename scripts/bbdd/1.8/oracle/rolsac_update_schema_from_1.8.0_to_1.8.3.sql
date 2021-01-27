-- Añadimos la traducción del campo URL
ALTER TABLE RSC_TRASER ADD TSR_ULRSER VARCHAR2(256);
COMMENT ON COLUMN RSC_TRASER.TSR_ULRSER is 'Indica la url del servicio en funcion del idioma';
