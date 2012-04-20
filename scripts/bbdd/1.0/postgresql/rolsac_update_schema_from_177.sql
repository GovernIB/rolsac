﻿-- Aquestes sentencies son les que han d'executar les versions de rolsac 177(ajuntaments) per actualitzar-se a la versio rolsac-1.0
ALTER TABLE RSC_TAXA RENAME COLUMN CODITRA TO TAX_CODTRA; 


ALTER TABLE RSC_NORMAT ADD COLUMN NER_IDEXTE int8;
ALTER TABLE RSC_NORMAT ADD COLUMN NER_URLREM varchar(512);
ALTER TABLE RSC_NORMAT ADD COLUMN NER_CODADM int8;
   
ALTER TABLE RSC_NORMAT ADD CONSTRAINT RSC_NERADM_FK FOREIGN KEY (NER_CODADM) REFERENCES RSC_ADMREM;



alter table RSC_DOCUME drop constraint RSC_DOCARC_FK;

alter table RSC_DOCUME add column DOC_CODARC2 BIGINT;
update RSC_DOCUME set DOC_CODARC2 = DOC_CODARC;
alter table RSC_DOCUME drop column DOC_CODARC;

alter table RSC_DOCUME add constraint RSC_DOCARC2_FK foreign key (DOC_CODARC2) references RSC_ARCHIV;


ALTER TABLE RSC_DOCUME ADD COLUMN DOR_IDEXTE int8;
ALTER TABLE RSC_DOCUME ADD COLUMN DOR_URLREM varchar(512);
ALTER TABLE RSC_DOCUME ADD COLUMN DOR_CODADM int8;

alter table rsc_docume add DOC_TYPE varchar(64);
update rsc_docume set doc_type='documento';
alter table rsc_docume alter doc_type set not null;

ALTER TABLE RSC_DOCUME ADD CONSTRAINT RSC_DORADM_FK FOREIGN KEY (DOR_CODADM) REFERENCES RSC_ADMREM;


alter table RSC_TRAMIT add TRA_DATACTUVUDS varchar(255);

alter table RSC_TAXA drop constraint RSC_CODITRA_FK;


--alter table RSC_TAXA add column TAX_CODTRA BIGINT;
--update RSC_TAXA set TAX_CODTRA = CODITRA;
--alter table RSC_TAXA drop column CODITRA;
alter table RSC_TAXA add constraint RSC_TAXTRA_FK foreign key (TAX_CODTRA) references RSC_TRAMIT;

alter table RSC_TRAUNA add TUN_CVRESP TEXT;