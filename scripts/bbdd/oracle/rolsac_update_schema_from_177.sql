-- Aquestes sentencies son les que han d'executar les versions de rolsac 177(ajuntaments) per actualitzar-se a la versió unica.
--(revisat de marilen per ultima vegada dia 25/07/2011).

ALTER TABLE RSC_TAXA RENAME COLUMN CODITRA TO TAX_CODTRA; 


ALTER TABLE RSC_NORMAT
ADD
   (
      NER_IDEXTE number(19,0), 
      NER_URLREM varchar2(512),  
      NER_CODADM number(19,0)
   );
   
ALTER TABLE RSC_NORMAT ADD CONSTRAINT RSC_NERADM_FK FOREIGN KEY (NER_CODADM) REFERENCES RSC_ADMREM;


ALTER TABLE RSC_DOCUME
ADD
   (
      DOR_IDEXTE number(19,0),
	  DOR_URLREM varchar2(512), 
	  DOR_CODADM number(19,0) 
   );

ALTER TABLE RSC_DOCUME ADD CONSTRAINT RSC_DORADM_FK FOREIGN KEY (DOR_CODADM) REFERENCES RSC_ADMREM;


alter table RSC_TRAMIT add TRA_DATACTUVUDS varchar2(255);
