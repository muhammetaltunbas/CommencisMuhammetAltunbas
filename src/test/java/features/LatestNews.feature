Feature: Test Tech Blog Website

  Scenario: Verify that each latest news if they have expected information
    Given Go Homepage
    Then Homepage is opened
    And Check if each latest news has an author and an image
    And Close browser

  Scenario Outline: Verify that full content news has expected information
    Given Go Homepage
    Then Homepage is opened
    When Click for <indexNews> news
    Then Check if the browser title is the same with the news title
    And Check the links within the news content
    And Close browser
    Examples:
      | indexNews |
      | 1         |
