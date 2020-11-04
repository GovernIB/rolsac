-- Añadimos la traducción del campo URL
ALTER TABLE RSC_TRASER ADD TSR_ULRSER varchar(256);
COMMENT ON COLUMN RSC_TRASER.TSR_ULRSER is 'Indica la url del servicio en funcion del idioma';
