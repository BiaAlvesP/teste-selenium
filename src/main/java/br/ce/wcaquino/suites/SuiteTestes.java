package br.ce.wcaquino.suites;

import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.test.DesafioRegrasNegocio;
import br.ce.wcaquino.test.TesteAlert;
import br.ce.wcaquino.test.TesteCampoTreinamento;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

// dessa forma rodamos todos os testes de nossa suide e
// eu defino a ordem que as classess são executadas


@RunWith(Suite.class)
@Suite.SuiteClasses({
        TesteCampoTreinamento.class,
        TesteAlert.class,
        DesafioRegrasNegocio.class})

public class SuiteTestes {
    @AfterClass
    public static void finalizaTudo(){
        DriverFactory.killDriver();
    }

}
