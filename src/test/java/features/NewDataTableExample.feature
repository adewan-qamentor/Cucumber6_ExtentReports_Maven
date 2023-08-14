#Author       :  QACult
#Scenario     :  Create and delete bookmark from Box app
#Application  :  Box
Feature: Create Delete Bookmark Feature

  Background: 
    Given browser is "chrome"

  @bookmarkExampleScenario2
  Scenario Outline: Create and delete bookmark using Examples
    Given user navigates to the Box App
    Then user verify the "<title1>" in the app
    When user enter the correct "<name>" and "<password>" and Login to the app
    Then user verify the "<title>" in the app
    When user creates a new bookmark with data as below:
      | DcFolder |
      | fridge   |
    Then user verifies the "<Message>" success message
    When user deletes a bookmark
    Then user verifies the "<DeleteMessage>" deletion message
    When user logout of the application
    Then user verify the "<title1>" in the app

    Examples: 
      | name                    | password    | title                       | title1       | Message                                                  | DeleteMessage                     |
      | vaas.mj.seenu@gmail.com | Srinivas061 | All Files \| Powered by Box | Box \| Login | A bookmark for "bookmarkEntry" was created successfully. | Item successfully moved to trash. |
  #| nancydhingra131@gmail.com | nancy131    | All Files \| Powered by Box | Box \| Login | A bookmark for "bookmarkEntry" was created successfully. | Item successfully moved to trash. |
      