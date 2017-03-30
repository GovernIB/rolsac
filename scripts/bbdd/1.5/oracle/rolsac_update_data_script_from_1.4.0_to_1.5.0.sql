set serveroutput on;
declare
    CURSOR cFICHAS IS
        SELECT *
          FROM RSC_FICHA
         WHERE FIC_BANER IS NOT NULL
            OR FIC_IMAGEN IS NOT NULL
            OR FIC_ICONO IS NOT NULL;
  cIdIdioma VARCHAR2(2);
  cExisteCat NUMBER;
  cExisteEsp NUMBER;
  cExiste    NUMBER;
  cuantos    NUMBER := 0;
begin
    
    cuantos := cuantos +1 ;
    IF MOD (cuantos, 40) = 0
    then 
        commit;
    END IF;
    
    
    DBMS_OUTPUT.PUT_LINE(' INICIO ' );
    FOR ficha IN cFICHAS
    LOOP
        select count(*)
          into cExisteCat
          from rsc_trafic
         where tfi_codidi = 'ca'
           and tfi_codfic = ficha.fic_codi;
        
         select count(*)
          into cExisteEsp
          from rsc_trafic
         where tfi_codidi = 'es'
           and tfi_codfic = ficha.fic_codi;
         
          select count(*)
          into cExiste
          from rsc_trafic
         where tfi_codidi = 'ca'
           and tfi_codfic = ficha.fic_codi;  
           
        IF cExisteCat = 1
        THEN
            cIdIdioma := 'ca';
        ELSIF cExisteEsp = 1
        THEN
            cIdIdioma := 'es';
        ELSIF cExiste > 0 
        THEN
             select TFI_CODIDI
              into cIdIdioma
              from rsc_trafic
             where tfi_codfic = ficha.fic_codi
               and rownum = 1;  
        ELSE 
            cIdIdioma := null;
        END IF;
        
        
        
        
        IF cIdIdioma IS NOT NULL
        THEN
            UPDATE RSC_TRAFIC
               SET TFI_IMAGEN = FICHA.FIC_IMAGEN,
                   TFI_BANNER = FICHA.FIC_BANER,
                   TFI_ICONO  = FICHA.FIC_ICONO
             WHERE TFI_CODIDI = cIdIdioma
               AND TFI_CODFIC = ficha.fic_codi;
        END IF;

        /*DBMS_OUTPUT.PUT_LINE(' FICHA ' || ficha.fic_codi || ' IDIOMA:' || cIdIdioma || ' AFECTADOS:' || sql%rowcount);*/
        
    END LOOP;
    
    commit;
    DBMS_OUTPUT.PUT_LINE(' FIN ' );
end;