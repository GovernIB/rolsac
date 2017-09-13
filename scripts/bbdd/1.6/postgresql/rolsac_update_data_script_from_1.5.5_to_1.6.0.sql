SET SERVEROUTPUT ON;
---SCRIPT PARA PASAR EL DOCUMENTO A RSC_DOCNOR Y RSC_TRADNR
---Ademas, ese enlace pasa a ser del documento, no de la normativa.
-------- Tras pasar el documento de enlace, ponemos el enlace a null.
DECLARE

    cursor traducciones is
        select DISTINCT tno_codnor
          from rsc_tranor
         where tno_codarc is not null
           or  tno_enlace is not null;
           
    ID_DOCNOR NUMBER;
    TITULO    VARCHAR2(512 CHAR);
BEGIN
  DBMS_OUTPUT.PUT_LINE ('INICIO');
  FOR traduccion IN traducciones
  LOOP
      SELECT RSC_SEQ_ALL.nextval
        INTO ID_DOCNOR 
        FROM DUAL;
        
        
      DBMS_OUTPUT.PUT_LINE ('   DNO_CODI:' || ID_DOCNOR || ' NORM:' || traduccion.TNO_CODNOR);
      
      INSERT INTO RSC_DOCNOR (DNO_CODI, DNO_CODNOR, DNO_ORDEN) VALUES (ID_DOCNOR, traduccion.TNO_CODNOR, 0);
      
      INSERT INTO RSC_TRADNR (TDN_CODDNR, TDN_TITULO, TDN_ENLACE, TDN_CODARC, TDN_CODIDI)
      ( SELECT ID_DOCNOR, 'Documento ' || TNO_CODIDI, TNO_ENLACE, TNO_CODARC, TNO_CODIDI
          FROM RSC_TRANOR           
         WHERE TNO_CODNOR = traduccion.TNO_CODNOR
           AND (TNO_CODARC is not null OR TNO_ENLACE is not null)
          
            
      );
      
      
  END LOOP;
  
  --Quitamos el enlace a null (ya lo tiene el documento)
  UPDATE rsc_tranor
     SET TNO_ENLACE = null;
  
  DBMS_OUTPUT.PUT_LINE ('FIN');
  COMMIT;
END;
