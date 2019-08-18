/* @author Rakesh Mustoor
 * 
 */
package com.edkal.qa.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.edkal.qa.base.Base;

public class TestUtilities extends Base
{
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	
	public static void takeScreenshotAtEndOfTest() throws IOException 
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String curDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(curDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
}
