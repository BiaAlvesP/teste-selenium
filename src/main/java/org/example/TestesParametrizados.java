    package org.example;

    import org.junit.After;
    import org.junit.Assert;
    import org.junit.Before;
    import org.junit.Test;
    import org.junit.runner.RunWith;
    import org.junit.runners.Parameterized;

    import java.util.Arrays;
    import java.util.Collection;
    import java.util.List;
    import java.util.Objects;

    //DATA DRIVEN/ DESENVOLVIMENTO DIRIGIDO A DATA

    @RunWith(Parameterized.class)
    public class TestesParametrizados extends Base {

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
            iniciarDriver();
            driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
            dsl = new DSL(driver);
            page = new CampoTreinamentoPage(driver);

        }

        @After
        public void finalizando() {
            driver.quit();
        }

        @Parameterized.Parameters
        public static Collection<Object[]> getColletion() {
            return Arrays.asList(new Object[][]{
                    {"", "", "", Arrays.<String>asList(), new String[]{}, "Nome eh obrigatorio"},
                    {"Joao", "", "", Arrays.<String>asList(), new String[]{}, "Sobrenome eh obrigatorio"}
            });
        }



        @Test
        public void deveValidarRegras() {
            page.setNome(nome);
            page.setSobrenome(sobrenome);

            if (sexo.equals("Masculino"))
                page.setSexoMasculino();
            else if (sexo.equals("Feminino"))
                page.setSexoFeminino();

            if (comidas.contains("Carne")) page.setComidaFavoritaCarne();
            if (comidas.contains("Pizza")) page.setComidaFavoritaPizza();

            page.setEsportes(esportes);
            page.cadastrar();

            String resultado;
            try {
                // tenta pegar alerta
                resultado = driver.switchTo().alert().getText();
                driver.switchTo().alert().accept(); // fecha o alerta
            } catch (org.openqa.selenium.NoAlertPresentException e) {
                // se não houver alerta, pega do resultado normal
                resultado = page.obterResultadoCadastro();
            }

            Assert.assertEquals(msg, resultado);
        }


    }
