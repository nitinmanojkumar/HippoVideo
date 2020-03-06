package pagesForExtension;

import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import common.Helper;

public class extensionsPage extends Helper {

	WebDriver driver;
	Helper helper;

	@FindBy(id = "searchbox-input")
	WebElement searchBoxField;

	@FindBy(xpath = "//*[text()='https://www.hippovideo.io']")
	WebElement hippoVideoLink;
	
	@FindBy(id = "extension-url")
	WebElement urlField;
	
	@FindBy(id = "form-extension-downloader-btn")
	WebElement downloadBtn;

	public extensionsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}
	
	public void download() {
		try {
			helper.explicitWait(searchBoxField);
			new Actions(driver).moveToElement(searchBoxField).sendKeys("hippo video").sendKeys(Keys.ENTER).build().perform();
			Thread.sleep(1000);
			helper.explicitWait(hippoVideoLink);
			hippoVideoLink.click();
			Thread.sleep(3000);
			String url=driver.getCurrentUrl();
			driver.get("https://crx-downloader.com/");
			helper.explicitWait(urlField);
			urlField.sendKeys(url);
			downloadBtn.click();
			
		}catch(Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to download extension");
		}
	}

}
