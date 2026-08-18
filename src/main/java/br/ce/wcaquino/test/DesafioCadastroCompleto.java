package br.ce.wcaquino.test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DesafioCadastroCompleto extends BaseTest {

    private DSL dsl;
    private CampoTreinamentoPage page;

    @Before
    public void Iniciando() {
        DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();

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
