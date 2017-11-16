---Datos de creación mínimos indispensable para el correcto funcionamiento de ROLSAC (usuarios, perfiles, 1 UA y tipos)

INSERT INTO rsc_idioma(idi_codi, idi_orden, idi_codest, idi_nombre) VALUES('ca', 1, 'ca', 'Català');
INSERT INTO rsc_idioma(idi_codi, idi_orden, idi_codest, idi_nombre) VALUES('es', 2, 'es', 'Castellano');
INSERT INTO rsc_usuari(usu_codi, usu_userna, usu_passwo, usu_nombre, usu_observ, usu_perfil) VALUES(1, 'admin', 'admin', 'Admin', '', 'sacsystem');
INSERT INTO rsc_uniadm(una_codi, una_orden, una_valida, una_type) values (1,0,1,'unidadAdministrativa');
INSERT INTO rsc_trauna(tun_coduna, tun_nombre, tun_codidi, tun_url, tun_presen, tun_abrevi, tun_cvresp) values(1, 'UA Proves', 'ca', 'http://www.caib.es','UA de prueba', 'UAPrueba', 'Responsable UA Prueba');
INSERT INTO rsc_trauna(tun_coduna, tun_nombre, tun_codidi, tun_url, tun_presen, tun_abrevi, tun_cvresp) values(1, 'UA Proves', 'es', 'http://www.caib.es','UA de proves', 'UAProves', 'Responsable UA Proves');
INSERT INTO rsc_unausu (unu_coduna, unu_codusu) values (1,1);
INSERT INTO rsc_perges (peg_codi, peg_orden, peg_codest, peg_duplic) values (1, 0 , null, null); 
INSERT INTO rsc_usupeg (usp_codpeg, usp_codusu) values (1, 1);
INSERT INTO rsc_boleti (bol_codi, bol_nombre, bol_enlace) values (1, 'BOIB', 'http://boib.caib.es/pdf/2009015/mp2.pdf');
INSERT INTO rsc_boleti (bol_codi, bol_nombre, bol_enlace) values (2, 'BOE', 'http://www.caib.es');
INSERT INTO rsc_boleti (bol_codi, bol_nombre, bol_enlace) values (3, 'DOUE', 'http://www.caib.es');

----------  BIBLIOTECA BASICA DE DATOS PARA COMENZAR. --------------------
-----Tipo para la normativa
--INSERT INTO rsc_tipo (tip_codi, tip_codsia, tip_idboib) values (1, null, null);
--INSERT INTO rsc_tratip (tti_codtip,  tti_nombre,  tti_codidi) values (1, 'Tipo', 'es');
--INSERT INTO rsc_tratip (tti_codtip,  tti_nombre,  tti_codidi) values (1, 'Tipo', 'ca');

--Public objetivo
--INSERT INTO rsc_pubobj (pob_codi, pob_codest, pob_orden) values (1, null, 0);
--INSERT INTO rsc_trapob (trp_codpob, trp_titulo, trp_descri, trp_palcla, trp_codidi) values (1, 'Public Objectiu', 'Descripció del públic objectiu', 'Palcal del públic objectiu', 'ca');
--INSERT INTO rsc_trapob (trp_codpob, trp_titulo, trp_descri, trp_palcla, trp_codidi) values (1, 'Publico Objetivo', 'Descripción del publico objetivo', 'Palcal del publico objetivo', 'es');

--Secciones (y su relación con perfil)
--INSERT INTO rsc_seccio (sec_codi, sec_codest, sec_perfil, sec_orden, sec_codsec) values (1, null, 'sacsystem', 0, null);
--INSERT INTO rsc_trasec (tse_codsec, tse_titulo, tse_descri, tse_codidi) values (1, 'Seccio', 'Seccio', 'ca');
--INSERT INTO rsc_trasec (tse_codsec, tse_titulo, tse_descri, tse_codidi) values (1, 'Seccion', 'Seccion', 'es');
--INSERT INTO rsc_pegsec (pgs_codpeg, pgs_codsec) values (1,1);

---Forma de inicio
--INSERT INTO rsc_inici  (ini_codi, ini_codest) values (1,null);
--INSERT INTO rsc_traini (tin_codini, tin_nombre, tin_descri, tin_codidi) values(1,'Inici', 'Inici', 'ca');
--INSERT INTO rsc_traini (tin_codini, tin_nombre, tin_descri, tin_codidi) values(1,'Inicio', 'Inicio', 'es');

---Silcencio administrativo
--INSERT INTO rsc_siladm (sil_codi) values (1);
--INSERT INTO rsc_trasil (tsi_codsil, tsi_nombre, tsi_descri, tsi_codidi) values (1,'Silenci', 'Silenci', 'ca');
--INSERT INTO rsc_trasil (tsi_codsil, tsi_nombre, tsi_descri, tsi_codidi) values (1,'Silencio', 'Silencio', 'es');

---Familia
--INSERT INTO rsc_famili(fam_codi) values (1);
--INSERT INTO rsc_trafam ( tfa_codfam, tfa_nombre, tfa_descri, tfa_codidi) values (1, 'Familia', 'Familia', 'ca');
--INSERT INTO rsc_trafam ( tfa_codfam, tfa_nombre, tfa_descri, tfa_codidi) values (1, 'Familia', 'Familia', 'es');