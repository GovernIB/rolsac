CREATE TABLE rsc_perges
(
  peg_codi   bigint NOT NULL,
  peg_orden  integer,
  peg_codest character varying(256),
  peg_duplic  character varying(1)
);

ALTER TABLE public.rsc_perges OWNER TO rolsac;

CREATE TABLE rsc_trapeg
(
  tpg_codpeg bigint NOT NULL,
  tpg_nombre character varying(256),
  tpg_descri character varying(4000),
  tpg_codidi character varying(2) NOT NULL
);

ALTER TABLE public.rsc_trapeg OWNER TO rolsac;

CREATE TABLE rsc_pegsec
(
  pgs_codpeg bigint NOT NULL,
  pgs_codsec bigint NOT NULL
);

ALTER TABLE public.rsc_pegsec OWNER TO rolsac;

CREATE TABLE rsc_usupeg
(
  usp_codusu bigint NOT NULL,
  usp_codpeg bigint NOT NULL
);

ALTER TABLE public.rsc_usupeg OWNER TO rolsac;

ALTER TABLE ONLY rsc_perges 
	ADD CONSTRAINT rsc_perges_pkey PRIMARY KEY (peg_codi);
	
ALTER TABLE ONLY rsc_trapeg
	ADD CONSTRAINT rsc_trapeg_pkey PRIMARY KEY (tpg_codpeg,tpg_codidi);

ALTER TABLE ONLY rsc_pegsec
	ADD CONSTRAINT rsc_pegsec_pkey PRIMARY KEY (pgs_codpeg,pgs_codsec);
	
ALTER TABLE ONLY rsc_usupeg
	ADD CONSTRAINT rsc_usupeg_pkey PRIMARY KEY (usp_codusu,usp_codpeg);
	    
ALTER TABLE ONLY rsc_trapeg
	ADD CONSTRAINT rsc_trapeg_fk FOREIGN KEY (tpg_codpeg) REFERENCES rsc_perges (peg_codi);
	
ALTER TABLE ONLY rsc_pegsec
	ADD CONSTRAINT rsc_pegsec_fk FOREIGN KEY (pgs_codpeg) REFERENCES rsc_perges (peg_codi);
	
ALTER TABLE ONLY rsc_pegsec 
	ADD CONSTRAINT rsc_pgssec_fk FOREIGN KEY (pgs_codsec) REFERENCES rsc_seccio (sec_codi);
	
ALTER TABLE ONLY rsc_usupeg
	ADD CONSTRAINT rsc_uspusu_fk FOREIGN KEY (usp_codusu) REFERENCES rsc_usuari (usu_codi);
	
ALTER TABLE ONLY rsc_usupeg
	ADD CONSTRAINT rsc_usppgs_fk FOREIGN KEY (usp_codpeg) REFERENCES rsc_perges (peg_codi);
	
ALTER TABLE rsc_edific ALTER COLUMN edi_lat TYPE character varying(64);
ALTER TABLE rsc_edific ALTER COLUMN edi_lng TYPE character varying(64);

--ISSUE #351
ALTER TABLE rsc_proced ADD pro_coduna_serv bigint;
