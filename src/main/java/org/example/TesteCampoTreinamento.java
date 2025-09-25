package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;


public class TesteCampoTreinamento extends Base {

    @Before
    public void Iniciando() {
        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finalizando() {
        driver.quit();
    }


    private DSL dsl;


    @Test
    public void testeTextField() {

        //seleciono o elemento com que quero mexer
        dsl.escrever("elementosForm:nome", "Teste de escrita");


        //Para confirmar que o texto foi mesmo escrito,usamos o Attribute "value" que retorna o valor que foi escrito
        Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));

    }

    @Test
    public void deveInteragirComTextoArea() {

        //seleciono o elemento com que quero mexer
        dsl.escrever("elementosForm:sugestoes", "teste");

        //Para confirmar que retorna o valor que foi escrito
        Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));

    }

    @Test
    public void deveInteragirComRadioButton() {

        dsl.clicar("elementosForm:sexo:0");
        Assert.assertTrue(dsl.checarClick("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckbox() {


        dsl.clicar("elementosForm:comidaFavorita:2");

        Assert.assertTrue(dsl.checarClick("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {

        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");

        //Para validar o valor utilizado
        Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));

    }


    //Para verificar os valores disponiveis no Combo
    @Test
    public void verificarValoresDisponiveis() {


        //seleciono o elemento com que quero mexer
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();

        Assert.assertEquals(8, options.size()); // para validar qtd de opções

        boolean encontrou = false;
        for (WebElement option : options) {
            if (option.getText().equals("Superior")) {
                encontrou = true;
                break;
            }
        }

        Assert.assertTrue(encontrou); //Para saber se há um item especifico


    }


    @Test
    public void deveSelecionarMultiplos() {

        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Futebol");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");

        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Futebol");
        combo.selectByVisibleText("Corrida");

        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(3, allSelectedOptions.size()); // para validar qtd de opções

        combo.deselectByVisibleText("Corrida");

        allSelectedOptions = combo.getAllSelectedOptions();
        Assert.assertEquals(2, allSelectedOptions.size());

    }


    @Test
    public void deveInteragirComBotoes() {

        dsl.clicar("buttonSimple");

        WebElement button = driver.findElement(By.id("buttonSimple"));

        Assert.assertEquals("Obrigado!", button.getAttribute("value"));

    }

    @Test
    public void deveInteragirComLink() {

        dsl.clicarLink("Voltar");

        Assert.assertEquals("Voltou!", dsl.obterText("resultado"));
    }

    @Test
    public void deveBuscarTextos() {


        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.tagName("facilAchar")));


    }

}
