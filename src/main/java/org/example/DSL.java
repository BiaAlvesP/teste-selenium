package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class DSL {

        private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    void escrever(String id, String text){

        //sendKeys = faz escrever no campo
        driver.findElement(By.id(id)).sendKeys(text);

    }

    public String obterValorCampo(String id){
        return driver.findElement(By.id(id)).getAttribute("value");
    }

    void clicar(String id){

        driver.findElement(By.id(id)).click();

    }

    public Boolean checarClick(String id){

       return driver.findElement(By.id(id)).isSelected();
    }

    void selecionarCombo(String id, String valor){
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        // combo.selectByIndex(4); // por index
//          combo.selectByValue("superior"); // pelo value
        combo.selectByVisibleText(valor);      // mais legal usar esse, porque é como o usuário visualiza

    }

    public String obterValorCombo(String id){

        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    void clicarLink(String link){

        driver.findElement(By.linkText(link)).click();
    }

    public String obterText(String id){
        return driver.findElement(By.id(id)).getText();
    }


    public String obterTexto(By by){
        return driver.findElement(by).getText();
    }

}
