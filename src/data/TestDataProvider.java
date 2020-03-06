package data;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

	@DataProvider(name = "User")
	public Object[] dataProvider() {
		Object[][] obj = { { "nitinmanojkumar@live.com", "My@12345", "manoj", "TCS", "9879876756",
				"provide your creds", "your pwd", "manojkumarkaalimuthu@gmail.com", "Subject to it" } };
		return obj;
	}
}
