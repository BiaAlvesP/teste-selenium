package br.ce.wcaquino.test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class TesteCampoTreinamento extends BaseTest {

    @Before
    public void Iniciando() {
       DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        dsl = new DSL();
        page = new CampoTreinamentoPage();
    }


    private DSL dsl;
    private CampoTreinamentoPage page;


    @Test
    public void testeTextField() {

        page.setNome("Teste de escrita");
        //seleciono o elemento com que quero mexer

        //Para confirmar que o texto foi mesmo escrito,usamos o Attribute "value" que retorna o valor que foi escrito
        Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));

    }

    @Test
    public void deveInteragirComTextoArea() {

        dsl.escrever(By.id("elementosForm:sugestoes"), "teste");

        //Para confirmar que retorna o valor que foi escrito
        Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));

    }

    @Test
    public void deveInteragirComRadioButton() {

        page.setSexoMasculino();
        Assert.assertTrue(dsl.checarClick("elementosForm:sexo:0"));
    }

    @Test
    public void deveInteragirComCheckbox() {


        page.setComidaFavoritaPizza();

        Assert.assertTrue(dsl.checarClick("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void deveInteragirComCombo() {


        page.setEscolaridade("Superior");

        //Para validar o valor utilizado
        Assert.assertEquals("Superior", dsl.obterValorCombo("elementosForm:escolaridade"));

    }


    @Test
    public void verificarValoresDisponiveis() {

        Assert.assertEquals(8, dsl.obterQtdOpcaoCombo("elementosForm:escolaridade")); // para validar qtd de opções
        Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Superior")); // para validar se existe certa opções
    }


    @Test
    public void deveSelecionarMultiplos() {

        page.setEsportes("Natacao");
        page.setEsportes("Futebol");
        page.setEsportes("Corrida");

        List<String> opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(3, opcoesMarcadas.size()); // para validar qtd de opções

        dsl.deSelecionarCombo("elementosForm:esportes", "Corrida");

        opcoesMarcadas = dsl.obterValoresCombo("elementosForm:esportes");
        Assert.assertEquals(2, opcoesMarcadas.size());
        Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "Futebol")));
    }


    @Test
    public void deveInteragirComBotoes() {

        dsl.clicar("buttonSimple");
        Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));

    }

    @Test
    public void deveInteragirComLink() {

        dsl.clicarLink("Voltar");

        Assert.assertEquals("Voltou!", dsl.obterText("resultado"));
    }

    @Test
    public void deveBuscarTextos() {


        Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

        Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));


    }

    @Test
    public void testJavaScript() {
        JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
        // js.executeScript("alert('Testando js via Selenium')");
        js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via Js'");

        WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
        js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
    }

    // Tabela

    public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTab) {

        //procurar coluna do registro
        WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//*[@id= 'elementosForm:tableUsuarios']"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);

        //encontrar a linha do registro
        List<WebElement> linhas = tabela.findElements(By.xpath(".//tr//td["+ idColuna+"]"));
        int idLinha = -1;
        for (int i = 0; i < linhas.size(); i++) {
            if (linhas.get(i).getText().equals(linhas)) {
                idLinha = i + 1;
                break;
            }
        }
        //procurar coluna do botao

        //clicar no botao da celula encontrada

    }

    protected int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;
        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equals(coluna)) {
                idColuna = i + 1;
                break;
            }
        }
        return idColuna;
    }

    // Sincronismo

    //Espera um tempo determinado e da erro se demorar mais que o tempo pré definido, ruim pois em
    // situações de demora maior tiramos o desempenho se aumentamos demais o tempo
    @Test
    public void DeveUtilizarEsperaFixa() throws InterruptedException{
        dsl.clicar("buttonDelay");
        Thread.sleep(5000);
        dsl.escrever(By.id("novoCampo"),"Deu certo?");
    }


    // Pré definimos um tempo (que pode ser maiorzinho), mas ele só vai utilizar o tempo definido até encontrar
    // o que esta procurando (se achar em 2 ele para) , é bom colocarmos no @Before para se aplicar a tudo
    @Test
    public void DeveUtilizarEsperaImplicita() throws InterruptedException{
        dsl.clicar("buttonDelay");
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        dsl.escrever(By.id("novoCampo"),"Deu certo?");
    }

    // Pré definimos um tempo (que pode ser maiorzinho), mas ele só vai utilizar o tempo definido até encontrar
    // o que esta procurando (se achar em 2 ele para) , é bom colocarmos no @Before para se aplicar a tudo
    @Test
    public void DeveUtilizarEsperaExplicita() throws InterruptedException{
        dsl.clicar("buttonDelay");
        dsl.esperarElemento(By.id("novoCampo"));
        dsl.escrever(By.id("novoCampo"),"Deu certo?");
    }
}