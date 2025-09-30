package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DesafioRegrasNegocio extends Base {

    private CampoTreinamentoPage page;
    private WebDriverWait wait;
    private DSL dsl;

    @Before
    public void Iniciando() {
        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);

    }


    @After
    public void finalizando() {
        driver.quit();

    }

    @Test
    public void desafioRegraNegocio() {


        // erro nome
        page.cadastrar();
        Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());

        page.setNome("Bianca");

        // erro sobrenome
        page.cadastrar();
        Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());

        page.setSobrenome("Alves");


        // erro sexo
        page.cadastrar();

        Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());

        page.setSexoFeminino();

        //erro carne + vegetariano
        page.setComidaFavoritaCarne();
        page.setComidaFavoritaVegetariano();

        page.cadastrar();

        Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());


        page.setComidaFavoritaVegetariano();


        //erro esporte
        page.setEsportes("Natacao","O que eh esporte?");

        page.cadastrar();


        Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());

        dsl.deSelecionarCombo("elementosForm:esportes", "O que eh esporte?");

        page.cadastrar();

    }


}
