alter table RSC_TRAENL drop constraint RSC_TENENL_FK;
alter table RSC_HEVIAG drop constraint RSC_HVAAGH_FK;
alter table RSC_HEVIAG drop constraint RSC_HVAHEV_FK;
alter table RSC_DOCNOR drop constraint DNO_CODNOR_FK;
alter table RSC_TRAEDI drop constraint RSC_TEDEDI_FK;
alter table RSC_AFECTA drop constraint RSC_AFENOR_FK;
alter table RSC_AFECTA drop constraint RSC_AFETIA_FK;
alter table RSC_AFECTA drop constraint RSC_AFENOA_FK;
alter table RSC_FORMUL drop constraint RSC_FORARC_FK;
alter table RSC_FORMUL drop constraint RSC_FORTRA_FK;
alter table RSC_FORMUL drop constraint RSC_FORMAN_FK;
alter table RSC_UNAEDI drop constraint RSC_UNEEDI_FK;
alter table RSC_UNAEDI drop constraint RSC_UNEUNA_FK;
alter table RSC_SIAPDT drop constraint RSC_SIP_SIAUA_FK;
alter table RSC_ICOFAM drop constraint RSC_ICFICO_FK;
alter table RSC_ICOFAM drop constraint RSC_ICFPEC_FK;
alter table RSC_ICOFAM drop constraint RSC_ICFFAM_FK;
alter table RSC_SCRKEY drop constraint RSC_STPSCK_FK;
alter table RSC_AGRMAT drop constraint RSC_AGMSEC_FK;
alter table RSC_SIAUA drop constraint RSC_SUAUNA_FK;
alter table RSC_NORMAT drop constraint RSC_NORBOL_FK;
alter table RSC_NORMAT drop constraint RSC_NORTIP_FK;
alter table RSC_NORMAT drop constraint RSC_NERADM_FK;
alter table RSC_TAXA drop constraint RSC_TAXTRA_FK;
alter table RSC_SERNOR drop constraint RSC_SRNSER_FK;
alter table RSC_SERNOR drop constraint RSC_SRNNOR_FK;
alter table RSC_TRAPEG drop constraint RSC_TPGPEG_FK;
alter table RSC_TRASEC drop constraint RSC_TSESEC_FK;
alter table RSC_TRADOCTRA drop constraint RSC_TDOARC_FK;
alter table RSC_TRADOCTRA drop constraint RSC_TDODOCTRA_FK;
alter table RSC_TRAPEC drop constraint RSC_TPEPEC_FK;
alter table RSC_TRAPOB drop constraint RSC_TRPPOB_FK;
alter table RSC_UNIADM drop constraint RSC_UNAFOG_FK;
alter table RSC_UNIADM drop constraint RSC_UNAUNA_FK;
alter table RSC_UNIADM drop constraint RSC_UNALOV_FK;
alter table RSC_UNIADM drop constraint RSC_UNATRT_FK;
alter table RSC_UNIADM drop constraint RSC_UNALOH_FK;
alter table RSC_UNIADM drop constraint RSC_UNALOS_FK;
alter table RSC_UNIADM drop constraint RSC_UNALOT_FK;
alter table RSC_UNIADM drop constraint RSC_UNRADM_FK;
alter table RSC_UNIADM drop constraint RSC_UNAFOP_FK;
alter table RSC_UNIADM drop constraint RSC_UNAESP_FK;
alter table RSC_MATERI drop constraint RSC_MATICG_FK;
alter table RSC_MATERI drop constraint RSC_MATICO_FK;
alter table RSC_MATERI drop constraint RSC_MATFOT_FK;
alter table RSC_PROMAT drop constraint RSC_PRMMAT_FK;
alter table RSC_PROMAT drop constraint RSC_PRMPRO_FK;
alter table RSC_ADMREM drop constraint RSC_ADMESP_FK;
alter table RSC_ADMREM drop constraint RSC_ADMLOP_FK;
alter table RSC_ADMREM drop constraint RSC_ADMLOG_FK;
alter table RSC_FICHEV drop constraint RSC_FIHFIC_FKE5B59C45;
alter table RSC_FICHEV drop constraint RSC_FIHFIC_FK;
alter table RSC_FICHEV drop constraint RSC_FIHHEV_FK;
alter table RSC_HISTOR drop constraint RSC_HISMAT_FK;
alter table RSC_HISTOR drop constraint RSC_HISSER_FK;
alter table RSC_HISTOR drop constraint RSC_HISPRO_FK;
alter table RSC_HISTOR drop constraint RSC_HISNOR_FK;
alter table RSC_HISTOR drop constraint RSC_HISUNA_FK;
alter table RSC_HISTOR drop constraint RSC_HISFIC_FK;
alter table RSC_SCRGRP drop constraint RSC_STPSGR_FK;
alter table RSC_TRASER drop constraint RSC_TRASER_SER_FK;
alter table RSC_TRAINI drop constraint RSC_TRAINI_FK;
alter table RSC_TRADSR drop constraint RSC_TRADSR_ARCHIV_FK;
alter table RSC_TRADSR drop constraint RSC_TRADSR_DOCSER_FK;
alter table RSC_TRAAGM drop constraint RSC_TAMAGM_FK;
alter table RSC_GRPGEN drop constraint FK_RSC_GRPG_REFERENCE_RSC_SCRT;
alter table RSC_POBPRO drop constraint RSC_PPRPRO_FK;
alter table RSC_POBPRO drop constraint RSC_PPRPOB_FK;
alter table RSC_TRAAGH drop constraint RSC_TRGAGH_FK;
alter table RSC_SCRGID drop constraint RSC_SGRSGI_FK;
alter table RSC_SERMAT drop constraint RSC_SRMSER_FK;
alter table RSC_SERMAT drop constraint RSC_SRMMAT_FK;
alter table RSC_PROCED drop constraint RSC_PRO_CODSIL_FK;
alter table RSC_PROCED drop constraint RSC_PRO_CODUNA_RESOL_FK;
alter table RSC_PROCED drop constraint RSC_PRRADM_FK;
alter table RSC_PROCED drop constraint RSC_PROINI_FK;
alter table RSC_PROCED drop constraint RSC_PRO_CODUNA_SERV_FK;
alter table RSC_PROCED drop constraint RSC_PROFAM_FK;
alter table RSC_PROCED drop constraint RSC_PROUNA_FK;
alter table RSC_GRPGID drop constraint FK_RSC_GRPG_REFERENCE_RSC_GRPG;
alter table RSC_FICHA drop constraint RSC_FICICO_FK;
alter table RSC_FICHA drop constraint RSC_FICBAN_FK;
alter table RSC_FICHA drop constraint RSC_FICIMA_FK;
alter table RSC_FICHA drop constraint RSC_FIRADM_FK;
alter table RSC_HEVIPR drop constraint RSC_HVPHEV_FK;
alter table RSC_HEVIPR drop constraint RSC_HVPPRO_FK;
alter table RSC_ENVTEM drop constraint FK_RSC_ENVT_REFERENCE_RSC_HISE;
alter table RSC_TRAUNA drop constraint RSC_TUNUNA_FK;
alter table RSC_SECCIO drop constraint RSC_SECSEC_FK;
alter table RSC_UNANOR drop constraint RSC_UNNUNA_FK;
alter table RSC_UNANOR drop constraint RSC_UNNNOR_FK;
alter table RSC_TRAEXD drop constraint RSC_TEEXD_FK;
alter table RSC_ENLACE drop constraint RSC_ENLPRO_FK;
alter table RSC_ENLACE drop constraint RSC_ENLFIC_FK;
alter table RSC_ESPTER drop constraint RSC_ESPLOG_FK;
alter table RSC_ESPTER drop constraint RSC_ESPESP_FK;
alter table RSC_ESPTER drop constraint RSC_ESPMAP_FK;
alter table RSC_EDIFIC drop constraint RSC_EDIPLA_FK;
alter table RSC_EDIFIC drop constraint RSC_EDRADM_FK;
alter table RSC_EDIFIC drop constraint RSC_EDIFOG_FK;
alter table RSC_EDIFIC drop constraint RSC_EDIFOP_FK;
alter table RSC_POBSER drop constraint RSC_PSRSER_FK;
alter table RSC_POBSER drop constraint RSC_PSRPOB_FK;
alter table RSC_TRAUNM drop constraint RSC_TRNUNM_FK;
alter table RSC_TRAFAM drop constraint RSC_TFAFAM_FK;
alter table RSC_AGHEVI drop constraint RSC_AGHICG_FK;
alter table RSC_AGHEVI drop constraint RSC_AGHPOB_FK;
alter table RSC_AGHEVI drop constraint RSC_AGHICO_FK;
alter table RSC_AGHEVI drop constraint RSC_AGHFOT_FK;
alter table RSC_TRAFIC drop constraint RSC_FICICO_FK;
alter table RSC_TRAFIC drop constraint RSC_FICBAN_FK;
alter table RSC_TRAFIC drop constraint RSC_FICIMA_FK;
alter table RSC_TRAFIC drop constraint RSC_TFIFIC_FKE5B59C45;
alter table RSC_TRAFIC drop constraint RSC_TFIFIC_FK;
alter table RSC_TRATAX drop constraint RSC_TRATAX_FK;
alter table RSC_COMENT drop constraint RSC_COMPRO_FK;
alter table RSC_COMENT drop constraint RSC_COMFIC_FK;
alter table RSC_COMENT drop constraint RSC_COMUSU_FK;
alter table RSC_SCRTEM drop constraint FK_RSC_SCRT_REFERENCE_RSC_SCRI;
alter table RSC_AUDITO drop constraint RSC_AUDHIS_FK;
alter table RSC_FICHUA drop constraint RSC_FUAFIC_FK825EC5FC;
alter table RSC_FICHUA drop constraint RSC_FUAUNA_FK;
alter table RSC_FICHUA drop constraint RSC_FUASEC_FK;
alter table RSC_FICHUA drop constraint RSC_FUAFIC_FK;
alter table RSC_TRAMAT drop constraint RSC_TMAMAT_FK;
alter table RSC_TRAMAT drop constraint RSC_TMACON_FK;
alter table RSC_TRAMAT drop constraint RSC_TMADIS_FK;
alter table RSC_TRAMAT drop constraint RSC_TMANOR_FK;
alter table RSC_SCRENV drop constraint RSC_STPSENV_FK;
alter table RSC_TRASIL drop constraint RSC_TSISIL_FK;
alter table RSC_TRADNR drop constraint RSC_TRADNR_DOCNOR_FK;
alter table RSC_TRADNR drop constraint RSC_TRADNR_ARCHIV_FK;
alter table RSC_USUPEG drop constraint RSC_USPPGS_FK;
alter table RSC_USUPEG drop constraint RSC_USPUSU_FK;
alter table RSC_UNAMAT drop constraint RSC_UNMUNA_FK;
alter table RSC_UNAMAT drop constraint RSC_UNMMAT_FK;
alter table RSC_ICMATE drop constraint RSC_ICMPEC_FK;
alter table RSC_ICMATE drop constraint RSC_ICMICO_FK;
alter table RSC_ICMATE drop constraint RSC_ICMMAT_FK;
alter table RSC_TRAESP drop constraint RSC_TESESP_FK;
alter table RSC_TRAMIT drop constraint RSC_TRAPRO_FK;
alter table RSC_TRAMIT drop constraint RSC_TRRADM_FK;
alter table RSC_TRAMIT drop constraint RSC_ORGCOMP_FK;
alter table RSC_SERVIC drop constraint RSC_SET_SERRSP_FK;
alter table RSC_SERVIC drop constraint RSC_SER_INSTRU_FK;
alter table RSC_HISENV drop constraint FK_RSC_HISE_REFERENCE_RSC_SCRT;
alter table RSC_TRADOC drop constraint RSC_TDODOC_FK;
alter table RSC_TRADOC drop constraint RSC_TDOARC_FKE61D7DD2;
alter table RSC_TRADOC drop constraint RSC_TDODOC_FKA31073AA;
alter table RSC_TRADOC drop constraint RSC_TDOARC_FK;
alter table RSC_POBFIC drop constraint RSC_PFCFIC_FKE5B59C45;
alter table RSC_POBFIC drop constraint RSC_PFCFIC_FK;
alter table RSC_POBFIC drop constraint RSC_PFCPOB_FK;
alter table RSC_TRAPRO drop constraint RSC_TPRPRO_FK;
alter table RSC_UNAUSU drop constraint RSC_UNUUNA_FK;
alter table RSC_UNAUSU drop constraint RSC_UNUUSU_FK;
alter table RSC_SCRIPC drop constraint RSC_STPSCR_FK;
alter table RSC_SCRIPC drop constraint FK_RSC_SCRI_REF_RSC_GRG;
alter table RSC_SCRIPC drop constraint FK_RSC_SCRI_REFERENCE_RSC_GRG;
alter table RSC_SCRIPC drop constraint RSC_SGRSCR_FK;
alter table RSC_TRATIP drop constraint RSC_TTITIP_FK;
alter table RSC_CATDOC drop constraint RSC_CDCEXD_FK;
alter table RSC_TRACDC drop constraint RSC_TCDCDC_FK;
alter table RSC_PEGSEC drop constraint RSC_PGSPEG_FK;
alter table RSC_PEGSEC drop constraint RSC_PGSSEC_FK;
alter table RSC_ESTADI drop constraint RSC_ESTHIS_FK;
alter table RSC_TRATIA drop constraint RSC_TTATIA_FK;
alter table RSC_MATAGR drop constraint RSC_MAGMAT_FK;
alter table RSC_MATAGR drop constraint RSC_MAGAGM_FK;
alter table RSC_TRATRA drop constraint RSC_TTRTRA_FK;
alter table RSC_PERSON drop constraint RSC_PERUNA_FK;
alter table RSC_TRAHEV drop constraint RSC_THEHEV_FK;
alter table RSC_TRAHEV drop constraint RSC_THENOR_FK;
alter table RSC_TRAHEV drop constraint RSC_THECON_FK;
alter table RSC_TRAHEV drop constraint RSC_THEDIS_FK;
alter table RSC_DOCTRA drop constraint RSC_DOCARC_FK;
alter table RSC_DOCTRA drop constraint RSC_CODITRA_FK;
alter table RSC_DOCTRA drop constraint RSC_DOCTRA_CATDOC_FK;
alter table RSC_DOCTRA drop constraint RSC_DOCTRA_EXCDOC_FK;
alter table RSC_DOCUME drop constraint RSC_DOCARC2_FK;
alter table RSC_DOCUME drop constraint RSC_DOCARC2_FKE61D7DD2;
alter table RSC_DOCUME drop constraint RSC_DOCPRO_FK;
alter table RSC_DOCUME drop constraint RSC_DOCFIC_FK;
alter table RSC_DOCUME drop constraint RSC_DORADM_FK;
alter table RSC_DOCSER drop constraint DSR_CODSER_FK;
alter table RSC_SENSGR drop constraint FKB4A83B7F8E2C8202;
alter table RSC_SENSGR drop constraint FKB4A83B7F8E2C81C0;
alter table RSC_PRONOR drop constraint RSC_PRNNOR_FK;
alter table RSC_PRONOR drop constraint RSC_PRNPRO_FK;
alter table RSC_FICMAT drop constraint RSC_FIMFIC_FKE5B59C45;
alter table RSC_FICMAT drop constraint RSC_FIMMAT_FK;
alter table RSC_FICMAT drop constraint RSC_FIMFIC_FK;
alter table RSC_TRATRT drop constraint RSC_TTTTRT_FK;
alter table RSC_HEVISR drop constraint RSC_HVSHEV_FK;
alter table RSC_HEVISR drop constraint RSC_HVSSER_FK;
alter table RSC_TRANOR drop constraint RSC_TNONOR_FK;
alter table RSC_HECVIT drop constraint RSC_HEVICO_FK;
alter table RSC_HECVIT drop constraint RSC_HEVFOT_FK;
alter table RSC_HECVIT drop constraint RSC_HEVICG_FK;
drop table RSC_SCRTIP;
drop table RSC_TRAENL;
drop table RSC_INICI;
drop table RSC_HEVIAG;
drop table RSC_DOCNOR;
drop table RSC_TRAEDI;
drop table RSC_AFECTA;
drop table RSC_FORMUL;
drop table RSC_UNAEDI;
drop table RSC_SIAPDT;
drop table RSC_ICOFAM;
drop table RSC_SCRKEY;
drop table RSC_PUBOBJ;
drop table RSC_AGRMAT;
drop table RSC_SIAUA;
drop table RSC_NORMAT;
drop table RSC_TAXA;
drop table RSC_SERNOR;
drop table RSC_TRAPEG;
drop table RSC_TRASEC;
drop table RSC_TRADOCTRA;
drop table RSC_TRAPEC;
drop table RSC_TRAPOB;
drop table RSC_UNIADM;
drop table RSC_EXCDOC;
drop table RSC_MATERI;
drop table RSC_PROMAT;
drop table RSC_SIAJOB;
drop table RSC_ADMREM;
drop table RSC_FICHEV;
drop table RSC_HISTOR;
drop table RSC_SCRGRP;
drop table RSC_TRASER;
drop table RSC_TRAINI;
drop table RSC_TRADSR;
drop table RSC_TRAAGM;
drop table RSC_PERCIU;
drop table RSC_USUARI;
drop table RSC_GRPGEN;
drop table RSC_POBPRO;
drop table RSC_FAMILI;
drop table RSC_SOLJOB;
drop table RSC_TRAAGH;
drop table RSC_TRATAM;
drop table RSC_SCRGID;
drop table RSC_SERMAT;
drop table RSC_PROCED;
drop table RSC_GRPGID;
drop table RSC_FICHA;
drop table RSC_HEVIPR;
drop table RSC_ENVTEM;
drop table RSC_TRAUNA;
drop table RSC_SECCIO;
drop table RSC_UNANOR;
drop table RSC_TRAEXD;
drop table RSC_ENLACE;
drop table RSC_ESPTER;
drop table RSC_EDIFIC;
drop table RSC_POBSER;
drop table RSC_TRAUNM;
drop table RSC_TRAFAM;
drop table RSC_AGHEVI;
drop table RSC_TRAFIC;
drop table RSC_TRATAX;
drop table RSC_COMENT;
drop table RSC_SCRTEM;
drop table RSC_AUDITO;
drop table RSC_FICHUA;
drop table RSC_TIPAFE;
drop table RSC_TRAMAT;
drop table RSC_SCRENV;
drop table RSC_SILADM;
drop table RSC_TIPO;
drop table RSC_TRASIL;
drop table RSC_TRADNR;
drop table RSC_USUPEG;
drop table RSC_UNAMAT;
drop table RSC_ICMATE;
drop table RSC_TRAESP;
drop table RSC_TRAMIT;
drop table RSC_SERVIC;
drop table RSC_PERGES;
drop table RSC_HISENV;
drop table RSC_TRADOC;
drop table RSC_IDIOMA;
drop table RSC_POBFIC;
drop table RSC_TRAPRO;
drop table RSC_UNAUSU;
drop table RSC_DESTIN;
drop table RSC_SCRIPC;
drop table RSC_TRATIP;
drop table RSC_CATDOC;
drop table RSC_TRACDC;
drop table RSC_BOLETI;
drop table RSC_PEGSEC;
drop table RSC_ESTADI;
drop table RSC_TRATIA;
drop table RSC_MATAGR;
drop table RSC_TRATRA;
drop table RSC_PERSON;
drop table RSC_TRAHEV;
drop table RSC_DOCTRA;
drop table RSC_DOCUME;
drop table RSC_DOCSER;
drop table RSC_SENSGR;
drop table RSC_PRONOR;
drop table RSC_FICMAT;
drop table RSC_TRATRT;
drop table RSC_ARCHIV;
drop table RSC_HEVISR;
drop table RSC_SOLRPD;
drop table RSC_TRANOR;
drop table RSC_HECVIT;
drop sequence RSC_SEQSEN;
drop sequence RSC_SEQHIS;
drop sequence RSC_SEQ_ALL;
drop sequence RSC_SEQSCK;
drop sequence RSC_SEQ_COM;
drop sequence RSC_SEQGRP;
drop sequence RSC_SEQSGR;
drop sequence RSC_SEQSCR;
create table RSC_SCRTIP (
   STP_COD int8 not null,
   STP_CODUNI int8,
   STP_IDENT varchar(10),
   STP_URLCAB varchar(200),
   STP_TITULO varchar(50),
   STP_NOM varchar(200),
   STP_MAIL varchar(50),
   STP_PWD varchar(50),
   STP_ESTADO int4,
   STP_HORAJOB timestamp,
   primary key (STP_COD)
);
create table RSC_TRAENL (
   TEN_CODENL int8 not null,
   TEN_TITULO varchar(256),
   TEN_ENLACE varchar(512),
   TEN_CODIDI varchar(2) not null,
   primary key (TEN_CODENL, TEN_CODIDI)
);
create table RSC_INICI (
   INI_CODI int8 not null,
   INI_CODEST varchar(256),
   primary key (INI_CODI)
);
create table RSC_HEVIAG (
   HVA_CODI int8 not null,
   HVA_CODHEV int8,
   HVA_CODAGH int8,
   HVA_ORDEN int4,
   primary key (HVA_CODI)
);
create table RSC_DOCNOR (
   DNO_CODI int8 not null,
   DNO_CODNOR int8,
   ORDEN int8,
   primary key (DNO_CODI)
);
create table RSC_TRAEDI (
   TED_CODEDI int8 not null,
   TED_DESCRI varchar(4000),
   TED_CODIDI varchar(2) not null,
   primary key (TED_CODEDI, TED_CODIDI)
);
create table RSC_AFECTA (
   AFE_CODNOR int8 not null,
   AFE_CODNOA int8,
   AFE_CODTIA int8
);
create table RSC_FORMUL (
   FOR_CODI int8 not null,
   FOR_NOMBRE varchar(256),
   FOR_URL varchar(512),
   FOR_CODARC int8,
   FOR_URLMAN varchar(512),
   FOR_MANUAL int8,
   FOR_CODTRA int8,
   primary key (FOR_CODI)
);
create table RSC_UNAEDI (
   UNE_CODEDI int8 not null,
   UNE_CODUNA int8 not null,
   primary key (UNE_CODEDI, UNE_CODUNA)
);
create table RSC_SIAPDT (
   SIP_ID int8 not null,
   SIP_TIPO varchar(255),
   SIP_IDELEM int8,
   SIP_ESTADO int4,
   SIP_FECALT timestamp,
   SIP_FECIDX timestamp,
   SIP_MENSA varchar(255),
   SIP_EXISTE int4,
   SIP_SIA int8,
   SIP_SIAUA int8,
   primary key (SIP_ID)
);
create table RSC_ICOFAM (
   ICF_CODI int8 not null,
   ICF_CODFAM int8,
   ICF_CODPEC int8,
   ICF_ICONO int8,
   primary key (ICF_CODI)
);
create table RSC_SCRKEY (
   SCK_EMAIL varchar(255) not null,
   SCK_IDIMG varchar(255),
   SCK_IDACT varchar(255),
   SCK_NUMENV int2,
   SCK_FCENV timestamp,
   SCK_STPCOD int8 not null,
   SCK_COD int8 not null,
   primary key (SCK_COD)
);
create table RSC_PUBOBJ (
   POB_CODI int8 not null,
   POB_CODEST varchar(128),
   POB_ORDEN int4,
   primary key (POB_CODI)
);
create table RSC_AGRMAT (
   AGM_CODI int8 not null,
   AGM_CODEST varchar(256),
   AGM_CODSEC int8,
   primary key (AGM_CODI)
);
create table RSC_SIAUA (
   SUA_CODI int8 not null,
   SUA_CODUNA int8,
   SUA_USUARI varchar(255),
   SUA_CONTRA varchar(255),
   primary key (SUA_CODI)
);
create table RSC_NORMAT (
   NOR_CODI int8 not null,
   NOR_TYPEN varchar(64) not null,
   NOR_NUMERO int8,
   NOR_REGIST int8,
   NOR_LEY varchar(256),
   NOR_FECHA timestamp,
   NOR_FECBOL timestamp,
   NOR_VALIDN int4,
   NOR_CODIVUDS varchar(255),
   NOR_DESCCODIVUDS varchar(255),
   NOR_NUMNOR varchar(255),
   NOR_CODBOL int8,
   NOR_CODTIP int8,
   NER_IDEXTE int8,
   NER_URLREM varchar(512),
   NER_CODADM int8,
   primary key (NOR_CODI)
);
create table RSC_TAXA (
   TAX_CODI int8 not null,
   TAX_CODTRA int8,
   primary key (TAX_CODI)
);
create table RSC_SERNOR (
   SRN_CODNOR int8 not null,
   SRN_CODSER int8 not null,
   primary key (SRN_CODSER, SRN_CODNOR)
);
create table RSC_TRAPEG (
   TPG_CODPEG int8 not null,
   TPG_NOMBRE varchar(256),
   TPG_DESCRI varchar(4000),
   TPG_CODIDI varchar(2) not null,
   primary key (TPG_CODPEG, TPG_CODIDI)
);
create table RSC_TRASEC (
   TSE_CODSEC int8 not null,
   TSE_TITULO varchar(256),
   TSE_DESCRI varchar(4000),
   TSE_CODIDI varchar(2) not null,
   primary key (TSE_CODSEC, TSE_CODIDI)
);
create table RSC_TRADOCTRA (
   TDO_CODTRA int8 not null,
   TDO_TITULO varchar(256),
   TDO_DESCRI varchar(4000),
   TDO_CODARC int8,
   TDO_CODIDI varchar(2) not null,
   primary key (TDO_CODTRA, TDO_CODIDI)
);
create table RSC_TRAPEC (
   TPE_CODPEC int8 not null,
   TPE_NOMBRE varchar(256),
   TPE_DESCRI varchar(4000),
   TPE_CODIDI varchar(2) not null,
   primary key (TPE_CODPEC, TPE_CODIDI)
);
create table RSC_TRAPOB (
   TRP_CODPOB int8 not null,
   TRP_TITULO varchar(256),
   TRP_DESCRI varchar(4000),
   TRP_PALCLA varchar(4000),
   TRP_CODIDI varchar(2) not null,
   primary key (TRP_CODPOB, TRP_CODIDI)
);
create table RSC_UNIADM (
   UNA_CODI int8 not null,
   UNA_TYPE varchar(64) not null,
   UNA_BUSKEY varchar(41),
   UNA_CLVHIT varchar(128),
   UNA_DOMINI varchar(256),
   UNA_ORDEN int8,
   UNA_VALIDA int4,
   UNA_RESPON varchar(256),
   UNA_EMAILR varchar(256),
   UNA_TELEFO varchar(64),
   UNA_FAX varchar(64),
   UNA_EMAIL varchar(256),
   UNA_SEXRES int4,
   UNA_CODEST varchar(256),
   UNA_NFOTO1 int4,
   UNA_NFOTO2 int4,
   UNA_NFOTO3 int4,
   UNA_NFOTO4 int4,
   UNA_CODDR3 varchar(12),
   UNA_FOTOP int8,
   UNA_FOTOG int8,
   UNA_LOGOH int8,
   UNA_LOGOV int8,
   UNA_LOGOS int8,
   UNA_LOGOT int8,
   UNA_CODUNA int8,
   UNA_CODESP int8,
   UNA_CODTRT int8,
   UNR_IDEXTE int8,
   UNR_URLREM varchar(512),
   UNR_CODADM int8,
   primary key (UNA_CODI)
);
create table RSC_EXCDOC (
   EXD_CODI int8 not null,
   primary key (EXD_CODI)
);
create table RSC_MATERI (
   MAT_CODI int8 not null,
   MAT_CODHIT varchar(128),
   MAT_CODEST varchar(256),
   MAT_DESTAC bool not null,
   MAT_CODSIA int8,
   MAT_ICONO int8,
   MAT_FOTO int8,
   MAT_ICOGRA int8,
   primary key (MAT_CODI)
);
create table RSC_PROMAT (
   PRM_CODMAT int8 not null,
   PRM_CODPRO int8 not null,
   primary key (PRM_CODPRO, PRM_CODMAT)
);
create table RSC_SIAJOB (
   SIJ_ID int8 not null,
   SIJ_FECINI timestamp,
   SIJ_FECFIN timestamp,
   SIJ_DESBRE text,
   SIJ_DESCRI text,
   SIJ_ESTADO int4,
   SIJ_TIPO varchar(255),
   primary key (SIJ_ID)
);
create table RSC_ADMREM (
   ADM_CODI int8 not null,
   ADM_ENDPOI varchar(512),
   ADM_NOMBRE varchar(512),
   ADM_NIVPRO int4,
   ADM_CODSUA varchar(512),
   ADM_IDREMO varchar(512) not null unique,
   ADM_RESPON varchar(256),
   ADM_VERSIO int8,
   ADM_CODESP int8,
   ADM_LOGOP int8,
   ADM_LOGOG int8,
   primary key (ADM_CODI)
);
create table RSC_FICHEV (
   FIH_CODFIC int8 not null,
   FIH_CODHEV int8 not null,
   primary key (FIH_CODFIC, FIH_CODHEV)
);
create table RSC_HISTOR (
   HIS_CODI int8 not null,
   HIS_TYPE varchar(64) not null,
   HIS_NOMBRE varchar(512),
   HIS_CODUNA int8,
   HIS_CODNOR int8,
   HIS_CODPRO int8,
   HIS_CODSER int8,
   HIS_CODFIC int8,
   HIS_CODMAT int8,
   primary key (HIS_CODI)
);
create table RSC_SCRGRP (
   SGR_COD int8 not null,
   SGR_IDENT varchar(255),
   SGR_STPCOD int8 not null,
   primary key (SGR_COD)
);
create table RSC_TRASER (
   TSR_CODSER int8 not null,
   TSR_NOMBRE varchar(256),
   TSR_OBJETO varchar(256),
   TSR_DESTIN text,
   TSR_REQUIS text,
   TSR_OBSERV text,
   TSR_CODIDI varchar(2) not null,
   primary key (TSR_CODSER, TSR_CODIDI)
);
create table RSC_TRAINI (
   TIN_CODINI int8 not null,
   TIN_NOMBRE varchar(256),
   TIN_DESCRI varchar(4000),
   TIN_CODIDI varchar(2) not null,
   primary key (TIN_CODINI, TIN_CODIDI)
);
create table RSC_TRADSR (
   TDS_CODDSR int8 not null,
   TDS_TITULO varchar(256),
   TDS_DESCRI varchar(4000),
   TDS_CODARC int8,
   TDS_CODIDI varchar(2) not null,
   primary key (TDS_CODDSR, TDS_CODIDI)
);
create table RSC_TRAAGM (
   TAM_CODAGM int8 not null,
   TAM_NOMBRE varchar(256),
   TAM_CODIDI varchar(2) not null,
   primary key (TAM_CODAGM, TAM_CODIDI)
);
create table RSC_PERCIU (
   PEC_CODI int8 not null,
   PEC_CODEST varchar(10),
   PEC_PATICO varchar(256),
   primary key (PEC_CODI)
);
create table RSC_USUARI (
   USU_CODI int8 not null,
   USU_USERNA varchar(128) not null unique,
   USU_PASSWO varchar(128),
   USU_NOMBRE varchar(256),
   USU_OBSERV varchar(4000),
   USU_PERFIL varchar(64),
   USU_EMAIL varchar(256),
   primary key (USU_CODI)
);
create table RSC_GRPGEN (
   GRP_COD int8 not null,
   GRP_TIPO int4,
   GRP_STPCOD int8 not null,
   primary key (GRP_COD)
);
create table RSC_POBPRO (
   PPR_CODPRO int8 not null,
   PPR_CODPOB int8 not null,
   primary key (PPR_CODPRO, PPR_CODPOB)
);
create table RSC_FAMILI (
   FAM_CODI int8 not null,
   primary key (FAM_CODI)
);
create table RSC_SOLJOB (
   JOB_ID int8 not null,
   JOB_FECINI timestamp,
   JOB_FECFIN timestamp,
   JOB_FECFCH timestamp,
   JOB_FECPRO timestamp,
   JOB_FECSER timestamp,
   JOB_FECNOR timestamp,
   JOB_FECTRA timestamp,
   JOB_FECUNA timestamp,
   JOB_TOTFCH float4,
   JOB_TOTDFC float4,
   JOB_TOTPRO float4,
   JOB_TOTDPR float4,
   JOB_TOTSER float4,
   JOB_TOTDSR float4,
   JOB_TOTNOR float4,
   JOB_TOTDNO float4,
   JOB_TOTTRA float4,
   JOB_TOTUNA float4,
   primary key (JOB_ID)
);
create table RSC_TRAAGH (
   TRG_CODAGH int8 not null,
   TRG_NOMBRE varchar(256),
   TRG_DESCRI varchar(4000),
   TRG_PALCLA varchar(4000),
   TRG_CODIDI varchar(2) not null,
   primary key (TRG_CODAGH, TRG_CODIDI)
);
create table RSC_TRATAM (
   TRT_CODI int8 not null,
   TRT_CODEST varchar(128),
   primary key (TRT_CODI)
);
create table RSC_SCRGID (
   SGI_SGRCOD int8 not null,
   SGI_NOM varchar(100),
   SGI_CODIDI varchar(2) not null,
   primary key (SGI_SGRCOD, SGI_CODIDI)
);
create table RSC_SERMAT (
   SRM_CODMAT int8 not null,
   PRM_CODSER int8 not null,
   SRM_CODSER int8 not null,
   primary key (SRM_CODSER, SRM_CODMAT)
);
create table RSC_PROCED (
   PRO_CODI int8 not null,
   PRO_TYPE varchar(64) not null,
   PRO_SIGNAT varchar(256),
   PRO_FECCAD timestamp,
   PRO_FECPUB timestamp,
   PRO_FECACT timestamp,
   PRO_VALIDA int4,
   PRO_TRAMIT varchar(255),
   PRO_VERSIO int8,
   PRO_INFO varchar(4000),
   PRO_URLEXT varchar(1024),
   PRO_ORDCON int8,
   PRO_ORDDIR int8,
   PRO_ORDSER int8,
   PRO_INDICA varchar(1024),
   PRO_VENTANA varchar(1024),
   PRO_RESPON varchar(256),
   PRO_TAXA varchar(1024),
   PRO_CODSIA varchar(12),
   PRO_ESTSIA varchar(1),
   PRO_FECSIA timestamp,
   PRO_CODUNA_RESOL int8,
   PRO_CODUNA int8,
   PRO_CODFAM int8,
   PRO_CODINI int8,
   PRO_CODUNA_SERV int8,
   PRO_CODSIL int8,
   PRR_IDEXTE int8,
   PRR_URLREM varchar(512),
   PRR_CODADM int8,
   primary key (PRO_CODI)
);
create table RSC_GRPGID (
   SGR_GRPCOD int8 not null,
   SGR_NOM varchar(100),
   SGR_CODIDI varchar(2) not null,
   primary key (SGR_GRPCOD, SGR_CODIDI)
);
create table RSC_FICHA (
   FIC_CODI int8 not null,
   FIC_TYPE varchar(64) not null,
   FIC_FECPUB timestamp,
   FIC_FECCAD timestamp,
   FIC_FECACT timestamp,
   FIC_VALIDA int4,
   FIC_INFO varchar(4000),
   FIC_RESPON varchar(256),
   FIC_FORTEM varchar(1),
   FIR_IDEXTE int8,
   FIR_URLREM varchar(512),
   FIR_CODADM int8,
   FIC_ICONO int8,
   FIC_IMAGEN int8,
   FIC_BANER int8,
   FIC_URLVID varchar(255),
   FIC_URLFOR varchar(255),
   primary key (FIC_CODI)
);
create table RSC_HEVIPR (
   HVP_CODI int8 not null,
   HVP_CODHEV int8,
   HVP_CODPRO int8,
   HVP_ORDEN int4,
   primary key (HVP_CODI)
);
create table RSC_ENVTEM (
   HEN_COD int8 not null,
   MAT_CODI int8,
   HEN_HTML text,
   HEN_ACTIVO int4
);
create table RSC_TRAUNA (
   TUN_CODUNA int8 not null,
   TUN_NOMBRE varchar(256),
   TUN_PRESEN varchar(4000),
   TUN_ABREVI varchar(64),
   TUN_URL varchar(256),
   TUN_CVRESP text,
   TUN_CODIDI varchar(2) not null,
   primary key (TUN_CODUNA, TUN_CODIDI)
);
create table RSC_SECCIO (
   SEC_CODI int8 not null,
   SEC_CODEST varchar(20),
   SEC_PERFIL varchar(128),
   SEC_ORDEN int4,
   SEC_CODSEC int8,
   primary key (SEC_CODI)
);
create table RSC_UNANOR (
   UNN_CODI int8 not null,
   UNN_CODNOR int8,
   UNN_CODUNA int8,
   primary key (UNN_CODI)
);
create table RSC_TRAEXD (
   TED_CODEXD int8 not null,
   TED_NOM varchar(128),
   TED_DESCRI varchar(4000),
   TED_CODIDI varchar(2) not null,
   primary key (TED_CODEXD, TED_CODIDI)
);
create table RSC_ENLACE (
   ENL_CODI int8 not null,
   ENL_CODFIC int8,
   ENL_CODPRO int8,
   ENL_ORDEN int8,
   primary key (ENL_CODI)
);
create table RSC_ESPTER (
   ESP_CODI int8 not null,
   ESP_NIVEL int4 not null,
   ESP_COORDE varchar(255),
   ESP_MAPA int8,
   ESP_LOGO int8,
   ESP_CODESP int8,
   primary key (ESP_CODI)
);
create table RSC_EDIFIC (
   EDI_CODI int8 not null,
   EDI_TYPE varchar(64) not null,
   EDI_DIRECC varchar(256),
   EDI_CODPOS varchar(64),
   EDI_POBLAC varchar(64),
   EDI_TELEFO varchar(64),
   EDI_FAX varchar(64),
   EDI_EMAIL varchar(256),
   EDI_FOTOP int8,
   EDI_FOTOG int8,
   EDI_PLANO int8,
   EDI_LAT varchar(15),
   EDI_LNG varchar(15),
   EDR_IDEXTE int8,
   EDR_CODADM int8,
   primary key (EDI_CODI)
);
create table RSC_POBSER (
   PSR_CODSER int8 not null,
   PSR_CODPOB int8 not null,
   primary key (PSR_CODSER, PSR_CODPOB)
);
create table RSC_TRAUNM (
   TRN_CODUNM int8 not null,
   TRN_URLUM varchar(256),
   TRN_CODIDI varchar(2) not null,
   primary key (TRN_CODUNM, TRN_CODIDI)
);
create table RSC_TRAFAM (
   TFA_CODFAM int8 not null,
   TFA_NOMBRE varchar(256),
   TFA_DESCRI varchar(4000),
   TFA_CODIDI varchar(2) not null,
   primary key (TFA_CODFAM, TFA_CODIDI)
);
create table RSC_AGHEVI (
   AGH_CODI int8 not null,
   AGH_CODEST varchar(128),
   AGH_FOTO int8,
   AGH_ICONO int8,
   AGH_ICOGRA int8,
   AGH_CODPOB int8,
   primary key (AGH_CODI)
);
create table RSC_TRAFIC (
   TFI_CODFIC int8 not null,
   TFI_TITULO varchar(256),
   TFI_URL varchar(512),
   TFI_DESABR text,
   TFI_DESCRI text,
   TFI_URLFOR varchar(255),
   TFI_URLVID varchar(255),
   TFI_ICONO int8,
   TFI_IMAGEN int8,
   TFI_BANNER int8,
   TFI_CODIDI varchar(2) not null,
   primary key (TFI_CODFIC, TFI_CODIDI)
);
create table RSC_TRATAX (
   TTAX_CODI int8 not null,
   TAX_ID varchar(256),
   DESCRI varchar(4000),
   FORMPAG varchar(4000),
   COD_IDI varchar(2) not null,
   primary key (TTAX_CODI, COD_IDI)
);
create table RSC_COMENT (
   COM_CODI int8 not null,
   COM_TYPE varchar(64) not null,
   COM_TITULO varchar(256),
   COM_MOTIVO varchar(64) not null,
   COM_FECHA timestamp,
   COM_CONTEN varchar(4000),
   COM_SUBSAN bool,
   COM_AUTOR varchar(256),
   COM_CODUSU int8,
   COM_CODPRO int8,
   COM_CODFIC int8,
   primary key (COM_CODI)
);
create table RSC_SCRTEM (
   SCR_COD int8 not null,
   MAT_CODI int8
);
create table RSC_AUDITO (
   AUD_CODI int8 not null,
   AUD_USUARI varchar(256),
   AUD_FECHA timestamp,
   AUD_CODHIS int8,
   AUD_CODOPE int4,
   primary key (AUD_CODI)
);
create table RSC_FICHUA (
   FUA_CODI int8 not null,
   FUA_CODFIC int8,
   FUA_CODUNA int8,
   FUA_CODSEC int8,
   FUA_ORDEN int4,
   FUA_ORDSEC int4,
   primary key (FUA_CODI)
);
create table RSC_TIPAFE (
   TIA_CODI int8 not null,
   primary key (TIA_CODI)
);
create table RSC_TRAMAT (
   TMA_CODMAT int8 not null,
   TMA_NOMBRE varchar(256),
   TMA_DESCRI varchar(4000),
   TMA_PALCLA varchar(4000),
   TMA_CODDIS int8,
   TMA_NORMAT int8,
   TMA_CONTEN int8,
   TMA_CODIDI varchar(2) not null,
   primary key (TMA_CODMAT, TMA_CODIDI)
);
create table RSC_SCRENV (
   SEN_COD int8 not null,
   SEN_FCENVIO timestamp,
   SEN_CANAL varchar(1),
   SEN_ASUNTO varchar(500),
   SEN_TITULO varchar(100),
   SEN_HTML text,
   SEN_TIPO varchar(1),
   SEN_IDSECC int8,
   SEN_ESTADO varchar(1),
   SEN_FCENV timestamp,
   SEN_FCALTA timestamp,
   SEN_UALTA int8,
   SEN_FCMOD timestamp,
   SEN_UMOD int8,
   SEN_FCBAJA timestamp,
   SEN_STPCOD int8 not null,
   primary key (SEN_COD)
);
create table RSC_SILADM (
   SIL_CODI int8 not null,
   primary key (SIL_CODI)
);
create table RSC_TIPO (
   TIP_CODI int8 not null,
   TIP_CODSIA int8,
   TIP_IDBOIB int8,
   primary key (TIP_CODI)
);
create table RSC_TRASIL (
   TSI_CODSIL int8 not null,
   TSI_NOMBRE varchar(256),
   TSI_DESCRI varchar(4000),
   TSI_CODIDI varchar(2) not null,
   primary key (TSI_CODSIL, TSI_CODIDI)
);
create table RSC_TRADNR (
   TDN_CODDNR int8 not null,
   TDN_TITULO varchar(256),
   TDN_DESCRI varchar(4000),
   TDN_ENLACE varchar(256),
   TDN_CODARC int8,
   TDN_CODIDI varchar(2) not null,
   primary key (TDN_CODDNR, TDN_CODIDI)
);
create table RSC_USUPEG (
   USP_CODPEG int8 not null,
   USP_CODUSU int8 not null,
   primary key (USP_CODUSU, USP_CODPEG)
);
create table RSC_UNAMAT (
   UNM_CODI int8 not null,
   UNM_UNAPRN varchar(1),
   UNM_CODMAT int8,
   UNM_CODUNA int8,
   primary key (UNM_CODI)
);
create table RSC_ICMATE (
   ICM_CODI int8 not null,
   ICM_CODMAT int8,
   ICM_CODPEC int8,
   ICM_ICONO int8,
   primary key (ICM_CODI)
);
create table RSC_TRAESP (
   TES_CODESP int8 not null,
   TES_NOMBRE varchar(256),
   TES_CODIDI varchar(2) not null,
   primary key (TES_CODESP, TES_CODIDI)
);
create table RSC_TRAMIT (
   TRA_CODI int8 not null,
   TRA_TYPE varchar(64) not null,
   TRA_FASE int4,
   TRA_ORDEN int8,
   TRA_CODPRO int8,
   TRA_CODIVUDS varchar(255),
   TRA_DESCCODIVUDS varchar(255),
   TRA_VALIDA int8,
   TRA_DATCADU timestamp,
   TRA_DATPUBL timestamp,
   TRA_DATACTU timestamp,
   TRA_IDTRAMTEL varchar(255),
   TRA_VERSIO int4,
   TRA_URLEXTE varchar(255),
   TRA_DATACTUVUDS varchar(255),
   TRA_DATINICI timestamp,
   TRA_DATTANCAMENT timestamp,
   TRA_ORGCOMP int8,
   TRR_IDEXTE int8,
   TRR_URLREM varchar(512),
   TRR_CODADM int8,
   primary key (TRA_CODI)
);
create table RSC_SERVIC (
   SER_CODI int8 not null,
   SER_VALIDA int4,
   SER_CODIGO varchar(256),
   SER_CODSIA varchar(12),
   SER_ESTSIA varchar(1),
   SER_FECSIA timestamp,
   SER_TASURL varchar(256),
   SER_NOMRSP varchar(256),
   SER_CORREO varchar(256),
   SER_TELEFO varchar(256),
   SER_FECPUB timestamp,
   SER_FECDES timestamp,
   SER_FECACT timestamp,
   SER_TRAULR varchar(256),
   SER_TRAID varchar(256),
   SER_TRAVER varchar(256),
   SER_INSTRU int8,
   SER_SERRSP int8,
   primary key (SER_CODI)
);
create table RSC_PERGES (
   PEG_CODI int8 not null,
   PEG_ORDEN int4,
   PEG_CODEST varchar(255),
   PEG_DUPLIC varchar(255),
   primary key (PEG_CODI)
);
create table RSC_HISENV (
   HEN_COD int8 not null,
   HEN_FENVIO timestamp,
   HEN_STPCOD int8 not null,
   primary key (HEN_COD)
);
create table RSC_TRADOC (
   TDO_CODDOC int8 not null,
   TDO_TITULO varchar(256),
   TDO_DESCRI varchar(4000),
   TDO_CODARC int8,
   TDO_CODIDI varchar(2) not null,
   primary key (TDO_CODDOC, TDO_CODIDI)
);
create table RSC_IDIOMA (
   IDI_CODI varchar(2) not null,
   IDI_ORDEN int4 not null,
   IDI_CODEST varchar(128),
   IDI_NOMBRE varchar(128),
   IDI_TRADUCTOR varchar(128),
   primary key (IDI_CODI)
);
create table RSC_POBFIC (
   PFC_CODFIC int8 not null,
   PFC_CODPOB int8 not null,
   primary key (PFC_CODFIC, PFC_CODPOB)
);
create table RSC_TRAPRO (
   TPR_CODPRO int8 not null,
   TPR_NOMBRE varchar(256),
   TPR_RESOLUCION text,
   TPR_NOTIFICACION text,
   TPR_RESULT text,
   TPR_RESUME text,
   TPR_DESTIN text,
   TPR_REQUIS text,
   TPR_PLAZOS text,
   TPR_RECURS text,
   TPR_OBSERV text,
   TPR_LUGAR text,
   TPR_CODIDI varchar(2) not null,
   primary key (TPR_CODPRO, TPR_CODIDI)
);
create table RSC_UNAUSU (
   UNU_CODUNA int8 not null,
   UNU_CODUSU int8 not null,
   primary key (UNU_CODUSU, UNU_CODUNA)
);
create table RSC_DESTIN (
   DES_CODI int8 not null,
   DES_NOMBRE varchar(512),
   DES_IDREMO varchar(512),
   DES_ENDPOI varchar(512),
   DES_EMAIL varchar(256),
   primary key (DES_CODI)
);
create table RSC_SCRIPC (
   SCR_COD int8 not null,
   SCR_NOM varchar(100),
   SCR_APE1 varchar(100),
   SCR_APE2 varchar(100),
   SCR_EMAIL varchar(100),
   SCR_SMS varchar(100),
   SCR_TELEFONO varchar(100),
   SCR_TIPO varchar(1),
   SCR_ENOM varchar(200),
   SCR_EAREA varchar(200),
   SCR_EDPTO varchar(200),
   SCR_ECARGO varchar(200),
   SCR_IDBOLE varchar(1),
   SCR_IDALER varchar(1),
   SCR_IDESTU varchar(1),
   SCR_ESTADO varchar(1),
   SCR_ORIGEN varchar(1),
   SCR_REFTRA varchar(100),
   SCR_UALTA int8,
   SCR_FCALTA timestamp,
   SCR_FCMOD timestamp,
   SCR_FCBAJA timestamp,
   SCR_SEXO varchar(255),
   SCR_ANYONAC int4,
   SCR_PAISNAC int4,
   SCR_PROVNAC int4,
   SCR_ISLANAC int4,
   SCR_MUNINAC int4,
   SCR_LNACOTROS varchar(255),
   SCR_PAISRESID int4,
   SCR_PROVRESID int4,
   SCR_ISLARESID int4,
   SCR_MUNIRESID int4,
   SCR_IDIOMA varchar(255),
   SCR_CIF varchar(255),
   SCR_SEDESOCIAL varchar(255),
   SCR_RAZONSOCIAL varchar(255),
   SCR_CP varchar(255),
   SCR_CONTACTO varchar(255),
   SCR_GRNOM varchar(255),
   SCR_RESUMENTEMAS varchar(255),
   SCR_MOTIVOBAJA varchar(200),
   SCR_ESTUDIOS int8,
   SCR_PROFESION int8,
   SCR_STPCOD int8 not null,
   SCR_SGRCOD int8,
   primary key (SCR_COD)
);
create table RSC_TRATIP (
   TTI_CODTIP int8 not null,
   TTI_NOMBRE varchar(128),
   TTI_CODIDI varchar(2) not null,
   primary key (TTI_CODTIP, TTI_CODIDI)
);
create table RSC_CATDOC (
   CDC_CODI int8 not null,
   CDC_ORDEN int8,
   CDC_ADMRSP int4,
   CDC_CODEXD int8,
   primary key (CDC_CODI)
);
create table RSC_TRACDC (
   TCD_CODCDC int8 not null,
   TCD_NOM varchar(256),
   TCD_DESCRI varchar(4000),
   TCD_CODIDI varchar(2) not null,
   primary key (TCD_CODCDC, TCD_CODIDI)
);
create table RSC_BOLETI (
   BOL_CODI int8 not null,
   BOL_NOMBRE varchar(256),
   BOL_ENLACE varchar(512),
   primary key (BOL_CODI)
);
create table RSC_PEGSEC (
   PGS_CODPEG int8 not null,
   PGS_CODSEC int8 not null,
   primary key (PGS_CODPEG, PGS_CODSEC)
);
create table RSC_ESTADI (
   EST_CODI int8 not null,
   EST_FECHA timestamp,
   EST_CONTAD int4,
   EST_CODHIS int8,
   primary key (EST_CODI)
);
create table RSC_TRATIA (
   TTA_CODTIA int8 not null,
   TTA_NOMBRE varchar(256),
   TTA_CODIDI varchar(2) not null,
   primary key (TTA_CODTIA, TTA_CODIDI)
);
create table RSC_MATAGR (
   MAG_CODI int8 not null,
   MAG_CODMAT int8,
   MAG_CODAGM int8,
   MAG_ORDEN int4,
   primary key (MAG_CODI)
);
create table RSC_TRATRA (
   TTR_CODTTR int8 not null,
   TTR_NOMBRE varchar(256),
   TTR_DESCRI text,
   TTR_DOCUME text,
   TTR_REQUI text,
   TTR_PLAZOS varchar(512),
   TTR_LUGAR varchar(512),
   TTR_CODIDI varchar(2) not null,
   primary key (TTR_CODTTR, TTR_CODIDI)
);
create table RSC_PERSON (
   PER_CODI int8 not null,
   PER_USERNA varchar(128),
   PER_NOMBRE varchar(256),
   PER_FUNCIO varchar(256),
   PER_CARGO varchar(256),
   PER_EMAIL varchar(256),
   PER_EXTPUB varchar(64),
   PER_NUMPUB varchar(64),
   PER_EXTPRI varchar(64),
   PER_NUMPRI varchar(64),
   PER_EXTMOV varchar(64),
   PER_NUMMOV varchar(64),
   PER_CODUNA int8,
   primary key (PER_CODI)
);
create table RSC_TRAHEV (
   THE_CODHEV int8 not null,
   THE_NOMBRE varchar(256),
   THE_DESCRI varchar(4000),
   THE_PALCLA varchar(4000),
   THE_CODDIS int8,
   THE_NORMAT int8,
   THE_CONTEN int8,
   THE_CODIDI varchar(2) not null,
   primary key (THE_CODHEV, THE_CODIDI)
);
create table RSC_DOCTRA (
   DOC_CODI int8 not null,
   CODITRA int8,
   DOC_CODARC int8,
   DOC_CODCDC int8,
   DOC_CODEXD int8,
   ORDEN int8,
   TIPUS int4,
   primary key (DOC_CODI)
);
create table RSC_DOCUME (
   DOC_CODI int8 not null,
   DOC_TYPE varchar(64) not null,
   DOC_CODFIC int8,
   DOC_CODPRO int8,
   DOC_CODARC2 int8,
   DOC_ORDEN int8,
   DOR_IDEXTE int8,
   DOR_URLREM varchar(512),
   DOR_CODADM int8,
   primary key (DOC_CODI)
);
create table RSC_DOCSER (
   DSR_CODI int8 not null,
   DSR_CODSER int8,
   ORDEN int8,
   primary key (DSR_CODI)
);
create table RSC_SENSGR (
   SSC_CODSEN int8 not null,
   SSC_CODSGR int8 not null,
   primary key (SSC_CODSEN, SSC_CODSGR)
);
create table RSC_PRONOR (
   PRN_CODNOR int8 not null,
   PRN_CODPRO int8 not null,
   primary key (PRN_CODPRO, PRN_CODNOR)
);
create table RSC_FICMAT (
   FIM_CODFIC int8 not null,
   FIM_CODMAT int8 not null,
   primary key (FIM_CODFIC, FIM_CODMAT)
);
create table RSC_TRATRT (
   TTT_CODTRT int8 not null,
   TTT_TIPO varchar(256),
   TTT_CARGOM varchar(256),
   TTT_CARGOF varchar(256),
   TTT_TRATAM varchar(256),
   TTT_TRATAF varchar(256),
   TTT_CODIDI varchar(2) not null,
   primary key (TTT_CODTRT, TTT_CODIDI)
);
create table RSC_ARCHIV (
   ARC_CODI int8 not null,
   ARC_NOMBRE varchar(128) not null,
   ARC_MIME varchar(128) not null,
   ARC_PESO int8 not null,
   ARC_DATOS bytea,
   primary key (ARC_CODI)
);
create table RSC_HEVISR (
   HVS_CODI int8 not null,
   HVS_CODHEV int8,
   HVS_CODSER int8,
   HVS_ORDEN int4,
   HVP_CODHEV int8,
   HVP_ORDEN int4,
   primary key (HVS_CODI)
);
create table RSC_SOLRPD (
   SLP_ID int8 not null,
   SLP_TIPO varchar(255),
   SLP_IDELEM int8,
   SLP_ACCION int4,
   SLP_FECCRE timestamp,
   SLP_FECIDX timestamp,
   SLP_RESULT int4,
   SLP_TXTERR varchar(300),
   primary key (SLP_ID)
);
create table RSC_TRANOR (
   TNO_CODNOR int8 not null,
   TNO_SECCIO varchar(256),
   TNO_APARTA varchar(256),
   TNO_PAGINI int4,
   TNO_PAGFIN int4,
   TNO_TITULO varchar(1024),
   TNO_ENLACE varchar(512),
   TNO_RESPON varchar(512),
   TNO_OBSERV varchar(4000),
   TNO_CODIDI varchar(2) not null,
   primary key (TNO_CODNOR, TNO_CODIDI)
);
create table RSC_HECVIT (
   HEV_CODI int8 not null,
   HEV_ORDEN int4,
   HEV_CODEST varchar(255),
   HEV_ICONO int8,
   HEV_FOTO int8,
   HEV_ICOGRA int8,
   primary key (HEV_CODI)
);
alter table RSC_TRAENL add constraint RSC_TENENL_FK foreign key (TEN_CODENL) references RSC_ENLACE;
alter table RSC_HEVIAG add constraint RSC_HVAAGH_FK foreign key (HVA_CODAGH) references RSC_AGHEVI;
alter table RSC_HEVIAG add constraint RSC_HVAHEV_FK foreign key (HVA_CODHEV) references RSC_HECVIT;
alter table RSC_DOCNOR add constraint DNO_CODNOR_FK foreign key (DNO_CODNOR) references RSC_NORMAT;
alter table RSC_TRAEDI add constraint RSC_TEDEDI_FK foreign key (TED_CODEDI) references RSC_EDIFIC;
alter table RSC_AFECTA add constraint RSC_AFENOR_FK foreign key (AFE_CODNOR) references RSC_NORMAT;
alter table RSC_AFECTA add constraint RSC_AFETIA_FK foreign key (AFE_CODTIA) references RSC_TIPAFE;
alter table RSC_AFECTA add constraint RSC_AFENOA_FK foreign key (AFE_CODNOA) references RSC_NORMAT;
alter table RSC_FORMUL add constraint RSC_FORARC_FK foreign key (FOR_CODARC) references RSC_ARCHIV;
alter table RSC_FORMUL add constraint RSC_FORTRA_FK foreign key (FOR_CODTRA) references RSC_TRAMIT;
alter table RSC_FORMUL add constraint RSC_FORMAN_FK foreign key (FOR_MANUAL) references RSC_ARCHIV;
alter table RSC_UNAEDI add constraint RSC_UNEEDI_FK foreign key (UNE_CODEDI) references RSC_EDIFIC;
alter table RSC_UNAEDI add constraint RSC_UNEUNA_FK foreign key (UNE_CODUNA) references RSC_UNIADM;
alter table RSC_SIAPDT add constraint RSC_SIP_SIAUA_FK foreign key (SIP_SIAUA) references RSC_SIAUA;
alter table RSC_ICOFAM add constraint RSC_ICFICO_FK foreign key (ICF_ICONO) references RSC_ARCHIV;
alter table RSC_ICOFAM add constraint RSC_ICFPEC_FK foreign key (ICF_CODPEC) references RSC_PERCIU;
alter table RSC_ICOFAM add constraint RSC_ICFFAM_FK foreign key (ICF_CODFAM) references RSC_FAMILI;
alter table RSC_SCRKEY add constraint RSC_STPSCK_FK foreign key (SCK_STPCOD) references RSC_SCRTIP;
alter table RSC_AGRMAT add constraint RSC_AGMSEC_FK foreign key (AGM_CODSEC) references RSC_SECCIO;
alter table RSC_SIAUA add constraint RSC_SUAUNA_FK foreign key (SUA_CODUNA) references RSC_UNIADM;
alter table RSC_NORMAT add constraint RSC_NORBOL_FK foreign key (NOR_CODBOL) references RSC_BOLETI;
alter table RSC_NORMAT add constraint RSC_NORTIP_FK foreign key (NOR_CODTIP) references RSC_TIPO;
alter table RSC_NORMAT add constraint RSC_NERADM_FK foreign key (NER_CODADM) references RSC_ADMREM;
alter table RSC_TAXA add constraint RSC_TAXTRA_FK foreign key (TAX_CODTRA) references RSC_TRAMIT;
alter table RSC_SERNOR add constraint RSC_SRNSER_FK foreign key (SRN_CODSER) references RSC_SERVIC;
alter table RSC_SERNOR add constraint RSC_SRNNOR_FK foreign key (SRN_CODNOR) references RSC_NORMAT;
alter table RSC_TRAPEG add constraint RSC_TPGPEG_FK foreign key (TPG_CODPEG) references RSC_PERGES;
alter table RSC_TRASEC add constraint RSC_TSESEC_FK foreign key (TSE_CODSEC) references RSC_SECCIO;
alter table RSC_TRADOCTRA add constraint RSC_TDOARC_FK foreign key (TDO_CODARC) references RSC_ARCHIV;
alter table RSC_TRADOCTRA add constraint RSC_TDODOCTRA_FK foreign key (TDO_CODTRA) references RSC_DOCTRA;
alter table RSC_TRAPEC add constraint RSC_TPEPEC_FK foreign key (TPE_CODPEC) references RSC_PERCIU;
alter table RSC_TRAPOB add constraint RSC_TRPPOB_FK foreign key (TRP_CODPOB) references RSC_PUBOBJ;
alter table RSC_UNIADM add constraint RSC_UNAFOG_FK foreign key (UNA_FOTOG) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNAUNA_FK foreign key (UNA_CODUNA) references RSC_UNIADM;
alter table RSC_UNIADM add constraint RSC_UNALOV_FK foreign key (UNA_LOGOV) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNATRT_FK foreign key (UNA_CODTRT) references RSC_TRATAM;
alter table RSC_UNIADM add constraint RSC_UNALOH_FK foreign key (UNA_LOGOH) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNALOS_FK foreign key (UNA_LOGOS) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNALOT_FK foreign key (UNA_LOGOT) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNRADM_FK foreign key (UNR_CODADM) references RSC_ADMREM;
alter table RSC_UNIADM add constraint RSC_UNAFOP_FK foreign key (UNA_FOTOP) references RSC_ARCHIV;
alter table RSC_UNIADM add constraint RSC_UNAESP_FK foreign key (UNA_CODESP) references RSC_ESPTER;
alter table RSC_MATERI add constraint RSC_MATICG_FK foreign key (MAT_ICOGRA) references RSC_ARCHIV;
alter table RSC_MATERI add constraint RSC_MATICO_FK foreign key (MAT_ICONO) references RSC_ARCHIV;
alter table RSC_MATERI add constraint RSC_MATFOT_FK foreign key (MAT_FOTO) references RSC_ARCHIV;
alter table RSC_PROMAT add constraint RSC_PRMMAT_FK foreign key (PRM_CODMAT) references RSC_MATERI;
alter table RSC_PROMAT add constraint RSC_PRMPRO_FK foreign key (PRM_CODPRO) references RSC_PROCED;
alter table RSC_ADMREM add constraint RSC_ADMESP_FK foreign key (ADM_CODESP) references RSC_ESPTER;
alter table RSC_ADMREM add constraint RSC_ADMLOP_FK foreign key (ADM_LOGOP) references RSC_ARCHIV;
alter table RSC_ADMREM add constraint RSC_ADMLOG_FK foreign key (ADM_LOGOG) references RSC_ARCHIV;
alter table RSC_FICHEV add constraint RSC_FIHFIC_FKE5B59C45 foreign key (FIH_CODFIC) references RSC_FICHA;
alter table RSC_FICHEV add constraint RSC_FIHFIC_FK foreign key (FIH_CODFIC) references RSC_FICHA;
alter table RSC_FICHEV add constraint RSC_FIHHEV_FK foreign key (FIH_CODHEV) references RSC_HECVIT;
alter table RSC_HISTOR add constraint RSC_HISMAT_FK foreign key (HIS_CODMAT) references RSC_MATERI;
alter table RSC_HISTOR add constraint RSC_HISSER_FK foreign key (HIS_CODSER) references RSC_SERVIC;
alter table RSC_HISTOR add constraint RSC_HISPRO_FK foreign key (HIS_CODPRO) references RSC_PROCED;
alter table RSC_HISTOR add constraint RSC_HISNOR_FK foreign key (HIS_CODNOR) references RSC_NORMAT;
alter table RSC_HISTOR add constraint RSC_HISUNA_FK foreign key (HIS_CODUNA) references RSC_UNIADM;
alter table RSC_HISTOR add constraint RSC_HISFIC_FK foreign key (HIS_CODFIC) references RSC_FICHA;
alter table RSC_SCRGRP add constraint RSC_STPSGR_FK foreign key (SGR_STPCOD) references RSC_SCRTIP;
alter table RSC_TRASER add constraint RSC_TRASER_SER_FK foreign key (TSR_CODSER) references RSC_SERVIC;
alter table RSC_TRAINI add constraint RSC_TRAINI_FK foreign key (TIN_CODINI) references RSC_INICI;
alter table RSC_TRADSR add constraint RSC_TRADSR_ARCHIV_FK foreign key (TDS_CODARC) references RSC_ARCHIV;
alter table RSC_TRADSR add constraint RSC_TRADSR_DOCSER_FK foreign key (TDS_CODDSR) references RSC_DOCSER;
alter table RSC_TRAAGM add constraint RSC_TAMAGM_FK foreign key (TAM_CODAGM) references RSC_AGRMAT;
alter table RSC_GRPGEN add constraint FK_RSC_GRPG_REFERENCE_RSC_SCRT foreign key (GRP_STPCOD) references RSC_SCRTIP;
alter table RSC_POBPRO add constraint RSC_PPRPRO_FK foreign key (PPR_CODPRO) references RSC_PROCED;
alter table RSC_POBPRO add constraint RSC_PPRPOB_FK foreign key (PPR_CODPOB) references RSC_PUBOBJ;
alter table RSC_TRAAGH add constraint RSC_TRGAGH_FK foreign key (TRG_CODAGH) references RSC_AGHEVI;
alter table RSC_SCRGID add constraint RSC_SGRSGI_FK foreign key (SGI_SGRCOD) references RSC_SCRGRP;
alter table RSC_SERMAT add constraint RSC_SRMSER_FK foreign key (SRM_CODSER) references RSC_SERVIC;
alter table RSC_SERMAT add constraint RSC_SRMMAT_FK foreign key (SRM_CODMAT) references RSC_MATERI;
alter table RSC_PROCED add constraint RSC_PRO_CODSIL_FK foreign key (PRO_CODSIL) references RSC_SILADM;
alter table RSC_PROCED add constraint RSC_PRO_CODUNA_RESOL_FK foreign key (PRO_CODUNA_RESOL) references RSC_UNIADM;
alter table RSC_PROCED add constraint RSC_PRRADM_FK foreign key (PRR_CODADM) references RSC_ADMREM;
alter table RSC_PROCED add constraint RSC_PROINI_FK foreign key (PRO_CODINI) references RSC_INICI;
alter table RSC_PROCED add constraint RSC_PRO_CODUNA_SERV_FK foreign key (PRO_CODUNA_SERV) references RSC_UNIADM;
alter table RSC_PROCED add constraint RSC_PROFAM_FK foreign key (PRO_CODFAM) references RSC_FAMILI;
alter table RSC_PROCED add constraint RSC_PROUNA_FK foreign key (PRO_CODUNA) references RSC_UNIADM;
alter table RSC_GRPGID add constraint FK_RSC_GRPG_REFERENCE_RSC_GRPG foreign key (SGR_GRPCOD) references RSC_GRPGEN;
alter table RSC_FICHA add constraint RSC_FICICO_FK foreign key (FIC_ICONO) references RSC_ARCHIV;
alter table RSC_FICHA add constraint RSC_FICBAN_FK foreign key (FIC_BANER) references RSC_ARCHIV;
alter table RSC_FICHA add constraint RSC_FICIMA_FK foreign key (FIC_IMAGEN) references RSC_ARCHIV;
alter table RSC_FICHA add constraint RSC_FIRADM_FK foreign key (FIR_CODADM) references RSC_ADMREM;
alter table RSC_HEVIPR add constraint RSC_HVPHEV_FK foreign key (HVP_CODHEV) references RSC_HECVIT;
alter table RSC_HEVIPR add constraint RSC_HVPPRO_FK foreign key (HVP_CODPRO) references RSC_PROCED;
alter table RSC_ENVTEM add constraint FK_RSC_ENVT_REFERENCE_RSC_HISE foreign key (HEN_COD) references RSC_HISENV;
alter table RSC_TRAUNA add constraint RSC_TUNUNA_FK foreign key (TUN_CODUNA) references RSC_UNIADM;
alter table RSC_SECCIO add constraint RSC_SECSEC_FK foreign key (SEC_CODSEC) references RSC_SECCIO;
alter table RSC_UNANOR add constraint RSC_UNNUNA_FK foreign key (UNN_CODUNA) references RSC_UNIADM;
alter table RSC_UNANOR add constraint RSC_UNNNOR_FK foreign key (UNN_CODNOR) references RSC_NORMAT;
alter table RSC_TRAEXD add constraint RSC_TEEXD_FK foreign key (TED_CODEXD) references RSC_EXCDOC;
alter table RSC_ENLACE add constraint RSC_ENLPRO_FK foreign key (ENL_CODPRO) references RSC_PROCED;
alter table RSC_ENLACE add constraint RSC_ENLFIC_FK foreign key (ENL_CODFIC) references RSC_FICHA;
alter table RSC_ESPTER add constraint RSC_ESPLOG_FK foreign key (ESP_LOGO) references RSC_ARCHIV;
alter table RSC_ESPTER add constraint RSC_ESPESP_FK foreign key (ESP_CODESP) references RSC_ESPTER;
alter table RSC_ESPTER add constraint RSC_ESPMAP_FK foreign key (ESP_MAPA) references RSC_ARCHIV;
alter table RSC_EDIFIC add constraint RSC_EDIPLA_FK foreign key (EDI_PLANO) references RSC_ARCHIV;
alter table RSC_EDIFIC add constraint RSC_EDRADM_FK foreign key (EDR_CODADM) references RSC_ADMREM;
alter table RSC_EDIFIC add constraint RSC_EDIFOG_FK foreign key (EDI_FOTOG) references RSC_ARCHIV;
alter table RSC_EDIFIC add constraint RSC_EDIFOP_FK foreign key (EDI_FOTOP) references RSC_ARCHIV;
alter table RSC_POBSER add constraint RSC_PSRSER_FK foreign key (PSR_CODSER) references RSC_SERVIC;
alter table RSC_POBSER add constraint RSC_PSRPOB_FK foreign key (PSR_CODPOB) references RSC_PUBOBJ;
alter table RSC_TRAUNM add constraint RSC_TRNUNM_FK foreign key (TRN_CODUNM) references RSC_UNAMAT;
alter table RSC_TRAFAM add constraint RSC_TFAFAM_FK foreign key (TFA_CODFAM) references RSC_FAMILI;
alter table RSC_AGHEVI add constraint RSC_AGHICG_FK foreign key (AGH_ICOGRA) references RSC_ARCHIV;
alter table RSC_AGHEVI add constraint RSC_AGHPOB_FK foreign key (AGH_CODPOB) references RSC_PUBOBJ;
alter table RSC_AGHEVI add constraint RSC_AGHICO_FK foreign key (AGH_ICONO) references RSC_ARCHIV;
alter table RSC_AGHEVI add constraint RSC_AGHFOT_FK foreign key (AGH_FOTO) references RSC_ARCHIV;
alter table RSC_TRAFIC add constraint RSC_FICICO_FK foreign key (TFI_ICONO) references RSC_ARCHIV;
alter table RSC_TRAFIC add constraint RSC_FICBAN_FK foreign key (TFI_BANNER) references RSC_ARCHIV;
alter table RSC_TRAFIC add constraint RSC_FICIMA_FK foreign key (TFI_IMAGEN) references RSC_ARCHIV;
alter table RSC_TRAFIC add constraint RSC_TFIFIC_FKE5B59C45 foreign key (TFI_CODFIC) references RSC_FICHA;
alter table RSC_TRAFIC add constraint RSC_TFIFIC_FK foreign key (TFI_CODFIC) references RSC_FICHA;
alter table RSC_TRATAX add constraint RSC_TRATAX_FK foreign key (TTAX_CODI) references RSC_TAXA;
alter table RSC_COMENT add constraint RSC_COMPRO_FK foreign key (COM_CODPRO) references RSC_PROCED;
alter table RSC_COMENT add constraint RSC_COMFIC_FK foreign key (COM_CODFIC) references RSC_FICHA;
alter table RSC_COMENT add constraint RSC_COMUSU_FK foreign key (COM_CODUSU) references RSC_USUARI;
alter table RSC_SCRTEM add constraint FK_RSC_SCRT_REFERENCE_RSC_SCRI foreign key (SCR_COD) references RSC_SCRIPC;
alter table RSC_AUDITO add constraint RSC_AUDHIS_FK foreign key (AUD_CODHIS) references RSC_HISTOR;
alter table RSC_FICHUA add constraint RSC_FUAFIC_FK825EC5FC foreign key (FUA_CODFIC) references RSC_FICHA;
alter table RSC_FICHUA add constraint RSC_FUAUNA_FK foreign key (FUA_CODUNA) references RSC_UNIADM;
alter table RSC_FICHUA add constraint RSC_FUASEC_FK foreign key (FUA_CODSEC) references RSC_SECCIO;
alter table RSC_FICHUA add constraint RSC_FUAFIC_FK foreign key (FUA_CODFIC) references RSC_FICHA;
alter table RSC_TRAMAT add constraint RSC_TMAMAT_FK foreign key (TMA_CODMAT) references RSC_MATERI;
alter table RSC_TRAMAT add constraint RSC_TMACON_FK foreign key (TMA_CONTEN) references RSC_ARCHIV;
alter table RSC_TRAMAT add constraint RSC_TMADIS_FK foreign key (TMA_CODDIS) references RSC_ARCHIV;
alter table RSC_TRAMAT add constraint RSC_TMANOR_FK foreign key (TMA_NORMAT) references RSC_ARCHIV;
alter table RSC_SCRENV add constraint RSC_STPSENV_FK foreign key (SEN_STPCOD) references RSC_SCRTIP;
alter table RSC_TRASIL add constraint RSC_TSISIL_FK foreign key (TSI_CODSIL) references RSC_SILADM;
alter table RSC_TRADNR add constraint RSC_TRADNR_DOCNOR_FK foreign key (TDN_CODDNR) references RSC_DOCNOR;
alter table RSC_TRADNR add constraint RSC_TRADNR_ARCHIV_FK foreign key (TDN_CODARC) references RSC_ARCHIV;
alter table RSC_USUPEG add constraint RSC_USPPGS_FK foreign key (USP_CODPEG) references RSC_PERGES;
alter table RSC_USUPEG add constraint RSC_USPUSU_FK foreign key (USP_CODUSU) references RSC_USUARI;
alter table RSC_UNAMAT add constraint RSC_UNMUNA_FK foreign key (UNM_CODUNA) references RSC_UNIADM;
alter table RSC_UNAMAT add constraint RSC_UNMMAT_FK foreign key (UNM_CODMAT) references RSC_MATERI;
alter table RSC_ICMATE add constraint RSC_ICMPEC_FK foreign key (ICM_CODPEC) references RSC_PERCIU;
alter table RSC_ICMATE add constraint RSC_ICMICO_FK foreign key (ICM_ICONO) references RSC_ARCHIV;
alter table RSC_ICMATE add constraint RSC_ICMMAT_FK foreign key (ICM_CODMAT) references RSC_MATERI;
alter table RSC_TRAESP add constraint RSC_TESESP_FK foreign key (TES_CODESP) references RSC_ESPTER;
alter table RSC_TRAMIT add constraint RSC_TRAPRO_FK foreign key (TRA_CODPRO) references RSC_PROCED;
alter table RSC_TRAMIT add constraint RSC_TRRADM_FK foreign key (TRR_CODADM) references RSC_ADMREM;
alter table RSC_TRAMIT add constraint RSC_ORGCOMP_FK foreign key (TRA_ORGCOMP) references RSC_UNIADM;
alter table RSC_SERVIC add constraint RSC_SET_SERRSP_FK foreign key (SER_SERRSP) references RSC_UNIADM;
alter table RSC_SERVIC add constraint RSC_SER_INSTRU_FK foreign key (SER_INSTRU) references RSC_UNIADM;
alter table RSC_HISENV add constraint FK_RSC_HISE_REFERENCE_RSC_SCRT foreign key (HEN_STPCOD) references RSC_SCRTIP;
alter table RSC_TRADOC add constraint RSC_TDODOC_FK foreign key (TDO_CODDOC) references RSC_DOCUME;
alter table RSC_TRADOC add constraint RSC_TDOARC_FKE61D7DD2 foreign key (TDO_CODARC) references RSC_ARCHIV;
alter table RSC_TRADOC add constraint RSC_TDODOC_FKA31073AA foreign key (TDO_CODDOC) references RSC_DOCUME;
alter table RSC_TRADOC add constraint RSC_TDOARC_FK foreign key (TDO_CODARC) references RSC_ARCHIV;
alter table RSC_POBFIC add constraint RSC_PFCFIC_FKE5B59C45 foreign key (PFC_CODFIC) references RSC_FICHA;
alter table RSC_POBFIC add constraint RSC_PFCFIC_FK foreign key (PFC_CODFIC) references RSC_FICHA;
alter table RSC_POBFIC add constraint RSC_PFCPOB_FK foreign key (PFC_CODPOB) references RSC_PUBOBJ;
alter table RSC_TRAPRO add constraint RSC_TPRPRO_FK foreign key (TPR_CODPRO) references RSC_PROCED;
alter table RSC_UNAUSU add constraint RSC_UNUUNA_FK foreign key (UNU_CODUNA) references RSC_UNIADM;
alter table RSC_UNAUSU add constraint RSC_UNUUSU_FK foreign key (UNU_CODUSU) references RSC_USUARI;
alter table RSC_SCRIPC add constraint RSC_STPSCR_FK foreign key (SCR_STPCOD) references RSC_SCRTIP;
alter table RSC_SCRIPC add constraint FK_RSC_SCRI_REF_RSC_GRG foreign key (SCR_ESTUDIOS) references RSC_GRPGEN;
alter table RSC_SCRIPC add constraint FK_RSC_SCRI_REFERENCE_RSC_GRG foreign key (SCR_PROFESION) references RSC_GRPGEN;
alter table RSC_SCRIPC add constraint RSC_SGRSCR_FK foreign key (SCR_SGRCOD) references RSC_SCRGRP;
alter table RSC_TRATIP add constraint RSC_TTITIP_FK foreign key (TTI_CODTIP) references RSC_TIPO;
alter table RSC_CATDOC add constraint RSC_CDCEXD_FK foreign key (CDC_CODEXD) references RSC_EXCDOC;
alter table RSC_TRACDC add constraint RSC_TCDCDC_FK foreign key (TCD_CODCDC) references RSC_CATDOC;
alter table RSC_PEGSEC add constraint RSC_PGSPEG_FK foreign key (PGS_CODPEG) references RSC_PERGES;
alter table RSC_PEGSEC add constraint RSC_PGSSEC_FK foreign key (PGS_CODSEC) references RSC_SECCIO;
alter table RSC_ESTADI add constraint RSC_ESTHIS_FK foreign key (EST_CODHIS) references RSC_HISTOR;
alter table RSC_TRATIA add constraint RSC_TTATIA_FK foreign key (TTA_CODTIA) references RSC_TIPAFE;
alter table RSC_MATAGR add constraint RSC_MAGMAT_FK foreign key (MAG_CODMAT) references RSC_MATERI;
alter table RSC_MATAGR add constraint RSC_MAGAGM_FK foreign key (MAG_CODAGM) references RSC_AGRMAT;
alter table RSC_TRATRA add constraint RSC_TTRTRA_FK foreign key (TTR_CODTTR) references RSC_TRAMIT;
alter table RSC_PERSON add constraint RSC_PERUNA_FK foreign key (PER_CODUNA) references RSC_UNIADM;
alter table RSC_TRAHEV add constraint RSC_THEHEV_FK foreign key (THE_CODHEV) references RSC_HECVIT;
alter table RSC_TRAHEV add constraint RSC_THENOR_FK foreign key (THE_NORMAT) references RSC_ARCHIV;
alter table RSC_TRAHEV add constraint RSC_THECON_FK foreign key (THE_CONTEN) references RSC_ARCHIV;
alter table RSC_TRAHEV add constraint RSC_THEDIS_FK foreign key (THE_CODDIS) references RSC_ARCHIV;
alter table RSC_DOCTRA add constraint RSC_DOCARC_FK foreign key (DOC_CODARC) references RSC_ARCHIV;
alter table RSC_DOCTRA add constraint RSC_CODITRA_FK foreign key (CODITRA) references RSC_TRAMIT;
alter table RSC_DOCTRA add constraint RSC_DOCTRA_CATDOC_FK foreign key (DOC_CODCDC) references RSC_CATDOC;
alter table RSC_DOCTRA add constraint RSC_DOCTRA_EXCDOC_FK foreign key (DOC_CODEXD) references RSC_EXCDOC;
alter table RSC_DOCUME add constraint RSC_DOCARC2_FK foreign key (DOC_CODARC2) references RSC_ARCHIV;
alter table RSC_DOCUME add constraint RSC_DOCARC2_FKE61D7DD2 foreign key (DOC_CODARC2) references RSC_ARCHIV;
alter table RSC_DOCUME add constraint RSC_DOCPRO_FK foreign key (DOC_CODPRO) references RSC_PROCED;
alter table RSC_DOCUME add constraint RSC_DOCFIC_FK foreign key (DOC_CODFIC) references RSC_FICHA;
alter table RSC_DOCUME add constraint RSC_DORADM_FK foreign key (DOR_CODADM) references RSC_ADMREM;
alter table RSC_DOCSER add constraint DSR_CODSER_FK foreign key (DSR_CODSER) references RSC_SERVIC;
alter table RSC_SENSGR add constraint FKB4A83B7F8E2C8202 foreign key (SSC_CODSGR) references RSC_SCRGRP;
alter table RSC_SENSGR add constraint FKB4A83B7F8E2C81C0 foreign key (SSC_CODSEN) references RSC_SCRENV;
alter table RSC_PRONOR add constraint RSC_PRNNOR_FK foreign key (PRN_CODNOR) references RSC_NORMAT;
alter table RSC_PRONOR add constraint RSC_PRNPRO_FK foreign key (PRN_CODPRO) references RSC_PROCED;
alter table RSC_FICMAT add constraint RSC_FIMFIC_FKE5B59C45 foreign key (FIM_CODFIC) references RSC_FICHA;
alter table RSC_FICMAT add constraint RSC_FIMMAT_FK foreign key (FIM_CODMAT) references RSC_MATERI;
alter table RSC_FICMAT add constraint RSC_FIMFIC_FK foreign key (FIM_CODFIC) references RSC_FICHA;
alter table RSC_TRATRT add constraint RSC_TTTTRT_FK foreign key (TTT_CODTRT) references RSC_TRATAM;
alter table RSC_HEVISR add constraint RSC_HVSHEV_FK foreign key (HVS_CODHEV) references RSC_HECVIT;
alter table RSC_HEVISR add constraint RSC_HVSSER_FK foreign key (HVS_CODSER) references RSC_SERVIC;
alter table RSC_TRANOR add constraint RSC_TNONOR_FK foreign key (TNO_CODNOR) references RSC_NORMAT;
alter table RSC_HECVIT add constraint RSC_HEVICO_FK foreign key (HEV_ICONO) references RSC_ARCHIV;
alter table RSC_HECVIT add constraint RSC_HEVFOT_FK foreign key (HEV_FOTO) references RSC_ARCHIV;
alter table RSC_HECVIT add constraint RSC_HEVICG_FK foreign key (HEV_ICOGRA) references RSC_ARCHIV;
create sequence RSC_SEQSEN;
create sequence RSC_SEQHIS;
create sequence RSC_SEQ_ALL;
create sequence RSC_SEQSCK;
create sequence RSC_SEQ_COM;
create sequence RSC_SEQGRP;
create sequence RSC_SEQSGR;
create sequence RSC_SEQSCR;
