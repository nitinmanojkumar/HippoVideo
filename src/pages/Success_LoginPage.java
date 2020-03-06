package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class Success_LoginPage extends Helper{

	WebDriver driver;
	Helper helper;
	
	@FindBy(css="[id='headerTitleNotificationCount']")
	WebElement successLoginTitle;
	
	@FindBy(css="div[id^='Business'] span")
	WebElement businessBtn;
	
	@FindBy(css="div[id^='Marketing'] span")
	WebElement personaliseBtn;
	
	@FindBy(css="[id='Business-next-btn']")
	WebElement nextBtn;
	
	public Success_LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}
	
	public EnterUserDetailsPage Login_Business_PersonalisedVideo() {
		try {
		helper.explicitWait(businessBtn);
		businessBtn.click();
		helper.explicitWait(personaliseBtn);
		personaliseBtn.click();
		helper.explicitWait(nextBtn);
		nextBtn.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new EnterUserDetailsPage(this.driver);
	}
	
	
}
