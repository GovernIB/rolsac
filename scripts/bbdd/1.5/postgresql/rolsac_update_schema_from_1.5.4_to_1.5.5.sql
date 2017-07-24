--Incluye restricción de unicidad al código SIA y al id procedimiento en sia pendientes.
alter table RSC_SIAPDT add CONSTRAINT "RSC_PRO_SIAPDT_UNIQUE" UNIQUE ("SIP_IDELEM");
alter table RSC_PROCED add CONSTRAINT "RSC_PRO_CODSIA_UNIQUE" UNIQUE ("PRO_CODSIA");


--Incluye una restricción de unicidad en documento de id ficha, id procedimiento y orden.
alter table rsc_docume add CONSTRAINT "RSC_DOC_ORDEN_UNIQUE" UNIQUE ("DOC_CODFIC", "DOC_CODPRO", "DOC_ORDEN");
