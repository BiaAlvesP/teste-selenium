package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento extends Base {

    @Test
    public void testeTextField() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer
        //sendKeys = faz escrever no campo
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");

        //Para confirmar que o texto foi mesmo escrito,usamos o Attribute "value" que retorna o valor que foi escrito
        Assert.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));


        driver.quit();
    }

    @Test
    public void deveInteragirComTextoArea() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer
        //sendKeys = faz escrever no campo
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste"); // no textArea, posso escrever mais de uma linha "teste\nqa\ntestesss"

        //Para confirmar que retorna o valor que foi escrito
        Assert.assertEquals("teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));


        driver.quit();
    }

    @Test
    public void deveInteragirComRadioButton() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer
        driver.findElement(By.id("elementosForm:sexo:0")).click();


        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());


        driver.quit();
    }

    @Test
    public void deveInteragirComCheckbox() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer
        //sendKeys = faz escrever no campo
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());


        driver.quit();
    }

    @Test
    public void deveInteragirComCombo() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer
        //sendKeys = faz escrever no campo
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        // combo.selectByIndex(4); // por index
//          combo.selectByValue("superior"); // pelo value
        combo.selectByVisibleText("Superior"); // mais legal usar esse, porque é como o usuário visualiza

        //Para validar o valor utilizado
        Assert.assertEquals("Superior", combo.getFirstSelectedOption().getText());


        driver.quit();
    }


    //Para verificar os valores disponiveis no Combo
    @Test
    public void verificarValoresDisponiveis() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer
        //sendKeys = faz escrever no campo
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


        driver.quit();
    }


    @Test
    public void deveSelecionarMultiplos() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer
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

        driver.quit();
    }


    @Test
    public void deveInteragirComBotoes() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer

        WebElement button = driver.findElement(By.id("buttonSimple"));

        button.click();

        Assert.assertEquals("Obrigado!", button.getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComLink() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        //seleciono o elemento com que quero mexer

        driver.findElement(By.linkText("Voltar")).click();

        Assert.assertEquals("Voltou!",driver.findElement(By.id("resultado")).getText());


        driver.quit();
    }

    @Test
    public void deveBuscarTextos() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

       Assert.assertEquals("Campo de Treinamento",driver.findElement(By.tagName("h3")).getText());

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",driver.findElement(By.className("facilAchar")).getText());

        driver.quit();
    }

}
