package urbanApplication;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browse {
	WebDriver driver;
	@BeforeSuite
	public void setup() throws InterruptedException
	{
	
	WebDriverManager.chromedriver().setup();	
	 driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
	driver.get("https://www.urbanladder.com/");
	Thread.sleep(2000);
	}
	@Test
	public void browse() throws InterruptedException
	{
		SoftAssert softAssert = new SoftAssert(); 
		Actions act=new Actions(driver);
		WebElement DealZone=driver.findElement(By.xpath("(//span[@class='topnav_itemname'])[1]"));
		act.moveToElement(DealZone).perform();
		WebElement dailyDeals=driver.findElement(By.xpath("(//a[@rel=\"nofollow\"])[1]"));
		dailyDeals.click();
		WebElement dailyDealPage=driver.findElement(By.xpath("//h1[@data-taxon-name='Daily Deals']"));
		String ActualText=dailyDealPage.getText();
		System.out.println(ActualText);
		String ExpectedText="Daily Deals";
	     softAssert.assertEquals(ActualText,ExpectedText);
		softAssert.assertAll(); 
		Thread.sleep(3000);

	}
	@AfterTest
	public void teardown()
	{
		driver.close();
	}
	

}
