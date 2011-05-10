alter table RSC_DOCUME drop constraint RSC_DOCARC_FK;

alter table RSC_DOCUME add  DOC_CODARC2 NUMBER(19,0);
update RSC_DOCUME set DOC_CODARC2 = DOC_CODARC;
alter table RSC_DOCUME drop column DOC_CODARC;

alter table RSC_DOCUME add constraint RSC_DOCARC2_FK foreign key (DOC_CODARC2) references RSC_ARCHIV;

alter table RSC_TAXA drop constraint RSC_CODITRA_FK;


alter table RSC_TAXA add  TAX_CODTRA NUMBER(19,0);
update RSC_TAXA set TAX_CODTRA = CODITRA;
alter table RSC_TAXA drop column CODITRA;
alter table RSC_TAXA add constraint RSC_TAXTRA_FK foreign key (TAX_CODTRA) references RSC_TRAMIT;
