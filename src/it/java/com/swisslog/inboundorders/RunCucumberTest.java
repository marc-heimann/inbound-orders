package com.swisslog.inboundorders;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(		
		plugin ={
				"pretty", 
				"html:target/cucumber/inboundorders.html",
				"json:target/cucumber/inboundorders.json"
				},
		features = "src/it/resources",
		tags = {"~@ignored"}
)
public class RunCucumberTest {
 
}