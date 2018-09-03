-- canales de presentacion . 
ALTER TABLE RSC_TRAMIT ADD TRA_CTELEM NUMBER(1,0) DEFAULT (0);
ALTER TABLE RSC_TRAMIT ADD TRA_CPRESE NUMBER(1,0) DEFAULT (0);

ALTER TABLE RSC_SERVIC ADD SER_CTELEM NUMBER(1,0) DEFAULT (0);
ALTER TABLE RSC_SERVIC ADD SER_CPRESE NUMBER(1,0) DEFAULT (0);
ALTER TABLE RSC_SERVIC ADD SER_CTELEF NUMBER(1,0) DEFAULT (0);

COMMENT ON COLUMN RSC_TRAMIT.TRA_CTELEM is 'Canal telematico.';
COMMENT ON COLUMN RSC_TRAMIT.TRA_CPRESE is 'Canal presencial.';

COMMENT ON COLUMN RSC_SERVIC.SER_CTELEM is 'Canal telematico.';
COMMENT ON COLUMN RSC_SERVIC.SER_CPRESE is 'Canal presencial.';
COMMENT ON COLUMN RSC_SERVIC.SER_CTELEF is 'Canal telefonico.';