
-- 9 columnes

alter table RSC_COMENT add COM_SUBSAN number(1,0);
alter table RSC_ADMREM add ADM_RESPON varchar2(256);
alter table RSC_ADMREM add ADM_VERSIO number(19,0);
alter table RSC_USUARI add USU_EMAIL varchar2(256);
alter table RSC_PROCED add PRO_RESPON varchar2(256);
alter table RSC_FICHA add FIC_RESPON varchar2(256);
alter table RSC_EDIFIC add EDI_TYPE varchar2(64);
alter table RSC_EDIFIC add EDR_IDEXTE number(19,0);
alter table RSC_EDIFIC add EDR_CODADM number(19,0);

-- 1 constraints

alter table RSC_EDIFIC add constraint RSC_EDRADM_FK foreign key (EDR_CODADM) references RSC_ADMREM;

-- inicialitzacio del camp edi_type

update RSC_EDIFIC set edi_type='edificio';
