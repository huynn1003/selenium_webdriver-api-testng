package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_02_Xpath_CSS_Part_I {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Login_With_Emty_Email_And_Password() {
		driver.get("http://live.demoguru99.com/");		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();		
		driver.findElement(By.id("email")).sendKeys("");		
		driver.findElement(By.name("login[password]")).sendKeys("");		
		driver.findElement(By.xpath("//button[@title='Login']")).click();		
		String errorEmailMessage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(errorEmailMessage, "This is a required field.");		
		String errorPasswordMessage = driver.findElement(By.id("advice-required-entry-pass")).getText();
		Assert.assertEquals(errorPasswordMessage, "This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.demoguru99.com/");		
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("1234141@12312312.123");		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String errorEmailMessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(errorEmailMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_03_Login_With_Password_Less_Than_6_Characters() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();	
		driver.findElement(By.id("email")).sendKeys("huynguyen@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("12");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String errorPasswordMessage = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(errorPasswordMessage, "Please enter 6 or more characters without leading or trailing spaces.");		
	}
	
	@Test
	public void TC_04_Login_With_Incorrect_Password() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("asd1234");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		String incorrectPasswordMessage = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(incorrectPasswordMessage, "Invalid login or password.");
	}
	
	@Test
	public void TC_05_Login_With_Valid_Email_And_Password() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.name("login[password]")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//Cách 1: tuyệt đối: Text ngắn / Không xuống dòng / Tab / Khoảng trắng đầu / cuối chuỗi
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']/h1")).getText(), "My Dashboard".toUpperCase());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[text()='My Dashboard']")).isDisplayed());
		
		//Cách 2: tuyệt đối: Text ngắn / Không xuống dòng / Tab / Khoảng trắng đầu / cuối chuỗi
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']/h1[contains(text(),'My Dashboard')]")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='hello']/strong")).getText() , "Hello, Automation Testing!");
		
		// Tương đối
		String contactInfoText = driver.findElement(By.xpath("//div[@class='box-content']//p")).getText();
		Assert.assertTrue(contactInfoText.contains("Automation Testing"));
		Assert.assertTrue(contactInfoText.contains("automation_13@gmail.com"));
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'Automation Testing')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'automation_13@gmail.com')]")).isDisplayed());
		
		//Log out
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title ='Log Out']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//div[@class='page-title']//h2[contains(text(),'This is demo site for')]")).isDisplayed();

	}
	
	@Test
	public void TC_06_Register_New_Account() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//div[@class='buttons-set']/a[@title='Create an Account']")).click();
		
		String firstName = "Huy";
		String lastName = "Nguyen";
		String email = "nnh" + randomNumber() + "@gmail.com";
		
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);;
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys("123123");
		driver.findElement(By.id("confirmation")).sendKeys("123123");
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		String contactInfoText = driver.findElement(By.xpath("//div[@class='box-content']//p")).getText();
		Assert.assertTrue(contactInfoText.contains(firstName + " " +lastName));
		Assert.assertTrue(contactInfoText.contains(email));
		
		//Log out
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title ='Log Out']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//div[@class='page-title']//h2[contains(text(),'This is demo site for')]")).isDisplayed();
	}
	
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
