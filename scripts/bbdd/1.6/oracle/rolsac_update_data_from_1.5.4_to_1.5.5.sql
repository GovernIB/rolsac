SET SERVEROUTPUT ON;
DECLARE
  ORDEN NUMBER := 1;
  /* CURSOR DOCUMENTS PROCEDIMENTS AMB ORDRE INCORRECTE */
  CURSOR c_proced IS
  select * from (
  select doc_codpro, count(doc_orden) documentos_duplicados 
  from rsc_docume where doc_codpro is not null 
  group by doc_codpro, doc_orden
  ) where documentos_duplicados > 1;
  
  proced c_proced%ROWTYPE;
  
  /* CURSOR DOCUMENTS FITXA AMB ORDRE INCORRECTE */
  CURSOR c_fitxa IS
  select * from (
  select doc_codfic, count(doc_orden) documentos_duplicados 
  from rsc_docume where doc_codfic is not null 
  group by doc_codfic, doc_orden
  ) where documentos_duplicados > 1;
  
   fitxa c_fitxa%ROWTYPE;
 
BEGIN
  OPEN c_proced;
  LOOP
      FETCH c_proced INTO proced;
      EXIT WHEN c_proced%NOTFOUND;
        dbms_output.put_line('proced:' || proced.doc_codpro);
        ORDEN := 1;
        FOR i IN (select * from rsc_docume where doc_codpro = proced.doc_codpro order by doc_orden) LOOP
                        update rsc_docume set doc_orden= ORDEN where doc_codi = i.doc_codi;
                        dbms_output.put_line('update rsc_docume set doc_orden=' || ORDEN || ' where doc_codi =' || i.doc_codi || ';');
                        ORDEN := ORDEN + 1;
        END LOOP;
  END LOOP;
  CLOSE c_proced;
  COMMIT;
  
  OPEN c_fitxa;
  LOOP
      FETCH c_fitxa INTO fitxa;
      EXIT WHEN c_fitxa%NOTFOUND;
        dbms_output.put_line('fitxa:' || fitxa.doc_codfic);
        ORDEN := 1;
        FOR i IN (select * from rsc_docume where doc_codfic = fitxa.doc_codfic order by doc_orden) LOOP
                        update rsc_docume set doc_orden= ORDEN where doc_codi = i.doc_codi;
                        dbms_output.put_line('update rsc_docume set doc_orden=' || ORDEN || ' where doc_codi =' || i.doc_codi || ';');
                        ORDEN := ORDEN + 1;
        END LOOP;
  END LOOP;
  CLOSE c_fitxa;
  COMMIT;
  
  
END;