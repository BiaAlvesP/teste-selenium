package org.example;

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


}
