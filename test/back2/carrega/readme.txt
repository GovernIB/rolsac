testPlan.jmx �s un pla de test de JMeter amb la configuraci� necess�ria per poder anar afegint m�s c�rregues de test (anar a jmeter Archivo>Mezclar).

A la pantalla del pla de test s'han d'adaptar les variables d'usuari a l'entorn particular:

- host
- port
- usuari
- contrasenya


-----------------------------------------------------------------------------------------------------------------------------------
Els seg�ents tests estan configurats amb un usuari que fa una petici�. Augmentar el nombre segons les necessitats de cada execuci�. 
-----------------------------------------------------------------------------------------------------------------------------------

* loginThradGrpup.jmx: test per fer login una vegada. Despr�s del login es carrega la pantalla de quadre de control.

* seleccionarUAThreadGroup: test per seleccionar la unitat administrativa amb id 1 "Govern de les Illes Balears".
