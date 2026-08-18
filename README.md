# 🧪 Automação de Testes com Selenium WebDriver + Java

Projeto de estudos em **automação de testes funcionais**, construído durante minha transição de QA Manual para QA Automation. O objetivo não é só passar pelos testes, mas consolidar uma arquitetura de automação reutilizável, de fácil manutenção e alinhada com boas práticas de mercado.

---

## 🛠️ Stack Técnica

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 23 | Linguagem principal |
| Selenium WebDriver | 4.44.0 | Automação do navegador |
| JUnit 4 | 4.13.x | Framework de testes |
| Maven | — | Gerenciador de dependências e build |
| Apache Commons IO | 2.16.1 | Manipulação de arquivos (screenshots) |
| IntelliJ IDEA | Community | IDE |

---

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas, separando responsabilidades entre "o que testar", "onde estão os elementos" e "como interagir com eles":

```
Teste
  ↓  (extends BaseTest)
Page Object
  ↓  (usa DSL via composição)
DSL
  ↓  (usa DriverFactory)
WebDriver
  ↓
Navegador (Chrome / Firefox)
```

### Camadas

**`Teste`** — Define *o que* está sendo validado no cenário (regras de negócio, fluxos, asserts).

**`Page Object`** — Representa uma página da aplicação e centraliza as operações específicas dela (ex: `setNome()`, `cadastrar()`). Sabe *onde* está cada elemento.

**`DSL` (Domain Specific Language)** — Centraliza ações reutilizáveis do Selenium (`clicar`, `escrever`, `selecionarCombo`, etc). Sabe *como* executar uma ação, sem saber o significado de negócio dela.

**`DriverFactory`** — Fábrica centralizada do `WebDriver`, responsável por criar e gerenciar a instância única do navegador (Factory Pattern), incluindo o **chaveamento entre Chrome e Firefox**.

> **Composição, não herança:** `BasePage` não herda de `DSL` — ela **tem um** `DSL` como atributo. Isso evita acoplamento desnecessário e reflete corretamente a relação entre as classes (uma Page *usa* a DSL como ferramenta, ela não *é* uma DSL).

---

## 📁 Estrutura de Pacotes

```
src/main/java/br/ce/wcaquino/
├── core/
│   ├── DriverFactory.java     # Factory Pattern + chaveamento de browser
│   ├── DSL.java                # Ações reutilizáveis do Selenium
│   ├── BaseTest.java           # Setup/teardown comum + screenshot automático
│   ├── BasePage.java           # Base para Page Objects (composição com DSL)
│   └── Propriedades.java       # Configurações estáticas (browser, fechar ao final)
│
├── page/
│   └── CampoTreinamentoPage.java   # Page Object da aplicação de treino
│
├── test/
│   ├── TesteCampoTreinamento.java  # Interações básicas (campos, combos, botões...)
│   ├── TesteAlert.java             # Alerts simples, confirm e prompt
│   ├── TestesFrames.java           # Frames e múltiplas janelas
│   ├── DesafioCadastroCompleto.java
│   ├── DesafioRegrasNegocio.java
│   └── TestesParametrizados.java   # Data Driven Testing com @RunWith(Parameterized)
│
└── suites/
    └── SuiteTestes.java        # Execução orquestrada de múltiplas classes de teste

src/main/resources/
├── componentes.html    # Aplicação de treino (Campo de Treinamento)
└── frame.html           # Página usada nos testes de iframe
```

---

## ✅ O que já foi implementado

- [x] Interações com elementos básicos (text field, textarea, radio, checkbox, combo, multi-select)
- [x] Botões, links e obtenção de texto de elementos
- [x] Alerts: simples, confirm e prompt (`switchTo().alert()`)
- [x] Frames (visíveis e "escondidos", com scroll via JavaScript)
- [x] Múltiplas janelas / popups (Window Handles)
- [x] Execução de JavaScript via `JavascriptExecutor`
- [x] Estratégias de sincronismo: espera fixa, implícita e **explícita** (`WebDriverWait` + `ExpectedConditions`)
- [x] Data Driven Testing com `@RunWith(Parameterized.class)`
- [x] Driver centralizado (Factory Pattern)
- [x] Chaveamento de browser (Chrome / Firefox) via `enum` + classe de configuração
- [x] Screenshot automático ao final de cada teste (nome dinâmico via `@Rule TestName`)
- [x] Suite de testes com execução orquestrada

## 🔜 Próximos passos

- [ ] Testando uma aplicação real (fora do ambiente de treino)
- [ ] Execução de testes em paralelo
- [ ] Execução de testes na nuvem (Selenium Grid)
- [ ] Relatórios e logs estruturados
- [ ] Integração com CI/CD

---

## 🐛 Melhorias conhecidas (transparência é uma prática de QA)

Documentar bugs conscientemente identificados, mesmo sem correção imediata, faz parte de manter um projeto rastreável:

- **`DriverFactory`**: existe uma linha residual (`driver = new ChromeDriver();`) fora do `switch`, que sobrescreve a escolha de browser feita pelo `enum`. Efeito: o chaveamento para Firefox não funciona ainda, mesmo estando configurado corretamente. Correção identificada, pendente de teste (ambiente atual só tem Chrome instalado).
- **`TestesParametrizados`**: usa `DriverFactory.getDriver().quit()` direto no `@After`, em vez de herdar o comportamento padronizado de `BaseTest`. Deixa a suíte inconsistente — candidato a refatoração.
- **Dependência duplicada no `pom.xml`**: JUnit está declarado duas vezes com versões diferentes (`4.13.2` e `4.13.1`). Não quebra o build, mas deveria ser consolidado em uma única declaração com `scope test`.

---

## ▶️ Como rodar

Pré-requisitos: Java 23, Maven, Chrome instalado.

```bash
# Rodar todos os testes
mvn test

# Rodar uma suíte específica
mvn test -Dtest=SuiteTestes
```

Os screenshots de cada execução são salvos em `target/screenshot/`, nomeados automaticamente com o nome do método de teste.

---

## 🎓 Sobre este projeto

Este repositório acompanha meus estudos em automação de testes, baseado no curso *Testes Funcionais Automatizados com Selenium WebDriver*. Além de seguir as aulas, venho adaptando e revisando a arquitetura para reforçar conceitos de Java (POO, composição vs. herança), boas práticas de organização de código, e debugging independente de problemas reais — não apenas reprodução do conteúdo assistido.

---

## 👩‍💻 Autora

Bianca Alves Pinheiro — em transição de QA Manual para QA Automation.