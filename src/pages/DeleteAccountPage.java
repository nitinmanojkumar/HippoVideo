package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;
import common.TestBase;

public class DeleteAccountPage extends Helper {

	WebDriver driver;
	Helper helper;

	@FindBy(css = "[class='topbar-profile-pic topbar-profile-text first-letter']")
	WebElement profileToolPick;

	@FindBy(id = "userProfileSettings")
	WebElement profileSettingBtn;

	@FindBy(id = "deactivateUserInPage")
	WebElement deactivateUserLink;
	
	@FindBy(id = "proceed_btn")
	WebElement proceedBtn;

	@FindBy(css = "[name='tech']")
	WebElement surveyField;
	
	@FindBy(css = "[class='button wiz-primary-btn un-submit-btn unsubmit']")
	WebElement submitBtn;

	public DeleteAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}

	public void deleteAccount() {
		try {
			driver.switchTo().window(TestBase.parentWindowID);
			helper.explicitWait(profileToolPick);
			profileToolPick.click();
			helper.explicitWait(profileSettingBtn);
			profileSettingBtn.click();
			helper.explicitWait(deactivateUserLink);
			deactivateUserLink.click();
			helper.explicitWait(proceedBtn);
			proceedBtn.click();
			driver.switchTo().window(TestBase.parentWindowID);
			helper.explicitWait(surveyField);
			surveyField.sendKeys("test");
			submitBtn.click();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

}
