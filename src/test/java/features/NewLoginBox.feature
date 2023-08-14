#Author       :  QAMentor
#Scenario     :  Login and Logout from Box app
#Application  :  Box
@Scenario
Feature: Login to the Box App

  Background: 
    Given browser is "chrome"

  @boxScenario1
  Scenario: Login into box scenario1
    Given user navigates to the Box App
    Then user verify the "Box | Login" in the app
    When user enter the credentials "qacult.demo@gmail.com" and "testing123" and Login to the app
    Then user verify the "All Files | Powered by Box" in the app
    When user logout of the application
    Then user verify the "Box | Login" in the app

  @boxScenario2
  Scenario: Login into box scenario2
    Given user navigates to the Box App
    Then user verify the "Box | Login" in the app
    When user enter the correct "vaas.mj.seenu@gmail.com" and "Srinivas061" and Login to the app
    Then user verify the "All Files | Powered by Box" in the app
    When user logout of the application
    Then user verify the "Box | Login" in the app

  @boxScenario_dataTable
  Scenario: Login into box scenario2
    Given user navigates to the Box App
    Then user verify the "Box | Login" in the app
   When user enter the username as below and password "axa12345" and Login to the app
      | pfighter@gmail.com |
      | pfighter@gmail.com |
    Then user verify the "All Files | Powered by Box" in the app
    When user logout of the application
    Then user verify the "Box | Login" in the app

  @boxScenario_dataTable2
  Scenario: Login into box scenario2
    Given user navigates to the Box App
    Then user verify the "Box | Login" in the app
   When user enter the username and password as below and Login to the app
      | pfighter@gmail.com    | axa12345   |
      | qacult.demo@gmail.com | testing123 |
    Then user verify the "All Files | Powered by Box" in the app
    When user logout of the application
    Then user verify the "Box | Login" in the app

  @boxScenario3
  Scenario: Login into box scenario3
    Given user navigates to the Box App
    Then user verify the "Box | Login" in the app
    When user enter the wrong "abbcdefgh@gmail.com" and "asdfreqw" and Login to the app
    Then user verify the error message
