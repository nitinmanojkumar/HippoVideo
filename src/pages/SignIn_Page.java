package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class SignIn_Page extends Helper {

	WebDriver driver;
	Helper helper;

	@FindBy(xpath = "(//*[@type='submit'])[1]")
	WebElement signUpBtn1;

	@FindBy(xpath = "(//*[@class='panel home']//*[@type='email'])")
	WebElement emailField;

	@FindBy(xpath = "(//*[@class='panel home']//*[@type='password'])")
	WebElement pwdField;

	@FindBy(xpath = "(//*[@type='submit'])[2]")
	WebElement signUpBtn2;

	@FindBy(xpath = "//*[@class='signup_message error-box']")
	WebElement errorMsg;

	@FindBy(css = "[aria-label='login']")
	WebElement loginBtn;

	@FindBy(id = "user_email")
	WebElement SignInemailField;

	@FindBy(id = "user_password")
	WebElement SignInpwdField;

	@FindBy(css = "[type='submit']")
	WebElement SignInSubmitBtn;
	
	@FindBy(xpath = "//*[text()='SIGNING UP...']")
	WebElement signingUP;
	
	@FindBy(xpath = "//*[text()='SIGNUP FOR FREE']")
	WebElement signingUPForFree;

	public SignIn_Page(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}

	public boolean signUp(String usnm, String pwd) {
		try {
			helper.explicitWait(signUpBtn1);
			signUpBtn1.click();
			emailField.sendKeys(usnm);
			pwdField.sendKeys(pwd);
			signUpBtn2.click();
			helper.explicitWait(signingUP);
			String error=errorMsg.getText();
			helper.explicitWait(signingUPForFree);
			String error1=errorMsg.getText();
			Thread.sleep(3000);
			
			if (!error.equals("") || !error1.equals("")) {
				loginBtn.click();
				helper.explicitWait(SignInemailField);
				SignInemailField.sendKeys(usnm);
				SignInpwdField.sendKeys(pwd);
				SignInSubmitBtn.click();
				Thread.sleep(3000);
				return false;
			}else {
				return true;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return true;
		}
	}

}
