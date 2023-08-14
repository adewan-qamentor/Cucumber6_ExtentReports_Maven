#Author       :  QAMentor
#Scenario     :  Create and delete bookmark using Doc String from Box app
#Application  :  Box
Feature: Create Delete Bookmark Feature using DocString

  Background: 
    Given browser is "chrome"

  @bookmarkDocStringScenario
  Scenario Outline: Create and delete bookmark using DocString
    Given user navigates to the Box App
    Then user verify the "<title1>" in the app
    When user enter the correct "<name>" and "<password>" and Login to the app
    Then user verify the "<title2>" in the app
    When user creates a new bookmark with url "<bookmarkurl>"
    And user enters the bookmark description as:
    """
    This bookmark is a link for the code,
    which explains how to randomise the given text in the following context.
   """
    Then user verifies the following success message:
    """
    A bookmark for "www.random.com" was created successfully.
    """
    When user deletes a bookmark
    Then user verifies the "<DeleteMessage>" deletion message
    When user logout of the application
    Then user verify the "<title1>" in the app

    Examples: 
      | name                | password     | title2                      | title1       | bookmarkurl    | DeleteMessage                     |
      | gujral608@gmail.com | Welcome@1234 | All Files \| Powered by Box | Box \| Login | www.random.com | Item successfully moved to trash. |
