package common;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import pages.SignIn_Page;

public class TestBase {

	public static WebDriver driver;
	public static String parentWindowID;

	@BeforeSuite()
	public void beforeSuite() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");

		ChromeOptions options = new ChromeOptions();

		options.addExtensions(
				new File(System.getProperty("user.dir") +"\\extensions\\extension_3_13_6_0.crx"));
		options.addArguments("use-fake-ui-for-media-stream");

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		Set<String> set = driver.getWindowHandles();
		Thread.sleep(1000);
		String parentWindow = null;
		
		for (String string : set) {

			System.out.println(string);
			driver.switchTo().window(string);
			System.out.println(driver.getTitle());
			if (driver.getTitle().equals("Hippo Video - Onboard")) {
				new Helper(driver).explicitWait(driver.findElement(By.cssSelector("[class='wiz-primary-btn goi-it-btn']")));
				driver.findElement(By.cssSelector("[class='wiz-primary-btn goi-it-btn']")).click();
			}else {
				parentWindow=string;
			}
		}

		driver.switchTo().window(parentWindow);
		parentWindowID=parentWindow;
		
	}

	@AfterSuite
	public void afterSuite() {
		//driver.quit();
	}

}
