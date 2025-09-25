package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class DesafioCadastroCompleto extends Base {

    @Before
    public void Iniciando() {
        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

    }

    @After
    public void finalizando() {
        driver.quit();
    }

    @Test
    public void cadastroCompleto() {

        // Inicializando

        //nome
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Bianca");
        Assert.assertEquals("Bianca", driver.findElement(By.id("elementosForm:nome")).getAttribute("value")); // consultei

        //sobrenome
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves Pinheiro");
        Assert.assertEquals("Alves Pinheiro", driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value"));

        //sexo
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:1")).isSelected());

        //comida
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());

        //escolaridade
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element); // consultei

        combo.selectByVisibleText("2o grau completo");
        Assert.assertEquals("2o grau completo", combo.getFirstSelectedOption().getText()); //CONSULTEI

        //esportes
        WebElement elements = driver.findElement(By.id("elementosForm:esportes"));
        Select combos = new Select(elements); // consultei

        combos.selectByVisibleText("Natacao");
        combos.selectByVisibleText("Corrida");

        List<WebElement> allSelectedOptions = combos.getAllSelectedOptions(); // Consultei
        Assert.assertEquals(2, allSelectedOptions.size()); // para validar qtd de opções

        //cadastrar
        driver.findElement(By.id("elementosForm:cadastrar")).click();

     Assert.assertEquals("Cadastrado!\nNome: Bianca\nSobrenome: Alves Pinheiro\nSexo: Feminino\nComida: Pizza\nEscolaridade: 2graucomp\nEsportes: Natacao Corrida\nSugestoes:", driver.findElement(By.id("resultado")).getText());


    }

}
