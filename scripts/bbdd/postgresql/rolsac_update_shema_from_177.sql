-- Aquestes sentencies son les que han d'executar les versions de rolsac 177(ajuntaments) per actualitzar-se a la versi� unica.
--(revisat de marilen per ultima vegada dia 25/07/2011).

ALTER TABLE RSC_TAXA RENAME COLUMN CODITRA TO TAX_CODTRA; 


ALTER TABLE RSC_NORMAT
ADD
   (
      NER_IDEXTE int8, 
      NER_URLREM varchar(512),  
      NER_CODADM int8
   );
   
ALTER TABLE RSC_NORMAT ADD CONSTRAINT RSC_NERADM_FK FOREIGN KEY (NER_CODADM) REFERENCES RSC_ADMREM;


ALTER TABLE RSC_DOCUME
ADD
   (
      DOR_IDEXTE int8,
	  DOR_URLREM varchar(512), 
	  DOR_CODADM int8 
   );

ALTER TABLE RSC_DOCUME ADD CONSTRAINT RSC_DORADM_FK FOREIGN KEY (DOR_CODADM) REFERENCES RSC_ADMREM;


alter table RSC_TRAMIT add TRA_DATACTUVUDS varchar(255);
