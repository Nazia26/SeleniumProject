package urbanApplication;

import org.openqa.selenium.By;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyText {
	WebDriver driver;
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.urbanladder.com/login");
		Thread.sleep(4000);
	}
   @Test
	public void VerifyTextBox() throws InterruptedException
	{
	   WebElement username=driver.findElement(By.xpath("//input[@class='email required input_authentication'][1]"));
		username.sendKeys("naziadec@yahoo.co.in");
		WebElement password=driver.findElement(By.xpath("//input[@class='required input_authentication'][1]"));
		password.sendKeys("$abcd1234");
		WebElement signInBtn=driver.findElement(By.xpath("//input[@class='button primary'][1]"));
		signInBtn.click();
	    WebElement cart=driver.findElement(By.id("header-icon-cart"));
		cart.click();
		WebElement checkout=driver.findElement(By.xpath("//button[@data-event='Checkedout Order']"));
		checkout.click();
		Thread.sleep(4000);
		WebElement textbox=driver.findElement(By.xpath("//input[@id='order_ship_address_attributes_firstname']"));
		textbox.sendKeys("123435@#@");
		SoftAssert softAssert = new SoftAssert(); 
	    String EnteredText=textbox.getAttribute("value");
	    boolean result = EnteredText.matches("[a-zA-Z]+");
		softAssert.assertTrue(result);
		softAssert.assertAll();
		Thread.sleep(4000);
	     
	}
   @AfterMethod
	public void teardown()
	{
	
		driver.close();
	}
}