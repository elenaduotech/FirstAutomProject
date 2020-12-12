package VerifyNewOrderCreation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class verifyNewOrderCreation{
	public static void main(String[] args) {
		
	
	// 1. Open the chrome browser and 2.Go to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
	System.setProperty("webdriver.chrome.driver", "C:\\SeleniumFiles\\browserDrivers\\chromedriver.exe");
	
	WebDriver driver = new ChromeDriver(); 
			
	driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
	
	
	
	// 3. Login using username Tester and password test
	
	driver.findElement(By.id("ctl00_MainContent_username")).sendKeys(Keys.chord("Tester", Keys.ENTER));
	driver.findElement(By.id("ctl00_MainContent_password")).sendKeys(Keys.chord("test", Keys.ENTER));
	
	
	// 4. Click on Order link
	driver.findElement(By.xpath("//a[@href='Process.aspx']")).click();
	
	
	// 5. Enter a random quantity between 1 and 100
	int quantity = (int)(1 + (Math.random()*100));
	String q = String.valueOf(quantity);
	
	driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Keys.BACK_SPACE);
	
	driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(q);
	
	
	
     //	6. Enter Customer Name: John <Middle Name > Doe.  Instead of <Middle Name> your code should enter a random string of length 5 every time.
	char middleName1=(char)(int)(65 + Math.random()*26);
	String middleName = "";
	for(int i=0; i<4; i++) {
		middleName+=(char)(int)(97+Math.random()*26);
		}
	middleName = middleName1+middleName;
	driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(Keys.chord("John " + middleName + " Doe", Keys.ENTER));
	
     //	7. Enter street: 8607 Westwood Center Dr
	driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(Keys.chord("8607 Westwood Center Dr", Keys.ENTER));
	
     //	8. Enter City: Vienna
	driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Vienna");
	
     //	9. Enter State: Virginia
	driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Virginia");
	
     //	10. Enter a random 5 digit number to the zip code field
	int zipcode = (int)(10000 + Math.random()*90000);
	String zipcodeS = String.valueOf(zipcode);
	driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zipcodeS);
	
	
     //	11. Select any card type. Every time your code should select a different type.
	String cardEndingNumber = "" + (int)(Math.random()*3);
	String cardButton = "//input[@id='ctl00_MainContent_fmwOrder_cardList_" + cardEndingNumber +"']";
	driver.findElement(By.xpath(cardButton)).click();
		
	
     //	12. Enter any card number: 	If you selected Visa, card number should start with 4. If you selected Master, card number should start with 5. 
     //	If you selected American Express, card number should start with 3. New card number should be auto generated every time you run the test. 
     //	Card numbers should be 16 digits for Visa and Master, 15 for American Express.
	String cardNumber ="";
	if(cardButton.endsWith("0")) {
		for(int i=0; i<15; i++) {
			cardNumber+=(int)(Math.random()*9);}
		String visaNumber = "4" + cardNumber;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(visaNumber);
	}else if(cardButton.endsWith("1")) {
		for(int i=0; i<15; i++) {
			cardNumber+=(int)(Math.random()*9);}
		String masterNumber = "5" + cardNumber;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(masterNumber);
	}else {
		for(int i=0; i<14; i++) {
			cardNumber+=(int)(Math.random()*9);}
		String americanNumber = "3" + cardNumber;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(americanNumber);
	}
	
     //	13. Enter a valid expiration date (newer than the current date)
	int month = (int)(1+Math.random()*11);
	String monthS = "" + month;;
	if(month<10) {
		monthS="0"+month;
	}
   
	int year = (int)(21 + Math.random()*3);
	String yearS = "" + year;
	String ccDate = monthS + "/" + yearS;
	
	driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(ccDate);
	
    //	14. Click on Process
	driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
	
    //	15. Verify that the page contains text “New order has been successfully added”.
	String expectedResults = "New order has been successfully added.";
	String actualResults = driver.findElement(By.xpath("//*[contains(text(), 'New order has been successfully added')]")).getText(); 
	if(expectedResults.contains(actualResults))
		System.out.println("Pass!");
	else
		System.out.println("Fail");
	
	}

}