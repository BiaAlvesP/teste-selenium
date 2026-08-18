package br.ce.wcaquino.test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

//DATA DRIVEN/ DESENVOLVIMENTO DIRIGIDO A DATA

@RunWith(Parameterized.class)
public class TestesParametrizados extends BaseTest {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @Parameterized.Parameter(value = 0)
    public String nome;
    @Parameterized.Parameter(value = 1)
    public String sobrenome;
    @Parameterized.Parameter(value = 2)
    public String sexo;
    @Parameterized.Parameter(value = 3)
    public List<String> comidas;
    @Parameterized.Parameter(value = 4)
    public String[] esportes;
    @Parameterized.Parameter(value = 5)
    public String msg;


    @Before
    public void Iniciando() {
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();

    }

    @After
    public void finalizando() {
        DriverFactory.getDriver().quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getColletion() {
        //Cada linha representa um preenchimento, ou seja, um ct de teste.
        return Arrays.asList(new Object[][]{
                {"", "", "", Arrays.<String>asList(), new String[]{}, "Nome eh obrigatorio"},
                {"Miguel", "", "", Arrays.<String>asList(), new String[]{}, "Sobrenome eh obrigatorio"},
                {"Miguel", "Oliveira", "Masculino", Arrays.<String>asList("Carne", "Vegetariano"), new String[]{}, "Tem certeza que voce eh vegetariano?"},
                {"Miguel", "Oliveira", "Masculino", Arrays.<String>asList("Carne"), new String[]{"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
        });
    }


    @Test
    public void deveValidarRegras() {
        page.setNome(nome);
        page.setSobrenome(sobrenome);

        if (sexo.equals("Masculino")) {
            page.setSexoMasculino();
        }
        if (sexo.equals("Feminino")) {
            page.setSexoFeminino();
        }

        if (comidas.contains("Carne")) page.setComidaFavoritaCarne();
        if (comidas.contains("Pizza")) page.setComidaFavoritaPizza();
        if (comidas.contains("Vegetariano"))
            page.setComidaFavoritaVegetariano();

        page.setEsportes(esportes);
        page.cadastrar();

        String resultado;
        try {
            // tenta pegar alerta
            resultado = DriverFactory.getDriver().switchTo().alert().getText();
            DriverFactory.getDriver().switchTo().alert().accept(); // fecha o alerta
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            // se não houver alerta, pega do resultado normal
            resultado = page.obterResultadoCadastro();
        }

        Assert.assertEquals(msg, resultado);
    }


}
