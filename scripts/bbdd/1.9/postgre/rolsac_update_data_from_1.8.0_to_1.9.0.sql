/** LOPD LEGITIMO **/
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES(RSC_SEQ_ALL.NEXTVAL, 'EJECUCION_CONTRATO', 1);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'es', 'Ejecución de un contrato');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'ca', 'Execució d''un contracte');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES(RSC_SEQ_ALL.NEXTVAL, 'CUMPLIMIENTO_LEGAL', 0);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'es', 'Cumplimiento de una obligación legal');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'ca', 'Compliment d''una obligació legal');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES(RSC_SEQ_ALL.NEXTVAL, 'MISION_INTERES_PUB', 0);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'es', 'Misión en el interés público');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'ca', 'Missió en l''interès públic');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES(RSC_SEQ_ALL.NEXTVAL, 'EJER_PODERES_PUB', 0);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'es', 'Ejercicio de Poderes Públicos');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'ca', 'Exercici de Poders Públics');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES(RSC_SEQ_ALL.NEXTVAL, 'INTER_LEG_RESP', 0);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'es', 'Interés legítimo del Responsable');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'ca', 'Interès legítim del Responsable');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES(RSC_SEQ_ALL.NEXTVAL, 'INTER_LEG_TERCER', 0);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'es', 'Interés legítimo de un tercer');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'ca', 'Interès legítim d''un tercer');
INSERT INTO RSC_LOPDLEG (LEG_CODI, LEG_IDENTI, LEG_DEFAULT) VALUES(RSC_SEQ_ALL.NEXTVAL, 'CONSEN_INTERESADO', 0);
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'es', 'Consentimiento del interesado');
INSERT INTO RSC_TRALET (TLE_CODLEG, TLE_CODIDI, TLE_NOMBRE) VALUES(RSC_SEQ_ALL.currval, 'ca', 'Consentiment de l''interessat');

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
