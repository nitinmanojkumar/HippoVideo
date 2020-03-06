package testCases;

import org.testng.annotations.Test;
import common.TestBase2;
import pagesForExtension.extensionsPage;

public class TC002 extends TestBase2{

	
	@Test
	public void downloadExtension() {
		driver.get("https://chrome.google.com/webstore/category/extensions");
		new extensionsPage(driver).download();;
	}
	
}
