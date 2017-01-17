
create table RSC_SIAJOB  (
   SIJ_ID number(19,0) not null,
   SIJ_FECINI date,
   SIJ_FECFIN date,
   SIJ_DESBRE clob,
   SIJ_DESCRI clob,
   SIJ_ESTADO number(1,0),
   primary key (SIJ_ID)
);

  COMMENT ON TABLE  RSC_SIAJOB IS 'SIA Job';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_ID       IS 'Identificador';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_FECINI   IS 'Fecha de creación';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_FECFIN   IS 'Fecha de finalización';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_DESBRE   IS 'Descripción breve del resultado';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_DESCRI   IS 'Descripción';
  COMMENT ON COLUMN RSC_SIAJOB.SIJ_ESTADO   IS '0: Creado y en proceso; 1:Ejecución; 2:Enviado y sin errores; 3:Enviado y con errores; -1:Problema grave';
  
create table RSC_SIAPDT  (
   SIP_ID number(19,0) not null,
   SIP_TIPO varchar2(100),
   SIP_IDELEM number(8,0),
   SIP_ESTADO number(1,0),
   SIP_FECALT date,
   SIP_FECIDX date,
   SIP_MENSA varchar2(255),
   SIP_EXISTE number(1,0),
   SIP_SIA    number(19,0),
   primary key (SIP_ID)
);
  COMMENT ON TABLE  RSC_SIAPDT 			IS 'SIA pendiente';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_ID      	    IS 'Identificador';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_TIPO     	IS 'UA/PROC/NORM';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_IDELEM       IS 'Código elemento';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_ESTADO   	IS '0:Espera; 1:enviado correctamente; 2:enviado incorrectamente';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_FECALT   	IS 'Fecha creación';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_FECIDX   	IS 'Fecha envio';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_MENSA   	 	IS 'Mensaje';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_EXISTE   	IS 'Existe, siendo 1 que si y 0 que ha sido borrado del sistema.';
  COMMENT ON COLUMN RSC_SIAPDT.SIP_SIA   		IS 'ID SIA necesario para cuando se borra la entidad';
 
  --Añadimos los campos a la tabla de traducciones ficha RSC_TRAFIC
  ALTER TABLE RSC_TRAFIC ADD TFI_URLFOR VARCHAR2(256 CHAR);
  ALTER TABLE RSC_TRAFIC ADD TFI_URLVID VARCHAR2(256 CHAR);
  ALTER TABLE RSC_TRAFIC ADD TFI_ICONO NUMBER(19,0);
  ALTER TABLE RSC_TRAFIC ADD TFI_IMAGEN NUMBER(19,0);
  ALTER TABLE RSC_TRAFIC ADD TFI_BANNER NUMBER(19,0);
  
  COMMENT ON COLUMN RSC_TRAFIC.TFI_URLFOR      	IS 'Url foro';
  COMMENT ON COLUMN RSC_TRAFIC.TFI_URLVID     	IS 'Url video';
  COMMENT ON COLUMN RSC_TRAFIC.TFI_ICONO   		IS 'Icono';
  COMMENT ON COLUMN RSC_TRAFIC.TFI_IMAGEN   	IS 'Imagen';
  COMMENT ON COLUMN RSC_TRAFIC.TFI_BANNER   	IS 'Banner';
  
   --AFEGIR CAMPS PER CORRESPONDENCIA UAS SIA-ROLSAC
ALTER TABLE RSC_PROCED ADD PRO_ESTSIA VARCHAR2(1 CHAR);
COMMENT ON COLUMN RSC_PROCED.PRO_ESTSIA IS 'Estado envio SIA';

ALTER TABLE RSC_PROCED ADD PRO_FECSIA DATE;
COMMENT ON COLUMN RSC_PROCED.PRO_FECSIA IS 'Fecha de envio a SIA';