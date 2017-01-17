    UPDATE RSC_TRAFIC traduccionFicha
       SET (tfi_banner, tfi_imagen, tfi_icono) = (SELECT ficha.fic_baner, ficha.fic_imagen, ficha.fic_icono
                                                    FROM RSC_FICHA ficha
                                                   WHERE traduccionficha.tfi_codfic = ficha.fic_codi);