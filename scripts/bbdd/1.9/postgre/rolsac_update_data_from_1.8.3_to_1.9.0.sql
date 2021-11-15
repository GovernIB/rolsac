/** LOPD LEGITIMO **/
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES (nextval('RSC_SEQ_ALL') , 'EJECUCION_CONTRATO', TRUE);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'es', 'Ejecución de un contrato');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'ca', 'Execució d''un contracte');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES (nextval('RSC_SEQ_ALL'), 'CUMPLIMIENTO_LEGAL', FALSE);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'es', 'Cumplimiento de una obligación legal');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'ca', 'Compliment d''una obligació legal');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES (nextval('RSC_SEQ_ALL'), 'MISION_INTERES_PUB', FALSE);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'es', 'Misión en el interés público');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'ca', 'Missió en l''interès públic');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES (nextval('RSC_SEQ_ALL'), 'EJER_PODERES_PUB', FALSE);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'es', 'Ejercicio de Poderes Públicos');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'ca', 'Exercici de Poders Públics');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES (nextval('RSC_SEQ_ALL'), 'INTER_LEG_RESP', FALSE);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'es', 'Interés legítimo del Responsable');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'ca', 'Interès legítim del Responsable');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES (nextval('RSC_SEQ_ALL'), 'INTER_LEG_TERCER', FALSE);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'es', 'Interés legítimo de un tercer');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'ca', 'Interès legítim d''un tercer');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES (nextval('RSC_SEQ_ALL'), 'CONSEN_INTERESADO', FALSE);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'es', 'Consentimiento del interesado');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES (currval('RSC_SEQ_ALL'), 'ca', 'Consentiment de l''interessat');

update RSC_TRAPRO
   set TPR_LOPDFI = 'Tramitació del procediment',
       TPR_LOPDDS = 'Destinatario lopd' ,
       TPR_LOPDDR = 'Derechos lopd'
 where TPR_CODIDI = 'ca';

update RSC_TRAPRO
   set TPR_LOPDFI = 'Tramitación del procedimento',
       TPR_LOPDDS = 'Destinatario lopd' ,
       TPR_LOPDDR = 'Derechos lopd'
 where TPR_CODIDI = 'es';

update RSC_TRASER
   set TSR_LOPDFI = 'Tramitació del servei',
       TSR_LOPDDS = 'Destinatario lopd' ,
       TSR_LOPDDR = 'Derechos lopd'
 where TSR_CODIDI = 'ca';

update RSC_TRASER
   set TSR_LOPDFI = 'Tramitación del servicio',
       TSR_LOPDDS = 'Destinatario lopd' ,
       TSR_LOPDDR = 'Derechos lopd'
 where TSR_CODIDI = 'es';

  /** PONE LA VERSION A NULO DONDE EL IDTRAMITEVERSION Y PLATAFORMA ESTÉN A NULO **/
 update RSC_TRAMIT
  set TRA_VERSIO = null
 where TRA_CODPLT IS NULL
  AND  TRA_IDTRAMTEL IS NULL
  AND  TRA_VERSIO IS NOT NULL;

  /** INTRODUCE EN LA TRADUCCION URL DEL TRAMITE, LA URL ANTERIOR **/
  UPDATE RSC_TRATRA 
   SET TTR_ULRTRA = (
           SELECT TRA_URLEXTE FROM RSC_TRAMIT WHERE RSC_TRATRA.TTR_CODTTR = TRA_CODI
                         )
 WHERE TTR_ULRTRA IS NULL;