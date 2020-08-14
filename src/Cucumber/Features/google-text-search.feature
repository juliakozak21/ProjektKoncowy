Feature: Create new address after login

  Scenario Outline: logged in user can add a new address
    Given user is logged in the store
    And user is on Addresses page
    When user adds new address "<alias>", "<address>", "<postcode>", "<city>", "<country>"
    Then address has been added
    And checks if data is true "<alias>", "<address>", "<postcode>", "<city>", "<country>"


    Examples:
      | alias | address | postcode | city     | country     |
      | Robo  | Str1    | 11111    | Berlin   | United Kingdom |
