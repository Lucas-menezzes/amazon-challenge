# 🛒 Amazon Automation

Testes automatizados para um desafio técnico de QA. O projeto valida funcionalidades essenciais da **página inicial da Amazon**, incluindo **autocomplete, menu de navegação e fluxo de compra de um produto**.

## 📌 Funcionalidades Testadas

- **Sugestões de Pesquisa (Autocomplete)** → Verifica se as sugestões aparecem corretamente enquanto o usuário digita.
- **Menu de Navegação** → Valida a responsividade e usabilidade do menu principal.
- **Adição ao Carrinho** → Testa o fluxo de compra, garantindo que o item correto é adicionado ao carrinho.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Selenium WebDriver**
- **Cucumber (BDD)**
- **JUnit**
- **Maven**
- **Extent Reports** (para relatórios de execução)
- **ChromeDriver**

## ▶️ Como Executar os Testes

### **Pré-requisitos**
Certifique-se de ter instalado:
- **Java 17+** → [Baixar JDK](https://adoptium.net/)
- **Maven** → [Baixar Maven](https://maven.apache.org/download.cgi)
- **Google Chrome** (atualizado)
- **ChromeDriver** compatível com sua versão do Chrome.

### **Rodando os testes**
Para executar todos os testes, use:

```sh
mvn clean test
