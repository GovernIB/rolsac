--Introducir el dato de la UA en la nueva tabla que relaciona UA con Normativas.
INSERT INTO RSC_UNANOR (UNN_CODNOR, UNN_CODUNA, UNN_CODI) (
  SELECT NOR_CODI, NOR_CODUNA, RSC_SEQ_ALL.NEXTVAL
    FROM RSC_NORMAT
   WHERE NOR_CODUNA IS NOT NULL
);

/*** 
  Traducciones mezcladas de traduccion normativas local y externas.
  Hay que ejecutarlo antes del script de arriba para ver si est� correcto. 
  
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
---Actualizamos el tipo boletin para que cree el DOUE y fusione el DOCE y el Diario Europeu en DOUE.
INSERT INTO RSC_BOLETI (BOL_CODI , BOL_NOMBRE, BOL_ENLACE) VALUES ( RSC_SEQ_ALL.nextval, 'DOUE', null);
update rsc_normat set nor_codbol = (select bol_codi from rsc_boleti where lower(bol_nombre) like 'doue') where nor_codbol in (select bol_codi from rsc_boleti where lower(bol_nombre) like 'doce' );
update rsc_normat set nor_codbol = (select bol_codi from rsc_boleti where lower(bol_nombre) like 'doue') where nor_codbol in (select bol_codi from rsc_boleti where lower(bol_nombre) like '%diario%uropea%' );
delete from rsc_boleti where lower(bol_nombre) like 'doce';

/** Actualiza el id boib en los tipos de normativa. **/ 
UPDATE RSC_TIPO SET TIP_IDBOIB = 5225 WHERE TIP_CODI = 2;
UPDATE RSC_TIPO SET TIP_IDBOIB = 184  WHERE TIP_CODI = 20;
UPDATE RSC_TIPO SET TIP_IDBOIB = 31   WHERE TIP_CODI = 8;
UPDATE RSC_TIPO SET TIP_IDBOIB = 29   WHERE TIP_CODI = 7;
UPDATE RSC_TIPO SET TIP_IDBOIB = 2    WHERE TIP_CODI = 1;
UPDATE RSC_TIPO SET TIP_IDBOIB = 5227 WHERE TIP_CODI = 4;

/** Introduce los nuevos tipos, directiva europea, llei organica y reglament europeu. **/
INSERT INTO RSC_TIPO (TIP_CODI,TIP_CODSIA,TIP_IDBOIB) VALUES (30, null, null);
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (30, 'ca', 'Directiva Europea'); 
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (30, 'es', 'Directiva Europea');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (30, 'en', 'European Directive');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (30, 'fr', 'Directive Europ�enne');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (30, 'de', 'Europ�ische Richtlinie');
  
INSERT INTO RSC_TIPO (TIP_CODI,TIP_CODSIA,TIP_IDBOIB) VALUES (31, null, null);
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (31, 'ca', 'Llei Org�nica'); 
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (31, 'es', 'Ley Org�nica');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (31, 'en', 'Organic Law');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (31, 'fr', 'Loi Organique');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (31, 'de', 'Organisches Recht');
  
INSERT INTO RSC_TIPO (TIP_CODI,TIP_CODSIA,TIP_IDBOIB) VALUES (32, null, null);
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (32, 'ca', 'Reglament Europeu'); 
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (32, 'es', 'Reglamento Europeo');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (32, 'en', 'European Regulation');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (32, 'fr', 'R�glementation europ�enne');
INSERT INTO RSC_TRATIP (TTI_CODTIP, TTI_CODIDI, TTI_NOMBRE) VALUES (32, 'de', 'Europ�ische Regelung');
  
