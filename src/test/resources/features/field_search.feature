@Search
Feature: Sugestoes de Pesquisa (Autocomplete)

  Scenario Outline: Exibicao de sugestoes ao digitar um termo valido
    Given que o usuario esta na pagina inicial da Amazon
    When o usuario digita "<term>" na barra de pesquisa
    Then o sistema deve exibir sugestoes relacionadas ao termo digitado

  Examples:
    | term         |
    | notebook     |
    | teclado      |

  Scenario Outline: Exibicao de sugestoes ao digitar um termo invalido
    Given que o usuario esta na pagina inicial da Amazon
    When o usuario digita "<term>" na barra de pesquisa
    Then o sistema deve nao exibir sugestoes relacionadas ao termo digitado

  Examples:
    | term         |
    | a1b2cdww     |
    | ########     |

  