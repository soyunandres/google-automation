@google
Feature: Probar la búsqueda de google
  Scenario: Buscar algo en google
    Given Ingresamos a la página de google
    When Esccribimos algo en la barra de busqueda
    And Damos clic al boton de busqueda de google
    Then Nos muestra los resultados de la búsqueda
