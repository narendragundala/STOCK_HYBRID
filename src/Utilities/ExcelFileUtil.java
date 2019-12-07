package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil
{
	static FileInputStream fi;
	static FileOutputStream fo;
	static Workbook wb;
	
	public  ExcelFileUtil() throws IOException
	{
		fi=new FileInputStream("D:\\Narendra1130\\Stock_Accounting\\TestInput\\InputSheet.xlsx");
		wb=new XSSFWorkbook(fi);		
	}
	public int rowCount(String sheetname) throws Exception
	{
		
		return wb.getSheet(sheetname).getLastRowNum();		
	}
	public int colCount(String sheetname) throws Exception
	{
		return wb.getSheet(sheetname).getRow(0).getLastCellNum();
	}
	public String getData(String sheetname, int row, int column) throws Exception
	{
		String data;
		
		if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata=(int) wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		//fi.close();
		return data;
	}
	public void setData(String sheetname, int row, int column, String result) throws Exception
	{
		FileInputStream fi=new FileInputStream("D:\\Narendra1130\\Stock_Accounting\\TestInput\\InputSheet.xlsx");
		Workbook wb=new XSSFWorkbook(fi);
		Sheet sh=wb.getSheet(sheetname);
		Row rownum=sh.getRow(row);
		Cell cell=rownum.createCell(column);
		cell.setCellValue(result);
		if(result.equalsIgnoreCase("pass"))
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		else
		{
			CellStyle style=wb.createCellStyle();
			Font font=wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo=new FileOutputStream("D:\\Narendra1130\\Stock_Accounting\\TestInput\\InputSheet.xlsx");
		wb.write(fo);
		wb.close();
		
		
	}
	
	
	
}
