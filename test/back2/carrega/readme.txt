testPlan.jmx és un pla de test de JMeter amb la configuració necessària per poder anar afegint més càrregues de test (anar a jmeter Archivo>Mezclar).

A la pantalla del pla de test s'han d'adaptar les variables d'usuari a l'entorn particular:

- host
- port
- usuari
- contrasenya


-----------------------------------------------------------------------------------------------------------------------------------
Els següents tests estan configurats amb un usuari que fa una petició. Augmentar el nombre segons les necessitats de cada execució. 
-----------------------------------------------------------------------------------------------------------------------------------

--- Generals ---

* loginThreadGrpup.jmx: test per fer login una vegada. Després del login es carrega la pantalla de quadre de control.

* seleccionarUAThreadGroup: test per seleccionar la unitat administrativa amb id 1 "Govern de les Illes Balears".


--- Procediments ---

* llistarProcedimentsThreadGroup.jmx: test per mostrar el llistat de procediments.

* cercaProcedimentsThreadGroup.jmx: test per fer una cerca de procediments amb alguns filtres.

* cercaProcedimentsAmbUAsThreadGroup.jmx: test per fer una cerca de procediments amb alguns filtres, incloent la cerca a les UAs filles.

* crearProcedimentThreadGroup.jmx: test per crear un nou procediment.

* modificarProcedimentThreadGroup.kmx: test per modificar un procediment.


--- UAs ---

* crearUAThreadGroup.jmx: test per crear una nova UA.

* modificarUAThreadGroup.jmx: test per modificar una UA.


--- Personal ---

* llistarPersonalThreadGroup.jmx: test per mostrar el llistat de personal.

* cercaPersonalThreadGroup.jmx: test per fer una cerca de personal.

* crearPersonalThreadGroup.jmx: test per crear personal.

* modificarPersonalThreadGroup.jmx: test per modificar personal.


--- Fitxa ---

* llistarFitxesThreadGroup.jmx: test per mostrar el llistat de fitxes.

* cercaFitxesThreadGroup.jmx: test per fer una cerca de fitxes.

* crearFitxaThreadGroup.jmx: test per crear una fitxa.

* modificarFitxaThreadGroup.jmx: test per modificar una fitxa.


--- Normativa ---

* llistarNormativesThreadGroup.jmx: test per mostrar el llistat de normatives.

* cercaNormativesThreadGroup.jmx: test per fer una cerca de normatives.

* crearNormativaThreadGroup.jmx: test per crear una normatvia.

* modificarNormativaThreadGroup.jmx: test per modificar una normatvia.


--- Usuaris ---

* llistarUsuarisThreadGroup.jmx: test per mostrar el llistat de usuaris.

* cercaUsuarisThreadGroup.jmx: test per fer una cerca de usuaris.

* crearUsuariThreadGroup.jmx: test per crear un usuari.

* modificarUsuariThreadGroup.jmx: test per modificar un usuari.


--- Fets vitals ---

* llistarFetsVitalsThreadGroup.jmx: test per mostrar el llistat de fets vitals.

* pujarFetVitalThreadGroup.jmx: test per pujar un de fet vital.

* crearFetVitalThreadGroup.jmx: test per crear un fet vital.

* modificarFetVitalThreadGroup.jmx: test per modificar un fet vital.


--- Seccions ---

* llistarSeccionsThreadGroup.jmx: test per mostrar el llistat de seccions.

* pujarSeccionThreadGroup.jmx: test per pujar una secció.

* crearSeccioThreadGroup.jmx: test per crear una secció.

* modificarSeccioThreadGroup.jmx: test per modificar una secció.


--- Espais territorials ---

* llistarEspaisTerritorialsThreadGroup.jmx: test per mostrar el llistat de espais territorials.

* crearEspaiTerritorialThreadGroup.jmx: test per crear un espai territorial.

* modificarEspaiTerritorialThreadGroup.jmx: test per modificar un espai territorial.
