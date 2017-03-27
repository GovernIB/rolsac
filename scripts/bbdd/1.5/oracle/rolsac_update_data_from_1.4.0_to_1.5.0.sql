 /** Actualiza el estado de SIA a ALTA de aquellos que ya tengan código SIA y estén si estado. **/                                            
update rsc_proced
     set pro_estsia = 'A'
  where pro_codsia is not null
    and pro_estsia is null;                                                  
 