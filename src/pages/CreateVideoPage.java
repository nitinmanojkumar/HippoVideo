package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class CreateVideoPage  extends Helper{

	WebDriver driver;
	Helper helper;
	
	@FindBy(css="[class='importIcon import']")
	WebElement addIcon;
	
	@FindBy(css="[data-name^='Create video _']")
	WebElement createVideoBtn;
	
	public CreateVideoPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}
	
	public RecordVideoPage creatingVideo() {
		try {
		helper.explicitWait(addIcon);
		addIcon.click();
		helper.explicitWait(createVideoBtn);
		createVideoBtn.click();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new RecordVideoPage(this.driver);
	}
	
	
}
