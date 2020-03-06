package common;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {

	WebDriver driver;

	public Helper(WebDriver driver) {
		this.driver = driver;
	}

	public void explicitWait(WebElement element) {
		try {
			WebDriverWait webdriverWait = new WebDriverWait(driver, 20);
			webdriverWait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {

		}
	}

	public void explicitWait_Invisible(WebElement element) {
		WebDriverWait webdriverWait = new WebDriverWait(driver, 20);
		webdriverWait.until(ExpectedConditions.invisibilityOf(element));
	}

	public void explicitWait_Clickable(WebElement element) {
		WebDriverWait webdriverWait = new WebDriverWait(driver, 20);
		webdriverWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void explicitWait_Alertpresent() {
		try {
			WebDriverWait webdriverWait = new WebDriverWait(driver, 20);
			webdriverWait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
