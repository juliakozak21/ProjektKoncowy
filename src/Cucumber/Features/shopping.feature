Feature: Buy a product

  Scenario Outline: logged in user can can buy a product
    Given user is logged in
    And is on HUMMINGBIRD PRINTED SWEATER product page
    When chooses size "<size>"
    And adds items "<quantity>"
    And adds to basket
    Then can proceed to checkout
    And confirms address
    And adds delivery option "pick up in store"
    And Payment method
    And clicks "order with an obligation to pay"
    And makes a screenshot

    Examples:
      | size | quantity |
      | M    | 5        |

