package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class DSL {

    private WebDriver driver;
    private WebDriverWait wait;


    public DSL(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
    }


    void escrever(By by , String text) {

        driver.findElement(by).clear();// apaga se algo já estiver escrito
        //sendKeys = faz escrever no campo
        driver.findElement(by).sendKeys(text);


    }

    public String obterValorCampo(String id) {
        return driver.findElement(By.id(id)).getAttribute("value");
    }

    void clicar(String id) {

        driver.findElement(By.id(id)).click();

    }

    public Boolean checarClick(String id) {

        return driver.findElement(By.id(id)).isSelected();
    }

    void selecionarCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        // combo.selectByIndex(4); // por index
//          combo.selectByValue("superior"); // pelo value
        combo.selectByVisibleText(valor);      // mais legal usar esse, porque é como o usuário visualiza

    }

    void deSelecionarCombo(String id, String valor) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);

    }

    public String obterValorCombo(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);

        return combo.getFirstSelectedOption().getText();

    }

    public List<String> obterValoresCombo(String id) {

        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<String>();

        for (WebElement opcao : allSelectedOptions) {
            valores.add(opcao.getText());
        }

        return valores;
    }


    public int obterQtdOpcaoCombo(String id) {

        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();

        return options.size();
    }

    public boolean verificarOpcaoCombo(String id, String opcao) {

        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();

        for (WebElement option : options) {
            if (option.getText().equals(opcao)) {
                return true;
            }

        }

        return false;
    }

    void clicarLink(String link) {

        driver.findElement(By.linkText(link)).click();
    }

    public String obterText(String id) {
        return driver.findElement(By.id(id)).getText();
    }


    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }


    public String obterValueElemento(String id) {
        WebElement button = driver.findElement(By.id(id));
        return button.getAttribute("value");
    }


    ////////////////Alertas///////////////////////////

    public String alertaObterTexto() {
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        return alerta.getText();
    }

    public String alertaObterTextoEAceita() {
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        String texto = alerta.getText();
        alerta.accept();
        return texto;
    }

    public String alertaObterTextoENega() {
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        String texto = alerta.getText();
        alerta.dismiss();
        return texto;
    }

    void alertaEscrever(String valor) {
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        alerta.sendKeys(valor);
        alerta.accept();
    }

    ////////////////Frames///////////////////////////

    void entrarFrame(String id) {

        driver.switchTo().frame(id);
    }

    void sairFrame() {

        driver.switchTo().defaultContent();
    }


    void fecharFrame() {

        driver.close();    }

    void mudarJanela(String id) {

        driver.switchTo().window(id);// nem todos os popup tem indentificados
    }


}
