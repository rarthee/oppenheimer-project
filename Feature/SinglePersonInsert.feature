Feature: As the Clerk, I should be able to insert a single record of working class hero into database via an API
@insert
Scenario Outline: As the Clerk, I should be able to insert a single record of working class hero into database via an API
Given I set POST service API end point
When I Send a POST HTTP request with <birthday><gender><name><natid><salary><tax>
Then (1)AC1:I receive a valid response code 202
Examples:
| birthday   | gender  |name                          |natid       |salary        |tax     |
| "20101981" | "F"     |"AllenMiss. Elisabeth Walton" |"120000"    |"7966.67"     |"211.3375" |