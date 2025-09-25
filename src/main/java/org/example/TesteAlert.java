package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TesteAlert extends Base {

    private WebDriverWait wait;

    @Before
    public void Iniciando() {
        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        // cria uma espera de até 10 segundos
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void finalizando() {
        driver.quit();
    }

    @Test
    public void deveInteragirComAlertSimples() {
        driver.findElement(By.id("alert")).click();

        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        String texto = alerta.getText();
        alerta.accept();

        Assert.assertEquals("Alert Simples", texto);
        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
    }

    @Test
    public void deveInteragirComAlertConfirm() {
        driver.findElement(By.id("confirm")).click();

        // primeiro alerta
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("Confirm Simples", alerta.getText());
        alerta.accept();

        // segundo alerta - Confirmado
        alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("Confirmado", alerta.getText());
        alerta.accept();

        // abrir de novo o confirm
        driver.findElement(By.id("confirm")).click();

        // primeiro alerta
        alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("Confirm Simples", alerta.getText());
        alerta.dismiss();

        // segundo alerta - Negado
        alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("Negado", alerta.getText());
        alerta.dismiss();
    }

    @Test
    public void deveInteragirComAlertPrompt() {
        driver.findElement(By.id("prompt")).click();

        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("Digite um numero", alerta.getText());

        alerta.sendKeys("9");
        alerta.accept();

        alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals("Era 9?", alerta.getText());
        alerta.accept();

        alerta = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(":D", alerta.getText());
        alerta.accept();
    }
}
