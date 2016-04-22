--
-- PostgreSQL database dump
--


--
-- TOC entry 149 (class 1259 OID 17775)
-- Dependencies: 5
-- Name: rsc_admrem; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_admrem (
    adm_codi bigint NOT NULL,
    adm_endpoi character varying(512),
    adm_nombre character varying(512),
    adm_nivpro integer,
    adm_codsua character varying(512),
    adm_idremo character varying(512) NOT NULL,
    adm_respon character varying(256),
    adm_versio bigint,
    adm_codesp bigint,
    adm_logop bigint,
    adm_logog bigint
);


ALTER TABLE public.rsc_admrem OWNER TO rolsac;

--
-- TOC entry 226 (class 1259 OID 18274)
-- Dependencies: 5
-- Name: rsc_afecta; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_afecta (
    afe_codnor bigint NOT NULL,
    afe_codnoa bigint,
    afe_codtia bigint
);


ALTER TABLE public.rsc_afecta OWNER TO rolsac;

--
-- TOC entry 173 (class 1259 OID 17924)
-- Dependencies: 5
-- Name: rsc_aghevi; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_aghevi (
    agh_codi bigint NOT NULL,
    agh_codest character varying(128),
    agh_foto bigint,
    agh_icono bigint,
    agh_icogra bigint,
    agh_codpob bigint
);


ALTER TABLE public.rsc_aghevi OWNER TO rolsac;

--
-- TOC entry 180 (class 1259 OID 17969)
-- Dependencies: 5
-- Name: rsc_agrmat; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_agrmat (
    agm_codi bigint NOT NULL,
    agm_codest character varying(256),
    agm_codsec bigint
);


ALTER TABLE public.rsc_agrmat OWNER TO rolsac;

--
-- TOC entry 169 (class 1259 OID 17901)
-- Dependencies: 5
-- Name: rsc_archiv; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_archiv (
    arc_codi bigint NOT NULL,
    arc_nombre character varying(128) NOT NULL,
    arc_mime character varying(128) NOT NULL,
    arc_peso bigint NOT NULL,
    arc_datos bytea
);


ALTER TABLE public.rsc_archiv OWNER TO rolsac;

--
-- TOC entry 167 (class 1259 OID 17888)
-- Dependencies: 5
-- Name: rsc_audito; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_audito (
    aud_codi bigint NOT NULL,
    aud_usuari character varying(256),
    aud_fecha timestamp without time zone,
    aud_codhis bigint,
    aud_codope integer
);


ALTER TABLE public.rsc_audito OWNER TO rolsac;

--
-- TOC entry 174 (class 1259 OID 17929)
-- Dependencies: 5
-- Name: rsc_boleti; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_boleti (
    bol_codi bigint NOT NULL,
    bol_nombre character varying(256),
    bol_enlace character varying(512)
);


ALTER TABLE public.rsc_boleti OWNER TO rolsac;

--
-- TOC entry 239 (class 1259 OID 19124)
-- Dependencies: 5
-- Name: rsc_catdoc; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_catdoc (
    cdc_codi numeric(19,0) NOT NULL,
    cdc_orden numeric(10,0),
    cdc_admrsp numeric(2,0),
    cdc_codexd numeric(19,0)
);


ALTER TABLE public.rsc_catdoc OWNER TO rolsac;

--
-- TOC entry 168 (class 1259 OID 17893)
-- Dependencies: 5
-- Name: rsc_coment; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_coment (
    com_codi bigint NOT NULL,
    com_type character varying(64) NOT NULL,
    com_titulo character varying(256),
    com_motivo character varying(64) NOT NULL,
    com_fecha timestamp without time zone,
    com_conten character varying(4000),
    com_subsan boolean,
    com_autor character varying(256),
    com_codusu bigint,
    com_codpro bigint,
    com_codfic bigint
);


ALTER TABLE public.rsc_coment OWNER TO rolsac;

--
-- TOC entry 202 (class 1259 OID 18106)
-- Dependencies: 5
-- Name: rsc_destin; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_destin (
    des_codi bigint NOT NULL,
    des_nombre character varying(512),
    des_idremo character varying(512),
    des_endpoi character varying(512),
    des_email character varying(256)
);


ALTER TABLE public.rsc_destin OWNER TO rolsac;

--
-- TOC entry 171 (class 1259 OID 17914)
-- Dependencies: 5
-- Name: rsc_doctra; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_doctra (
    doc_codi bigint NOT NULL,
    coditra bigint,
    doc_codarc bigint,
    orden bigint,
    tipus integer,
    doc_codcdc numeric(19,0),
    doc_codexd numeric(19,0)
);


ALTER TABLE public.rsc_doctra OWNER TO rolsac;

--
-- TOC entry 182 (class 1259 OID 17979)
-- Dependencies: 5
-- Name: rsc_docume; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_docume (
    doc_codi bigint NOT NULL,
    doc_type character varying(64) NOT NULL,
    doc_codfic bigint,
    doc_codpro bigint,
    doc_codarc2 bigint,
    doc_orden bigint,
    dor_idexte bigint,
    dor_urlrem character varying(512),
    dor_codadm bigint
);


ALTER TABLE public.rsc_docume OWNER TO rolsac;

--
-- TOC entry 199 (class 1259 OID 18088)
-- Dependencies: 5
-- Name: rsc_edific; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_edific (
    edi_codi bigint NOT NULL,
    edi_type character varying(64) NOT NULL,
    edi_direcc character varying(256),
    edi_codpos character varying(64),
    edi_poblac character varying(64),
    edi_telefo character varying(64),
    edi_fax character varying(64),
    edi_email character varying(256),
    edi_fotop bigint,
    edi_fotog bigint,
    edi_plano bigint,
    edi_lat character varying(64),
    edi_lng character varying(64),
    edr_idexte bigint,
    edr_codadm bigint
);


ALTER TABLE public.rsc_edific OWNER TO rolsac;

--
-- TOC entry 175 (class 1259 OID 17937)
-- Dependencies: 5
-- Name: rsc_enlace; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_enlace (
    enl_codi bigint NOT NULL,
    enl_codfic bigint,
    enl_codpro bigint,
    enl_orden bigint
);


ALTER TABLE public.rsc_enlace OWNER TO rolsac;

--
-- TOC entry 214 (class 1259 OID 18192)
-- Dependencies: 5
-- Name: rsc_envtem; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_envtem (
    hen_cod bigint NOT NULL,
    mat_codi bigint,
    hen_html text,
    hen_activo integer
);


ALTER TABLE public.rsc_envtem OWNER TO rolsac;

--
-- TOC entry 227 (class 1259 OID 18277)
-- Dependencies: 5
-- Name: rsc_espter; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_espter (
    esp_codi bigint NOT NULL,
    esp_nivel integer NOT NULL,
    esp_coorde character varying(255),
    esp_mapa bigint,
    esp_logo bigint,
    esp_codesp bigint
);


ALTER TABLE public.rsc_espter OWNER TO rolsac;

--
-- TOC entry 185 (class 1259 OID 18000)
-- Dependencies: 5
-- Name: rsc_estadi; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_estadi (
    est_codi bigint NOT NULL,
    est_fecha timestamp without time zone,
    est_contad integer,
    est_codhis bigint
);


ALTER TABLE public.rsc_estadi OWNER TO rolsac;

--
-- TOC entry 241 (class 1259 OID 19142)
-- Dependencies: 5
-- Name: rsc_excdoc; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_excdoc (
    exd_codi numeric(19,0) NOT NULL
);


ALTER TABLE public.rsc_excdoc OWNER TO rolsac;

--
-- TOC entry 210 (class 1259 OID 18166)
-- Dependencies: 5
-- Name: rsc_famili; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_famili (
    fam_codi bigint NOT NULL
);


ALTER TABLE public.rsc_famili OWNER TO rolsac;

--
-- TOC entry 197 (class 1259 OID 18072)
-- Dependencies: 5
-- Name: rsc_ficha; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_ficha (
    fic_codi bigint NOT NULL,
    fic_type character varying(64) NOT NULL,
    fic_fecpub timestamp without time zone,
    fic_feccad timestamp without time zone,
    fic_fecact timestamp without time zone,
    fic_icono bigint,
    fic_imagen bigint,
    fic_baner bigint,
    fic_valida integer,
    fic_info character varying(4000),
    fic_respon character varying(256),
    fic_urlvid character varying(255),
    fic_urlfor character varying(255),
    fic_fortem character varying(1),
    fir_idexte bigint,
    fir_urlrem character varying(512),
    fir_codadm bigint
);


ALTER TABLE public.rsc_ficha OWNER TO rolsac;

--
-- TOC entry 219 (class 1259 OID 18227)
-- Dependencies: 5
-- Name: rsc_fichev; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_fichev (
    fih_codfic bigint NOT NULL,
    fih_codhev bigint NOT NULL
);


ALTER TABLE public.rsc_fichev OWNER TO rolsac;

--
-- TOC entry 187 (class 1259 OID 18013)
-- Dependencies: 5
-- Name: rsc_fichua; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_fichua (
    fua_codi bigint NOT NULL,
    fua_coduna bigint,
    fua_codfic bigint,
    fua_codsec bigint,
    fua_orden integer,
    fua_ordsec integer
);


ALTER TABLE public.rsc_fichua OWNER TO rolsac;

--
-- TOC entry 162 (class 1259 OID 17854)
-- Dependencies: 5
-- Name: rsc_ficmat; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_ficmat (
    fim_codfic bigint NOT NULL,
    fim_codmat bigint NOT NULL
);


ALTER TABLE public.rsc_ficmat OWNER TO rolsac;

--
-- TOC entry 189 (class 1259 OID 18023)
-- Dependencies: 5
-- Name: rsc_formul; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_formul (
    for_codi bigint NOT NULL,
    for_nombre character varying(256),
    for_url character varying(512),
    for_codarc bigint,
    for_urlman character varying(512),
    for_manual bigint,
    for_codtra bigint
);


ALTER TABLE public.rsc_formul OWNER TO rolsac;

--
-- TOC entry 190 (class 1259 OID 18031)
-- Dependencies: 5
-- Name: rsc_grpgen; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_grpgen (
    grp_cod bigint NOT NULL,
    grp_tipo integer,
    grp_stpcod bigint NOT NULL
);


ALTER TABLE public.rsc_grpgen OWNER TO rolsac;

