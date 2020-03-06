package pages;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class RecordVideoPage extends Helper {

	WebDriver driver;
	Helper helper;

	@FindBy(xpath = "(//*[@id='startRecording']//*)")
	WebElement recordBtn;

	@FindBy(css = "[id='chrome-modalYesBtn']")
	WebElement installPlugin;

	@FindBy(xpath = "(//*[@aria-label='Add to Chrome'])[1]")
	WebElement addTochromeBtn;
	
	@FindBy(css = "[class='wiz-inner-box selected-wiz screen-selected float-right record-enable wiz-tooltip']")
	WebElement screenRecordSelected;

	@FindBy(css = "[class='wiz-inner-box screen-selected float-right record-enable wiz-tooltip']")
	WebElement screenRecordUnSelected;

	@FindBy(css = "[id='hippo-inner-div']")
	WebElement recordingOverLay;
	
	@FindBy(css = "[id='hv-previewcontrol-stop']")
	WebElement stopRecodingBtn;
	
	public RecordVideoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}

	public PersonalizeVideo startRecord() {
		try {
			Thread.sleep(3000);
			helper.explicitWait_Clickable(recordBtn);
			
			helper.explicitWait(screenRecordSelected);
			screenRecordSelected.click();
			helper.explicitWait(screenRecordUnSelected);
		
			System.out.println(recordBtn.getClass().toString());
			System.out.println(recordBtn.getLocation());
			System.out.println(recordBtn.getRect());
			System.out.println(recordBtn.getSize());

			new Actions(driver).moveToElement(recordBtn).click().perform();
			
			Thread.sleep(5000);
			helper.explicitWait(recordingOverLay);
			Thread.sleep(10000);
			stopRecodingBtn.click();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new PersonalizeVideo(this.driver);

	}

}
