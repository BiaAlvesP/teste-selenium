package org.example;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TesteGoogle {

    @Test
    public void testeFirefox() {
        System.setProperty("webdriver.gecko.driver", "C:\\gecko\\geckodriver-v0.36.0-win32\\geckodriver.exe");

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www.google.com");
        System.out.println("Título (Firefox): " + driver.getTitle());
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
