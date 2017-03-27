SET SERVEROUTPUT ON;
DECLARE
  CURSOR cFichasImagen
      IS
          SELECT *
           FROM RSC_TRAFIC
          WHERE TFI_CODIDI != 'ca'
            AND TFI_IMAGEN IN (
                    select tfi_imagen
                     from rsc_trafic
                    where tfi_imagen is not null
                    group by tfi_imagen
                   having count(*) >= 2
            )
            ORDER BY TFI_codfic asc;
 
  CURSOR cFichasBanner
      IS
           SELECT *
             FROM RSC_TRAFIC
            WHERE TFI_CODIDI != 'ca'
              AND TFI_banner IN (
                    select TFI_banner
                     from rsc_trafic
                    where TFI_banner is not null
                    group by TFI_banner
                   having count(*) >= 2
            )
            ORDER BY TFI_codfic asc;
  CURSOR cFichasIcono
      IS
           SELECT *
             FROM RSC_TRAFIC
            WHERE TFI_CODIDI != 'ca'
              AND TFI_icono IN (
                    select TFI_icono
                     from rsc_trafic
                    where TFI_icono is not null
                    group by TFI_icono
                   having count(*) >= 2
            )
            ORDER BY TFI_codfic asc;
     idNuevo NUMBER;
     cuantos NUMBER := 0;
BEGIN
  dbms_output.put_line('empieza');
  cuantos := 0;
  FOR ficha IN cFichasImagen
  LOOP
    cuantos := cuantos +1;
    if mod(cuantos,50) = 0
    then
        commit;
    end if;
    
      --Obtenemos el nuevo valor de la secuencia
      select RSC_SEQ_ALL.NEXTVAL
        into idNuevo
        from dual;
        
      --Generamos el objeto archivo a partir del ya existente.
      INSERT INTO RSC_ARCHIV ("ARC_CODI",
                              "ARC_NOMBRE",
                              "ARC_MIME",
                              "ARC_PESO",
                              "ARC_DATOS")
                    SELECT idNuevo, arc_nombre, arc_mime, arc_peso, arc_datos
                      from rsc_archiv where arc_codi = ficha.tfi_imagen;
                      
      --Actualizamos la traducción de la ficha con el nuevo valor de imagen.
      update rsc_trafic
         set tfi_imagen = idNuevo
       where tfi_codfic = ficha.tfi_codfic
         and tfi_codidi = ficha.tfi_codidi
         and tfi_imagen = ficha.tfi_imagen;
  END LOOP;
  COMMIT;
  
  cuantos := 0;
  FOR ficha IN cFichasBanner
  LOOP
  
    cuantos := cuantos +1;
    if mod(cuantos,50) = 0
    then
        commit;
    end if;
    
     --Obtenemos el nuevo valor de la secuencia
      select RSC_SEQ_ALL.NEXTVAL
        into idNuevo
        from dual;
        
        
         --Generamos el objeto archivo a partir del ya existente.
      INSERT INTO RSC_ARCHIV ("ARC_CODI",
                              "ARC_NOMBRE",
                              "ARC_MIME",
                              "ARC_PESO",
                              "ARC_DATOS")
                    SELECT idNuevo, arc_nombre, arc_mime, arc_peso, arc_datos
                      from rsc_archiv where arc_codi = ficha.tfi_banner;
    
     --Actualizamos la traducción de la ficha con el nuevo valor de banner.
      update rsc_trafic
         set tfi_banner = idNuevo
       where tfi_codfic = ficha.tfi_codfic
         and tfi_codidi = ficha.tfi_codidi
         and tfi_banner = ficha.tfi_banner;
  END LOOP;
  
  
  cuantos := 0;
  FOR ficha IN cFichasIcono
  LOOP
    cuantos := cuantos +1;
    if mod(cuantos,50) = 0
    then
        commit;
    end if;
    
     --Obtenemos el nuevo valor de la secuencia
      select RSC_SEQ_ALL.NEXTVAL
        into idNuevo
        from dual;
        
        
         --Generamos el objeto archivo a partir del ya existente.
      INSERT INTO RSC_ARCHIV ("ARC_CODI",
                              "ARC_NOMBRE",
                              "ARC_MIME",
                              "ARC_PESO",
                              "ARC_DATOS")
                    SELECT idNuevo, arc_nombre, arc_mime, arc_peso, arc_datos
                      from rsc_archiv where arc_codi = ficha.tfi_icono;
    
     --Actualizamos la traducción de la ficha con el nuevo valor de banner.
      update rsc_trafic
         set tfi_icono = idNuevo
       where tfi_codfic = ficha.tfi_codfic
         and tfi_codidi = ficha.tfi_codidi
         and tfi_icono = ficha.tfi_icono;
  END LOOP;
  
  
  
  dbms_output.put_line('fin');

END;