package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;


import java.util.Arrays;
import java.util.List;


public class TesteCampoTreinamento extends Base {

    @Before
    public void Iniciando() {
        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @After
    public void finalizando() {
        driver.quit();
    }


    private DSL dsl;
    private CampoTreinamentoPage page;


    @Test
    public void testeTextField() {

        page.setNome("Teste de escrita");
        //seleciono o elemento com que quero mexer

        //Para confirmar que o texto foi mesmo escrito,usamos o Attribute "value" que retorna o valor que foi escrito
        Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));

    }

    @Test
    public void deveInteragirComTextoArea() {

       dsl.escrever(By.id("elementosForm:sugestoes"),"teste" );

        //Para confirmar que retorna o valor que foi escrito
        Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));

    }

    @Test
    public void deveInteragirComRadioButton() {

        page.setSexoMasculino();
        Assert.assertTrue(dsl.checarClick("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckbox() {


        page.setComidaFavoritaPizza();

        Assert.assertTrue(dsl.checarClick("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {


        page.setEscolaridade("Superior");

        //Para validar o valor utilizado
        Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));

    }


    @Test
    public void verificarValoresDisponiveis() {

        Assert.assertEquals(8, dsl.obterQtdOpcaoCombo("elementosForm:escolaridade")); // para validar qtd de opções
        Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Superior")); // para validar se existe certa opções
    }


    @Test
    public void deveSelecionarMultiplos() {

        page.setEsportes("Natacao");
        page.setEsportes("Futebol");
        page.setEsportes("Corrida");

        List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(3, opcoesMarcadas.size()); // para validar qtd de opções

        dsl.deSelecionarCombo("elementosForm:esportes", "Corrida");

        opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(2, opcoesMarcadas.size());
        Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "Futebol")));
    }


    @Test
    public void deveInteragirComBotoes() {

        dsl.clicar("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));

    }

    @Test
    public void deveInteragirComLink() {

        dsl.clicarLink("Voltar");

        Assert.assertEquals("Voltou!", dsl.obterText("resultado"));
    }

    @Test
    public void deveBuscarTextos() {


        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));


    }

}
