/* @author Rakesh Mustoor
 * 
 */
package com.edkal.qa.testcases;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.edkal.qa.base.Base;
import com.edkal.qa.pages.HomePage;
import com.edkal.qa.pages.SearchResultPage;
import com.edkal.qa.util.TestUtilities;

public class AmazonSearchTests extends Base
{
	Logger logger = Logger.getLogger("devpinoyLogger");
	HomePage homePage;
	SearchResultPage searchPage;
	TestUtilities testUtil;
	
	public AmazonSearchTests()
	{
		super();
	}
	
	
	@BeforeMethod
	public void setup()
	{
		initialization();
		logger.info("Browser launched with the url 'www.amazon.com'");
		testUtil = new TestUtilities();
		homePage = new HomePage();
		searchPage = new SearchResultPage();
	}
	
	
	// Search for the Headphones and adds all the non duplicate Best seller items to the cart
	@Test(priority=0)
	public void addUniqueBestSellerItemsToCart()
	{
		logger.info("-- Execution started for new test case");
		homePage.searchTheKeyword(prop.getProperty("searchKeyword"));
		searchPage.addUniqueItemsToCart();
		//System.out.println("All Items: " + searchPage.totalUniqueBestSellerItems);
		//System.out.println("Cart Items: " + searchPage.getCartItemsCount());
		Assert.assertEquals(searchPage.totalUniqueBestSellerItems, searchPage.getCartItemsCount(), "Mismatch in items");
		logger.info("-- Execution ended for the test case");
	}
	
	
	// Search for the Headphones and adds all the Best seller items to the cart
	@Test(priority=1)
	public void addAllBestSellerItemsToCart()
	{
		logger.info("-- Execution started for new test case");
		homePage.searchTheKeyword(prop.getProperty("searchKeyword"));
		searchPage.addAllItemsToCart();
		//System.out.println("All Items: " + searchPage.totalBestSellerItems);
		//System.out.println("Cart Items: " + searchPage.getCartItemsCount());
		Assert.assertEquals(searchPage.totalBestSellerItems, searchPage.getCartItemsCount(), "Mismatch in items");
		logger.info("-- Execution ended for the test case");
	}
	

	@AfterMethod
	public void cleanUp()
	{
		driver.quit();
		logger.info("Browser is closed");
	}
}
