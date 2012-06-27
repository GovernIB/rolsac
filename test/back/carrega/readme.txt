

webcaib-rolsacv1.jmx es un test plan de jmeter.

En la pantalla del test plan s'han adaptar les variables d'usuari a l'entorn particular:
- host
- port
- usuari
- contrasenya


En aquesta versió el test plan envia peticions per:

- (1 usuari)    Grabar una ficha #201214 en el backoffice 1.0 del rolsac 
- (10 usuaris ) Llegir RSS Noticies actualitat webcaib
- (100 usuaris) Buscar la paraula 'dona' en el buscador del webcaib


------------------

27.juliol.2012  S'ha descomposat el test webcaib-rolsacv1.jmx en varis arxius, per tal de modularitzar-los
i crear barreges de tests amb jmeter (anar a jmeter Archivo>Mezclar) . Així queda:

  * caib-planDePruebas.jmx : arxiu principal sobre el que es poden barrejar  altres  tests. 
  No conté cap test, nomes definicions (host, port...)   
 
  * webcaib-buscarParaulesClau: test de carrega que envia peticions al buscador de la caib. Les paraules
  clau s'agafen de l'arxiu paraulesClau.csv 
  
  * webcaib-rssNoticiesActualitat.jmx: test de carrega que envia peticions al lector de rss de la caib.
  
  * rolsac1.0-grabarFichaSimple.jmx: test de carrega que graba els camps simples d'una fitxa sobre rolsac1.0
  Llegeix les variables d'usuari:
  	- usuari
	- contrasenya
  
  