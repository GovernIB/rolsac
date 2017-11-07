--Introducir el dato de la UA en la nueva tabla que relaciona UA con Normativas.
INSERT INTO RSC_UNANOR (UNN_CODNOR, UNN_CODUNA, UNN_CODI) (
  SELECT NOR_CODI, NOR_CODUNA, RSC_SEQ_ALL.NEXTVAL
    FROM RSC_NORMAT
   WHERE NOR_CODUNA IS NOT NULL
);

/*** 
  Traducciones mezcladas de traduccion normativas local y externas.
  Hay que ejecutarlo antes del script de arriba para ver si está correcto. 
  
  SELECT * 
   FROM RSC_TRANOL 
   WHERE TNL_CODNOR IN (SELECT TNE_CODNOR FROM RSC_TRANOE);
   
   select *
   FROM RSC_TRANOE 
   WHERE TNE_CODNOR IN (SELECT TNL_CODNOR FROM RSC_TRANOL);
   
   ***/
---Actualizar el fichero de traducciones de normativas para unificarlo.
INSERT INTO RSC_TRANOR (TNO_CODNOR, TNO_SECCIO, TNO_APARTA, TNO_PAGINI, TNO_PAGFIN, TNO_TITULO, TNO_ENLACE, TNO_RESPON, TNO_CODARC, TNO_OBSERV, TNO_CODIDI)
(
   SELECT TNE_CODNOR AS TNO_CODNOR,
    TNE_SECCIO      AS TNO_SECCIO,
    TNE_APARTA      AS TNO_APARTA,
    TNE_PAGINI      AS TNO_PAGINI,
    TNE_PAGFIN      AS TNO_PAGFIN,
    TNE_TITULO      AS TNO_TITULO,
    TNE_ENLACE      AS TNO_ENLACE,
    TNE_RESPON      AS TNO_RESPON,
    TNE_CODARC      AS TNO_CODARC,
    TNE_OBSERV      AS TNO_OBSERV,
    TNE_CODIDI      AS TNO_CODIDI
  FROM RSC_TRANOE
  UNION
  SELECT TNL_CODNOR,
    TNL_SECCIO,
    TNL_APARTA,
    TNL_PAGINI,
    TNL_PAGFIN,
    TNL_TITULO,
    TNL_ENLACE,
    NULL,
    TNL_CODARC,
    TNL_OBSERV,
    TNL_CODIDI
  FROM RSC_TRANOL );
  
-- Backup de normativa type y Cambiar la normativaLocal y normativaExterna al valor normativa  mientras que normativaExternaRemota/normativaLocalRemota pasa a Remota.  
update rsc_normat set nor_typen = 'normativa';
update rsc_normat set nor_validn = 1;
update rsc_normat set nor_codbol_ant = nor_codbol;
update rsc_normat set nor_codbol = null where nor_codbol not in (select bol_codi from rsc_boleti where bol_nombre in ('BOIB', 'BOE', 'DOCE'));
delete rsc_bolleti where bol_nombre not in ('BOIB','BOE','DOCE');

/** Actualiza el id boib en los tipos de normativa. **/ 
UPDATE RSC_TIPO SET TIP_IDBOIB = 5225 WHERE TIP_CODI = 2;
UPDATE RSC_TIPO SET TIP_IDBOIB = 184  WHERE TIP_CODI = 20;
UPDATE RSC_TIPO SET TIP_IDBOIB = 31   WHERE TIP_CODI = 8;
UPDATE RSC_TIPO SET TIP_IDBOIB = 29   WHERE TIP_CODI = 7;
UPDATE RSC_TIPO SET TIP_IDBOIB = 2    WHERE TIP_CODI = 1;
UPDATE RSC_TIPO SET TIP_IDBOIB = 5227 WHERE TIP_CODI = 4;