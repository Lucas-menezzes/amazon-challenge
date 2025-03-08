@Carrinho
Feature: Adicionar produtos ao carrinho

    Scenario: Adicionar um produto ao carrinho com sucesso
        Given que o usuario esta na pagina inicial da Amazon
        When o usuario pesquisa por "notebook"
        And seleciona primeiro produto
        And valor do produto correto com o selecionado
        And adiciona o produto ao carrinho
        And o produto deve aparecer no carrinho corretamente