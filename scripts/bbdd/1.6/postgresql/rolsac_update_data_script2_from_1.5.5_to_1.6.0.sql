SET SERVEROUTPUT ON;
---SCRIPT PARA RELLENAR EL ENLACE DE LAS TRADUCCIONES SIN EL ENLACE RELLENO
DECLARE
    cursor normativas is 
         select * 
          from rsc_normat
         where nor_codi in (select tno_codnor from rsc_tranor where tno_enlace is null and tno_titulo is not null);
    cursor traducciones (cId NUMBER)is
        select *
          from rsc_tranor
         where tno_titulo is not null
           and tno_enlace is null
           and tno_codnor = cId; -- Comprobamos si el titulo no es nulo para quitar las traducciones sin rellenar.
           
    nAnyo NUMBER;
    nUA   NUMBER;
    
BEGIN
  DBMS_OUTPUT.PUT_LINE ('INICIO');
  FOR normativa IN normativas
  LOOP 
     DBMS_OUTPUT.PUT_LINE ('Normativa.id:' || normativa.nor_codi);
     FOR traduccion IN traducciones(normativa.nor_codi)
      LOOP
                IF normativa.nor_codbol is null AND normativa.nor_fecha is not null
                THEN 
                    nAnyo := to_number(to_char(normativa.nor_fecha, 'yyyy'));
                
                    UPDATE RSC_TRANOR
                       SET TNO_ENLACE = '/eboibfront/VisPdf?action=VisHistoric' || chr(38) || 'p_any=' || nAnyo || chr(38) || 'p_numero=' || normativa.nor_numero || chr(38) || 'p_finpag=' || traduccion.tno_pagfin || chr(38) || 'p_inipag=' || traduccion.tno_pagini || chr(38) || 'idDocument=' || +normativa.nor_codi || chr(38) || 'lang=' || traduccion.tno_codidi
                     WHERE TNO_CODIDI = traduccion.TNO_CODIDI
                       AND TNO_CODNOR = traduccion.TNO_CODNOR;
                ELSE
                
                    nUA := '';
                    IF normativa.nor_coduna IS NOT NULL
                    THEN
                        nUA := TO_CHAR (normativa.nor_coduna);
                    END IF;
                
                    UPDATE RSC_TRANOR
                       SET TNO_ENLACE = '/govern/dadesNormativa.do' || chr(38) || 'lang=' || traduccion.tno_codidi || chr(38) || 'codi=' || normativa.nor_codi || chr(38) || 'coduo=' || nUA
                     WHERE TNO_CODIDI = traduccion.TNO_CODIDI
                       AND TNO_CODNOR = traduccion.TNO_CODNOR;
                END IF;
                
                
      END LOOP;
  END LOOP;
  
  DBMS_OUTPUT.PUT_LINE ('FIN');
  COMMIT;
END;


