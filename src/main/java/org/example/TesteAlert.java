package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

public class TesteAlert extends Base {

    @Test
    public void deveInteragirComAlertSimples() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("alert")).click();


        // para mudar o foco para o alerta
        Alert alerta = driver.switchTo().alert();
        String texto = alerta.getText();

        alerta.accept();

        Assert.assertEquals("Alert Simples", texto);

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);


        driver.quit();
    }

    @Test
    public void deveInteragirComAlertConfirm() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("confirm")).click();


        // para mudar o foco para o alerta
        Alert alerta = driver.switchTo().alert();

        Assert.assertEquals("Confirm Simples", alerta.getText());

        alerta.accept();

        Assert.assertEquals("Confirmado", alerta.getText());

        alerta.accept();



        driver.findElement(By.id("confirm")).click();


        // para mudar o foco para o alerta
      alerta = driver.switchTo().alert();


        Assert.assertEquals("Confirm Simples", alerta.getText());

        alerta.dismiss();

        Assert.assertEquals("Negado", alerta.getText());

        alerta.dismiss();

        driver.quit();

    }

    @Test
    public void deveInteragirComAlertPrompt() {
        iniciarDriver();

        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("prompt")).click();


        // para mudar o foco para o alerta
        Alert alerta = driver.switchTo().alert();

        Assert.assertEquals("Digite um numero", alerta.getText());

        alerta.sendKeys("9");

        alerta.accept();

        Assert.assertEquals("Era 9?", alerta.getText());

        alerta.accept();

        Assert.assertEquals(":D", alerta.getText());

        alerta.accept();

        driver.quit();

    }

}
