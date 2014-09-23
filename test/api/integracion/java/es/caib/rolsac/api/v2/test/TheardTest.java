package es.caib.rolsac.api.v2.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.CertificadoUtil;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.rolsac.RolsacQueryService;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class TheardTest {

    RolsacQueryService rolsacQS;
    private static int NUMPROCS = 60;

    @Before
    public void setup() {
        rolsacQS = (RolsacQueryService) BeanUtils.getAdapter("rolsac", STRATEGY.WS);
        CertificadoUtil.autentificar("contrasena", "storerolsac.jks");
    }

    @Test
    public void testCase() throws InterruptedException {

        Runnable runnable1 = new Runnable() {
            public void run() {
                try {
                    UnitatAdministrativaCriteria unitatAdministrativaCriteria = new UnitatAdministrativaCriteria();
                    unitatAdministrativaCriteria.setId("1");
                    UnitatAdministrativaQueryServiceAdapter unitatAdministrativa;
                    unitatAdministrativa = rolsacQS.obtenirUnitatAdministrativa(unitatAdministrativaCriteria);
                    List<Long> uaHijas = unitatAdministrativa.llistarDescendents();
                    System.out.println("PID:" + Thread.currentThread().getId() + " -Num. Descendents:" + uaHijas.size());

                } catch (QueryServiceException e) {
                    e.printStackTrace();
                }
            }
        };

        ArrayList<Thread> a = new ArrayList<Thread>();

        for (int i = 0; i < NUMPROCS; i++) {
            a.add(new Thread(runnable1));
            a.get(i).start();
        }

        for (int i = 0; i < NUMPROCS; i++) {
            a.get(i).join();
        }
    }

}
