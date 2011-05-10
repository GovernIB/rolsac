--VUDS de la CAIB
 create table RSC_TAXA (TAX_CODI int8 not null, CODITRA int8, primary key (TAX_CODI));
 create table RSC_DOCTRA (DOC_CODI int8 not null, CODITRA int8, DOC_CODARC int8, ORDEN int8, TIPUS int4, primary key (DOC_CODI));
 create table RSC_TRADOCTRA (TDO_CODTRA int8 not null, TDO_TITULO varchar(256), TDO_DESCRI varchar(4000), TDO_CODARC int8, TDO_CODIDI varchar(2) not null, primary key (TDO_CODTRA, TDO_CODIDI));
 create table RSC_TRATAX (TTAX_CODI int8 not null, TAX_ID varchar(256), DESCRI varchar(4000), FORMPAG varchar(4000), COD_IDI varchar(2) not null, primary key (TTAX_CODI, COD_IDI));

 alter table RSC_TRAPRO add TPR_RESULT varchar(4000);
 alter table RSC_NORMAT add NOR_CODIVUDS varchar(255);
 alter table RSC_NORMAT add NOR_DESCCODIVUDS varchar(255);
 alter table RSC_TRATRA add TTR_REQUI varchar(4000);
 alter table RSC_TRATRA add TTR_LUGAR varchar(512);
 alter table RSC_TRAMIT add TRA_CODIVUDS varchar(255);
 alter table RSC_TRAMIT add TRA_DESCCODIVUDS varchar(255);
 alter table RSC_TRAMIT add TRA_VALIDA int8;
 alter table RSC_TRAMIT add TRA_ORDEN int8;  
 alter table RSC_TRAMIT add TRA_DATCADU timestamp;
 alter table RSC_TRAMIT add TRA_DATPUBL timestamp;
 alter table RSC_TRAMIT add TRA_DATACTU timestamp;
 alter table RSC_TRAMIT add TRA_IDTRAMTEL varchar(255);
 alter table RSC_TRAMIT add TRA_VERSIO int4;
 alter table RSC_TRAMIT add TRA_URLEXTE varchar(255);
 alter table RSC_TRAMIT add TRA_ORGCOMP int8;
 alter table RSC_PROCED add PRO_TAXA varchar(1024);
 alter table RSC_PROCED add PRO_CODUNA_RESOL int8;
 alter table RSC_IDIOMA add IDI_TRADUCTOR varchar(128);
 alter table RSC_TAXA add constraint RSC_CODITRA_FK foreign key (CODITRA) references RSC_TRAMIT;
 alter table RSC_DOCTRA add constraint RSC_CODITRA_FK foreign key (CODITRA) references RSC_TRAMIT;
 alter table RSC_DOCTRA add constraint RSC_DOCARC_FK foreign key (DOC_CODARC) references RSC_ARCHIV;
 alter table RSC_TRADOCTRA add constraint RSC_TDODOCTRA_FK foreign key (TDO_CODTRA) references RSC_DOCTRA;
 alter table RSC_TRADOCTRA add constraint RSC_TDOARC_FK foreign key (TDO_CODARC) references RSC_ARCHIV;
 alter table RSC_TRAMIT add constraint RSC_ORGCOMP_FK foreign key (TRA_ORGCOMP) references RSC_UNIADM;
 alter table RSC_TRATAX add constraint RSC_TRATAX_FK foreign key (TTAX_CODI) references RSC_TAXA;
 alter table RSC_PROCED add constraint RSC_PRO_CODUNA_RESOL_FK foreign key (PRO_CODUNA_RESOL) references RSC_UNIADM;
 

--VUDS NUESTROS

--18-06-10

alter table RSC_EDIFIC add EDR_IDEXTE  int8;
alter table RSC_EDIFIC add EDR_CODADM  int8;
alter table RSC_EDIFIC add EDI_TYPE  varchar(64) not null;

alter table RSC_EDIFIC add constraint RSC_EDRADM_FK foreign key (EDR_CODADM) references RSC_ADMREM;
UPDATE RSC_EDIFIC SET EDI_TYPE='edificio' WHERE EDI_TYPE is NULL;

--24-06-10

alter table RSC_ADMREM add ADM_VERSIO int8;
UPDATE RSC_ADMREM SET ADM_VERSIO=1 WHERE ADM_VERSIO is NULL;

--xx-07-10
ALTER TABLE RSC_TRAMIT ADD TRR_IDEXTE int8 NULL;
ALTER TABLE RSC_TRAMIT ADD TRR_URLREM varchar(512) NULL;
ALTER TABLE RSC_TRAMIT ADD TRR_CODADM int8 NULL;
ALTER TABLE RSC_TRAMIT ADD TRA_TYPE varchar(64) NOT NULL;

alter table RSC_TRAMIT add constraint RSC_TRRADM_FK foreign key (TRR_CODADM) references RSC_ADMREM;
UPDATE RSC_TRAMIT SET TRA_TYPE='tramite' WHERE TRA_TYPE is NULL;
 
alter table RSC_INICI add INI_CODEST varchar(256) NULL;


