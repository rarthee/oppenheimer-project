Feature: As a Bookkeeper,I should be able to query the amount of tax relief for each person in the database so that I can report the figures to 
my Bookkeeping Manager
@QueryTaxRelief
Scenario: As a Bookkeeper,I should be able to query the amount of tax relief for each person in the database so that I can report the figures to 
my Bookkeeping Manager
Given I set up the url  for GET endpoint
When I Send a GET HTTP request
Then I receive a valid response code 200 from GET tax relief
And (4)AC1: I Get the response data
Then (4)AC2: The Natid has to be masked from the 5th character with a dollar "$" sign
Given I have the response data
When I check the tax relief field value
Then (4)AC3: It should compute correct tax amount as per formula
Given I have the calculated tax relief information
When (4)AC6: Truncate to 2 decimal places if more than 2 dp 
Then (4)AC4: The final relief  amount is of format .00 with zero decimal values due to the rounding rule
Given I have the calculated tax relief information
When The tax amount is more than 0.00 and less than 50.00
Then (4)AC5: The final tax amount is 50.00
Given I have the calculated tax relief information
Then The total tax relief value should be correct



