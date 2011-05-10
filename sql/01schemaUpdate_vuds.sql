--
-- scripts de ventanilla unica
-- 
-- enric@dgtic
-- 20 Agost 2010
--
-- descomentar si es necessari fer un
-- drop d'objetees per la ventanilla unica.
--

-- ===== drop tables

-- ALTER TABLE ROLSAC.RSC_TRATAX DROP PRIMARY KEY CASCADE;
-- drop table RSC_TRATAX;

-- ALTER TABLE ROLSAC.RSC_TAXA DROP PRIMARY KEY CASCADE;
-- drop table RSC_TAXA;

-- ALTER TABLE ROLSAC.RSC_TRADOCTRA DROP PRIMARY KEY CASCADE;
-- drop table RSC_TRADOCTRA;

-- ALTER TABLE ROLSAC.RSC_DOCTRA DROP PRIMARY KEY CASCADE;
-- drop table RSC_DOCTRA;

-- ===== drop columns

-- ALTER TABLE RSC_TRAPRO DROP column TPR_RESULT;

-- alter table RSC_NORMAT DROP  column NOR_CODIVUDS ;

-- alter table RSC_NORMAT drop column NOR_DESCCODIVUDS ;

-- alter table RSC_TRATRA drop column TTR_REQUI ;

-- alter table RSC_TRATRA drop column TTR_LUGAR ;

-- alter table RSC_TRAMIT drop column TRA_CODIVUDS ;

-- alter table RSC_TRAMIT drop column TRA_DESCCODIVUDS ;

-- alter table RSC_TRAMIT drop column TRA_VALIDA ;

-- alter table RSC_TRAMIT drop column TRA_ORDEN ;

-- alter table RSC_TRAMIT drop column TRA_DATCADU ;

-- alter table RSC_TRAMIT drop column TRA_DATPUBL ;

-- alter table RSC_TRAMIT drop column TRA_DATACTU ;

-- alter table RSC_TRAMIT drop column TRA_IDTRAMTEL ;

-- alter table RSC_TRAMIT drop column TRA_VERSIO ;

-- alter table RSC_TRAMIT drop column TRA_URLEXTE ;

-- alter table RSC_TRAMIT drop column TRA_ORGCOMP ;

-- alter table RSC_PROCED drop column PRO_TAXA ;

-- alter table RSC_PROCED drop column PRO_CODUNA_RESOL ;  -- dice id no valido

-- ===== drop constraints

 -- alter table RSC_TRADOC    drop constraint RSC_TRADOC_ARCHIV_FK ; 

 -- alter table RSC_DOCTRA    drop constraint RSC_DOCTRA_ARCHIV_FK ;
 
 -- alter table RSC_DOCTRA    drop constraint RSC_DOCTRA_TRAMIT_FK ;
 
 -- alter table RSC_TRADOCTRA drop constraint RSC_TRADOCTRA_DOCTRA_FK ;
 
 -- alter table RSC_TRADOCTRA drop constraint RSC_TRADOCTRA_ARCHIV_FK ;

 -- alter table RSC_TRAMIT    drop constraint RSC_TRAMIT_UNIADM_FK ;  -- no existe

 -- alter table RSC_PROCED    drop constraint RSC_PROCED_UNIADM_FK ;  -- no existe

 -- alter table RSC_TRAINI    drop constraint RSC_TRAINI_INICI_FK ;

 --
 -- aquests alters els ha trobat hibernate, pero no son propiament de la ventanilla unica 
 --
 -- alter table RSC_TRAINI    drop constraint RSC_TRAINI_INICI_FK ;
 -- alter table RSC_UNIADM    RSC_UNIADM_UNIADM_FK drop  constraint ;
 -- alter table RSC_UNIADM    RSC_UNIADM_ARCHIV_LOGOV_FK drop constraint ;
 -- alter table RSC_UNIADM    RSC_UNIADM_ARCHIV_LOGOS_FK drop constraint ;
 -- alter table RSC_UNIADM    RSC_UNIADM_ARCHIV_LOGOT_FK drop constraint ;
 -- alter table RSC_UNIADM    RSC_UNIADM_ARCHIV_LOGOH_FK drop constraint ;




--
--  creacio d'objectes per la ventanilla unica
-- 

-- 4 taules
 
 create table RSC_TAXA (TAX_CODI number(19,0) not null, CODITRA number(19,0), primary key (TAX_CODI));
 create table RSC_DOCTRA (DOC_CODI number(19,0) not null, CODITRA number(19,0), DOC_CODARC number(19,0), ORDEN number(19,0), TIPUS number(10,0), primary key (DOC_CODI));
 create table RSC_TRADOCTRA (TDO_CODTRA number(19,0) not null, TDO_TITULO varchar2(256), TDO_DESCRI varchar2(4000), TDO_CODARC number(19,0), TDO_CODIDI varchar2(2) not null, primary key (TDO_CODTRA, TDO_CODIDI));
 create table RSC_TRATAX (TTAX_CODI number(19,0) not null, TAX_ID varchar2(256), DESCRI varchar2(4000), FORMPAG varchar2(4000), COD_IDI varchar2(2) not null, primary key (TTAX_CODI, COD_IDI));

