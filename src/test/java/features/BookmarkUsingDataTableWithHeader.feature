#Author       :  QAMentor
#Scenario     :  Create and delete bookmark from Box app
#Application  :  Box
Feature: Create Delete Bookmark Feature

  Background: 
    Given browser is "chrome"

  @bookmarkDataTableHeaderScenario
  Scenario Outline: Create and delete multiple bookmarks using DataTable
    Given user navigates to the Box App
    Then user verify the "<title1>" in the app
    When user enter the correct "<name>" and "<password>" and Login to the app
    Then user verify the "<title>" in the app
    When user create multiple new bookmark with name and verifies the "<Message>" success message as below:
      | url          | name     | description |
      | dcfolder.com | dcfolder | good        |
      | fridge.com   | fridge   | best        |
      | garage.com   | garage   | better      |
    When user deletes multiple bookmarks and verifies the "<DeleteMessage>" deletion message
    When user logout of the application
    Then user verify the "<title1>" in the app

    Examples: 
      | name                  | password   | title                       | title1       | Message                                                  | DeleteMessage                     |
      | qacult.demo@gmail.com | testing123 | All Files \| Powered by Box | Box \| Login | A bookmark for "bookmarkEntry" was created successfully. | Item successfully moved to trash. |
