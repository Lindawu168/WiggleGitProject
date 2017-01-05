package com.wiggle;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions( format = "html: target/cucumber_report"
            )
public class RunTest {}



/*Note: "Scenario Outline" only works when:
 * Got to Help/About Eclipse, click on "Installation Details". Select "Cucumber Eclipse Feature" 
 * and click in "Update...". Don't worry if your plug-in is up to date...
 */

/*Now open your POM file (Maven Project) and set artifacts "cucumber-java", 
 * "cucumber-core" and "cucumber-junit" to version 1.2.2 at least 
 * (junit must be at least 4.11).
 */

/* The last negative case Failed: the expected should be "Please use a valid email address in the format: name@site.domain"*/