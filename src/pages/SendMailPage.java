package pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class SendMailPage extends Helper {

	WebDriver driver;
	Helper helper;

	@FindBy(id = "videoCampaignsTab-heading")
	WebElement videoCampaignTab;

	@FindBy(id = "videoCampaign")
	WebElement sendMailTab;

	@FindBy(id = "grantAccessBtn")
	WebElement configureMailBtn;

	@FindBy(xpath = "(//span[@class='add-contacts-btn'])[1]")
	WebElement addContactBtn;

	@FindBy(xpath = "//*[@id='importContactsPopupId' and contains(@style,'visible')]")
	WebElement importContactsOverLay;

	@FindBy(xpath = "(//*[@name='email'])[1]")
	WebElement emailField1;

	@FindBy(id = "addContacts")
	WebElement submitBtn;

	@FindBy(id = "emailSubject")
	WebElement subjectField;

	@FindBy(id = "sendEmailBtn")
	WebElement sendEmailBtn;

	@FindBy(xpath = "//*[@id='sendEmailBtn' and @disabled='disabled']")
	WebElement sendEmailBtnDisabled;

	// Configuration Window
	@FindBy(id = "configureGmailSettingsBtn")
	WebElement configureGMailBtn;

	// Gmail config
	@FindBy(id = "identifierId")
	WebElement mailIdField;

	@FindBy(css = "[type='password']")
	WebElement pwdField;

	@FindBy(xpath = "//*[text()='Next']")
	WebElement nextBtn;

	@FindBy(xpath = "//*[text()='Allow']")
	WebElement allowBtn;

	public SendMailPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}

	public boolean compose_EMail() {
		try {
			helper.explicitWait(videoCampaignTab);
			videoCampaignTab.click();
			helper.explicitWait(sendMailTab);
			sendMailTab.click();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean configureEMail(String mailID, String pwd) {
		try {

			String parentWindow = driver.getWindowHandle();

			helper.explicitWait(configureMailBtn);
			configureMailBtn.click();
			Thread.sleep(3000);
			Set<String> set = driver.getWindowHandles();
			for (String string : set) {
				if (!string.equals(parentWindow)) {
					driver.switchTo().window(string);

				}
			}
			helper.explicitWait(configureGMailBtn);
			configureGMailBtn.click();

			Set<String> set1 = driver.getWindowHandles();
			for (String string : set1) {
				if (!set.contains(string)) {
					driver.switchTo().window(string);
				}
			}
			helper.explicitWait(mailIdField);
			mailIdField.sendKeys(mailID);
			nextBtn.click();
			helper.explicitWait(pwdField);
			pwdField.sendKeys(pwd);
			nextBtn.click();

			helper.explicitWait(allowBtn);
			allowBtn.click();
			Thread.sleep(1000);

			driver.switchTo().window(parentWindow);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean sendEMail(String mailIDTo, String subject) {
		try {

			helper.explicitWait(sendEmailBtn);
			addContactBtn.click();
			helper.explicitWait(importContactsOverLay);
			emailField1.sendKeys(mailIDTo);
			submitBtn.click();

			helper.explicitWait(sendEmailBtn);
			subjectField.sendKeys(subject);
			sendEmailBtn.click();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
