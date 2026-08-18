package br.ce.wcaquino.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class DSL {


    private WebDriverWait wait;


    public DSL() {
        DriverFactory.getDriver();
        this.wait = new WebDriverWait(DriverFactory.getDriver(), java.time.Duration.ofSeconds(5));
    }


    public void escrever(By by, String text) {

        DriverFactory.getDriver().findElement(by).clear();// apaga se algo já estiver escrito
        //sendKeys = faz escrever no campo
        DriverFactory.getDriver().findElement(by).sendKeys(text);


    }

    public String obterValorCampo(String id) {
        return DriverFactory.getDriver().findElement(By.id(id)).getAttribute("value");
    }

    public void clicar(String id) {

        DriverFactory.getDriver().findElement(By.id(id)).click();

    }

    public Boolean checarClick(String id) {

        return DriverFactory.getDriver().findElement(By.id(id)).isSelected();
    }

    public void selecionarCombo(String id, String valor) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        // combo.selectByIndex(4); // por index
//          combo.selectByValue("superior"); // pelo value
        combo.selectByVisibleText(valor);      // mais legal usar esse, porque é como o usuário visualiza

    }

    public void deSelecionarCombo(String id, String valor) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);

    }

    public String obterValorCombo(String id) {
        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);

        return combo.getFirstSelectedOption().getText();

    }

    public List<String> obterValoresCombo(String id) {

        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<String>();

        for (WebElement opcao : allSelectedOptions) {
            valores.add(opcao.getText());
        }

        return valores;
    }


    public int obterQtdOpcaoCombo(String id) {

        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();

        return options.size();
    }

    public boolean verificarOpcaoCombo(String id, String opcao) {

        WebElement element = DriverFactory.getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();

        for (WebElement option : options) {
            if (option.getText().equals(opcao)) {
                return true;
            }

        }

        return false;
    }

    public void clicarLink(String link) {

        DriverFactory.getDriver().findElement(By.linkText(link)).click();
    }

    public String obterText(String id) {
        return DriverFactory.getDriver().findElement(By.id(id)).getText();
    }


    public String obterTexto(By by) {
        return DriverFactory.getDriver().findElement(by).getText();
    }


    public String obterValueElemento(String id) {
        WebElement button = DriverFactory.getDriver().findElement(By.id(id));
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

    public void alertaEscrever(String valor) {
        Alert alerta = wait.until(ExpectedConditions.alertIsPresent());
        alerta.sendKeys(valor);
        alerta.accept();
    }

    ////////////////Frames///////////////////////////

    public void entrarFrame(String id) {

        DriverFactory.getDriver().switchTo().frame(id);
    }

    public void sairFrame() {

        DriverFactory.getDriver().switchTo().defaultContent();
    }


    public void fecharFrame() {

        DriverFactory.getDriver().close();    }

    public void mudarJanela(String id) {

        DriverFactory.getDriver().switchTo().window(id);// nem todos os popup tem indentificados
    }


    //Wait
    public void esperarElemento(By by){
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

          /************JS*******************/

          public Object executarJS(String cmd, Object... param){
              JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
              return js.executeScript(cmd,param);
          }



}
