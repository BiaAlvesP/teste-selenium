package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Base {
    protected static WebDriver driver;

    static {
        System.setProperty("webdriver.gecko.driver", "C:\\gecko\\geckodriver-v0.36.0-win32\\geckodriver.exe");
    }

    public void iniciarDriver() {
        // Configura opções do Firefox
        FirefoxOptions options = new FirefoxOptions();

        // Caminho do Firefox.exe no seu computador
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe"); // ajuste se o seu estiver em outro caminho

        // Cria o driver
        driver = new FirefoxDriver(options);

    }
}
