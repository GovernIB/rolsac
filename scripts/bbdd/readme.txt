
- rolsac_create_schema.sql 
Arxiu d'exportació per branques noves, conté la última versió de la branca. 

- rolsac_update_schema_from_XXX_to_YYY.sql 
Arxiu d'exportació dins la branca, per versions fins a XXX. 
És una actualització de la branca a la versió YYY.


_______________________________________
Instruccions per exportar scripts sql

Exemples:

cas 1) No tenim cap instal.lació de rolsac. Volem instal.lar rolsac 1.0.
 Exportar  bbdd/1.0/oracle/rolsac_create_schema.sql

cas 2) No tenim cap instal.lació de rolsac. Volem instal.lar rolsac 1.1.
 Exportar  bbdd/1.1/oracle/rolsac_create_schema.sql

cas 3) Ja tenim instal.lat rolsac 1.0.2. Volem actualitzar a rolsac 1.0.4 (última versió de la branca)
 Exportar, si existeix  bbdd/1.0/rolsac_update_from_1.0.2_to_1.0.3.sql 
 Exportar, si existeix  bbdd/1.0/rolsac_update_from_1.0.3_to_1.0.4.sql 
 
cas 3) Ja tenim instal.lat rolsac 1.0.2. 1.0.4 és la última versió. Volem actualitzar a rolsac 1.1.
 Exportar, si existeix  bbdd/1.0/rolsac_update_from_1.0.2_to_1.0.3.sql 
 Exportar, si existeix  bbdd/1.0/rolsac_update_from_1.0.3_to_1.0.4.sql 

cas 4) Ja tenim instal.lat rolsac 1.1.0. Volem actualitzar a rolsac 1.1.1.
 Exportar  bbdd/1.1/rolsac_update_from_1.1.0_to_1.1.1.sql  
 


_________________________________________
Instruccions per generar scripts sql


1) Executar el target initdb en build-bd.xml
Això genera un output/schema.sql
Això no modifica la bbdd (perquè text=true) 

2a) Si es la primera versió de la branca:
  2a 1) Crear un directori amb el numero de versió 
  2a 2) Crear rolsac_create_schema.sql amb el schema.sql generat.
 
 
2b) Si no es la primera versió de la branca:
  2b 1) Generar un arxiu rolsac_update_schema_from_XXX_to_YYY.sql  
amb les diferències entre schema.sql i rolsac_create_schema.sql 
  2b 2) Sobreescriure rolsac_create_schema.sql amb el schema.sql generat.


