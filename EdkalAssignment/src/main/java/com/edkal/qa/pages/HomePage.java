/* @author Rakesh Mustoor
 * 
 */
package com.edkal.qa.pages;

import com.edkal.qa.base.Base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Base
{
	@FindBy(id="twotabsearchtextbox")
	WebElement searchInputBox;
	
	@FindBy(xpath="//div[@class='nav-search-submit nav-sprite']")
	WebElement searchButton;
	
	Logger logger = Logger.getLogger("devpinoyLogger");
	
	public HomePage() 
	{
		PageFactory.initElements(driver, this);
	}
	
	public void loadUrl(String url)
	{
		driver.get(url);
	}
	
	public void searchTheKeyword(String inputKeyword)
	{
		searchInputBox.sendKeys(inputKeyword);
		searchButton.click();
		logger.info("Searching for the keyword 'Headphones'");
	}
}
