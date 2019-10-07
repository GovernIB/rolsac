set define off;
INSERT INTO RSC_PLATAF (PLT_CODI , PLT_NOMBRE, PLT_ORDEN) VALUES (1, 'SISTRA1' , 1);
INSERT INTO RSC_PLATAF (PLT_CODI , PLT_NOMBRE, PLT_ORDEN) VALUES (2, 'SISTRA2' , 2);
INSERT INTO RSC_TRAPLT (TPT_CODPLT, TPT_CODIDI, TPT_DESCRI, TPT_URL) VALUES (1, 'es', 'Plataforma de tramitación SISTRA1' , 'http://caibter.indra.es/sistrafront/inicio?language=es&modelo=${idTramitePlataforma}&version=${versionTramitePlatorma}${parametros}');
INSERT INTO RSC_TRAPLT (TPT_CODPLT, TPT_CODIDI, TPT_DESCRI, TPT_URL) VALUES (1, 'ca', 'Plataforma de tramitació SISTRA1'  , 'http://caibter.indra.es/sistrafront/inicio?language=ca&modelo=${idTramitePlataforma}&version=${versionTramitePlatorma}${parametros}');
INSERT INTO RSC_TRAPLT (TPT_CODPLT, TPT_CODIDI, TPT_DESCRI, TPT_URL) VALUES (2, 'es', 'Plataforma de tramitación SISTRA2' , 'http://caibter.indra.es/sistramitfront/asistente/iniciarTramite.html?tramite=${idTramitePlataforma}&version=${versionTramitePlatorma}&idioma=es&servicioCatalogo=${servicio}&idTramiteCatalogo=${idTramiteRolsac}&parametros=${parametros}');
INSERT INTO RSC_TRAPLT (TPT_CODPLT, TPT_CODIDI, TPT_DESCRI, TPT_URL) VALUES (2, 'ca', 'Plataforma de tramitació SISTRA2'  , 'http://caibter.indra.es/sistramitfront/asistente/iniciarTramite.html?tramite=${idTramitePlataforma}&version=${versionTramitePlatorma}&idioma=ca&servicioCatalogo=${servicio}&idTramiteCatalogo=${idTramiteRolsac}&parametros=${parametros}');

UPDATE RSC_TRAMIT SET TRA_CODPLT = 1 WHERE TRA_IDTRAMTEL IS NOT NULL AND LENGTH(TRIM(TRA_IDTRAMTEL)) > 0;
UPDATE RSC_SERVIC SET SER_CODPLT = 1 WHERE SER_TRAID IS NOT NULL AND LENGTH(TRIM(SER_TRAID)) > 0;
COMMIT;