Feature: Login feature with Excel data 

 

  Scenario Outline: Login using Excel rows 

    Given user launches the application 

    When user logs in using excel row <rowNumber> 

    Then user should see expected result from excel 

 

    Examples: 

      | rowNumber | 

      | 1         | 

      | 2         | 

      | 3         | 