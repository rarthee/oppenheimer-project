Feature: As the Clerk, I should be able to insert more than one working class hero into database via an API
@multiinsert
Scenario: As the Clerk, I should be able to insert more than one working class hero into database via an API
Given I set POST service API end point for multiperson
When I Send a POST HTTP request for multiperson
Then (2)AC1:I receive a valid response code 202 for multiperson


