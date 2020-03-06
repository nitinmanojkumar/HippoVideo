package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class EnterUserDetailsPage extends Helper{

	WebDriver driver;
	Helper helper;
	
	@FindBy(css="[id='firstNameTxt']")
	WebElement firstNMfield;
	
	@FindBy(css="[id='companyNameTxt']")
	WebElement coompanyfield;
	
	@FindBy(css="[id='phoneTxt']")
	WebElement phoneNofield;
	
	@FindBy(xpath="//*[@id='saveCompanyName']//img")
	WebElement getStartedBtn;
	
	public EnterUserDetailsPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}
	
	public CreateVideoPage enterDetails(String name,String company,String phoneNo) {
		try {
		helper.explicitWait(firstNMfield);
		firstNMfield.sendKeys(name);
		coompanyfield.sendKeys(company);
		phoneNofield.sendKeys(phoneNo);
		getStartedBtn.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new CreateVideoPage(this.driver);
	}
	
	
}
