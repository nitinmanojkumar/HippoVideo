package pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Helper;

public class PersonalizeVideo extends Helper {

	WebDriver driver;
	Helper helper;

	@FindBy(id = "personlizationTab-heading")
	WebElement personalizeTab;

	@FindBy(id = "videoPersonalization")
	WebElement videoTab;

	@FindBy(id = "personalizeButton")
	WebElement personalizeBtn;

	@FindBy(id = "saveSimpleEdit")
	WebElement saveBtn;

	@FindBy(css = ".simple-edit-trim")
	WebElement editTrimIcon;

	@FindBy(css = "[class='trim-after-time']")
	WebElement trimmedAfterTime;

	@FindBy(css = "[id='trimSlider'] [class^='ui-slider-handle ui-corner-all ui-state-default'][style='left: 100%;']")
	WebElement moveHorizontalFrom;

	@FindBy(css = "[id='trimSlider'] [class^='ui-slider-handle ui-corner-all ui-state-default'][style='left: 0%;']")
	WebElement moveHorizontalTo;

	@FindBy(css = "[class='player-currenttime']")
	WebElement playerCurrentTime;

	public PersonalizeVideo(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		helper = new Helper(driver);
	}

	public SendMailPage selectAndRecordAndTrim() {
		try {
			helper.explicitWait(personalizeTab);
			personalizeTab.click();
			helper.explicitWait(videoTab);
			videoTab.click();
			helper.explicitWait(personalizeBtn);
			personalizeBtn.click();
			helper.explicitWait(saveBtn);

			String timeOfVideoBeforeTrim = playerCurrentTime.getText();
			System.out.println("Time of the video before editing : " + timeOfVideoBeforeTrim);

			editTrimIcon.click();

			// Actions class method to drag and drop
			Actions builder = new Actions(driver);

			WebElement from = moveHorizontalFrom;

			WebElement to = moveHorizontalTo;
			int xOffset1 = from.getLocation().getX();
			int yOffset1 = from.getLocation().getY();

			System.out.println("xOffset1--->" + xOffset1 + " yOffset1--->" + yOffset1);

			// Secondly, get x and y offset for to object
			int xOffset = to.getLocation().getX();
			int yOffset = to.getLocation().getY();

			System.out.println("xOffset--->" + xOffset + " yOffset--->" + yOffset);

			int xOffset2 = (xOffset1 - xOffset) / 2;
			// yOffset=(yOffset-yOffset1)-20;

			
			builder.dragAndDropBy(from, -xOffset2, 0).perform();

			Thread.sleep(3000);
			String timeOfVideoAfterTrim = playerCurrentTime.getText();
			System.out.println("Time of the video after editing : " + timeOfVideoAfterTrim);

			saveBtn.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SendMailPage(this.driver);
	}
	
	

}
