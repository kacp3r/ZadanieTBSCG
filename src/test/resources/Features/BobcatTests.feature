Feature: Bobcat tests - price calculation is only handles for the elements used below
  # currently price is checked with the expected amount calculated
  # of course it's also possible to compare the on-screen number with an amount given in the test
  # for some reason this test opens two windows, I don't know why, but it works, and I don't have time to investigate xd
  
  Scenario: Choose model
    Given I open model T4 page
    When I select model "T450 T4 V2 Bobcat Compact Track Loader"
    And I click continue
    And I select option "P10 Performance Package" from group "Performance Packages"
    And I select option "C10 Comfort Package" from group "Comfort Packages"
    And I click continue
    And I select attachment "Quick-Tach Water Kit" from group "Water Kit"
    And I select attachment "48\" Vibratory Roller W/Padded Drum" from group "Vibratory Roller"
    And I click continue
    Then The price is correct according to calculation
    When I fill out the form with valid data
    |First name|First|
    |Last name |Last |
    |Country   |United States|
    |Zip       |21044        |
    |Phone     |8143008709   |
    |Email     |a@b.com      |
    And I click Submit
    Then I can see message saying that configuration has been sent to local dealer
