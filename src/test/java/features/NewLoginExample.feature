#Author       :  QAMentor
#Scenario     :  Login and Logout from Box app
#Application  :  Box
Feature: Login to the Box App

  Background: 
    Given browser is "chrome"

  @boxExampleScenario_part1
  Scenario Outline: Login with Examples Table
    Given user navigates to the Box App
    Then user verify the "<title1>" in the app
    When user enter the "<name>"
    And user enter the "<password>"
    And user login  into app
    Then user verify the "<title2>" in the app
    When user logout of the application
    Then user verify the "<title1>" in the app

    Examples: 
      | name                  | password    | title2                      | title1       |
      | qacult.demo@gmail.com | testing123 | All Files \| Powered by Box | Box \| Login |
