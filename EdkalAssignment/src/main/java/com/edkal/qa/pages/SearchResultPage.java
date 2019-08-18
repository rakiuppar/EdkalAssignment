/* @author Rakesh Mustoor
 * 
 */
package com.edkal.qa.pages;

import com.edkal.qa.base.Base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchResultPage  extends Base
{
	private int i=1;
	private int j=1;
	public int duplicateCount=0;
	public int totalBestSellerItems;
	public int totalUniqueBestSellerItems;
	public int totalItemsInCart;
	public String previousString="";
	public String newString="";
	Logger logger = Logger.getLogger("devpinoyLogger");
	
	@FindBy(xpath="(//span[contains(text(),'Best Seller')]//following::a[1])")
	List<WebElement> bestSellerItems;
	
	@FindBy(id="add-to-cart-button")
	WebElement addToCartButton;
	
	@FindBy(xpath="//*[contains(@class,'close')]")
	WebElement closeIcon;
	
	@FindBy(id="buy-now-button")
	WebElement buyNowButton;
	
	@FindBy(id="nav-cart-count")
	WebElement cartCount;
	
	@FindBy(id="productTitle")
	WebElement title;
	
	
	public SearchResultPage() 
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void addAllItemsToCart()
	{
		logger.info("Adding all the Best Seller Headphones to the cart");
		totalBestSellerItems = bestSellerItems.size();
		if(totalBestSellerItems>0)
		{
			for(i=1; i<=totalBestSellerItems; i++)
			{
				try
				{
					driver.findElement(By.xpath("(//span[contains(text(),'Best Seller')]//following::a[1])[" + i +"]")).click();
					addToCartButton.click();
					closeIcon.click();
				}catch(Exception e){}
				WebDriverWait wait = new WebDriverWait(driver,10);
				wait.until(ExpectedConditions.visibilityOf(buyNowButton));
				driver.navigate().back();
			}
		}
	}	
	
	public void addUniqueItemsToCart()
	{
		logger.info("Adding unique (non duplicate) Best Seller Headphones to the cart");
		totalBestSellerItems = bestSellerItems.size();
		if(totalBestSellerItems>0)
		{
			for(j=1; j<=totalBestSellerItems; j++)
			{
				try
				{
					driver.findElement(By.xpath("(//span[contains(text(),'Best Seller')]//following::a[1])[" + j +"]")).click();
				}catch(Exception e){driver.navigate().refresh();			}
				newString = title.getText();
				logger.info("Checking whether the same product is added to cart earlier: "+ j);
				if(!previousString.contains(newString))
				{
					addToCartButton.click();
					try
					{
						closeIcon.click();
					}catch(Exception e){}
					WebDriverWait wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.visibilityOf(buyNowButton));
				} 
				else 	
				{
					duplicateCount++;	
				}
				driver.navigate().back();
				previousString = previousString+newString;
			}
			totalUniqueBestSellerItems = totalBestSellerItems - duplicateCount;
		}
	}
		
	public int getCartItemsCount()
	{		
		logger.info("Getting no of items from the cart");
		driver.navigate().refresh();
		totalItemsInCart = (Integer.parseInt(cartCount.getText()));
		return totalItemsInCart;
	}
	
}
