Feature: As the Governor,I should be able to see a button on the screen so that I can dispense tax relief for my working class heroes 
@DispenseTaxRelief
Scenario: As the Governor,I should be able to see a button on the screen so that I can dispense tax relief for my working class heroes
Given I am on the tax relief UI page
When (5)AC1: The button must be Red color
And (5)AC2: The text on the button must be exactly "Dispense Now"
And Total Tax Relief amount should display with format $NNNN.00
And I click on the button
Then (5)AC3: It should direct me to a page with a text that says "Cash dispensed"
And I should be on page with title "Dispense"

