Feature: Logging into the Wiggle website
  As a Wiggle customer
  I want to be able to log into the Wiggle website
  So I can access the checkout sections of the site.

  @login
  Scenario: Valid credentials
    Given I am on the wiggle login page
    When I sign in with the valid email and password
    Then I should be logged in.

  @loginAttemptWithInvalidCredentials
  Scenario Outline: Unregistered User
    Given I am on the wiggle login page
    When I sign in with the <Email> email and password <Password>
    Then I should see the message <Message>

    Examples: 
      | Email                              | Password      | Message                                                                          |
      | "Liliwang1@test.com"               | "Password123" | "Sorry we could not log you in. Please try entering your account details again." |
   
   @LoginUncompletedCredentials
   Scenario Outline: No username or No passward
    Given I am on the wiggle login page
    When I sign in with the <Email> email and password <Password>
    Then I should get <Message> message
   
   Examples:
   
      | Email                              | Password      | Message                                                   |
      | "cucumber_login_test@wiggle.co.uk" | ""            | "Please enter your password"                              |
      | ""                                 | "Password123" | "Please ensure you have entered the correct email address"|
      
  @forgotPasword
  Scenario: Forgot password
    Given I am on the wiggle login page
    When I click the forgot my password link
    Then I should be prompted for my email address.