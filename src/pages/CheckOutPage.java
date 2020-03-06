package pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class CheckOutPage {

	WebDriver driver;
	Helper helper = new Helper(driver);

	@FindBy(css = "[class='account']")
	WebElement viewProfile;

	@FindBy(css = "[title='Orders']")
	WebElement orderHistory;
	
	@FindBy(css = "[title='Log me out']")
	WebElement signOutBtn;
	
	
	public CheckOutPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void orderHistory() throws InterruptedException, IOException {
		Thread.sleep(3000);
		helper.explicitWait( viewProfile);
		viewProfile.click();
		helper.explicitWait(orderHistory);
		orderHistory.click();
		File src=((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"OrderDetails.png"));
		signOutBtn.click();
	}
	
	
	
}
