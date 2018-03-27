SET SERVEROUTPUT ON;
---SCRIPT PARA PASAR EL DOCUMENTO A RSC_DOCNOR Y RSC_TRADNR
---Ademas, ese enlace pasa a ser del documento, no de la normativa.
-------- Tras pasar el documento de enlace, ponemos el enlace a null.
DECLARE

    cursor traducciones is
        select DISTINCT tno_codnor
          from rsc_tranor_view
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
      
         INSERT INTO RSC_TRADNR (TDN_CODDNR, TDN_TITULO, TDN_CODARC, TDN_CODIDI, TDN_ENLACE)
        ( SELECT ID_DOCNOR, 'Documento ' || TNO_CODIDI,  TNO_CODARC, TNO_CODIDI, 'https://www.caib.es/seucaib/ca/arxiuServlet?id=' || TNO_CODARC
            FROM RSC_TRANOR_VIEW           
           WHERE TNO_CODNOR = traduccion.TNO_CODNOR
             AND (TNO_CODARC is not null AND TNO_ENLACE is null)
          UNION
          SELECT ID_DOCNOR, 'Documento ' || TNO_CODIDI, TNO_CODARC, TNO_CODIDI, TNO_ENLACE
            FROM RSC_TRANOR_VIEW           
           WHERE TNO_CODNOR = traduccion.TNO_CODNOR
             AND TNO_ENLACE is not null
        );
      
      
  END LOOP;
  
  DBMS_OUTPUT.PUT_LINE ('FIN');
  COMMIT;
END;
