package br.ce.wcaquino.test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TestesFrames extends BaseTest {

    private DSL dsl;

    @Before
    public void Iniciando() {
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
    }




    @Test
    public void deveInteragirComFrames() {

        dsl.entrarFrame("frame1");

        dsl.clicar("frameButton");

        String msg = dsl.alertaObterTextoEAceita();

        Assert.assertEquals("Frame OK!", msg);

        dsl.sairFrame();
        dsl.escrever(By.id("elementosForm:nome"), msg);


    }

    @Test
    public void deveInteragirComFrameEscondido(){
        WebElement frame = DriverFactory.getDriver().findElement(By.id("frame2"));
        dsl.executarJS("window.scrollBy(0,arguments[0])", frame.getLocation().y);
        dsl.entrarFrame("frame2");
        dsl.clicar("frameButton");
        String msg = dsl.alertaObterTextoEAceita();
        Assert.assertEquals("Frame OK!", msg);
    }

    @Test
    public void deveInteragirComJanelas() {

        dsl.clicar("buttonPopUpEasy");

        dsl.mudarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");

       dsl.fecharFrame();


    }

    @Test
    public void deveInteragirComJanelas2() {

        dsl.clicar("buttonPopUpHard");

        System.out.println(DriverFactory.getDriver().getWindowHandle()); // janela atual
        System.out.println(DriverFactory.getDriver().getWindowHandles());// todas as janelas

        dsl.mudarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        dsl.mudarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");


    }
}
