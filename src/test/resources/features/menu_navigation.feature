@MenuNavegacao
Feature: Menu de Navegação

    Scenario Outline: O menu deve estar visivel em todos os dispositos
    Given que o usuario acessa o site da Amazon no modo "<device>"
    Then o menu de navegacao deve estar visivel ou recolhido para "<device>"
    
    Examples:
        | device   |
        | desktop  |
        | tablet   |
        | mobile   |

    Scenario Outline: Menu hambuguer deve está visivel e funcional
        Given que o usuario acessa o site da Amazon no modo "<device>"

        When icone do menu hambuguer deve estar visivel
        And usuario clica no icone do menu
        Then menu de navegacao deve ser expandido

        When o usuario clica fora do menu
        Then o menu de navegacao deve ser recolhido

    Examples:
        | device   |
        | desktop  |
        | tablet   |
        | mobile   |

    Scenario Outline: Validar o tempo de carregamento da página
        Given que o usuario acessa o site da Amazon no modo "<device>"
        When a pagina e carregada completamente
        Then o tempo de carregamento deve ser inferior a 3 segundos
        
    Examples:
        | device   |
        | desktop  |
        | tablet   |
        | mobile   |

    Scenario Outline: Validar o tempo de carregamento da página em diferentes navegadores
        Given que o usuario acessa o site da Amazon no modo "<device>"
        When a pagina e carregada completamente
        Then o tempo de carregamento deve ser inferior a 3 segundos

    Examples:
        | device   |
        | desktop  |
        | tablet   |
        | mobile   |