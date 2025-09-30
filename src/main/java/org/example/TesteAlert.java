package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TesteAlert extends Base {

    private WebDriverWait wait;

    private DSL dsl;

    @Before
    public void Iniciando() {
        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // inicializa aqui

    }

    @After
    public void finalizando() {
        driver.quit();
    }

    @Test
    public void deveInteragirComAlertSimples() {
        dsl.clicar("alert");

        String texto = dsl.alertaObterTextoEAceita();

        Assert.assertEquals("Alert Simples",texto);
        dsl.escrever(By.id("elementosForm:nome"), texto);
    }

    @Test
    public void deveInteragirComAlertConfirm() {
        dsl.clicar("confirm");

        // primeiro alerta
        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());


        // segundo alerta - Confirmado
        Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());

        // abrir de novo o confirm
        driver.findElement(By.id("confirm")).click();

        // primeiro alerta

        Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());

        // segundo alerta - Negado
        Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
    }

    @Test
    public void deveInteragirComAlertPrompt() {
        dsl.clicar("prompt");
        Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
        dsl.alertaEscrever("9");

        Assert.assertEquals("Era 9?", dsl.alertaObterTextoEAceita());
        Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
    }

}
