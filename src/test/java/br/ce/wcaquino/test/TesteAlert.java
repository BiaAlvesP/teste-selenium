package br.ce.wcaquino.test;

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TesteAlert {

    private WebDriverWait wait;

    private DSL dsl;

    @Before
    public void Iniciando() {
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(5)); // inicializa aqui

    }

    @After
    public void finalizando() {
        DriverFactory.killDriver();
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
        DriverFactory.getDriver().findElement(By.id("confirm")).click();

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
