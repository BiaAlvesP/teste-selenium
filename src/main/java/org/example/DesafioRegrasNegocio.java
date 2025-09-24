package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DesafioRegrasNegocio extends Base {

    @Test
    public void desafioRegraNegocio() {

        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        // erro nome
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Alert alert = driver.switchTo().alert();
        Assert.assertEquals("Nome eh obrigatorio", alert.getText());
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Bianca");

        // erro sobrenome
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        alert.accept();

        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Alves");


        // erro sexo
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
        alert.accept();

        driver.findElement(By.id("elementosForm:sexo:1")).click();

        //erro carne + vegetariano
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        alert.accept();

        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        //erro esporte
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);

        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        alert = driver.switchTo().alert();
        Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
        alert.accept();

        combo.deselectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        driver.quit();
    }


}
