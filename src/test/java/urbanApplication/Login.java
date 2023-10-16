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

public class Login {
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
	public void login() throws InterruptedException
	{
		WebElement username=driver.findElement(By.xpath("//input[@class='email required input_authentication'][1]"));
		username.sendKeys("naziadec@yahoo.co.in");
		WebElement password=driver.findElement(By.xpath("//input[@class='required input_authentication'][1]"));
		password.sendKeys("$abcd1234");
		WebElement signInBtn=driver.findElement(By.xpath("//input[@class='button primary'][1]"));
		signInBtn.click();
		
		WebElement signIn=driver.findElement(By.xpath("//a[@class='featuredLinksBar__link'][1]"));
		String ActualText=signIn.getText();
		System.out.println(ActualText);
		SoftAssert softAssert = new SoftAssert(); 
		String ExpectedText="Find a Store";
		softAssert.assertEquals(ActualText,ExpectedText);
		Thread.sleep(3000);
		 
	}
   
  
   
   
   
	@AfterMethod
	public void teardown()
	{
	
		driver.close();
	}
	

}

