package com.wiggle;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefs {
	public WebDriver driver;
	@Before
	public void startBrowser(){
		System.out.println("Opening browser...");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Cindy\\Selenium\\chromedriver\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.wiggle.co.uk/secure/myaccount/logon?returnurl=%2F");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		
	}
	
	
	@Given("^I am on the wiggle login page$")
	public void I_am_on_the_wiggle_login_page() {   
	   String header=driver.findElement(By.id("loginFieldset")).getText();
	   Assert.assertTrue(header.contains("Log In or Register"));
	   System.out.println("I am on the wiggle login page!");
	}
//login with valid credentials
	@When("^I sign in with the valid email and password$")
	public void I_sign_in_with_the_valid_email_and_password() throws Throwable {
		// "cucumber_login_test@wiggle.co.uk" and password "123Password"
		System.out.println("Actions");
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.id("LogInOptionMirror")).click();
		driver.findElement(By.id("LogOnModel_UserName")).clear();
		driver.findElement(By.id("LogOnModel_UserName")).sendKeys("Liliwang@test.com");
		driver.findElement(By.id("LogOnModel_Password")).clear();
		driver.findElement(By.id("LogOnModel_Password")).sendKeys("Password123");
		driver.findElement(By.xpath(".//*[@id='LogInForm']/form/button")).click();
	}
	
	@Then("^I should be logged in.$")
	public void I_should_be_logged_in(){
		Assert.assertTrue(driver.findElement(By.id("btnSignOut")).isDisplayed());
		driver.findElement(By.id("btnSignOut")).click();
	    System.out.println("I should be logged in.");
	    
	}

	//Attempt to login with invalid credentials 
	@When("^I sign in with the \"([^\"]*)\" email and password \"([^\"]*)\"$")
	public void I_sign_in_with_the_email_and_password(String email, String pwd){
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.id("LogInOptionMirror")).click();
		driver.findElement(By.id("LogOnModel_UserName")).clear();
		driver.findElement(By.id("LogOnModel_UserName")).sendKeys(email);
		driver.findElement(By.id("LogOnModel_Password")).clear();
		driver.findElement(By.id("LogOnModel_Password")).sendKeys(pwd);
		driver.findElement(By.xpath(".//*[@id='LogInForm']/form/button")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}

	@Then("^I should see the message \"([^\"]*)\"$")
	public void I_should_see_the_message(String message) throws InterruptedException{	
		Thread.sleep(1000);
		String text=driver.findElement(By.xpath("//*[@id='loginFieldset']/div[1]")).getText();
		//System.out.println(text);
	    Assert.assertTrue(text.contains(message));  
	}
	@Then("^I should get \"([^\"]*)\" message$")
	public void i_should_get_message(String message) throws Throwable {
		Thread.sleep(1000);
		String text=driver.findElement(By.xpath("//*[@id='LogInForm']")).getText();
		//System.out.println(text);
	    Assert.assertTrue(text.contains(message)); 
	}
	//Forgot password link
	@When("^I click the forgot my password link$")
	public void i_click_the_forgot_my_password_link() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)", "");
	    driver.findElement(By.xpath(".//*[@id='forgotten-password']")).click();
	    driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
	}

	@Then("^I should be prompted for my email address\\.$")
	public void i_should_be_prompted_for_my_email_address(){
		System.out.println(driver.getCurrentUrl());	
	    Assert.assertTrue(driver.getCurrentUrl().equals("https://www.wiggle.co.uk/secure/myaccount/RecoverPassword"));
	   
	}

	@After 
	public void closeAndExit(){
		System.out.println("Closing browser...");
		driver.quit();
	}
}
