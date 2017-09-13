 /** Actualiza el estado de SIA a ALTA de aquellos que ya tengan código SIA y estén si estado. **/                                            
update rsc_proced
     set pro_estsia = 'A'
  where pro_codsia is not null
    and pro_estsia is null;                                                  
 
    
 /** Actualiza la imagen, icono, banner de todas las traducciones en catalán con la que tenga la ficha. **/
UPDATE RSC_TRAFIC TRADUCCION_FICHA
   SET (TRADUCCION_FICHA.TFI_IMAGEN, 
        TRADUCCION_FICHA.TFI_BANNER, 
        TRADUCCION_FICHA.TFI_ICONO) = (SELECT FICHA.FIC_IMAGEN, FICHA.FIC_BANER, FICHA.FIC_ICONO 
                                                FROM RSC_FICHA FICHA
                                               WHERE FICHA.FIC_CODI = TRADUCCION_FICHA.TFI_CODFIC
                                               )
 WHERE TRADUCCION_FICHA.TFI_CODIDI = 'ca';
 