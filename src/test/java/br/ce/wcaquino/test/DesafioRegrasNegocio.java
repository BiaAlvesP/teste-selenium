package br.ce.wcaquino.test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DesafioRegrasNegocio extends BaseTest {

    private CampoTreinamentoPage page;
    private WebDriverWait wait;
    private DSL dsl;

    @Before
    public void iniciando() {
        String caminho = System.getProperty("user.dir")
                + "/src/main/resources/componentes.html";

        System.out.println("Caminho: " + caminho);

        DriverFactory.getDriver().get("file:///" + caminho);

        System.out.println("URL atual: " +
                DriverFactory.getDriver().getCurrentUrl());

        wait = new WebDriverWait(
                DriverFactory.getDriver(),
                Duration.ofSeconds(5)
        );

        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }



    @Test
    public void desafioRegraNegocio() {


        // Erro nome obrigatório
        page.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());

        page.setNome("Bianca");

        // erro sobrenome obrigatório
        page.cadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());

        page.setSobrenome("Alves");


        // erro sexo obrigatório
        page.cadastrar();

        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());

        page.setSexoFeminino();

        //erro ao escolher carne, não se pode selecionar vegetariano
        page.setComidaFavoritaCarne();
        page.setComidaFavoritaVegetariano();

        page.cadastrar();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());


        page.setComidaFavoritaVegetariano();


        //erro esporte 1 opção que faz e outra não
        page.setEsportes("Natacao","O que eh esporte?");

        page.cadastrar();


        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());

        dsl.deSelecionarCombo("elementosForm:esportes", "O que eh esporte?");

        page.cadastrar();

    }


}
