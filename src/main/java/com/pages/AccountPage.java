package com.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class AccountPage {
	
private WebDriver driver;
	
	//1. By Locators: Object Repository
	private By accounSections= By.cssSelector("div#center_column span");

	
	//2. Constractor of the page class:
		public AccountPage(WebDriver driver) {
			this.driver=driver;
		}
	
		public int getAccountsSectionCount() {
			return driver.findElements(accounSections).size();
		}
		
		public List<String> getAccountSectionsList() {
			List<String> accountsList = new ArrayList<>();
			List<WebElement> accountsHeaderList=driver.findElements(accounSections);
			for(WebElement e: accountsHeaderList) {
				String text = e.getText();
				System.out.println(text);
				accountsList.add(text);
			}
			return accountsList;
			
		}
		
		public String getsAccountPageTitle() {
			return driver.getTitle();
		}
}