-- 18 columnes

 alter table RSC_TRAPRO add TPR_RESULT varchar2(4000);
 alter table RSC_NORMAT add NOR_CODIVUDS varchar2(255);
 alter table RSC_NORMAT add NOR_DESCCODIVUDS varchar2(255);
 alter table RSC_TRATRA add TTR_REQUI varchar2(4000);
 alter table RSC_TRATRA add TTR_LUGAR varchar2(512);
 alter table RSC_TRAMIT add TRA_CODIVUDS varchar2(255);
 alter table RSC_TRAMIT add TRA_DESCCODIVUDS varchar2(255);
 alter table RSC_TRAMIT add TRA_VALIDA number(19,0);
 alter table RSC_TRAMIT add TRA_ORDEN number(19,0);  
 alter table RSC_TRAMIT add TRA_DATCADU date;
 alter table RSC_TRAMIT add TRA_DATPUBL date;
 alter table RSC_TRAMIT add TRA_DATACTU date;
 alter table RSC_TRAMIT add TRA_IDTRAMTEL varchar2(255);
 alter table RSC_TRAMIT add TRA_VERSIO number(10,0);
 alter table RSC_TRAMIT add TRA_URLEXTE varchar2(255);
 alter table RSC_TRAMIT add TRA_ORGCOMP number(19,0);
 alter table RSC_TRAMIT add TRA_DATACTUVUDS varchar2(255);
 alter table RSC_PROCED add PRO_TAXA varchar2(1024);
 alter table RSC_PROCED add PRO_CODUNA_RESOL number(19,0);

 -- 11 constraints
 
 alter table RSC_TAXA      add constraint RSC_TAXA_TRAMIT_FK foreign key (CODITRA) references RSC_TRAMIT; 
 alter table RSC_DOCTRA    add constraint RSC_DOCTRA_ARCHIV_FK foreign key (DOC_CODARC) references RSC_ARCHIV;
 alter table RSC_DOCTRA    add constraint RSC_DOCTRA_TRAMIT_FK foreign key (CODITRA) references RSC_TRAMIT;
 alter table RSC_TRADOC    add constraint RSC_TRADOC_ARCHIV_FK foreign key (TDO_CODARC) references RSC_ARCHIV;
 alter table RSC_TRAINI    add constraint RSC_TRAINI_INICI_FK foreign key (TIN_CODINI) references RSC_INICI;
 alter table RSC_TRADOCTRA add constraint RSC_TRADOCTRA_DOCTRA_FK foreign key (TDO_CODTRA) references RSC_DOCTRA;
 alter table RSC_TRADOCTRA add constraint RSC_TRADOCTRA_ARCHIV_FK foreign key (TDO_CODARC) references RSC_ARCHIV;
 alter table RSC_TRAMIT    add constraint RSC_TRAMIT_UNIADM_FK foreign key (TRA_ORGCOMP) references RSC_UNIADM;
 alter table RSC_TRATAX    add constraint RSC_TRATAX_TAXA_FK foreign key (TTAX_CODI) references RSC_TAXA;
 alter table RSC_PROCED    add constraint RSC_PROCED_UNIADM_FK foreign key (PRO_CODUNA_RESOL) references RSC_UNIADM;
 -- alter table RSC_TRAPRO    add constraint RSC_TRAPRO_PROCED_FK foreign key (TPR_CODPRO) references ROLSAC.RSC_PROCED(PRO_CODI);
 -- dona error 0RA-02298. sembla que cal borrar abans els fills orfans

 
-- == diferencies trobades per hibernate, pero que no son de la ventanilla unica:
-- alter table RSC_UNIADM    add constraint RSC_UNIADM_UNIADM_FK foreign key (UNA_CODUNA) references RSC_UNIADM;
-- alter table RSC_UNIADM    add constraint RSC_UNIADM_ARCHIV_LOGOV_FK foreign key (UNA_LOGOV) references RSC_ARCHIV;
-- alter table RSC_UNIADM    add constraint RSC_UNIADM_ARCHIV_LOGOS_FK foreign key (UNA_LOGOS) references RSC_ARCHIV;
-- alter table RSC_UNIADM    add constraint RSC_UNIADM_ARCHIV_LOGOT_FK foreign key (UNA_LOGOT) references RSC_ARCHIV; alter table RSC_UNIADM    add constraint RSC_UNIADM_ARCHIV_LOGOH_FK foreign key (UNA_LOGOH) references RSC_ARCHIV;
 
 -- 4 sinomins
 
 create synonym WWW_ROLSAC.RSC_TAXA FOR ROLSAC.RSC_TAXA;
 create synonym WWW_ROLSAC.RSC_TRADOCTRA FOR ROLSAC.RSC_TRADOCTRA;
 create synonym WWW_ROLSAC.RSC_TRATAX FOR ROLSAC.RSC_TRATAX;
 create synonym WWW_ROLSAC.RSC_DOCTRA FOR ROLSAC.RSC_DOCTRA;
 
 -- 4 grants
 
GRANT DELETE, INSERT, SELECT, UPDATE ON ROLSAC.RSC_TAXA TO "WWW_ROLSAC" WITH GRANT OPTION;
GRANT DELETE, INSERT, SELECT, UPDATE ON ROLSAC.RSC_TRADOCTRA TO "WWW_ROLSAC" WITH GRANT OPTION;
GRANT DELETE, INSERT, SELECT, UPDATE ON ROLSAC.RSC_TRATAX TO "WWW_ROLSAC" WITH GRANT OPTION;
GRANT DELETE, INSERT, SELECT, UPDATE ON ROLSAC.RSC_DOCTRA TO "WWW_ROLSAC" WITH GRANT OPTION;


-- incialitzacio del camp tra_orden

declare 
 contador number;
 begin
 for p in (select pro_codi from rsc_proced) loop
    contador:=0;
    for t in (select tra_codi from rsc_tramit where tra_codpro=p.pro_codi) loop
    --DBMS_OUTPUT.put_line(contador);
        update rsc_tramit set tra_orden=contador where tra_codi=t.tra_codi;
        contador:=contador+1;
    end loop;
 end loop;    
end;


-- posar publics tots el tramits
update rsc_tramit set tra_fase=1
