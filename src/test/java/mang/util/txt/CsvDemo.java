package mang.util.txt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

import org.junit.Test;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

public class CsvDemo {

	/**
	 * 来自 https://www.csvreader.com/java_csv_samples.php
	 * 是官方的例子 演示如何读取csv文件，而且其
	 * */
	@Test
	public void testRead() {
		try {

			CsvReader products = new CsvReader("c:/test/products.csv");
			products.readHeaders();
			while (products.readRecord()) {
				String productID = products.get("ProductID");
				String productName = products.get("ProductName");
				String supplierID = products.get("SupplierID");
				String categoryID = products.get("CategoryID");
				String quantityPerUnit = products.get("QuantityPerUnit");
				String unitPrice = products.get("UnitPrice");
				String unitsInStock = products.get("UnitsInStock");
				String unitsOnOrder = products.get("UnitsOnOrder");
				String reorderLevel = products.get("ReorderLevel");
				String discontinued = products.get("Discontinued");
				// perform program logic here
				System.out.println(productID + ":" + productName);
			}

			products.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRead2() throws URISyntaxException{
		try {

//			URL url = ClassLoader.getSystemResource("mang/util/txt/productsNoHead.csv");
			URL url = ClassLoader.getSystemResource("mang/util/txt/test2.csv");
			InputStream  inpurtStream=url.openStream();
			File file=new File(url.toURI());
			char del=',';
			Charset charset=Charset.forName("utf-8");
			CsvReader products = new CsvReader(inpurtStream,del,charset);
//			products.readHeaders();
			while (products.readRecord()) {
				//先读行后 才能取得列的数量
				int columnCount=products.getColumnCount();
				System.out.println("列数:"+columnCount);
				System.out.println("原始行:"+products.getRawRecord());
				String productID = products.get(0);
				String test2=products.get(1);
				String test6=products.get(6);
				String productName = products.get("ProductName");
				String supplierID = products.get("SupplierID");
				String categoryID = products.get("CategoryID");
				String quantityPerUnit = products.get("QuantityPerUnit");
				String unitPrice = products.get("UnitPrice");
				String unitsInStock = products.get("UnitsInStock");
				String unitsOnOrder = products.get("UnitsOnOrder");
				String reorderLevel = products.get("ReorderLevel");
				String discontinued = products.get("Discontinued");

				// perform program logic here
				System.out.println(productID + ":" + productName);
			}

			products.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 来自 https://www.csvreader.com/java_csv_samples.php
	 * 演示如何生成csv文件
	 * */
	@Test
	public void testWrite(){
		String outputFile = "c:/test/users.csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				csvOutput.write("id");
				csvOutput.write("name");
				csvOutput.endRecord();
			}
			// else assume that the file already has the correct header line
			
			// write out a few records
			csvOutput.write("1");
			csvOutput.write("Bruce");
			csvOutput.endRecord();
			
			csvOutput.write("2");
			csvOutput.write("John");
			csvOutput.endRecord();
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
