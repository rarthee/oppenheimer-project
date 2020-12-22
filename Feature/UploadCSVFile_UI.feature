Feature: As the Clerk, I should be able to upload a csv file to a portal so that I can populate the database from a UI
@uploadCSVFileUI
Scenario: As the Clerk, I should be able to upload a csv file through the UI to a portal so that I can populate the database from a UI
Given I open the application
And I Find a Browse Button
When (3)AC3: There is an Upload CSV File Button
Then I upload the csv file
And I can click Refresh Tax Relief Table button
And I am able to click Visit Swagger button


