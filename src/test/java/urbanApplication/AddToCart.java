package urbanApplication;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddToCart {


WebDriver driver;

@BeforeSuite
public void setup() throws InterruptedException
{
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://www.urbanladder.com/login");
	Thread.sleep(4000);
}
@Test
public void SearchTest() throws InterruptedException
{
	SoftAssert softAssert = new SoftAssert(); 
	WebElement username=driver.findElement(By.xpath("//input[@class='email required input_authentication'][1]"));
	username.sendKeys("naziadec@yahoo.co.in");
	WebElement password=driver.findElement(By.xpath("//input[@class='required input_authentication'][1]"));
	password.sendKeys("$abcd1234");
	WebElement signInBtn=driver.findElement(By.xpath("//input[@class='button primary'][1]"));
	signInBtn.click();
	WebElement signIn=driver.findElement(By.xpath("//a[@class='featuredLinksBar__link'][1]"));
	String Actualtext=signIn.getText();
	String Expectedtext="Find a Store";
	softAssert.assertEquals(Actualtext,Expectedtext);
	System.out.println("Log in Successful");
	
	WebElement search=driver.findElement(By.xpath("//input[@id='search']"));
	search.sendKeys("Trissino Sofa"+Keys.ENTER);
	driver.findElement(By.xpath("//span[text()='(Cream Italian Leather)'][1]")).click();
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	jse.executeScript("window.scrollBy(0,700)","");
	driver.findElement(By.xpath("//button[@id='add-to-cart-button']")).click();
	driver.get("https://www.urbanladder.com/cart");
	Thread.sleep(4000);
	
	
	WebElement checkout=driver.findElement(By.xpath("//button[@data-event='Checkedout Order']"));
	String ActualText=checkout.getText();
	System.out.println(ActualText);
	String ExpectedText="CHECKOUT";
    softAssert.assertEquals(ActualText,ExpectedText);
	softAssert.assertAll(); 
	System.out.println("Added To Cart Successfully!!");
	
	
}
@AfterSuite
public void teardown()
{
	
	driver.close();
	 
}
}


