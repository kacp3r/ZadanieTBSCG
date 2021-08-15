Feature: Doosan Tests - for now only India/English and Europe/German are handled

  Scenario Outline: Selecting region
    Given I open Doosan homepage
    When I click the region selection link
    And I select the <region> region in <language> language
    Then Homepage is displayed with <region> region and <language> language

    Examples:
    |region|language|
    |India |English |
    |Europe|German  |