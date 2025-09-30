package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public void setNome(String nome) {
        dsl.escrever(By.id("elementosForm:nome"), nome);
    }

    public void setSobrenome(String sobrenome) {
        dsl.escrever(By.id("elementosForm:sobrenome"), sobrenome);
    }

    public void setSexoMasculino() {
        dsl.clicar("elementosForm:sexo:0");
    }

    public void setSexoFeminino() {
        dsl.clicar("elementosForm:sexo:1");
    }

    public void setComidaFavoritaPizza() {
        dsl.clicar("elementosForm:comidaFavorita:2");
    }

    public void setComidaFavoritaCarne() {
        dsl.clicar("elementosForm:comidaFavorita:0");
    }

    public void setComidaFavoritaVegetariano() {
        dsl.clicar("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valor) {
        dsl.selecionarCombo("elementosForm:escolaridade", valor);

    }

    public void setEsportes(String... valores) {
        for (String valor : valores)
            dsl.selecionarCombo("elementosForm:esportes", valor);

    }


    public void cadastrar() {
        dsl.clicar("elementosForm:cadastrar");
    }


    public String obterResultadoCadastro() {
        return dsl.obterText("resultado");
    }

}
