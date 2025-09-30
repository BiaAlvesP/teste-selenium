package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TestesFrames extends Base {

    private DSL dsl;

    @Before
    public void Iniciando() {
        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
    }

    @After
    public void finalizando() {
        driver.quit();
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
    public void deveInteragirComJanelas() {

        dsl.clicar("buttonPopUpEasy");

        dsl.mudarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");

       dsl.fecharFrame();


    }

    @Test
    public void deveInteragirComJanelas2() {

        dsl.clicar("buttonPopUpHard");

        System.out.println(driver.getWindowHandle()); // janela atual
        System.out.println(driver.getWindowHandles());// todas as janelas

        dsl.mudarJanela((String) driver.getWindowHandles().toArray()[1]);
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        dsl.mudarJanela((String) driver.getWindowHandles().toArray()[0]);
        dsl.escrever(By.tagName("textarea"), "e agora?");


    }
}
