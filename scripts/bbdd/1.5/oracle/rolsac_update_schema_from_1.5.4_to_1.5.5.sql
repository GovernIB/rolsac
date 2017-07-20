--Incluye restricción de unicidad al código SIA y al id procedimiento en sia pendientes.
alter table RSC_SIAPDT add CONSTRAINT "RSC_PRO_SIAPDT_UNIQUE" UNIQUE ("SIP_IDELEM");
alter table RSC_PROCED add CONSTRAINT "RSC_PRO_CODSIA_UNIQUE" UNIQUE ("PRO_CODSIA");