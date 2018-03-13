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
    nURL  RSC_TRANOR.TNO_ENLACE%TYPE;
    cuantos NUMBER;
    cuantosAct NUMBER := 0;
    cuantosNul NUMBER := 0;
BEGIN
  DBMS_OUTPUT.PUT_LINE ('INICIO');
  FOR normativa IN normativas
  LOOP 
     
     FOR traduccion IN traducciones(normativa.nor_codi)
     LOOP
              select count(*)
                into cuantos
                from rolsac.rsc_tranor tn, rolsac.rsc_normat n
              where tn.tno_pagini is not null
              and tn.tno_pagini!=0
              and tn.tno_pagfin is not null
              and tn.tno_pagfin!=0
              and n.nor_numero is not null
              and length(n.nor_numero)>=5
              and tn.tno_codnor =  n.nor_codi
              and tn.tno_codidi =  traduccion.TNO_CODIDI
              and n.nor_codi = normativa.nor_codi;
          
              IF cuantos = 1
              THEN
                    select nvl(tno_enlace, 'http://www.caib.es/eboibfront/pdf/VisPdf?action=VisHistoric' ||  chr(38) || 'p_any=' || substr(nor_numero,0,4) ||  chr(38) || 'p_numero=' || substr(nor_numero,5,3)
                         ||  chr(38) || 'p_finpag=' || tno_pagfin ||  chr(38) || 'p_inipag=' || tno_pagini || chr(38) || 'lang=' || traduccion.TNO_CODIDI)
                      into nURL
                    from rolsac.rsc_tranor tn, rolsac.rsc_normat n
                    where tn.tno_pagini is not null
                    and tn.tno_pagini!=0
                    and tn.tno_pagfin is not null
                    and tn.tno_pagfin!=0
                    and n.nor_numero is not null
                    and length(n.nor_numero)>=5
                    and tn.tno_codnor =  n.nor_codi
                    and tn.tno_codidi =  traduccion.TNO_CODIDI
                    and n.nor_codi = normativa.nor_codi;
                
                    IF nURL IS NOT NULL
                    THEN
                          UPDATE RSC_TRANOR
                             SET TNO_ENLACE = nURL
                           WHERE TNO_CODIDI = traduccion.TNO_CODIDI
                             AND TNO_CODNOR = traduccion.TNO_CODNOR;
                      
                    END IF;
                  DBMS_OUTPUT.PUT_LINE ('Normativa.id:' || normativa.nor_codi || ' Actualizada URL');
                  cuantosAct := cuantosAct + 1;
            ELSE 
                  DBMS_OUTPUT.PUT_LINE ('Normativa.id:' || normativa.nor_codi || ' URL Nula');
                  cuantosNul := cuantosNul + 1;
            END IF;
      END LOOP;
  END LOOP;
  DBMS_OUTPUT.PUT_LINE ('Normativas URL actualizadas ' || cuantosAct); 
  DBMS_OUTPUT.PUT_LINE ('Normativas URL nulas  ' || cuantosNul);
  DBMS_OUTPUT.PUT_LINE ('FIN');
  COMMIT;
END;


