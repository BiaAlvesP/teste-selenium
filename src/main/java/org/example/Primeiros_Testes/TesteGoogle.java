package org.example.Primeiros_Testes;

import br.ce.wcaquino.core.DriverFactory;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TesteGoogle {

    @Test
    public void testeChrome() {

        DriverFactory.getDriver().get("https://www.google.com");

        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));

        wait.until(driver ->
                !driver.getTitle().isEmpty());

        System.out.println(DriverFactory.getDriver().getTitle());

        DriverFactory.getDriver().quit();
    }
}