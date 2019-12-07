package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil 
{
	public static String getValueForKey(String key) throws Exception
	{
	Properties pr=new Properties();
	FileInputStream fi=new FileInputStream("D:\\Narendra1130\\Stock_Accounting\\PropertiesFile\\Repository.properties");
	pr.load(fi);
	fi.close();
	return(String) pr.get(key);
	}
	
	
}
