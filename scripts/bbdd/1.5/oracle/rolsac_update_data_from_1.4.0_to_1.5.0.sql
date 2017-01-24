    UPDATE RSC_TRAFIC traduccionFicha
       SET (tfi_banner, tfi_imagen, tfi_icono) = (SELECT ficha.fic_baner, ficha.fic_imagen, ficha.fic_icono
                                                    FROM RSC_FICHA ficha
                                                   WHERE traduccionficha.tfi_codfic = ficha.fic_codi);
                                                   
       /** Actualiza el estado de SIA a ALTA de aquellos que ya tengan código SIA y estén si estado. **/                                            
update rsc_proced
     set pro_estsia = 'A'
  where pro_codsia is not null
    and pro_estsia is null;                                                   