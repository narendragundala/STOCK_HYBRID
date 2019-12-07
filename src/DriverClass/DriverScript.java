package DriverClass;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;
public class DriverScript
{	
	static WebDriver driver;
	@Test
	public static void main() throws Exception
	{	
		System.out.println("Test Started");
		ExcelFileUtil excel=new ExcelFileUtil();
		System.out.println("Excel object Created");
		int masterrc=excel.rowCount("MasterTestCases");
		System.out.println(masterrc);		
		for(int i=1;i<=masterrc;i++)
		{
			System.out.println(excel.getData("MasterTestCases", i, 2));			
			if(excel.getData("MasterTestCases", i, 2).equalsIgnoreCase("Y"))
		{		
				String Module_Status=null;
				String tcmodule=excel.getData("MasterTestCases", i, 1);
				ExtentReports report=new ExtentReports("D:\\Narendra1130\\Stock_Accounting\\Reports"+tcmodule+FunctionLibrary.generateDate()+".html");
				int modulerc=excel.rowCount(tcmodule);
				for (int j = 1; j <=modulerc; j++) 
				{
					ExtentTest test=report.startTest(tcmodule);
					String Description=excel.getData(tcmodule, j, 0);
					String Object_Type=excel.getData(tcmodule, j, 1);
					String Locator_Type=excel.getData(tcmodule, j, 2);
					String Locator_Value=excel.getData(tcmodule, j, 3);
					String Test_Data=excel.getData(tcmodule, j, 4);
					
					try{
					if(Object_Type.equalsIgnoreCase("startBrowser"))
					{
						driver=FunctionLibrary.startBrowser();
					Module_Status = "True";
					System.out.println("Browser Started");
					excel.setData(tcmodule, j, 5, "PASS");
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("openApplication"))
					{
						FunctionLibrary.openApplication();	
						 Module_Status = "True";
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
						FunctionLibrary.waitForElement(Locator_Type, Locator_Value, Test_Data);
						 Module_Status = "True";
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("typeAction"))
					{
						FunctionLibrary.typeAction(Locator_Type, Locator_Value, Test_Data);
						 Module_Status = "True";
						test.log(LogStatus.INFO, Description);
					}
					if(Object_Type.equalsIgnoreCase("clickAction"))
					{
						FunctionLibrary.clickAction(Locator_Type, Locator_Value);
						 Module_Status = "True";
						test.log(LogStatus.INFO, Description);
					}
					else if(Object_Type.equalsIgnoreCase("waitForElement"))
					{
					FunctionLibrary.waitForElement(Locator_Type, Locator_Value, Test_Data);	
					 Module_Status = "True";
					test.log(LogStatus.INFO, Description);
					}
						
					else if(Object_Type.equalsIgnoreCase("closeBrowser"))
					{
						FunctionLibrary.closeBrowser();
						 Module_Status = "True";
						test.log(LogStatus.INFO, Description);
					}					
					excel.setData(tcmodule, j, 5, "PASS");	
					System.out.println("Data entered into Excel");
					test.log(LogStatus.PASS, Description);
					}
					
					catch(Exception e)
					{
						File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						System.out.println("exception handled");						
 						FileUtils.copyFile(srcFile, new File("D:\\Narendra1130\\Stock_Accounting\\Screens\\"+Description+FunctionLibrary.generateDate()+".png"));
						excel.setData(tcmodule, j, 5, "FAIL");
						test.log(LogStatus.FAIL, Description);
						break;
					}
					report.flush();
					report.endTest(test);
					
				}
			}
		}
	}

}
