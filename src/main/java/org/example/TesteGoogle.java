package org.example;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TesteGoogle extends Base{

    @Test
    public void testeFirefox() {
        iniciarDriver();
        driver.get("https://www.google.com");
        System.out.println("Título (Firefox): " + driver.getTitle());


        // tamanho da tela
      //  driver.manage().window().setSize(new Dimension (1200,765));

        // fecha o driver (melhor que close)
        driver.quit();
    }

//    @Test
//    public void testeChrome() {
//        System.setProperty("webdriver.chrome.driver", "C:\\gecko\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--remote-allow-origins=*");
//
//// FLAGS ADICIONAIS:
//        options.addArguments("--disable-gpu"); // desativa GPU
//        options.addArguments("--disable-software-rasterizer"); // ajuda a não travar
//        options.addArguments("--headless=new"); // testa sem abrir GUI, útil para diagnosticar
//
//
//        WebDriver driver = new ChromeDriver(options);
//        driver.get("https://www.google.com");
//        System.out.println("Título (Chrome): " + driver.getTitle());
//        driver.quit();
//    }
}
