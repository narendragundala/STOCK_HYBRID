package CommonFunLibrary;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;

public class FunctionLibrary 
{
	static WebDriver driver;
	public static WebDriver startBrowser() throws Exception
	{
		String browser = PropertyFileUtil.getValueForKey("Browser");
		if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "D:\\Narendra1130\\Stock_Accounting\\CommonJArs\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		return driver;
	}
	public static void openApplication() throws Exception
	{
		driver.get(PropertyFileUtil.getValueForKey("url"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	public static void waitForElement(String attType,String attValue, String timetoWait)
	{
		WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(timetoWait));
		if (attType.equalsIgnoreCase("xpath")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(attValue)));
		}else if (attType.equalsIgnoreCase("id")) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(attValue)));
		}else{
			System.out.println("Not such element found in WeitForElement Section");
		}
		
	}
	
	public static void typeAction(String attType, String attValue, String testData)
	{
		if (attType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(attValue)).clear();
			driver.findElement(By.id(attValue)).sendKeys(testData);
		} 
		else if (attType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(attValue)).clear();
			driver.findElement(By.xpath(attValue)).sendKeys(testData);
		}
		else
		{
			System.out.println("Element not found");
		}
	}
	public static void clickAction(String attType, String attValue)
	{
		if (attType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(attValue)).click();
		}
		else if (attType.equalsIgnoreCase("xpath")) 
		{
			driver.findElement(By.xpath(attValue)).click();
		}
		else 
		{
			System.out.println("Element not found");
		}
	}
	public static void closeBrowser()
	{
		driver.close();
	}
	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
		
	}
}