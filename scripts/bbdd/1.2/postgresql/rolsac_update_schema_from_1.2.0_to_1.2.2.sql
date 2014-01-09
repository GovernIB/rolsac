﻿-- VISTA RSC_TRANOR
CREATE VIEW RSC_TRANOR AS
SELECT 
  TNE_CODNOR as TNO_CODNOR,
  TNE_SECCIO AS TNO_SECCIO,
  TNE_APARTA AS TNO_APARTA,
  TNE_PAGINI AS TNO_PAGINI,
  TNE_PAGFIN AS TNO_PAGFIN,
  TNE_TITULO AS TNO_TITULO,
  TNE_ENLACE AS TNO_ENLACE,
  TNE_RESPON AS TNO_RESPON,
  TNE_CODARC AS TNO_CODARC,
  TNE_OBSERV AS TNO_OBSERV,
  TNE_CODIDI AS TNO_CODIDI 
FROM RSC_TRANOE
UNION
SELECT 
  TNL_CODNOR,TNL_SECCIO,TNL_APARTA,TNL_PAGINI,TNL_PAGFIN,TNL_TITULO,TNL_ENLACE,null,TNL_CODARC,TNL_OBSERV,TNL_CODIDI 
FROM RSC_TRANOL;

