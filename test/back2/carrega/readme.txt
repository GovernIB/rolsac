testPlan.jmx és un pla de test de JMeter amb la configuració necessària per poder anar afegint més càrregues de test (anar a jmeter Archivo>Mezclar).

A la pantalla del pla de test s'han d'adaptar les variables d'usuari a l'entorn particular:

- host
- port
- usuari
- contrasenya


-----------------------------------------------------------------------------------------------------------------------------------
Els següents tests estan configurats amb un usuari que fa una petició. Augmentar el nombre segons les necessitats de cada execució. 
-----------------------------------------------------------------------------------------------------------------------------------

* loginThradGrpup.jmx: test per fer login una vegada. Després del login es carrega la pantalla de quadre de control.

* seleccionarUAThreadGroup: test per seleccionar la unitat administrativa amb id 1 "Govern de les Illes Balears".
