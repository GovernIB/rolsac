--Incluye la ua raiz del procedimiento.
ALTER TABLE RSC_SIAPDT ADD SIP_SIAUA NUMBER(19,0);
ALTER TABLE RSC_SIAPDT ADD CONSTRAINT RSC_SIP_SIAUA_FK FOREIGN KEY (SIP_SIAUA) REFERENCES RSC_SIAUA(SUA_CODI); 