package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DesafioCadastroCompleto extends Base {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void Iniciando() {
        iniciarDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);

    }

    @After
    public void finalizando() {
        driver.quit();
    }

    @Test
    public void cadastroCompleto() {


        //nome
        page.setNome("Bianca");

        //sobrenome
        page.setSobrenome("Alves Pinheiro");

        //sexo
        page.setSexoFeminino();

        //comida
        page.setComidaFavoritaPizza();

        //escolaridade
        page.setEscolaridade("2o grau completo");

        //esportes
        page.setEsportes("Natacao","Corrida");


        //cadastrar
        page.cadastrar();
        Assert.assertEquals("Cadastrado!\nNome: Bianca\nSobrenome: Alves Pinheiro\nSexo: Feminino\nComida: Pizza\nEscolaridade: 2graucomp\nEsportes: Natacao Corrida\nSugestoes:", page.obterResultadoCadastro()    );


    }

}
