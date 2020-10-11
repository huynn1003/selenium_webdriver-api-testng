package webdriver;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class Topic_04_WebBrowser_Command {
	WebDriver driver;
	String project_location = System.getProperty("user.dir");


	@Test
	public void TC_01_Web_Browser() {
		// Apply cho tab/ window
				driver.close(); // **

				// Apply cho browser
				driver.quit(); // **

				// Mở ra 1 web app (Url)
				driver.get("https://www.facebook.com/"); // **

				// Các hàm mà tương tác lên trình duyệt/ element -> kiểu trả về của hàm là void
				// Các hàm mà lấy ra dữ liệu thì sẽ có kiểu trả về chứa dữ liệu đó (String/ int/ boolean)

				// Lấy ra cái Url của page hiện tại
				String loginPageUrl = driver.getCurrentUrl(); // **
				// loginPageUrl = https://www.facebook.com/
				Assert.assertEquals(loginPageUrl, "https://www.facebook.com/");
				Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

				// Lấy ra HTML Code của page hiện tại
				driver.getPageSource();

				// Lấy ra cái title của page hiện tại
				driver.getTitle(); // **
				Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
				// Facebook - Đăng nhập hoặc đăng ký

				// Lấy ra cái GUID của tab/ window hiện tại
				driver.getWindowHandle(); // **

				// Lấy ra cái GUID của all tab/ window đang có
				driver.getWindowHandles(); // **

				// Chờ cho element được load ra thành công trong vòng 15 second
				driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // **

				// Chờ cho page được load thành công
				driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

				// JS Executor
				driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);

				// Fullscreen
				driver.manage().window().fullscreen();

				driver.manage().window().maximize(); // **

				// Responsive
				// driver.manage().window().setSize(arg0);

				// Back lại page trước
				driver.navigate().back();

				// Forward tới page trước
				driver.navigate().forward();

				// Tải lại trang
				driver.navigate().refresh();

				driver.navigate().to("https://www.facebook.com/");
				
				// Alert/ Iframe (Frame)/ Window (Tab)
				driver.switchTo().alert(); // **
				
				driver.switchTo().frame(""); // **
				
				driver.switchTo().window(""); // **
	}



	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