--
-- TOC entry 170 (class 1259 OID 17909)
-- Dependencies: 5
-- Name: rsc_grpgid; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_grpgid (
    sgr_grpcod bigint NOT NULL,
    sgr_nom character varying(100),
    sgr_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_grpgid OWNER TO rolsac;

--
-- TOC entry 195 (class 1259 OID 18059)
-- Dependencies: 5
-- Name: rsc_hecvit; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_hecvit (
    hev_codi bigint NOT NULL,
    hev_orden integer,
    hev_codest character varying(255),
    hev_icono bigint,
    hev_foto bigint,
    hev_icogra bigint
);


ALTER TABLE public.rsc_hecvit OWNER TO rolsac;

--
-- TOC entry 155 (class 1259 OID 17813)
-- Dependencies: 5
-- Name: rsc_heviag; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_heviag (
    hva_codi bigint NOT NULL,
    hva_codhev bigint,
    hva_codagh bigint,
    hva_orden integer
);


ALTER TABLE public.rsc_heviag OWNER TO rolsac;

--
-- TOC entry 152 (class 1259 OID 17798)
-- Dependencies: 5
-- Name: rsc_hevipr; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_hevipr (
    hvp_codi bigint NOT NULL,
    hvp_codhev bigint,
    hvp_codpro bigint,
    hvp_orden integer
);


ALTER TABLE public.rsc_hevipr OWNER TO rolsac;

--
-- TOC entry 193 (class 1259 OID 18049)
-- Dependencies: 5
-- Name: rsc_hisenv; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_hisenv (
    hen_cod bigint NOT NULL,
    hen_fenvio timestamp without time zone,
    hen_stpcod bigint NOT NULL
);


ALTER TABLE public.rsc_hisenv OWNER TO rolsac;

--
-- TOC entry 218 (class 1259 OID 18219)
-- Dependencies: 5
-- Name: rsc_histor; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_histor (
    his_codi bigint NOT NULL,
    his_type character varying(64) NOT NULL,
    his_nombre character varying(512),
    his_coduna bigint,
    his_codnor bigint,
    his_codpro bigint,
    his_codfic bigint,
    his_codmat bigint
);


ALTER TABLE public.rsc_histor OWNER TO rolsac;

--
-- TOC entry 220 (class 1259 OID 18232)
-- Dependencies: 5
-- Name: rsc_icmate; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_icmate (
    icm_codi bigint NOT NULL,
    icm_codmat bigint,
    icm_codpec bigint,
    icm_icono bigint
);


ALTER TABLE public.rsc_icmate OWNER TO rolsac;

--
-- TOC entry 184 (class 1259 OID 17995)
-- Dependencies: 5
-- Name: rsc_icofam; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_icofam (
    icf_codi bigint NOT NULL,
    icf_codfam bigint,
    icf_codpec bigint,
    icf_icono bigint
);


ALTER TABLE public.rsc_icofam OWNER TO rolsac;

--
-- TOC entry 159 (class 1259 OID 17839)
-- Dependencies: 5
-- Name: rsc_idioma; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_idioma (
    idi_codi character varying(2) NOT NULL,
    idi_orden integer NOT NULL,
    idi_codest character varying(128),
    idi_nombre character varying(128),
    idi_traductor character varying(128)
);


ALTER TABLE public.rsc_idioma OWNER TO rolsac;

--
-- TOC entry 142 (class 1259 OID 17731)
-- Dependencies: 5
-- Name: rsc_inici; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_inici (
    ini_codi bigint NOT NULL,
    ini_codest character varying(256)
);


ALTER TABLE public.rsc_inici OWNER TO rolsac;

--
-- TOC entry 157 (class 1259 OID 17823)
-- Dependencies: 5
-- Name: rsc_lucfil; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_lucfil (
    luf_name character varying(255) NOT NULL,
    luf_modifi bigint,
    luf_length bigint,
    luf_data bytea
);


ALTER TABLE public.rsc_lucfil OWNER TO rolsac;

--
-- TOC entry 201 (class 1259 OID 18101)
-- Dependencies: 5
-- Name: rsc_matagr; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_matagr (
    mag_codi bigint NOT NULL,
    mag_codmat bigint,
    mag_codagm bigint,
    mag_orden integer
);


ALTER TABLE public.rsc_matagr OWNER TO rolsac;

--
-- TOC entry 203 (class 1259 OID 18114)
-- Dependencies: 5
-- Name: rsc_materi; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_materi (
    mat_codi bigint NOT NULL,
    mat_codhit character varying(128),
    mat_codest character varying(256),
    mat_destac boolean NOT NULL,
    mat_icono bigint,
    mat_foto bigint,
    mat_icogra bigint
);


ALTER TABLE public.rsc_materi OWNER TO rolsac;

--
-- TOC entry 165 (class 1259 OID 17872)
-- Dependencies: 5
-- Name: rsc_normat; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_normat (
    nor_codi bigint NOT NULL,
    nor_type character varying(64) NOT NULL,
    nor_numero bigint,
    nor_regist bigint,
    nor_ley character varying(256),
    nor_fecha timestamp without time zone,
    nor_fecbol timestamp without time zone,
    nor_valida integer,
    nor_codivuds character varying(255),
    nor_desccodivuds character varying(255),
    nor_codbol bigint,
    nor_codtip bigint,
    nor_coduna bigint,
    ner_idexte bigint,
    ner_urlrem character varying(512),
    ner_codadm bigint
);


ALTER TABLE public.rsc_normat OWNER TO rolsac;

--
-- TOC entry 206 (class 1259 OID 18137)
-- Dependencies: 5
-- Name: rsc_perciu; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_perciu (
    pec_codi bigint NOT NULL,
    pec_codest character varying(10),
    pec_patico character varying(256)
);


ALTER TABLE public.rsc_perciu OWNER TO rolsac;

--
-- TOC entry 213 (class 1259 OID 18184)
-- Dependencies: 5
-- Name: rsc_person; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_person (
    per_codi bigint NOT NULL,
    per_userna character varying(128),
    per_nombre character varying(256),
    per_funcio character varying(256),
    per_cargo character varying(256),
    per_email character varying(256),
    per_extpub character varying(64),
    per_numpub character varying(64),
    per_extpri character varying(64),
    per_numpri character varying(64),
    per_extmov character varying(64),
    per_nummov character varying(64),
    per_coduna bigint
);


ALTER TABLE public.rsc_person OWNER TO rolsac;

--
-- TOC entry 237 (class 1259 OID 19094)
-- Dependencies: 5
-- Name: rsc_pobfic; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_pobfic (
    pfc_codfic bigint NOT NULL,
    pfc_codpob bigint NOT NULL
);


ALTER TABLE public.rsc_pobfic OWNER TO rolsac;

--
-- TOC entry 238 (class 1259 OID 19109)
-- Dependencies: 5
-- Name: rsc_pobpro; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_pobpro (
    ppr_codpro bigint NOT NULL,
    ppr_codpob bigint NOT NULL
);


ALTER TABLE public.rsc_pobpro OWNER TO rolsac;

--
-- TOC entry 222 (class 1259 OID 18245)
-- Dependencies: 5
-- Name: rsc_proced; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_proced (
    pro_codi bigint NOT NULL,
    pro_type character varying(64) NOT NULL,
    pro_signat character varying(256),
    pro_feccad timestamp without time zone,
    pro_fecpub timestamp without time zone,
    pro_fecact timestamp without time zone,
    pro_valida integer,
    pro_tramit character varying(255),
    pro_versio bigint,
    pro_info character varying(4000),
    pro_urlext character varying(1024),
    pro_ordcon bigint,
    pro_orddir bigint,
    pro_ordser bigint,
    pro_indica character varying(1024),
    pro_ventana character varying(1024),
    pro_respon character varying(256),
    pro_taxa character varying(1024),
    pro_coduna_resol bigint,
    pro_coduna bigint,
    pro_codfam bigint,
    pro_codini bigint,
    prr_idexte bigint,
    prr_urlrem character varying(512),
    prr_codadm bigint
);


ALTER TABLE public.rsc_proced OWNER TO rolsac;

--
-- TOC entry 143 (class 1259 OID 17736)
-- Dependencies: 5
-- Name: rsc_promat; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_promat (
    prm_codmat bigint NOT NULL,
    prm_codpro bigint NOT NULL
);


ALTER TABLE public.rsc_promat OWNER TO rolsac;

--
-- TOC entry 194 (class 1259 OID 18054)
-- Dependencies: 5
-- Name: rsc_pronor; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_pronor (
    prn_codnor bigint NOT NULL,
    prn_codpro bigint NOT NULL
);


ALTER TABLE public.rsc_pronor OWNER TO rolsac;

--
-- TOC entry 156 (class 1259 OID 17818)
-- Dependencies: 5
-- Name: rsc_pubobj; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_pubobj (
    pob_codi bigint NOT NULL,
    pob_codest character varying(128),
    pob_orden integer
);


ALTER TABLE public.rsc_pubobj OWNER TO rolsac;

--
-- TOC entry 223 (class 1259 OID 18253)
-- Dependencies: 5
-- Name: rsc_screnv; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_screnv (
    sen_cod bigint NOT NULL,
    sen_fcenvio timestamp without time zone,
    sen_canal character varying(1),
    sen_asunto character varying(500),
    sen_titulo character varying(100),
    sen_html text,
    sen_tipo character varying(1),
    sen_idsecc bigint,
    sen_estado character varying(1),
    sen_fcenv timestamp without time zone,
    sen_fcalta timestamp without time zone,
    sen_ualta bigint,
    sen_fcmod timestamp without time zone,
    sen_umod bigint,
    sen_fcbaja timestamp without time zone,
    sen_stpcod bigint NOT NULL
);


ALTER TABLE public.rsc_screnv OWNER TO rolsac;

--
-- TOC entry 172 (class 1259 OID 17919)
-- Dependencies: 5
-- Name: rsc_scrgid; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_scrgid (
    sgi_sgrcod bigint NOT NULL,
    sgi_nom character varying(100),
    sgi_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_scrgid OWNER TO rolsac;

--
-- TOC entry 144 (class 1259 OID 17741)
-- Dependencies: 5
-- Name: rsc_scrgrp; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_scrgrp (
    sgr_cod bigint NOT NULL,
    sgr_ident character varying(255),
    sgr_stpcod bigint NOT NULL
);


ALTER TABLE public.rsc_scrgrp OWNER TO rolsac;

--
-- TOC entry 225 (class 1259 OID 18266)
-- Dependencies: 5
-- Name: rsc_scripc; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_scripc (
    scr_cod bigint NOT NULL,
    scr_nom character varying(100),
    scr_ape1 character varying(100),
    scr_ape2 character varying(100),
    scr_email character varying(100),
    scr_sms character varying(100),
    scr_telefono character varying(100),
    scr_tipo character varying(1),
    scr_enom character varying(200),
    scr_earea character varying(200),
    scr_edpto character varying(200),
    scr_ecargo character varying(200),
    scr_idbole character varying(1),
    scr_idaler character varying(1),
    scr_idestu character varying(1),
    scr_estado character varying(1),
    scr_origen character varying(1),
    scr_reftra character varying(100),
    scr_ualta bigint,
    scr_fcalta timestamp without time zone,
    scr_fcmod timestamp without time zone,
    scr_fcbaja timestamp without time zone,
    scr_sexo character varying(255),
    scr_anyonac integer,
    scr_paisnac integer,
    scr_provnac integer,
    scr_islanac integer,
    scr_muninac integer,
    scr_lnacotros character varying(255),
    scr_paisresid integer,
    scr_provresid integer,
    scr_islaresid integer,
    scr_muniresid integer,
    scr_idioma character varying(255),
    scr_cif character varying(255),
    scr_sedesocial character varying(255),
    scr_razonsocial character varying(255),
    scr_cp character varying(255),
    scr_contacto character varying(255),
    scr_grnom character varying(255),
    scr_resumentemas character varying(255),
    scr_motivobaja character varying(200),
    scr_estudios bigint,
    scr_profesion bigint,
    scr_stpcod bigint NOT NULL,
    scr_sgrcod bigint
);


ALTER TABLE public.rsc_scripc OWNER TO rolsac;

--
-- TOC entry 176 (class 1259 OID 17942)
-- Dependencies: 5
-- Name: rsc_scrkey; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_scrkey (
    sck_email character varying(255) NOT NULL,
    sck_idimg character varying(255),
    sck_idact character varying(255),
    sck_numenv smallint,
    sck_fcenv timestamp without time zone,
    sck_stpcod bigint NOT NULL,
    sck_cod bigint NOT NULL
);


ALTER TABLE public.rsc_scrkey OWNER TO rolsac;

--
-- TOC entry 178 (class 1259 OID 17958)
-- Dependencies: 5
-- Name: rsc_scrtem; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_scrtem (
    scr_cod bigint NOT NULL,
    mat_codi bigint
);


ALTER TABLE public.rsc_scrtem OWNER TO rolsac;

--
-- TOC entry 198 (class 1259 OID 18080)
-- Dependencies: 5
-- Name: rsc_scrtip; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_scrtip (
    stp_cod bigint NOT NULL,
    stp_coduni bigint,
    stp_ident character varying(10),
    stp_urlcab character varying(200),
    stp_titulo character varying(50),
    stp_nom character varying(200),
    stp_mail character varying(50),
    stp_pwd character varying(50),
    stp_estado integer,
    stp_horajob timestamp without time zone
);


ALTER TABLE public.rsc_scrtip OWNER TO rolsac;

--
-- TOC entry 215 (class 1259 OID 18198)
-- Dependencies: 5
-- Name: rsc_seccio; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_seccio (
    sec_codi bigint NOT NULL,
    sec_codest character varying(20),
    sec_perfil character varying(128),
    sec_orden integer,
    sec_codsec bigint
);


ALTER TABLE public.rsc_seccio OWNER TO rolsac;

--
-- TOC entry 192 (class 1259 OID 18044)
-- Dependencies: 5
-- Name: rsc_sensgr; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_sensgr (
    ssc_codsen bigint NOT NULL,
    ssc_codsgr bigint NOT NULL
);


ALTER TABLE public.rsc_sensgr OWNER TO rolsac;

--
-- TOC entry 230 (class 1259 OID 19057)
-- Dependencies: 5
-- Name: rsc_seq_all; Type: SEQUENCE; Schema: public; Owner: rolsac
--

CREATE SEQUENCE rsc_seq_all
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rsc_seq_all OWNER TO rolsac;

--
-- TOC entry 229 (class 1259 OID 19055)
-- Dependencies: 5
-- Name: rsc_seq_com; Type: SEQUENCE; Schema: public; Owner: rolsac
--

CREATE SEQUENCE rsc_seq_com
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rsc_seq_com OWNER TO rolsac;

--
-- TOC entry 235 (class 1259 OID 19067)
-- Dependencies: 5
-- Name: rsc_seqgrp; Type: SEQUENCE; Schema: public; Owner: rolsac
--

CREATE SEQUENCE rsc_seqgrp
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rsc_seqgrp OWNER TO rolsac;

--
-- TOC entry 236 (class 1259 OID 19069)
-- Dependencies: 5
-- Name: rsc_seqhis; Type: SEQUENCE; Schema: public; Owner: rolsac
--

CREATE SEQUENCE rsc_seqhis
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rsc_seqhis OWNER TO rolsac;

--
-- TOC entry 233 (class 1259 OID 19063)
-- Dependencies: 5
-- Name: rsc_seqsck; Type: SEQUENCE; Schema: public; Owner: rolsac
--

CREATE SEQUENCE rsc_seqsck
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rsc_seqsck OWNER TO rolsac;

--
-- TOC entry 231 (class 1259 OID 19059)
-- Dependencies: 5
-- Name: rsc_seqscr; Type: SEQUENCE; Schema: public; Owner: rolsac
--

CREATE SEQUENCE rsc_seqscr
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rsc_seqscr OWNER TO rolsac;

--
-- TOC entry 232 (class 1259 OID 19061)
-- Dependencies: 5
-- Name: rsc_seqsen; Type: SEQUENCE; Schema: public; Owner: rolsac
--

CREATE SEQUENCE rsc_seqsen
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rsc_seqsen OWNER TO rolsac;

--
-- TOC entry 234 (class 1259 OID 19065)
-- Dependencies: 5
-- Name: rsc_seqsgr; Type: SEQUENCE; Schema: public; Owner: rolsac
--

CREATE SEQUENCE rsc_seqsgr
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.rsc_seqsgr OWNER TO rolsac;

--
-- TOC entry 160 (class 1259 OID 17844)
-- Dependencies: 5
-- Name: rsc_taxa; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_taxa (
    tax_codi bigint NOT NULL,
    tax_codtra bigint
);


ALTER TABLE public.rsc_taxa OWNER TO rolsac;

--
-- TOC entry 147 (class 1259 OID 17762)
-- Dependencies: 5
-- Name: rsc_tipafe; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tipafe (
    tia_codi bigint NOT NULL
);


ALTER TABLE public.rsc_tipafe OWNER TO rolsac;

--
-- TOC entry 188 (class 1259 OID 18018)
-- Dependencies: 5
-- Name: rsc_tipo; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tipo (
    tip_codi bigint NOT NULL
);


ALTER TABLE public.rsc_tipo OWNER TO rolsac;

--
-- TOC entry 186 (class 1259 OID 18005)
-- Dependencies: 5
-- Name: rsc_traagh; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_traagh (
    trg_codagh bigint NOT NULL,
    trg_nombre character varying(256),
    trg_descri character varying(4000),
    trg_palcla character varying(4000),
    trg_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_traagh OWNER TO rolsac;

--
-- TOC entry 161 (class 1259 OID 17849)
-- Dependencies: 5
-- Name: rsc_traagm; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_traagm (
    tam_codagm bigint NOT NULL,
    tam_nombre character varying(256),
    tam_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_traagm OWNER TO rolsac;

--
-- TOC entry 240 (class 1259 OID 19129)
-- Dependencies: 5
-- Name: rsc_tracdc; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tracdc (
    tcd_codcdc numeric(19,0) NOT NULL,
    tcd_descri character varying(4000),
    tcd_codidi character varying(2) NOT NULL,
    tcd_nom character varying(256)
);


ALTER TABLE public.rsc_tracdc OWNER TO rolsac;

--
-- TOC entry 179 (class 1259 OID 17961)
-- Dependencies: 5
-- Name: rsc_tradoc; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tradoc (
    tdo_coddoc bigint NOT NULL,
    tdo_titulo character varying(256),
    tdo_descri character varying(4000),
    tdo_codarc bigint,
    tdo_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tradoc OWNER TO rolsac;

--
-- TOC entry 209 (class 1259 OID 18158)
-- Dependencies: 5
-- Name: rsc_tradoctra; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tradoctra (
    tdo_codtra bigint NOT NULL,
    tdo_titulo character varying(256),
    tdo_descri character varying(4000),
    tdo_codarc bigint,
    tdo_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tradoctra OWNER TO rolsac;

--
-- TOC entry 164 (class 1259 OID 17864)
-- Dependencies: 5
-- Name: rsc_traedi; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_traedi (
    ted_codedi bigint NOT NULL,
    ted_descri character varying(4000),
    ted_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_traedi OWNER TO rolsac;

--
-- TOC entry 166 (class 1259 OID 17880)
-- Dependencies: 5
-- Name: rsc_traenl; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_traenl (
    ten_codenl bigint NOT NULL,
    ten_titulo character varying(256),
    ten_enlace character varying(512),
    ten_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_traenl OWNER TO rolsac;

--
-- TOC entry 181 (class 1259 OID 17974)
-- Dependencies: 5
-- Name: rsc_traesp; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_traesp (
    tes_codesp bigint NOT NULL,
    tes_nombre character varying(256),
    tes_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_traesp OWNER TO rolsac;

--
-- TOC entry 242 (class 1259 OID 19152)
-- Dependencies: 5
-- Name: rsc_traexd; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_traexd (
    ted_codexd numeric(19,0) NOT NULL,
    ted_nom character varying(256),
    ted_descri character varying(4000),
    ted_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_traexd OWNER TO rolsac;

--
-- TOC entry 228 (class 1259 OID 18282)
-- Dependencies: 5
-- Name: rsc_trafam; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_trafam (
    tfa_codfam bigint NOT NULL,
    tfa_nombre character varying(256),
    tfa_descri character varying(4000),
    tfa_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_trafam OWNER TO rolsac;

--
-- TOC entry 207 (class 1259 OID 18142)
-- Dependencies: 5
-- Name: rsc_trafic; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_trafic (
    tfi_codfic bigint NOT NULL,
    tfi_titulo character varying(256),
    tfi_url character varying(512),
    tfi_desabr text,
    tfi_descri text,
    tfi_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_trafic OWNER TO rolsac;

--
-- TOC entry 211 (class 1259 OID 18171)
-- Dependencies: 5
-- Name: rsc_trahev; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_trahev (
    the_codhev bigint NOT NULL,
    the_nombre character varying(256),
    the_descri character varying(4000),
    the_palcla character varying(4000),
    the_coddis bigint,
    the_normat bigint,
    the_conten bigint,
    the_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_trahev OWNER TO rolsac;

--
-- TOC entry 196 (class 1259 OID 18064)
-- Dependencies: 5
-- Name: rsc_traini; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_traini (
    tin_codini bigint NOT NULL,
    tin_nombre character varying(256),
    tin_descri character varying(4000),
    tin_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_traini OWNER TO rolsac;

--
-- TOC entry 204 (class 1259 OID 18119)
-- Dependencies: 5
-- Name: rsc_tramat; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tramat (
    tma_codmat bigint NOT NULL,
    tma_nombre character varying(256),
    tma_descri character varying(4000),
    tma_palcla character varying(4000),
    tma_coddis bigint,
    tma_normat bigint,
    tma_conten bigint,
    tma_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tramat OWNER TO rolsac;

--
-- TOC entry 217 (class 1259 OID 18211)
-- Dependencies: 5
-- Name: rsc_tramit; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tramit (
    tra_codi bigint NOT NULL,
    tra_type character varying(64) NOT NULL,
    tra_fase integer,
    tra_orden bigint,
    tra_codpro bigint,
    tra_codivuds character varying(255),
    tra_desccodivuds character varying(255),
    tra_valida bigint,
    tra_datcadu timestamp without time zone,
    tra_datpubl timestamp without time zone,
    tra_datactu timestamp without time zone,
    tra_idtramtel character varying(255),
    tra_versio integer,
    tra_urlexte character varying(255),
    tra_datactuvuds character varying(255),
    tra_orgcomp bigint,
    trr_idexte bigint,
    trr_urlrem character varying(512),
    trr_codadm bigint,
    tra_datinici date,
    tra_dattancament date
);


ALTER TABLE public.rsc_tramit OWNER TO rolsac;

--
-- TOC entry 146 (class 1259 OID 17754)
-- Dependencies: 5
-- Name: rsc_tranoe; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tranoe (
    tne_codnor bigint NOT NULL,
    tne_seccio character varying(256),
    tne_aparta character varying(256),
    tne_pagini integer,
    tne_pagfin integer,
    tne_titulo character varying(1024),
    tne_enlace character varying(512),
    tne_respon character varying(512),
    tne_codarc bigint,
    tne_observ character varying(4000),
    tne_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tranoe OWNER TO rolsac;

--
-- TOC entry 208 (class 1259 OID 18150)
-- Dependencies: 5
-- Name: rsc_tranol; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tranol (
    tnl_codnor bigint NOT NULL,
    tnl_seccio character varying(256),
    tnl_aparta character varying(256),
    tnl_pagini integer,
    tnl_pagfin integer,
    tnl_titulo character varying(512),
    tnl_enlace character varying(512),
    tnl_codarc bigint,
    tnl_observ character varying(4000),
    tnl_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tranol OWNER TO rolsac;

--
-- TOC entry 243 (class 1259 OID 19175)
-- Dependencies: 2012 5
-- Name: rsc_tranor; Type: VIEW; Schema: public; Owner: rolsac
--

CREATE VIEW rsc_tranor AS
    SELECT rsc_tranoe.tne_codnor AS tno_codnor, rsc_tranoe.tne_seccio AS tno_seccio, rsc_tranoe.tne_aparta AS tno_aparta, rsc_tranoe.tne_pagini AS tno_pagini, rsc_tranoe.tne_pagfin AS tno_pagfin, rsc_tranoe.tne_titulo AS tno_titulo, rsc_tranoe.tne_enlace AS tno_enlace, rsc_tranoe.tne_respon AS tno_respon, rsc_tranoe.tne_codarc AS tno_codarc, rsc_tranoe.tne_observ AS tno_observ, rsc_tranoe.tne_codidi AS tno_codidi FROM rsc_tranoe UNION SELECT rsc_tranol.tnl_codnor AS tno_codnor, rsc_tranol.tnl_seccio AS tno_seccio, rsc_tranol.tnl_aparta AS tno_aparta, rsc_tranol.tnl_pagini AS tno_pagini, rsc_tranol.tnl_pagfin AS tno_pagfin, rsc_tranol.tnl_titulo AS tno_titulo, rsc_tranol.tnl_enlace AS tno_enlace, NULL::unknown AS tno_respon, rsc_tranol.tnl_codarc AS tno_codarc, rsc_tranol.tnl_observ AS tno_observ, rsc_tranol.tnl_codidi AS tno_codidi FROM rsc_tranol;


ALTER TABLE public.rsc_tranor OWNER TO rolsac;

--
-- TOC entry 158 (class 1259 OID 17831)
-- Dependencies: 5
-- Name: rsc_trapec; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_trapec (
    tpe_codpec bigint NOT NULL,
    tpe_nombre character varying(256),
    tpe_descri character varying(4000),
    tpe_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_trapec OWNER TO rolsac;

--
-- TOC entry 145 (class 1259 OID 17746)
-- Dependencies: 5
-- Name: rsc_trapob; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_trapob (
    trp_codpob bigint NOT NULL,
    trp_titulo character varying(256),
    trp_descri character varying(4000),
    trp_palcla character varying(4000),
    trp_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_trapob OWNER TO rolsac;

--
-- TOC entry 148 (class 1259 OID 17767)
-- Dependencies: 5
-- Name: rsc_trapro; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_trapro (
    tpr_codpro bigint NOT NULL,
    tpr_nombre character varying(256),
    tpr_resolucion text,
    tpr_notificacion text,
    tpr_result text,
    tpr_resume text,
    tpr_destin text,
    tpr_requis text,
    tpr_plazos text,
    tpr_silen text,
    tpr_recurs text,
    tpr_observ text,
    tpr_lugar text,
    tpr_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_trapro OWNER TO rolsac;

--
-- TOC entry 183 (class 1259 OID 17987)
-- Dependencies: 5
-- Name: rsc_trasec; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_trasec (
    tse_codsec bigint NOT NULL,
    tse_titulo character varying(256),
    tse_descri character varying(4000),
    tse_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_trasec OWNER TO rolsac;

--
-- TOC entry 200 (class 1259 OID 18096)
-- Dependencies: 5
-- Name: rsc_tratam; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tratam (
    trt_codi bigint NOT NULL,
    trt_codest character varying(128)
);


ALTER TABLE public.rsc_tratam OWNER TO rolsac;

--
-- TOC entry 221 (class 1259 OID 18237)
-- Dependencies: 5
-- Name: rsc_tratax; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tratax (
    ttax_codi bigint NOT NULL,
    tax_id character varying(256),
    descri character varying(4000),
    formpag character varying(4000),
    cod_idi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tratax OWNER TO rolsac;

--
-- TOC entry 153 (class 1259 OID 17803)
-- Dependencies: 5
-- Name: rsc_tratia; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tratia (
    tta_codtia bigint NOT NULL,
    tta_nombre character varying(256),
    tta_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tratia OWNER TO rolsac;

--
-- TOC entry 154 (class 1259 OID 17808)
-- Dependencies: 5
-- Name: rsc_tratip; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tratip (
    tti_codtip bigint NOT NULL,
    tti_nombre character varying(128),
    tti_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tratip OWNER TO rolsac;

--
-- TOC entry 191 (class 1259 OID 18036)
-- Dependencies: 5
-- Name: rsc_tratra; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tratra (
    ttr_codttr bigint NOT NULL,
    ttr_nombre character varying(256),
    ttr_descri character varying(4000),
    ttr_docume character varying(4000),
    ttr_requi character varying(4000),
    ttr_plazos character varying(512),
    ttr_lugar character varying(512),
    ttr_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tratra OWNER TO rolsac;

--
-- TOC entry 177 (class 1259 OID 17950)
-- Dependencies: 5
-- Name: rsc_tratrt; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_tratrt (
    ttt_codtrt bigint NOT NULL,
    ttt_tipo character varying(256),
    ttt_cargom character varying(256),
    ttt_cargof character varying(256),
    ttt_tratam character varying(256),
    ttt_trataf character varying(256),
    ttt_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_tratrt OWNER TO rolsac;

--
-- TOC entry 151 (class 1259 OID 17790)
-- Dependencies: 5
-- Name: rsc_trauna; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_trauna (
    tun_coduna bigint NOT NULL,
    tun_nombre character varying(256),
    tun_presen character varying(4000),
    tun_abrevi character varying(64),
    tun_url character varying(256),
    tun_cvresp text,
    tun_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_trauna OWNER TO rolsac;

--
-- TOC entry 212 (class 1259 OID 18179)
-- Dependencies: 5
-- Name: rsc_traunm; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_traunm (
    trn_codunm bigint NOT NULL,
    trn_urlum character varying(256),
    trn_codidi character varying(2) NOT NULL
);


ALTER TABLE public.rsc_traunm OWNER TO rolsac;

--
-- TOC entry 224 (class 1259 OID 18261)
-- Dependencies: 5
-- Name: rsc_unaedi; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_unaedi (
    une_codedi bigint NOT NULL,
    une_coduna bigint NOT NULL
);


ALTER TABLE public.rsc_unaedi OWNER TO rolsac;

--
-- TOC entry 150 (class 1259 OID 17785)
-- Dependencies: 5
-- Name: rsc_unamat; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_unamat (
    unm_codi bigint NOT NULL,
    unm_unaprn character varying(1),
    unm_codmat bigint,
    unm_coduna bigint
);


ALTER TABLE public.rsc_unamat OWNER TO rolsac;

--
-- TOC entry 163 (class 1259 OID 17859)
-- Dependencies: 5
-- Name: rsc_unausu; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_unausu (
    unu_coduna bigint NOT NULL,
    unu_codusu bigint NOT NULL
);


ALTER TABLE public.rsc_unausu OWNER TO rolsac;

--
-- TOC entry 216 (class 1259 OID 18203)
-- Dependencies: 5
-- Name: rsc_uniadm; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_uniadm (
    una_codi bigint NOT NULL,
    una_type character varying(64) NOT NULL,
    una_buskey character varying(41),
    una_clvhit character varying(128),
    una_domini character varying(256),
    una_orden bigint,
    una_valida integer,
    una_respon character varying(256),
    una_telefo character varying(64),
    una_fax character varying(64),
    una_email character varying(256),
    una_sexres integer,
    una_codest character varying(256),
    una_nfoto1 integer,
    una_nfoto2 integer,
    una_nfoto3 integer,
    una_nfoto4 integer,
    una_fotop bigint,
    una_fotog bigint,
    una_logoh bigint,
    una_logov bigint,
    una_logos bigint,
    una_logot bigint,
    una_coduna bigint,
    una_codesp bigint,
    una_codtrt bigint,
    unr_idexte bigint,
    unr_urlrem character varying(512),
    una_emailr character varying(256),
    unr_codadm bigint
);


ALTER TABLE public.rsc_uniadm OWNER TO rolsac;

--
-- TOC entry 205 (class 1259 OID 18127)
-- Dependencies: 5
-- Name: rsc_usuari; Type: TABLE; Schema: public; Owner: rolsac; Tablespace: 
--

CREATE TABLE rsc_usuari (
    usu_codi bigint NOT NULL,
    usu_userna character varying(128) NOT NULL,
    usu_passwo character varying(128),
    usu_nombre character varying(256),
    usu_observ character varying(4000),
    usu_perfil character varying(64),
    usu_email character varying(256)
);


ALTER TABLE public.rsc_usuari OWNER TO rolsac;

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


--
-- TOC entry 2219 (class 2606 OID 17784)
-- Dependencies: 149 149
-- Name: rsc_admrem_adm_idremo_key; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_admrem
    ADD CONSTRAINT rsc_admrem_adm_idremo_key UNIQUE (adm_idremo);


--
-- TOC entry 2221 (class 2606 OID 17782)
-- Dependencies: 149 149
-- Name: rsc_admrem_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_admrem
    ADD CONSTRAINT rsc_admrem_pkey PRIMARY KEY (adm_codi);


--
-- TOC entry 2269 (class 2606 OID 17928)
-- Dependencies: 173 173
-- Name: rsc_aghevi_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_aghevi
    ADD CONSTRAINT rsc_aghevi_pkey PRIMARY KEY (agh_codi);


--
-- TOC entry 2281 (class 2606 OID 17973)
-- Dependencies: 180 180
-- Name: rsc_agrmat_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_agrmat
    ADD CONSTRAINT rsc_agrmat_pkey PRIMARY KEY (agm_codi);


--
-- TOC entry 2261 (class 2606 OID 17908)
-- Dependencies: 169 169
-- Name: rsc_archiv_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_archiv
    ADD CONSTRAINT rsc_archiv_pkey PRIMARY KEY (arc_codi);


--
-- TOC entry 2257 (class 2606 OID 17892)
-- Dependencies: 167 167
-- Name: rsc_audito_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_audito
    ADD CONSTRAINT rsc_audito_pkey PRIMARY KEY (aud_codi);


--
-- TOC entry 2271 (class 2606 OID 17936)
-- Dependencies: 174 174
-- Name: rsc_boleti_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_boleti
    ADD CONSTRAINT rsc_boleti_pkey PRIMARY KEY (bol_codi);


--
-- TOC entry 2381 (class 2606 OID 19128)
-- Dependencies: 239 239
-- Name: rsc_catdoc_pk; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_catdoc
    ADD CONSTRAINT rsc_catdoc_pk PRIMARY KEY (cdc_codi);


--
-- TOC entry 2259 (class 2606 OID 17900)
-- Dependencies: 168 168
-- Name: rsc_coment_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_coment
    ADD CONSTRAINT rsc_coment_pkey PRIMARY KEY (com_codi);


--
-- TOC entry 2325 (class 2606 OID 18113)
-- Dependencies: 202 202
-- Name: rsc_destin_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_destin
    ADD CONSTRAINT rsc_destin_pkey PRIMARY KEY (des_codi);


--
-- TOC entry 2265 (class 2606 OID 17918)
-- Dependencies: 171 171
-- Name: rsc_doctra_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_doctra
    ADD CONSTRAINT rsc_doctra_pkey PRIMARY KEY (doc_codi);


--
-- TOC entry 2285 (class 2606 OID 17986)
-- Dependencies: 182 182
-- Name: rsc_docume_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_docume
    ADD CONSTRAINT rsc_docume_pkey PRIMARY KEY (doc_codi);


--
-- TOC entry 2319 (class 2606 OID 18095)
-- Dependencies: 199 199
-- Name: rsc_edific_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_edific
    ADD CONSTRAINT rsc_edific_pkey PRIMARY KEY (edi_codi);


--
-- TOC entry 2273 (class 2606 OID 17941)
-- Dependencies: 175 175
-- Name: rsc_enlace_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_enlace
    ADD CONSTRAINT rsc_enlace_pkey PRIMARY KEY (enl_codi);


--
-- TOC entry 2373 (class 2606 OID 18281)
-- Dependencies: 227 227
-- Name: rsc_espter_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_espter
    ADD CONSTRAINT rsc_espter_pkey PRIMARY KEY (esp_codi);


--
-- TOC entry 2291 (class 2606 OID 18004)
-- Dependencies: 185 185
-- Name: rsc_estadi_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_estadi
    ADD CONSTRAINT rsc_estadi_pkey PRIMARY KEY (est_codi);


--
-- TOC entry 2385 (class 2606 OID 19146)
-- Dependencies: 241 241
-- Name: rsc_excdoc_pk; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_excdoc
    ADD CONSTRAINT rsc_excdoc_pk PRIMARY KEY (exd_codi);


--
-- TOC entry 2343 (class 2606 OID 18170)
-- Dependencies: 210 210
-- Name: rsc_famili_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_famili
    ADD CONSTRAINT rsc_famili_pkey PRIMARY KEY (fam_codi);


--
-- TOC entry 2315 (class 2606 OID 18079)
-- Dependencies: 197 197
-- Name: rsc_ficha_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_ficha
    ADD CONSTRAINT rsc_ficha_pkey PRIMARY KEY (fic_codi);


--
-- TOC entry 2359 (class 2606 OID 18231)
-- Dependencies: 219 219 219
-- Name: rsc_fichev_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_fichev
    ADD CONSTRAINT rsc_fichev_pkey PRIMARY KEY (fih_codfic, fih_codhev);


--
-- TOC entry 2295 (class 2606 OID 18017)
-- Dependencies: 187 187
-- Name: rsc_fichua_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_fichua
    ADD CONSTRAINT rsc_fichua_pkey PRIMARY KEY (fua_codi);


--
-- TOC entry 2247 (class 2606 OID 17858)
-- Dependencies: 162 162 162
-- Name: rsc_ficmat_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_ficmat
    ADD CONSTRAINT rsc_ficmat_pkey PRIMARY KEY (fim_codfic, fim_codmat);


--
-- TOC entry 2299 (class 2606 OID 18030)
-- Dependencies: 189 189
-- Name: rsc_formul_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_formul
    ADD CONSTRAINT rsc_formul_pkey PRIMARY KEY (for_codi);


--
-- TOC entry 2301 (class 2606 OID 18035)
-- Dependencies: 190 190
-- Name: rsc_grpgen_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_grpgen
    ADD CONSTRAINT rsc_grpgen_pkey PRIMARY KEY (grp_cod);


--
-- TOC entry 2263 (class 2606 OID 17913)
-- Dependencies: 170 170 170
-- Name: rsc_grpgid_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_grpgid
    ADD CONSTRAINT rsc_grpgid_pkey PRIMARY KEY (sgr_grpcod, sgr_codidi);


--
-- TOC entry 2311 (class 2606 OID 18063)
-- Dependencies: 195 195
-- Name: rsc_hecvit_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_hecvit
    ADD CONSTRAINT rsc_hecvit_pkey PRIMARY KEY (hev_codi);


--
-- TOC entry 2233 (class 2606 OID 17817)
-- Dependencies: 155 155
-- Name: rsc_heviag_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_heviag
    ADD CONSTRAINT rsc_heviag_pkey PRIMARY KEY (hva_codi);


--
-- TOC entry 2227 (class 2606 OID 17802)
-- Dependencies: 152 152
-- Name: rsc_hevipr_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_hevipr
    ADD CONSTRAINT rsc_hevipr_pkey PRIMARY KEY (hvp_codi);


--
-- TOC entry 2307 (class 2606 OID 18053)
-- Dependencies: 193 193
-- Name: rsc_hisenv_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_hisenv
    ADD CONSTRAINT rsc_hisenv_pkey PRIMARY KEY (hen_cod);


--
-- TOC entry 2357 (class 2606 OID 18226)
-- Dependencies: 218 218
-- Name: rsc_histor_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_histor
    ADD CONSTRAINT rsc_histor_pkey PRIMARY KEY (his_codi);


--
-- TOC entry 2361 (class 2606 OID 18236)
-- Dependencies: 220 220
-- Name: rsc_icmate_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_icmate
    ADD CONSTRAINT rsc_icmate_pkey PRIMARY KEY (icm_codi);


--
-- TOC entry 2289 (class 2606 OID 17999)
-- Dependencies: 184 184
-- Name: rsc_icofam_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_icofam
    ADD CONSTRAINT rsc_icofam_pkey PRIMARY KEY (icf_codi);


--
-- TOC entry 2241 (class 2606 OID 17843)
-- Dependencies: 159 159
-- Name: rsc_idioma_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_idioma
    ADD CONSTRAINT rsc_idioma_pkey PRIMARY KEY (idi_codi);


--
-- TOC entry 2205 (class 2606 OID 17735)
-- Dependencies: 142 142
-- Name: rsc_inici_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_inici
    ADD CONSTRAINT rsc_inici_pkey PRIMARY KEY (ini_codi);


--
-- TOC entry 2237 (class 2606 OID 17830)
-- Dependencies: 157 157
-- Name: rsc_lucfil_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_lucfil
    ADD CONSTRAINT rsc_lucfil_pkey PRIMARY KEY (luf_name);


--
-- TOC entry 2323 (class 2606 OID 18105)
-- Dependencies: 201 201
-- Name: rsc_matagr_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_matagr
    ADD CONSTRAINT rsc_matagr_pkey PRIMARY KEY (mag_codi);


--
-- TOC entry 2327 (class 2606 OID 18118)
-- Dependencies: 203 203
-- Name: rsc_materi_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_materi
    ADD CONSTRAINT rsc_materi_pkey PRIMARY KEY (mat_codi);


--
-- TOC entry 2253 (class 2606 OID 17879)
-- Dependencies: 165 165
-- Name: rsc_normat_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_normat
    ADD CONSTRAINT rsc_normat_pkey PRIMARY KEY (nor_codi);


--
-- TOC entry 2335 (class 2606 OID 18141)
-- Dependencies: 206 206
-- Name: rsc_perciu_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_perciu
    ADD CONSTRAINT rsc_perciu_pkey PRIMARY KEY (pec_codi);


--
-- TOC entry 2349 (class 2606 OID 18191)
-- Dependencies: 213 213
-- Name: rsc_person_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_person
    ADD CONSTRAINT rsc_person_pkey PRIMARY KEY (per_codi);


--
-- TOC entry 2377 (class 2606 OID 19098)
-- Dependencies: 237 237 237
-- Name: rsc_pobfic_pk; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_pobfic
    ADD CONSTRAINT rsc_pobfic_pk PRIMARY KEY (pfc_codfic, pfc_codpob);


--
-- TOC entry 2379 (class 2606 OID 19113)
-- Dependencies: 238 238 238
-- Name: rsc_pobpro_pk; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_pobpro
    ADD CONSTRAINT rsc_pobpro_pk PRIMARY KEY (ppr_codpro, ppr_codpob);


--
-- TOC entry 2365 (class 2606 OID 18252)
-- Dependencies: 222 222
-- Name: rsc_proced_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_proced
    ADD CONSTRAINT rsc_proced_pkey PRIMARY KEY (pro_codi);


--
-- TOC entry 2207 (class 2606 OID 17740)
-- Dependencies: 143 143 143
-- Name: rsc_promat_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_promat
    ADD CONSTRAINT rsc_promat_pkey PRIMARY KEY (prm_codpro, prm_codmat);


--
-- TOC entry 2309 (class 2606 OID 18058)
-- Dependencies: 194 194 194
-- Name: rsc_pronor_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_pronor
    ADD CONSTRAINT rsc_pronor_pkey PRIMARY KEY (prn_codpro, prn_codnor);


--
-- TOC entry 2235 (class 2606 OID 17822)
-- Dependencies: 156 156
-- Name: rsc_pubobj_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_pubobj
    ADD CONSTRAINT rsc_pubobj_pkey PRIMARY KEY (pob_codi);


--
-- TOC entry 2367 (class 2606 OID 18260)
-- Dependencies: 223 223
-- Name: rsc_screnv_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_screnv
    ADD CONSTRAINT rsc_screnv_pkey PRIMARY KEY (sen_cod);


--
-- TOC entry 2267 (class 2606 OID 17923)
-- Dependencies: 172 172 172
-- Name: rsc_scrgid_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_scrgid
    ADD CONSTRAINT rsc_scrgid_pkey PRIMARY KEY (sgi_sgrcod, sgi_codidi);


--
-- TOC entry 2209 (class 2606 OID 17745)
-- Dependencies: 144 144
-- Name: rsc_scrgrp_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_scrgrp
    ADD CONSTRAINT rsc_scrgrp_pkey PRIMARY KEY (sgr_cod);


--
-- TOC entry 2371 (class 2606 OID 18273)
-- Dependencies: 225 225
-- Name: rsc_scripc_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_scripc
    ADD CONSTRAINT rsc_scripc_pkey PRIMARY KEY (scr_cod);


--
-- TOC entry 2275 (class 2606 OID 17949)
-- Dependencies: 176 176
-- Name: rsc_scrkey_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_scrkey
    ADD CONSTRAINT rsc_scrkey_pkey PRIMARY KEY (sck_cod);


--
-- TOC entry 2317 (class 2606 OID 18087)
-- Dependencies: 198 198
-- Name: rsc_scrtip_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_scrtip
    ADD CONSTRAINT rsc_scrtip_pkey PRIMARY KEY (stp_cod);


--
-- TOC entry 2351 (class 2606 OID 18202)
-- Dependencies: 215 215
-- Name: rsc_seccio_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_seccio
    ADD CONSTRAINT rsc_seccio_pkey PRIMARY KEY (sec_codi);


--
-- TOC entry 2305 (class 2606 OID 18048)
-- Dependencies: 192 192 192
-- Name: rsc_sensgr_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_sensgr
    ADD CONSTRAINT rsc_sensgr_pkey PRIMARY KEY (ssc_codsen, ssc_codsgr);


--
-- TOC entry 2243 (class 2606 OID 17848)
-- Dependencies: 160 160
-- Name: rsc_taxa_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_taxa
    ADD CONSTRAINT rsc_taxa_pkey PRIMARY KEY (tax_codi);


--
-- TOC entry 2215 (class 2606 OID 17766)
-- Dependencies: 147 147
-- Name: rsc_tipafe_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tipafe
    ADD CONSTRAINT rsc_tipafe_pkey PRIMARY KEY (tia_codi);


--
-- TOC entry 2297 (class 2606 OID 18022)
-- Dependencies: 188 188
-- Name: rsc_tipo_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tipo
    ADD CONSTRAINT rsc_tipo_pkey PRIMARY KEY (tip_codi);


--
-- TOC entry 2293 (class 2606 OID 18012)
-- Dependencies: 186 186 186
-- Name: rsc_traagh_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_traagh
    ADD CONSTRAINT rsc_traagh_pkey PRIMARY KEY (trg_codagh, trg_codidi);


--
-- TOC entry 2245 (class 2606 OID 17853)
-- Dependencies: 161 161 161
-- Name: rsc_traagm_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_traagm
    ADD CONSTRAINT rsc_traagm_pkey PRIMARY KEY (tam_codagm, tam_codidi);


--
-- TOC entry 2383 (class 2606 OID 19136)
-- Dependencies: 240 240 240
-- Name: rsc_tracdc_pk; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tracdc
    ADD CONSTRAINT rsc_tracdc_pk PRIMARY KEY (tcd_codcdc, tcd_codidi);


--
-- TOC entry 2279 (class 2606 OID 17968)
-- Dependencies: 179 179 179
-- Name: rsc_tradoc_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tradoc
    ADD CONSTRAINT rsc_tradoc_pkey PRIMARY KEY (tdo_coddoc, tdo_codidi);


--
-- TOC entry 2341 (class 2606 OID 18165)
-- Dependencies: 209 209 209
-- Name: rsc_tradoctra_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tradoctra
    ADD CONSTRAINT rsc_tradoctra_pkey PRIMARY KEY (tdo_codtra, tdo_codidi);


--
-- TOC entry 2251 (class 2606 OID 17871)
-- Dependencies: 164 164 164
-- Name: rsc_traedi_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_traedi
    ADD CONSTRAINT rsc_traedi_pkey PRIMARY KEY (ted_codedi, ted_codidi);


--
-- TOC entry 2255 (class 2606 OID 17887)
-- Dependencies: 166 166 166
-- Name: rsc_traenl_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_traenl
    ADD CONSTRAINT rsc_traenl_pkey PRIMARY KEY (ten_codenl, ten_codidi);


--
-- TOC entry 2283 (class 2606 OID 17978)
-- Dependencies: 181 181 181
-- Name: rsc_traesp_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_traesp
    ADD CONSTRAINT rsc_traesp_pkey PRIMARY KEY (tes_codesp, tes_codidi);


--
-- TOC entry 2387 (class 2606 OID 19159)
-- Dependencies: 242 242 242
-- Name: rsc_traexd_pk; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_traexd
    ADD CONSTRAINT rsc_traexd_pk PRIMARY KEY (ted_codexd, ted_codidi);


--
-- TOC entry 2375 (class 2606 OID 18289)
-- Dependencies: 228 228 228
-- Name: rsc_trafam_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_trafam
    ADD CONSTRAINT rsc_trafam_pkey PRIMARY KEY (tfa_codfam, tfa_codidi);


--
-- TOC entry 2337 (class 2606 OID 18149)
-- Dependencies: 207 207 207
-- Name: rsc_trafic_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_trafic
    ADD CONSTRAINT rsc_trafic_pkey PRIMARY KEY (tfi_codfic, tfi_codidi);


--
-- TOC entry 2345 (class 2606 OID 18178)
-- Dependencies: 211 211 211
-- Name: rsc_trahev_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_trahev
    ADD CONSTRAINT rsc_trahev_pkey PRIMARY KEY (the_codhev, the_codidi);


--
-- TOC entry 2313 (class 2606 OID 18071)
-- Dependencies: 196 196 196
-- Name: rsc_traini_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_traini
    ADD CONSTRAINT rsc_traini_pkey PRIMARY KEY (tin_codini, tin_codidi);


--
-- TOC entry 2329 (class 2606 OID 18126)
-- Dependencies: 204 204 204
-- Name: rsc_tramat_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tramat
    ADD CONSTRAINT rsc_tramat_pkey PRIMARY KEY (tma_codmat, tma_codidi);


--
-- TOC entry 2355 (class 2606 OID 18218)
-- Dependencies: 217 217
-- Name: rsc_tramit_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tramit
    ADD CONSTRAINT rsc_tramit_pkey PRIMARY KEY (tra_codi);


--
-- TOC entry 2213 (class 2606 OID 17761)
-- Dependencies: 146 146 146
-- Name: rsc_tranoe_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tranoe
    ADD CONSTRAINT rsc_tranoe_pkey PRIMARY KEY (tne_codnor, tne_codidi);


--
-- TOC entry 2339 (class 2606 OID 18157)
-- Dependencies: 208 208 208
-- Name: rsc_tranol_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tranol
    ADD CONSTRAINT rsc_tranol_pkey PRIMARY KEY (tnl_codnor, tnl_codidi);


--
-- TOC entry 2239 (class 2606 OID 17838)
-- Dependencies: 158 158 158
-- Name: rsc_trapec_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_trapec
    ADD CONSTRAINT rsc_trapec_pkey PRIMARY KEY (tpe_codpec, tpe_codidi);


--
-- TOC entry 2211 (class 2606 OID 17753)
-- Dependencies: 145 145 145
-- Name: rsc_trapob_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_trapob
    ADD CONSTRAINT rsc_trapob_pkey PRIMARY KEY (trp_codpob, trp_codidi);


--
-- TOC entry 2217 (class 2606 OID 17774)
-- Dependencies: 148 148 148
-- Name: rsc_trapro_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_trapro
    ADD CONSTRAINT rsc_trapro_pkey PRIMARY KEY (tpr_codpro, tpr_codidi);


--
-- TOC entry 2287 (class 2606 OID 17994)
-- Dependencies: 183 183 183
-- Name: rsc_trasec_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_trasec
    ADD CONSTRAINT rsc_trasec_pkey PRIMARY KEY (tse_codsec, tse_codidi);


--
-- TOC entry 2321 (class 2606 OID 18100)
-- Dependencies: 200 200
-- Name: rsc_tratam_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tratam
    ADD CONSTRAINT rsc_tratam_pkey PRIMARY KEY (trt_codi);


--
-- TOC entry 2363 (class 2606 OID 18244)
-- Dependencies: 221 221 221
-- Name: rsc_tratax_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tratax
    ADD CONSTRAINT rsc_tratax_pkey PRIMARY KEY (ttax_codi, cod_idi);


--
-- TOC entry 2229 (class 2606 OID 17807)
-- Dependencies: 153 153 153
-- Name: rsc_tratia_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tratia
    ADD CONSTRAINT rsc_tratia_pkey PRIMARY KEY (tta_codtia, tta_codidi);


--
-- TOC entry 2231 (class 2606 OID 17812)
-- Dependencies: 154 154 154
-- Name: rsc_tratip_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tratip
    ADD CONSTRAINT rsc_tratip_pkey PRIMARY KEY (tti_codtip, tti_codidi);


--
-- TOC entry 2303 (class 2606 OID 18043)
-- Dependencies: 191 191 191
-- Name: rsc_tratra_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tratra
    ADD CONSTRAINT rsc_tratra_pkey PRIMARY KEY (ttr_codttr, ttr_codidi);


--
-- TOC entry 2277 (class 2606 OID 17957)
-- Dependencies: 177 177 177
-- Name: rsc_tratrt_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_tratrt
    ADD CONSTRAINT rsc_tratrt_pkey PRIMARY KEY (ttt_codtrt, ttt_codidi);


--
-- TOC entry 2225 (class 2606 OID 17797)
-- Dependencies: 151 151 151
-- Name: rsc_trauna_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_trauna
    ADD CONSTRAINT rsc_trauna_pkey PRIMARY KEY (tun_coduna, tun_codidi);


--
-- TOC entry 2347 (class 2606 OID 18183)
-- Dependencies: 212 212 212
-- Name: rsc_traunm_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_traunm
    ADD CONSTRAINT rsc_traunm_pkey PRIMARY KEY (trn_codunm, trn_codidi);


--
-- TOC entry 2369 (class 2606 OID 18265)
-- Dependencies: 224 224 224
-- Name: rsc_unaedi_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_unaedi
    ADD CONSTRAINT rsc_unaedi_pkey PRIMARY KEY (une_codedi, une_coduna);


--
-- TOC entry 2223 (class 2606 OID 17789)
-- Dependencies: 150 150
-- Name: rsc_unamat_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_unamat
    ADD CONSTRAINT rsc_unamat_pkey PRIMARY KEY (unm_codi);


--
-- TOC entry 2249 (class 2606 OID 17863)
-- Dependencies: 163 163 163
-- Name: rsc_unausu_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_unausu
    ADD CONSTRAINT rsc_unausu_pkey PRIMARY KEY (unu_codusu, unu_coduna);


--
-- TOC entry 2353 (class 2606 OID 18210)
-- Dependencies: 216 216
-- Name: rsc_uniadm_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_uniadm_pkey PRIMARY KEY (una_codi);


--
-- TOC entry 2331 (class 2606 OID 18134)
-- Dependencies: 205 205
-- Name: rsc_usuari_pkey; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_usuari
    ADD CONSTRAINT rsc_usuari_pkey PRIMARY KEY (usu_codi);


--
-- TOC entry 2333 (class 2606 OID 18136)
-- Dependencies: 205 205
-- Name: rsc_usuari_usu_userna_key; Type: CONSTRAINT; Schema: public; Owner: rolsac; Tablespace: 
--

ALTER TABLE ONLY rsc_usuari
    ADD CONSTRAINT rsc_usuari_usu_userna_key UNIQUE (usu_userna);
    
ALTER TABLE ONLY rsc_perges 
	ADD CONSTRAINT rsc_perges_pkey PRIMARY KEY (peg_codi);
	
ALTER TABLE ONLY rsc_trapeg
	ADD CONSTRAINT rsc_trapeg_pkey PRIMARY KEY (tpg_codpeg,tpg_codidi);

ALTER TABLE ONLY rsc_pegsec
	ADD CONSTRAINT rsc_pegsec_pkey PRIMARY KEY (pgs_codpeg,pgs_codsec);
	
ALTER TABLE ONLY rsc_usupeg
	ADD CONSTRAINT rsc_usupeg_pkey PRIMARY KEY (usp_codusu,usp_codpeg);
	
--
-- TOC entry 2498 (class 2606 OID 18830)
-- Dependencies: 2306 193 214
-- Name: fk_rsc_envt_reference_rsc_hise; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_envtem
    ADD CONSTRAINT fk_rsc_envt_reference_rsc_hise FOREIGN KEY (hen_cod) REFERENCES rsc_hisenv(hen_cod);


--
-- TOC entry 2424 (class 2606 OID 18470)
-- Dependencies: 170 190 2300
-- Name: fk_rsc_grpg_reference_rsc_grpg; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_grpgid
    ADD CONSTRAINT fk_rsc_grpg_reference_rsc_grpg FOREIGN KEY (sgr_grpcod) REFERENCES rsc_grpgen(grp_cod);


--
-- TOC entry 2459 (class 2606 OID 18635)
-- Dependencies: 190 198 2316
-- Name: fk_rsc_grpg_reference_rsc_scrt; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_grpgen
    ADD CONSTRAINT fk_rsc_grpg_reference_rsc_scrt FOREIGN KEY (grp_stpcod) REFERENCES rsc_scrtip(stp_cod);


--
-- TOC entry 2463 (class 2606 OID 18655)
-- Dependencies: 198 193 2316
-- Name: fk_rsc_hise_reference_rsc_scrt; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_hisenv
    ADD CONSTRAINT fk_rsc_hise_reference_rsc_scrt FOREIGN KEY (hen_stpcod) REFERENCES rsc_scrtip(stp_cod);


--
-- TOC entry 2532 (class 2606 OID 19000)
-- Dependencies: 190 2300 225
-- Name: fk_rsc_scri_ref_rsc_grg; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_scripc
    ADD CONSTRAINT fk_rsc_scri_ref_rsc_grg FOREIGN KEY (scr_estudios) REFERENCES rsc_grpgen(grp_cod);


--
-- TOC entry 2534 (class 2606 OID 19010)
-- Dependencies: 2300 225 190
-- Name: fk_rsc_scri_reference_rsc_grg; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_scripc
    ADD CONSTRAINT fk_rsc_scri_reference_rsc_grg FOREIGN KEY (scr_profesion) REFERENCES rsc_grpgen(grp_cod);


--
-- TOC entry 2438 (class 2606 OID 18530)
-- Dependencies: 178 225 2370
-- Name: fk_rsc_scrt_reference_rsc_scri; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_scrtem
    ADD CONSTRAINT fk_rsc_scrt_reference_rsc_scri FOREIGN KEY (scr_cod) REFERENCES rsc_scripc(scr_cod);


--
-- TOC entry 2461 (class 2606 OID 18645)
-- Dependencies: 192 2366 223
-- Name: fkb4a83b7f8e2c81c0; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_sensgr
    ADD CONSTRAINT fkb4a83b7f8e2c81c0 FOREIGN KEY (ssc_codsen) REFERENCES rsc_screnv(sen_cod);


--
-- TOC entry 2462 (class 2606 OID 18650)
-- Dependencies: 192 2208 144
-- Name: fkb4a83b7f8e2c8202; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_sensgr
    ADD CONSTRAINT fkb4a83b7f8e2c8202 FOREIGN KEY (ssc_codsgr) REFERENCES rsc_scrgrp(sgr_cod);


--
-- TOC entry 2395 (class 2606 OID 18325)
-- Dependencies: 2372 149 227
-- Name: rsc_admesp_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_admrem
    ADD CONSTRAINT rsc_admesp_fk FOREIGN KEY (adm_codesp) REFERENCES rsc_espter(esp_codi);


--
-- TOC entry 2396 (class 2606 OID 18330)
-- Dependencies: 149 2260 169
-- Name: rsc_admlog_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_admrem
    ADD CONSTRAINT rsc_admlog_fk FOREIGN KEY (adm_logog) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2397 (class 2606 OID 18335)
-- Dependencies: 149 2260 169
-- Name: rsc_admlop_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_admrem
    ADD CONSTRAINT rsc_admlop_fk FOREIGN KEY (adm_logop) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2537 (class 2606 OID 19025)
-- Dependencies: 226 2252 165
-- Name: rsc_afenoa_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_afecta
    ADD CONSTRAINT rsc_afenoa_fk FOREIGN KEY (afe_codnoa) REFERENCES rsc_normat(nor_codi);


--
-- TOC entry 2536 (class 2606 OID 19020)
-- Dependencies: 2252 165 226
-- Name: rsc_afenor_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_afecta
    ADD CONSTRAINT rsc_afenor_fk FOREIGN KEY (afe_codnor) REFERENCES rsc_normat(nor_codi);


--
-- TOC entry 2538 (class 2606 OID 19030)
-- Dependencies: 147 226 2214
-- Name: rsc_afetia_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_afecta
    ADD CONSTRAINT rsc_afetia_fk FOREIGN KEY (afe_codtia) REFERENCES rsc_tipafe(tia_codi);


--
-- TOC entry 2433 (class 2606 OID 18505)
-- Dependencies: 169 2260 173
-- Name: rsc_aghfot_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_aghevi
    ADD CONSTRAINT rsc_aghfot_fk FOREIGN KEY (agh_foto) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2430 (class 2606 OID 18490)
-- Dependencies: 173 2260 169
-- Name: rsc_aghicg_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_aghevi
    ADD CONSTRAINT rsc_aghicg_fk FOREIGN KEY (agh_icogra) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2431 (class 2606 OID 18495)
-- Dependencies: 173 169 2260
-- Name: rsc_aghico_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_aghevi
    ADD CONSTRAINT rsc_aghico_fk FOREIGN KEY (agh_icono) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2432 (class 2606 OID 18500)
-- Dependencies: 2234 173 156
-- Name: rsc_aghpob_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_aghevi
    ADD CONSTRAINT rsc_aghpob_fk FOREIGN KEY (agh_codpob) REFERENCES rsc_pubobj(pob_codi);


--
-- TOC entry 2441 (class 2606 OID 18545)
-- Dependencies: 2350 180 215
-- Name: rsc_agmsec_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_agrmat
    ADD CONSTRAINT rsc_agmsec_fk FOREIGN KEY (agm_codsec) REFERENCES rsc_seccio(sec_codi);


--
-- TOC entry 2420 (class 2606 OID 18450)
-- Dependencies: 167 218 2356
-- Name: rsc_audhis_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_audito
    ADD CONSTRAINT rsc_audhis_fk FOREIGN KEY (aud_codhis) REFERENCES rsc_histor(his_codi);


--
-- TOC entry 2547 (class 2606 OID 19147)
-- Dependencies: 239 241 2384
-- Name: rsc_cdcexd_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_catdoc
    ADD CONSTRAINT rsc_cdcexd_fk FOREIGN KEY (cdc_codexd) REFERENCES rsc_excdoc(exd_codi);


--
-- TOC entry 2426 (class 2606 OID 18480)
-- Dependencies: 171 2354 217
-- Name: rsc_coditra_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_doctra
    ADD CONSTRAINT rsc_coditra_fk FOREIGN KEY (coditra) REFERENCES rsc_tramit(tra_codi);


--
-- TOC entry 2421 (class 2606 OID 18455)
-- Dependencies: 197 168 2314
-- Name: rsc_comfic_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_coment
    ADD CONSTRAINT rsc_comfic_fk FOREIGN KEY (com_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2423 (class 2606 OID 18465)
-- Dependencies: 168 222 2364
-- Name: rsc_compro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_coment
    ADD CONSTRAINT rsc_compro_fk FOREIGN KEY (com_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2422 (class 2606 OID 18460)
-- Dependencies: 168 205 2330
-- Name: rsc_comusu_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_coment
    ADD CONSTRAINT rsc_comusu_fk FOREIGN KEY (com_codusu) REFERENCES rsc_usuari(usu_codi);


--
-- TOC entry 2443 (class 2606 OID 18555)
-- Dependencies: 2260 182 169
-- Name: rsc_docarc2_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_docume
    ADD CONSTRAINT rsc_docarc2_fk FOREIGN KEY (doc_codarc2) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2425 (class 2606 OID 18475)
-- Dependencies: 171 2260 169
-- Name: rsc_docarc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_doctra
    ADD CONSTRAINT rsc_docarc_fk FOREIGN KEY (doc_codarc) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2446 (class 2606 OID 18570)
-- Dependencies: 182 197 2314
-- Name: rsc_docfic_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_docume
    ADD CONSTRAINT rsc_docfic_fk FOREIGN KEY (doc_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2445 (class 2606 OID 18565)
-- Dependencies: 222 2364 182
-- Name: rsc_docpro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_docume
    ADD CONSTRAINT rsc_docpro_fk FOREIGN KEY (doc_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2427 (class 2606 OID 19165)
-- Dependencies: 171 2380 239
-- Name: rsc_doctra_catdoc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_doctra
    ADD CONSTRAINT rsc_doctra_catdoc_fk FOREIGN KEY (doc_codcdc) REFERENCES rsc_catdoc(cdc_codi);


--
-- TOC entry 2428 (class 2606 OID 19170)
-- Dependencies: 2384 241 171
-- Name: rsc_doctra_excdoc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_doctra
    ADD CONSTRAINT rsc_doctra_excdoc_fk FOREIGN KEY (doc_codexd) REFERENCES rsc_excdoc(exd_codi);


--
-- TOC entry 2444 (class 2606 OID 18560)
-- Dependencies: 182 2220 149
-- Name: rsc_doradm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_docume
    ADD CONSTRAINT rsc_doradm_fk FOREIGN KEY (dor_codadm) REFERENCES rsc_admrem(adm_codi);


--
-- TOC entry 2476 (class 2606 OID 18720)
-- Dependencies: 169 199 2260
-- Name: rsc_edifog_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_edific
    ADD CONSTRAINT rsc_edifog_fk FOREIGN KEY (edi_fotog) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2477 (class 2606 OID 18725)
-- Dependencies: 2260 199 169
-- Name: rsc_edifop_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_edific
    ADD CONSTRAINT rsc_edifop_fk FOREIGN KEY (edi_fotop) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2475 (class 2606 OID 18715)
-- Dependencies: 169 199 2260
-- Name: rsc_edipla_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_edific
    ADD CONSTRAINT rsc_edipla_fk FOREIGN KEY (edi_plano) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2474 (class 2606 OID 18710)
-- Dependencies: 2220 199 149
-- Name: rsc_edradm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_edific
    ADD CONSTRAINT rsc_edradm_fk FOREIGN KEY (edr_codadm) REFERENCES rsc_admrem(adm_codi);


--
-- TOC entry 2435 (class 2606 OID 18515)
-- Dependencies: 175 2314 197
-- Name: rsc_enlfic_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_enlace
    ADD CONSTRAINT rsc_enlfic_fk FOREIGN KEY (enl_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2434 (class 2606 OID 18510)
-- Dependencies: 2364 222 175
-- Name: rsc_enlpro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_enlace
    ADD CONSTRAINT rsc_enlpro_fk FOREIGN KEY (enl_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2541 (class 2606 OID 19045)
-- Dependencies: 227 2372 227
-- Name: rsc_espesp_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_espter
    ADD CONSTRAINT rsc_espesp_fk FOREIGN KEY (esp_codesp) REFERENCES rsc_espter(esp_codi);


--
-- TOC entry 2539 (class 2606 OID 19035)
-- Dependencies: 227 169 2260
-- Name: rsc_esplog_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_espter
    ADD CONSTRAINT rsc_esplog_fk FOREIGN KEY (esp_logo) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2540 (class 2606 OID 19040)
-- Dependencies: 227 2260 169
-- Name: rsc_espmap_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_espter
    ADD CONSTRAINT rsc_espmap_fk FOREIGN KEY (esp_mapa) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2451 (class 2606 OID 18595)
-- Dependencies: 2356 218 185
-- Name: rsc_esthis_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_estadi
    ADD CONSTRAINT rsc_esthis_fk FOREIGN KEY (est_codhis) REFERENCES rsc_histor(his_codi);


--
-- TOC entry 2470 (class 2606 OID 18690)
-- Dependencies: 197 169 2260
-- Name: rsc_ficban_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_ficha
    ADD CONSTRAINT rsc_ficban_fk FOREIGN KEY (fic_baner) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2471 (class 2606 OID 18695)
-- Dependencies: 197 169 2260
-- Name: rsc_ficico_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_ficha
    ADD CONSTRAINT rsc_ficico_fk FOREIGN KEY (fic_icono) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2472 (class 2606 OID 18700)
-- Dependencies: 169 197 2260
-- Name: rsc_ficima_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_ficha
    ADD CONSTRAINT rsc_ficima_fk FOREIGN KEY (fic_imagen) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2518 (class 2606 OID 18930)
-- Dependencies: 219 197 2314
-- Name: rsc_fihfic_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_fichev
    ADD CONSTRAINT rsc_fihfic_fk FOREIGN KEY (fih_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2519 (class 2606 OID 18935)
-- Dependencies: 219 195 2310
-- Name: rsc_fihhev_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_fichev
    ADD CONSTRAINT rsc_fihhev_fk FOREIGN KEY (fih_codhev) REFERENCES rsc_hecvit(hev_codi);


--
-- TOC entry 2410 (class 2606 OID 18400)
-- Dependencies: 197 2314 162
-- Name: rsc_fimfic_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_ficmat
    ADD CONSTRAINT rsc_fimfic_fk FOREIGN KEY (fim_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2411 (class 2606 OID 18405)
-- Dependencies: 2326 203 162
-- Name: rsc_fimmat_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_ficmat
    ADD CONSTRAINT rsc_fimmat_fk FOREIGN KEY (fim_codmat) REFERENCES rsc_materi(mat_codi);


--
-- TOC entry 2473 (class 2606 OID 18705)
-- Dependencies: 149 197 2220
-- Name: rsc_firadm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_ficha
    ADD CONSTRAINT rsc_firadm_fk FOREIGN KEY (fir_codadm) REFERENCES rsc_admrem(adm_codi);


--
-- TOC entry 2456 (class 2606 OID 18620)
-- Dependencies: 189 2260 169
-- Name: rsc_forarc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_formul
    ADD CONSTRAINT rsc_forarc_fk FOREIGN KEY (for_codarc) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2457 (class 2606 OID 18625)
-- Dependencies: 2260 169 189
-- Name: rsc_forman_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_formul
    ADD CONSTRAINT rsc_forman_fk FOREIGN KEY (for_manual) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2458 (class 2606 OID 18630)
-- Dependencies: 217 2354 189
-- Name: rsc_fortra_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_formul
    ADD CONSTRAINT rsc_fortra_fk FOREIGN KEY (for_codtra) REFERENCES rsc_tramit(tra_codi);


--
-- TOC entry 2455 (class 2606 OID 18615)
-- Dependencies: 2314 187 197
-- Name: rsc_fuafic_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_fichua
    ADD CONSTRAINT rsc_fuafic_fk FOREIGN KEY (fua_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2454 (class 2606 OID 18610)
-- Dependencies: 2350 215 187
-- Name: rsc_fuasec_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_fichua
    ADD CONSTRAINT rsc_fuasec_fk FOREIGN KEY (fua_codsec) REFERENCES rsc_seccio(sec_codi);


--
-- TOC entry 2453 (class 2606 OID 18605)
-- Dependencies: 187 2352 216
-- Name: rsc_fuauna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_fichua
    ADD CONSTRAINT rsc_fuauna_fk FOREIGN KEY (fua_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2466 (class 2606 OID 18670)
-- Dependencies: 169 195 2260
-- Name: rsc_hevfot_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_hecvit
    ADD CONSTRAINT rsc_hevfot_fk FOREIGN KEY (hev_foto) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2468 (class 2606 OID 18680)
-- Dependencies: 195 169 2260
-- Name: rsc_hevicg_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_hecvit
    ADD CONSTRAINT rsc_hevicg_fk FOREIGN KEY (hev_icogra) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2467 (class 2606 OID 18675)
-- Dependencies: 2260 169 195
-- Name: rsc_hevico_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_hecvit
    ADD CONSTRAINT rsc_hevico_fk FOREIGN KEY (hev_icono) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2516 (class 2606 OID 18920)
-- Dependencies: 2314 197 218
-- Name: rsc_hisfic_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_histor
    ADD CONSTRAINT rsc_hisfic_fk FOREIGN KEY (his_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2517 (class 2606 OID 18925)
-- Dependencies: 203 218 2326
-- Name: rsc_hismat_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_histor
    ADD CONSTRAINT rsc_hismat_fk FOREIGN KEY (his_codmat) REFERENCES rsc_materi(mat_codi);


--
-- TOC entry 2514 (class 2606 OID 18910)
-- Dependencies: 165 2252 218
-- Name: rsc_hisnor_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_histor
    ADD CONSTRAINT rsc_hisnor_fk FOREIGN KEY (his_codnor) REFERENCES rsc_normat(nor_codi);


--
-- TOC entry 2515 (class 2606 OID 18915)
-- Dependencies: 222 2364 218
-- Name: rsc_hispro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_histor
    ADD CONSTRAINT rsc_hispro_fk FOREIGN KEY (his_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2513 (class 2606 OID 18905)
-- Dependencies: 218 216 2352
-- Name: rsc_hisuna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_histor
    ADD CONSTRAINT rsc_hisuna_fk FOREIGN KEY (his_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2405 (class 2606 OID 18375)
-- Dependencies: 2268 155 173
-- Name: rsc_hvaagh_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_heviag
    ADD CONSTRAINT rsc_hvaagh_fk FOREIGN KEY (hva_codagh) REFERENCES rsc_aghevi(agh_codi);


--
-- TOC entry 2406 (class 2606 OID 18380)
-- Dependencies: 2310 155 195
-- Name: rsc_hvahev_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_heviag
    ADD CONSTRAINT rsc_hvahev_fk FOREIGN KEY (hva_codhev) REFERENCES rsc_hecvit(hev_codi);


--
-- TOC entry 2401 (class 2606 OID 18355)
-- Dependencies: 152 2310 195
-- Name: rsc_hvphev_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_hevipr
    ADD CONSTRAINT rsc_hvphev_fk FOREIGN KEY (hvp_codhev) REFERENCES rsc_hecvit(hev_codi);


--
-- TOC entry 2402 (class 2606 OID 18360)
-- Dependencies: 2364 222 152
-- Name: rsc_hvppro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_hevipr
    ADD CONSTRAINT rsc_hvppro_fk FOREIGN KEY (hvp_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2448 (class 2606 OID 18580)
-- Dependencies: 210 184 2342
-- Name: rsc_icffam_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_icofam
    ADD CONSTRAINT rsc_icffam_fk FOREIGN KEY (icf_codfam) REFERENCES rsc_famili(fam_codi);


--
-- TOC entry 2449 (class 2606 OID 18585)
-- Dependencies: 184 169 2260
-- Name: rsc_icfico_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_icofam
    ADD CONSTRAINT rsc_icfico_fk FOREIGN KEY (icf_icono) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2450 (class 2606 OID 18590)
-- Dependencies: 2334 184 206
-- Name: rsc_icfpec_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_icofam
    ADD CONSTRAINT rsc_icfpec_fk FOREIGN KEY (icf_codpec) REFERENCES rsc_perciu(pec_codi);


--
-- TOC entry 2521 (class 2606 OID 18945)
-- Dependencies: 2260 169 220
-- Name: rsc_icmico_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_icmate
    ADD CONSTRAINT rsc_icmico_fk FOREIGN KEY (icm_icono) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2522 (class 2606 OID 18950)
-- Dependencies: 2326 220 203
-- Name: rsc_icmmat_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_icmate
    ADD CONSTRAINT rsc_icmmat_fk FOREIGN KEY (icm_codmat) REFERENCES rsc_materi(mat_codi);


--
-- TOC entry 2520 (class 2606 OID 18940)
-- Dependencies: 206 220 2334
-- Name: rsc_icmpec_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_icmate
    ADD CONSTRAINT rsc_icmpec_fk FOREIGN KEY (icm_codpec) REFERENCES rsc_perciu(pec_codi);


--
-- TOC entry 2478 (class 2606 OID 18730)
-- Dependencies: 180 201 2280
-- Name: rsc_magagm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_matagr
    ADD CONSTRAINT rsc_magagm_fk FOREIGN KEY (mag_codagm) REFERENCES rsc_agrmat(agm_codi);


--
-- TOC entry 2479 (class 2606 OID 18735)
-- Dependencies: 2326 201 203
-- Name: rsc_magmat_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_matagr
    ADD CONSTRAINT rsc_magmat_fk FOREIGN KEY (mag_codmat) REFERENCES rsc_materi(mat_codi);


--
-- TOC entry 2480 (class 2606 OID 18740)
-- Dependencies: 2260 203 169
-- Name: rsc_matfot_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_materi
    ADD CONSTRAINT rsc_matfot_fk FOREIGN KEY (mat_foto) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2482 (class 2606 OID 18750)
-- Dependencies: 203 169 2260
-- Name: rsc_maticg_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_materi
    ADD CONSTRAINT rsc_maticg_fk FOREIGN KEY (mat_icogra) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2481 (class 2606 OID 18745)
-- Dependencies: 2260 169 203
-- Name: rsc_matico_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_materi
    ADD CONSTRAINT rsc_matico_fk FOREIGN KEY (mat_icono) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2417 (class 2606 OID 18435)
-- Dependencies: 149 2220 165
-- Name: rsc_neradm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_normat
    ADD CONSTRAINT rsc_neradm_fk FOREIGN KEY (ner_codadm) REFERENCES rsc_admrem(adm_codi);


--
-- TOC entry 2415 (class 2606 OID 18425)
-- Dependencies: 2270 174 165
-- Name: rsc_norbol_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_normat
    ADD CONSTRAINT rsc_norbol_fk FOREIGN KEY (nor_codbol) REFERENCES rsc_boleti(bol_codi);


--
-- TOC entry 2418 (class 2606 OID 18440)
-- Dependencies: 188 165 2296
-- Name: rsc_nortip_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_normat
    ADD CONSTRAINT rsc_nortip_fk FOREIGN KEY (nor_codtip) REFERENCES rsc_tipo(tip_codi);


--
-- TOC entry 2416 (class 2606 OID 18430)
-- Dependencies: 216 165 2352
-- Name: rsc_noruna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_normat
    ADD CONSTRAINT rsc_noruna_fk FOREIGN KEY (nor_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2512 (class 2606 OID 18900)
-- Dependencies: 217 2352 216
-- Name: rsc_orgcomp_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tramit
    ADD CONSTRAINT rsc_orgcomp_fk FOREIGN KEY (tra_orgcomp) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2497 (class 2606 OID 18825)
-- Dependencies: 2352 216 213
-- Name: rsc_peruna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_person
    ADD CONSTRAINT rsc_peruna_fk FOREIGN KEY (per_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2543 (class 2606 OID 19099)
-- Dependencies: 2314 237 197
-- Name: rsc_pfcfic_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_pobfic
    ADD CONSTRAINT rsc_pfcfic_fk FOREIGN KEY (pfc_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2544 (class 2606 OID 19104)
-- Dependencies: 237 156 2234
-- Name: rsc_pfcpob_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_pobfic
    ADD CONSTRAINT rsc_pfcpob_fk FOREIGN KEY (pfc_codpob) REFERENCES rsc_pubobj(pob_codi);


--
-- TOC entry 2546 (class 2606 OID 19119)
-- Dependencies: 156 2234 238
-- Name: rsc_pprpob_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_pobpro
    ADD CONSTRAINT rsc_pprpob_fk FOREIGN KEY (ppr_codpob) REFERENCES rsc_pubobj(pob_codi);


--
-- TOC entry 2545 (class 2606 OID 19114)
-- Dependencies: 238 222 2364
-- Name: rsc_pprpro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_pobpro
    ADD CONSTRAINT rsc_pprpro_fk FOREIGN KEY (ppr_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2388 (class 2606 OID 18290)
-- Dependencies: 203 2326 143
-- Name: rsc_prmmat_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_promat
    ADD CONSTRAINT rsc_prmmat_fk FOREIGN KEY (prm_codmat) REFERENCES rsc_materi(mat_codi);


--
-- TOC entry 2389 (class 2606 OID 18295)
-- Dependencies: 222 2364 143
-- Name: rsc_prmpro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_promat
    ADD CONSTRAINT rsc_prmpro_fk FOREIGN KEY (prm_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2464 (class 2606 OID 18660)
-- Dependencies: 194 165 2252
-- Name: rsc_prnnor_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_pronor
    ADD CONSTRAINT rsc_prnnor_fk FOREIGN KEY (prn_codnor) REFERENCES rsc_normat(nor_codi);


--
-- TOC entry 2465 (class 2606 OID 18665)
-- Dependencies: 222 194 2364
-- Name: rsc_prnpro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_pronor
    ADD CONSTRAINT rsc_prnpro_fk FOREIGN KEY (prn_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2525 (class 2606 OID 18965)
-- Dependencies: 2352 222 216
-- Name: rsc_pro_coduna_resol_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_proced
    ADD CONSTRAINT rsc_pro_coduna_resol_fk FOREIGN KEY (pro_coduna_resol) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2526 (class 2606 OID 18970)
-- Dependencies: 210 222 2342
-- Name: rsc_profam_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_proced
    ADD CONSTRAINT rsc_profam_fk FOREIGN KEY (pro_codfam) REFERENCES rsc_famili(fam_codi);


--
-- TOC entry 2527 (class 2606 OID 18975)
-- Dependencies: 2204 222 142
-- Name: rsc_proini_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_proced
    ADD CONSTRAINT rsc_proini_fk FOREIGN KEY (pro_codini) REFERENCES rsc_inici(ini_codi);


--
-- TOC entry 2524 (class 2606 OID 18960)
-- Dependencies: 2352 216 222
-- Name: rsc_prouna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_proced
    ADD CONSTRAINT rsc_prouna_fk FOREIGN KEY (pro_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2528 (class 2606 OID 18980)
-- Dependencies: 222 149 2220
-- Name: rsc_prradm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_proced
    ADD CONSTRAINT rsc_prradm_fk FOREIGN KEY (prr_codadm) REFERENCES rsc_admrem(adm_codi);


--
-- TOC entry 2499 (class 2606 OID 18835)
-- Dependencies: 2350 215 215
-- Name: rsc_secsec_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_seccio
    ADD CONSTRAINT rsc_secsec_fk FOREIGN KEY (sec_codsec) REFERENCES rsc_seccio(sec_codi);


--
-- TOC entry 2535 (class 2606 OID 19015)
-- Dependencies: 225 144 2208
-- Name: rsc_sgrscr_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_scripc
    ADD CONSTRAINT rsc_sgrscr_fk FOREIGN KEY (scr_sgrcod) REFERENCES rsc_scrgrp(sgr_cod);


--
-- TOC entry 2429 (class 2606 OID 18485)
-- Dependencies: 2208 172 144
-- Name: rsc_sgrsgi_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_scrgid
    ADD CONSTRAINT rsc_sgrsgi_fk FOREIGN KEY (sgi_sgrcod) REFERENCES rsc_scrgrp(sgr_cod);


--
-- TOC entry 2436 (class 2606 OID 18520)
-- Dependencies: 198 176 2316
-- Name: rsc_stpsck_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_scrkey
    ADD CONSTRAINT rsc_stpsck_fk FOREIGN KEY (sck_stpcod) REFERENCES rsc_scrtip(stp_cod);


--
-- TOC entry 2533 (class 2606 OID 19005)
-- Dependencies: 2316 225 198
-- Name: rsc_stpscr_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_scripc
    ADD CONSTRAINT rsc_stpscr_fk FOREIGN KEY (scr_stpcod) REFERENCES rsc_scrtip(stp_cod);


--
-- TOC entry 2529 (class 2606 OID 18985)
-- Dependencies: 2316 223 198
-- Name: rsc_stpsenv_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_screnv
    ADD CONSTRAINT rsc_stpsenv_fk FOREIGN KEY (sen_stpcod) REFERENCES rsc_scrtip(stp_cod);


--
-- TOC entry 2390 (class 2606 OID 18300)
-- Dependencies: 198 144 2316
-- Name: rsc_stpsgr_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_scrgrp
    ADD CONSTRAINT rsc_stpsgr_fk FOREIGN KEY (sgr_stpcod) REFERENCES rsc_scrtip(stp_cod);


--
-- TOC entry 2409 (class 2606 OID 18395)
-- Dependencies: 161 2280 180
-- Name: rsc_tamagm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_traagm
    ADD CONSTRAINT rsc_tamagm_fk FOREIGN KEY (tam_codagm) REFERENCES rsc_agrmat(agm_codi);


--
-- TOC entry 2408 (class 2606 OID 18390)
-- Dependencies: 217 2354 160
-- Name: rsc_taxtra_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_taxa
    ADD CONSTRAINT rsc_taxtra_fk FOREIGN KEY (tax_codtra) REFERENCES rsc_tramit(tra_codi);


--
-- TOC entry 2548 (class 2606 OID 19137)
-- Dependencies: 240 239 2380
-- Name: rsc_tcdcdc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tracdc
    ADD CONSTRAINT rsc_tcdcdc_fk FOREIGN KEY (tcd_codcdc) REFERENCES rsc_catdoc(cdc_codi);


--
-- TOC entry 2439 (class 2606 OID 18535)
-- Dependencies: 169 179 2260
-- Name: rsc_tdoarc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tradoc
    ADD CONSTRAINT rsc_tdoarc_fk FOREIGN KEY (tdo_codarc) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2491 (class 2606 OID 18795)
-- Dependencies: 209 2260 169
-- Name: rsc_tdoarc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tradoctra
    ADD CONSTRAINT rsc_tdoarc_fk FOREIGN KEY (tdo_codarc) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2440 (class 2606 OID 18540)
-- Dependencies: 2284 179 182
-- Name: rsc_tdodoc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tradoc
    ADD CONSTRAINT rsc_tdodoc_fk FOREIGN KEY (tdo_coddoc) REFERENCES rsc_docume(doc_codi);


--
-- TOC entry 2490 (class 2606 OID 18790)
-- Dependencies: 209 2264 171
-- Name: rsc_tdodoctra_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tradoctra
    ADD CONSTRAINT rsc_tdodoctra_fk FOREIGN KEY (tdo_codtra) REFERENCES rsc_doctra(doc_codi);


--
-- TOC entry 2414 (class 2606 OID 18420)
-- Dependencies: 2318 164 199
-- Name: rsc_tededi_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_traedi
    ADD CONSTRAINT rsc_tededi_fk FOREIGN KEY (ted_codedi) REFERENCES rsc_edific(edi_codi);


--
-- TOC entry 2549 (class 2606 OID 19160)
-- Dependencies: 242 241 2384
-- Name: rsc_tedexd_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_traexd
    ADD CONSTRAINT rsc_tedexd_fk FOREIGN KEY (ted_codexd) REFERENCES rsc_excdoc(exd_codi);


--
-- TOC entry 2419 (class 2606 OID 18445)
-- Dependencies: 166 175 2272
-- Name: rsc_tenenl_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_traenl
    ADD CONSTRAINT rsc_tenenl_fk FOREIGN KEY (ten_codenl) REFERENCES rsc_enlace(enl_codi);


--
-- TOC entry 2442 (class 2606 OID 18550)
-- Dependencies: 181 2372 227
-- Name: rsc_tesesp_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_traesp
    ADD CONSTRAINT rsc_tesesp_fk FOREIGN KEY (tes_codesp) REFERENCES rsc_espter(esp_codi);


--
-- TOC entry 2542 (class 2606 OID 19050)
-- Dependencies: 2342 228 210
-- Name: rsc_tfafam_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trafam
    ADD CONSTRAINT rsc_tfafam_fk FOREIGN KEY (tfa_codfam) REFERENCES rsc_famili(fam_codi);


--
-- TOC entry 2487 (class 2606 OID 18775)
-- Dependencies: 207 2314 197
-- Name: rsc_tfific_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trafic
    ADD CONSTRAINT rsc_tfific_fk FOREIGN KEY (tfi_codfic) REFERENCES rsc_ficha(fic_codi);


--
-- TOC entry 2492 (class 2606 OID 18800)
-- Dependencies: 2260 211 169
-- Name: rsc_thecon_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trahev
    ADD CONSTRAINT rsc_thecon_fk FOREIGN KEY (the_conten) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2493 (class 2606 OID 18805)
-- Dependencies: 211 2260 169
-- Name: rsc_thedis_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trahev
    ADD CONSTRAINT rsc_thedis_fk FOREIGN KEY (the_coddis) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2495 (class 2606 OID 18815)
-- Dependencies: 2310 211 195
-- Name: rsc_thehev_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trahev
    ADD CONSTRAINT rsc_thehev_fk FOREIGN KEY (the_codhev) REFERENCES rsc_hecvit(hev_codi);


--
-- TOC entry 2494 (class 2606 OID 18810)
-- Dependencies: 211 2260 169
-- Name: rsc_thenor_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trahev
    ADD CONSTRAINT rsc_thenor_fk FOREIGN KEY (the_normat) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2486 (class 2606 OID 18770)
-- Dependencies: 2260 169 204
-- Name: rsc_tmacon_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tramat
    ADD CONSTRAINT rsc_tmacon_fk FOREIGN KEY (tma_conten) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2483 (class 2606 OID 18755)
-- Dependencies: 2260 204 169
-- Name: rsc_tmadis_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tramat
    ADD CONSTRAINT rsc_tmadis_fk FOREIGN KEY (tma_coddis) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2485 (class 2606 OID 18765)
-- Dependencies: 203 2326 204
-- Name: rsc_tmamat_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tramat
    ADD CONSTRAINT rsc_tmamat_fk FOREIGN KEY (tma_codmat) REFERENCES rsc_materi(mat_codi);


--
-- TOC entry 2484 (class 2606 OID 18760)
-- Dependencies: 204 2260 169
-- Name: rsc_tmanor_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tramat
    ADD CONSTRAINT rsc_tmanor_fk FOREIGN KEY (tma_normat) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2392 (class 2606 OID 18310)
-- Dependencies: 169 2260 146
-- Name: rsc_tnearc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tranoe
    ADD CONSTRAINT rsc_tnearc_fk FOREIGN KEY (tne_codarc) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2393 (class 2606 OID 18315)
-- Dependencies: 146 2252 165
-- Name: rsc_tnenor_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tranoe
    ADD CONSTRAINT rsc_tnenor_fk FOREIGN KEY (tne_codnor) REFERENCES rsc_normat(nor_codi);


--
-- TOC entry 2488 (class 2606 OID 18780)
-- Dependencies: 2260 208 169
-- Name: rsc_tnlarc_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tranol
    ADD CONSTRAINT rsc_tnlarc_fk FOREIGN KEY (tnl_codarc) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2489 (class 2606 OID 18785)
-- Dependencies: 208 2252 165
-- Name: rsc_tnlnor_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tranol
    ADD CONSTRAINT rsc_tnlnor_fk FOREIGN KEY (tnl_codnor) REFERENCES rsc_normat(nor_codi);


--
-- TOC entry 2407 (class 2606 OID 18385)
-- Dependencies: 206 158 2334
-- Name: rsc_tpepec_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trapec
    ADD CONSTRAINT rsc_tpepec_fk FOREIGN KEY (tpe_codpec) REFERENCES rsc_perciu(pec_codi);


--
-- TOC entry 2394 (class 2606 OID 18320)
-- Dependencies: 222 2364 148
-- Name: rsc_tprpro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trapro
    ADD CONSTRAINT rsc_tprpro_fk FOREIGN KEY (tpr_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2469 (class 2606 OID 18685)
-- Dependencies: 196 142 2204
-- Name: rsc_traini_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_traini
    ADD CONSTRAINT rsc_traini_fk FOREIGN KEY (tin_codini) REFERENCES rsc_inici(ini_codi);


--
-- TOC entry 2510 (class 2606 OID 18890)
-- Dependencies: 217 2364 222
-- Name: rsc_trapro_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tramit
    ADD CONSTRAINT rsc_trapro_fk FOREIGN KEY (tra_codpro) REFERENCES rsc_proced(pro_codi);


--
-- TOC entry 2523 (class 2606 OID 18955)
-- Dependencies: 160 221 2242
-- Name: rsc_tratax_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tratax
    ADD CONSTRAINT rsc_tratax_fk FOREIGN KEY (ttax_codi) REFERENCES rsc_taxa(tax_codi);


--
-- TOC entry 2452 (class 2606 OID 18600)
-- Dependencies: 2268 173 186
-- Name: rsc_trgagh_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_traagh
    ADD CONSTRAINT rsc_trgagh_fk FOREIGN KEY (trg_codagh) REFERENCES rsc_aghevi(agh_codi);


--
-- TOC entry 2496 (class 2606 OID 18820)
-- Dependencies: 150 212 2222
-- Name: rsc_trnunm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_traunm
    ADD CONSTRAINT rsc_trnunm_fk FOREIGN KEY (trn_codunm) REFERENCES rsc_unamat(unm_codi);


--
-- TOC entry 2391 (class 2606 OID 18305)
-- Dependencies: 145 156 2234
-- Name: rsc_trppob_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trapob
    ADD CONSTRAINT rsc_trppob_fk FOREIGN KEY (trp_codpob) REFERENCES rsc_pubobj(pob_codi);


--
-- TOC entry 2511 (class 2606 OID 18895)
-- Dependencies: 217 2220 149
-- Name: rsc_trradm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tramit
    ADD CONSTRAINT rsc_trradm_fk FOREIGN KEY (trr_codadm) REFERENCES rsc_admrem(adm_codi);


--
-- TOC entry 2447 (class 2606 OID 18575)
-- Dependencies: 215 2350 183
-- Name: rsc_tsesec_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trasec
    ADD CONSTRAINT rsc_tsesec_fk FOREIGN KEY (tse_codsec) REFERENCES rsc_seccio(sec_codi);


--
-- TOC entry 2403 (class 2606 OID 18365)
-- Dependencies: 153 147 2214
-- Name: rsc_ttatia_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tratia
    ADD CONSTRAINT rsc_ttatia_fk FOREIGN KEY (tta_codtia) REFERENCES rsc_tipafe(tia_codi);


--
-- TOC entry 2404 (class 2606 OID 18370)
-- Dependencies: 188 154 2296
-- Name: rsc_ttitip_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tratip
    ADD CONSTRAINT rsc_ttitip_fk FOREIGN KEY (tti_codtip) REFERENCES rsc_tipo(tip_codi);


--
-- TOC entry 2460 (class 2606 OID 18640)
-- Dependencies: 217 2354 191
-- Name: rsc_ttrtra_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tratra
    ADD CONSTRAINT rsc_ttrtra_fk FOREIGN KEY (ttr_codttr) REFERENCES rsc_tramit(tra_codi);


--
-- TOC entry 2437 (class 2606 OID 18525)
-- Dependencies: 200 177 2320
-- Name: rsc_ttttrt_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_tratrt
    ADD CONSTRAINT rsc_ttttrt_fk FOREIGN KEY (ttt_codtrt) REFERENCES rsc_tratam(trt_codi);


--
-- TOC entry 2400 (class 2606 OID 18350)
-- Dependencies: 151 216 2352
-- Name: rsc_tununa_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_trauna
    ADD CONSTRAINT rsc_tununa_fk FOREIGN KEY (tun_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2500 (class 2606 OID 18840)
-- Dependencies: 216 227 2372
-- Name: rsc_unaesp_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unaesp_fk FOREIGN KEY (una_codesp) REFERENCES rsc_espter(esp_codi);


--
-- TOC entry 2509 (class 2606 OID 18885)
-- Dependencies: 216 2260 169
-- Name: rsc_unafog_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unafog_fk FOREIGN KEY (una_fotog) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2508 (class 2606 OID 18880)
-- Dependencies: 2260 216 169
-- Name: rsc_unafop_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unafop_fk FOREIGN KEY (una_fotop) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2507 (class 2606 OID 18875)
-- Dependencies: 216 169 2260
-- Name: rsc_unaloh_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unaloh_fk FOREIGN KEY (una_logoh) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2505 (class 2606 OID 18865)
-- Dependencies: 216 169 2260
-- Name: rsc_unalos_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unalos_fk FOREIGN KEY (una_logos) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2506 (class 2606 OID 18870)
-- Dependencies: 216 2260 169
-- Name: rsc_unalot_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unalot_fk FOREIGN KEY (una_logot) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2502 (class 2606 OID 18850)
-- Dependencies: 169 216 2260
-- Name: rsc_unalov_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unalov_fk FOREIGN KEY (una_logov) REFERENCES rsc_archiv(arc_codi);


--
-- TOC entry 2504 (class 2606 OID 18860)
-- Dependencies: 200 216 2320
-- Name: rsc_unatrt_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unatrt_fk FOREIGN KEY (una_codtrt) REFERENCES rsc_tratam(trt_codi);


--
-- TOC entry 2503 (class 2606 OID 18855)
-- Dependencies: 216 216 2352
-- Name: rsc_unauna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unauna_fk FOREIGN KEY (una_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2531 (class 2606 OID 18995)
-- Dependencies: 224 199 2318
-- Name: rsc_uneedi_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_unaedi
    ADD CONSTRAINT rsc_uneedi_fk FOREIGN KEY (une_codedi) REFERENCES rsc_edific(edi_codi);


--
-- TOC entry 2530 (class 2606 OID 18990)
-- Dependencies: 2352 224 216
-- Name: rsc_uneuna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_unaedi
    ADD CONSTRAINT rsc_uneuna_fk FOREIGN KEY (une_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2398 (class 2606 OID 18340)
-- Dependencies: 2326 203 150
-- Name: rsc_unmmat_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_unamat
    ADD CONSTRAINT rsc_unmmat_fk FOREIGN KEY (unm_codmat) REFERENCES rsc_materi(mat_codi);


--
-- TOC entry 2399 (class 2606 OID 18345)
-- Dependencies: 216 2352 150
-- Name: rsc_unmuna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_unamat
    ADD CONSTRAINT rsc_unmuna_fk FOREIGN KEY (unm_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2501 (class 2606 OID 18845)
-- Dependencies: 216 2220 149
-- Name: rsc_unradm_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_uniadm
    ADD CONSTRAINT rsc_unradm_fk FOREIGN KEY (unr_codadm) REFERENCES rsc_admrem(adm_codi);


--
-- TOC entry 2412 (class 2606 OID 18410)
-- Dependencies: 2352 216 163
-- Name: rsc_unuuna_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_unausu
    ADD CONSTRAINT rsc_unuuna_fk FOREIGN KEY (unu_coduna) REFERENCES rsc_uniadm(una_codi);


--
-- TOC entry 2413 (class 2606 OID 18415)
-- Dependencies: 2330 163 205
-- Name: rsc_unuusu_fk; Type: FK CONSTRAINT; Schema: public; Owner: rolsac
--

ALTER TABLE ONLY rsc_unausu
    ADD CONSTRAINT rsc_unuusu_fk FOREIGN KEY (unu_codusu) REFERENCES rsc_usuari(usu_codi);
    
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


--
-- TOC entry 2553 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-01-07 14:39:47 CET

--
-- PostgreSQL database dump complete
--

