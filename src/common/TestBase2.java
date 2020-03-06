package common;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestBase2 {

	public static WebDriver driver;
	public static String parentWindowID;

	// @SuppressWarnings("deprecation")
	@BeforeSuite
	public void beforeSuite() throws InterruptedException, ClientProtocolException, IOException {

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
		String downloadPath = System.getProperty("user.dir") + "\\extensions\\";

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		// options.addArguments("--headless");
		options.addArguments("--disable-extensions"); // to disable browser extension popup

		ChromeDriverService driverService = ChromeDriverService.createDefaultService();
		driver = new ChromeDriver(driverService, options);

		Map<String, Object> commandParams = new HashMap<>();
		commandParams.put("cmd", "Page.setDownloadBehavior");
		Map<String, String> params = new HashMap<>();
		params.put("behavior", "allow");
		params.put("downloadPath", downloadPath);
		commandParams.put("params", params);
		ObjectMapper objectMapper = new ObjectMapper();
		HttpClient httpClient = HttpClientBuilder.create().build();
		String command = objectMapper.writeValueAsString(commandParams);
		String u = driverService.getUrl().toString() + "/session/" + ((RemoteWebDriver) driver).getSessionId()
				+ "/chromium/send_command";
		HttpPost request = new HttpPost(u);
		request.addHeader("content-type", "application/json");
		request.setEntity(new StringEntity(command));
		httpClient.execute(request);

		// driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterSuite
	public void afterSuite() {
		// driver.quit();
	}

}
