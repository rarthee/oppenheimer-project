Feature: As the Clerk, I should be able to upload a csv file to a portal so that I can populate the database from a UI
@uploadCSVFileAPI
Scenario: As the Clerk, I should be able to upload a csv file to a portal so that I can populate the database from a UI
Given (3)AC1: First Row of a CSV file is of format "natid name gender salary birthday tax"
When (3)AC2: I read rows of data for each of the fields
When I open the url
And I click Try Out button
Then (3)AC3: Check if upload button exists to upload
And I am Able to click a button  to upload the file from the pc to portal
And I Click Execute
And Validate if uploaded successfully with response code 200



