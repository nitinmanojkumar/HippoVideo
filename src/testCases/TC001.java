package testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.TestBase;
import data.TestDataProvider;
import pages.DeleteAccountPage;
import pages.SendMailPage;
import pages.SignIn_Page;
import pages.Success_LoginPage;

public class TC001 extends TestBase {

	SignIn_Page signInPage;
	
	
	@Test(dataProvider = "User",dataProviderClass=TestDataProvider.class)
	public void test(String userNM, String pwd, String name, String company, String phoneNo, String emailId,
			String passwd, String mailTo, String subject) throws InterruptedException, IOException {
		
		SoftAssert softassertion=new SoftAssert();
		driver.get("https://www.hippovideo.io");

		boolean bool = new SignIn_Page(driver).signUp(userNM, pwd);
		if (bool == true) {
			new Success_LoginPage(driver).Login_Business_PersonalisedVideo().enterDetails(name, company, phoneNo)
					.creatingVideo().startRecord().selectAndRecordAndTrim();
			
			boolean bool1=new SendMailPage(driver).compose_EMail();
			softassertion.assertEquals(bool1, true,"Error in composing Email");
			boolean bool2=new SendMailPage(driver).configureEMail(emailId, passwd);
			softassertion.assertEquals(bool2, true,"Error in configuring Email");
			boolean bool3=new SendMailPage(driver).sendEMail(mailTo, subject);
			softassertion.assertEquals(bool3, true,"Error in sending Email");
		}

		else {
			new Success_LoginPage(driver).Login_Business_PersonalisedVideo().enterDetails(name, company, phoneNo)
					.creatingVideo().startRecord().selectAndRecordAndTrim();
			
			boolean bool1=new SendMailPage(driver).compose_EMail();
			softassertion.assertEquals(bool1, true,"Error in composing Email");
			boolean bool2=new SendMailPage(driver).configureEMail(emailId, passwd);
			softassertion.assertEquals(bool2, true,"Error in configuring Email");
			boolean bool3=new SendMailPage(driver).sendEMail(mailTo, subject);
			softassertion.assertEquals(bool3, true,"Error in sending Email");
		}
		
		softassertion.assertAll();

	}
	
	@AfterMethod()
	public void deactivate() {
		new DeleteAccountPage(driver).deleteAccount();
	}

	

}
