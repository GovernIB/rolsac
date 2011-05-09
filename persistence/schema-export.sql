alter table RSC_PROMAT drop constraint RSC_PRMMAT_FK;
alter table RSC_PROMAT drop constraint RSC_PRMPRO_FK;
alter table RSC_SCRGRP drop constraint RSC_STPSGR_FK;
alter table RSC_TRAPOB drop constraint RSC_TRPPOB_FK;
alter table RSC_TRANOE drop constraint RSC_TNEARC_FK;
alter table RSC_TRANOE drop constraint RSC_TNENOR_FK;
alter table RSC_TRAPRO drop constraint RSC_TPRPRO_FK;
alter table RSC_ADMREM drop constraint RSC_ADMESP_FK;
alter table RSC_ADMREM drop constraint RSC_ADMLOG_FK;
alter table RSC_ADMREM drop constraint RSC_ADMLOP_FK;
alter table RSC_UNAMAT drop constraint RSC_UNMMAT_FK;
alter table RSC_UNAMAT drop constraint RSC_UNMUNA_FK;
alter table RSC_TRAUNA drop constraint RSC_TUNUNA_FK;
alter table RSC_HEVIPR drop constraint RSC_HVPHEV_FK;
alter table RSC_HEVIPR drop constraint RSC_HVPPRO_FK;
alter table RSC_TRATIA drop constraint RSC_TTATIA_FK;
alter table RSC_TRATIP drop constraint RSC_TTITIP_FK;
alter table RSC_HEVIAG drop constraint RSC_HVAAGH_FK;
alter table RSC_HEVIAG drop constraint RSC_HVAHEV_FK;
alter table RSC_TRAPEC drop constraint RSC_TPEPEC_FK;
alter table RSC_TRAAGM drop constraint RSC_TAMAGM_FK;
alter table RSC_FICMAT drop constraint RSC_FIMFIC_FK;
alter table RSC_FICMAT drop constraint RSC_FIMMAT_FK;
alter table RSC_UNAUSU drop constraint RSC_UNUUNA_FK;
alter table RSC_UNAUSU drop constraint RSC_UNUUSU_FK;
alter table RSC_TRAEDI drop constraint RSC_TEDEDI_FK;
alter table RSC_NORMAT drop constraint RSC_NORBOL_FK;
alter table RSC_NORMAT drop constraint RSC_NORUNA_FK;
alter table RSC_NORMAT drop constraint RSC_NORTIP_FK;
alter table RSC_TRAENL drop constraint RSC_TENENL_FK;
alter table RSC_AUDITO drop constraint RSC_AUDHIS_FK;
alter table RSC_COMENT drop constraint RSC_COMFIC_FK;
alter table RSC_COMENT drop constraint RSC_COMUSU_FK;
alter table RSC_COMENT drop constraint RSC_COMPRO_FK;
alter table RSC_GRPGID drop constraint FK_RSC_GRPG_REFERENCE_RSC_GRPG;
alter table RSC_SCRGID drop constraint RSC_SGRSGI_FK;
alter table RSC_AGHEVI drop constraint RSC_AGHICG_FK;
alter table RSC_AGHEVI drop constraint RSC_AGHICO_FK;
alter table RSC_AGHEVI drop constraint RSC_AGHPOB_FK;
alter table RSC_AGHEVI drop constraint RSC_AGHFOT_FK;
alter table RSC_ENLACE drop constraint RSC_ENLPRO_FK;
alter table RSC_ENLACE drop constraint RSC_ENLFIC_FK;
alter table RSC_SCRKEY drop constraint RSC_STPSCK_FK;
alter table RSC_TRATRT drop constraint RSC_TTTTRT_FK;
alter table RSC_SCRTEM drop constraint FK_RSC_SCRT_REFERENCE_RSC_SCRI;
alter table RSC_AGRMAT drop constraint RSC_AGMSEC_FK;
alter table RSC_TRADOC drop constraint RSC_TDOARC_FK;
alter table RSC_TRADOC drop constraint RSC_TDODOC_FK;
alter table RSC_TRAESP drop constraint RSC_TESESP_FK;
alter table RSC_DOCUME drop constraint RSC_DOCPRO_FK;
alter table RSC_DOCUME drop constraint RSC_DOCFIC_FK;
alter table RSC_DOCUME drop constraint RSC_DOCARC_FK;
alter table RSC_DOCUME drop constraint RSC_DOCTRA_FK;
alter table RSC_TRASEC drop constraint RSC_TSESEC_FK;
alter table RSC_ICOFAM drop constraint RSC_ICFFAM_FK;
alter table RSC_ICOFAM drop constraint RSC_ICFICO_FK;
alter table RSC_ICOFAM drop constraint RSC_ICFPEC_FK;
alter table RSC_ESTADI drop constraint RSC_ESTHIS_FK;
alter table RSC_TRAAGH drop constraint RSC_TRGAGH_FK;
alter table RSC_FICHUA drop constraint RSC_FUAUNA_FK;
alter table RSC_FICHUA drop constraint RSC_FUASEC_FK;
alter table RSC_FICHUA drop constraint RSC_FUAFIC_FK;
alter table RSC_FORMUL drop constraint RSC_FORARC_FK;
alter table RSC_FORMUL drop constraint RSC_FORMAN_FK;
alter table RSC_FORMUL drop constraint RSC_FORTRA_FK;
alter table RSC_GRPGEN drop constraint FK_RSC_GRPG_REFERENCE_RSC_SCRT;
alter table RSC_TRATRA drop constraint RSC_TTRTRA_FK;
alter table RSC_SENSGR drop constraint FKB4A83B7F8E2C81C0;
alter table RSC_SENSGR drop constraint FKB4A83B7F8E2C8202;
alter table RSC_HISENV drop constraint FK_RSC_HISE_REFERENCE_RSC_SCRT;
alter table RSC_PRONOR drop constraint RSC_PRNNOR_FK;
alter table RSC_PRONOR drop constraint RSC_PRNPRO_FK;
alter table RSC_HECVIT drop constraint RSC_HEVFOT_FK;
alter table RSC_HECVIT drop constraint RSC_HEVICO_FK;
alter table RSC_HECVIT drop constraint RSC_HEVICG_FK;
alter table RSC_TRAINI drop constraint RSC_TRAINI_FK;
alter table RSC_FICHA drop constraint RSC_FICBAN_FK;
alter table RSC_FICHA drop constraint RSC_FICICO_FK;
alter table RSC_FICHA drop constraint RSC_FICIMA_FK;
alter table RSC_FICHA drop constraint RSC_FIRADM_FK;
alter table RSC_EDIFIC drop constraint RSC_EDIPLA_FK;
alter table RSC_EDIFIC drop constraint RSC_EDIFOG_FK;
alter table RSC_EDIFIC drop constraint RSC_EDIFOP_FK;
alter table RSC_MATAGR drop constraint RSC_MAGAGM_FK;
alter table RSC_MATAGR drop constraint RSC_MAGMAT_FK;
alter table RSC_MATERI drop constraint RSC_MATFOT_FK;
alter table RSC_MATERI drop constraint RSC_MATICO_FK;
alter table RSC_MATERI drop constraint RSC_MATICG_FK;
alter table RSC_TRAMAT drop constraint RSC_TMADIS_FK;
alter table RSC_TRAMAT drop constraint RSC_TMANOR_FK;
alter table RSC_TRAMAT drop constraint RSC_TMAMAT_FK;
alter table RSC_TRAMAT drop constraint RSC_TMACON_FK;
alter table RSC_TRAFIC drop constraint RSC_TFIFIC_FK;
alter table RSC_TRANOL drop constraint RSC_TNLARC_FK;
alter table RSC_TRANOL drop constraint RSC_TNLNOR_FK;
alter table RSC_TRAHEV drop constraint RSC_THECON_FK;
alter table RSC_TRAHEV drop constraint RSC_THEDIS_FK;
alter table RSC_TRAHEV drop constraint RSC_THENOR_FK;
alter table RSC_TRAHEV drop constraint RSC_THEHEV_FK;
alter table RSC_TRAUNM drop constraint RSC_TRNUNM_FK;
alter table RSC_PERSON drop constraint RSC_PERUNA_FK;
alter table RSC_ENVTEM drop constraint FK_RSC_ENVT_REFERENCE_RSC_HISE;
alter table RSC_SECCIO drop constraint RSC_SECSEC_FK;
alter table RSC_UNIADM drop constraint RSC_UNAESP_FK;
alter table RSC_UNIADM drop constraint RSC_UNRADM_FK;
alter table RSC_UNIADM drop constraint RSC_UNALOV_FK;
alter table RSC_UNIADM drop constraint RSC_UNAUNA_FK;
alter table RSC_UNIADM drop constraint RSC_UNATRT_FK;
alter table RSC_UNIADM drop constraint RSC_UNALOS_FK;
alter table RSC_UNIADM drop constraint RSC_UNALOT_FK;
alter table RSC_UNIADM drop constraint RSC_UNALOH_FK;
alter table RSC_UNIADM drop constraint RSC_UNAFOP_FK;
alter table RSC_UNIADM drop constraint RSC_UNAFOG_FK;
alter table RSC_TRAMIT drop constraint RSC_TRAPRO_FK;
alter table RSC_HISTOR drop constraint RSC_HISUNA_FK;
alter table RSC_HISTOR drop constraint RSC_HISNOR_FK;
alter table RSC_HISTOR drop constraint RSC_HISPRO_FK;
alter table RSC_HISTOR drop constraint RSC_HISFIC_FK;
alter table RSC_HISTOR drop constraint RSC_HISMAT_FK;
alter table RSC_FICHEV drop constraint RSC_FIHFIC_FK;
alter table RSC_FICHEV drop constraint RSC_FIHHEV_FK;
alter table RSC_ICMATE drop constraint RSC_ICMPEC_FK;
alter table RSC_ICMATE drop constraint RSC_ICMICO_FK;
alter table RSC_ICMATE drop constraint RSC_ICMMAT_FK;
alter table RSC_PROCED drop constraint RSC_PROUNA_FK;
alter table RSC_PROCED drop constraint RSC_PROFAM_FK;
alter table RSC_PROCED drop constraint RSC_PROINI_FK;
alter table RSC_PROCED drop constraint RSC_PRRADM_FK;
alter table RSC_SCRENV drop constraint RSC_STPSENV_FK;
alter table RSC_UNAEDI drop constraint RSC_UNEUNA_FK;
alter table RSC_UNAEDI drop constraint RSC_UNEEDI_FK;
alter table RSC_SCRIPC drop constraint FK_RSC_SCRI_REF_RSC_GRG;
alter table RSC_SCRIPC drop constraint RSC_STPSCR_FK;
alter table RSC_SCRIPC drop constraint FK_RSC_SCRI_REFERENCE_RSC_GRG;
alter table RSC_SCRIPC drop constraint RSC_SGRSCR_FK;
alter table RSC_AFECTA drop constraint RSC_AFENOR_FK;
alter table RSC_AFECTA drop constraint RSC_AFENOA_FK;
alter table RSC_AFECTA drop constraint RSC_AFETIA_FK;
alter table RSC_ESPTER drop constraint RSC_ESPLOG_FK;
alter table RSC_ESPTER drop constraint RSC_ESPMAP_FK;
alter table RSC_ESPTER drop constraint RSC_ESPESP_FK;
alter table RSC_TRAFAM drop constraint RSC_TFAFAM_FK;
drop table RSC_INICI cascade constraints;
drop table RSC_PROMAT cascade constraints;
drop table RSC_SCRGRP cascade constraints;
drop table RSC_TRAPOB cascade constraints;
drop table RSC_TRANOE cascade constraints;
drop table RSC_TIPAFE cascade constraints;
drop table RSC_TRAPRO cascade constraints;
drop table RSC_ADMREM cascade constraints;
drop table RSC_UNAMAT cascade constraints;
drop table RSC_TRAUNA cascade constraints;
drop table RSC_HEVIPR cascade constraints;
drop table RSC_TRATIA cascade constraints;
drop table RSC_TRATIP cascade constraints;
drop table RSC_HEVIAG cascade constraints;
drop table RSC_PUBOBJ cascade constraints;
drop table RSC_TRAPEC cascade constraints;
drop table RSC_IDIOMA cascade constraints;
drop table RSC_TRAAGM cascade constraints;
drop table RSC_FICMAT cascade constraints;
drop table RSC_UNAUSU cascade constraints;
drop table RSC_TRAEDI cascade constraints;
drop table RSC_NORMAT cascade constraints;
drop table RSC_TRAENL cascade constraints;
drop table RSC_AUDITO cascade constraints;
drop table RSC_COMENT cascade constraints;
drop table RSC_ARCHIV cascade constraints;
drop table RSC_GRPGID cascade constraints;
drop table RSC_SCRGID cascade constraints;
drop table RSC_AGHEVI cascade constraints;
drop table RSC_BOLETI cascade constraints;
drop table RSC_ENLACE cascade constraints;
drop table RSC_SCRKEY cascade constraints;
drop table RSC_TRATRT cascade constraints;
drop table RSC_SCRTEM cascade constraints;
drop table RSC_AGRMAT cascade constraints;
drop table RSC_TRADOC cascade constraints;
drop table RSC_TRAESP cascade constraints;
drop table RSC_DOCUME cascade constraints;
drop table RSC_TRASEC cascade constraints;
drop table RSC_ICOFAM cascade constraints;
drop table RSC_ESTADI cascade constraints;
drop table RSC_TRAAGH cascade constraints;
drop table RSC_FICHUA cascade constraints;
drop table RSC_TIPO cascade constraints;
drop table RSC_FORMUL cascade constraints;
drop table RSC_GRPGEN cascade constraints;
drop table RSC_TRATRA cascade constraints;
drop table RSC_SENSGR cascade constraints;
drop table RSC_HISENV cascade constraints;
drop table RSC_PRONOR cascade constraints;
drop table RSC_HECVIT cascade constraints;
drop table RSC_TRAINI cascade constraints;
drop table RSC_FICHA cascade constraints;
drop table RSC_SCRTIP cascade constraints;
drop table RSC_EDIFIC cascade constraints;
drop table RSC_TRATAM cascade constraints;
drop table RSC_MATAGR cascade constraints;
drop table RSC_DESTIN cascade constraints;
drop table RSC_MATERI cascade constraints;
drop table RSC_TRAMAT cascade constraints;
drop table RSC_USUARI cascade constraints;
drop table RSC_PERCIU cascade constraints;
drop table RSC_TRAFIC cascade constraints;
drop table RSC_TRANOL cascade constraints;
drop table RSC_FAMILI cascade constraints;
drop table RSC_TRAHEV cascade constraints;
drop table RSC_TRAUNM cascade constraints;
drop table RSC_PERSON cascade constraints;
drop table RSC_ENVTEM cascade constraints;
drop table RSC_SECCIO cascade constraints;
drop table RSC_UNIADM cascade constraints;
drop table RSC_TRAMIT cascade constraints;
drop table RSC_HISTOR cascade constraints;
drop table RSC_FICHEV cascade constraints;
drop table RSC_ICMATE cascade constraints;
drop table RSC_PROCED cascade constraints;
drop table RSC_SCRENV cascade constraints;
drop table RSC_UNAEDI cascade constraints;
drop table RSC_SCRIPC cascade constraints;
drop table RSC_AFECTA cascade constraints;
drop table RSC_ESPTER cascade constraints;
drop table RSC_TRAFAM cascade constraints;
drop sequence RSC_SEQ_COM;
drop sequence RSC_SEQ_ALL;
drop sequence RSC_SEQSCR;
drop sequence RSC_SEQSEN;
drop sequence RSC_SEQSCK;
drop sequence RSC_SEQSGR;
drop sequence RSC_SEQGRP;
drop sequence RSC_SEQHIS;
create table RSC_INICI (
   INI_CODI number(19,0) not null,
   primary key (INI_CODI)
);
create table RSC_PROMAT (
   PRM_CODMAT number(19,0) not null,
   PRM_CODPRO number(19,0) not null,
   primary key (PRM_CODPRO, PRM_CODMAT)
);
create table RSC_SCRGRP (
   SGR_COD number(19,0) not null,
   SGR_IDENT varchar2(255),
   SGR_STPCOD number(19,0) not null,
   primary key (SGR_COD)
);
create table RSC_TRAPOB (
   TRP_CODPOB number(19,0) not null,
   TRP_TITULO varchar2(256),
   TRP_DESCRI varchar2(4000),
   TRP_PALCLA varchar2(4000),
   TRP_CODIDI varchar2(2) not null,
   primary key (TRP_CODPOB, TRP_CODIDI)
);
create table RSC_TRANOE (
   TNE_CODNOR number(19,0) not null,
   TNE_SECCIO varchar2(256),
   TNE_APARTA varchar2(256),
   TNE_PAGINI number(10,0),
   TNE_PAGFIN number(10,0),
   TNE_TITULO varchar2(512),
   TNE_ENLACE varchar2(512),
   TNE_RESPON varchar2(512),
   TNE_CODARC number(19,0),
   TNE_OBSERV varchar2(4000),
   TNE_CODIDI varchar2(2) not null,
   primary key (TNE_CODNOR, TNE_CODIDI)
);
create table RSC_TIPAFE (
   TIA_CODI number(19,0) not null,
   primary key (TIA_CODI)
);
create table RSC_TRAPRO (
   TPR_CODPRO number(19,0) not null,
   TPR_NOMBRE varchar2(256),
   TPR_RESUME varchar2(4000),
   TPR_DESTIN varchar2(4000),
   TPR_REQUIS varchar2(4000),
   TPR_PLAZOS varchar2(4000),
   TPR_SILEN varchar2(4000),
   TPR_RESOLUCION varchar2(4000),
   TPR_NOTIFICACION varchar2(4000),
   TPR_RECURS varchar2(4000),
   TPR_OBSERV varchar2(4000),
   TPR_LUGAR varchar2(4000),
   TPR_CODIDI varchar2(2) not null,
   primary key (TPR_CODPRO, TPR_CODIDI)
);
create table RSC_ADMREM (
   ADM_CODI number(19,0) not null,
   ADM_ENDPOI varchar2(512),
   ADM_NOMBRE varchar2(512),
   ADM_NIVPRO number(10,0),
   ADM_CODSUA varchar2(512),
   ADM_IDREMO varchar2(512) not null unique,
   ADM_CODESP number(19,0),
   ADM_LOGOP number(19,0),
   ADM_LOGOG number(19,0),
   primary key (ADM_CODI)
);
create table RSC_UNAMAT (
   UNM_CODI number(19,0) not null,
   UNM_UNAPRN varchar2(1),
   UNM_CODMAT number(19,0),
   UNM_CODUNA number(19,0),
   primary key (UNM_CODI)
);
create table RSC_TRAUNA (
   TUN_CODUNA number(19,0) not null,
   TUN_NOMBRE varchar2(256),
   TUN_PRESEN varchar2(4000),
   TUN_ABREVI varchar2(64),
   TUN_URL varchar2(256),
   TUN_CODIDI varchar2(2) not null,
   primary key (TUN_CODUNA, TUN_CODIDI)
);
create table RSC_HEVIPR (
   HVP_CODI number(19,0) not null,
   HVP_CODHEV number(19,0),
   HVP_CODPRO number(19,0),
   HVP_ORDEN number(10,0),
   primary key (HVP_CODI)
);
create table RSC_TRATIA (
   TTA_CODTIA number(19,0) not null,
   TTA_NOMBRE varchar2(256),
   TTA_CODIDI varchar2(2) not null,
   primary key (TTA_CODTIA, TTA_CODIDI)
);
create table RSC_TRATIP (
   TTI_CODTIP number(19,0) not null,
   TTI_NOMBRE varchar2(128),
   TTI_CODIDI varchar2(2) not null,
   primary key (TTI_CODTIP, TTI_CODIDI)
);
create table RSC_HEVIAG (
   HVA_CODI number(19,0) not null,
   HVA_CODHEV number(19,0),
   HVA_CODAGH number(19,0),
   HVA_ORDEN number(10,0),
   primary key (HVA_CODI)
);
create table RSC_PUBOBJ (
   POB_CODI number(19,0) not null,
   POB_CODEST varchar2(128),
   POB_ORDEN number(10,0),
   primary key (POB_CODI)
);
create table RSC_TRAPEC (
   TPE_CODPEC number(19,0) not null,
   TPE_NOMBRE varchar2(256),
   TPE_DESCRI varchar2(4000),
   TPE_CODIDI varchar2(2) not null,
   primary key (TPE_CODPEC, TPE_CODIDI)
);
create table RSC_IDIOMA (
   IDI_CODI varchar2(2) not null,
   IDI_ORDEN number(10,0) not null,
   IDI_CODEST varchar2(128),
   IDI_NOMBRE varchar2(128),
   primary key (IDI_CODI)
);
create table RSC_TRAAGM (
   TAM_CODAGM number(19,0) not null,
   TAM_NOMBRE varchar2(256),
   TAM_CODIDI varchar2(2) not null,
   primary key (TAM_CODAGM, TAM_CODIDI)
);
create table RSC_FICMAT (
   FIM_CODFIC number(19,0) not null,
   FIM_CODMAT number(19,0) not null,
   primary key (FIM_CODFIC, FIM_CODMAT)
);
create table RSC_UNAUSU (
   UNU_CODUNA number(19,0) not null,
   UNU_CODUSU number(19,0) not null,
   primary key (UNU_CODUSU, UNU_CODUNA)
);
create table RSC_TRAEDI (
   TED_CODEDI number(19,0) not null,
   TED_DESCRI varchar2(4000),
   TED_CODIDI varchar2(2) not null,
   primary key (TED_CODEDI, TED_CODIDI)
);
create table RSC_NORMAT (
   NOR_CODI number(19,0) not null,
   NOR_TYPE varchar2(64) not null,
   NOR_NUMERO number(19,0),
   NOR_REGIST number(19,0),
   NOR_LEY varchar2(256),
   NOR_FECHA date,
   NOR_FECBOL date,
   NOR_VALIDA number(10,0),
   NOR_CODBOL number(19,0),
   NOR_CODTIP number(19,0),
   NOR_CODUNA number(19,0),
   primary key (NOR_CODI)
);
create table RSC_TRAENL (
   TEN_CODENL number(19,0) not null,
   TEN_TITULO varchar2(256),
   TEN_ENLACE varchar2(512),
   TEN_CODIDI varchar2(2) not null,
   primary key (TEN_CODENL, TEN_CODIDI)
);
create table RSC_AUDITO (
   AUD_CODI number(19,0) not null,
   AUD_USUARI varchar2(256),
   AUD_FECHA date,
   AUD_CODHIS number(19,0),
   AUD_CODOPE number(10,0),
   primary key (AUD_CODI)
);
create table RSC_COMENT (
   COM_CODI number(19,0) not null,
   COM_TYPE varchar2(64) not null,
   COM_TITULO varchar2(256),
   COM_MOTIVO varchar2(64) not null,
   COM_FECHA date,
   COM_CONTEN varchar2(4000),
   COM_AUTOR varchar2(256),
   COM_CODUSU number(19,0),
   COM_CODPRO number(19,0),
   COM_CODFIC number(19,0),
   primary key (COM_CODI)
);
create table RSC_ARCHIV (
   ARC_CODI number(19,0) not null,
   ARC_NOMBRE varchar2(128) not null,
   ARC_MIME varchar2(128) not null,
   ARC_PESO number(19,0) not null,
   ARC_DATOS blob,
   primary key (ARC_CODI)
);
create table RSC_GRPGID (
   SGR_GRPCOD number(19,0) not null,
   SGR_NOM varchar2(100),
   SGR_CODIDI varchar2(2) not null,
   primary key (SGR_GRPCOD, SGR_CODIDI)
);
create table RSC_SCRGID (
   SGI_SGRCOD number(19,0) not null,
   SGI_NOM varchar2(100),
   SGI_CODIDI varchar2(2) not null,
   primary key (SGI_SGRCOD, SGI_CODIDI)
);
create table RSC_AGHEVI (
   AGH_CODI number(19,0) not null,
   AGH_CODEST varchar2(128),
   AGH_FOTO number(19,0),
   AGH_ICONO number(19,0),
   AGH_ICOGRA number(19,0),
   AGH_CODPOB number(19,0),
   primary key (AGH_CODI)
);
create table RSC_BOLETI (
   BOL_CODI number(19,0) not null,
   BOL_NOMBRE varchar2(256),
   BOL_ENLACE varchar2(512),
   primary key (BOL_CODI)
);
create table RSC_ENLACE (
   ENL_CODI number(19,0) not null,
   ENL_CODFIC number(19,0),
   ENL_CODPRO number(19,0),
   ENL_ORDEN number(19,0),
   primary key (ENL_CODI)
);
create table RSC_SCRKEY (
   SCK_EMAIL varchar2(255) not null,
   SCK_IDIMG varchar2(255),
   SCK_IDACT varchar2(255),
   SCK_NUMENV number(5,0),
   SCK_FCENV date,
   SCK_STPCOD number(19,0) not null,
   SCK_COD number(19,0) not null,
   primary key (SCK_COD)
);
create table RSC_TRATRT (
   TTT_CODTRT number(19,0) not null,
   TTT_TIPO varchar2(256),
   TTT_CARGOM varchar2(256),
   TTT_CARGOF varchar2(256),
   TTT_TRATAM varchar2(256),
   TTT_TRATAF varchar2(256),
   TTT_CODIDI varchar2(2) not null,
   primary key (TTT_CODTRT, TTT_CODIDI)
);
create table RSC_SCRTEM (
   SCR_COD number(19,0) not null,
   MAT_CODI number(19,0)
);
create table RSC_AGRMAT (
   AGM_CODI number(19,0) not null,
   AGM_CODEST varchar2(256),
   AGM_CODSEC number(19,0),
   primary key (AGM_CODI)
);
create table RSC_TRADOC (
   TDO_CODDOC number(19,0) not null,
   TDO_TITULO varchar2(256),
   TDO_DESCRI varchar2(4000),
   TDO_CODARC number(19,0),
   TDO_CODIDI varchar2(2) not null,
   primary key (TDO_CODDOC, TDO_CODIDI)
);
create table RSC_TRAESP (
   TES_CODESP number(19,0) not null,
   TES_NOMBRE varchar2(256),
   TES_CODIDI varchar2(2) not null,
   primary key (TES_CODESP, TES_CODIDI)
);
create table RSC_DOCUME (
   DOC_CODI number(19,0) not null,
   DOC_CODFIC number(19,0),
   DOC_CODPRO number(19,0),
   DOC_CODARC number(19,0),
   DOC_CODTRA number(19,0),
   DOC_ORDEN number(19,0),
   primary key (DOC_CODI)
);
create table RSC_TRASEC (
   TSE_CODSEC number(19,0) not null,
   TSE_TITULO varchar2(256),
   TSE_CODIDI varchar2(2) not null,
   primary key (TSE_CODSEC, TSE_CODIDI)
);
create table RSC_ICOFAM (
   ICF_CODI number(19,0) not null,
   ICF_CODFAM number(19,0),
   ICF_CODPEC number(19,0),
   ICF_ICONO number(19,0),
   primary key (ICF_CODI)
);
create table RSC_ESTADI (
   EST_CODI number(19,0) not null,
   EST_FECHA date,
   EST_CONTAD number(10,0),
   EST_CODHIS number(19,0),
   primary key (EST_CODI)
);
create table RSC_TRAAGH (
   TRG_CODAGH number(19,0) not null,
   TRG_NOMBRE varchar2(256),
   TRG_DESCRI varchar2(4000),
   TRG_PALCLA varchar2(4000),
   TRG_CODIDI varchar2(2) not null,
   primary key (TRG_CODAGH, TRG_CODIDI)
);
create table RSC_FICHUA (
   FUA_CODI number(19,0) not null,
   FUA_CODUNA number(19,0),
   FUA_CODFIC number(19,0),
   FUA_CODSEC number(19,0),
   FUA_ORDEN number(10,0),
   FUA_ORDSEC number(10,0),
   primary key (FUA_CODI)
);
create table RSC_TIPO (
   TIP_CODI number(19,0) not null,
   primary key (TIP_CODI)
);
create table RSC_FORMUL (
   FOR_CODI number(19,0) not null,
   FOR_NOMBRE varchar2(256),
   FOR_URL varchar2(512),
   FOR_CODARC number(19,0),
   FOR_URLMAN varchar2(512),
   FOR_MANUAL number(19,0),
   FOR_CODTRA number(19,0),
   primary key (FOR_CODI)
);
create table RSC_GRPGEN (
   GRP_COD number(19,0) not null,
   GRP_TIPO number(10,0),
   GRP_STPCOD number(19,0) not null,
   primary key (GRP_COD)
);
create table RSC_TRATRA (
   TTR_CODTTR number(19,0) not null,
   TTR_NOMBRE varchar2(256),
   TTR_DESCRI varchar2(4000),
   TTR_DOCUME varchar2(4000),
   TTR_PLAZOS varchar2(512),
   TTR_CODIDI varchar2(2) not null,
   primary key (TTR_CODTTR, TTR_CODIDI)
);
create table RSC_SENSGR (
   SSC_CODSEN number(19,0) not null,
   SSC_CODSGR number(19,0) not null,
   primary key (SSC_CODSEN, SSC_CODSGR)
);
create table RSC_HISENV (
   HEN_COD number(19,0) not null,
   HEN_FENVIO date,
   HEN_STPCOD number(19,0) not null,
   primary key (HEN_COD)
);
create table RSC_PRONOR (
   PRN_CODNOR number(19,0) not null,
   PRN_CODPRO number(19,0) not null,
   primary key (PRN_CODPRO, PRN_CODNOR)
);
create table RSC_HECVIT (
   HEV_CODI number(19,0) not null,
   HEV_ORDEN number(10,0),
   HEV_CODEST varchar2(255),
   HEV_ICONO number(19,0),
   HEV_FOTO number(19,0),
   HEV_ICOGRA number(19,0),
   primary key (HEV_CODI)
);
create table RSC_TRAINI (
   TIN_CODINI number(19,0) not null,
   TIN_NOMBRE varchar2(256),
   TIN_DESCRI varchar2(4000),
   TIN_CODIDI varchar2(2) not null,
   primary key (TIN_CODINI, TIN_CODIDI)
);
create table RSC_FICHA (
   FIC_CODI number(19,0) not null,
   FIC_TYPE varchar2(64) not null,
   FIC_FECPUB date,
   FIC_FECCAD date,
   FIC_FECACT date,
   FIC_ICONO number(19,0),
   FIC_IMAGEN number(19,0),
   FIC_BANER number(19,0),
   FIC_VALIDA number(10,0),
   FIC_INFO varchar2(4000),
   FIC_URLVID varchar2(255),
   FIC_URLFOR varchar2(255),
   FIC_FORTEM varchar2(1),
   FIR_IDEXTE number(19,0),
   FIR_URLREM varchar2(512),
   FIR_CODADM number(19,0),
   primary key (FIC_CODI)
);
create table RSC_SCRTIP (
   STP_COD number(19,0) not null,
   STP_CODUNI number(19,0),
   STP_IDENT varchar2(10),
   STP_URLCAB varchar2(200),
   STP_TITULO varchar2(50),
   STP_NOM varchar2(200),
   STP_MAIL varchar2(50),
   STP_PWD varchar2(50),
   STP_ESTADO number(10,0),
   STP_HORAJOB date,
   primary key (STP_COD)
);
create table RSC_EDIFIC (
   EDI_CODI number(19,0) not null,
   EDI_DIRECC varchar2(256),
   EDI_CODPOS varchar2(64),
   EDI_POBLAC varchar2(64),
   EDI_TELEFO varchar2(64),
   EDI_FAX varchar2(64),
   EDI_EMAIL varchar2(256),
   EDI_FOTOP number(19,0),
   EDI_FOTOG number(19,0),
   EDI_PLANO number(19,0),
   EDI_LAT varchar2(15),
   EDI_LNG varchar2(15),
   primary key (EDI_CODI)
);
create table RSC_TRATAM (
   TRT_CODI number(19,0) not null,
   TRT_CODEST varchar2(128),
   primary key (TRT_CODI)
);
create table RSC_MATAGR (
   MAG_CODI number(19,0) not null,
   MAG_CODMAT number(19,0),
   MAG_CODAGM number(19,0),
   MAG_ORDEN number(10,0),
   primary key (MAG_CODI)
);
create table RSC_DESTIN (
   DES_CODI number(19,0) not null,
   DES_NOMBRE varchar2(512),
   DES_IDREMO varchar2(512),
   DES_ENDPOI varchar2(512),
   DES_EMAIL varchar2(256),
   primary key (DES_CODI)
);
create table RSC_MATERI (
   MAT_CODI number(19,0) not null,
   MAT_CODHIT varchar2(128),
   MAT_CODEST varchar2(256),
   MAT_DESTAC number(1,0) not null,
   MAT_ICONO number(19,0),
   MAT_FOTO number(19,0),
   MAT_ICOGRA number(19,0),
   primary key (MAT_CODI)
);
create table RSC_TRAMAT (
   TMA_CODMAT number(19,0) not null,
   TMA_NOMBRE varchar2(256),
   TMA_DESCRI varchar2(4000),
   TMA_PALCLA varchar2(4000),
   TMA_CODDIS number(19,0),
   TMA_NORMAT number(19,0),
   TMA_CONTEN number(19,0),
   TMA_CODIDI varchar2(2) not null,
   primary key (TMA_CODMAT, TMA_CODIDI)
);
create table RSC_USUARI (
   USU_CODI number(19,0) not null,
   USU_USERNA varchar2(128) not null unique,
   USU_PASSWO varchar2(128),
   USU_NOMBRE varchar2(256),
   USU_OBSERV varchar2(4000),
   USU_PERFIL varchar2(64),
   primary key (USU_CODI)
);
create table RSC_PERCIU (
   PEC_CODI number(19,0) not null,
   PEC_CODEST varchar2(10),
   PEC_PATICO varchar2(256),
   primary key (PEC_CODI)
);
create table RSC_TRAFIC (
   TFI_CODFIC number(19,0) not null,
   TFI_TITULO varchar2(256),
   TFI_DESABR varchar2(4000),
   TFI_DESCRI varchar2(4000),
   TFI_URL varchar2(512),
   TFI_CODIDI varchar2(2) not null,
   primary key (TFI_CODFIC, TFI_CODIDI)
);
create table RSC_TRANOL (
   TNL_CODNOR number(19,0) not null,
   TNL_SECCIO varchar2(256),
   TNL_APARTA varchar2(256),
   TNL_PAGINI number(10,0),
   TNL_PAGFIN number(10,0),
   TNL_TITULO varchar2(512),
   TNL_ENLACE varchar2(512),
   TNL_CODARC number(19,0),
   TNL_OBSERV varchar2(4000),
   TNL_CODIDI varchar2(2) not null,
   primary key (TNL_CODNOR, TNL_CODIDI)
);
create table RSC_FAMILI (
   FAM_CODI number(19,0) not null,
   primary key (FAM_CODI)
);
create table RSC_TRAHEV (
   THE_CODHEV number(19,0) not null,
   THE_NOMBRE varchar2(256),
   THE_DESCRI varchar2(4000),
   THE_PALCLA varchar2(4000),
   THE_CODDIS number(19,0),
   THE_NORMAT number(19,0),
   THE_CONTEN number(19,0),
   THE_CODIDI varchar2(2) not null,
   primary key (THE_CODHEV, THE_CODIDI)
);
create table RSC_TRAUNM (
   TRN_CODUNM number(19,0) not null,
   TRN_URLUM varchar2(256),
   TRN_CODIDI varchar2(2) not null,
   primary key (TRN_CODUNM, TRN_CODIDI)
);
create table RSC_PERSON (
   PER_CODI number(19,0) not null,
   PER_USERNA varchar2(128),
   PER_NOMBRE varchar2(256),
   PER_FUNCIO varchar2(256),
   PER_CARGO varchar2(256),
   PER_EMAIL varchar2(256),
   PER_EXTPUB varchar2(64),
   PER_NUMPUB varchar2(64),
   PER_EXTPRI varchar2(64),
   PER_NUMPRI varchar2(64),
   PER_EXTMOV varchar2(64),
   PER_NUMMOV varchar2(64),
   PER_CODUNA number(19,0),
   primary key (PER_CODI)
);
create table RSC_ENVTEM (
   HEN_COD number(19,0) not null,
   MAT_CODI number(19,0),
   HEN_HTML varchar2(255),
   HEN_ACTIVO number(10,0)
);
create table RSC_SECCIO (
   SEC_CODI number(19,0) not null,
   SEC_CODEST varchar2(20),
   SEC_PERFIL varchar2(128),
   SEC_ORDEN number(10,0),
   SEC_CODSEC number(19,0),
   primary key (SEC_CODI)
);
create table RSC_UNIADM (
   UNA_CODI number(19,0) not null,
   UNA_TYPE varchar2(64) not null,
   UNA_BUSKEY varchar2(41),
   UNA_CLVHIT varchar2(128),
   UNA_DOMINI varchar2(256),
   UNA_ORDEN number(19,0),
   UNA_VALIDA number(10,0),
   UNA_RESPON varchar2(256),
   UNA_TELEFO varchar2(64),
   UNA_FAX varchar2(64),
   UNA_EMAIL varchar2(256),
   UNA_SEXRES number(10,0),
   UNA_CODEST varchar2(256),
   UNA_NFOTO1 number(10,0),
   UNA_NFOTO2 number(10,0),
   UNA_NFOTO3 number(10,0),
   UNA_NFOTO4 number(10,0),
   UNA_FOTOP number(19,0),
   UNA_FOTOG number(19,0),
   UNA_LOGOH number(19,0),
   UNA_LOGOV number(19,0),
   UNA_LOGOS number(19,0),
   UNA_LOGOT number(19,0),
   UNA_CODUNA number(19,0),
   UNA_CODESP number(19,0),
   UNA_CODTRT number(19,0),
   UNR_IDEXTE number(19,0),
   UNR_URLREM varchar2(512),
   UNR_CODADM number(19,0),
   primary key (UNA_CODI)
);
create table RSC_TRAMIT (
   TRA_CODI number(19,0) not null,
   TRA_FASE number(10,0),
   TRA_CODPRO number(19,0),
   TRA_CODIVUDS number(19,0),
   TRA_DATCADU date,
   TRA_DATPUBL date,
   TRA_DATACTU date,
   TRA_DESCTAXA varchar2(255),
   TRA_CODTAXA varchar2(255),
   TRA_FORPAGTAXA varchar2(255),
   TRA_VERSIO number(10,0),
   TRA_URLEXTE varchar2(255),
   primary key (TRA_CODI)
);
create table RSC_HISTOR (
   HIS_CODI number(19,0) not null,
   HIS_TYPE varchar2(64) not null,
   HIS_NOMBRE varchar2(512),
   HIS_CODUNA number(19,0),
   HIS_CODNOR number(19,0),
   HIS_CODPRO number(19,0),
   HIS_CODFIC number(19,0),
   HIS_CODMAT number(19,0),
   primary key (HIS_CODI)
);
create table RSC_FICHEV (
   FIH_CODFIC number(19,0) not null,
   FIH_CODHEV number(19,0) not null,
   primary key (FIH_CODFIC, FIH_CODHEV)
);
create table RSC_ICMATE (
   ICM_CODI number(19,0) not null,
   ICM_CODMAT number(19,0),
   ICM_CODPEC number(19,0),
   ICM_ICONO number(19,0),
   primary key (ICM_CODI)
);
create table RSC_PROCED (
   PRO_CODI number(19,0) not null,
   PRO_TYPE varchar2(64) not null,
   PRO_SIGNAT varchar2(256),
   PRO_FECCAD date,
   PRO_FECPUB date,
   PRO_FECACT date,
   PRO_VALIDA number(10,0),
   PRO_TRAMIT varchar2(255),
   PRO_VERSIO number(19,0),
   PRO_INFO varchar2(4000),
   PRO_URLEXT varchar2(1024),
   PRO_ORDCON number(19,0),
   PRO_ORDDIR number(19,0),
   PRO_ORDSER number(19,0),
   PRO_INDICA varchar2(1024),
   PRO_VENTANA varchar2(1024),
   PRO_CODUNA number(19,0),
   PRO_CODFAM number(19,0),
   PRO_CODINI number(19,0),
   PRR_IDEXTE number(19,0),
   PRR_URLREM varchar2(512),
   PRR_CODADM number(19,0),
   primary key (PRO_CODI)
);
create table RSC_SCRENV (
   SEN_COD number(19,0) not null,
   SEN_FCENVIO date,
   SEN_CANAL varchar2(1),
   SEN_ASUNTO varchar2(500),
   SEN_TITULO varchar2(100),
   SEN_HTML clob,
   SEN_TIPO varchar2(1),
   SEN_IDSECC number(19,0),
   SEN_ESTADO varchar2(1),
   SEN_FCENV date,
   SEN_FCALTA date,
   SEN_UALTA number(19,0),
   SEN_FCMOD date,
   SEN_UMOD number(19,0),
   SEN_FCBAJA date,
   SEN_STPCOD number(19,0) not null,
   primary key (SEN_COD)
);
create table RSC_UNAEDI (
   UNE_CODEDI number(19,0) not null,
   UNE_CODUNA number(19,0) not null,
   primary key (UNE_CODUNA, UNE_CODEDI)
);
create table RSC_SCRIPC (
   SCR_COD number(19,0) not null,
   SCR_NOM varchar2(100),
   SCR_APE1 varchar2(100),
   SCR_APE2 varchar2(100),
   SCR_EMAIL varchar2(100),
   SCR_SMS varchar2(100),
   SCR_TELEFONO varchar2(100),
   SCR_TIPO varchar2(1),
   SCR_ENOM varchar2(200),
   SCR_EAREA varchar2(200),
   SCR_EDPTO varchar2(200),
   SCR_ECARGO varchar2(200),
   SCR_IDBOLE varchar2(1),
   SCR_IDALER varchar2(1),
   SCR_IDESTU varchar2(1),
   SCR_ESTADO varchar2(1),
   SCR_ORIGEN varchar2(1),
   SCR_REFTRA varchar2(100),
   SCR_UALTA number(19,0),
   SCR_FCALTA date,
   SCR_FCMOD date,
   SCR_FCBAJA date,
   SCR_SEXO varchar2(255),
   SCR_ANYONAC number(10,0),
   SCR_PAISNAC number(10,0),
   SCR_PROVNAC number(10,0),
   SCR_ISLANAC number(10,0),
   SCR_MUNINAC number(10,0),
   SCR_LNACOTROS varchar2(255),
   SCR_PAISRESID number(10,0),
   SCR_PROVRESID number(10,0),
   SCR_ISLARESID number(10,0),
   SCR_MUNIRESID number(10,0),
   SCR_IDIOMA varchar2(255),
   SCR_CIF varchar2(255),
   SCR_SEDESOCIAL varchar2(255),
   SCR_RAZONSOCIAL varchar2(255),
   SCR_CP varchar2(255),
   SCR_CONTACTO varchar2(255),
   SCR_GRNOM varchar2(255),
   SCR_RESUMENTEMAS varchar2(255),
   SCR_MOTIVOBAJA varchar2(200),
   SCR_ESTUDIOS number(19,0),
   SCR_PROFESION number(19,0),
   SCR_STPCOD number(19,0) not null,
   SCR_SGRCOD number(19,0),
   primary key (SCR_COD)
);
create table RSC_AFECTA (
   AFE_CODNOR number(19,0) not null,
   AFE_CODNOA number(19,0),
   AFE_CODTIA number(19,0)
);
create table RSC_ESPTER (
   ESP_CODI number(19,0) not null,
   ESP_NIVEL number(10,0) not null,
   ESP_COORDE varchar2(255),
   ESP_MAPA number(19,0),
   ESP_LOGO number(19,0),
   ESP_CODESP number(19,0),
   primary key (ESP_CODI)
);
create table RSC_TRAFAM (
   TFA_CODFAM number(19,0) not null,
   TFA_NOMBRE varchar2(256),
   TFA_DESCRI varchar2(4000),
   TFA_CODIDI varchar2(2) not null,
   primary key (TFA_CODFAM, TFA_CODIDI)
);
alter table RSC_PROMAT add constraint RSC_PRMMAT_FK foreign key (PRM_CODMAT) references RSC_MATERI;
alter table RSC_PROMAT add constraint RSC_PRMPRO_FK foreign key (PRM_CODPRO) references RSC_PROCED;
alter table RSC_SCRGRP add constraint RSC_STPSGR_FK foreign key (SGR_STPCOD) references RSC_SCRTIP;
alter table RSC_TRAPOB add constraint RSC_TRPPOB_FK foreign key (TRP_CODPOB) references RSC_PUBOBJ;
alter table RSC_TRANOE add constraint RSC_TNEARC_FK foreign key (TNE_CODARC) references RSC_ARCHIV;
alter table RSC_TRANOE add constraint RSC_TNENOR_FK foreign key (TNE_CODNOR) references RSC_NORMAT;
alter table RSC_TRAPRO add constraint RSC_TPRPRO_FK foreign key (TPR_CODPRO) references RSC_PROCED;
alter table RSC_ADMREM add constraint RSC_ADMESP_FK foreign key (ADM_CODESP) references RSC_ESPTER;
alter table RSC_ADMREM add constraint RSC_ADMLOG_FK foreign key (ADM_LOGOG) references RSC_ARCHIV;
alter table RSC_ADMREM add constraint RSC_ADMLOP_FK foreign key (ADM_LOGOP) references RSC_ARCHIV;
alter table RSC_UNAMAT add constraint RSC_UNMMAT_FK foreign key (UNM_CODMAT) references RSC_MATERI;
alter table RSC_UNAMAT add constraint RSC_UNMUNA_FK foreign key (UNM_CODUNA) references RSC_UNIADM;
alter table RSC_TRAUNA add constraint RSC_TUNUNA_FK foreign key (TUN_CODUNA) references RSC_UNIADM;
alter table RSC_HEVIPR add constraint RSC_HVPHEV_FK foreign key (HVP_CODHEV) references RSC_HECVIT;
alter table RSC_HEVIPR add constraint RSC_HVPPRO_FK foreign key (HVP_CODPRO) references RSC_PROCED;
alter table RSC_TRATIA add constraint RSC_TTATIA_FK foreign key (TTA_CODTIA) references RSC_TIPAFE;
alter table RSC_TRATIP add constraint RSC_TTITIP_FK foreign key (TTI_CODTIP) references RSC_TIPO;
alter table RSC_HEVIAG add constraint RSC_HVAAGH_FK foreign key (HVA_CODAGH) references RSC_AGHEVI;
alter table RSC_HEVIAG add constraint RSC_HVAHEV_FK foreign key (HVA_CODHEV) references RSC_HECVIT;
alter table RSC_TRAPEC add constraint RSC_TPEPEC_FK foreign key (TPE_CODPEC) references RSC_PERCIU;
alter table RSC_TRAAGM add constraint RSC_TAMAGM_FK foreign key (TAM_CODAGM) references RSC_AGRMAT;
alter table RSC_FICMAT add constraint RSC_FIMFIC_FK foreign key (FIM_CODFIC) references RSC_FICHA;
alter table RSC_FICMAT add constraint RSC_FIMMAT_FK foreign key (FIM_CODMAT) references RSC_MATERI;
alter table RSC_UNAUSU add constraint RSC_UNUUNA_FK foreign key (UNU_CODUNA) references RSC_UNIADM;
alter table RSC_UNAUSU add constraint RSC_UNUUSU_FK foreign key (UNU_CODUSU) references RSC_USUARI;
alter table RSC_TRAEDI add constraint RSC_TEDEDI_FK foreign key (TED_CODEDI) references RSC_EDIFIC;
alter table RSC_NORMAT add constraint RSC_NORBOL_FK foreign key (NOR_CODBOL) references RSC_BOLETI;
alter table RSC_NORMAT add constraint RSC_NORUNA_FK foreign key (NOR_CODUNA) references RSC_UNIADM;
alter table RSC_NORMAT add constraint RSC_NORTIP_FK foreign key (NOR_CODTIP) references RSC_TIPO;
alter table RSC_TRAENL add constraint RSC_TENENL_FK foreign key (TEN_CODENL) references RSC_ENLACE;
alter table RSC_AUDITO add constraint RSC_AUDHIS_FK foreign key (AUD_CODHIS) references RSC_HISTOR;
alter table RSC_COMENT add constraint RSC_COMFIC_FK foreign key (COM_CODFIC) references RSC_FICHA;
alter table RSC_COMENT add constraint RSC_COMUSU_FK foreign key (COM_CODUSU) references RSC_USUARI;
alter table RSC_COMENT add constraint RSC_COMPRO_FK foreign key (COM_CODPRO) references RSC_PROCED;
alter table RSC_GRPGID add constraint FK_RSC_GRPG_REFERENCE_RSC_GRPG foreign key (SGR_GRPCOD) references RSC_GRPGEN;
alter table RSC_SCRGID add constraint RSC_SGRSGI_FK foreign key (SGI_SGRCOD) references RSC_SCRGRP;
alter table RSC_AGHEVI add constraint RSC_AGHICG_FK foreign key (AGH_ICOGRA) references RSC_ARCHIV;
alter table RSC_AGHEVI add constraint RSC_AGHICO_FK foreign key (AGH_ICONO) references RSC_ARCHIV;
alter table RSC_AGHEVI add constraint RSC_AGHPOB_FK foreign key (AGH_CODPOB) references RSC_PUBOBJ;
alter table RSC_AGHEVI add constraint RSC_AGHFOT_FK foreign key (AGH_FOTO) references RSC_ARCHIV;
alter table RSC_ENLACE add constraint RSC_ENLPRO_FK foreign key (ENL_CODPRO) references RSC_PROCED;
alter table RSC_ENLACE add constraint RSC_ENLFIC_FK foreign key (ENL_CODFIC) references RSC_FICHA;
alter table RSC_SCRKEY add constraint RSC_STPSCK_FK foreign key (SCK_STPCOD) references RSC_SCRTIP;
alter table RSC_TRATRT add constraint RSC_TTTTRT_FK foreign key (TTT_CODTRT) references RSC_TRATAM;
alter table RSC_SCRTEM add constraint FK_RSC_SCRT_REFERENCE_RSC_SCRI foreign key (SCR_COD) references RSC_SCRIPC;
alter table RSC_AGRMAT add constraint RSC_AGMSEC_FK foreign key (AGM_CODSEC) references RSC_SECCIO;
alter table RSC_TRADOC add constraint RSC_TDOARC_FK foreign key (TDO_CODARC) references RSC_ARCHIV;
alter table RSC_TRADOC add constraint RSC_TDODOC_FK foreign key (TDO_CODDOC) references RSC_DOCUME;
alter table RSC_TRAESP add constraint RSC_TESESP_FK foreign key (TES_CODESP) references RSC_ESPTER;
alter table RSC_DOCUME add constraint RSC_DOCPRO_FK foreign key (DOC_CODPRO) references RSC_PROCED;
alter table RSC_DOCUME add constraint RSC_DOCFIC_FK foreign key (DOC_CODFIC) references RSC_FICHA;
alter table RSC_DOCUME add constraint RSC_DOCARC_FK foreign key (DOC_CODARC) references RSC_ARCHIV;
alter table RSC_DOCUME add constraint RSC_DOCTRA_FK foreign key (DOC_CODTRA) references RSC_ARCHIV;
alter table RSC_TRASEC add constraint RSC_TSESEC_FK foreign key (TSE_CODSEC) references RSC_SECCIO;
alter table RSC_ICOFAM add constraint RSC_ICFFAM_FK foreign key (ICF_CODFAM) references RSC_FAMILI;
alter table RSC_ICOFAM add constraint RSC_ICFICO_FK foreign key (ICF_ICONO) references RSC_ARCHIV;
alter table RSC_ICOFAM add constraint RSC_ICFPEC_FK foreign key (ICF_CODPEC) references RSC_PERCIU;
alter table RSC_ESTADI add constraint RSC_ESTHIS_FK foreign key (EST_CODHIS) references RSC_HISTOR;
alter table RSC_TRAAGH add constraint RSC_TRGAGH_FK foreign key (TRG_CODAGH) references RSC_AGHEVI;
alter table RSC_FICHUA add constraint RSC_FUAUNA_FK foreign key (FUA_CODUNA) references RSC_UNIADM;
alter table RSC_FICHUA add constraint RSC_FUASEC_FK foreign key (FUA_CODSEC) references RSC_SECCIO;
alter table RSC_FICHUA add constraint RSC_FUAFIC_FK foreign key (FUA_CODFIC) references RSC_FICHA;
alter table RSC_FORMUL add constraint RSC_FORARC_FK foreign key (FOR_CODARC) references RSC_ARCHIV;
alter table RSC_FORMUL add constraint RSC_FORMAN_FK foreign key (FOR_MANUAL) references RSC_ARCHIV;
alter table RSC_FORMUL add constraint RSC_FORTRA_FK foreign key (FOR_CODTRA) references RSC_TRAMIT;
alter table RSC_GRPGEN add constraint FK_RSC_GRPG_REFERENCE_RSC_SCRT foreign key (GRP_STPCOD) references RSC_SCRTIP;
alter table RSC_TRATRA add constraint RSC_TTRTRA_FK foreign key (TTR_CODTTR) references RSC_TRAMIT;
alter table RSC_SENSGR add constraint FKB4A83B7F8E2C81C0 foreign key (SSC_CODSEN) references RSC_SCRENV;
alter table RSC_SENSGR add constraint FKB4A83B7F8E2C8202 foreign key (SSC_CODSGR) references RSC_SCRGRP;
alter table RSC_HISENV add constraint FK_RSC_HISE_REFERENCE_RSC_SCRT foreign key (HEN_STPCOD) references RSC_SCRTIP;
alter table RSC_PRONOR add constraint RSC_PRNNOR_FK foreign key (PRN_CODNOR) references RSC_NORMAT;
alter table RSC_PRONOR add constraint RSC_PRNPRO_FK foreign key (PRN_CODPRO) references RSC_PROCED;
alter table RSC_HECVIT add constraint RSC_HEVFOT_FK foreign key (HEV_FOTO) references RSC_ARCHIV;
alter table RSC_HECVIT add constraint RSC_HEVICO_FK foreign key (HEV_ICONO) references RSC_ARCHIV;
alter table RSC_HECVIT add constraint RSC_HEVICG_FK foreign key (HEV_ICOGRA) references RSC_ARCHIV;
alter table RSC_TRAINI add constraint RSC_TRAINI_FK foreign key (TIN_CODINI) references RSC_INICI;
alter table RSC_FICHA add constraint RSC_FICBAN_FK foreign key (FIC_BANER) references RSC_ARCHIV;
alter table RSC_FICHA add constraint RSC_FICICO_FK foreign key (FIC_ICONO) references RSC_ARCHIV;
alter table RSC_FICHA add constraint RSC_FICIMA_FK foreign key (FIC_IMAGEN) references RSC_ARCHIV;
alter table RSC_FICHA add constraint RSC_FIRADM_FK foreign key (FIR_CODADM) references RSC_ADMREM;
alter table RSC_EDIFIC add constraint RSC_EDIPLA_FK foreign key (EDI_PLANO) references RSC_ARCHIV;
alter table RSC_EDIFIC add constraint RSC_EDIFOG_FK foreign key (EDI_FOTOG) references RSC_ARCHIV;
alter table RSC_EDIFIC add constraint RSC_EDIFOP_FK foreign key (EDI_FOTOP) references RSC_ARCHIV;
alter table RSC_MATAGR add constraint RSC_MAGAGM_FK foreign key (MAG_CODAGM) references RSC_AGRMAT;
alter table RSC_MATAGR add constraint RSC_MAGMAT_FK foreign key (MAG_CODMAT) references RSC_MATERI;
alter table RSC_MATERI add constraint RSC_MATFOT_FK foreign key (MAT_FOTO) references RSC_ARCHIV;
alter table RSC_MATERI add constraint RSC_MATICO_FK foreign key (MAT_ICONO) references RSC_ARCHIV;
alter table RSC_MATERI add constraint RSC_MATICG_FK foreign key (MAT_ICOGRA) references RSC_ARCHIV;
alter table RSC_TRAMAT add constraint RSC_TMADIS_FK foreign key (TMA_CODDIS) references RSC_ARCHIV;
alter table RSC_TRAMAT add constraint RSC_TMANOR_FK foreign key (TMA_NORMAT) references RSC_ARCHIV;
alter table RSC_TRAMAT add constraint RSC_TMAMAT_FK foreign key (TMA_CODMAT) references RSC_MATERI;
alter table RSC_TRAMAT add constraint RSC_TMACON_FK foreign key (TMA_CONTEN) references RSC_ARCHIV;
alter table RSC_TRAFIC add constraint RSC_TFIFIC_FK foreign key (TFI_CODFIC) references RSC_FICHA;
alter table RSC_TRANOL add constraint RSC_TNLARC_FK foreign key (TNL_CODARC) references RSC_ARCHIV;
alter table RSC_TRANOL add constraint RSC_TNLNOR_FK foreign key (TNL_CODNOR) references RSC_NORMAT;
alter table RSC_TRAHEV add constraint RSC_THECON_FK foreign key (THE_CONTEN) references RSC_ARCHIV;
alter table RSC_TRAHEV add constraint RSC_THEDIS_FK foreign key (THE_CODDIS) references RSC_ARCHIV;
alter table RSC_TRAHEV add constraint RSC_THENOR_FK foreign key (THE_NORMAT) references RSC_ARCHIV;
alter table RSC_TRAHEV add constraint RSC_THEHEV_FK foreign key (THE_CODHEV) references RSC_HECVIT;
alter table RSC_TRAUNM add constraint RSC_TRNUNM_FK foreign key (TRN_CODUNM) references RSC_UNAMAT;
alter table RSC_PERSON add constraint RSC_PERUNA_FK foreign key (PER_CODUNA) references RSC_UNIADM;
alter table RSC_ENVTEM add constraint FK_RSC_ENVT_REFERENCE_RSC_HISE foreign key (HEN_COD) references RSC_HISENV;
alter table RSC_SECCIO add constraint RSC_SECSEC_FK foreign key (SEC_CODSEC) references RSC_SECCIO;
alter table RSC_UNIADM add constraint RSC_UNAESP_FK foreign key (UNA_CODESP) references RSC_ESPTER;
alter table RSC_UNIADM add constraint RSC_UNRADM_FK foreign key (UNR_CODADM) references RSC_ADMREM;
alter table RSC_UNIADM add constraint RSC_UNALOV_FK foreign key (UNA_LOGOV) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNAUNA_FK foreign key (UNA_CODUNA) references RSC_UNIADM;
alter table RSC_UNIADM add constraint RSC_UNATRT_FK foreign key (UNA_CODTRT) references RSC_TRATAM;
alter table RSC_UNIADM add constraint RSC_UNALOS_FK foreign key (UNA_LOGOS) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNALOT_FK foreign key (UNA_LOGOT) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNALOH_FK foreign key (UNA_LOGOH) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNAFOP_FK foreign key (UNA_FOTOP) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNAFOG_FK foreign key (UNA_FOTOG) references RSC_ARCHIV;
alter table RSC_TRAMIT add constraint RSC_TRAPRO_FK foreign key (TRA_CODPRO) references RSC_PROCED;
alter table RSC_HISTOR add constraint RSC_HISUNA_FK foreign key (HIS_CODUNA) references RSC_UNIADM;
alter table RSC_HISTOR add constraint RSC_HISNOR_FK foreign key (HIS_CODNOR) references RSC_NORMAT;
alter table RSC_HISTOR add constraint RSC_HISPRO_FK foreign key (HIS_CODPRO) references RSC_PROCED;
alter table RSC_HISTOR add constraint RSC_HISFIC_FK foreign key (HIS_CODFIC) references RSC_FICHA;
alter table RSC_HISTOR add constraint RSC_HISMAT_FK foreign key (HIS_CODMAT) references RSC_MATERI;
alter table RSC_FICHEV add constraint RSC_FIHFIC_FK foreign key (FIH_CODFIC) references RSC_FICHA;
alter table RSC_FICHEV add constraint RSC_FIHHEV_FK foreign key (FIH_CODHEV) references RSC_HECVIT;
alter table RSC_ICMATE add constraint RSC_ICMPEC_FK foreign key (ICM_CODPEC) references RSC_PERCIU;
alter table RSC_ICMATE add constraint RSC_ICMICO_FK foreign key (ICM_ICONO) references RSC_ARCHIV;
alter table RSC_ICMATE add constraint RSC_ICMMAT_FK foreign key (ICM_CODMAT) references RSC_MATERI;
alter table RSC_PROCED add constraint RSC_PROUNA_FK foreign key (PRO_CODUNA) references RSC_UNIADM;
alter table RSC_PROCED add constraint RSC_PROFAM_FK foreign key (PRO_CODFAM) references RSC_FAMILI;
alter table RSC_PROCED add constraint RSC_PROINI_FK foreign key (PRO_CODINI) references RSC_INICI;
alter table RSC_PROCED add constraint RSC_PRRADM_FK foreign key (PRR_CODADM) references RSC_ADMREM;
alter table RSC_SCRENV add constraint RSC_STPSENV_FK foreign key (SEN_STPCOD) references RSC_SCRTIP;
alter table RSC_UNAEDI add constraint RSC_UNEUNA_FK foreign key (UNE_CODUNA) references RSC_UNIADM;
alter table RSC_UNAEDI add constraint RSC_UNEEDI_FK foreign key (UNE_CODEDI) references RSC_EDIFIC;
alter table RSC_SCRIPC add constraint FK_RSC_SCRI_REF_RSC_GRG foreign key (SCR_ESTUDIOS) references RSC_GRPGEN;
alter table RSC_SCRIPC add constraint RSC_STPSCR_FK foreign key (SCR_STPCOD) references RSC_SCRTIP;
alter table RSC_SCRIPC add constraint FK_RSC_SCRI_REFERENCE_RSC_GRG foreign key (SCR_PROFESION) references RSC_GRPGEN;
alter table RSC_SCRIPC add constraint RSC_SGRSCR_FK foreign key (SCR_SGRCOD) references RSC_SCRGRP;
alter table RSC_AFECTA add constraint RSC_AFENOR_FK foreign key (AFE_CODNOR) references RSC_NORMAT;
alter table RSC_AFECTA add constraint RSC_AFENOA_FK foreign key (AFE_CODNOA) references RSC_NORMAT;
alter table RSC_AFECTA add constraint RSC_AFETIA_FK foreign key (AFE_CODTIA) references RSC_TIPAFE;
alter table RSC_ESPTER add constraint RSC_ESPLOG_FK foreign key (ESP_LOGO) references RSC_ARCHIV;
alter table RSC_ESPTER add constraint RSC_ESPMAP_FK foreign key (ESP_MAPA) references RSC_ARCHIV;
alter table RSC_ESPTER add constraint RSC_ESPESP_FK foreign key (ESP_CODESP) references RSC_ESPTER;
alter table RSC_TRAFAM add constraint RSC_TFAFAM_FK foreign key (TFA_CODFAM) references RSC_FAMILI;
create sequence RSC_SEQ_COM;
create sequence RSC_SEQ_ALL;
create sequence RSC_SEQSCR;
create sequence RSC_SEQSEN;
create sequence RSC_SEQSCK;
create sequence RSC_SEQSGR;
create sequence RSC_SEQGRP;
create sequence RSC_SEQHIS;
