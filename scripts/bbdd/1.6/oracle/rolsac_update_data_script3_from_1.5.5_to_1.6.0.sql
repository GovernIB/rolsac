/***
  SCRIPT PARA COMPROBAR NORMATIVAS. 
 ***/
SET SERVEROUTPUT ON;
DECLARE
  CURSOR cNormativas 
      IS 
	SELECT NOR_CODI AS ID 
        FROM RSC_NORMAT
	WHERE NOR_DATVAL IS NULL; 
		
  INCORRECTO_VALIDA       NUMBER;
  INCORRECTO_TIPO         NUMBER;
  INCORRECTO_TIPO_BOLETIN NUMBER;
  INCORRECTO_NORFEC		  NUMBER;
  
  TOTAL_INCORRECTO        NUMBER := 0;
  TOTAL_CORRECTO          NUMBER := 0;
BEGIN
  FOR normativa IN cNormativas
  LOOP
	/* NORMATIVA SENSE VIGÈNCIA */
        SELECT COUNT(*) 
          INTO INCORRECTO_VALIDA
          FROM RSC_NORMAT
         WHERE normativa.ID = NOR_CODI AND (NOR_VALIDN IS NULL OR NOR_VALIDN != 1);
           
        /* NORMATIVA SENSE TIPUS DE NORMA */
	SELECT COUNT(*) 
          INTO INCORRECTO_TIPO
          FROM RSC_NORMAT
         WHERE NOR_CODI = normativa.ID AND NOR_CODTIP IS NULL;
        
	/* NORMATIVA SENSE TIPUS DE BUTLLETÍ */
        SELECT COUNT(*) 
          INTO INCORRECTO_TIPO_BOLETIN
          FROM RSC_NORMAT
         WHERE NOR_CODI = normativa.ID AND NOR_CODBOL IS NULL; 
		 
	/* NORMATIVA SENSE DATA DE PUBLICACIÓ */
	SELECT COUNT(*)
	  INTO INCORRECTO_NORFEC
	  FROM RSC_NORMAT
	WHERE NOR_CODI = normativa.ID AND NOR_FECHA IS NULL;
		
        IF  INCORRECTO_VALIDA = 0 AND
            INCORRECTO_TIPO  = 0  AND
            INCORRECTO_TIPO_BOLETIN = 0 AND
	        INCORRECTO_NORFEC = 0
			
        THEN
            TOTAL_CORRECTO := TOTAL_CORRECTO + 1;
            UPDATE RSC_NORMAT SET NOR_DATVAL = 1 WHERE NOR_CODI = normativa.ID;
            DBMS_OUTPUT.PUT_LINE('NORMATIVA CON CODI: ' || normativa.ID || ' ESTA CORRECTO.');
        ELSE 
            TOTAL_INCORRECTO := TOTAL_INCORRECTO + 1;
            UPDATE RSC_NORMAT SET NOR_DATVAL = 0 WHERE NOR_CODI = normativa.ID;
            DBMS_OUTPUT.PUT_LINE('NORMATIVA CON CODI: ' || normativa.ID || ' ES INCORRECTO.');
        END IF;
               
  END LOOP;
  
  UPDATE RSC_NORMAT SET NOR_VALIDN = 1 WHERE NOR_VALIDN = 4;
  DBMS_OUTPUT.PUT_LINE('EN TOTAL. CORRECTO: ' || TOTAL_CORRECTO || ' INCORRECTOS: ' || TOTAL_INCORRECTO);
END;